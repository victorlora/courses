from SearchEngine import SearchEngine
from SearchEngineConfigs import SearchEngineConfigs

if __name__ == '__main__':
    searchEngineConfigs = SearchEngineConfigs()
    searchEngineConfigs.setPreprocessorParams(True, True, True)
    searchEngineConfigs.setShouldCrawl(True)
    searchEngineConfigs.setCrawlerParams("http://muhlenberg.edu/", 50)

    print "Crawling 50 sites with multithreading"
    engine = SearchEngine(searchEngineConfigs, "presentation")
