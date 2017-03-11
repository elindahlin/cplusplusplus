
$(document).ready(function() {
	$('#fullpage').fullpage();

	console.log("READY");
	
	$('input[type="range"]').val(10).change();

	$("#content1").hide();

	var cw = $('.circle').width();
	$('.circle').css({'height':cw+'px'});


function myFunction() {
    var x = document.createElement("INPUT");
    x.setAttribute("type", "range");
    document.body.appendChild(x);
}

	$(".amount").click(function(event) {
		var clicked = $(this).attr('id');
		var people = 0;

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
		console.log("Search activity with " + people + " people...");
		
    });


});

function sayhey(){
 	$.fn.fullpage.moveSectionDown();
	$("#locationSection").addClass('slideUp');
}