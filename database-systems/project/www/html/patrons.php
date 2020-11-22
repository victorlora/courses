<?php
	session_start();
	error_reporting(0);

	if (!isset($_SESSION['userName']))
		header("Location: home.html");

	$userId 	 = $_SESSION['userId'];
	$accountType = $_SESSION['accountType'];

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
	<title>Patrons Report</title>
	<link href="../css/bootstrap.min.css" type="text/css" rel="stylesheet">
	<style>
	.scrollable-menu {
	    height: auto;
	    max-height: 200px;
	    overflow-x: hidden;
	}
	.row-prim {
		display: block;
		text-align: center;
		padding-top: 20;
	}
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
		</section>
		<a class="navbar-brand" href="main.php">Welcome</a>
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

	// Conntect to database

	$mysqli = new mysqli("localhost", "project3", "teamrow2", "pj03");

	if(!$mysqli) {
		print "Error could not connect to MySQL/Database";
		exit;
	}

	//Construct the query

	$query = "SELECT concat(c.fname, ' ', c.lname) as 'Name', l.street 'Address', l.city 'City', l.state 'State', l.country 'Country', l.zip_code 'ZIP', c.phone_number 'Phone Number' "
			."FROM Contact c "
			."INNER JOIN Location l "
			."	ON c.location_id = l.objectid "
			."WHERE c.isPatron = 1";
	trim($query);

	$result = $mysqli -> query($query);

	error_check($result);

	print "<table class='table'><h1>Patrons</h1>";
	print "<tr>";

	// Collect the number of rows and add them to an array

	$num_rows = mysqli_num_rows($result);
	$row = mysqli_fetch_array($result);
	$num_fields = sizeof($row);


	// If there are no rows return error message
	if($num_rows == 0) {
		print("<h4 style=\"text-align:center\">There are currently no patrons</h4>");
		exit;
	}


	// Label the columns
	while($next_element = each($row)) {
		$next_element = each($row);
		$next_key = $next_element['key'];
		print "<th>" . $next_key . "</th>";
	}

	print "</tr>";

	// Output the values

	for ($i = 0; $i < $num_rows; $i++) {
		reset($row);
		for($j = 0; $j < $num_fields / 2; $j++)
		{
			print "<th>" . $row[$j] . "</th>";
		}
		print "</tr>";
		$row = mysqli_fetch_array($result);
	}

	print "</table><br/>";

	mysqli_close($mysqli);
	?>

</section>
</body>
</html>