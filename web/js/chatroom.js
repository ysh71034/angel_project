let bno='';
let pno='';
const init_data=function(buyerNo, productNo) {
    bno=buyerNo;
    pno=productNo;
    fetch("chatlist?pno="+pno+"&bno="+bno,{
        method : "get"
        ,headers : {"Content-Type" : "application/x-www-form-urlencoded" ,"Accept" : "text/json"}
    }).then(res=>res.json())
        .then((data)=>{
            let buyer = data[0].writer;
            data.forEach((d,i)=>{
                let enter_p;
                let buyerName_p;
                let buyermsg_p;
                let sellerName_p;
                let sellermsg_p
                if(i==0){
                    enter_p = document.createElement("p");
                    enter_p.appendChild(document.createTextNode(d.content));
                    enter_p.setAttribute("class","enter");
                    document.getElementById("msg_box").appendChild(enter_p);
                } else if(d.writer == buyer) {
                    buyerName_p = document.createElement("p");
                    buyerName_p.appendChild(document.createTextNode("😊 "+d.writer));
                    buyerName_p.setAttribute("class","buyer_name")
                    buyermsg_p = document.createElement("p");
                    buyermsg_p.appendChild(document.createTextNode(d.content));
                    buyermsg_p.setAttribute("class","buyer_msg");
                    document.getElementById("msg_box").appendChild(buyerName_p);
                    document.getElementById("msg_box").appendChild(buyermsg_p);
                } else {
                    sellerName_p = document.createElement("p");
                    sellerName_p.appendChild(document.createTextNode("😊 판매자"));
                    sellerName_p.setAttribute("class","seller_name");
                    sellermsg_p = document.createElement("p");
                    sellermsg_p.appendChild(document.createTextNode(d.content));
                    sellermsg_p.setAttribute("class","seller_msg");
                    document.getElementById("msg_box").appendChild(sellerName_p);
                    document.getElementById("msg_box").appendChild(sellermsg_p);
                }
            })
        }).catch(error=>{
        console.log("error: ",error);
    }).finally(()=>{
        console.log("end");
    });
}
