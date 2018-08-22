// let bnkcook;
//
// function setCookie(id) {
//     document.cookie = "userID=" + id;
//     bnkcook = document.cookie;
//     console.log(bnkcook);
//     console.log(document.cookie);
// }
//
// console.log(setCookie(123));

function register() {
    setTimeout(function () {
        window.location.replace("login.html");
    }, 400);
}

$(document).ready(function () {
    let id;
    $("#loginForm").submit(function (event) {
        event.preventDefault();
        $.post("active/process/login",
            $(this).serialize(),
            function (data, status) {
                id = JSON.parse(data);
                if (id.length == 0) {
                    $("#loginResponse").html("Email or password incorrect");
                } else {
                    if (id[1] == "true") {
                        sessionStorage.userid = id[0];
                        sessionStorage.client = id[1];
                        $(location).attr("href", "client.html");
                    } else {
                        sessionStorage.userid = id[0];
                        sessionStorage.client = id[1];
                        $(location).attr("href", "user.html");
                    }
                }
            });
    })
});

$(document).ready(function () {
    $("#addActivity").submit(function (event) {
        event.preventDefault();
        $('<input>').attr({
            type: 'hidden',
            name: 'id',
            value: sessionStorage.userid
        }).appendTo(this);
        $.post("active/activity/addactivity",
            $(this).serialize())
    })
});

function logout() {
    sessionStorage.clear();
    // window.location.replace("index.html");
}
