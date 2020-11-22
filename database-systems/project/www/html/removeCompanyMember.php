<?php

		session_start();
		error_reporting(0);
		
		if (!isset($_SESSION['userName']))
			header("Location: home.html");

		$userName = $_SESSION["userName"];
		$companyId = $_GET['companyId'];
		$companyMemberId = $_GET['companyMemberId'];

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
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		<script>
			function successAlert() {
				alert("Account information updated successfully");
				window.location.href="companyInfo.php?companyId=<?php echo $companyId ?>";
			}

			function failAlert() {
				alert("Failed to perform update. Please try again or contact system administrator.");
				window.location.href="companyInfo.php?companyId=<?php echo $companyId ?>";
			}
		</script>
	</head>
<body>

<?php
	$mysqli = new mysqli("localhost", "project3", "teamrow2", "pj03");

		if(!$mysqli) {
			print "<p>Could not connect to MySQL/Database";
			exit;
		}

		$query = " DELETE FROM Company_Members "
				." WHERE company_id = $companyId "
				."   	 AND contact_id = $companyMemberId";
		trim($query);

		$result = $mysqli -> query($query);
		
		error_check($result);

		if($result) {
			echo '<script>successAlert();</script>';
		}
		else 
		{
			echo '<script>failAlert();</script>';
		}

	?>
</body>
</html>