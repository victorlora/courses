from SearchEngine import SearchEngine
from SearchEngineConfigs import SearchEngineConfigs
from Tkinter import *
from ttk import Frame, Style
import webbrowser


class SearchEngineGUI(Frame):
    def __init__(self, master):
        Frame.__init__(self, master)
        print "Loading Index..."
        self.initSearchEngine()
        print "Loading complete. Ready for Query."
        self.master = master
        self.pack()
        self.createWidgets()

    def initSearchEngine(self):
        searchEngineConfigs = SearchEngineConfigs()
        # PreprocessorParams takes: removePunct, removeStopWords, stemWords
        searchEngineConfigs.setPreprocessorParams(True, True, True)
        searchEngineConfigs.setShouldCrawl(False)
        # Crawler Params takes: url, docRange
        searchEngineConfigs.setCrawlerParams("http://muhlenberg.edu/", 10000)
        fileDirectory = "documents"
        self.engine = SearchEngine(searchEngineConfigs, fileDirectory)

    def createWidgets(self):
        self.LOGO = Label(self, text="Muhlenberg College Search", \
            fg="red", font="Helvetica 22 bold italic")
        self.LOGO.grid(row=0, column=0, columnspan=2)
        self.LOGO["pady"] = 30

        self.ENTRY = Entry(self, width=75)
        self.ENTRY.bind("<Return>", self.onEnter)
        self.ENTRY.grid(row=1, column=0, columnspan=2)
        self.ENTRY.focus()

        self.OK = Button(self, width=25)
        self.OK["text"] = "Search",
        self.OK["command"] = self.search
        self.OK["padx"] = 5
        self.OK["pady"] = 5
        self.OK.grid(row=2, column=0)

        self.QUIT = Button(self, width=25)
        self.QUIT["text"] = "Quit"
        self.QUIT["fg"]   = "red"
        self.QUIT["padx"] = 5
        self.QUIT["pady"] = 5
        self.QUIT["command"] =  self.quit
        self.QUIT.grid(row=2, column=1)

        self.LABEL = Label(self, text="0 results found.")
        self.DOUBLECLICK = Label(self, text="Double-click to open link.")

        self.LISTBOX = Listbox(self, width=75)
        self.LISTBOX.bind("<Double-Button-1>", self.openUrl)

    def onEnter(self, event):
        self.search()

    def search(self):
        query = self.ENTRY.get()
        urls = self.engine.processAndIndexQuery(query)
        self.LABEL.grid_forget()
        self.LISTBOX.grid_forget()
        self.DOUBLECLICK.grid_forget()
        self.LISTBOX.delete(0, END)
        if urls:
            urls.reverse() # Inserted in reverse order
            for i in range(len(urls)):
                self.LISTBOX.insert(0, urls[i])
            self.LISTBOX.grid(row=4, column=0, columnspan=2)
            self.DOUBLECLICK.grid(row=5, column=0, columnspan=2)
        else:
            self.LABEL.grid(row=4, column=0, columnspan=2)

    def openUrl(self, event):
        url = self.LISTBOX.get(ACTIVE)
        if url:
            webbrowser.open_new(url)


if __name__ == '__main__':
    root = Tk()
    root.geometry("800x600")
    app = SearchEngineGUI(root)
    app.mainloop()
    root.destroy()
