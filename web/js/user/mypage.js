window.onload=function () {
    const uno = document.getElementsByName("uno")[0];
    console.log(uno.value);
    fetch("findlist.my?uno="+uno.value+"&find=info",{
        method : "get"
        ,headers : {"Content-Type" : "application/x-www-form-urlencoded" ,"Accept" : "text/json"}
    }).then(res=>res.json())
        .then((data)=>{
            console.log(data);
            let h3 = document.createElement("h3");
            let userName = document.createTextNode(data.userName+" 님 안녕하세요 😍");
            h3.appendChild(userName)
            document.getElementById("headImg").after(h3);
            let span1 = document.createElement("span");
            let sellCount = document.createTextNode(data.sellCount);
            span1.setAttribute("class","statistic_content");
            span1.appendChild(sellCount)
            document.getElementById("sellCount").after(span1);
            let span2 = document.createElement("span");
            let hotCtg = document.createTextNode(data.hotCtg);
            span2.setAttribute("class","statistic_content");
            span2.appendChild(hotCtg)
            document.getElementById("hotCtg").after(span2);
        }).catch(error=>{
        console.log("error: ",error);
    });

    fetch("findlist.my?uno="+uno.value+"&find=chat",{
        method : "get"
        ,headers : {"Content-Type" : "application/x-www-form-urlencoded" ,"Accept" : "text/json"}
    }).then(res=>res.json())
        .then((data)=>{
            console.log(data);
        }).catch(error=>{
        console.log("error: ",error);
    });

    fetch("findlist.my?uno="+uno.value+"&find=order",{
        method : "get"
        ,headers : {"Content-Type" : "application/x-www-form-urlencoded" ,"Accept" : "text/json"}
    }).then(res=>res.json())
        .then((data)=>{
            console.log(data);
        }).catch(error=>{
        console.log("error: ",error);
    });

    fetch("findlist.my?uno="+uno.value+"&find=sell",{
        method : "get"
        ,headers : {"Content-Type" : "application/x-www-form-urlencoded" ,"Accept" : "text/json"}
    }).then(res=>res.json())
        .then((data)=>{
            console.log(data);
            data.forEach(d=>{
                // ul, li 로 추가 하기
            });
        }).catch(error=>{
        console.log("error: ",error);
    });
}

