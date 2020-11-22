<?php
    session_start();
    echo var_dump($_SESSION);
    $month = $_SESSION['month'];
    $day   = $_SESSION['day'];
    $year  = $_SESSION['year'];
    $dateFormat = 'm-d-Y';
    $formattedDate = DateTime::createFromFormat($dateFormat, $month.'-'.$day.'-'.$year, new DateTimeZone('America/New_York'));

    $hoursWorked = $_SESSION['hoursWorked'];
    $payRate = $_SESSION['payRate'];
    if ($hoursWorked > 40) {
        $totalEarned = 40 * $payRate + ($hoursWorked % 40) * ($payRate * 1.5);
    } else {
        $totalEarned = $hoursWorked * $payRate;
    }
?>

<!DOCTYPE html>
<!--
  mathcs Account: csi345_13
  File: ~/www/programs/program3/getPay.php
  I pledge the AIC: Victor A. Lora
-->

<html lang="en-US">
<head>
    <title>Get Pay</title>
    <meta charset="utf-8">
    <meta name="description" content="Get Pay">
    <meta name="keywords" content="CSI345; Get Pay; HTML Form; PHP validation">
    <meta name="author" content="Victor A. Lora">
    <link rel="stylesheet" type="text/css" href="program3.css">
</head>

<body>
    <h1>Get Pay</h1>
    <table>
        <caption>Earning for pay period ending <?= $formattedDate->format('M d, Y') ?></caption>
        <tr>
            <th>Hours Worked</th>
            <th>Pay Rate</th>
            <th>Amount Earned</th>
        </tr>
        <tr>
            <td><?= $hoursWorked ?></td>
            <td><?= money_format('$%i', $payRate) ?></td>
            <td><?= money_format('$%i', $totalEarned) ?></td>
        </tr>
    </table>
    <p>Return to <a href="program3.php">program3.php</a></p>
</body>
</html>

