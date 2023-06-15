function notice(){
  if(document.getElementByName("alert_check").checked){
    var check = 1;
  }
  else{
    var check = 0;
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
	// 今回は上の<div id="test"></div>の中に返ってきた文字列を入れる
	document.getElementById("test").innerText=data[0].name;
	})
	//非同期通信が失敗したときの処理
	.fail(function() {
	//失敗とアラートを出す
	alert("失敗！");
	 });
}