<?php

	session_start();
	error_reporting(0);

	if (!isset($_SESSION['userName']))
		header("Location: home.html");

	// Declare variables
	$accountType = $_SESSION['accountType'];
	$title = $_POST["title"];
	$author = $_POST["author"];
	$genre = $_POST["genre"];
	$acts = $_POST["acts"];
	$companyID = $_POST["company"] ? $_POST["company"] : 0;
	$theaterID = $_POST["theater"] ? $_POST["theater"] : 0;

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
	<title>Create Play</title>
	<link href="../css/boostrap.css" type="text/css" rel="stylesheet">
	<link href="../css/bootstrap.min.css" type="text/css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script>
		function successAlert() {
			alert("Play was created successfully");
			window.location.href="playListings.php";
		}

		function failAlert() {
			alert("Failed to create play. Please try again or contact system administrator.");
			window.location.href="addPlay.php";
		}
	</script>
	<style>
		h1 {
			text-align: center;
		}
	</style>
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

	// Connect to mysql

	$mysqli = new mysqli("localhost", "project3", "teamrow2", "pj03");

	if(!$mysqli) {
		print "Error could not connect to MySQL/Database";
		exit;
	}

	$query = "INSERT INTO Play (company_id, theater_id, title, author, genre, number_of_acts) "
		   ."VALUES ($companyID, $theaterID, \"" .$title. "\", \"" .$author. "\", \"" .$genre. "\", $acts)";
	trim($query);

	$result = $mysqli -> query($query);
	error_check($result);

	if($result) {
		echo '<script>successAlert(); </script>';
	}
	else {
		echo '<script>failAlert(); </script>';
	}

	//Close the connection
	mysqli_close($mysqli);
?>

</section>
</body>
</html>