let tempHold;
$(document).ready(function () {
    $.get("active/process/userorders?clientid=" + sessionStorage.userid,
        function (data, status) {
            console.log(data);
            // console.log(JSON.stringify(data));
            tempHold = data;
            for (let i =0; i < data.length; i++){
                console.log(data[i][1].title);
            }
        });
});