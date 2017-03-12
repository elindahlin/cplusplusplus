
$(document).ready(function() {
	$('#fullpage').fullpage();
	var distance = 0;
	var pris = 0;
	var act = '';
	var people = 0;
	var long = 0;
	var lati = 0;
	var planned;

	console.log("DATE:  " + DATE);

	navigator.geolocation.getCurrentPosition(function(location) {
  		lati = location.coords.latitude;
  		long = location.coords.longitude;
	});

	var cw = $('.circle').width();
	$('.circle').css({'height':cw+'px'});

	var price = $('.price').width();
	$('.price').css({'height':price+'px'});

	$("#editLocation").hide();
	$("#content1").hide();

	var cw = $('.circle').width();
	$('.circle').css({'height':cw+'px'});

	$(".activity").click(function(){
		$(".activity").css({"border-color": "transparent"});
		$(this).css({"border-color": "#ff1d8e", 
             "border-weight":"2px"});
   		setTimeout(function() {
        	$.fn.fullpage.moveSectionDown();
    	}, 200);

   		act = $(this).find(".activityType").text();

	});

	$("#search").click(function(){
		console.log("Distance: " + distance + " Pris: " + pris + " Aktivitet: " + act
			+ " Antal: " + people + " Longitud: " + long + " Latitud: "+ lati
			+ "Datum: " + planned);
		
		var activityURL = url + "/activity?"+
			"lat="+lati+
			"&lon="+long+
			"&rangeKm="+distance+
			"&dateTime="+planned+
			"&pricePerPerson="+pris+
			"&nbrOfPersons="+people+
			"&activityType="+act;

		$.ajax({
	        url: activityURL, 
	        type: 'GET',
	        dataType: 'application/json',
	        success: function(coord) 
	        {
	            console.log("SUCCESS");
	        },
	        error: function(err)
	        {
	            console.log("ERROR: " + coord);// throw error
	        }
    	});
	});

	$(".date").click(function(){
		console.log("THIS: " + $(this).attr('id'));
		var clicked = parseInt($(this).attr('id').charAt(3));
		planned = new Date();
		planned.setDate(DATE.getDate()+clicked);
		planned = planned.toISOString();
		console.log("PLANNED DATE: " + planned);
		$.fn.fullpage.moveSectionDown();
	});

	$('#customLocation').bind("enterKey",function(e){
		console.log("Enter");
		console.log("CITY:" + $('#customLocation').val());
	});
	$('#customLocation').keyup(function(e){
		if(e.keyCode == 13) {
	  		$(this).trigger("enterKey");
	  		console.log("Enter");
			console.log("CITY:" + $('#customLocation').val());
		}
	});

	$('#btnGetCityCoords').click(function(){
		var c = getCityCoordinates($('#customLocation').val());
		//long = c.lng;
		//lati = c.lat;
		setTimeout(function() {
        	$.fn.fullpage.moveSectionDown();
    	}, 200);
	});

	$(".price").click(function(){
		var p = $(this).attr('id').charAt(6);
		$("#price-0").css({"border-color": "#FFF", 
             "border-weight":"2px"});
		$("#price-1").css({"border-color": "#FFF", 
             "border-weight":"2px"});
		$("#price-2").css({"border-color": "#FFF", 
             "border-weight":"2px"});
		$("#price-3").css({"border-color": "#FFF", 
             "border-weight":"2px"});
		$(this).css({"border-color": "#ff1d8e", 
             "border-weight":"6px", 
             "border-style":"solid"});

		if (p == 0) {
			pris = 0;
		} else if(p == 1) {
			pris = 100;
		} else if(p == 2) {
			pris = 500;
		} else if (p == 3) {
			pris = -1;
		}
		setTimeout(function() {
        	$.fn.fullpage.moveSectionDown();
    	}, 200);
	});

	$(".btnEditPosition").click(function(){
		console.log("EDIT LOCATION");
		$("#editLocation").removeClass('slideUp').addClass("slideDown").show();
		$("#editPositionFrame").css({"border-color": "#ff1d8e", 
             "border-weight":"2px"});
		$("#currentPositionFrame").css({"border-color": "#fff", 
             "border-weight":"2px"});
	});

	$("#currentPositionFrame").click(function(){
		$("#editLocation").removeClass('slideDown').addClass("slideUp").fadeOut();
		$("#editPositionFrame").css({"border-color": "#fff", 
             "border-weight":"2px"});
		$("#currentPositionFrame").css({"border-color": "#ff1d8e", 
             "border-weight":"2px"});
		setTimeout(function() {
        	$.fn.fullpage.moveSectionDown();
    	}, 200);
    	//getActivityCoordinates();
	});

	$(".distance").click(function(){
		var dis = $(this).attr('id').charAt(9);
		$("#distance-0").css({"border-color": "#FFF", 
             "border-weight":"2px"});
		$("#distance-1").css({"border-color": "#FFF", 
             "border-weight":"2px"});
		$("#distance-2").css({"border-color": "#FFF", 
             "border-weight":"2px"});
		$("#distance-3").css({"border-color": "#FFF", 
             "border-weight":"2px"});
		$(this).css({"border-color": "#ff1d8e", 
             "border-weight":"6px", 
             "border-style":"solid"});

		if(dis == 0) {
			distance = 5000;
		} else if (dis == 1) {
			distance = 10000;
		} else if(dis == 2) {
			distance = 20000;
		} else if(dis = 3){
			distance = 50000;
		}
	});

	$(".amount").click(function(event) {
		var clicked = $(this).attr('id');

		if(clicked == "first-man"){
			people = 1;
			$(this).attr('src',"images/man-pink.png");
			$("#second-man").attr('src', "images/man.png");
			$("#third-man").attr('src', "images/man.png");
			$("#fourth-man").attr('src', "images/man.png");
			$("#fifth-man").attr('src', "images/man.png");
			$("#more-men").attr('src', "images/add.png");
		} else if(clicked == "second-man") {
			people = 2;
			$(this).attr('src',"images/man-pink.png");
			$("#first-man").attr('src', "images/man-pink.png");
			$("#third-man").attr('src', "images/man.png");
			$("#fourth-man").attr('src', "images/man.png");
			$("#fifth-man").attr('src', "images/man.png");
			$("#more-men").attr('src', "images/add.png");
		} else if(clicked == "third-man") {
			people = 3;
			$("#first-man").attr('src',"images/man-pink.png");
			$("#second-man").attr('src', "images/man-pink.png");
			$("#third-man").attr('src', "images/man-pink.png");
			$("#fourth-man").attr('src', "images/man.png");
			$("#fifth-man").attr('src', "images/man.png");
			$("#more-men").attr('src', "images/add.png");
		}  else if(clicked == "fourth-man") {
			people = 4;
			$("#first-man").attr('src',"images/man-pink.png");
			$("#second-man").attr('src', "images/man-pink.png");
			$("#third-man").attr('src', "images/man-pink.png");
			$("#fourth-man").attr('src', "images/man-pink.png");
			$("#fifth-man").attr('src', "images/man.png");
			$("#more-men").attr('src', "images/add.png");
		} else if(clicked == "fifth-man") {
			people = 5;
			$("#first-man").attr('src',"images/man-pink.png");
			$("#second-man").attr('src', "images/man-pink.png");
			$("#third-man").attr('src', "images/man-pink.png");
			$("#fourth-man").attr('src', "images/man-pink.png");
			$("#fifth-man").attr('src', "images/man-pink.png");
			$("#more-men").attr('src', "images/add.png");
		} else if(clicked == "more-men") {
			people = 6;
			$("#first-man").attr('src',"images/man-pink.png");
			$("#second-man").attr('src', "images/man-pink.png");
			$("#third-man").attr('src', "images/man-pink.png");
			$("#fourth-man").attr('src', "images/man-pink.png");
			$("#fifth-man").attr('src', "images/man-pink.png");
			$("#more-men").attr('src', "images/add-pink.png");
		}
		setTimeout(function() {
        	$.fn.fullpage.moveSectionDown();
    	}, 200);
		
    });
});

function getCityCoordinates(city) {
	var url = "http://localhost:9090";

	var cityURL = url + "/location?place="+city;
	$.ajax({
        url: cityURL, 
        type: 'GET',
        dataType: 'application/json',
        success: function(coord) 
        {
            return coord;
        },
        error: function(err)
        {
            console.log("ERROR: " + coord);// throw error
        }
    });
}

function myFunction() {
    var x = document.createElement("INPUT");
    x.setAttribute("type", "range");
    document.body.appendChild(x);
}