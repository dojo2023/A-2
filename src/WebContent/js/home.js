function notice(){
  if(document.getElementById("alert_check").checked){
    var check = true;
  }
  else{
    var check = false;
  }
 let postData = {check:check}
 //非同期通信始めるよ
	$.ajaxSetup({scriptCharset:'utf-8'});
	$.ajax({
	//どのサーブレットに送るか
	//ajaxSampleのところは自分のプロジェクト名に変更する必要あり。
	url: '/syuudeen/HomeServlet',
	//どのメソッドを使用するか
	type:"POST",
	//受け取るデータのタイプ
	dataType:"json",
	//何をサーブレットに飛ばすか（変数を記述）
	data: postData,
	//この下の２行はとりあえず書いてる（書かなくても大丈夫？）
	processDate:false,
	timeStamp: new Date().getTime()
	//非同期通信が成功したときの処理
	}).done(function(data) {
	alert("成功1");
	})
	//非同期通信が失敗したときの処理
	.fail(function() {
	//失敗とアラートを出す
	alert("失敗！");
	 });
}

var position = "";

function success(pos) {
    let crd = pos.coords;
    position = crd.latitude + "," + crd.longitude;
}

function position() {
    navigator.geolocation.getCurrentPosition(success);
    document.getElementById("hidden_position").value=position;

}

