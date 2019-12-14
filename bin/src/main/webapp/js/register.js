$("#registerBtn").click(function(){
	var $usernameInput = $("#usernameInput");
	var $emailInput = $("#emailInput");
	var $passwordInput = $("#passwordInput");

	var username = $usernameInput.val();
	var email = $emailInput.val();
	var password = $passwordInput.val();

	$.ajax({
		method: "POST",
		url: "/register",
		data: {
			username: username,
			email: email,
			password: password
		}
	}).done(function(response){
		window.location = response;
	})
	.fail(function(response){
		console.log(response);
	});
});