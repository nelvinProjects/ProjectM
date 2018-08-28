let tempHold;
$(document).ready(function () {
    $.get("active/process/userorders?clientid=" + sessionStorage.userid,
        function (data, status) {
            tempHold = JSON.parse(data);
            if(tempHold.length == 0){
                $("#ordersHere").html("<h2><b>No orders</b></h2>");
            }else{
                let table = document.createElement("Table");
                table.setAttribute("class", "w3-table w3-striped w3-centered w3-bordered");
                let headRow = document.createElement("tr");
                let heading1 = document.createElement("th");
                let heading2 = document.createElement("th");
                let heading3 = document.createElement("th");
                let heading4 = document.createElement("th");
                let heading5 = document.createElement("th");
                let heading6 = document.createElement("th");
                heading1.appendChild(document.createTextNode("Title"));
                heading2.appendChild(document.createTextNode("Quantity"));
                heading3.appendChild(document.createTextNode("Date"));
                heading4.appendChild(document.createTextNode("Time"));
                heading5.appendChild(document.createTextNode("Price"));
                heading6.appendChild(document.createTextNode("Address"));
                headRow.appendChild(heading1);
                headRow.appendChild(heading2);
                headRow.appendChild(heading3);
                headRow.appendChild(heading4);
                headRow.appendChild(heading5);
                headRow.appendChild(heading6);
                headRow.setAttribute("class", "w3-deep-purple");
                table.appendChild(headRow);
                for (let i = 0; i < tempHold.length; i++) {
                    let row = document.createElement("tr");
                    let cell1 = document.createElement("td");
                    let cell2 = document.createElement("td");
                    let cell3 = document.createElement("td");
                    let cell4 = document.createElement("td");
                    let cell5 = document.createElement("td");
                    let cell6 = document.createElement("td");
                    cell1.appendChild(document.createTextNode(tempHold[i].title));
                    cell2.appendChild(document.createTextNode("1"));
                    cell3.appendChild(document.createTextNode(tempHold[i].date.day + "/" +
                        tempHold[i].date.month + "/" + tempHold[i].date.year));
                    if (tempHold[i].time.minute == 0) {
                        cell4.appendChild(document.createTextNode(tempHold[i].time.hour + ":00"));
                    } else {
                        cell4.appendChild(document.createTextNode(tempHold[i].time.hour + ":" + tempHold[i].time.minute));
                    }
                    cell5.appendChild(document.createTextNode(tempHold[i].price));
                    cell6.appendChild(document.createTextNode(tempHold[i].streetAddress1 + " " + tempHold[i].streetAddress2 + ", "
                        + tempHold[i].city + ", " + tempHold[i].postcode));
                    row.appendChild(cell1);
                    row.appendChild(cell2);
                    row.appendChild(cell3);
                    row.appendChild(cell4);
                    row.appendChild(cell5);
                    row.appendChild(cell6);
                    table.appendChild(row);
                }
                $("#ordersHere").html(table);
            }
        });
});