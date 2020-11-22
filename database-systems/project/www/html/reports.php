<?php
	session_start();
	error_reporting(0);

	if (!isset($_SESSION['userName']))
		header("Location: home.html");

	$userId 	 = $_SESSION['userId'];
	$accountType = $_SESSION['accountType'];

?>

<html>
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script src="../js/addAccount.js" type="text/javascript"></script>
	<script src="../js/bootstrap.min.js" type="text/javascript"></script>
	<title>Reports</title>
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


	<h1>Reports</h1>
	<div class="container">
  		<div class="row row-prim">
		    <div class="col-sm-4">
		    	<button type="button" class="btn btn-default btn-lg" 
		    		onclick="window.location.href='memberDues.php'">
		    		Member Dues
		    	</button>
		    </div>
		    <div class="col-sm-4">
		    	<button type="button" class="btn btn-default btn-lg" 
		    		onclick="window.location.href='patrons.php'">
		    		Patrons
		    	</button>
		    </div>
		    <div class="col-sm-4">
		    	<button type="button" class="btn btn-default btn-lg" 
		    		onclick="window.location.href='ticketSalesReport.php'">
		    		Ticket Sales Report
		    	</button>
		    </div>
		</div>
	</div>

</body>

</html>