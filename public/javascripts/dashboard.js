function onLoad() {

	$("#logout").click(function() {
		$.ajax({
			url : "/user/logout?sessionId=" + sessionId
		}).done(function(data) {
			console.log("logout Response", data);
			var Json = eval("(" + data + ")");			
			if (Json.result == true) {
				removeCookie("cloapp-sessionId");
				window.location.href = "/";
			} else {
				alert(Json.message);
			}
		});
	});

}

function removeCookie(c_name) {
	var exdate = new Date();
	exdate.setDate(exdate.getDate() - 1);
	var c_value = escape("") + "; expires=" + exdate.toUTCString();
	document.cookie = c_name + "=" + c_value;
}