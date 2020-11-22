<!DOCTYPE html>
<!--
  mathcs Account: csi345_13
  File: ~/www/labs/lab4/lab4.html
  We pledge the AIC: Anna Turner, Victor Lora
-->
<html lang="en">
<head>
<title>Lab 5: PHP</title>
<meta charset="UTF-8">
<meta name="author" content="Victor Lora, Anna Turner">
<meta name="description" content="Lab5">
<meta name="keywords" content="computer science" />
<style>
    body { border: 4px solid;
           padding: 20px;
          }
    table { border: solid;
          border-collapse: collapse; }
    th, td { padding: 5px;
             border: solid; }
</style>
</head>
<body>

<?php
define ("DESIRED_LENGTH", 31);
$months = array("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
$daysInMonth = array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
?>
<h1>Lab 5</h1>
<table>
<caption>Days in a Month</caption>
<tr>
		<?php
				for  ($x = 0; $x < sizeof($months); $x++)
				{
		 		 	echo "<th>" . strtoupper($months[$x]) . "</th>";
				}
		?>
</tr>
<tr>
		<?php
				for  ($x = 0; $x < sizeof($daysInMonth); $x++)
				{
		 		 	echo "<td>" . $daysInMonth[$x] . "</td>";
				}
		?>
</tr>
</table>

		<?php
		function getMonths($desiredLength, $monthArray, $dayArray) {
					 $monthStr = "";
					 for($x = 0; $x <sizeof($dayArray); $x++) {
					 			if ($dayArray[$x] == $desiredLength) {
									 $monthStr = $monthStr . $monthArray[$x] . ", ";
								}
					 }
					 $monthStr = rtrim($monthStr, ", ");
					 
					 return $monthStr;
		}
		?>
		
		<p> The following months have <?php echo DESIRED_LENGTH ?> days: <?php echo getMonths(DESIRED_LENGTH, $months, $daysInMonth) ?></p>

</body>
</html>
