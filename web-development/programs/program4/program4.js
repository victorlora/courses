// Account: csi345_13
// File: ~/www/programs/program4/program4.js
// A.I.C Victor A. Lora

window.onload = pageLoad; // global code

// Called when page loads; attaches the event handler
function pageLoad() {
  document.getElementById("buyerName").onblur = validateBuyerName;
  document.getElementById("address").onblur = validateAddress;
  document.getElementById("city").onblur = validateCity;
  document.getElementById("unpopped").onblur = validateQuantity;
  document.getElementById("caramel").onblur = validateQuantity;
  document.getElementById("caramelNut").onblur  = validateQuantity;
  document.getElementById("toffeyNut").onblur  = validateQuantity;
  document.getElementById("ccid").onblur = validateCCNumber;
  document.getElementById("visa").onchange = handlePaymentTypeChange;
  document.getElementById("master").onchange = handlePaymentTypeChange;
  document.getElementById("check").onchange = handlePaymentTypeChange;
  document.getElementById("submitButton").onclick = submitDetails;
}

function validateBuyerName() {
  var buyerName = document.getElementById("buyerName");

  if (buyerName.value == "") {
    alert("Your name is required.");
    return false;
  } else if (!buyerName.value.match(/^[A-Z][a-z]+\s+[A-Z][a-z]+$/)){
    alert("Your name must conform to the following format 'Firstname Lastname'");
    return false;
  }

  return true;
}

function validateAddress() {
  var address = document.getElementById("address");

  if (address.value == "") {
    alert("Your address is required.");
    return false;
  } else if (!address.value.match(/^\d+\s+[A-Z][a-z]+(\s{1,2}[A-z][a-z]+)?$/)){
    alert("Street address value begins with a number, followed by one or more "
    + "spaces, followed by one or two street names (starting with an uppercase "
    + "letter), with at most two spaces between them");
    return false;
  }

  return true;
}

function validateCity() {
  var city = document.getElementById("city");

  if (city.value == "") {
    alert("Your city, state, and zip code is required.");
    return false;
  } else if (!city.value.match(/^[A-Z][a-z]+,?\s*[A-Z][A-Za-z],?\s*\d{5}$/)){
    alert("City must begin with an uppercase letter followed by at least one "
    + "lowercase letter followed by an optional comma. "
    + "State must begin with an uppercase letter followed by one upper or "
    + "lowercase letter followed by an optional comma. "
    + "Zip must be exactly 5 digits");
    return false;
  }

  return true;
}

function validateQuantity() {
  var unpopped = document.getElementById("unpopped").value;
  var caramel = document.getElementById("caramel").value;
  var caramelNut = document.getElementById("caramelNut").value;
  var toffeyNut = document.getElementById("toffeyNut").value;

  if (unpopped == "" && caramel == "" && caramelNut == "" && toffeyNut == "") {
    alert("At least one quantity must be set.");
    return false;
  }

  if (unpopped != "") {
    if (!validateQuantityValue(unpopped)) {
      return false;
    }
  }

  if (caramel != "") {
    if (!validateQuantityValue(caramel)) {
      return false;
    }
  }

  if (caramelNut != "") {
    if (!validateQuantityValue(caramelNut)) {
      return false;
    }
  }

  if (toffeyNut != "") {
    if (!validateQuantityValue(toffeyNut)) {
      return false;
    }
  }

  return true;

}

function validateQuantityValue(value) {
  if (!value.match(/\d/)) {
    alert("Quantity must be a number.");
    return false;
  } else if (value < 1 || value > 99) {
    alert("The value must be a number between 1-99.");
    return false;
  }

  return true;
}

function handlePaymentTypeChange() {
  var radios = document.getElementsByName('paymentType');
  var paymentType = "check"

  for (var i = 0, length = radios.length; i < length; i++) {
    if (radios[i].checked) {
      paymentType = radios[i].value;
      if (paymentType == "check") {
        document.getElementById("ccid").value = "";
      } else {
        validateCCNumber();
      }
      break;
    }
  }
}

function validateCCNumber() {
  var radios = document.getElementsByName('paymentType');
  var paymentType = "check"

  for (var i = 0, length = radios.length; i < length; i++) {
    if (radios[i].checked) {
      paymentType = radios[i].value;
      break;
    }
  }

  var ccNumber = document.getElementById("ccid");
  if (paymentType != "check") {
    if (ccNumber.value == "") {
      alert("A credit card number is required.");
      return false;
    } else if (!ccNumber.value.match(/(\d{4}\-\d{4}\-\d{4}\-\d{4})||(\d{16})/)) {
      alert("Your credit card number must be in one of the following formats: "
      + " 'dddd-dddd-dddd-dddd' or dddddddddddddddd");
      return false;
    }
  }

  return true;
}

function validate() {
  if (validateBuyerName() && validateAddress() && validateCity() && validateCCNumber() && validateQuantity()) {
    return true;
  }

  return false;
}

function submitDetails() {
  if(!validate()) {
    return false;
  }

  return true;
}
