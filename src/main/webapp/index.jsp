<!DOCTYPE html>
<html>
<head>
    <title>Examples of valid requests</title>   
</head>
<body>
<h3>Get current weather conditions with latitude and longitude</h3>
<form action="/weather">
	Latitude (double): <input type="lat" name="lat"></br>
	Longitude (double): <input type="lon" name="lon"></br>
	<input type="submit">
</form>
<h3>Get supported activity types</h3>
<form action="/activityType">
	<input type="submit">
</form>
<h3>Get activity suggestions based on information provided</h3>
<form action="/activity">
	Latitude (double): <input type="lat" name="lat"></br>
	Longitude (double): <input type="lon" name="lon"></br>
	Range in Km (int): <input type="rangeKm" name="rangeKm"></br>
	DateTime ("YYYY-MM-DDTHH:MM+01:00"): <input type="dateTime" name="dateTime"></br>
	Price per person (int): <input type="pricePerPerson" name="pricePerPerson"></br>
	Number of persons (int): <input type="nbrOfPersons" name="nbrOfPersons"></br>
	Activity type (string of valid type): <input type="activityType" name="activityType"></br>
	<input type="submit">
</form>
</body>
</html>
