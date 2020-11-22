window.onload = pageLoad;

function pageLoad() {
	var form = document.getElementById("form");
	form.onsubmit = validate;
	form.onsubmit = validate;
}

function validate() {
	var userName = document.getElementById("userName").value;
	var password = document.getElementById("password").value;
	var firstName = document.getElementById("firstName").value;
	var lastName = document.getElementById("lastName").value;
	var phone = document.getElementById("phone").value;
	var address = document.getElementById("address").value;
	var city = document.getElementById("city").value;
	var state = document.getElementById("state").value;
	var zip_code = document.getElementById("zip_code").value;
	var country = document.getElementById("country").value;

	if (userName == "") {
		alert("Please enter a username");
		return false;
	}

	if (password == "") {
		alert("Please enter a password");
		return false;
	}

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

	if (address == "") {
		alert("Please enter a address number");
		return false;
	}

	if (city == "") {
		alert("Please enter a city");
		return false;
	}

	if (state == 0) {
		alert("Please select a state");
		return false;
	}

	if (zip_code == "") {
		alert("Please enter a zip code");
		return false;
	}

	if (country == "") {
		alert("Please enter a country");
		return false;
	}

	return true;
}

