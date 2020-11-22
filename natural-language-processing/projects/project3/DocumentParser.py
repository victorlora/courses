from pprint import pprint

class DocumentParser:

    def __init__(self, entry):
        self.entry = entry
        self.words = dict()

    # Parses the entry and calculates the word frequencies
    def parse(self):
        if self.entry:
            self.totalWordCount = 0
            for w in self.entry.split():
                self.incrementWordCount(w)
                self.totalWordCount += 1

    # Increments the word frequency for a specific word
    def incrementWordCount(self, word):
        if (word in self.words):
            self.words[word] += 1
        else:
            self.words[word] = 1

    # Returns totalWordCount
    def getTotalWordCount(self):
        return self.totalWordCount

    # Returns the words dictionary containing the word frequency count
    def getWordFrequencies(self):
        return self.words.items()

    # Returns the word frequency for a specific word
    def getWordFrequency(self, word):
        return self.words[word]

    def getMaxFrequencyDocumentWord(self):
        maxFrequency = 0
        for word, freq in self.words.items():
            if freq > maxFrequency:
                maxFrequency = freq

        return maxFrequency
