
 /**
 * 位置情報を取得して現在地からの最寄り駅を返す機能
 * ・jsp側での指定
 *      駅ID…id="stationId"
 *      駅名…id="station"
 *  駅IDはinput type="hidden"で作成、駅名はvarとかで囲った普通のテキストにしてください！！！
 *  駅IDの要素の指定方法の例は「change_nearest.jsp」を参考
 *
 */

var position = "";

function success(pos) {
    let crd = pos.coords;
    position = crd.latitude + "," + crd.longitude;
}

function search_position() {
    navigator.geolocation.getCurrentPosition(success);
    let postData = { position: position };

    //非同期通信始めるよ
    $.ajaxSetup({ scriptCharset: 'utf-8' });
    $.ajax({
        //どのサーブレットに送るか
        url: '/syuudeen/UserRegisterServlet',
        //どのメソッドを使用するか
        type: "POST",
        //受け取るデータのタイプ
        dataType: "json",
        //何をサーブレットに飛ばすか（変数を記述）
        data: postData,
        //この下の２行はとりあえず書いてる（書かなくても大丈夫？）
        processDate: false,
        timeStamp: new Date().getTime()
        //非同期通信が成功したときの処理
    }).done(function (data) {
        document.getElementById("station_id").value = data[0];
        document.getElementById("station").innerText = data[1];
    })
        //非同期通信が失敗したときの処理
        .fail(function () {
            //失敗とアラートを出す
            alert("失敗！");
        });
}

/*PWとPWチェックの確認*/

     function pushHideButton() {
        var txtPass1 = document.getElementById("user_pw");
        var txtPass2 = document.getElementById("pw_check");
        var btnEye = document.getElementById("pw_check_button");
        if (txtPass1.type === "text" || txtPass2.type === "text") {
          txtPass1.type = "password";
          txtPass2.type = "password";
          btnEye.className = "fa fa-eye-slash";
        } else {
          txtPass1.type = "text";
          txtPass2.type = "text";
          btnEye.className = "fa fa-eye";
        }
      }
