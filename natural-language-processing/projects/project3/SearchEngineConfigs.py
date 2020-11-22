"""
SearchEngineConfigs.py

Victor A. Lora
Ron Gerschel

CSI-382
November 30, 2016
"""

class SearchEngineConfigs:

    def __init__(self):
        self.removePunct = True
        self.removeStopWords = True
        self.stemWords = True
        self.shouldCrawl = True
        self.shouldIndex = True
        self.url = ""
        self.docRange = 0

    def setPreprocessorParams(self, removePunct, removeStopWords, stemWords):
        self.removePunct = removePunct
        self.removeStopWords = removeStopWords
        self.stemWords = stemWords

    def getPreprocessorParams(self):
        return (self.removePunct, self.removeStopWords, self.stemWords)

    def setCrawlerParams(self, url, docRange):
        self.url = url
        self.docRange = docRange

    def getCrawlerParams(self):
        return (self.url, self.docRange)

    def setShouldCrawl(self, shouldCrawl):
        self.shouldCrawl = shouldCrawl

    def getShouldCrawl(self):
        return self.shouldCrawl
