window.onload = pageLoad;

function pageLoad() {
	var form = document.getElementById("form");
	form.onsubmit = validate;
	form.onsubmit = validate;
}

function validate() {
	var firstName = document.getElementById("firstName").value;
	var lastName = document.getElementById("lastName").value;
	var phone = document.getElementById("phone").value;

	if (firstName == "") {
		alert("Please enter a first name");
		return false;
	}

	if (lastName == "") {
		alert("Please enter a last name");
		return false;
	}

	if (phone == "") {
		alert("Please enter a phone number");
		return false;
	}

	return true;
}

