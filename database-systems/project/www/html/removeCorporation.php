<html>
	<head>
		<script>
			function successAlert() {
				alert("Corporation was removed successfully");
				window.location.href="corporation.php";
			}

			function failAlert() {
				alert("Failed to remove corporation. Please try again or contact system administrator.");
				window.location.href="corporation.php";
			}
		</script>
	</head>

<body>
	<?php

		session_start();
		if (!isset($_SESSION['userName']))
			header("Location: home.html");

		$corporationId = $_GET['corporationId'];

		function error_check($query_result) {
			if(!$query_result) {
				print "Error - query could not be executed<br/>";
				$error = mysql_error();
				print "<p>".$error."</p>";
			}
		}

		$mysqli = new mysqli("localhost", "project3", "teamrow2", "pj03");

		if(!$mysqli) {
			print "<p>Could not connect to MySQL/Database";
			exit;
		}

		$query = " DELETE FROM Corporation "
				." WHERE objectid = \"".$corporationId."\"";
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

	?>

</body>
</html>