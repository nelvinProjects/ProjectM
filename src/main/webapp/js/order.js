$(document).ready(function () {
    if (typeof sessionStorage.postcode == 'undefined' || typeof sessionStorage.userid == 'undefined') {
        $(location).attr("href", "index.html");
    }

    $.get("active/activity/specificActivities?idchosen=" + sessionStorage.tempActivity +
        "&postcode=" + sessionStorage.postcode, function (data, status) {
        $("#payTitle").html("Title: " + data.title);
        $("#payAddress").html("Address: " + data.streetAddress1 + " " + data.streetAddress2 + ", " + data.city +
            ", " + data.postcode);
        $("#payPrice").html("£" + data.price);
        if (data.time.minute == 0) {
            $("#payDateTime").html("On: " + data.date.day + "/" + data.date.month + "/" + data.date.year + " At: " + data.time.hour + ":00");
        } else {
            $("#payDateTime").html("On: " + data.date.day + "/" + data.date.month + "/" + data.date.year + " At: " + data.time.hour + ":" + data.time.minute);
        }
        $("#payAge").html("Age: " + data.age + "+");
    });

    $("#backActivities").onclick = "location.href='activity.html';";
});

function moveToActivites() {
    $(location).attr("href", "activity.html");
}