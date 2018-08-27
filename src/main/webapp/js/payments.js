let price = 0.01;
paypal.Button.render({
    env: 'sandbox',
    client: {
        sandbox: 'AXpnQUmGpMnSv4AEIgNJrBr4W5I7hGWRjsSY0PZDr1SPqup1jY46_JA-uMSAO_VZEFGVIw6yBxAuy7a_'
    },
    payment: function (data, actions) {
        console.log(price);
        return actions.payment.create({
            transactions: [{
                amount: {
                    total: price,
                    currency: 'GBP'
                }
            }]
        });
    },
    onAuthorize: function (data, actions) {
        return actions.payment.execute()
            .then(function () {
                console.log($("#buyThis").serialize());
                $.post("active/buyActivity", $("#buyThis").serialize()
                    , function (data, status) {
                        $(location).attr("href", "complete.html");
                    });
            });
    }
}, '#paypal-button');

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
        $("#payDate").html("On: " + data.date.day + "/" + data.date.month + "/" + data.date.year);
        if (data.time.minute == 0) {
            $("#payTime").html("At: " + data.time.hour + ":00");
        } else {
            $("#payTime").html("At: " + data.time.hour + ":" + data.time.minute);
        }
        $("#payAge").html("Age: " + data.age + "+");
        $("#payUserID").val(sessionStorage.userid);
        $("#payActivityID").val(data.activityID);
    })
});
