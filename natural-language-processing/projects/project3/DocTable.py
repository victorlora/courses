import os
import pickle

class DocTable():
    def __init__(self, openFromFile, fileDirectory):
        self.fileDirectory = fileDirectory
        self.errorPages = []
        try:
            if (openFromFile):
                self.docTable = self.openDocTable()
                self.visitedLinks = self.openVisitedLinks()
            else:
                self.docTable = dict()
                self.visitedLinks = []
        except KeyboardInterrupt:
            quit()
        except IOError:
            self.docTable = dict()
            self.visitedLinks = []

    def getDocTable(self):
        return self.docTable

    def getVisitedLinks(self):
        return self.visitedLinks

    def addToDocTable(self, docID, url ):
        self.docTable[docID] = [url]

    def updateDocTable(self, docID, maxWordFreq, docLength):
        self.docTable[docID].append(maxWordFreq)
        self.docTable[docID].append(docLength)

    def getDocUrl(self, docID):
        return self.docTable[docID][0]

    def getDocLength(self, docID):
        return self.docTable[docID][2]

    def getDocMaxWordFreq(self, docID):
        return self.docTable[docID][1]

    def addLinkToVisited(self, url):
        self.visitedLinks.append(url)

    def getVisitedLinks(self):
        return self.visitedLinks

    def existsInVistedLinks(self, url):
        return url in self.visitedLinks

    def addLinkToErrorPages(self, url):
        self.errorPages.append(url)

    def existsInErrorPages(self, url):
        return url in self.errorPages

    def saveDocTable(self):
        filename = "docTable.pickle"
        with open(os.path.join(self.fileDirectory, filename), 'wb') as handle:
            pickle.dump(self.docTable, handle)

    def openDocTable(self):
        filename = "docTable.pickle"
        with open(os.path.join(self.fileDirectory, filename), 'rb') as handle:
            newObject = pickle.load(handle)
            return newObject

    def saveVisitedLinks(self):
        filename = "visitedLinks.pickle"
        with open(os.path.join(self.fileDirectory, filename), 'wb') as handle:
            pickle.dump(self.visitedLinks, handle)

    def openVisitedLinks(self):
        filename = "visitedLinks.pickle"
        with open(os.path.join(self.fileDirectory, filename), 'rb') as handle:
            newObject = pickle.load(handle)
            return newObject
