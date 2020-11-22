// Declare and initialize the frequencies array to 0


function generateTable(numberOfRolls) {
		var frequencies = new Array(13);
		for (var index = 2; index <= 12; index++) {
   			frequencies[index] = 0;
		}

		for (var index = 0; index < numberOfRolls; index++) {
				// get a random number between 1 and 6
			 dice1 = Math.floor(Math.random() * 6) + 1;
			 dice2 = Math.floor(Math.random() * 6) + 1;
			 total = dice1 + dice2;
			 frequencies[total] = frequencies[total] + 1;
			// document.writeln("<p>for loop 1</p>
		}

		// get a percentage
		var totalFrequencies = 0;
		var totalPercentage = 0;
		for (var index = 2; index <=12; index++) {
				percent = Math.round((frequencies[index] / numberOfRolls) * 100);
				totalFrequencies += frequencies[index];
				totalPercentage += percent;
				document.writeln("<tr>");
				document.writeln("<th>" + index + "</th>");
				document.writeln("<td>" + frequencies[index] + "</td>");
				document.writeln("<td>" + percent.toFixed(2) + "%</td>");
				document.writeln("</tr>");
		}
		
		document.writeln("<tr>");
		document.writeln("<th>Totals</th>");
		document.writeln("<th>" + totalFrequencies + "</th>");
		document.writeln("<th>" + totalPercentage.toFixed(2) + "%</th>");
		document.writeln("</tr>");
}