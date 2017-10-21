//校验手机号码
var checkIsMobil = function(s) {
    var patrn = /^0?(13[0-9]|15[012356789]|18[0236789]|14[57])[0-9]{8}$/;
    if (!patrn.exec(s)) return false
    return true;
}

var setLocalUser = function(user){
	localStorage.setItem("user", user);
}

var getLocalUser = function(){
	return localStorage.getItem("user");
}