<!DOCTYPE html>

<html>

<?php
	session_start();
	error_reporting(0);

	if (!isset($_SESSION['userName']))
		header("Location: home.html");

	$accountType = $_SESSION['accountType'];
?>

<head>
	<title>Change Password</title>
	<link href="../css/bootstrap.min.css" type="text/css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script src="../js/updateAccount.js" type="text/javascript"></script>
	<script src="../js/bootstrap.min.js"></script>

	<style>
	h4 {
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
	<h4>Change Password</h4>
	<form id="form" name="form" method="post" action="passwordCheck.php">
		<section class="form-group">
			<label for="currentPassword">Enter Your Old Password</label>
			<input type="password" name="currentPassword" id="currentPassword" class="form-control" value="">
		</section>
		<section class="form-group">
			<label for="password">Enter Your New Password</label>
			<input type="password" name="password" id="password" class="form-control" value="">
		</section>
		<section class="form-group">
			<label for="confirmPassword">Confirm Your New Password</label>
			<input type="password" name="confirmPassword" id="confirmPassword" class="form-control" value="">
		</section>
		<section class="form-group">
			<button type="submit" class="btn btn-default" id="button" name="button">Submit</button>
		</section>
	</form>
</section>
</body>
</html>
