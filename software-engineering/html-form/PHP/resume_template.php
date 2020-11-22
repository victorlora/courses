<!doctype html>
<html>
<head>
	<title>Resume</title>
	<link rel="stylesheet" href="resume.css">
</head>
<body>
	<?php

	session_start();

	if (isset($_SESSION["firstName"])) {          
			$firstName = $_SESSION["firstName"];}  

	if (isset($_SESSION["lastName"])) {          
			$lastName = $_SESSION["lastName"];}

	if (isset($_SESSION["address"])) {          
			$address = $_SESSION["address"];}

	if (isset($_SESSION["phone"])) {          
			$phone = $_SESSION["phone"];}

	if (isset($_SESSION["email"])) {          
			$email = $_SESSION["email"];}

	if (isset($_SESSION["objective"])) {          
			$objective = $_SESSION["objective"];}

	if (isset($_SESSION["experience1"])) {          
			$experience1 = $_SESSION["experience1"];}

	if (isset($_SESSION["startDate1"])) {          
			$startDate1 = $_SESSION["startDate1"];}

	if (isset($_SESSION["endDate1"])) {          
			$endDate1 = $_SESSION["endDate1"];}

	if (isset($_SESSION["description1"])) {          
			$description1 = $_SESSION["description1"];}

	if (isset($_SESSION["experience2"])) {          
			$experience2 = $_SESSION["experience2"];}

	if (isset($_SESSION["startDate2"])) {          
			$startDate2 = $_SESSION["startDate2"];}

	if (isset($_SESSION["endDate2"])) {          
			$endDate2 = $_SESSION["endDate2"];}

	if (isset($_SESSION["description2"])) {          
			$description2 = $_SESSION["description2"];}

	if (isset($_SESSION["schoolName"])) {          
			$schoolName = $_SESSION["schoolName"];}

	if (isset($_SESSION["degree"])) {          
			$degree = $_SESSION["degree"];}

	if (isset($_SESSION["graduationDate"])) {          
			$graduationDate = $_SESSION["graduationDate"];}

	if (isset($_SESSION["awardAchievement1"])) {          
			$awardAchievement1 = $_SESSION["awardAchievement1"];}

	if (isset($_SESSION["awardAchievement2"])) {          
			$awardAchievement2 = $_SESSION["awardAchievement2"];}

	if (isset($_SESSION["awardAchievement3"])) {          
			$awardAchievement3 = $_SESSION["awardAchievement3"];}

	?>
	<div id="wrapper">
		<div id="wrapper2">
			<p>
				<span id="name">
					<?php 
						echo $firstName;
						echo " ";
						echo $lastName;
					?>
				</span>	
				<br>
				<span id="info">
					<?php 
						echo $address;
						echo " | ";
						echo $phone;
						echo " | ";
						echo $email; 
					?>
				</span>
			</p>
		
		
		<h3>Objective</h3>

			<p>
				<span id="objective" class="ten">
					<?php echo $objective; ?>
				</span>
			</p>


		<h3>Experience</h3>
			<p>
				<span class="experience">
					<?php echo $experience1; ?>
				</span>
				<br>
				<span class="eight">
					<?php 
						echo $startDate1; 
						echo " - ";
						echo $endDate1;
					?>
				</span>

				<ul class="ten">
					<li>
						<?php echo $description1; ?>
					</li>
				</ul>
				<span class="experience">
					<?php echo $experience2; ?>
				</span>
				<br>
				<span class="eight">
					<?php 
						echo $startDate2; 
						echo " - ";
						echo $endDate2;
					?>
				</span>
				<ul class="ten">
					<li>
						<?php echo $description2; ?>
					</li>
				</ul>
			</p>


		<h3>Education</h3>	
			<p>
				<span id="schoolName">
					<?php echo $schoolName;?>
				</span>
				<br>
				<span id="degree" class="ten">
					<?php echo $degree; ?>
				</span>
				<span id="gradYear" class="ten">
					<?php echo $graduationDate; ?>
				</span>
			</p>


		<h3>Awards and Acknowledgments</h3>
			<ul class="ten">
				<li>
					<?php echo $awardAchievement1; ?>
				</li>

				<li>
					<?php echo $awardAchievement2; ?>
				</li>

				<li>
					<?php echo $awardAchievement3; ?>
				</li>
			</ul>
		</div>
	</div>
	<form id="resumeInput" action="done.html" method="post">
		<input id="submitButton" name="doneButton" value="Done!" type="submit">
	</form>

</body>
</html>