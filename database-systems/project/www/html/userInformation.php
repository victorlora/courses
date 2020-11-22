<?php

	session_start();
	error_reporting(0);
	
	if (!isset($_SESSION['userName']))
		header("Location: home.html");

	$userId = $_SESSION["userId"];
	$userName = $_SESSION["userName"];
	$accountType = $_SESSION["accountType"];

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
	<title>User Information</title>
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


<?php


	$mysqli = new mysqli("localhost", "project3", "teamrow2", "pj03");

	if(!$mysqli) {
		print "<p>Could not connect to MySQL/Database";
		exit;
	}

	$query = " SELECT c.objectid, fname, lname, location_id, "
			."       phone_number, street, city, state,"
			."		 zip_code, country"
			." FROM Contact c"
			." INNER JOIN Location l ON c.location_id = l.objectid"
			." WHERE c.objectid = \"" .$userId. "\"";
	trim($query);

	$result = $mysqli -> query($query);
	
	error_check($result);

	if($result) {
		$row = mysqli_fetch_row($result);
	}

	$contact_id = $row[0];
	$firstName = $row[1];
	$lastName = $row[2];
	$locId = $row[3];
	$phone = $row[4];
	$address = $row[5];
	$city = $row[6];
	$state = $row[7];
	$zip = $row[8];
	$country = $row[9];
	

	


?>

<section class="container">
	<h4 >Please fill out the information to be updated</h4>
	<form id="form" name="form" method="post" action="updateUserInformation.php">
		<section class="form-group">
			<label for="firstName">First Name</label>
			<input type="text" name="firstName" id="firstName" class="form-control" value="<?php echo $firstName ?>">
		</section>
		<section class="form-group">
			<label for="lastName">Last Name</label>
			<input type="text" name="lastName" id="lastName" class="form-control" value="<?php echo $lastName ?>">
		</section>
		<section class="form-group">
			<label for="phone">Phone Number</label>
			<input type="text" name="phone" id="phone" class="form-control" value="<?php echo $phone ?>">
		</section>
		<hr>
		<section class="form-group">
			<label for="address">Address</label>
			<input type="text" name="address" id="address" class="form-control" value="<?php echo $address ?>">
		</section>
		<section class="form-group">
			<label for="city">City</label>
			<input type="text" name="city" id="city" class="form-control" value="<?php echo $city ?>">
		</section>
		<section class="form-group">
			<label for="state">State</label>
			<input type="text" name="state" id="state" class="form-control" value="<?php echo $state ?>">
		</section>
		<section class="form-group">
			<label>Zip Code</label>
			<input type="text" name="zip_code" id="zip_code" class="form-control" value="<?php echo $zip ?>">
		</section>
		<section class="form-group">
			<label for="country">Country</label>
			<input type="text" name="country" id="country" class="form-control" value="<?php echo $country ?>">
		</section>
		<section class="form-group">
			<button type="submit" class="btn btn-default" id="button" name="button">Submit</button>
		</section>
	</form>
</section>

<script>

	//Create json list of dropdown
	var jsonList = {"List" : [
	{"value" : "0" , "Name" : "Select a State"},
    {"value" : "AL", "Name" : "AL", "name" : "AL"},
    {"value" : "AK", "Name" : "AK", "name" : "AK"},
    {"value" : "AZ", "Name" : "AZ", "name" : "AZ"},
    {"value" : "AR", "Name" : "AR", "name" : "AR"},
    {"value" : "CA", "Name" : "CA", "name" : "CA"},
    {"value" : "CO", "Name" : "CO", "name" : "CO"},
    {"value" : "CT", "Name" : "CT", "name" : "CT"},
    {"value" : "DE", "Name" : "DE", "name" : "DE"},
    {"value" : "FL", "Name" : "FL", "name" : "FL"},
    {"value" : "GA", "Name" : "GA", "name" : "GA"},
    {"value" : "HI", "Name" : "HI", "name" : "HI"},
    {"value" : "ID", "Name" : "ID", "name" : "ID"},
    {"value" : "IL", "Name" : "IL", "name" : "IL"},
    {"value" : "IN", "Name" : "IN", "name" : "IN"},
    {"value" : "IA", "Name" : "IA", "name" : "IA"},
    {"value" : "KS", "Name" : "KS", "name" : "KS"},
    {"value" : "KY", "Name" : "KY", "name" : "KY"},
    {"value" : "LA", "Name" : "LA", "name" : "LA"},
    {"value" : "ME", "Name" : "ME", "name" : "ME"},
    {"value" : "Md", "Name" : "MD", "name" : "MD"},
    {"value" : "MA", "Name" : "MA", "name" : "MA"},
    {"value" : "MI", "Name" : "MI", "name" : "MI"},
    {"value" : "MN", "Name" : "MN", "name" : "MN"},
    {"value" : "MS", "Name" : "MS", "name" : "MS"},
    {"value" : "MO", "Name" : "MO", "name" : "MO"},
    {"value" : "MT", "Name" : "MT", "name" : "MT"},
    {"value" : "NE", "Name" : "NE", "name" : "NE"},
    {"value" : "NV", "Name" : "NV", "name" : "NV"},
    {"value" : "NH", "Name" : "NH", "name" : "NH"},
    {"value" : "NJ", "Name" : "NJ", "name" : "NJ"},
    {"value" : "NM", "Name" : "NM", "name" : "NM"},
    {"value" : "NY", "Name" : "NY", "name" : "NY"},
    {"value" : "NC", "Name" : "NC", "name" : "NC"},
    {"value" : "ND", "Name" : "ND", "name" : "ND"},
    {"value" : "OH", "Name" : "OH", "name" : "OH"},
    {"value" : "OK", "Name" : "OK", "name" : "OK"},
    {"value" : "OR", "Name" : "OR", "name" : "OR"},
    {"value" : "PA", "Name" : "PA", "name" : "PA"},
    {"value" : "RI", "Name" : "RI", "name" : "RI"},
    {"value" : "SC", "Name" : "SC", "name" : "SC"},
    {"value" : "SD", "Name" : "SD", "name" : "SD"},
    {"value" : "TN", "Name" : "TN", "name" : "TN"},
    {"value" : "TX", "Name" : "TX", "name" : "TX"},
    {"value" : "UT", "Name" : "UT", "name" : "UT"},
    {"value" : "VT", "Name" : "VT", "name" : "VT"},
    {"value" : "VA", "Name" : "VA", "name" : "VA"},
    {"value" : "WA", "Name" : "WA", "name" : "WA"},
    {"value" : "WV", "Name" : "WV", "name" : "WV"},
    {"value" : "WI", "Name" : "WI", "name" : "WI"},
    {"value" : "WY", "Name" : "WY", "name" : "WY"}
	]}

//Populate dropdown menu for selection of dropdown
$(document).ready(function(){
    var listItems = "";
    	for (var i = 0; i < jsonList.List.length; i++){
        listItems += "<option name=" + jsonList.List[i].name + " value=" + jsonList.List[i].value + ">" + jsonList.List[i].Name + "</option>";
    }
$("#state").html(listItems);

});
</script>

</body>
</html>

