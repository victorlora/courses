import nltk
import re
from pprint import pprint
from nltk.corpus import brown

def main():
    unigram = dict()
    bigram  = dict()
    tagged_words = brown.tagged_words()

    NN_reg_count = 0
    NN_re_count = 0
    for word, tag in tagged_words:
        if re.match("[NN]", tag):
            NN_reg_count += 1
        if (re.search("NN", tag)):
            NN_re_count += 1
    print "NN_reg_count: %d" %NN_reg_count
    print "NN_re_count: %d" %NN_re_count

    for word, tag in tagged_words:
        if tag in unigram:
            unigram[tag] = unigram.get(tag) + 1
        else:
            unigram[tag] = 1
    #pprint(unigram)
    print "Unigram NN count %d" %unigram["NN"]

    for i in range(len(tagged_words) - 1):
        word0, tag0 = tagged_words[i]
        word1, tag1 = tagged_words[i + 1]

        if tag0 in bigram: 
            if tag1 in bigram[tag0]:
                bigram[tag0][tag1] = bigram[tag0][tag1] + 1
            else:
                bigram[tag0].update({tag1:1})
        else:
            bigram[tag0] = {tag1:1}
    #pprint(bigram)

main()
