"""
hw3_ngrams.py

Victor A. Lora
Dr. Reyes
CSI-382
September 21, 2016
"""

import re

unigram_dict = dict()
bigram_dict  = dict()

def main():

    print "\nWelcome to uni-bigram creator.\n"
    keep_going = True

    while keep_going:
        user_input = print_menu()

        if user_input == '1':
            create_unigram_and_bigram()
        elif user_input == '2':
            search_unigram()
        elif user_input == '3':
            search_bigram()
        elif user_input == '4':
            print "\nBye\n"
            keep_going = False
        else:
            print "\nInvalid input.\n"

def print_menu():
    user_input = raw_input("Pick an option\n\n"
                    + "1. Create unigram and bigram\n\n"
                    + "2. Search for unigram\n\n"
                    + "3. Search for bigram\n\n"
                    + "4. Exit\n\n")
    return user_input

def create_unigram_and_bigram():
    file_name = raw_input("\nEnter file name: ")

    processed_file_name = preprocess_file(file_name)
    create_unigram(processed_file_name)
    create_bigram(processed_file_name)

    print "\nUnigram and bigram created successfully\n"

# Preprocessing removes all punctuation and replaces it with PUNCT,
# lower cases all words and strips extra white spaces, tabs and line-breaks
# Removing punctuation removes ambiguity between words like 'your' and 'your.'
def preprocess_file(file_name):
    file = open(file_name, 'r')
    processed_file_name = file_name + ".processed"
    processed_file = open(processed_file_name, 'w')

    line = file.read()
    new_line = re.sub(r'([^\n])\n', r'\1 ', line)
    new_line = re.sub('\s+', ' ', new_line)
    new_line = re.sub("[^A-Za-z0-9\s|\'|-]", ' PUNCT ', new_line)

    # Ron Gerschel assisted me with the regex expression for removing
    # apostrophes being used as quotes below
    new_line = re.sub('[\s][\']+', ' PUNCT ', new_line)
    new_line = re.sub('[\'][\s]+', ' PUNCT ', new_line)
    new_line = new_line.lower()

    # Add <s> </s> around sentences constructed with punctuation
    ## Because the method below adds <s> </s> tags around sentences
    ## with punctuation, it also adds it around lines that end with
    ## ','s or any punctuation for that matter. As a result, I'm commenting
    ## lines 60-62 because I don't know if it will affect the data

    #new_line = re.sub('PUNCT\s', 'PUNCT </s> <s> ', new_line)
    #new_line = '<s> ' + new_line + '</s>'
    #new_line = re.sub('</s> <s> </s>', '</s>', new_line)

    processed_file.write(new_line)

    file.close()
    processed_file.close()

    return processed_file_name

def create_unigram(file_name):
    file = open(file_name, 'r')
    for line in file:
        for word in line.split():
            value = unigram_dict.get(word, -1)
            if (value != -1):
                unigram_dict[word] = value + 1
            else:
                unigram_dict[word] = 1
    file.close()

def create_bigram(file_name):
    file = open(file_name, 'r')
    for line in file:
        line_array = line.split()
        for i in range(len(line_array) - 1):
            Wi_1 = line_array[i].lower()
            Wi   = line_array[i + 1].lower()

            if (bigram_dict.get(Wi_1, -1) != -1):
                if (bigram_dict[Wi_1].get(Wi, -1) != -1):
                    bigram_dict[Wi_1][Wi] = bigram_dict[Wi_1][Wi] + 1
                else:
                    bigram_dict[Wi_1].update({Wi:1})
            else:
                bigram_dict[Wi_1] = {Wi:1}
    file.close()

def search_unigram():
    search_word = raw_input("\nEnter word: ")
    count = unigram_dict.get(search_word.lower(), -1)

    if count != -1:
        print "\n'%s' appears %d times\n" %(search_word, count)
        print_top_5_before(search_word)
        print_top_5_after(search_word)
    else:
        print "\nThe word %s was not found in this unigram\n" %search_word

def print_top_5_before(search_word):
    before_list = []
    for pair in bigram_dict[search_word.lower()].items():
       before_list.append(pair)
    sorted_before_list = quick_sort_desc(before_list)

    print "Top 5 bigrams for %s at beginning\n" %search_word

    i = 0
    while i < 5 and i < len(sorted_before_list):
        key   = sorted_before_list[i][0]
        value = sorted_before_list[i][1]
        probability = float(value) / unigram_dict.get(search_word.lower())
        print "'%s %s' %d times %.2f\n" %(search_word, key, value, probability)
        i += 1

def print_top_5_after(search_word):
    after_list = []
    for key in bigram_dict.keys():
        value = bigram_dict[key].get(search_word.lower(), -1)
        if (value != -1):
            after_list.append( (key, value) )
    sorted_after_list = quick_sort_desc(after_list)

    print "Top 5 bigrams for %s at end\n" %search_word

    i = 0
    while i < 5 and i < len(sorted_after_list):
        key   = sorted_after_list[i][0]
        value = sorted_after_list[i][1]
        probability = float(value) / unigram_dict.get(key.lower())
        print "'%s %s' %d times %.2f\n" %(key, search_word, value, probability)
        i += 1

def quick_sort_desc(lst):
    # MY ORIGINAL QUICKSORT - BROKE - REACHING MAXIMUM RECURSION DEPTH ON
    # CERTAIN WORDS
    #if len(lst) <= 1: 
    #    return lst
    #smaller = [x for x in lst[1:] if x[1] < lst[0][1]]
    #larger = [x for x in lst[1:] if x[1] >= lst[0][1]]
    #return quick_sort_desc(larger) + [lst[0]] + quick_sort_desc(smaller)

    # GEORGE BENJAMIN - QUICKSORT - DATASTRUCTURES 2014
    smaller = []
    bigger = []
    equal = []

    if len(lst) > 1:
        pivot = lst[0][1]
        for x in lst:
            if x[1] < pivot:
                smaller.append(x)
            if x[1] > pivot:
                bigger.append(x)
            if x[1] == pivot:
                equal.append(x)
        return quick_sort_desc(bigger) + equal + quick_sort_desc(smaller)
    else:
        return lst

def search_bigram():
    search_words = raw_input("\nEnter two words: ")
    while len(search_words.split()) != 2:
        print("\nIncorrect number of words. Please try again.")
        search_words = raw_input("\nEnter bigram: ")

    search_words_arr = search_words.split()
    Wi_1 = search_words_arr[0].lower()
    Wi   = search_words_arr[1].lower()

    try:
        count = bigram_dict[Wi_1][Wi]
        probability = float(count) / float(unigram_dict.get(Wi_1))
        print "\n'%s' %d times %.2f \n" %(search_words, count, probability)
    except KeyError:
        print "\nThe bigram does not exist\n"
        pass

main()
