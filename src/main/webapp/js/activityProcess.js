let activityList;
$(document).ready(function () {
    if (typeof sessionStorage.postcode == 'undefined') {
        $(location).attr("href", "index.html");
    }
    $.get("active/activity/allActivities?postcodegiven=" + sessionStorage.postcode, function (data, status) {
        activityList = data;
        sessionStorage.activities = data;
        document.getElementById("headingPostcode").innerHTML = "Activities close to " + sessionStorage.postcode;

        for (let i = 0; i < data.length; i++) {
            let breakElem = document.createElement("BR");

            let main = document.createElement("div");
            main.setAttribute("class", "w3-card w3-round w3-white w3-animate-bottom");
            let contain = document.createElement("div");
            contain.setAttribute("class", "w3-container w3-padding");
            let header = document.createElement("h3");
            header.setAttribute("class", "w3-left");

            let headerText = document.createTextNode(activityList[i].title);
            header.appendChild(headerText);
            contain.appendChild(header);
            let numbers = document.createElement("div");
            numbers.setAttribute("class", "w3-topright");

            let price = document.createElement("p");
            price.appendChild(document.createTextNode("£" + activityList[i].price));
            numbers.appendChild(price);
            price.setAttribute("class", "w3-right w3-margin-right");

            let distance = document.createElement("p");
            distance.appendChild(document.createTextNode(activityList[i].distance + " miles away"));
            numbers.appendChild(distance);
            distance.setAttribute("class", "w3-right w3-margin-right");

            let age = document.createElement("p");
            age.appendChild(document.createTextNode("Minimum age: " + activityList[i].age));
            numbers.appendChild(age);
            age.setAttribute("class", "w3-right w3-margin-right");

            contain.appendChild(numbers);
            let line = document.createElement("hr");

            let description = document.createElement("p");
            description.appendChild(document.createTextNode(activityList[i].description));
            contain.appendChild(description);
            contain.appendChild(line);

            let button = document.createElement("button");
            button.setAttribute("value", activityList[i].activityID);
            button.appendChild(document.createTextNode("More details"));
            button.setAttribute("class", "w3-button");
            button.setAttribute("onclick", "goToDetails(this.value);");
            contain.appendChild(button);

            main.appendChild(contain);

            document.getElementById("mainBox").appendChild(main);
            document.getElementById("mainBox").appendChild(breakElem);
            document.getElementById("mainBox").appendChild(breakElem);
        }
    });
});

function goToDetails(value){
    sessionStorage.tempActivity = value;
    $(location).attr("href", "details.html");
}