$(document).ready(function(){
	const urlParams = new URLSearchParams(window.location.search);
	const id = urlParams.get('id');
	var $idInput = $("#updatedId");
	var $nameInput = $("#updatedName");
	var $artistInput = $("#updatedArtist");
	var $genreInput = $("#updatedGenre");
	$.ajax({
		method: "GET",
		url: "/songs/" + id,
	})
	.done(function(response){
		$idInput.val(response.id);
		$nameInput.val(response.name);
		$artistInput.val(response.artist);
		$genreInput.val(response.genre);
	});
});

$("#updateButton").click(function(){
	var $idInput = $("#updatedId");
	var $nameInput = $("#updatedName");
	var $artistInput = $("#updatedArtist");
	var $genreInput = $("#updatedGenre");

	var id = $idInput.val();
	var updatedName = $nameInput.val();
	var updatedArtist = $artistInput.val();
	var	updatedGenre = $genreInput.val();

	$.ajax({
		method: "PUT",
		url: "/songs/" + id,
		data: {
			name: updatedName,
			artist: updatedArtist,
			genre:updatedGenre
		}
	}).done(function(response){
		console.log(response);
	});
});