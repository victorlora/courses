"""
project1_hmm.py

Victor A. Lora
Dr. Reyes
CSI-382
October 14, 2016
"""


from math import *
from nltk.corpus import brown
from pprint import pprint
import collections
import operator
import nltk
import numpy as np
import re
import sys
import time

tag_frequency  = dict()
tag_tag_count  = dict()
tag_bigram = dict()
tag_word_count = dict()
lex_bigram = dict()
accuracy_sum = 0
accuracy_count = 0

def main():
    # Set up timer
    start_time = time.time()

    # Corpus
    brown_tagged_sents = brown.tagged_sents()

    # Train model
    training_set = brown_tagged_sents[:(len(brown_tagged_sents) * 2/3)]
    train(training_set) 

    # Test model
    testing_set = brown_tagged_sents[(len(training_set) + 1):len(training_set) + 100]
    print "Testing set length: %d" %len(testing_set)
    #testing_set = brown_tagged_sents[(len(training_set) + 1):] 
    print "Testing..."
    for i in range(len(testing_set)):
        try:
            test(brown_tagged_sents[i])
        except (ValueError, IndexError):
            print i
        percent_complete = float(i + 1) / len(testing_set) * 100
        sys.stdout.write('%.2f percent complete\r' %percent_complete)
        sys.stdout.flush()
    print("\n")
    
    total_accuracy = accuracy_sum / accuracy_count
    print "Total Accuracy: %.2f" %(total_accuracy * 100)

    # Print runtime in seconds
    print("--- %.2f seconds ---" % (time.time() - start_time))


def train(training_set):
    print "Training..."
    for i in range(len(training_set)):
        generate_counts(training_set[i])
        percent_complete = float(i)/len(training_set) * 100
        sys.stdout.write('%.2f percent complete\r' % percent_complete)
        sys.stdout.flush()
    generate_tag_bigram()
    generate_lex_bigram()
    print "\n"


def generate_counts(sentence):
    processed_sentence = preprocess_sentence(sentence)
    word0, tag0 = processed_sentence[0]
    calculate_tag_frequency(tag0)
    for i in range((len(processed_sentence) - 1)):
        word0, tag0 = processed_sentence[i]
        word1, tag1 = processed_sentence[i + 1]
        calculate_tag_frequency(tag1)
        calculate_tag_tag_count(tag0, tag1)
        calculate_tag_word_count(word1, tag1)

# Append the <s></s> tags, lower case the word & remove punctuation
def preprocess_sentence(sentence):
    processed_sentence = []
    processed_sentence.append(("<s>", "<s>"))
    for word, tag in sentence:
        #word = word.lower()
        #if re.match("\.|\(|\)|,|\:|--|''|``|'|NIL", tag):
        #    continue
        #else:
         processed_sentence.append((word, tag))
    processed_sentence.append(("</s>","</s>"))
    return processed_sentence


# Calculate C(tag)
def calculate_tag_frequency(tag):
    if tag in tag_frequency:
        tag_frequency[tag] = tag_frequency[tag] + 1
    else:
        tag_frequency[tag] = 1


# Calculate C(tag1 | tag0)
def calculate_tag_tag_count(tag0, tag1):
    if tag0 in tag_tag_count: 
        if tag1 in tag_tag_count[tag0]:
            tag_tag_count[tag0][tag1] = tag_tag_count[tag0][tag1] + 1
        else:
            tag_tag_count[tag0].update( {tag1:1} )
    else:
        tag_tag_count[tag0] = {tag1:1}


# Calculate Pr(tag1 | tag0) after Laplace Smoothing
def generate_tag_bigram():
    for tag0 in tag_tag_count.keys():
        for tag1 in tag_tag_count[tag0].keys():
            if tag0 in tag_bigram:
                tag_bigram[tag0].update( { tag1:calculate_tag_tag_pr(tag0, tag1) } )
            else:
                tag_bigram[tag0] = { tag1:calculate_tag_tag_pr(tag0, tag1) }


# Return Pr(tag1 | tag0) after Laplace Smoothing
def calculate_tag_tag_pr(tag0, tag1):
    tagTagPr = np.log10(1) - np.log10((tag_frequency.get(tag0, 0) + len(tag_frequency)))
    if (tag0 in tag_tag_count) and (tag1 in tag_tag_count[tag0]):
        tagTagPr = np.log10((tag_tag_count[tag0][tag1] + 1)) \
                    - np.log10((tag_frequency.get(tag0, 0) + len(tag_frequency)))
    return tagTagPr


# Calculate C(word | tag)
def calculate_tag_word_count(word, tag):
    if tag in tag_word_count:
        if word in tag_word_count[tag]:
            tag_word_count[tag][word] = tag_word_count[tag][word] + 1
        else:
            tag_word_count[tag].update( {word:1} )
    else:
        tag_word_count[tag] = {word:1}


