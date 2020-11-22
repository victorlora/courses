<?php

	error_reporting(0);

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
	<title>Login Check</title>
	<link href="../css/boostrap.css" type="text/css" rel="stylesheet">
	<link href="../css/bootstrap.min.css" type="text/css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
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
		<section class="collapse navbar-collapse" id="nav">
		<ul class="nav navbar-nav">
			<li><a href="home.html">Home</a></li>
			<li><a href="addAccount.html">Create An Account</a></li>
		</ul>
		</section>
	</section>
</nav>

<section class="container">
<?php
	
	session_start();
	
	$mysqli = new mysqli("localhost", "project3", "teamrow2", "pj03");

	if(!$mysqli) {
		print "<p>Could not connect to MySQL/Database</p>";
		exit;
	}
	
	//Get user and password sent from form

	$userName = $_POST["userName"];
	$password = $_POST["password"];

	//Securing the variables from SQL injection

	$userName = stripslashes($userName);
	$password = stripslashes($password);
	$userName = $mysqli->real_escape_string($userName);
	$password = $mysqli->real_escape_string($password);

	if ($userName != NULL && $password != NULL)
		$query = "SELECT objectid, accountType "
				."FROM Contact "
				."WHERE uname = '$userName' AND password = '$password'";
	else
	{
		print "<h3>The user and/or password is invalid</h3>";
		exit;
	}

	trim($query);

	$result = $mysqli -> query($query);
	error_check($result);
	if ($result)
	{
		$num_rows = mysqli_num_rows($result);
		$row = mysqli_fetch_array($result);
		$userId = $row[0];
		$accountType = $row[1];
	}

	if($num_rows != 0) {
		$_SESSION['userName']    = $userName;
		$_SESSION['password']    = $password;
		$_SESSION['userId']      = $userId;
		$_SESSION['accountType'] = $accountType;
		header("Location: main.php");
	}
	else {
		echo "<h3>The user and/or password is invalid</h3>";
	}

	mysqli_close($mysqli);
?>
</section>

</body>
</html>



