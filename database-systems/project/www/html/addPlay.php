<?php

	session_start();
	error_reporting(0);

	if (!isset($_SESSION['userName']))
		header("Location: home.html");

	$accountType = $_SESSION['accountType'];
	$title = $_POST["title"];
	$author = $_POST["author"];
	$genre = $_POST["genre"];
	$acts = $_POST["acts"];

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
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script src="../js/addAccount.js" type="text/javascript"></script>
	<script src="../js/bootstrap.min.js" type="text/javascript"></script>
	<title>Create Play</title>
	<link href="../css/bootstrap.min.css" type="text/css" rel="stylesheet">
	<style>
	.scrollable-menu {
	    height: auto;
	    max-height: 200px;
	    overflow-x: hidden;
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
	<form id="form" name="form" method="post" action="insertPlay.php">
		<section class="form-group">
			<label for="title">Title</label>
			<input type="text" name="title" id="title" class="form-control" value="">
		</section>
		<section class="form-group">
			<label for="author">Author</label>
			<input type="text" name="author" id="author" class="form-control" value="">
		</section>
		<section class="form-group">
			<label for="genre">Genre</label>
			<input type="text" name="genre" id="genre" class="form-control" value="">
		</section>
		<section class="form-group">
			<label for="acts">Number of Acts</label>
			<input type="text" name="acts" id="acts" class="form-control" value="">
		</section>
		<section class="form-group">
			<?php

			//Connect to mysql
			$mysqli = new mysqli("localhost", "project3", "teamrow2", "pj03");

			if(!$mysqli) {
				print "Error could not connect to MySQL/Database";
				exit;
			}

			// Create select options
			$options = '';
			$query = "SELECT objectid, company_name FROM Company";

			$result = $mysqli -> query($query);
			error_check($result);
			

			while ($row = mysqli_fetch_array($result)) {
				$options .= "<option  name='company' id='company' value=" .$row[0]. ">".$row[1]."</option>";
			}
			$menu = ("<label>Company</label>&nbsp;<select name='company' id='company'>" .$options. "</select>");
			print $menu;
			mysqli_close($mysqli);
			?>
		</section>
		<section class="form-group">
			<?php

			//Connect to mysql
			$mysqli = new mysqli("localhost", "project3", "teamrow2", "pj03");

			if(!$mysqli) {
				print "Error could not connect to MySQL/Database";
				exit;
			}

			// Create select options
			$options = '';
			$query = "SELECT objectid, name FROM Theater";

			$result = $mysqli -> query($query);
			error_check($result);
			

			while ($row = mysqli_fetch_array($result)) {
				$options .= "<option  name='theater' id='theater' value=" .$row[0]. ">".$row[1]."</option>";
			}
			$menu = ("<label>Theater</label>&nbsp;<select name='theater' id='theater'>" .$options. "</select>");
			print $menu;
			mysqli_close($mysqli);
			?>
		</section>
		<hr>
			<button type="submit" class="btn btn-default" id="button" name="button">Submit</button>
		</section>
	</form>
</section>



</body>
</html>