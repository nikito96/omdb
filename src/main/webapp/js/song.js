$(document).ready(function(){
	const urlParams = new URLSearchParams(window.location.search);
	const id = urlParams.get('id');
	var $name = $("#name");
	var $artist = $("#artist");
	var $genre = $("#genre");

	$.ajax({
		method: "GET",
		url: "/songs/" + id
	})
	.done(function(response){
		$name.html(response.name);
		$artist.html(response.artist);
		$genre.html(response.genre);
	});
});