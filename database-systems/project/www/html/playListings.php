<?php

	session_start();
	error_reporting(0);

	if (!isset($_SESSION['userName']))
		header("Location: home.html");

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
	<title>Plays</title>
	<link href="../css/boostrap.css" type="text/css" rel="stylesheet">
	<link href="../css/bootstrap.min.css" type="text/css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
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

	//Connect to database system
	
	$mysqli = new mysqli("localhost", "project3", "teamrow2", "pj03");

	if(!$mysqli) {
		print "Error could not connect to MySQL/Database";
		exit;
	}

	// Create Query

	$query = "SELECT play_id, p.company_id, s.theater_id, company_name 'Company', name 'Theater', title 'Title', Author, Genre, number_of_acts 'Number of Acts' "
			." FROM Seats s"
			."		INNER JOIN Play    p ON p.objectid = play_id"
			."		INNER JOIN Company c ON c.objectid = p.company_id"
			."		INNER JOIN Theater t ON t.objectid = s.theater_id"
			." WHERE p.active = 1";
	trim($query);

	$result = $mysqli -> query($query);
	error_check($result);


	// Create the table
	if ($accountType == "Administrator")
	{
		print "<table class='table'><h1>Play Listings<button type=\"button\" class=\"btn btn-default pull-right\" aria-label=\"Right Align\" 
				onclick=\"window.location.href='addPlay.php'\">
	  		<span class=\"glyphicon glyphicon-plus\" style=\"color:#00C800;\" aria-hidden=\"true\"></span>
		</button></h1>";
	}
	else
	{
		print "<table class='table'><h1>Play Listings</h1>";
	}
	print "<tr>";

	// Collect the number of rows and add them to an array

	$num_rows = mysqli_num_rows($result);
	$row = mysqli_fetch_array($result);
	$num_fields = sizeof($row);

	// If there are no rows return error message

	if($num_rows == 0) {
		print("<h4 style=\"text-align:center\">No records found</h4>");
		exit;
	}

	// Label the columns

	while($next_element = each($row)) {
		$next_element = each($row);
		$next_key = $next_element['key'];
		if (!($next_key === "play_id" || $next_key === "theater_id" || $next_key === "company_id"))
		{
			print "<th>" . $next_key . "</th>";
		}
	}

	print "</tr>";

	// Output the values

	for ($i = 0; $i < $num_rows; $i++) {
		reset($row);
		for($j = 3; $j < $num_fields / 2; $j++)
		{
			print "<th><a href=\"playInfo.php?playId=" .$row[0]. "\">" . $row[$j] . "</a></th>";
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