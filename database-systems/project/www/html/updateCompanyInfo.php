<?php

	//Set values of variables from form

	session_start();
	error_reporting(0);

	if (!isset($_SESSION['userName']))
		header("Location: home.html");

	$accountType = $_SESSION['accountType'];

	$companyId   = $_POST["companyId"]   ? $_POST["companyId"]   : NULL;
	$name 		 = $_POST["name"] 		 ? $_POST["name"] 		 : NULL;
	$description = $_POST["description"] ? $_POST["description"] : NULL;


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
	<title>Update Company Information</title>
	<link href="../css/bootstrap.min.css" type="text/css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script src="../js/updateAccount.js" type="text/javascript"></script>
	<script src="../js/bootstrap.min.js"></script>

	<script>
		function successAlert() {
			alert("Account information updated successfully");
			window.location.href="company.php";
		}

		function failAlert() {
			alert("Failed to perform update. Please try again or contact system administrator.");
			window.location.href="companyInfo.php?companyId=<?php echo $companyId ?>";
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
		print "<p>Could not connect to MySQL/Database</p><br/>";
		exit;
	}

	//Create the query

	$query = "UPDATE Company "
			."SET company_name = '$name', description = '$description' "
			."WHERE objectid = '$companyId'";
	
	trim($query);
	
	//Execute the query
	$result = $mysqli -> query($query);
	error_check($result);
	
	if($result) {
		echo '<script>successAlert(); </script>';
	}
	else 
	{
		echo '<script>failAlert();</script>';
	}

	mysqli_close($mysqli);
?>

</section>
</body>
</html>












