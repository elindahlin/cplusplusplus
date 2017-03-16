
$(function() {
	$('#fullpage').fullpage();
	var userPreferences = { distance : 0, 
							price : 0,
							activity : "",
							people : 0,
							lat : 0,
							lon : 0,
							planned : "",
							today : "",
							activities : "" };

	hideLoading();

 	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(function(location) {
 	 		userPreferences.lat = location.coords.latitude;
 	 		userPreferences.lon = location.coords.longitude;
  			var init = setInitials(userPreferences.lat, userPreferences.lon, 
  						userPreferences);
  			console.log("Current position: " + userPreferences.lat + ", " +
  						userPreferences.lon);
		});
	} else {
		console.log("Geolocation is not supported by this browser.");
	}

	initView();

	$(".date").click(function() {
		userPreferences.planned = selectDate(this, userPreferences);
		moveToNextSectionWithDelay(200);
		console.log("Selected date: " + userPreferences.planned);
	});

	$('#customLocation').bind("enterKey",function(e){
		console.log("Enter");
		console.log("CITY bind:" + $('#customLocation').val());
	});
	$('#customLocation').keyup(function(e){
		if(e.keyCode == 13) {
	  		$(this).trigger("enterKey");
	  		console.log("Enter");
			console.log("CITY keyup:" + $('#customLocation').val());
		}
	});

	$("#currentPositionFrame").click(function(){
		currentPositionClicked();
		// use current position for userPreferences => no update (reset?)
		console.log("Selected position: " + userPreferences.lat + ", " +
  						userPreferences.lon);
		moveToNextSectionWithDelay(200);
	});
    	
	$(".btnEditPosition").click(function(){
		editLocationClicked();
	});

	$('#btnGetCityCoords').click(function(){
		getCityCoordinates($('#customLocation').val(), userPreferences);
		moveToNextSectionWithDelay(200);
		console.log("Selected position: " + userPreferences.lat + ", " +
  						userPreferences.lon);
	});

	$(".activity").click(function() {
		userPreferences.activity = selectActivity(this);
		moveToNextSectionWithDelay(200);
		console.log("Selected activity: " + userPreferences.activity);
	});

	$(".cost").click(function(){
		userPreferences.price = selectPriceRange(this);
		console.log("Selected max price: " + userPreferences.price);
		moveToNextSectionWithDelay(200);
	});

	$(".amount").click(function(event) {
		userPreferences.people = selectNbrOfPersons(this);
		console.log("Selected group size: " + userPreferences.people);
		moveToNextSectionWithDelay(200);
    });

	$(".distance").click(function(){
		userPreferences.distance = selectDistance(this);
		console.log("Selected max distance: " + userPreferences.distance);
	});

	$("#search").click(function() {
		searchForActivities(userPreferences);
	});

	$(".more-info").click(function(){
		console.log("Show more info about..." + $(this).attr('id'));
	});
});

function setInitials(lat, lon, userPreferences) {
	getCity(lat, lon);
	getCurrentWeather(lat, lon, userPreferences);
}

function selectDate(buttonClicked, userPreferences) {
		var clickedId = parseInt($(buttonClicked).attr('id').charAt(3));
		var dateToday = new Date(userPreferences.today);
		var planned = new Date();
		planned.setDate(dateToday.getDate()+clickedId);
		return planned.toISOString();
}

function selectActivity(buttonClicked) {
	return activityClicked(buttonClicked);
}

function selectPriceRange(buttonClicked) {
	var buttonId = priceClicked(buttonClicked);
	var price;
	if (buttonId == 0) {
		price = 0;
	} else if (buttonId == 1) {
		price = 100;
	} else if (buttonId == 2) {
		price = 500;
	} else if (buttonId == 3) {
		price = -1;
	}	
    return price;
}

function selectNbrOfPersons(buttonClicked) {
	return peopleClicked(buttonClicked);
}

function selectDistance(buttonClicked) {
	var buttonId = distanceClicked(buttonClicked);
	var distance;
	if (buttonId == 0) {
		distance = 5000;
	} else if (buttonId == 1) {
		distance = 10000;
	} else if (buttonId == 2) {
		distance = 20000;
	} else if (buttonId = 3) {
		distance = 50000;
	}

	return distance;
}

function searchForActivities(userPreferences) {
	clearActivityTable();
	
	console.log("SEARCHING FOR MATCHES  ->  Distance: " + userPreferences.distance + 
			", Price: " + userPreferences.price + 
			", Activity: " + userPreferences.activity + 
			", Number: " + userPreferences.people + 
			", Longitude: " + userPreferences.lon + 
			", Latitude: " + userPreferences.lat + 
			", Date: " + userPreferences.planned);
		
	var activityUrl = "/activity?" +
			"lat=" + userPreferences.lat +
			"&lon=" + userPreferences.lon +
			"&rangeKm=" + userPreferences.distance +
			"&dateTime=" + userPreferences.planned +
			"&pricePerPerson=" + userPreferences.price +
			"&nbrOfPersons=" + userPreferences.people +
			"&activityType=" + userPreferences.activity;
		
	getActivities(activityUrl, userPreferences);
}

function myFunction() {
    var x = document.createElement("INPUT");
    x.setAttribute("type", "range");
    document.body.appendChild(x);
}