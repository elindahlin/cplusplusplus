var DATE;
//var url = "http://localhost:9090";
var url = "https://mayfly-c-plus-plus-plus.herokuapp.com";
var lon = 0;
var lat = 0;

$(document).ready(function() {

    // Get coordinates
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(setInitials);
    } else {
        console.log("Geolocation is not supported by this browser.");
    }
	
	
});

function setInitials(position) {
    console.log("Latitude: " + position.coords.latitude + 
    "<br>Longitude: " + position.coords.longitude); 

	lat = position.coords.latitude;
	lon = position.coords.longitude;
	
	getCity();
	getCurrentWeather();
}

function getCity() {
	
    var cityUrl = url + "/locationName?lat="+lat+"&lon="+lon;
	console.log(cityUrl);

    $.ajax({
        url: cityUrl, 
        type: 'GET',
        success: function(city) 
        {
            console.log("GOT CITY: " + city.name);
            $("#location").text(city.name);
        },
        error: function(err)
        {
            console.log("ERROR city: " + err);// throw error
        }
    });
}

function getCurrentWeather() {
    var weatherURL = url + "/weather?lat="+lat+"&lon="+lon;
	console.log(weatherURL);
	
    $.ajax({
        url: weatherURL, 
        type: 'GET',
        //dataType: 'application/json',
        success: function(weather) 
        {
            console.log("Temperature: " + weather.temperature);
            $("#temperature").text(weather.temperature + " °C");
            setWeatherGif(weather.weatherType);
            getWeekDay(weather.date);
            DATE = new Date(weather.date);
        },
        error: function(err)
        {
            console.log("ERROR weather: " + weather);// throw error
        }
    });
}

function setWeatherGif(weather){
    if(weather === 'CLEAR_SKY' || weather === 'NEARLY_CLEAR_SKY') {
        // solig gif
        $("body").css("background-image", "url('http://i.giphy.com/128ydcHOsrk5GM.gif')");
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