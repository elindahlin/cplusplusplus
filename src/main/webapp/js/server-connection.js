function getCity(lat, lon) {
    ajaxGet("/locationName?lat="+lat+"&lon="+lon).then(function(city) {
        console.log("Current location: " + city.name);
        $("#location").text(city.name);
        return city.name;
    });
}

function getCurrentWeather(lat, lon, userPreferences) {
    return ajaxGet("/weather?lat="+lat+"&lon="+lon).then(function(weather) {
        console.log("Weather: " + weather.weatherType);
        console.log("Temperature: " + weather.temperature);
        $("#temperature").text(weather.temperature + " " + String.fromCharCode(176) + "C");
        setWeatherGif(weather.weatherType);
        getWeekDay(weather.date);
        userPreferences.today = weather.date;
    });
}

function getCityCoordinates(city, userPreferences) {
    ajaxGet("/location?place="+city).then(function(coord) {
        userPreferences.lat = coord.lat;
        userPreferences.lon = coord.lng;
    });
}

function getActivities(activityUrl, userPreferences) {
    $("#loading").show();
    ajaxGet(activityUrl).then(function(activities) {
        console.log("SUCCESS");
        setTimeout(function() {
            $.fn.fullpage.moveSectionDown();
        }, 200);
        userPreferences.activities = activities;
        showActivities(userPreferences.activities);
    }).always(function() {
        $("#loading").hide();
    });
}