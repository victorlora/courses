<?php 
session_start();
$name=$_SESSION["name"];


?>
<!DOCTYPE html>

<!--
      Account: csi345_23
      File: ~/www/labs/lab7/thankyou.php
      Honor Code: We pledge the AIC: Victor Lora & Kathryn Prozzo
-->

<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Thank you</title>
<meta name="description" content="Lab Form Validation" />
<meta name="keywords" content="CS" />
<meta name="author" content="Your Names" />
</head>

<body>
<h1>Thank You</h1>




<p>Thank you, <?php echo $name ?> </p>

</body>
</html>
