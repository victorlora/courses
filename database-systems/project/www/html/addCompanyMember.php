<?php

	session_start();
	error_reporting(0);

	if (!isset($_SESSION['userName']))
		header("Location: home.html");

	$accountType = $_SESSION['accountType'];

	$userName  = $_SESSION["userName"];
	$companyId = $_GET['companyId'];

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
	<title>Update Company Member</title>
	<link href="../css/bootstrap.min.css" type="text/css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script src="../js/updateAccount.js" type="text/javascript"></script>
	<script src="../js/bootstrap.min.js"></script>

	<style>
		h4, h3 {
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
	<form id="form" name="form" method="post" action="insertCompanyMember.php">
		<input type="hidden" name="companyId" id="companyId" value="<?php echo $companyId ?>">
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
			$query = "SELECT c.objectid, concat(c.fname, ' ', c.lname) "
					."FROM Contact c "
					."WHERE c.objectid NOT IN (SELECT contact_id FROM Company_Members WHERE company_id = $companyId)";

			$result = $mysqli -> query($query);
			error_check($result);
			

			while ($row = mysqli_fetch_array($result)) {
				$options .= "<option  name='companyMember' id='companyMember' value=".$row[0].">".$row[1].$row[2]."</option>";
			}
			$menu = ("<label>Company Member</label>&nbsp;<select name='companyMember' id='companyMember'>" .$options. "</select>");
			print $menu;
			mysqli_close($mysqli);
			?>
		</section>
		<section class="form-group">
			<label for="bio">Bio</label>
			<textarea type="text" name="bio" id="bio" class="form-control" value=""
			placeholder="Enter bio here..."></textarea>
		</section>
		<section class="form-group">
			<button type="submit" class="btn btn-default" id="button" name="button">Submit</button>
		</section>
	</form>
</section>
</body>
</html>