let bno='';
let pno='';
const init_data=function(buyerNo, productNo) {
    bno=buyerNo;
    pno=productNo;
}

fetch("chatlist?pno="+pno+"&bno="+bno,{
    method : "get"
    ,headers : {"Content-Type" : "application/x-www-form-urlencoded" ,"Accept" : "text/json"}
}).then(res=>res.json())
    .then((data)=>{
        data.forEach((d,i)=>{
            let enter_p;
            let enter_txt;
            let buyerName_p;
            let buyermsg_p;
            let sellerName_p;
            let sellermsg_p
            if(i==0){
                enter_p = document.createElement("p");
                enter_txt = document.createTextNode(d.content);
            } else {
                buyerName_p = document.createElement("p");
                buyermsg_p = document.createElement("p");
                sellerName_p = document.createElement("p");
                sellermsg_p = document.createElement("p");
            }

        })

    })