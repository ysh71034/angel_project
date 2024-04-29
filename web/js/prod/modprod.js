document.getElementById('fileinput').addEventListener('change', function(e) {

    var fileName = '';
    if (this.files && this.files.length > 1) { // 다중 파일 선택을 처리
        fileName = (this.getAttribute('data-multiple-caption') || '').replace('{count}', this.files.length);
    } else {
        fileName = e.target.value.split('\\').pop(); // 파일 경로에서 파일 이름만 추출
    }
    if(fileName){
        document.getElementById('fileLabel').innerHTML=fileName;
        //document.getElementById("currimg").setAttribute("style","display:none;");
    }else {
        document.getElementById('fileLabel').innerHTML="파일 선택";
    }

    // 이미지 미리보기를 위한 div 생성
    var preview = document.getElementById('preview');
    preview.innerHTML = ''; // 기존 미리보기 초기화


    var files = e.target.files;

    for (var i = 0; i < files.length; i++) {
        var file = files[i];

        if (file.type.match('image.*')) { // 파일이 이미지인지 확인
            var reader = new FileReader();
            reader.onload=function (e){
                preview.innerHTML='<img src ="'+e.target.result+'" style="height:200px;"/>';
            }

            reader.readAsDataURL(file);
        }
    }

});

