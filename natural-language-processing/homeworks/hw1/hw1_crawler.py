"""
hw1_crawler.py

Victor A. Lora
CSI-382
September 6, 2016
"""

import urllib2
import re

def print_links(list):
    for l in list:
        print `l[0]` + " - " + l[1] + " - " + l[2]

def parse_links(links):
    absolute_links = []
    relative_links = []

    relative_params = ['/'];
    absolute_params = ['http://', 'https://', 'ftp://']
    
    relative_count = 0
    absolute_count = 0
    
    parsed_link_pattern = re.compile("(/|http://|https://|ftp://)[a-z0-9\.:/_\-\$&%=\?!~#@\'\*\+]*", re.IGNORECASE)

    for link in links:
        parsed_link = parsed_link_pattern.search(link[1])

        if parsed_link:
            line_num = link[0]

            if parsed_link.group(1) in absolute_params:
                absolute_links.append((line_num, parsed_link.group(0), "Absolute"))
                absolute_count += 1
            elif parsed_link.group(1) in relative_params:
                relative_links.append((line_num, parsed_link.group(0), "Relative"))
                relative_count += 1
    
    print " "
    print "-- Aboslute --"
    print_links(absolute_links)
    
    print " "
    print "-- Relative -- "
    print_links(relative_links)

    print " "
    print "-- Counts --"
    print "Absolute: " + `absolute_count`
    print "Relative: " + `relative_count`
    print "Total: " + `absolute_count + relative_count`
    print " "

def get_html_links(file):
    html_links = []
    link_pattern = re.compile('(?<=\<a href=)\"([a-z0-9\.:/_\-\$&%=\?!~#@\'\*\+]*)\"+', re.IGNORECASE)
    line_num = 1;

    for line in file:
        links = link_pattern.findall(line)

        if len(links) > 0:

            for link in links:
                html_links.append((line_num, link))
                
        line_num += 1

    return html_links

def parse_file(filename):
    file = open(filename, 'r')
    html_links = get_html_links(file)
    parse_links(html_links)

def print_file_contents(filename):
    file = open(filename, 'r')

    for line in file:
        print line

    file.close()

def write_to_file(html, filename):
    file = open(filename, 'w')
    file.write(html)
    file.close()

def get_website_html(url):
    response = urllib2.urlopen(url)
    html = response.read()
    return html


def main():
    #url = "http://muhlenberg.edu"
    url = raw_input("Enter a URL: ")
    html = get_website_html(url)
    filename = "website.txt"
    write_to_file(html, filename)
    #print_file_contents(filename)
    parse_file(filename)

main()
