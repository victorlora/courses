from DocumentParser import DocumentParser
from Preprocessor import Preprocessor
from DocTable import DocTable
from Index import Index
from pprint import pprint
import numpy as np
import os

docTable = DocTable(False, "hw6")
index = Index(docTable, True, "hw6")
preprocessor = Preprocessor(False, False, False)
vectors = dict()
cosines = []

def indexDocs():
    for ID in range(1, 5):
        docTable.addToDocTable(ID, "")
        docID = `ID` + '.hw6'
        document = open(os.path.join("hw6",docID), 'r').read()
        processedDocument = preprocessor.preprocess(document)
        parser = DocumentParser(processedDocument)
        parser.parse()
        for word, freq in parser.getWordFrequencies():
            index.addToIndex(word, ID, freq)
            maxFreqDocWord = parser.getMaxFrequencyDocumentWord()
            totalWordCount = parser.getTotalWordCount()
            docTable.updateDocTable(ID, maxFreqDocWord, totalWordCount)
    pprint(index.invertedIndex)

def processAndIndexQuery(query):
    queryIndex = Index(docTable, True, "hw6")
    processedQuery = preprocessor.preprocess(query)
    parser = DocumentParser(processedQuery)
    parser.parse()
    for word, freq in parser.getWordFrequencies():
        if index.wordExistsInDocs(word):
            queryIndex.addToIndex(word, "q", freq)
    tfidf = index.calculateTfIdf(queryIndex, processedQuery)
    generateVectors(tfidf, query)
    calculateCosineSimilarities()

def generateVectors(tfidf, query):
    vectors.clear()
    processedQuery = preprocessor.preprocess(query)
    for word in processedQuery.split():
        if index.wordExistsInDocs(word):
            for docID in docTable.getDocTable():
                weight = index.getTfIdfValue(tfidf, word, docID)
                addMagnitudeToVectors(docID, weight)
            weight = index.getTfIdfValue(tfidf, word, "q")
            addMagnitudeToVectors("q", weight)
    pprint(vectors)

def addMagnitudeToVectors(docID, weight):
    if docID in vectors:
        vectors[docID].append(weight)
    else:
        vectors[docID] = [weight]

def calculateCosineSimilarities():
    for docID in docTable.getDocTable():
        if docID != "q":
            dotDQ = np.dot(vectors[docID], vectors["q"])
            queryVLength = calculateVectorLength(vectors["q"])
            docVLength = calculateVectorLength(vectors[docID])
            weight = 0
            if queryVLength != 0 and docVLength != 0:
                weight = dotDQ / (queryVLength * docVLength)
            cosines.append((docID, weight))
    cosines.sort(key=lambda tup: tup[1])
    cosines.reverse()
    print cosines

def calculateVectorLength(vector):
    return np.linalg.norm(vector)

if __name__ == '__main__':
    indexDocs()
    processAndIndexQuery("Time is not squared")
