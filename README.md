<div>
<h1><img src="https://github.com/ysh71034/angel_project/assets/113234712/6c620d0f-b902-49a7-ba2d-b1589bdd95ff" width=100> 천사Mall</h1>
<h3> 교사 대상 온라인 중고 거래 서비스 (2024.04.17 ~2024.04.26) </h3>
  <ul>
    <li>교육에 관련된 중고 물품을 카테고리 별로 분류하여 쉽고 빠른 상품조회</li>
    <li>일대일 채팅을 통한 직거래 방식</li>
</ul>
</div>


  
<h2> 프로젝트 팀원 </h2>

|김혜연 (34%)|양세현 (33%)|황현준 (33%)| 
|:---:|:---:|:---:|
|<img src="https://avatars.githubusercontent.com/loveyrooney" width="100" > <p>채팅,마이페이지</p> 요구사항 분석 |<img src="https://avatars.githubusercontent.com/ysh71034" width="100" > <p>회원,메인페이지</p>DB관리|<img src="https://avatars.githubusercontent.com/skd9712" width="100"> <p>상품,판매자정보</p>UML관리|
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
<p>⚠️요구사항 관련하여 발생한 문제</p>
<ul>
  <li>사용자는 판매자도 될 수 있고, 구매자도 될 수 있도록 가입 시 역할 구분 없음</li>
  <li>채팅 방에는 이전 채팅 메시지가 보관되어 있어야 함</li>
  <li>⚠️역할 구분이 없어서 채팅 방 입장 시 <strong>접속자 식별 과정이 복잡</strong>해짐</li>
</ul>
<br>
<p>📌 chatroom 테이블을 만들어 채팅 방 입장 시 필요한 정보 일괄 관리</p>
<ul>
  <li>URL parameter 역할에 따라 구별 ( 구매자 - 상품id  /  판매자 - 상품id, 구매자 id )</li>
  <li>구매자의 최초 접속 시, chatroom 테이블 정보 생성</li>
  <li>판매자는 구매자의 최초 접속 이후에 채팅 방에 접근할 수 있도록 구현</li>
</ul>
<br>
<p>⚠️ 기술의 특징 관련하여 발생한 문제</p>
<ul>
  <li>WebSocket 서버는 HttpServletContext 와 독립적임</li>
  <li>⚠️ 일대일 채팅을 구현하려면 <strong>WebSocket session 식별</strong>이 필요</li>
</ul>
<br>
<p>📌 WebSocket 서버 내에 접속자 관리 Map 생성하여 메시지 타겟팅</p>
<ul>
  <li>ConcurrentHashMap<Session,String> 구조의 접속자 Map 생성</li>
  <li>채팅 방 접속 시, 해당 session 및 chatroom 테이블 정보를 Map 에 등록</li>
  <li>메시지는 Map 내의 session 중 chatroom 테이블 정보가 일치하는 대상에게만 전송</li>
</ul>

<h4>상품 관련</h4> 
<p>⚠️ 구현 관련하여 발생한 문제</p>
<ul>
  <li>상품 정보 수정 시 기존의 이미지가 삭제되지 않음</li>
</ul>
<p>📌 업로드 경로의 올바른 설정</p>
    

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
<img style="width: 600px" src="https://github.com/ysh71034/angel_project/assets/113234712/059b8771-44d2-4fb1-a222-1d36eb727a81">

<h2>UseCase Diagram</h2>
<img style="width: 500px" src="https://github.com/ysh71034/angel_project/assets/113234712/a45a8d8c-fbb9-4fe1-9398-e56fd0b15ddc">
