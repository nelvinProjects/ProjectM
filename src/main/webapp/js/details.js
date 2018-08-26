$(document).ready(function () {
    // console.log(sessionStorage.tempActivity);
    var cid;
    $.get("active/activity/specificActivities?idchosen=" + sessionStorage.tempActivity, function (data, status) {
        // for (var i =0; i< data.length;i++){
        //     if (data[i].activityID == sessionStorage.tempActivity){
        console.log(data);
        cid = data.clientID;
        sessionStorage.tempPostcode = data.postcode;
        $("#detailsTitle").html(data.title);
        $("#directioTo").html("Direction to " + data.postcode);
        $("#detailsDate").html(data.date);
        $("#detailsDistance").html(data.distance);
        $("#detailsPrice").html(data.price);
        $("#detailsTime").html(data.time);
        $("#detailsAge").html(data.age);
        $("#detailsDescription").html(data.description);
        $("#detailsAddress").html(data.streetAddress1 + " "+ data.streetAddress2 + " "+ data.city+
        " "+ data.postcode);

        // break;
        // }
        // }
    });
    $.get("active/process/clientName?clientid=" + cid, function (data, status) {
        console.log(data);
        $("#detailsCompany").html(data.clientID);
    });

});

//TODO set id to buy button