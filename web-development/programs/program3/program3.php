<?php
    session_start();

    function validateInput() {
        $dateFormat = 'm-d-Y';
        $errorArr = array();

        if (!isset($_POST["month"]) || empty($_POST["month"]) 
                || !isset($_POST["day"]) || empty($_POST["day"])
                || !isset($_POST["year"]) || empty($_POST["year"])) {
            $errorArr[] = "Complete date is required";
        } else if (DateTime::createFromFormat($dateFormat, 
                    $_POST["month"].'-'.$_POST["day"].'-'.$_POST["year"], 
                    new DateTimeZone('America/New_York')) == false) {
            $errorArr[] = "Incorrect date";
        } else if ($_POST["month"] > 12 || $_POST["month"] < 1) {
            $errorArr[] = "Incorrect date";
        } else if ($_POST["day"] > 31 || $_POST["day"] < 1) {
            $errorArr[] = "Incorrect date";
        }
                    

        if (!isset($_POST["hoursWorked"]) || empty($_POST["hoursWorked"])) {
            $errorArr[] = "Hours worked is required";
        } else if (!preg_match('/^\-?\d*$/', $_POST["hoursWorked"])) {
            $errorArr[] = "Hours must be a number";
        } else if ($_POST["hoursWorked"] <= 0) {
            $errorArr[] .= "Hours must be positive";
        } else if ($_POST["hoursWorked"] > 168) {
            $errorArr[] .= "Hours worked must be less than the number of hours in a week";
        }


        if (!isset($_POST["payRate"]) || empty($_POST["payRate"])) {
            $errorArr[] = "Pay rate is required";
        } else if (!preg_match('/^\-?\d*\.?\d*$/', $_POST["payRate"])) {
            $errorArr[] = "Pay rate must be a number";
        } else if ($_POST["payRate"] < 7.37) {
            $errorArr[] = "Payrate cannot be less than the minimum wage of $7.37 per hour";
        }

        return $errorArr;
    }

    if (isset($_POST['submitButton'])) {
        $errors = validateInput();

        if (empty($errors)) {
            $_SESSION["month"] = $_POST["month"];
            $_SESSION["day"] = $_POST["day"];
            $_SESSION["year"] = $_POST["year"];
            $_SESSION["hoursWorked"] = $_POST["hoursWorked"];
            $_SESSION["payRate"] = $_POST["payRate"];
            header("Location: getPay.php");
            exit();
        } else {
            $errorMessage = "<ul>";
            foreach ($errors as $value) {
                $errorMessage .= "<li>$value</li>";
            }
            $errorMessage .= "</ul>";
        }
    }

?>

<!DOCTYPE html>
<!--
  mathcs Account: csi345_13
  File: ~/www/programs/program3/program3.php
  I pledge the AIC: Victor A. Lora
-->

<html lang="en-US">
<head>
    <title>Program 3</title>
    <meta charset="utf-8">
    <meta name="description" content="Program 3: PHP Form">
    <meta name="keywords" content="CSI345; Program3; HTML Form; PHP validation">
    <meta name="author" content="Victor A. Lora">
    <link rel="stylesheet" type="text/css" href="program3.css">
</head>

<body>
    <h1>Program 3</h1>
    <div id="errorMessage">
    <?= $errorMessage ?>
    </div>
           
    <form id="payForm" action="program3.php" method="POST">
        <fieldset>
            <legend>Pay period ending</legend>
            <p class="formInputs">
            <label for="month">Month</label>
            <input id="month" name="month" type="text" value="<?= $_POST["month"] ?>" />
            <label for="day">Day</label>
            <input id="day" name="day" type="text" value="<?= $_POST["day"] ?>" />
            <label for="year">Year</label>
            <input id="year" name="year" type="text" value="<?= $_POST["year"] ?>" />
            <span class="required">* Required</span>
            </p>
        </fieldset>

        <fieldset>
            <legend>Hours worked and pay rate</legend>
            <p class="formInputs">
            <label for="hoursWorked">Hours worked</label>
            <input id="hoursWorked" name="hoursWorked" type="text" value="<?= $_POST["hoursWorked"] ?>" />
            <span class="required">* Required</span>
            </p>
            <p class="formInputs">
            <label for="payRate">Pay rate</label>
            <input id="payRate" name="payRate" type="text" value="<?= $_POST["payRate"] ?>" />
            <span class="required">* Required</span>
            </p>
       </fieldset>

       <p id="button-holder">
       <input id="submitButton" name="submitButton" type="submit" value="Calculate Pay"/>
       </p>
    </form>

</body>
</html>

