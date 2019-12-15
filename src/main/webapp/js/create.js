$("#createButton").click(function(){
	var $nameInput = $("#name");
	var $artistInput = $("#artist");
	var $genreInput = $("#genre");

	var name = $nameInput.val();
	var artist = $artistInput.val();
	var genre = $genreInput.val();

	$.ajax({
		method: "POST",
		url: "/songs",
		data: {
			name: name,
			artist: artist,
			genre: genre
		}
	}).done(function(response){
		console.log(response);
		window.location = "song.html?id="+response.id;
	});
});