def generate_lex_bigram():
    for tag in tag_word_count.keys():
        for word in tag_word_count[tag].keys():
            if tag in lex_bigram:
                lex_bigram[tag].update( { word:calculate_word_tag_pr(word, tag) } )
            else:
                lex_bigram[tag] = { word:calculate_word_tag_pr(word, tag) }

# Return Pr(word | tag) after Laplace Smoothing
def calculate_word_tag_pr(word, tag):
    wordTagPr = np.log10(1) - np.log10((tag_frequency.get(tag, 0) + len(tag_frequency)))
    if (tag in tag_word_count) and (word in tag_word_count[tag]):
        wordTagPr = np.log10((tag_word_count[tag][word] + 1)) \
                    - np.log10((tag_frequency.get(tag, 0) + len(tag_frequency)))
    return wordTagPr


# Viterbi
def test(sentence):
    score = dict()
    backptr = dict()

    processed_sentence = preprocess_sentence(sentence)
    tag_freq_items = tag_frequency.items()
    tag_set_length = len(tag_freq_items)

    word1 = processed_sentence[1][0]
    for tag, count in tag_freq_items:
        wordTagPr = get_tag_word_pr(tag, word1, tag_set_length) 
        tagTagPr = get_tag_tag_pr("<s>", tag, tag_set_length)
        add_to_score(word1, tag, (wordTagPr + tagTagPr), score)
        backptr[tag] = {word1 : 0}

    for w in range(2, len(processed_sentence)):
        prev_word = processed_sentence[w-1][0]
        current_word = processed_sentence[w][0]
        
        for t in range(len(tag_freq_items)):
            tagT = tag_freq_items[t][0]
            wordTagPr = get_tag_word_pr(tagT, current_word, tag_set_length)

            # List of Score(j, w-1) * Pr(TagT | TagJ) where j = 1 to T
            score_lst = [score[prev_word][tagJ] + get_tag_tag_pr(tagJ, tagT, tag_set_length) \
                            for tagJ, countJ in tag_freq_items]
            max_score_pr = max(score_lst)
            max_index = score_lst.index(max_score_pr)

            add_to_score(current_word, tagT, (wordTagPr + max_score_pr), score)
            backptr[tagT].update( {current_word:max_index} )

    last_word_index = len(processed_sentence) - 1
    last_word = processed_sentence[last_word_index][0]
    my_tag_seq = []
    my_tag_seq.append((last_word, get_sequence_tag(last_word, score)))

    for w in range(last_word_index - 1, 0, -1):
        prev_word = processed_sentence[w + 1][0]
        prev_tag = my_tag_seq[-1][1]
        index = backptr[prev_tag][prev_word]
        this_tag = tag_freq_items[index][0]
        this_word = processed_sentence[w][0]
        my_tag_seq.append((this_word, this_tag))
    my_tag_seq.reverse()

    brown_sent = processed_sentence[1:]
    #print  brown_sent
    #print  my_tag_seq
    update_total_accuracy(my_tag_seq, brown_sent)


def get_tag_tag_pr(tag0, tag1, tag_set_length):
    if (tag0 in tag_bigram) and (tag1 in tag_bigram[tag0]):
        return tag_bigram[tag0][tag1]
    else:
        tagTagPr = calculate_tag_tag_pr(tag0, tag1) - np.log10(tag_set_length)
        if tag0 in tag_bigram:
            tag_bigram[tag0].update({tag1: tagTagPr})
        else:
            tag_bigram[tag0] = {tag1: tagTagPr}
        return tagTagPr

def get_tag_word_pr(tag, word, tag_set_length):
    if (tag in lex_bigram) and (word in lex_bigram[tag]):
        return lex_bigram[tag][word]
    else:
        wordTagPr = calculate_word_tag_pr(word, tag) - np.log10(tag_set_length)
        if tag in lex_bigram:
            lex_bigram[tag].update({word: wordTagPr})
        else:
            lex_bigram[tag] = {word: wordTagPr}
        return wordTagPr


def add_to_score(word, tag, value, score):
    if word in score:
        score[word].update( {tag:value} )
    else:
        score[word] = {tag:value}


# Returns the Tag associated with the maximum value in score[word]
def get_sequence_tag(word, score):
    return max(score[word].iteritems(), key=operator.itemgetter(1))[0]


def update_total_accuracy(my_tag_seq, brown_sent):
    global accuracy_sum
    global accuracy_count
    
    correct = 0
    for i in range(len(brown_sent)):
        brown_tag = brown_sent[i][1]
        my_tag = my_tag_seq[i][1]
        # Counts a tag of NN-HL compared to NN or vise versa as correct
        if (my_tag == brown_tag): #or (brown_tag in my_tag):
            correct = correct + 1.0
    accuracy = correct/len(brown_sent)
    accuracy_sum += accuracy
    accuracy_count += 1
    #print "Accuracy: %.2f" %(accuracy)

    
main()
