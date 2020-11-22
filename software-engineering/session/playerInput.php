<!DOCTYPE html>

<!-- This is the input page to our php website demonstration-->

<html>
<head>
	<title>Racing Input</title>
	<meta name="author" content="Victor Lora" />
	<meta http-equiv="Content-Type" content="text/php; charset=UTF-8" />
	<meta name="description" content="PHP Game Input Page">
	<meta name="keywords" content="computer science, software engineering">
	<link rel="stylesheet" type="text/css" href="mystyle.css">
	<?php
		session_start();
		// returns a string of error messages if invalid user input found or an empty string if no errors
		function validate_input() {
		   $errorMessages = "";
		   
		   // error check name
		   if (empty($_POST['name'])) {
		      $errorMessages .= "Name is required<br />";
		   } elseif (!preg_match('/^[A-Za-z ]+$/', $_POST['name'])) {
		      $errorMessages .= "A name may only be letters or the blank character.<br />";
		   }
		   if (empty($_POST['car'])) {
		      $errorMessages .= "A car selection is required<br />";
		   }
		   if (empty($_POST['color'])) {
		      $errorMessages .= "A color selection is required<br />";
		   }
		   if (empty($_POST['track'])) {
		      $errorMessages .= "A track selection is required<br />";
		   }
		   
		   return $errorMessages;
		}
		function setSessionVariables(){
			$_SESSION['name'] 	= $_POST['name'];
			$_SESSION['car'] 	= $_POST['car'];
			$_SESSION['color']  = $_POST['color'];
			$_SESSION['track'] 	= $_POST['track'];
		}
	?>
	
</head>
<body>
    <header>
   	<h1 style="text-align:center"> Welcome to the Race Track! </h1> 
	</header>
	<div class="nav">	<!-- Our Menu bar that links to rest of site and our wiki. -->		
	      <ul>
	        <li class="home"><a href="http://mathcs.muhlenberg.edu/~ej248960/Sim/intro.html">Home</a></li>
	        <li class="input"><a href="http://mathcs.muhlenberg.edu/~ej248960/Sim/playerInput.php">Let's Race</a></li>
	        <li class="thankyou"><a href="http://mathcs.muhlenberg.edu/~ej248960/Sim/done.html">Thank You</a></li>
	      </ul>
	  </div>
	  <div class="wrapper">
<form id="playerInput" action="playerInput.php" method="post">
	<fieldset>
		<legend>Racing Game</legend>
		<?php
			if (isset($_POST['submitButton'])) {
				 // Check for errors in the user input
				 $errors = validate_input();
			 
				 //if user input is valid, process the data and go to confirmation page
				 if ($errors == "") {
				 	 setSessionVariables();
					 header('Location: output.php');	 //go to confirmation page
					 exit();
				}
					else {
		   				 //show the error messages 
		   				 echo "<p>$errors</p>";
					}
			}
		?>
	<div id="form">
		<h2>Choose Your Options</h2>
		<p>Required Field *</p>
		<label for="racer">Racer's Name:</label> 
		<input id="name" name="name" value="<?php if(isset($_SESSION['name'])) { echo $_SESSION['name']; } ?>" type="text" />
		*		
		<br /><br />

	
		Pick your car:
		<select name="car">
		  <option value="">Select...</option>
		  <option value="ferrari">Ferrari</option>
		  <option value="pagani">Pagani</option>
		  <option value="lamborghini">Lamborghini</option>
		</select> *
		<br /><br />

		
		Pick your color:
		<select name="color">
		  <option value="">Select...</option>
		  <option value="red">Red</option>
		  <option value="blue">Blue</option>
		  <option value="white">White</option>
		  <option value="black">Black</option>
		</select>*
		<br /><br />

	
		Pick the track:
		<select name="track">
		  <option value="">Select...</option>
		  <option value="track1" name="track_1">Track 1</option>
		  <option value="track2" name="track_2">Track 2</option>
		  <option value="track3" name="track_3">Track 3</option>
		</select> *
		<br />
	</div>
	<div id="submit">
		<p>
			<input id="submitButton" name="submitButton" value="Lets Race!" type="submit">
		</p>
	</div>


</fieldset>
</form>


	<p>
		Last round you picked:
	</p>
	<ul>
		<!-- Displays last sessions selections --> 
		<li><?php echo $_SESSION['lastCar']; ?></li>
		<li><?php echo $_SESSION['lastColor']; ?></li>
		<li><?php echo $_SESSION['lastTrack']; ?></li>
	</ul>
</div>
</body>
</html>