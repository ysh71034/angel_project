<div>
<h1><img src="https://github.com/ysh71034/angel_project/assets/113234712/6c620d0f-b902-49a7-ba2d-b1589bdd95ff" width=100> 천사Mall</h1>
<h3> 온라인 중고 직거래 서비스 (2024.04.17 ~2024.04-26) </h3>
</div>


  
<h2> 프로젝트 팀원 </h2>

|김혜연 (34%)|양세현 (33%)|황현준 (33%)| 
|:---:|:---:|:---:|
|<img src="https://avatars.githubusercontent.com/loveyrooney" width="100" > <p>채팅,마이페이지</p> |<img src="https://avatars.githubusercontent.com/ysh71034" width="100" > <p>회원,메인페이지</p>|<img src="https://avatars.githubusercontent.com/skd9712" width="100"> <p>상품,판매자정보</p>|
|<a href="https://github.com/loveyrooney"><img src="https://img.shields.io/badge/GitHub-181717?style=plastic&logo=GitHub&logoColor=white"/></a>|<a href="https://github.com/ysh71034"><img src="https://img.shields.io/badge/GitHub-181717?style=plastic&logo=GitHub&logoColor=white"/></a>|<a href="https://github.com/skd9712"><img src="https://img.shields.io/badge/GitHub-181717?style=plastic&logo=GitHub&logoColor=white"/></a>|


<h2> 기술 스택 </h2>
<div>
  <h4> Front End </h4>
<img src="https://img.shields.io/badge/-jsp-orange"/>

<img src="https://img.shields.io/badge/-html-orange"/>

<img src="https://img.shields.io/badge/-css-blue"/>

<img src="https://img.shields.io/badge/-javascript-yellow"/> 
</div>
<div>
   <h4> Back End </h4>
<img src="https://img.shields.io/badge/java-339933?style=plastic&logo=java&logoColor=white"/>

<img src="https://img.shields.io/badge/-jdbc-339933"/>

<img src="https://img.shields.io/badge/-MariaDB-darkblue"/>

<img src="https://img.shields.io/badge/-WebSocket-darkviolet"/>

<img src="https://img.shields.io/badge/-kakao주소API-yellow"/>

<h4> Version & Build </h4>
open JDK 11</br>
apache tomcat 9.89</br>
<p>AWS EC2(ubuntu) 및 RDS 사용, war 파일 톰캣 서버에 띄워 배포</p>
</div>

<h2>프로젝트 주요이슈</h2>
<h4>채팅 관련</h4>
<h6>⚠️문제 상황</h6>
<ol>
  <li>판매자와 구매자의 role 구분이 없어서 채팅방 입장시 접속자 식별의 복잡성</li>
  <li>일대일 채팅을 구현하기 위한 WebSocket 서버 세션 식별의 문제</li>
</ol>

<h6>📌해결 과정</h6>
<ol>
    <p>최초 시도시에는 chatroom 테이블 없이 chat 테이블만 가지고 채팅방 입장 시 기본 메시지 하나 추가하는 방식이었음<br>
    => chatroom 테이블을 만들어 채팅방 입장 시 필요한 정보 일괄 관리</p>
  <li>
    <ul>
      <li>⚙️접근 uri을 다르게 설정 : 구매자의 경우 파라미터에 상품id만, 판매자는 구매자id 까지 포함되도록 함</li>
      <li>판매자는 구매자의 최초 접속 이후에 채팅방에 접근할 수 있도록 함</li>
    </ul>
  </li>
  <br>
  <li>
    <ul>
      <li>WebSocket 세션과 HttpServlet 세션은 독립적이고, servletContext 를 웹소켓 서버 내에서 사용할 수 없다.</li>
      <li>웹소켓 서버 내에 접속 세션을 관리할 맵 형성</li>
      <li>웹소켓 접속 시, 세션 식별 정보를 맵에 등록하고, 메시지를 식별정보와 일치하는 세션에게만 전송</li>
    </ul>
  </li>
</ol>

<h4>상품 관련</h4> 
<h6>⚠️문제 상황</h6>
<ol>
  <li>상품 수정시 기존 이미지가 삭제되지 않는 문제</li>
  <li>문제2</li>
</ol>
<h6>📌해결 과정</h6>
<ol>
  <li>
    <ul>
      <li>파일 업로드 경로를 찾아 올바른 위치로 수정 </li>
      <li>문제 1 - 해결과정2</li>
    </ul>
  </li>
  <br>
  <li>
    <ul>
      <li>문제 2 - 해결과정1</li>
      <li>문제 2 - 해결과정2</li>
    </ul>
  </li>
</ol>

<h2>프로젝트 주요기능</h2>

|기능| 설명|
|:---|:---|
|메인페이지|1. 구매가능 상품 조회<br>2. 상단 바에서 판매등록, 로그인/회원가입/마이페이지 접근|
|상품정보|1. 상품 상세정보, 판매자의 다른 상품 조회<br>2.현재 접속자의 role 여부에 따라 채팅하기/채팅방목록 버튼 표시, 상품수정 버튼 표시 <br>[구매자] <br>3. 채팅하기 - 판매자와의 채팅방 바로 입장 <br> [판매자] <br>4. 채팅방목록 - 해당 상품의 채팅방 페이지로 이동한 후 개별 구매자와의 채팅방 입장 가능 <br> 상품수정 - 상품 정보 및 이미지 수정, 삭제 가능|
|회원가입/로그인|1. 이메일 중복 확인<br>2. 이메일과 비밀번호 유효성 검사<br> 3. 로그인 회원은 role 구분 명시적으로 하지 않고 모두 될 수 있음 <br>4. 로그인 정보 확인 로직은 필터 단에서 체크|
|채팅|1. 로그인 회원, 해당 상품 판매자 입장가능한 일대일 채팅방 <br>2. 상품 간단정보 조회 및 이전 채팅내역 조회 가능 <br>3. 판매자는 거래확정 가능|
|마이페이지| 1. 회원정보 수정 및 탈퇴 접근 <br> 2. 구매 내역 / 판매 내역 / 현재 대화중 채팅방 조회 및 해당 페이지 접근|
|판매등록|1. 메인 상단 바에서 판매등록 접근 <br>|
|판매자정보| 1. 판매자 인적 정보 조회 <br> 2. 판매자의 현재 판매중인 다른 상품 조회 및 해당 페이지 접근|

<h2>ErDiagram</h2>
<img style="width: 600px" src="https://github.com/ysh71034/angel_project/assets/113234712/c1032fa4-98aa-463f-ace5-e801c1569e16">

<h2>UseCase Diagram</h2>
<img style="width: 500px" src="https://github.com/ysh71034/angel_project/assets/113234712/a45a8d8c-fbb9-4fe1-9398-e56fd0b15ddc">
