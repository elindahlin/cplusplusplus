function ajaxGet(localUrl) {
    var url = "http://localhost:9090";
    //var url = "https://mayfly-c-plus-plus-plus.herokuapp.com";
    return $.ajax({
        url: url + localUrl, 
        type: 'GET',
        error: function(err)
        {
            console.log("ERROR:");// throw error
            console.log(err);
        }
    });
}