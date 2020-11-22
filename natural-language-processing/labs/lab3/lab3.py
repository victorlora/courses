import random
import re

production_dict = dict();

def main():
    filename = 'conspiracy_theory.g'
    grammar = open(filename, 'r')
    productions = re.findall("{([A-Za-z0-9<>\s;_\.'\-]*)}", grammar.read())
    for p in productions:
        p = p.replace(";", "").strip().split("\n")
        key = p[0]
        production_dict[key] = []
        for i in range(1, len(p)):
            production_dict[key].append(p[i])
    build_sentence()

def build_sentence():
    sentence = get_element("<start>")
    print sentence
   
def get_element(key):
    element = ""
    for w in random.choice(production_dict[key]).split(" "):
        if w in production_dict:
            w  = get_element(w)
        element += w + " "
    return element;

main()
