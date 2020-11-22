<!DOCTYPE html>

<!-- This is the input page to our php website demonstration-->

<html>
<head>
	<title>Resume Builder</title>
	<meta name="author" content="Bryan Crossman & Victor Lora" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="description" content="PHP Game Input Page">
	<meta name="keywords" content="computer science, software engineering">
	<link rel="stylesheet" href="form.css">

<?php 

	session_start();

	// returns a string of error messages if invalid user input found or an empty string if no errors
	function validate_input() {
	   $errorMessages = "";
	   
	   // error check firstName
	   if (empty($_POST['firstName'])) {
	      $errorMessages .= "Your first name is required<br />";
	   } elseif (!preg_match('/^[A-Za-z ]+$/', $_POST['firstName'])) {
	      $errorMessages .= "A name may only be letters or the blank character.<br />";
	   }
		
	   // error check lastName
	   if (empty($_POST['lastName'])) {
	      $errorMessages .= "Your last name is required<br />";
	   } elseif (!preg_match('/^[A-Za-z ]+$/', $_POST['lastName'])) {
	      $errorMessages .= "A last name may only be letters or the blank character.<br />";
	   } 
	   // error check address
	   if (!isset($_POST['address']) || empty($_POST['address'])) {
	      $errorMessages .= "An address is required<br />";
	   } elseif (!preg_match('/[A-Za-z0-9\-\\,.]+/', $_POST['address'])) {
	      $errorMessages .= "An address must contain only letters, numbers, periods, and commas.<br />";
	   }
		 
	   // error check phone
	   if (!isset($_POST['phone']) || empty($_POST['phone'])) {
	      $errorMessages .= "A phone number is required<br />";
	   } elseif (!preg_match('/^[+]?([\d]{0,3})?[\(\.\-\s]?([\d]{3})[\)\.\-\s]*([\d]{3})[\.\-\s]?([\d]{4})$/', $_POST['phone'])) {
	      $errorMessages .= "Please enter a valid phone number.<br />";
	   }

	   // error check email
	   if (!isset($_POST['email']) || empty($_POST['email'])) {
	      $errorMessages .= "An email is required<br />";
	   } elseif (!filter_var($_POST['email'], FILTER_VALIDATE_EMAIL)) {
	      $errorMessages .= "An email must contain only letters, numbers, periods, and @'s.<br />";
	   }

	   // error check objective
	   if (!isset($_POST['objective']) || empty($_POST['objective'])) {
	      $errorMessages .= "An objective is required<br />";
	   } elseif (!preg_match('/[A-Za-z0-9\-\\,.]+/', $_POST['objective'])) {
	      $errorMessages .= "An objective must contain only letters.<br />";
	   }

	   // error check experience1
	   if (!isset($_POST['experience1']) || empty($_POST['experience1'])) {
	      $errorMessages .= "An experience is required<br />";
	   } elseif (!preg_match('/[A-Za-z0-9\-\\,.]+/', $_POST['experience1'])) {
	      $errorMessages .= "An experience must contain only letters.<br />";
	   }

	   // error check startDate1
	   if (!isset($_POST['startDate1']) || empty($_POST['startDate1'])) {
	      $errorMessages .= "A start date is required<br />";
	   } elseif (!preg_match('/[0-9]{2}\/[0-9]{4}/', $_POST['startDate1'])) {
	      $errorMessages .= "Enter a valid start date 1: (MM/YYYY).<br />";
	   }
		 
	   // error check endDate1
	   if (!isset($_POST['endDate1']) || empty($_POST['endDate1'])) {
	      $errorMessages .= "An end date is required<br />";
	   } elseif (!preg_match('/[0-9]{2}\/[0-9]{4}/', $_POST['endDate1'])) {
	      $errorMessages .= "Enter a valid end date 1: (MM/YYYY).<br />";
	   }

	   // error check description1
	   if (!isset($_POST['description1']) || empty($_POST['description1'])) {
	      $errorMessages .= "A description is required<br />";
	   } elseif (!preg_match('/[A-Za-z0-9\-\\,.]+/', $_POST['description1'])) {
	      $errorMessages .= "Description 1 must contain only letters.<br />";
	   }

	   // error check experience2
	   if (!isset($_POST['experience2']) || empty($_POST['experience2'])) {
	      $errorMessages .= "An experience is required<br />";
	   } elseif (!preg_match('/[A-Za-z0-9\-\\,.]+/', $_POST['experience2'])) {
	      $errorMessages .= "An experience must contain only letters.<br />";
	   }

	   // error check startDate2
	   if (!isset($_POST['startDate2']) || empty($_POST['startDate2'])) {
	      $errorMessages .= "A start date is required<br />";
	   } elseif (!preg_match('/[0-9]{2}\/[0-9]{4}/', $_POST['startDate2'])) {
	      $errorMessages .= "Enter a valid start date 2: (MM/YYYY).<br />";
	   }

	   // error check endDate2
	   if (!isset($_POST['endDate2']) || empty($_POST['endDate2'])) {
	      $errorMessages .= "An endDate2 is required<br />";
	   } elseif (!preg_match('/[0-9]{2}\/[0-9]{4}/', $_POST['endDate2'])) {
	      $errorMessages .= "Enter a valid end date 2: (MM/YYYY).<br />";
	   }

	   // error check description2
	   if (!isset($_POST['description2']) || empty($_POST['description2'])) {
	      $errorMessages .= "A description is required<br />";
	   } elseif (!preg_match('/[A-Za-z0-9\-\\,.]+/', $_POST['description2'])) {
	      $errorMessages .= "Description 2 must contain only letters.<br />";
	   }

	   // error check schoolName
	   if (!isset($_POST['schoolName']) || empty($_POST['schoolName'])) {
	      $errorMessages .= "A schoolName is required<br />";
	   } elseif (!preg_match('/[A-Za-z0-9\-\\,.]+/', $_POST['schoolName'])) {
	      $errorMessages .= "A schoolName must contain only letters.<br />";
	   }

	   // error check degree
	   if (!isset($_POST['degree']) || empty($_POST['degree'])) {
	      $errorMessages .= "A degree is required.<br />";
	   } elseif (!preg_match('/[A-Za-z0-9\-\\,.]+/', $_POST['degree'])) {
	      $errorMessages .= "A degree must contain only letters.<br />";
	   }

	   // error check graduationDate
	   if (!isset($_POST['graduationDate']) || empty($_POST['graduationDate'])) {
	      $errorMessages .= "A graduation year is required.<br />";
	   } elseif (!preg_match('/[0-9]{4}/', $_POST['graduationDate'])) {
	      $errorMessages .= "Enter only the graduation year: (YYYY).<br />";
	   }

	   // error check awardAchievement1
	   if (!isset($_POST['awardAchievement1']) || empty($_POST['awardAchievement1'])) {
	      $errorMessages .= "An Award Achievement is required<br />";
	   } elseif (!preg_match('/[A-Za-z0-9\-\\,.]+/', $_POST['awardAchievement1'])) {
	      $errorMessages .= "An Award Achievement must contain only letters.<br />";
	   }

	   // error check awardAchievement2
	   if (!isset($_POST['awardAchievement2']) || empty($_POST['awardAchievement2'])) {
	      $errorMessages .= "An Award Achievement is required<br />";
	   } elseif (!preg_match('/[A-Za-z0-9\-\\,.]+/', $_POST['awardAchievement2'])) {
	      $errorMessages .= "An Award Achievement must contain only letters.<br />";
	   }

	   // error check awardAchievement3
	   if (!isset($_POST['awardAchievement3']) || empty($_POST['awardAchievement3'])) {
	      $errorMessages .= "An Award Achievement is required<br />";
	   } elseif (!preg_match('/[A-Za-z0-9\-\\,.]+/', $_POST['awardAchievement3'])) {
	      $errorMessages .= "An Award Achievement must contain only letters.<br />";
	   }

	   
	   return $errorMessages;
	}

	function setSessionVariables(){
		$_SESSION['firstName'] = $_POST['firstName'];
		$_SESSION['lastName'] = $_POST['lastName'];
		$_SESSION['address'] = $_POST['address'];
		$_SESSION['phone'] = $_POST['phone'];
		$_SESSION['email'] = $_POST['email'];
		$_SESSION['objective'] = $_POST['objective'];
		$_SESSION['experience1'] = $_POST['experience1'];
		$_SESSION['startDate1'] = $_POST['startDate1'];
		$_SESSION['endDate1'] = $_POST['endDate1'];
		$_SESSION['description1'] = $_POST['description1'];
		$_SESSION['experience2'] = $_POST['experience2'];
		$_SESSION['startDate2'] = $_POST['startDate2'];
		$_SESSION['endDate2'] = $_POST['endDate2'];
		$_SESSION['description2'] = $_POST['description2'];
		$_SESSION['schoolName'] = $_POST['schoolName'];
		$_SESSION['degree'] = $_POST['degree'];
		$_SESSION['graduationDate'] = $_POST['graduationDate'];
		$_SESSION['awardAchievement1'] = $_POST['awardAchievement1'];
		$_SESSION['awardAchievement2'] = $_POST['awardAchievement2'];
		$_SESSION['awardAchievement3'] = $_POST['awardAchievement3'];
	}
