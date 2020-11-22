<?php

	session_start();
	error_reporting(0);

	if (!isset($_SESSION['userName']))
		header("Location: home.html");

	$accountType = $_SESSION['accountType'];

	$playId = $_GET["playId"];
	$userName = $_SESSION['userName'];

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
	<title>Plays</title>
	<link href="../css/boostrap.css" type="text/css" rel="stylesheet">
	<link href="../css/bootstrap.min.css" type="text/css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<style>
		h4 {
			text-align: center;
			text-decoration: underline;
		}
		p {
			text-align: center;
		}
		#seats {
			text-align: center;
			color: red;
		}
		button {
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

	// Connect to the database
	$mysqli = new mysqli("localhost", "project3", "teamrow2", "pj03");

	if(!$mysqli) {
		print "Error could not connect to MySQL/Database";
		exit;
	}

	// Construct query to get contactid for username
	$query = "SELECT c.objectid "
			."FROM Contact c "
			."WHERE c.uname = \"".$userName."\"";
	trim($query);

	// Run query and check for errors

	$result = $mysqli -> query($query);
	
	error_check($result);

	// Collect data from the query

	if($result) {
		$row = mysqli_fetch_row($result);
	}

	$contactId = $row[0];

	// Construct the query for Company

	$query = "SELECT c.objectid, company_name 'Company', Description "
			."FROM Company c "
			."	INNER JOIN Play p on p.company_id = c.objectid "
			."WHERE p.objectid
			 = \"" .$playId. "\"";
	trim($query);

	// Run query and check for errors

	$result = $mysqli -> query($query);
	
	error_check($result);

	// Collect data from the query

	if($result) {
		$row = mysqli_fetch_row($result);
	}

	// Create variables

	$company = $row[1];
	$description = $row[2];

	// Construct new query for Theater

	$query = "SELECT t.objectid, Name "
			."FROM Theater t "
			."INNER JOIN Play p "
			."ON p.theater_id = t.objectid "
			."WHERE p.objectid = \"".$playId."\"";

	trim($query);

	// Run query and check for errors

	$result = $mysqli -> query($query);

	error_check($query);

	if($result) {
		$row = mysqli_fetch_row($result);
	}

	$theater_id = $row[0];
	$name = $row[1];

	// Construct new query for Seats

	$query = "SELECT seats "
		  ."FROM Seats s "
		  ."WHERE s.play_id = \"" .$playId. "\""
		  ."	AND s.theater_id = \"" .$theater_id."\"";
	trim($query);

	$result = $mysqli -> query($query);
	error_check($result);

	if($result) {
		$row = mysqli_fetch_row($result);
	}

	$seats = $row[0];

	// Construct new query for Seats

	$query = "SELECT title "
		  ."FROM Play "
		  ."WHERE objectid = \"" .$playId. "\"";
	trim($query);

	$result = $mysqli -> query($query);
	error_check($result);

	if($result) {
		$row = mysqli_fetch_row($result);
	}

	$play = $row[0];




	print "<h4>Play information</h4>";
	print "<p>Play: <strong>$play</strong></p>";
	print "<p>Theater: <strong>$name</strong></p>";
	print "<p>Company: <strong>$company</strong></p>";
	print "<h4>Description</h4>";
	print "<p>$description</p>";
	if ($seats > 0)
	{
		print "<p id='seats'><strong>Number of seats: $seats</strong></p>";
		print "<button type='button' class='btn btn-danger center-block' onclick=\"window.location.href='ticket.php?contactId=" .$contactId. "&playId=" .$playId. "&theaterId=" .$theater_id."'\">Purchase Ticket</button>";
	}
	else
	{
		print "<p id='seats'><strong>Sold out!</strong></p>";
		print "<button type='button' class='btn btn-danger center-block' disabled>Purchase Ticket</button>";
	}

	// 
	// Cast
	//
		$query = "SELECT ct.fname AS 'First Name', ct.lname AS 'Last Name', cm.bio AS 'Actors Bio'
						FROM Company c
						INNER JOIN Company_Members cm
							ON cm.company_id = c.objectid
						INNER JOIN Contact ct
							ON ct.objectid = cm.contact_id
						INNER JOIN Play p
							ON p.company_id = c.objectid
						WHERE p.objectid = \"" .$playId. "\";";

	trim($query);

	$result = $mysqli -> query($query);
	error_check($result);

	// Create the table
	print "<br /><br /><br /><br /><br /><br /><table class='table'><h1><center>Cast<center></h1>";
	print "<tr>";

	// Collect the number of rows and add them to an array
	$num_rows = mysqli_num_rows($result);
	$row = mysqli_fetch_array($result);
	$num_fields = sizeof($row);

	// If there are no rows return error message
	if($num_rows == 0) {
		print("<h5 style=\"text-align:center\">No records found</h5>");
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


	// 
	// Sponsors
	//
		$query = "SELECT concat(c.fname, ' ', c.lname) as 'Name', s.donation_type 'Type', s.amount 'Amount', Description
				FROM Sponsorship s
						INNER JOIN Sponsorship_Play_Link spl
							ON spl.sponsorship_id = s.objectid
						INNER JOIN Play p
							ON p.objectid = spl.play_id
						INNER JOIN Contact c
							ON c.objectid = s.contact_id
				WHERE p.objectid = '$play_id'
				UNION
				SELECT corp.name as 'Sponsor', s.donation_type 'Type', s.amount 'Amount', Description
				FROM Sponsorship s
						INNER JOIN Sponsorship_Play_Link spl
							ON spl.sponsorship_id = s.objectid
						INNER JOIN Play p
							ON p.objectid = spl.play_id
						INNER JOIN Corporation corp
							ON corp.objectid = s.corporation_id
				WHERE p.objectid = \"" .$playId. "\";";

	trim($query);

	$result = $mysqli -> query($query);
	error_check($result);

	// Create the table
	print "<br /><br /><br /><br /><br /><br /><table class='table'><h1><center>Sponsors<center></h1>";
	print "<tr>";

	// Collect the number of rows and add them to an array
	$num_rows = mysqli_num_rows($result);
	$row = mysqli_fetch_array($result);
	$num_fields = sizeof($row);

	// If there are no rows return error message
	if($num_rows == 0) {
		print("<h5 style=\"text-align:center\">No records found</h5>");
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