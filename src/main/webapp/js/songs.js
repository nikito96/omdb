$(document).ready(function(){
	var $result = $("#songs");
	$.ajax({
		method: "GET",
		url: "/songs",
	}).done(function(response){
		for(i in response){
			$result.append('<div class="song m-3 p-3 border border-light rounded">'+
			'<div class="d-inline m-2">'+
			'<span class="m-1 font-weight-bold">Име: </span>'+
			'<a class="m-1 text-white bg-dark p-1" href="/song.html?id='+response[i].id+'">'+response[i].name+'</a>'+
			'</div>'+
			'<div class="d-inline m-2"><span class="font-weight-bold">Изпълнител: </span>'+response[i].artist+'</div>'+
			'<div class="d-inline m-2"><span class="font-weight-bold>Жанр: </span>'+response[i].genre+'</div>'+
			'<div class="d-inline m-2">'+
			'<a class="btn-dark p-1" href="/update.html?id='+response[i].id+'">Редактирай</a>'+
			'</div>'+
			'<div class="d-inline m-2">'+
			'<button class="btn-danger delete" songId="'+response[i].id+'" class="delete">Изтрий</button>'+
			'</div>');
		}
	});
});