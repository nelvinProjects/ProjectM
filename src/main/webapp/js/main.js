$(document).ready(function () {
    $("#finder").submit(function (event) {
        sessionStorage.postcode = $("#indexPostcodeInput").val();
        window.location.replace("activity.html");
    })
});


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
                        if (sessionStorage.redirectLogin) {
                            $(location).attr("href", "details.html");
                        } else {
                            $(location).attr("href", "user.html");
                        }

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
            $(this).serialize());

        setTimeout(function () {
            $(location).attr("href", "client.html");
        }, 400);
    });
});