?>

</head>
<body>
<img src="http://neo-cdn.stretchinternet.com/YVqXBY/muhlenberg.png" alt="Muhlenberg Logo" ><br>
<header>Resume Builder</header>
<!--NavBar-->
<nav>
    <ul>
        <li><a href="resume_intro.html">Home</a></li>
            <li><a href="resume_input.php">Form</a></li>
            <li><a href="resume_aboutus.html">About Us</a></li>
            <li><a href="resume_help.html">Help</a></li>
        </li>
    </ul>
</nav>
<div id="errors">
	<?php

		if (isset($_POST['submitButton'])) {
			 // Check for errors in the user input
			 $errors = validate_input();
			 
			 //if user input is valid, process the data and go to confirmation page
			 if ($errors == "") {
			 	 setSessionVariables();
				 header('Location: resume_template.php');	 //go to confirmation page
				 exit();
			}
				else {
	   				 //show the error messages 
	   				 echo "<p>$errors</p>";
				}
		}
	 ?>
</div>
<form id="resumeInput" action="resume_input.php" method="post">
	<fieldset>
		<legend>Resume Form</legend>
	<div id="col1">

		<p>* Require Field</p>
		<h2>Personal Information</h2>
		<p>
			<label for="firstName">First Name:</label> 
			<input id="firstName" name="firstName" value="<?php if(isset($_POST['firstName'])) { echo $_POST['firstName']; } ?>" type="text" />
			*		
		</p>

		<p>
			<label for="lastName">Last Name:</label> 
			<input id="lastName" name="lastName" value="<?php if(isset($_POST['lastName'])) { echo $_POST['lastName']; } ?>" type="text" />
			*
		</p>

		<p>
			<label for="address">Address:</label> 
			<input id="address" name="address" value="<?php if(isset($_POST['address'])) { echo $_POST['address']; } ?>" type="text" />
			*
		</p>

		<p>
			<label for="phone">Phone Number:</label>
			<input id="phone" name="phone" value="<?php if(isset($_POST['phone'])) { echo $_POST['phone']; } ?>" type="text" />
			*
		</p>

		<p>
			<label for="email">Email:</label>
			<input id="email" name="email" value="<?php if(isset($_POST['email'])) { echo $_POST['email']; } ?>" type="text" />
			*
		</p>

		<p>
			<label for="objective">Objective: *</label> 
			<textarea id="objective" name="objective" value="<?php if(isset($_POST['objective'])) 
			{ echo $_POST['objective']; } ?>" type="text" rows="5" cols="40"></textarea>
		</p><br>


		<h2>Experience</h2>
		<p>
			<label for="experience1">Experience 1:</label> 
			<input id="experience1" name="experience1" value="<?php if(isset($_POST['experience1'])) { echo $_POST['experience1']; } ?>" type="text" />
			*
		</p>

		<p>
			<label for="startDate1">Start Date 1:</label> 
			<input id="startDate1" name="startDate1" value="<?php if(isset($_POST['startDate1'])) { echo $_POST['startDate1']; } ?>" type="text" />
			*
		</p>

		<p>
			<label for="endDate1">End Date 1:</label> 
			<input id="endDate1" name="endDate1" value="<?php if(isset($_POST['endDate1'])) { echo $_POST['endDate1']; } ?>" type="text" />
			*
		</p>

		<p>
			<label for="description1">Description 1: *</label> 
			<textarea id="description1" name="description1" value="<?php if(isset($_POST['description1'])) 
			{ echo $_POST['description1']; } ?>" type="text" rows="5" cols="40"></textarea>
		</p>		
	</div>

	<div id="col2">
		<h2>Experience (continued...)</h2>
		<p>
			<label for="experience2">Experience 2:</label> 
			<input id="experience2" name="experience2" value="<?php if(isset($_POST['experience2'])) { echo $_POST['experience2']; } ?>" type="text" />
			*
		</p>

		<p>
			<label for="startDate2">Start Date 2:</label> 
			<input id="startDate2" name="startDate2" value="<?php if(isset($_POST['startDate2'])) { echo $_POST['startDate2']; } ?>" type="text" />
			*
		</p>

		<p>
			<label for="endDate2">End Date 2:</label> 
			<input id="endDate2" name="endDate2" value="<?php if(isset($_POST['endDate2'])) { echo $_POST['endDate2']; } ?>" type="text" />
			*
		</p>

		<p>
			<label for="description2">Description 2: *</label> 
			<textarea id="description2" name="description2" value="<?php if(isset($_POST['description2'])) 
			{ echo $_POST['description2']; } ?>" type="text" rows="5" cols="40"></textarea>
		</p><br>

		<h2>Education</h2>
		<p>
			<label for="schoolName">School Name:</label> 
			<input id="schoolName" name="schoolName" value="<?php if(isset($_POST['schoolName'])) { echo $_POST['schoolName']; } ?>" type="text" />
			*
		</p>

		<p>
			<label for="degree">Degree:</label> 
			<input id="degree" name="degree" value="<?php if(isset($_POST['degree'])) { echo $_POST['degree']; } ?>" type="text" />
			*
		</p>

		<p>
			<label for="graduationDate">Graduation Date:</label> 
			<input id="graduationDate" name="graduationDate" value="<?php if(isset($_POST['graduationDate'])) { echo $_POST['graduationDate']; } ?>" type="text" />
			*
		</p><br>
		
		<h2>Awards & Achievements</h2>
		<p>
			<label for="awardAchievement1">Recognition 1:</label> 
			<input id="awardAchievement1" name="awardAchievement1" value="<?php if(isset($_POST['awardAchievement1'])) { echo $_POST['awardAchievement1']; } ?>" type="text" />
			*
		</p>

		<p>
			<label for="awardAchievement2">Recognition 2:</label> 
			<input id="awardAchievement2" name="awardAchievement2" value="<?php if(isset($_POST['awardAchievement2'])) { echo $_POST['awardAchievement2']; } ?>" type="text" />
			*
		</p>

		<p>
			<label for="awardAchievement3">Recognition 3:</label> 
			<input id="awardAchievement3" name="awardAchievement3" value="<?php if(isset($_POST['awardAchievement3'])) { echo $_POST['awardAchievement3']; } ?>" type="text" />
			*
		</p>
	</div>
	<div id="submit">
		<p>
			<input id="submitButton" name="submitButton" value="Build Resume!" type="submit">
		</p>
	</div>



</fieldset>
</form>

</body>