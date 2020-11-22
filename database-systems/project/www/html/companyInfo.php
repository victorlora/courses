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
	<title>Update Company Information</title>
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


<?php


	$mysqli = new mysqli("localhost", "project3", "teamrow2", "pj03");

	if(!$mysqli) {
		print "<p>Could not connect to MySQL/Database";
		exit;
	}

	$query = " SELECT c.objectid, company_name, description "
			." FROM Company c"
			." WHERE c.objectid = \"".$companyId."\"";
	trim($query);

	$result = $mysqli -> query($query);
	
	error_check($result);

	if($result) {
		$row = mysqli_fetch_row($result);
	}

	$companyId   = $row[0];
	$name        = $row[1];
	$description = $row[2];

?>

<section class="container">
	<h4 >Please fill out the information to be updated</h4>
	<form id="form" name="form" method="post" action="updateCompanyInfo.php">
		<input type="hidden" name="companyId" id="companyId" value="<?php echo $companyId ?>">
		<section class="form-group">
			<label for="name">Name</label>
			<input type="text" name="name" id="name" class="form-control" value="<?php echo $name ?>">
		</section>
		<section class="form-group">
			<label for="description">Description</label>
			<textarea type="text" name="description" id="description" class="form-control" value="<?php echo $description ?>"><?php echo $description ?></textarea>
		</section>
		<section class="form-group">
			<button type="submit" class="btn btn-default" id="button" name="button">Update</button>
		</section>
		<br>
		<br>
		<br>
		<?php
			$query = "SELECT c.objectid 'Contact ID', comp.objectid 'Company ID', c.fname 'First Name', c.lname 'Last Name', cm.bio 'Bio'"
				."FROM Company_Members cm "
				."INNER JOIN Company comp "
				."ON comp.objectid = cm.company_id "
				."INNER JOIN Contact c "
				."ON c.objectid = cm.contact_id "
				."WHERE comp.objectid = $companyId";
			trim($query);

			$result = $mysqli -> query($query);
			error_check($result);


			print "<table class='table'><h3>Company Members<button type=\"button\" class=\"btn btn-default pull-right\" aria-label=\"Right Align\" onclick=\"window.location.href='addCompanyMember.php?companyId=".$companyId."'\">
  				<span class=\"glyphicon glyphicon-plus\" style=\"color:#00C800;\" aria-hidden=\"true\"></span>
			</button></h3>";
			print "<tr>";

			// Collect the number of rows and add them to an array
			$num_rows = mysqli_num_rows($result);
			$row = mysqli_fetch_array($result);
			$num_fields = sizeof($row);

			// If there are no rows return error message
			if($num_rows == 0) {
				print("<h4 style=\"text-align:center\">No company members found</h4><br><br>");
				exit;
			}

			// Label the columns
			while($next_element = each($row)) {
				$next_element = each($row);
				$next_key = $next_element['key'];
				if ($next_key != "Contact ID" && $next_key != "Company ID")
					print "<th>" . $next_key . "</th>";
			}
			print "<th>     </th>";
			print "</tr>";

			// Output the values
			for ($i = 0; $i < $num_rows; $i++) {
				reset($row);
				for($j = 2; $j < $num_fields / 2; $j++)
				{
					print "<th>" . $row[$j] . "</th>";
					
				}
				print "<th><button type=\"button\" class=\"btn btn-default\" aria-label=\"Right Align\" 
					onclick=\"window.location.href='removeCompanyMember.php?companyId=" .$row[1]. "&companyMemberId=" .$row[0]. "'\">
  					<span class=\"glyphicon glyphicon-remove\" style=\"color:#C80006;\" aria-hidden=\"true\"></span>
				</button></th>";
				print "</tr>";
				$row = mysqli_fetch_array($result);
			}

			print "</table><br/>";
			mysqli_close($mysqli);

		?>
	</form>
</section>

</body>
</html>

