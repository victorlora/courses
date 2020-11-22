<?php 

// returns a string of error messages if invalid user input found or an empty string if no errors
function validate_input() {
   $errorMessages = "";
   
   // error check the users name
   if (!isset($_POST['name']) || empty($_POST['name'])) {
      $errorMessages .= "Your name is required<br />";
   } elseif (!preg_match('/^[A-Za-z ]+$/', $_POST['name'])) {
      $errorMessages .= "Your name may only be letters or the blank character<br />";
   }
	 
	 if (!isset($_POST['email']) || empty($_POST['email'])) {
      $errorMessages .= "Your email is required<br />";
   } elseif (!preg_match('/^[A-Za-z]{2}@[A-Za-z]{2}\.[A-Za-z]{2}$/', $_POST['email'])) {
      $errorMessages .= "Your email may only be letters the @ symbol and the domain<br />";
   }
   
	 if (isset($_POST['telephone'])) {
      	if (!preg_match('/^([0-9]{3}-)?[0-9]{3}-[0-9]{4}$/', $_POST['telephone'])) {
      		 	$errorMessages .= "Your phone number must be in one of the following formats ###-###-#### or ###-####<br />";
				}
   }
	 
	 if (isset($_POST['website'])) {
      	if (!preg_match('/^(http:\/\/|https:\/\/)?www.[A-Za-z0-9_\.]*\.com\/*[A-Za-z0-9_\.]*$/', $_POST['website'])) {
      		 	$errorMessages .= "Invalid URL<br />";
				}
   }
	 
	 if (!isset($_POST['projectType']) || empty($_POST['projectType'])) {
      $errorMessages .= "Your project type is required<br />";
   } 
   // error check everything else
   
   
   return $errorMessages;
}
?>
<!DOCTYPE html>

<!--
      Account: csi345_nn
      File: ~/www/labs/lab7/lab7validate.php
      Honor Code: We pledge the AIC: name(s)
-->

<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Lab 7: Validate</title>
<meta name="description" content="Lab Form Validation" />
<meta name="keywords" content="CS" />
<meta name="author" content="Your Names" />
</head>

<body>
<h1>Lab 7: Validate</h1>

<?php

// Check for errors in the user input
$errors = validate_input();
if ($errors != "") {
   echo "<p>$errors</p>";
}
else {
   //user input validates, display a dump of the POST variables 
   echo "<pre>";
   print_r($_POST);
   echo "</pre>";
}
 ?>
<p>Click here to return to <a href="lab7input.php">lab7input.php</a> page</p>

</body>
</html>

