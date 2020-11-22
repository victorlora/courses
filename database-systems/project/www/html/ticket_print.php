<?php
	
	session_start();
	error_reporting(0);

	if (!isset($_SESSION['userName']))
		header("Location: home.html");

	function error_check($query_result) {
		if(!$query_result) {
			print "Error - query could not be executed<br/>";
			$error = mysql_error();
			print "<p>".$error."</p>";
		}
	}
	
	$accountType = $_SESSION["accountType"];
	$userId = $_SESSION["userId"];
	$ticketId = $_GET["ticketId"];

	$mysqli = new mysqli("localhost", "project3", "teamrow2", "pj03");

	if(!$mysqli) {
		print "Error could not connect to MySQL/Database";
		exit;
	}

	$query = "SELECT at.play_id, at.theater_id, p.title 'Play', p.Author, p.Genre, t.name 'Theater', at.objectid,"
			." concat(c.fname, ' ', c.lname) as Name "
			."FROM Admission_Ticket at "
			."	INNER JOIN Play p ON p.objectid = at.play_id "
			."	INNER JOIN Theater t ON t.objectid = at.theater_id "
			."  INNER JOIN Contact c ON c.objectid = at.contact_id "
			."WHERE at.objectid = $ticketId";

	trim($query);

	$result = $mysqli -> query($query);
	error_check($result);
	if($result) {
		$row = mysqli_fetch_row($result);
	}

	$playId = $row[0];
	$theaterId = $row[1];
	$play = $row[2];
	$author = $row[3];
	$genre = $row[4];
	$theater = $row[5];
	$ticketn = $row[6];
	$name = $row[7];

?>

<html>
<head>
	<title>Print Ticket</title>
</head>
<body>
<IMG SRC="ticket_template.jpg" alt="test">
<DIV STYLE="position:absolute; top:14px; left:80px; width:415px; height:25px">
<CENTER><FONT SIZE="+1" COLOR="FFFFFF">Pleasantville Community Theater Presents: </FONT></CENTER>
</DIV>

<DIV STYLE="position:absolute; top:50px; left:80px; width:415px; height:25px">
<CENTER><FONT SIZE="+3" COLOR="FFFFFF"><b><?php echo $play ?></b></FONT></CENTER>
</DIV>

<DIV STYLE="position:absolute; top:100px; left:80px; width:415px; height:25px">
<CENTER><FONT SIZE="+2" COLOR="FFFFFF"><?php echo $theater ?> Theater</FONT></CENTER>
</DIV>

<DIV STYLE="position:absolute; top:125px; left:80px; width:415px; height:25px">
<CENTER><FONT SIZE="+1" COLOR="FFFFFF">2400 W Chew Street</FONT></CENTER>
</DIV>

<DIV STYLE="position:absolute; top:145px; left:80px; width:415px; height:25px">
<CENTER><FONT SIZE="+1" COLOR="FFFFFF">Allentown, PA 18104</FONT></CENTER>
</DIV>

<DIV STYLE="position:absolute; top:200px; left:80px; width:415px; height:25px">
<CENTER><FONT SIZE="+3" COLOR="FFFFFF"><?php echo $name ?></FONT></CENTER>
</DIV>

<DIV STYLE="position:absolute; top:270px; left:80px; width:415px; height:25px">
<CENTER><FONT SIZE="+1" COLOR="FFFFFF">No Refunds/Exchanges</FONT></CENTER>
</DIV>

<DIV STYLE="position:absolute; top:14px; left:575px; width:150px; height:25px">
<CENTER><FONT SIZE="+1" COLOR="FFFFFF">$7.00</FONT></CENTER>
</DIV>

<DIV STYLE="position:absolute; top:44px; left:575px; width:150px; height:25px">
<CENTER><FONT SIZE="+1" COLOR="FFFFFF"><b><?php echo $play ?></b></FONT></CENTER>
</DIV>

<DIV STYLE="position:absolute; top:94px; left:575px; width:150px; height:25px">
<CENTER><FONT SIZE="+0.75" COLOR="FFFFFF"><?php echo $theater ?> Theater</FONT></CENTER>
</DIV>

<DIV STYLE="position:absolute; top:144px; left:575px; width:150px; height:25px">
<CENTER><FONT SIZE="+1.5" COLOR="FFFFFF"><b><?php echo $name ?></b></FONT></CENTER>
</DIV>

<DIV STYLE="position:absolute; top:190px; left:575px; width:150px; height:25px">
<CENTER><FONT SIZE="+0.5" COLOR="FFFFFF">Ticket #: <?php echo $ticketn ?></FONT></CENTER>
</DIV>

<DIV STYLE="position:absolute; top:214px; left:575px; width:150px; height:25px">
<CENTER><FONT SIZE="+1" COLOR="FFFFFF">Purchase Date:</FONT></CENTER>
</DIV>

<DIV STYLE="position:absolute; top:234px; left:575px; width:150px; height:25px">
<CENTER><FONT SIZE="+1" COLOR="FFFFFF"><script language="javascript">
 var today = new Date();
 document.write(today);
 </script></CENTER>
</DIV>

</body>
</html>