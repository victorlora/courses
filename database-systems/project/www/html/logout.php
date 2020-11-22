<html>
<head>
	<title>Logout</title>
	<link href="../css/bootstrap.min.css" type"text/css" rel="stylesheet">
<body>

<section class="container">
	<?php
	   session_start();
	   unset($_SESSION["userName"]);
	   unset($_SESSION["password"]);

	   session_destroy();
	   header('Location: home.html');
	?>
</section>
</body>
</html>