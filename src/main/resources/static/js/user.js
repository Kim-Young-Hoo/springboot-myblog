let index = {
    init: function() {
        $("#btn-save").on("click", () => {
            this.save()
        });
        $("#btn-login").on("click", () => {
            this.login()
        });
    },

    save: function () {
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val(),
        };
        // console.log(data)

        $.ajax({
            type: "POST",
            url: "/blog/api/user",
            data: JSON.stringify(data), // http body
            contentType: "application/json; charset=utf-8", // body data의 타입
            dataType: "json" // 응답 데이터의 타입
        }).done(function(resp){
            console.log(resp)
            alert("회원가입이 완료 되었습니다.")
            location.href = "/blog"
        }).fail(function(error){
            alert(JSON.stringify(error))
        }); // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여  insert 요청
    },

    login: function () {
        let data = {
            username: $("#username").val(),
            password: $("#password").val()
        };
        console.log(data)

        $.ajax({
            type: "POST",
            url: "/blog/api/user/login",
            data: JSON.stringify(data), // http body
            contentType: "application/json; charset=utf-8", // body data의 타입
            dataType: "json" // 응답 데이터의 타입
        }).done(function(resp){
            console.log(resp)
            alert("로그인이 완료 되었습니다.")
            location.href = "/blog"
        }).fail(function(error){
            alert(JSON.stringify(error))
        }); // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여  insert 요청
    }
}

index.init();