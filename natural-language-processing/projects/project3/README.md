# Project 3

## Required Libraries

The following libraries are necessary to run the Search Engine:

1. Beautiful Soup: https://www.crummy.com/software/BeautifulSoup/bs4/doc/
2. NLTK: http://www.nltk.org/install.html
3. PPrint: https://pypi.python.org/pypi/pprintpp/0.3.0
4. Numpy: http://scipy.org/install.html

## Search Engine

In order to run the search engine, you must first crawl to generate the document table and inverted index (the documents weren't commited due to their size). To crawl, change the flag on line 19 in project3.py to 'True' and specify the URL to crawl.
Run 'python project3.py'. Once it has crawled, the crawl flag can be reset to 'False' since the document table and inverted index have already been saved to the file system and the search engine will pull from their. The project has some predefined queries that it will run as tests (lines 25-30). These can be commented out and lines 32-39 can be commented in to launch the Search engine in "query-input mode".
 

## GUI

To run the GUI, the websites must first be crawled following the steps in the section above (Search Engine). Once the websites are crawled and the index is created, you can launch the gui using 'python gui_project3.py'. Type in your query and if results are found the top 10 will show up in the result box. To open the links, simply double-click on any of them.


## Analysis

To run analysis, the 1400 analysis files must be parsed out from 'cran.all.1400'. Before running 'analyze.py', cd into the analysis directory and run 'python separated_paragraph.py'. Analysis can then be run from the '/project3' directory using 'python analyze.py'. The corresponding files are located in '/analysis'


## Crawler Multi-threading Demo

A demo of two versions of the crawler have been provided (one with multi-threading and one without). These are located in '/project3' and are named accordingly. They can be run using either 'python crawler_demo_threading.py' or 'python crawler_demo_without_threading.py'.


## Homework 6

A version of an assigned homework, where we manually calculated the tf-idf and cosine similarities has also been included. This can be ran using 'python hw6.py'. It's corresponding files are located in '/hw6'
