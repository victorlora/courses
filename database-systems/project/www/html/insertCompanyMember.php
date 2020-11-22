<?php

	session_start();
	error_reporting(0);

	if (!isset($_SESSION['userName']))
		header("Location: home.html");

	$accountType = $_SESSION['accountType'];

	$userName  = $_SESSION["userName"];
	$companyId = $_POST['companyId'];

	$companyMemberId = $_POST["companyMember"] ? $_POST["companyMember"] : NULL;
	$bio = $_POST["bio"] ? $_POST["bio"] : NULL;

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
	<script>
		function successAlert() {
			alert("Company member was added successfully");
			window.location.href="companyInfo.php?companyId=<?php echo $companyId ?>";
		}

		function failAlert() {
			alert("Failed to create company. Please try again or contact system administrator.");
			window.location.href="addCompanyMember.php?companyId=<?php echo $companyId ?>";
		}
	</script>
	<style>
		h4, h3 {
			text-align: center;
		}
	</style>
</head>

<body>

<section class="container">

<?php

	//Connect to database
	$mysqli = new mysqli("localhost", "project3", "teamrow2", "pj03");

	if(!$mysqli) {
		print "Error could not connect to MySQL/Database";
		exit;
	}

	$query = "INSERT INTO Company_Members (contact_id, company_id, bio) "
			."VALUES ($companyMemberId, $companyId, \"" .$bio. "\")";
	trim($query);
	$result = $mysqli -> query($query);
	error_check($result);

	if($result) {
		echo '<script>successAlert(); </script>';
	}
	else 
	{
		echo '<script>failAlert();</script>';
	}

	mysqli_close($mysqli);
?>

</section>
</body>
</html>