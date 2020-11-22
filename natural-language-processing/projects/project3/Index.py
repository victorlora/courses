"""
Index.py

Victor A. Lora
Ron Gerschel

CSI-382
November 30, 2016
"""

from pprint import pprint
import numpy as np
import os
import pickle

class Index:

    def __init__(self, docTable, index, fileDirectory):
        self.fileDirectory = fileDirectory
        self.docTable = docTable
        self.tfidf = dict()
        if index:
            self.invertedIndex = dict()
        else:
            self.invertedIndex = self.openIndex()

    def addToIndex(self, word, docID, freq):
        if (word in self.invertedIndex):
            self.invertedIndex[word][0] += 1          # Increment document count
            self.invertedIndex[word][1][docID] = freq # Add new doc frequency
        else:
            self.invertedIndex[word] = [1, dict()]    # Add word entry to dict
            self.invertedIndex[word][1][docID] = freq # Add new doc frequency

    def getInvertedIndex(self):
        return self.invertedIndex

    def printInvertedIndex(self):
        pprint(self.invertedIndex)

    def getDocOccurence(self, word):
        if self.wordExistsInIndex(word):
            if "q" in self.invertedIndex[word][1]:
                return self.invertedIndex[word][0] - 1
            return self.invertedIndex[word][0]
        return 0

    def wordExistsInDocs(self, word):
        return self.getDocOccurence(word) != 0

    def getWordDocFrequency(self, word, docID):
        return self.invertedIndex[word][1].get(docID, 0)

    def getMaxFrequencyDocumentQueryWord(self, query, docID):
         maxDocQueryWord = 0
         for word in query.split():
             if self.wordExistsInIndex(word):
                 currentDocWordFreq = self.getWordDocFrequency(word, docID)
             else:
                 currentDocWordFreq = 0
             if currentDocWordFreq > maxDocQueryWord:
                 maxDocQueryWord = currentDocWordFreq
         return maxDocQueryWord

    def getWordDocFrequencies(self, word):
        if self.wordExistsInIndex(word):
            return self.invertedIndex[word][1]
        return 0

    def wordExistsInIndex(self, word):
        if word in self.invertedIndex:
            return True
        return False

    def calculateTfIdf(self, queryIndex, query):
        tfidf = dict()
        for word in query.split():
            if self.wordExistsInIndex(word):
                idf = 0
                if self.wordExistsInDocs(word):
                    idf = 1.0 + np.log10(((1.0 *                            \
                        len(self.docTable.getDocTable()))                   \
                        / (self.getDocOccurence(word))))
                for docID in self.docTable.getDocTable():
                    maxDocQueryWord = \
                        self.getMaxFrequencyDocumentQueryWord(query, docID)
                    tf = 0
                    if maxDocQueryWord > 0:
                        tf = float(self.getWordDocFrequency(word, docID))   \
                            / maxDocQueryWord
                    self.addToTfIdfDict(tfidf, word, docID, tf * idf)
                maxDocQueryWord = \
                    queryIndex.getMaxFrequencyDocumentQueryWord(query, "q")
                tf = 0
                if maxDocQueryWord > 0:
                    tf = float(queryIndex.getWordDocFrequency(word, "q"))   \
                        / maxDocQueryWord
                self.addToTfIdfDict(tfidf, word,  "q", tf * idf)
        return tfidf

    def addToTfIdfDict(self, tfidf, word, docID, value):
        if (word in tfidf):
            tfidf[word].update({docID:value})
        else:
            tfidf[word] = {docID:value}

    def getTfIdfValue(self, tfidf, word, docID):
        if word in tfidf:
            return tfidf[word].get(docID, 0)
        print word.upper()
        return None

    def saveIndex(self):
        filename = "InvertedIndex.pickle"
        with open(os.path.join(self.fileDirectory, filename), 'wb') as handle:
            pickle.dump(self.invertedIndex, handle)

    def openIndex(self):
        filename = "InvertedIndex.pickle"
        with open(os.path.join(self.fileDirectory, filename), 'rb') as handle:
            invertedIndex = pickle.load(handle)
            return invertedIndex
