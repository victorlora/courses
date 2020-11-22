window.onload = pageLoad; // global code

// called when page loads; attaches the event handler
function pageLoad() {
  var contactForm = document.getElementById("contactForm");
  contactForm.onsubmit= validate;
	
  document.getElementById("projectType").onblur = checkProjectType;
  document.getElementById("completionDate").onblur = checkDate;
  document.getElementById("budget").onblur = checkBudget;
  document.getElementById("submitButton").onclick = submitDetails;
}

function validate() {
   var name = document.getElementById("custname").value;
   if (name == "") {
      alert("Your name is required");
      return false;
   } else if (!name.match(/^[A-Za-z ]+$/)) {
      alert("Only letters or space allowed in name field");
      return false;
   }
	 
	 var email = document.getElementById("email").value;
   if (email == "") {
      alert("Your email is required");
      return false;
   } else if (!email.match(/^[A-Za-z0-9]+\@[A-Za-z0-9]+\.[A-Za-z0-9]+$/)) {
	 	 alert("Invalid email address");
		 return false;
	 }
	 
	 var website = document.getElementById("website").value;
   if (website == "") {
      alert("Your website is required");
      return false;
   } else if (!website.match(/^http:\/\/[A-Za-z]+\.[A-Za-z]+\.[A-Za-z]+$/)) {
	 	 alert("Invalid website");
		 return false;
	 }
	 
	 var telephone = document.getElementById("telephone").value;
	 if (telephone != "" && !telephone.match(/^([0-9]{3}-)?[0-9]{3}-[0-9]{4}$/)) {
	 	 alert("Invalid telephone number");
		 return false;
	 }
	 
	 return true;
}

function checkProjectType() {
	 var projectType = document.getElementById("projectType").value;
   if (projectType == 0) {
      alert("Your projectType is required");
      return false;
	 }
	 return true;
}

function checkDate() {
	 var completionDate = document.getElementById("completionDate").value;
   if (completionDate == "") {
      alert("Your completion date is required");
      return false;
	 } else if (!completionDate.match(/^\d\d\/\d\d\/\d\d\d\d$/)) {
	 	 alert("Date is invalid");
		 return false;
	 }
	 return true;
}

function checkBudget() {
	 var budget = document.getElementById("budget").value;
   if (budget != "" && !budget.match(/^\d+$/)) {
	 	 alert("Budget is invalid");
		 return false;
	 }
	 return true;
}

function submitDetails() {
   if (checkProjectType() && checkDate() && checkBudget()) {
	 	 alert("Project details submitted");
		 return true;
	 }
	 return false;
}
