"""
Preprocesor.py

Victor A. Lora
Ron Gerschel

CSI-382
November 30, 2016
"""

from nltk.stem.snowball import SnowballStemmer
import re

class Preprocessor:

    def __init__(self, shouldRemovePunct, shouldRemoveStopWords, shouldStemWords):
        self.stopWords = open('EnglishStopWords.config', 'r').read()
        self.stemmer = SnowballStemmer('english')
        self.shouldRemovePunct = shouldRemovePunct
        self.shouldRemoveStopWords = shouldRemoveStopWords
        self.shouldStemWords = shouldStemWords

    # Returns entry post pre-processing
    def preprocess(self, entry):
        if entry:
            entry = entry.lower() # Lowercase all words in the entry
            entry = re.sub( '\s+', ' ', entry).strip() # Strip extra whitespace
            if (self.shouldRemovePunct):
                entry = self.removePunct(entry)
            if (self.shouldRemoveStopWords):
                entry = self.removeStopWords(entry).strip()
            if (self.shouldStemWords):
                entry = self.stemWords(entry)
            return entry
        else:
            return ""

    # Removes the stop words from the entry
    def removeStopWords(self, entry):
        cleanEntry = ""
        for w in entry.split():
            if w not in self.stopWords:
                cleanEntry += w + " "
        return cleanEntry

    # Stem words
    def stemWords(self, entry):
        stemmedEntry = ""
        for w in entry.split():
            stemmedWord = self.stemmer.stem(w)
            stemmedEntry += stemmedWord + " "
        return stemmedEntry

    # Remove punctuation
    def removePunct(self, entry):
        postPunctEntry = re.sub(r'[^\w\s]','',entry)
        return postPunctEntry
