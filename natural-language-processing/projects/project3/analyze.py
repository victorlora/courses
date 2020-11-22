from DocumentParser import DocumentParser
from Preprocessor import Preprocessor
from DocTable import DocTable
from Index import Index
from pprint import pprint
import numpy as np
import threading
import os
import re

fileDirectory = "analysis"
docTable = DocTable(True, fileDirectory)
preprocessor = Preprocessor(True, True, True)
queryCompare = dict()
_queryDict = dict()
results = dict()
avgPrecision = []
avgRecall = []
_index = Index(docTable, True, fileDirectory)
def main():
    indexDocs(_index)
    # TODO: FOR LOOP through the queries
    with open(os.path.join(fileDirectory, "cranqrel")) as t:
        for line in t:
            line = line.split()
            queryNum = line[0]
            queryAns = line[1]
            if queryNum in queryCompare.keys():
                queryCompare[queryNum].append(queryAns)
            else:
                queryCompare[queryNum] = list(queryAns)
    #pprint(queryCompare)

    with open(os.path.join(fileDirectory, "cran.qry")) as f:
        fileValue = f.read()
        for line in fileValue.split('.W'):
            y = re.search(r'(\.I)(\s)(\d*)', line)
            m = re.search(r'((.*\s*)*)(\.I)(.*)', line)
            if m:
                queryID = int(y.group(3).lstrip("0"))
                queryTxt = m.group(1).strip()
                _queryDict[queryID-1] = queryTxt

    thread1Dict = _queryDict.items()[1:len(_queryDict)/4]
    #print len(thread1Dict)
    thread2Dict = _queryDict.items()[len(_queryDict)/4:2*len(_queryDict)/4]
    #print len(thread2Dict)
    thread3Dict = _queryDict.items()[2*len(_queryDict)/4:3*len(_queryDict)/4]
    #print len(thread3Dict)
    thread4Dict = _queryDict.items()[3*len(_queryDict)/4:]
    #print len(thread4Dict)

    thread1 = threading.Thread(target=searchQueries, args=(thread1Dict, _index, 1))
    thread2 = threading.Thread(target=searchQueries, args=(thread2Dict, _index, 2))
    thread3 = threading.Thread(target=searchQueries, args=(thread3Dict, _index, 3))
    thread4 = threading.Thread(target=searchQueries, args=(thread4Dict, _index, 4))
    thread1.start()
    thread2.start()
    thread3.start()
    thread4.start()
    thread1.join()
    thread2.join()
    thread3.join()
    thread4.join()



    print("Calculated Average")
    calculateAverage()

def searchQueries(queryDict, index, threadID):
    for queryID, queryText in queryDict:
        #print "Thread:", threadID
        #print "Query:", queryID
        if str(queryID) in queryCompare.keys():
            tfidf = processAndIndexQuery(index, queryText)
            vectors = generateVectors(index, queryText, tfidf)
            cosines = calculateCosineSimilarities(vectors)
            returnedIds = getRelevantDocIds(cosines)
            compareDocs(queryID, returnedIds)

def indexDocs(index):
    for ID in range(1, 1401):
        docTable.addToDocTable(ID, "")
        docID = `ID` + ".analysis"
        document = open(os.path.join(fileDirectory, docID), 'r').read()
        processedDocument = preprocessor.preprocess(document)
        parser = DocumentParser(processedDocument)
        parser.parse()
        for word, freq in parser.getWordFrequencies():
            index.addToIndex(word, ID, freq)
            maxFreqDocWord = parser.getMaxFrequencyDocumentWord()
            totalWordCount = parser.getTotalWordCount()
            docTable.updateDocTable(ID, maxFreqDocWord, totalWordCount)
    # pprint(index.invertedIndex)

def processAndIndexQuery(index, query):
    queryIndex = Index(docTable, True, fileDirectory)
    processedQuery = preprocessor.preprocess(query)
    parser = DocumentParser(processedQuery)
    parser.parse()
    for word, freq in parser.getWordFrequencies():
        if index.wordExistsInDocs(word):
            queryIndex.addToIndex(word, "q", freq)
    tfidf = index.calculateTfIdf(queryIndex, processedQuery)
    return tfidf


def generateVectors(index, query, tfidf):
    vectors = dict()
    processedQuery = preprocessor.preprocess(query)
    for word in processedQuery.split():
        if index.wordExistsInDocs(word) and index.wordExistsInIndex(word):
            for docID in docTable.getDocTable():
                weight = index.getTfIdfValue(tfidf, word, docID)
                addMagnitudeToVectors(vectors, docID, weight)
            weight = index.getTfIdfValue(tfidf, word, "q")
            addMagnitudeToVectors(vectors, "q", weight)
    pprint(vectors)
    return vectors

def addMagnitudeToVectors(vectors, docID, weight):
    if docID in vectors:
        vectors[docID].append(weight)
    else:
        vectors[docID] = [weight]

def calculateCosineSimilarities(vectors):
    cosines = []
    if len(vectors) > 0:
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
    else:
        print "Query not found"
    return cosines

def getRelevantDocIds(cosines):
    # cosineValues = [doc[1] for doc in cosines]
    relevantDocs = [doc[0] for doc in cosines if doc[1] != 0]
    return relevantDocs

def calculateVectorLength(vector):
    return np.linalg.norm(vector)

def compareDocs(queryID, returnedIds):
    w = 0
    x = 0
    y = 0
    z = 0
    precision = 0
    recall = 0

    #print("Query ID: ")
    #pprint(queryID)

    #All of the relevant documents
    for d in queryCompare[""+`queryID`]:
        #If retreived add to W
        d = int(d)
        if d in returnedIds:
            #print d, " is in both"
            w += 1
        #If not retreived add to X
        if d not in returnedIds:
            #print d, " was not retreived"
            x +=1
    #All of the retreived Documents
    for r in returnedIds:
        #If not in relevant documents add to Y
        r = "" + `r`
        if r not in queryCompare[""+`queryID`]:
            #print r, " not relevant"
            y += 1

    for docID in docTable.getDocTable():
        if docID != "q":
            docID = "" + `docID`
            if docID not in queryCompare[""+`queryID`] and int(docID) not in returnedIds:
                z += 1

    recallB = w+x
    precisionB = w+y

    if recallB == 0:
        recall = "--"
    else:
        recall = float(w)/recallB

    if precisionB == 0:
        precision = "--"
    else:
        precision = float(w)/precisionB

    if queryID in results.keys():
        results[queryID].update({"Recall: ": recall, "Precision: ": precision})
    else:
        results[queryID] = ({"Recall: ": recall, "Precision: ": precision})

    if recall != "--" and precision != "--":
        avgPrecision.append(precision)
        avgRecall.append(recall)


def calculateAverage():
    averageP = 0
    for precision in avgPrecision:
        averageP = averageP + precision

    averageP = averageP/(len(avgPrecision)+1)
    print ("Average Precision: ", averageP)

    averageR = 0
    for recall in avgRecall:
        averageR = averageR + recall
    #print("averageR", averageR)

    averageR = averageR/(len(avgRecall)+1)
    print ("Average Recall: ", averageR)

main()
