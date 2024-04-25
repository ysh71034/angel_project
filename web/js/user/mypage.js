fetch("findlist.my?",{
    method : "get"
    ,headers : {"Content-Type" : "application/x-www-form-urlencoded" ,"Accept" : "text/json"}
}).then(res=>res.json())
    .then((data)=>{
        console.log(data);
    }).catch(error=>{
    console.log("error: ",error);
});
