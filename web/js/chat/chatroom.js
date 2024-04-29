window.onload = function () {
    const roomNo = document.getElementsByName("roomNo")[0].value;
    const productNo = document.getElementsByName("productNo")[0].value;
    const buyerNo = document.getElementsByName("buyerNo")[0].value;
    const sessionId = document.getElementsByName("sessionID")[0].value;
    console.log(buyerNo);
    // 사용자가 들어오면 웹소켓 객체 생성
    const webSocket = new WebSocket("ws://43.202.67.3/angel/chat.ch");
    let enter_p;
    let myName_p;
    let mymsg_p;
    let yourName_p;
    let yourmsg_p;
    webSocket.onopen = function(event) {
        console.log("Connected to WebSocket server.");
        // 클라이언트의 웹소켓 오픈 시, 기존 대화 리스트 받아오기
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
                            yourName_p.appendChild(document.createTextNode("😊 " + d.writer));
                            yourName_p.setAttribute("class", "your_name");
                            yourmsg_p = document.createElement("p");
                            yourmsg_p.appendChild(document.createTextNode(d.content));
                            yourmsg_p.setAttribute("class", "your_msg");
                            document.getElementById("msg_box").prepend(yourName_p);
                            document.getElementById("msg_box").prepend(yourmsg_p);
                        }
                    });
            }).then(()=>{
                webSocket.send("init_conn&"+sessionId+":"+roomNo);
            }).catch(error=>{
            console.log("error: ",error);
        });
    };
    // 채팅 메시지의 요소를 메시지 영역에 동적 추가
    webSocket.onmessage = function(event) {
        let data = event.data.split('&');
        if(data[1]==="enter"){
            enter_p = document.createElement("p");
            enter_p.appendChild(document.createTextNode(data[0]));
            enter_p.setAttribute("class", "enter");
            document.getElementById("msg_box").prepend(enter_p);
        }else if(data[1]===sessionId+":"+roomNo){
            myName_p = document.createElement("p");
            myName_p.appendChild(document.createTextNode("😊 " + sessionId));
            myName_p.setAttribute("class", "my_name")
            mymsg_p = document.createElement("p");
            mymsg_p.appendChild(document.createTextNode(data[0]));
            mymsg_p.setAttribute("class", "my_msg");
            document.getElementById("msg_box").prepend(myName_p);
            document.getElementById("msg_box").prepend(mymsg_p);
        } else {
            yourName_p = document.createElement("p");
            yourName_p.appendChild(document.createTextNode("😊 "+data[1].split(":")[0]));
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
    // 엔터 버튼을 누르거나, 전송 버튼을 클릭 시 채팅 메시지 서버로 전송
    const chatmsg = document.getElementsByName("content")[0];
    chatmsg.onkeydown=function (e) {
        if(chatmsg.value !==''){
            if(e.key==='Enter'){
                webSocket.send(chatmsg.value);
                chatmsg.value = '';
            }
        }
    };
    document.getElementById("chatBtn").onclick=function () {
        if(chatmsg.value !=='') {
            webSocket.send(chatmsg.value);
            chatmsg.value = '';
        }
    };
    // 거래확정 버튼 누르면 한번 더 알림
    document.getElementById("contract").onclick=function () {
        if (window.confirm("현재 구매자와 거래를 확정하시겠습니까? 거래는 취소할 수 없습니다.")) {
            window.open("contract.do?productNo="+productNo+"&buyerNo="+buyerNo);
        }
    }
}


