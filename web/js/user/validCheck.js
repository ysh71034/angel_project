// 입력받은 비밀번호
let userPwd = document.querySelector('#password');
// 입력받은 비밀번호 확인
let pwdCheck = document.querySelector('#pwdCheck');
// 입력받은 상세주소
let detailAddr = document.querySelector('#detailAddress');

// 비밀번호 불일치 메세지
let pwdMatch = document.querySelector('.pwd_mismatch');
// 비밀번호 형식 메세지
let pwdType = document.querySelector('.pwd_type');
// 상세주소 입력 메세지
let AddrCheck = document.querySelector('.detail_addr');

const strongPwd = function (str) {
    return /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/.test(str);
}

const isMatch = function (password, pwdCheck) {
    return password === pwdCheck;
}

userPwd.onkeyup = function (){
    if(userPwd.value.length !== 0){
        if(strongPwd(userPwd.value)){
            pwdType.classList.add('hide');
        }else{
            pwdType.classList.remove('hide');
        }
    }else{
        pwdType.classList.add('hide');
    }
}

pwdCheck.onkeyup = function (){
    if(pwdCheck.value.length !== 0){
        if(isMatch(userPwd.value, pwdCheck.value)){
            pwdMatch.classList.add('hide');
        }else{
            pwdMatch.classList.remove('hide');
        }
    }else{
        pwdMatch.classList.add('hide');
    }
}

detailAddr.onkeyup = function(){
    if(detailAddr.value.length !== 0){
        if(detailAddr.value.length > 1){
            AddrCheck.classList.add('hide');
        }else{
            AddrCheck.classList.remove('hide');
        }
    }else{
        AddrCheck.classList.add('hide');
    }
}