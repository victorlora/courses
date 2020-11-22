<?php 

session_start();
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
      $errorMessages .= "Your email should be in the following format: aa@bb.cc<br />";
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

// Check for errors in the user input
if (isset($_POST['submitButton'])) {
	 $errors = validate_input();
	 if ($errors == "") {
   		$_SESSION["name"] = $_POST["name"];
   		header("Location: thankyou.php");
			exit();
		}
	  else {
			echo "<p>$errors</p>";
	  }
}
 
?>

<!DOCTYPE html>

<!--
   Account: csi345_23
   File: ~/www/labs/lab7/lab7input.php
   Honor Code: We pledge the AIC : Victor A. Lora & Kathryn Prozzo
-->

<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lab 7: User Input</title>
<meta name="description" content="Lab 7: Form Validation">
<meta name="keywords" content="CS">
<meta name="author" content="Victor A. Lora & Kathryn Prozzo">
</head>

<body>
<h1>Lab 7: User Input</h1>

<p>
Please submit your project details and we will get back to you with a bid.
</p>

<form id="contactForm" action="lab7input.php" method="post">
<fieldset>
<legend>Contact Us Form</legend>

<p>
<label for="name">Name</label> 
<input id="name" name="name" type="text" value= "<?= $_POST["name"]; ?>">
(Required)
</p>

<p>
<label for="company">Company</label> 
<input id="company" name="company" type="text" value= "<?= $_POST["company"]; ?>">
(Optional)
</p>

<p>
<label for="email">Email</label> <input id="email" name="email" type="text" value= "<?= $_POST["email"]; ?>">
(Required)
</p>

<p>
<label for="telephone">Telephone</label> <input id="telephone" name="telephone" type="text" value= "<?= $_POST["telephone"]; ?>">
(Optional)
</p>

<p>
<label for="website">Website</label> <input id="website" name="website" type="text" value= "<?= $_POST["website"]; ?>">
(Optional)
</p>

<p>
<label for="projectType">Type of Project</label>
<select id="projectType" name="projectType">
<option value="0" <?php if ($_POST["projectType"] == 0) { echo "selected"; } ?>>Select Project Type</option>
<option value="1" <?php if ($_POST["projectType"] == 1) { echo "selected"; } ?>>HTML</option>
<option value="2" <?php if ($_POST["projectType"] == 2) { echo "selected"; } ?>>CSS</option>
<option value="3" <?php if ($_POST["projectType"] == 3) { echo "selected"; } ?>>PHP</option>
<option value="4" <?php if ($_POST["projectType"] == 4) { echo "selected"; } ?>>Other</option>
</select>
(Required)
</p>

<p>
<input id="submitButton" name="submitButton" value="Submit Project" type="submit">
</p>

</fieldset>
</form>



</body></html>
