$(document).on('click', ".delete", function() {
	var id = $(this).attr("songId");

	$.ajax({
		method: "DELETE",
		url: "/songs",
		data : {
			id: id
		}
	});
});