"""
Crawler.py

Victor A. Lora
Ron Gerschel

CSI-382
November 30, 2016
"""

from bs4 import BeautifulSoup, NavigableString
from pprint import pprint
import os
import re
import sys
reload(sys)
sys.setdefaultencoding('utf-8')
import threading
import time
import urlparse
import urllib2


class Crawler(threading.Thread):

    def __init__(self, startID, docRange, domainUrl, startingUrl, urlStack,   \
            docTable, lock, fileDirectory):
        threading.Thread.__init__(self)
        self.fileDirectory = fileDirectory
        self.lock = lock
        self.domainUrl = self.formatUrl(domainUrl)
        self.url = self.formatUrl(startingUrl)
        self.urlStack = urlStack
        self.urlStack.append(self.url)
        self.doNotVisit = []
        self.docTable = docTable
        self.polite = True
        self.robot()
        self.crawl(startID, docRange)
        print self.name + " Done"

    def crawl(self, startID, docRange):
        ID = startID
        while (len(self.urlStack) > 0 and ID < docRange):
            self.lock.acquire()     # Lock the threads
            self.url = self.getNextUrl()
            if self.url == None:
                self.lock.release()
                return
            while (self.linkHasBeenVisited(self.url) or \
                    self.linkExistsInUrlLinks(self.url)):
                self.url = self.getNextUrl()
                if self.url == None:
                    self.lock.release()
                    return

            html = self.getWebsiteHtml(self.url)
            while(self.pageNotFound(html)):
                self.url = self.getNextUrl()
                if self.url == None:
                    self.lock.release()
                    return
                html = self.getWebsiteHtml(self.url)
            ID += 1
            print ID
            print self.url

            self.docTable.addToDocTable(ID, self.url)
            self.docTable.addLinkToVisited(self.url)

            soup = BeautifulSoup(html, "lxml")
            html = self.getCleanHtml(soup)
            docID = `ID` + '.txt'
            doc = open(os.path.join(self.fileDirectory, docID), 'w')
            doc.write(html)

            try:
                self.docTable.saveDocTable()
                self.docTable.saveVisitedLinks()
            except KeyboardInterrupt:
                quit()
            finally:
                self.lock.release() # Release lock

            self.getLinks(soup)     # Push all links on this page to the stack

    def formatUrl(self, currentUrl):
        formattedUrl = ""
        if (currentUrl.startswith('http')):
            formattedUrl = currentUrl
        elif (currentUrl.startswith('/')):
            formattedUrl = urlparse.urljoin(self.url, currentUrl)
        formattedUrl = formattedUrl + "/"
        formattedUrl = re.sub("//+", "/", formattedUrl).replace(":/", "://")
        formattedUrl = formattedUrl.replace("www.", "")         # Strip 'www.'
        formattedUrl = formattedUrl.replace(".html/", ".html")
        formattedUrl = formattedUrl.replace(".htm/", ".htm")
        return formattedUrl

    def getNextUrl(self):
         if len(self.urlStack) > 0:
             return self.urlStack.pop()
         else:
             print "The stack is empty"
             return None

    def getWebsiteHtml(self, currentUrl):
        try:
            response = urllib2.urlopen(currentUrl, None, 60)
            html = response.read()
            return html
        except KeyboardInterrupt:
            quit()
        except:
            html = "<html> <p> " + currentUrl + " returned a 404 or other error.</p></html>"
            self.addPageToErrorList(currentUrl)
            return ""

    def pageNotFound(self, html):
        if "page not found" in html.lower():
            print self.url + " returned 'Page Not Found'"
            self.addPageToErrorList(self.url)
            return True
        return False

    def addPageToErrorList(self, url):
        self.docTable.addLinkToErrorPages(url)

    def getCleanHtml(self, soup):
        for script in soup('script'):
            if script:
                script.extract()
        for style in soup('style'):
            if style:
                style.extract()
        html = re.sub('\n+', '\n', soup.get_text()).strip()
        return html

    def getLinks(self, soup):
        links = soup.find_all('a')
        self.appendLinks(links)

    def appendLinks(self, links):
        startTime = time.time()
        for link in links:
            if (time.time() - startTime < 180):
                href = link.get('href')
                if href:
                    formattedUrl = self.formatUrl(href)
                    if self.shouldBeAppended(formattedUrl):
                        if self.polite and self.robotCheck(formattedUrl):
                            self.urlStack.append(formattedUrl)
                        else:
                            self.urlStack.append(formattedUrl)
            else:
                break

    def robot(self):
        robot = self.url + "robots.txt"
        robotText = urllib2.urlopen(robot).read()
        #print robotLinks
        if 'Page Not Found' in robotText:
            self.polite = False;
            if not self.polite:
                print "Ignoring all politeness"
        else:
            try:
                print "Page Found"
                robotLinks = urllib2.urlopen(robot).readlines()
                self.polite = True;
                for line in robotLinks:
                    m = re.search(r"Disallow:\s\/(.*)", line)
                    if m:
                        result = self.url + m.group(1)
                        self.doNotVisit.append(result)
                        ## If page not found, automatically polite
            except KeyboardInterrupt:
                quit()
            except:
                print "Exception"

    def robotCheck(self, formattedUrl):
        for link in self.doNotVisit:
            if link in formattedUrl:
                print("Not Allowed")
                return False;
        return True;

    def shouldBeAppended(self, currentUrl):
        return currentUrl != "" \
            and self.domainUrl in currentUrl \
            and self.hasAllowedExtensions(currentUrl) \
            and not self.docTable.existsInErrorPages(currentUrl) \
            and not self.linkHasBeenVisited(currentUrl) \
            and not self.linkExistsInUrlLinks(currentUrl)

    def hasAllowedExtensions(self, currentUrl):
        disallowedExtensions = ["#", ".pdf", ".doc", ".docx", ".xlsx", ".ppt", \
            ".jpg", ".png", ".gif", ".zip"]
        for ext in disallowedExtensions:
            if ext in currentUrl:
                return False
        return True

    def linkHasBeenVisited(self, currentUrl):
        if (self.docTable.existsInVistedLinks(currentUrl)):
            return True
        return False

    def linkExistsInUrlLinks(self, currentUrl):
        if (currentUrl in self.urlStack):
            return True
        try:
            finalUrl = urllib2.urlopen(currentUrl, timeout=60).geturl()
            formattedFinalUrl = self.formatUrl(finalUrl)
            if (formattedFinalUrl in self.urlStack):
                return True
            elif (self.linkHasBeenVisited(formattedFinalUrl)):
                return True
            else:
                return False
        except KeyboardInterrupt:
            quit()
        except IOError:
            print currentUrl + " returned timeout or 404"
            self.addPageToErrorList(currentUrl)
            return True
        except:
            return True
