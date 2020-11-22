/*
*   Account: csi345_13
*   File: ~/www/project/bestreads.js
*   AIC: Victor A. Lora
*/

window.onload = loadPage;

var ajax;
var ajaxDesc;
var ajaxReview;

function loadPage() {
    ajax = new XMLHttpRequest();
    ajax.onreadystatechange = loadCovers;
    ajax.open("GET", "bestreads.php?mode=books", false);
    ajax.send(null);

    var homeButton = document.getElementById("back");
    // Switch button innerHtml to home when appropriate
    var homeButtonValue = homeButton.innerHTML;
    if (homeButtonValue === "back") {
        homeButton.innerHTML = "home";
    }
    homeButton.setAttribute("onclick", "loadPage()");
}

function loadCovers() {
    if(ajax.readyState == 4 && ajax.status == 200) {
        // Clear current books before refreshing; otherwise duplicates occur
        removeAllBooks();

        // Parse XML repsonse from server
        var parser = new DOMParser();
        var xmlResp = parser.parseFromString(ajax.responseText, "application/xml");

        var allBooks = document.getElementById("allbooks");
        var xmlBooks = xmlResp.getElementsByTagName("book");
        for (var i = 0; i < xmlBooks.length; i++) {
            var title = xmlBooks[i].firstChild.innerHTML; // Book title
            var folderName = xmlBooks[i].lastChild.innerHTML; // Folder location

            var divNode = document.createElement("div");
            divNode.setAttribute("id", folderName);
            divNode.setAttribute("onclick", "loadSingleBook(\""+ folderName + "\")");

            // Create image element to hold book cover
            var imgNode = document.createElement("img");
            imgNode.setAttribute("src", "books/" + folderName + "/cover.jpg");
            imgNode.setAttribute("alt", folderName);
            divNode.appendChild(imgNode);

            // Create paragraph element to hold book title
            var paragraphNode = document.createElement("p");
            paragraphNode.innerHTML = title;
            divNode.appendChild(paragraphNode);

            // Append div to allBooks
            allBooks.appendChild(divNode);

        }

        // Switch to all books view
        document.getElementById("singlebook").style.visibility="hidden";
        document.getElementById("allbooks").style.visibility="visible";
    }
}

function loadSingleBook(folderName) {

    // Switch button value
    var homeButton = document.getElementById("back");
    var homeButtonValue = homeButton.innerHTML;
    if (homeButtonValue === "home") {
        homeButton.innerHTML = "back"
    }

    // Clear books from allbooks view
    removeAllBooks();

    // Switch to single book view
    document.getElementById("allbooks").style.visibility="hidden";
    document.getElementById("singlebook").style.visibility="visible";

    // Set book's cover image
    var cover = document.getElementById("cover");
    var coverImagePath = "books/" + folderName + "/cover.jpg";
    cover.setAttribute("src", coverImagePath);

    // Load other book info
    loadBookInfo(folderName);
    loadBookDescription(folderName);
    loadBookReviews(folderName);
}

function removeAllBooks() {
    var allBooks = document.getElementById("allbooks");
    while (allBooks.firstChild) {
        allBooks.removeChild(allBooks.firstChild);
    }
}

function loadBookInfo(folderName) {
    ajax = new XMLHttpRequest();
    ajax.onreadystatechange = getBookInfo;
    var params = "mode=info&title=" + folderName;
    ajax.open("GET","bestreads.php?" + params, false);
    ajax.send(null);
}

function getBookInfo() {
    if (ajax.readyState == 4 && ajax.status == 200) {
        var book = JSON.parse(ajax.responseText);
        document.getElementById("title").innerHTML = book.title;
        document.getElementById("author").innerHTML = book.author;
        document.getElementById("stars").innerHTML = book.stars;
    }
}


function loadBookDescription(folderName) {
    ajaxDesc = new XMLHttpRequest();
    ajaxDesc.onreadystatechange = getBookDescription;
    var params = "mode=description&title=" + folderName;
    ajaxDesc.open("GET","bestreads.php?" + params, true);
    ajaxDesc.send(null);
}

function getBookDescription() {
    if (ajaxDesc.readyState == 4 && ajaxDesc.status == 200) {
        document.getElementById("description").innerHTML = ajaxDesc.responseText;
    }
}


function loadBookReviews(folderName) {
    ajaxReview = new XMLHttpRequest();
    ajaxReview.onreadystatechange = getBookReviews;
    var params = "mode=reviews&title=" + folderName
    ajaxReview.open("GET","bestreads.php?" + params, false);
    ajaxReview.send(null);
}

function getBookReviews() {
    if (ajaxReview.readyState == 4 && ajaxReview.status == 200) {
        document.getElementById("reviews").innerHTML = ajaxReview.responseText;
    }
}
