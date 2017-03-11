$(document).ready(function() {

    console.log("HELLO FROM SERVER-CONNECTION");

    var url = "http://localhost:9090/activityType";

    var data = [];

    $.ajax({
        url: url, 
        type: 'GET',
        dataType: 'application/json',
        success: function(data) 
        {
            console.log("GOT DATA" + data);
        },
        error: function(err)
        {
            console.log("ERROR" + data);// throw error
        }
    });

});