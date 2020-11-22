<?php

/*
*   Account: csi345_13
*   File: ~/www/project/bestreads.php
*   AIC: Victor A. Lora
*/

$book = $_GET['title'];
$mode = $_GET['mode'];
$path = 'books/' . $book . '/';

if ($mode == 'info') {
    $infoFileArr = file($path . 'info.txt', FILE_IGNORE_NEW_LINES);
    $title = $infoFileArr[0];
    $author = $infoFileArr[1];
    $stars = $infoFileArr[2];
    echo createBookInfoJSON($title, $author, $stars);
} else if ($mode == 'description') {
    $description = file_get_contents($path . 'description.txt');
    echo $description;
} else if ($mode == 'reviews') {
    $responseHtml = "";
    foreach (glob($path . "review*.txt") as $fileName) {
        $reviewFileArr = file($fileName, FILE_IGNORE_NEW_LINES);
        $reviewer = $reviewFileArr[0];
        $stars = $reviewFileArr[1];
        $review = $reviewFileArr[2];
        $responseHtml .= createBookReviewHtml($reviewer, $stars, $review);
    }
    echo $responseHtml;
} else if ($mode == 'books') {
    $xml = '<books>';
    foreach (glob('books/*', GLOB_ONLYDIR) as $folderName) {
        $infoFileArr = file($folderName . '/info.txt', FILE_IGNORE_NEW_LINES);
        $title = $infoFileArr[0];
        $folderName = str_replace("books/", "", $folderName);
        $xml .= createBookXML($title, $folderName);
    }
    $xml .='</books>';
    echo $xml;
}

function createBookInfoJSON($title, $author, $stars) {
    $json = '{';
    $json .= '"title":' . '"' . $title.  '"' . ',';
    $json .= '"author":' . '"' . $author . '"' . ',';
    $json .= '"stars":' . '"' . $stars . '"';
    $json .= '}';
    return $json;
}

function createBookReviewHtml($reviewer, $stars, $review) {
    $reviewHtml = '<h3>' . $reviewer . '<span> ' . $stars . '</span></h3>';
    $reviewHtml .= '<p>' . $review . '</p>';
    return $reviewHtml;
}

function createBookXML($title, $folderName) {
    $bookXML = '<book>';
    $bookXML .= '<title>' . $title . '</title>';
    $bookXML .= '<folder>' . $folderName . '</folder>';
    $bookXML .= '</book>';
    return $bookXML;
}

?>
