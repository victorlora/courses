<?php

	session_start();
	error_reporting(0);

	if (!isset($_SESSION['userName']))
		header("Location: home.html");

	$accountType = $_SESSION['accountType'];
	
	$contactId = $_GET['contactId'];
	$playId = $_GET['playId'];
	$theaterId = $_GET['theaterId'];

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
	<title>Ticket Purchase</title>
	<link href="../css/boostrap.css" type="text/css" rel="stylesheet">
	<link href="../css/bootstrap.min.css" type="text/css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script>
		function successAlert() {
			alert("Ticket purchased successfully");
			window.location.href="playInfo.php?playId=<?php echo $playId ?>";
		}

		function failAlert() {
			alert("Failed to perform update. Please try again or contact system administrator.");
			window.location.href="playInfo.php?playId=<?php echo $playId ?>";
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

<?php


	//Connect to mysqli and database
	$mysqli = new mysqli("localhost", "project3", "teamrow2", "pj03");

	if(!$mysqli) {
		print "Error could not connect to MySQL/Database";
		exit;
	}

	$query = "INSERT INTO Admission_Ticket "
			  ."(contact_id, play_id, theater_id) "
			  ."VALUES "
			  ." ($contactId, $playId, $theaterId)";

	trim($query);

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


</body>

</html>