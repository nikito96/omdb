$(document).on('click', ".delete", function() {
	var id = $(this).attr("songId");
	var $songForRemove = $(this).closest(".song");

	$.ajax({
		method: "DELETE",
		url: "/songs/" + id
	})
	.done(function(){
		$songForRemove.remove();
	});
});