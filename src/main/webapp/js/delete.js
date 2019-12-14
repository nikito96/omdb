$(document).on('click', ".delete", function() {
	var id = $(this).attr("songId");
	var $songForRemove = $(this).closest(".song");

	$.ajax({
		method: "POST",
		url: "/song/" + id
	})
	.done(function(){
		$songForRemove.remove();
	});
});