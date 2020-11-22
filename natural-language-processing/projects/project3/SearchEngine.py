"""
SearchEngine.py

Victor A. Lora
Ron Gerschel

CSI-382
December 1, 2016
"""

from Crawler import Crawler
from DocTable import DocTable
from DocumentParser import DocumentParser
from Index import Index
from Preprocessor import Preprocessor
from pprint import pprint
import numpy as np
import os
import threading
import time

class SearchEngine:

    def __init__(self, searchEngineConfigs, fileDirectory):
        self.fileDirectory = fileDirectory
        shouldCrawl = searchEngineConfigs.getShouldCrawl()
        # When we crawl the DocTable should be recreated
        openDocTableFromFile = not shouldCrawl
        self.docTable = DocTable(openDocTableFromFile, fileDirectory)
        self.index = Index(self.docTable, shouldCrawl, fileDirectory)

        # preprocessorParams[removePunct, removeStopWords, stemWords]
        preprocessorParams = searchEngineConfigs.getPreprocessorParams()
        removePunct = preprocessorParams[0]
        removeStopWords = preprocessorParams[1]
        stemWords = preprocessorParams[2]
        self.preprocessor = Preprocessor(removePunct, removeStopWords, stemWords)

        self.vectors = dict()

        self.initiate(searchEngineConfigs)

    def initiate(self, searchEngineConfigs):
        shouldCrawl = searchEngineConfigs.getShouldCrawl()
        if shouldCrawl:
            crawlerParams = searchEngineConfigs.getCrawlerParams()
            url = crawlerParams[0]
            docRange = crawlerParams[1]
            crawlStartTime = time.time()
            print "Crawling..."
            self.crawl(docRange, url)
            print "Crawl Time: %.2fs" %(time.time() - crawlStartTime)

            print "Indexing Documents..."
            indexStartTime = time.time()
            self.processAndIndexDocs()
            print "Index Time: %.2fs" %(time.time() - indexStartTime)

    def crawl(self, docRange, url):
        domainUrl = url
        urlStack = []
        docTable = self.docTable
        lock = threading.Lock()
        # Initial crawler obtains the links from the original site
        # Takes: startingDocID, endingDocID, domainUrl, startingUrl, urlStack,
        #        docTable, threadLock
        crawler0 = Crawler(0, 1, domainUrl, url, urlStack, docTable, lock,     \
            self.fileDirectory)

        # Four crawlers then run asynchronously
        crawler1 = threading.Thread(target=Crawler, args=(1, docRange / 4,     \
            domainUrl, urlStack.pop(), urlStack, docTable, lock,               \
            self.fileDirectory))
        crawler2 = threading.Thread(target=Crawler, args=(docRange / 4,        \
            2 * docRange / 4, domainUrl, urlStack.pop(), urlStack, docTable,   \
            lock, self.fileDirectory))
        crawler3 = threading.Thread(target=Crawler, args=(2 * docRange / 4,    \
            3 * docRange / 4, domainUrl, urlStack.pop(), urlStack, docTable,   \
            lock, self.fileDirectory))
        crawler4 = threading.Thread(target=Crawler, args=(3 * docRange / 4,    \
            docRange, domainUrl, urlStack.pop(), urlStack, docTable, lock,     \
            self.fileDirectory))
        crawler1.start()
        crawler2.start()
        crawler3.start()
        crawler4.start()
        crawler1.join()
        crawler2.join()
        crawler3.join()
        crawler4.join()

    def processAndIndexDocs(self):
        for ID in self.docTable.getDocTable():
            docID = `ID` + '.txt'
            document = open(os.path.join(self.fileDirectory, docID), 'r').read()
            processedDocument = self.preprocessor.preprocess(document)
            parser = DocumentParser(processedDocument)
            parser.parse()
            for word, freq in parser.getWordFrequencies():
                self.index.addToIndex(word, ID, freq)
                maxFreqDocWord = parser.getMaxFrequencyDocumentWord()
                totalWordCount = parser.getTotalWordCount()
                self.docTable.updateDocTable(ID, maxFreqDocWord, totalWordCount)
        self.index.saveIndex()

    def processAndIndexQuery(self, query):
        queryIndex = Index(self.docTable, True, self.fileDirectory)
        processedQuery = self.preprocessor.preprocess(query)
        parser = DocumentParser(processedQuery)
        parser.parse()
        for word, freq in parser.getWordFrequencies():
            if self.index.wordExistsInDocs(word):
                queryIndex.addToIndex(word, "q", freq)
        tfidf = self.index.calculateTfIdf(queryIndex, processedQuery)
        self.generateVectors(processedQuery, tfidf)
        cosines = self.calculateCosineSimilarities()
        return self.getTopUrls(cosines)


    def generateVectors(self, query, tfidf):
        self.vectors.clear()
        for word in query.split():
            if self.index.wordExistsInDocs(word):
                for docID in self.docTable.getDocTable():
                    weight = self.index.getTfIdfValue(tfidf, word, docID)
                    self.addMagnitudeToVectors(docID, weight)
                weight = self.index.getTfIdfValue(tfidf, word, "q")
                self.addMagnitudeToVectors("q", weight)
        # pprint(self.vectors)

    def addMagnitudeToVectors(self, docID, weight):
        if docID in self.vectors:
            self.vectors[docID].append(weight)
        else:
            self.vectors[docID] = [weight]

    def calculateCosineSimilarities(self):
        cosines = []
        if len(self.vectors) > 0:
            for docID in self.docTable.getDocTable():
                if docID != "q":
                    dotDQ = np.dot(self.vectors[docID], self.vectors["q"])
                    queryVLength = self.calculateVectorLength(self.vectors["q"])
                    docVLength = self.calculateVectorLength(self.vectors[docID])
                    weight = 0
                    if queryVLength != 0 and docVLength != 0:
                        weight = dotDQ / (queryVLength * docVLength)
                    cosines.append((docID, weight))
            cosines.sort(key=lambda tup: tup[1])
            cosines.reverse()
            # print self.cosines
        else:
            print "Query not found"
        return cosines

    def calculateVectorLength(self, vector):
        return np.linalg.norm(vector)

    def getRelevantDocIds(self):
        relevantDocs = [doc[0] for doc in self.cosines if doc[1] != 0]
        print relevantDocs
        return relevant

    def getTopUrls(self, cosines):
        if len(self.vectors) > 0:
            urls = []
            for i in range(10):
                if i < len(self.vectors):
                    docID = cosines[i][0]
                    docUrl = self.docTable.getDocUrl(docID)
                    urls.append(docUrl)
                    print docUrl
            return urls
        return None
