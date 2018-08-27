let setID;
$(document).ready(function () {
    $.get("active/activity/specificActivities?idchosen=" + sessionStorage.tempActivity +
        "&postcode=" + sessionStorage.postcode, function (data, status) {
        console.log(data);
        setID = data.activityID;
        sessionStorage.tempPostcode = data.postcode;
        $("#detailsTitle").html(data.title);
        $("#directioTo").html("Direction to " + data.postcode);
        $("#detailsDate").html(" " + data.date.day + "/" + data.date.month + "/" + data.date.year);
        $("#detailsDistance").html(" " + data.distance + " miles");
        $("#detailsPrice").html("£" + data.price);
        if (data.time.minute == 0) {
            $("#detailsTime").html(data.time.hour + ":00");
        } else {
            $("#detailsTime").html(data.time.hour + ":" + data.time.minute);
        }
        $("#detailsAge").html(data.age + "+");
        $("#detailsQuantity").html("<b><i>Remaining: </i>" + data.quantity + "</b>");
        $("#detailsDescription").html(data.description);
        $("#detailsAddress").html(" " + data.streetAddress1 + " " + data.streetAddress2 + ", " + data.city +
            ", " + data.postcode);

        $.get("active/process/clientName?clientid=" + data.clientID, function (data, status) {
            $("#detailsCompany").html(data[1]);
        });
    });

    if (typeof sessionStorage.userid == 'undefined') {
        let button = document.createElement("button");
        button.appendChild(document.createTextNode("Login to buy ticket"));
        button.setAttribute("value", true);
        button.setAttribute("class", "w3-button");
        button.setAttribute("onclick", "loginAndBack(this.value);");
        $("#detailsBuy").html(button);
    } else {
        let button = document.createElement("button");
        button.appendChild(document.createTextNode("Buy"));
        button.setAttribute("value", setID);
        button.setAttribute("class", "w3-button");
        button.setAttribute("onclick", "buyThisActivity(this.value);");
        $("#detailsBuy").html(button);
    }

});

function loginAndBack(value) {
    sessionStorage.redirectLogin = value;
    $(location).attr("href", "login.html");
}

function buyThisActivity(value) {
    // sessionStorage.buyAct = value;
    $(location).attr("href", "purchase.html");
}