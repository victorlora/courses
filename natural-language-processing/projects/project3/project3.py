"""
project3.py

Victor A. Lora
Ron Gerschel

CSI-382
November 30, 2016
"""

from SearchEngine import SearchEngine
from SearchEngineConfigs import SearchEngineConfigs
import time

if __name__ == '__main__':
    searchEngineConfigs = SearchEngineConfigs()
    # PreprocessorParams takes: removePunct, removeStopWords, stemWords
    searchEngineConfigs.setPreprocessorParams(True, True, True)
    searchEngineConfigs.setShouldCrawl(False)
    # Crawler Params takes: url, docRange
    searchEngineConfigs.setCrawlerParams("http://muhlenberg.edu/", 10000)
    fileDirectory = "documents"
    engine = SearchEngine(searchEngineConfigs, fileDirectory)

    queries = ["Victor Lora", "Jorge Reyes", "Ron Gerschel"]
    for query in queries:
        queryStartTime = time.time()
        print "\nProcessing Query: " + query
        engine.processAndIndexQuery(query)
        print "Process query time: %.2fs" %(time.time() - queryStartTime)

    # userInput = raw_input("Search: ")
    # while userInput != "quit":
    #     query = userInput
    #     queryStartTime = time.time()
    #     print "Processing Query: " + query
    #     engine.processAndIndexQuery(query)
    #     print "Processed query in: %.2fs" %(time.time() - queryStartTime)
    #     userInput = raw_input("\nSearch: ")
