const uno = document.getElementsByName('uno')[0].value;

document.getElementById("delete_btn").onclick=function () {
    if (window.confirm("정말로 삭제할까요?")) {
        window.open("deluser.do?uno="+uno);
    }
}