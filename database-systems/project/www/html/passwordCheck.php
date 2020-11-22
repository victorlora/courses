<?php

	//Set values of variables from form

	session_start();
	error_reporting(0);

	if (!isset($_SESSION['userName']))
		header("Location: home.html");

	$userId		 = $_SESSION['userId'];
	$accountType = $_SESSION['accountType'];

	$currentPassword = $_POST["currentPassword"];
	$password 		 = $_POST["password"];
	$confirmPassword = $_POST["confirmPassword"];


	function error_check($query_result) {
		if(!$query_result) {
			print "Error - query could not be executed<br/>";
			$error = mysql_error();
			print "<p>".$error."</p>";
		}
	}
	?>

<html>

<head>
	<title>Change Password</title>
	<link href="../css/bootstrap.min.css" type="text/css" rel="stylesheet">
	<script>
		function wrongOldPassword() {
			alert("The old password you entered is incorrect.");
			window.location.href="updatePassword.php";
		}
		function newPasswordMismatch() {
			alert("The new passwords don't match.")
			window.location.href="updatePassword.php";
		}
		function successAlert() {
			alert("Your password was changed successfully.");
			window.location.href="updatePassword.php";
		}
		function failAlert() {
			alert("Failed to update password. Please try again or contact system administrator.");
			window.location.href="updatePassword.php";
		}
	</script>
</head>

<body>

<nav class="navbar navbar-default navbar-fixed">
	<section class="container-fluid">
		<section class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#nav">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="main.php">Welcome</a>
		</section>
		<section class="collapse navbar-collapse" id="nav">
		<ul class="nav navbar-nav">
			<?php
				if ($accountType == "Administrator")
				{
					print("
						<li><a href=\"updatePassword.php\">Change Password</a></li>
						<li><a href=\"contactInfo.php\">User Info</a></li>
						<li><a href=\"contacts.php\">Users</a></li>
						<li><a href=\"corporation.php\">Corporations</a></li>
						<li><a href=\"company.php\">Company</a></li>
						<li><a href=\"playListings.php\">Plays</a></li>
						<li><a href=\"purchasedTickets.php\">Purchased Tickets</a></li>
						<li><a href=\"reports.php\">Reports</a></li>
						<li><a href=\"logout.php\">Log out</a></li>
					");
				}
				else
				{
					print("
						<li><a href=\"updatePassword.php\">Change Password</a></li>
						<li><a href=\"userInformation.php\">User Info</a></li>
						<li><a href=\"playListings.php\">Plays</a></li>
						<li><a href=\"purchasedTickets.php\">Purchased Tickets</a></li>
						<li><a href=\"reports.php\">Reports</a></li>
						<li><a href=\"logout.php\">Log out</a></li>
					");
				}
			?>
		</ul>
		</section>
	</section>
</nav>

<section class="container">
<?php


//Connect to MySQL

	$mysqli = new mysqli("localhost", "project3", "teamrow2", "pj03");

	if(!$mysqli) {
		print "<p>Could not connect to MySQL/Database</p>";
		exit;
	}

	if ($password != $confirmPassword)
	{
		echo '<script>newPasswordMismatch();</script>';
		exit;
	}

	// Create query to get current password
	$query = "SELECT password "
			."FROM Contact "
			."WHERE objectid = '$userId'";
	trim($query);
	
	$result = $mysqli -> query($query);
	error_check($result);

	if ($result)
		$row = mysqli_fetch_array($result);
	$oldPassword = $row[0];

	//Check to see if user entered the correct current password
	if ($currentPassword == $oldPassword)
	{
		//Create the query to update password
		$query = "UPDATE Contact "
				."SET password = '$password' "
				."WHERE objectid = '$userId'";
		trim($query);
		
		$result = $mysqli -> query($query);
		error_check($result);
		
		if($result) {
			echo '<script>successAlert(); </script>';
		}
		else {
			echo '<script>failAlert();</script>';
		}
	}
	else
	{
		echo '<script>wrongOldPassword();</script>';
	}

	
	mysql_close($db);
?>



</section>
</body>
</html>












