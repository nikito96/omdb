$("#searchBtn").click(function(){
	var name = $("#name").val();
	var artist = $("#artist").val();
	var genre = $("#genre").val();

	$.ajax({
		method: "GET",
		url: "/songs",
		data: {
			name: name,
			artist: artist,
			genre: genre
		}
	})
	.done(function(response){
		var $results = $("#results");
		$results.html("");
		$results.removeClass("bg-warning");
		$results.addClass("bg-info mt-5");
		for (i in response){
			$results.append('<div class="text-white p-3">'+
					'<div class="result-content">Песен: '+response[i].name+'</div>'+
					'<div class="result-content">Изпълнител: '+response[i].artist+'</div>'+
					'<div class="result-content">Жанр: '+response[i].genre+'</div>'+
					'</div>');
		}
		$(".result-content").addClass("bg-primary p-4");
	})
	.fail(function(){
		var $results = $("#results");
		$results.html("");
		$results.addClass("bg-warning p-3 mt-5");
		$results.append("No songs found!");
	});
});

$("#toggleBtn").click(function(){
	var $results = $(".result-content");
	var display = $results.css("display");
	if (display == "block") {
		$results.css("display", "inline-block");
	} else if (display == "inline-block") {
		$results.css("display", "block");
	}
});
