<?php

function calculateTotal() {

  $unpoppedPrice = 3.0;
  $caramelPrice = 3.5;
  $caramelNutPrice = 4.5;
  $toffeyNutPrice = 5.0;

  $unpoppedQty = $_GET['unpopped'];
  $caramelQty = $_GET['caramel'];
  $caramelNutQty = $_GET['caramelNut'];
  $toffeyNutQty = $_GET['toffeyNut'];

  $total = 0.0;

  if (!empty($unpoppedQty)) {
    $total += $unpoppedQty * $unpoppedPrice;
  }

  if (!empty($caramelQty)) {
    $total += $caramelQty * $caramelPrice;
  }

  if (!empty($caramelNutQty)) {
    $total += $caramelNutQty * $caramelNutPrice;
  }

  if (!empty($toffeyNutQty)) {
    $total += $toffeyNutQty * $toffeyNutPrice;
  }

  return $total;
}

?>

<!DOCTYPE html>

<!--
  Account: csi345_13
  File: ~/www/programs/program4/program4.php
  AIC: Victor A. Lora
-->

<html lang="en-US">
<head>
    <title>ACM Club: Popcorn Confirmation</title>
    <meta name="description" content="CSI345 Program 4: JavaScript Error Checking" />
    <meta name="keywords" content="CSI345; Program 4; Javascript; Error Checking" />
    <meta name="author" content="Victor A. Lora" />
    <link href="program4.css" type="text/css" rel="stylesheet">
</head>
<body>
  <h1>ACM Club Popcorn Confirmation</h1>
  <p>Thank you for your order.</p>
  <p>Total amount owed is <?= money_format('$%i', calculateTotal()); ?></p>
  <p>Return to <a href="program4.html">program4.html</a></p>
</body>
</html>
