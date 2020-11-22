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
	<title>Users</title>
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

	// Connect to mysql

	$mysqli = new mysqli("localhost", "project3", "teamrow2", "pj03");

	if(!$mysqli) {
		print "Error could not connect to MySQL/Database";
		exit;
	}

	$query = "SELECT c.objectid 'ID', fname 'First Name', lname 'Last Name',"
			." 		uname 'User Name', Street, City, State, zip_code 'Zip', phone_number 'Phone', "
			." 		created_date 'Date Created', modified_date 'Date Modified', "
			." 		isSponsor 'Sponsor', isPatron 'Patron'"
			."FROM Contact c"
			."	INNER JOIN Location l ON l.objectid = location_id";

	trim($query);

	$result = $mysqli -> query($query);
	error_check($result);

	// Create the table
	print "<table class='table'><h1>Users<button type=\"button\" class=\"btn btn-default pull-right\" aria-label=\"Right Align\" 
			onclick=\"window.location.href='addContact.html'\">
  		<span class=\"glyphicon glyphicon-plus\" style=\"color:#00C800;\" aria-hidden=\"true\"></span>
	</button></h1>";
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
		print "<th>" . $next_key . "</th>";
	}
	print "<th>     </th>";

	print "</tr>";

	// Output the values

	for ($i = 0; $i < $num_rows; $i++) {
		reset($row);
		for($j = 0; $j < $num_fields / 2; $j++)
		{
			print "<th><a href=\"contactInfo.php?contactId=" .$row[0]. "\">" . $row[$j] . "</a></th>";
		}
		print 	"<th><button type=\"button\" class=\"btn btn-default\" aria-label=\"Right Align\" 
					onclick=\"window.location.href='removeContact.php?contactId=" .$row[0]."'\">
  					<span class=\"glyphicon glyphicon-remove\" style=\"color:#C80006;\" aria-hidden=\"true\"></span>
				</button></th>";
		print "</tr>";
		$row = mysqli_fetch_array($result);
	}

	print "</table><br/>";

	mysqli_close($mysqli);
	?>


</section>
</body>
</html>