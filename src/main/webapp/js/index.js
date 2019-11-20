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
	}).done(function(response){
		var $results = $("#results");
		for (i in response){
			console.log(response[i]);
			$results.append(
					'<div><div class="result-content">Песен: '+response[i].name+'</div>'+
					'<div class="result-content">Изпълнител: '+response[i].artist+'</div>'+
					'<div class="result-content">Жанр: '+response[i].genre+'</div></div>'
					);
		}
	});
});

$("#toggleBtn").click(function(){
	var results = $(".result-content");
	var display = results.css("display");
	if (display == "block") {
		results.css("display", "inline-block");
	} else if (display == "inline-block") {
		results.css("display", "block");
	}
});