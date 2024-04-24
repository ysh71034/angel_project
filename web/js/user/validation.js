const validateCheck = function (){
    const userID = document.getElementById("userID").value;
    location.href = 'validate.do?uid='+userID;
    console.log(userID);
}
// let dupCheck = '';
// const user_data = function (checkID){
//     dupCheck = checkID;
//     console.log(dupCheck);
// }


// const validation = function () {
//
// }

// window.onload=function (){
//     fetch("join?uid="+checkID
//         ,{
//             method: 'GET'
//             , headers: {
//                 "Content-Type": "application/x-www-form-urlencoded"
//                 , "Accept": "text/json"
//             }
//         }).then(res => {
//             return res.json()
//     }
//     ).then(data => {
//         data.forEach(item => {
//             let span = document.createElement('span');
//
//             document.getElementById('result').appendChild(span);
//         })
//     }).catch(error => {
//         console.log(error + "fetch error");
//     })
// }






