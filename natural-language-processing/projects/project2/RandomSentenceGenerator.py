"""
RandomSentenceGenerator.py

Victor A. Lora
Dr. Reyes
CSI-382
November 7, 2016
"""


import random
import re

class RandomSentenceGenerator:

    def __init__(self, filename):
        self.grammar = Grammar()
        try:
            grammar_file = open(filename, 'r')
            productions = self.getProductions(grammar_file)
            self.createGrammar(productions)
        except:
            print "File not found. Exiting program..."
            quit()

    def getProductions(self, grammar_file):
        return re.findall("{([^{|^}]+)}", grammar_file.read())

    def createGrammar(self, productions):
        for p in productions:
            # Remove semi-colon; Split at line breaks
            p = p.replace(";", "").strip().split("\n")
            key = p[0]
            for i in range(1, len(p)):
                self.grammar.addProduction(key, p[i])

    def createRandomSentence(self):
        return self.generateRandomSentence("<start>")

    def generateRandomSentence(self, key):
        error = []
        sentence = ""
        for w in self.grammar.getRandomRHS(key).split(" "):
            non_term = re.match("<[^<|^>]+>", w)
            if non_term:
                non_term = non_term.group(0)
                if self.grammar.containsNonTerminal(non_term):
                    w  = self.generateRandomSentence(non_term)
                else:
                    error.append(non_term)
            sentence += w + " "
        # Remove extra whitespace
        sentence = re.sub( '\s+', ' ', sentence).strip()
        if len(error) > 0:
            error_sentence = "The following non-terminals were not in the grammar for the sentence below: "
            for e in error:
                error_sentence += e
            print "\n" + error_sentence
        return sentence;

    def printGrammar(self):
        for key in self.grammar.production_dict:
            print key
            for p in self.grammar.production_dict.get(key):
                print "\t--> " + p
            print "\n"


class Grammar:

    def __init__(self):
        self.production_dict = dict()

    def addProduction(self, non_term, term):
        non_term = non_term.strip().lower()
        term = term.strip()
        if (self.containsNonTerminal(non_term)):
            self.production_dict[non_term].append(term)
        else:
            self.production_dict[non_term] = [term]

    def getRandomRHS(self, non_term):
        non_term = non_term.lower()
        return random.choice(self.production_dict[non_term])

    def containsNonTerminal(self, non_term):
        non_term = non_term.lower()
        return (non_term in self.production_dict)


# Main Function
if __name__ == '__main__':
    file_name = raw_input("Input grammar file name: ")
    generator = RandomSentenceGenerator(file_name)
    generator.printGrammar()
    for i in range(3):
        print "\n" + `i+1` + ": " + generator.createRandomSentence()
