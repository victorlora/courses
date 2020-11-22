from Crawler import Crawler
from DocTable import DocTable
import threading
import time

def crawl(docRange, url):
    domainUrl = url
    urlStack = []
    docTable = DocTable(False, "presentation")
    lock = threading.Lock()
    crawler0 = Crawler(0, docRange, url, url, urlStack, docTable, lock, "presentation")

if __name__ == '__main__':
    print "Crawling..."
    crawlStartTime = time.time()
    crawl(50, "http://muhlenberg.edu/")
    print "Crawl Time: %.2fs" %(time.time() - crawlStartTime)
