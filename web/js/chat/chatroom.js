window.onload = function () {
    const productNo = document.getElementsByName("productNo")[0].value;
    const buyerNo = document.getElementsByName("buyerNo")[0].value;
    const sessionId = document.getElementsByName("sessionID")[0].value;
    console.log(buyerNo);
    const webSocket = new WebSocket("ws://localhost:8080/angel_project/chat.ch");
    let enter_p;
    let myName_p;
    let mymsg_p;
    let yourName_p;
    let yourmsg_p;
    webSocket.onopen = function(event) {
        console.log("Connected to WebSocket server.");
        fetch("chatlist.ch?pno="+productNo+"&bno="+buyerNo,{
                method : "get"
                ,headers : {"Content-Type" : "application/x-www-form-urlencoded" ,"Accept" : "text/json"}
        }).then(res=>res.json())
            .then((data)=>{
                    data.forEach((d,i)=> {
                        if (i === 0) {
                            enter_p = document.createElement("p");
                            enter_p.appendChild(document.createTextNode(d.content));
                            enter_p.setAttribute("class", "enter");
                            document.getElementById("msg_box").prepend(enter_p);
                        } else if (d.writer === sessionId ) {
                            myName_p = document.createElement("p");
                            myName_p.appendChild(document.createTextNode("😊 " + d.writer));
                            myName_p.setAttribute("class", "my_name")
                            mymsg_p = document.createElement("p");
                            mymsg_p.appendChild(document.createTextNode(d.content));
                            mymsg_p.setAttribute("class", "my_msg");
                            document.getElementById("msg_box").prepend(myName_p);
                            document.getElementById("msg_box").prepend(mymsg_p);
                        } else {
                            yourName_p = document.createElement("p");
                            yourName_p.appendChild(document.createTextNode("😊 판매자"));
                            yourName_p.setAttribute("class", "your_name");
                            yourmsg_p = document.createElement("p");
                            yourmsg_p.appendChild(document.createTextNode(d.content));
                            yourmsg_p.setAttribute("class", "your_msg");
                            document.getElementById("msg_box").prepend(yourName_p);
                            document.getElementById("msg_box").prepend(yourmsg_p);
                        }
                    });
            }).then(()=>{
                webSocket.send("init_conn:"+sessionId+":"+productNo+":"+buyerNo);
            }).catch(error=>{
            console.log("error: ",error);
        });
    };

    webSocket.onmessage = function(event) {
        let data = event.data.split('&');
        if(data[1]==="enter"){
            enter_p = document.createElement("p");
            enter_p.appendChild(document.createTextNode(data[0]));
            enter_p.setAttribute("class", "enter");
            document.getElementById("msg_box").prepend(enter_p);
        }else if(data[1]===sessionId){
            myName_p = document.createElement("p");
            myName_p.appendChild(document.createTextNode("😊 " + sessionId));
            myName_p.setAttribute("class", "my_name")
            mymsg_p = document.createElement("p");
            mymsg_p.appendChild(document.createTextNode(data[0]));
            mymsg_p.setAttribute("class", "my_msg");
            document.getElementById("msg_box").prepend(myName_p);
            document.getElementById("msg_box").prepend(mymsg_p);
        } else{
            yourName_p = document.createElement("p");
            yourName_p.appendChild(document.createTextNode("😊 판매자"));
            yourName_p.setAttribute("class", "your_name");
            yourmsg_p = document.createElement("p");
            yourmsg_p.appendChild(document.createTextNode(data[0]));
            yourmsg_p.setAttribute("class", "your_msg");
            document.getElementById("msg_box").prepend(yourName_p);
            document.getElementById("msg_box").prepend(yourmsg_p);
        }
    };

    webSocket.onclose = function(event) {
        console.log("Connection closed.");
    };

    webSocket.onerror = function(event) {
        console.error("WebSocket error: " + event.data);
    };

    const chatmsg = document.getElementsByName("content")[0];
    chatmsg.onkeydown=function (e) {
        if(chatmsg.value !==''){
            if(e.key==='Enter'){
                webSocket.send(chatmsg.value);
            }
        }
    };
    document.getElementById("chatBtn").onclick=function () {
        webSocket.send(chatmsg.value);
    };
}


