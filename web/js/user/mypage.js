window.onload=function () {
    const uno = document.getElementsByName("uno")[0];
    console.log(uno.value);
    // 유저 정보 조회
    fetch("findlist.my?uno="+uno.value+"&find=info",{
        method : "get"
        ,headers : {"Content-Type" : "application/x-www-form-urlencoded" ,"Accept" : "text/json"}
    }).then(res=>res.json())
        .then((data)=>{
            console.log("info",data);
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
    // 구매 진행 중인 채팅방
    fetch("findlist.my?uno="+uno.value+"&find=chat",{
        method : "get"
        ,headers : {"Content-Type" : "application/x-www-form-urlencoded" ,"Accept" : "text/json"}
    }).then(res=>res.json())
        .then((data)=>{
            console.log("chat",data);
            if(data.length>0){
                data.forEach(d=>{
                    let rno_li = document.createElement("li");
                    let rno_txt = document.createTextNode(d.roomNo);
                    rno_li.appendChild(rno_txt);
                    let pn_li = document.createElement("li");
                    let pn_a = document.createElement("a");
                    pn_a.setAttribute("href","enterchat.do?productNo="+d.productNo);
                    let pn_txt = document.createTextNode(d.productName);
                    pn_a.appendChild(pn_txt);
                    pn_li.appendChild(pn_a);
                    document.getElementsByClassName("roomNo")[0].appendChild(rno_li);
                    document.getElementsByClassName("roomName")[0].appendChild(pn_li);
                });
            }
        }).catch(error=>{
        console.log("error: ",error);
    });
    // 나의 구매 내역
    fetch("findlist.my?uno="+uno.value+"&find=order",{
        method : "get"
        ,headers : {"Content-Type" : "application/x-www-form-urlencoded" ,"Accept" : "text/json"}
    }).then(res=>res.json())
        .then((data)=>{
            console.log("order",data);
            if(data.length>0){
                data.forEach(d=>{
                    let ono_li = document.createElement("li");
                    let no_txt = document.createTextNode(d.orderNo);
                    ono_li.appendChild(no_txt);
                    document.getElementsByClassName("orderNo")[0].appendChild(ono_li);
                    let oprod_li = document.createElement("li");
                    let oprod_a = document.createElement("a");
                    oprod_a.setAttribute("href","enterchat.do?productNo="+d.productNo);
                    let prod_txt = document.createTextNode(d.productName);
                    oprod_a.appendChild(prod_txt);
                    oprod_li.appendChild(oprod_a);
                    document.getElementsByClassName("orderprod")[0].appendChild(oprod_li);
                    let oseller_li = document.createElement("li");
                    let seller_txt = document.createTextNode(d.sellerName);
                    oseller_li.appendChild(seller_txt);
                    document.getElementsByClassName("orderseller")[0].appendChild(oseller_li);
                    let odate_li = document.createElement("li");
                    let date_txt = document.createTextNode(d.orderDate);
                    odate_li.appendChild(date_txt);
                    document.getElementsByClassName("orderdate")[0].appendChild(odate_li);
                });
            }
        }).catch(error=>{
        console.log("error: ",error);
    });
    // 나의 판매 상품
    fetch("findlist.my?uno="+uno.value+"&find=currsell",{
        method : "get"
        ,headers : {"Content-Type" : "application/x-www-form-urlencoded" ,"Accept" : "text/json"}
    }).then(res=>res.json())
        .then((data)=>{
            console.log("sell",data);
            if(data.length>0){
                data.forEach(d=>{
                    let sell_li = document.createElement("li");
                    let sell_a = document.createElement("a");
                    sell_a.setAttribute("href","detailprod.do?productNo="+d.productNo);
                    let sell_img = document.createElement("img");
                    sell_img.setAttribute("src","upload/"+d.imgpath);
                    sell_img.setAttribute("alt","my_product_img");
                    sell_a.appendChild(sell_img);
                    let sell_title = document.createElement("span");
                    let title_txt = document.createTextNode(d.pname);
                    sell_title.appendChild(title_txt);
                    sell_li.append(sell_a,sell_title);
                    document.getElementsByClassName("sellitem")[0].appendChild(sell_li);
                });
            }
        }).catch(error=>{
        console.log("error: ",error);
    });
    // 나의 지난 판매상품
    fetch("findlist.my?uno="+uno.value+"&find=pastsell",{
        method : "get"
        ,headers : {"Content-Type" : "application/x-www-form-urlencoded" ,"Accept" : "text/json"}
    }).then(res=>res.json())
        .then((data)=>{
            console.log("past",data);
            if(data.length>0){
                data.forEach(d=>{
                    let past_li = document.createElement("li");
                    let past_a = document.createElement("a");
                    past_a.setAttribute("href","detailprod.do?productNo="+d.productNo);
                    let past_img = document.createElement("img");
                    past_img.setAttribute("src","upload/"+d.imgpath);
                    past_img.setAttribute("alt","my_product_img");
                    past_a.appendChild(past_img);
                    let past_title = document.createElement("span");
                    let past_txt = document.createTextNode(d.pname);
                    past_title.appendChild(past_txt);
                    past_li.append(past_a,past_title);
                    document.getElementsByClassName("pastitem")[0].appendChild(past_li);
                });
            }
        }).catch(error=>{
        console.log("error: ",error);
    });
}

