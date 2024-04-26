window.onload = function () {

    fetch("brandnew.bn"
        , {
            method: 'GET'
            , headers: {
                "Content-Type": "application/x-www-form-urlencoded"
                , "Accept": "application/json"
            }
        }).then(res => {
        return res.json()
    }).then(data => {

        const div = document.getElementById('brandNew');
        const ul = document.createElement('ul');
        const list = data;

        if (list.length === 0 || list === 0) {
            const li = document.createElement('li');
            li.textContent = "자료가 없습니다.";
            ul.appendChild(li);
        } else {
            list.forEach(item => {
                const li = document.createElement('li');
                const a = document.createElement('a');
                a.href = "detailprod.do?productNo=" + item.productNo;
                const img = document.createElement('img');
                img.src = "upload/" + item.imagePath;
                img.alt = "최근 올라온 상품 5개";
                a.appendChild(img);
                li.appendChild(a);
                ul.appendChild(li);
            });
        }
        div.appendChild(ul);
    }).catch(error => {
        console.log(error + "fetch error");
    });
};



