function logout() {
    sessionStorage.clear();
}

$(document).ready(function () {
    console.log("USER ID: " + sessionStorage.userid);
    if (typeof sessionStorage.userid == 'undefined') {
        $(".mainUser").hide();
    } else {
        $(".mainButton").hide();
    }
});