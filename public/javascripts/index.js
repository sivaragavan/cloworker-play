function onLoad() {

	$("#signup").click(function() {
		$.ajax({
			url : "/user/create?email=" + $('#email').val() + "&username=" + $('#username').val() + "&password=" + $('#password').val()
		}).done(function(data) {
			console.log("Create Response", data);
			var Json = eval("(" + data + ")");			
			if (Json.result == true) {
				setCookie("cloapp-sessionId", Json.sessionId, 14);
				window.location.href = "/dashboard";
			} else {
				alert(Json.message);
			}
		});
	});

	$("#signin").click(function() {
		$.ajax({
			url : "/user/login?username=" + $('#username1').val() + "&password=" + $('#password1').val()
		}).done(function(data) {
			console.log("Login Response", data);
			var Json = eval("(" + data + ")");
			if (Json.result == true) {
				setCookie("cloapp-sessionId", Json.sessionId, 14);
				window.location.href = "/dashboard";
			} else {
				alert(Json.message);
			}
		});
	});
}

function setCookie(c_name, value, exdays) {
	var exdate = new Date();
	exdate.setDate(exdate.getDate() + exdays);
	var c_value = escape(value) + ((exdays == null) ? "" : "; expires=" + exdate.toUTCString());
	document.cookie = c_name + "=" + c_value;
}