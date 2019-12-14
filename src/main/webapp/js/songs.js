$(document).ready(function(){
	var $result = $("#songs");
	$.ajax({
		method: "GET",
		url: "/songs",
	}).done(function(response){
		for(i in response){
			$result.append('<div class="song">'+
			'<div><span>Име: </span>'+response[i].name+'</div>'+
			'<div><span>Изпълнител: </span>'+response[i].artist+'</div>'+
			'<div><span>Жанр: </span>'+response[i].genre+'</div>'+
			'<button songId="'+response[i].id+'" class="delete">Изтрий</button>'+
			'<a>'+'</a>'+
			'</div>');
		}
	});
});