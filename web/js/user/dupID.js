window.onload=function() {
    document.getElementById('check_btn').onclick=function() {
        const checkID = document.getElementById("userID").value;

        fetch("dupidcheck?checkID="+checkID
        ,{
            method: 'GET'
            , headers: {
                "Content-Type": "application/x-www-form-urlencoded"
                , "Accept": "text/plain"
            }
        }).then(res => {
            return res.text()
        }).then(data => {
            const err_msg = document.getElementById('error_msg');

            err_msg.append(data);
        }).catch(error => {
            console.log(error + "fetch error");
        })
    };
}