function hideLoading() {
	$("#loading").hide();
}

function moveToNextSection() {
	$.fn.fullpage.moveSectionDown();
}

function moveToNextSectionWithDelay(delay) {
	setTimeout(function() {
        $.fn.fullpage.moveSectionDown();
    }, delay);
}

function initView() {
	var cw = $('.circle').width();
	$('.circle').css({'height':cw+'px'});

	$("#editLocation").hide();
	$("#content1").hide();
}

function getWeekDay(dateString){
    var date = new Date(dateString);
    var day = date.getDay();
    $("#day1").text(getDayName(day+1));
    $("#day2").text(getDayName(day+2));
    $("#day3").text(getDayName(day+3));
    $("#day4").text(getDayName(day+4));
    $("#day5").text(getDayName(day+5));
    $("#day6").text(getDayName(day+6));
}

function getDayName(day){

    var day = day%7;
    if(day == 0) {
        return 'Sunday';
    } else if(day == 1) {
        return 'Monday';
    }  else if(day == 2) {
        return 'Tuesday';
    }  else if(day == 3) {
        return 'Wednesday';
    }  else if(day == 4) {
        return 'Thursday';
    }  else if(day == 5) {
        return 'Friday';
    } else if(day == 6) {
        return 'Saturday';
    }
}

function setWeatherGif(weather){
    if(weather === 'CLEAR_SKY' || weather === 'NEARLY_CLEAR_SKY') {
        // solig gif
        $("body").css("background-image", "url('http://i.giphy.com/CPghlEnbaVa2A.gif')");
    } else if(weather === 'VARIABLE_CLOUDINESS' || weather === 'HALFCREAR_SKY'
        || weather === 'CLOUDY_SKY' || weather === 'OVERCAST' || weather === 'FOG') {
        //  molnig gif
        $("body").css("background-image", "url('http://i.giphy.com/5HK4TiiBeLSZq.gif')");
    } else if(weather === 'RAIN_SHOWERS' || weather === 'RAIN' || weather === 'LIGHT_SLEET'
        || weather === 'SLEET') {
        $("body").css("background-image", "url('http://i35.photobucket.com/albums/d183/KnightMare21/rain-1.gif')");
    } else if(weather === 'THUNDERSTORM') {
        $("body").css("background-image", "url('http://i.giphy.com/xaZCqV4weJwHu.gif')");
    } else if(weather === 'SNOW_SHOWERS' || weather === 'SNOWFALL') {
        $("body").css("background-image", "url('http://i.giphy.com/BDucPOizdZ5AI.gif')");
    }
}

function currentPositionClicked() {
	$("#editLocation").removeClass('slideDown').addClass("slideUp").fadeOut();
	$("#editPositionFrame").css({"border-color": "#fff", 
             "border-weight":"2px"});
	$("#currentPositionFrame").css({"border-color": "#ff1d8e", 
             "border-weight":"2px"});
}

function editLocationClicked() {
	$("#editLocation").removeClass('slideUp').addClass("slideDown").show();
	$("#editPositionFrame").css({"border-color": "#ff1d8e", 
             "border-weight":"2px"});
	$("#currentPositionFrame").css({"border-color": "#fff", 
             "border-weight":"2px"});
}

function activityClicked(buttonClicked) {
	$(".activity").css({"border-color": "transparent"});
	$(buttonClicked).css({"border-color": "#ff1d8e", 
        "border-weight":"2px"});
    return $(buttonClicked).find(".activityType").text();
}

function priceClicked(buttonClicked) {
	$("#price-0").css({"border-color": "#FFF", 
            "border-weight":"2px"});
	$("#price-1").css({"border-color": "#FFF", 
             "border-weight":"2px"});
	$("#price-2").css({"border-color": "#FFF", 
             "border-weight":"2px"});
	$("#price-3").css({"border-color": "#FFF", 
             "border-weight":"2px"});
	$(buttonClicked).css({"border-color": "#ff1d8e", 
             "border-weight":"6px", 
             "border-style":"solid"});
	return $(buttonClicked).attr('id').charAt(6);
}

function peopleClicked(buttonClicked) {
	var people;
	var clicked = $(buttonClicked).attr('id');
	if(clicked == "first-man"){
		people = 1;
		$("#first-man").attr('src',"images/man-pink.png");
		$("#second-man").attr('src', "images/man.png");
		$("#third-man").attr('src', "images/man.png");
		$("#fourth-man").attr('src', "images/man.png");
		$("#fifth-man").attr('src', "images/man.png");
		$("#more-men").attr('src', "images/add.png");
	} else if(clicked == "second-man") {
		people = 2;
		$("#first-man").attr('src', "images/man-pink.png");
		$("#second-man").attr('src',"images/man-pink.png");
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
	return people;
}

function distanceClicked(buttonClicked) {
	$("#distance-0").css({"border-color": "#FFF", 
             "border-weight":"2px"});
	$("#distance-1").css({"border-color": "#FFF", 
             "border-weight":"2px"});
	$("#distance-2").css({"border-color": "#FFF", 
             "border-weight":"2px"});
	$("#distance-3").css({"border-color": "#FFF", 
             "border-weight":"2px"});
	$(buttonClicked).css({"border-color": "#ff1d8e", 
             "border-weight":"6px", 
             "border-style":"solid"});
	return $(buttonClicked).attr('id').charAt(9);
}

function showActivities(activities) {
	for (var i = 0; i < activities.length; i++) {
		var activity = activities[i];
		var row = "<th class=\" col-xs-2\"><img class=\"img-responsive\" src=\"images/" + activity.activityCategory.toLowerCase() + ".png\"></th>" +
				  "<th>" + activity.activityCategory + "</th>" + "<th id=\"activityCategory\" class=\"more-info\"><img src=\"images/arrow-right.png\"></th>";
	    var tr = "<tr class=\"col-xs-offset-1\">" + row + "</tr>";
		$('#activityTable').append(tr);
	}
}

function clearActivityTable() {
	$('#activityTable').empty();
}