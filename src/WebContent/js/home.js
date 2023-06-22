function notice() {
	if (document.getElementById("alert_check").checked) {
		var check = true;
	}
	else {
		var check = false;
	}
	let postData = { check: check }
	//非同期通信始めるよ
	$.ajaxSetup({ scriptCharset: 'utf-8' });
	$.ajax({
		//どのサーブレットに送るか
		//ajaxSampleのところは自分のプロジェクト名に変更する必要あり。
		url: '/syuudeen/HomeServlet',
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
	}).done(function(data) {
		alert("成功1");
	})
		//非同期通信が失敗したときの処理
		.fail(function() {
			//失敗とアラートを出す
			alert("失敗！");
		});
}

var pos = "";

function success(pos) {
	let crd = pos.coords;
	pos = crd.latitude + "," + crd.longitude;
	console.log(pos);

	document.getElementById("hidden_position").value = pos;

	document.search_form.submit();
}

function position() {
	navigator.geolocation.getCurrentPosition(success);
}

// start…現在時刻
// end…終電時刻
// ex: 01:30 - 10:30 = - 09:00
// -09:00 + 24:00 = 15:00
// ex: 01:30 - 00:30 = 01:00

var hours,minutes,seconds;

function countdown() {
	var lastTrain = document.getElementById("hidden_time").value;
	if (lastTrain == "") {
		document.getElementById("timer_text").innerHTML = "ボタンを押して終電検索";
		return;
	}
	else {
		var tmpDate = new Date();
		var endTime = lastTrain.split(":");
		var startDate = new Date(0, 0, 0, tmpDate.getHours(), tmpDate.getMinutes(), tmpDate.getSeconds());
		var lastTrainDate = new Date(0, 0, 0, endTime[0], endTime[1], 0);
		var diff = lastTrainDate.getTime() - startDate.getTime();
		hours = Math.floor(diff / 1000 / 60 / 60);
		minutes = Math.floor(diff / 1000 / 60) % 60;
		seconds = Math.floor(diff / 1000) % 60;

		// If using time pickers with 24 hours format, add the below line get exact hours
		if (hours < 0){
			hours = hours + 24;
		}
		if(minutes < 0){
			minutes += 60;
		}
		if(seconds < 0){
			seconds += 60;
		}

		document.getElementById("timer_text").innerHTML = (hours <= 9 ? "0" : "") + hours + ":" + (minutes <= 9 ? "0" : "") + minutes + ":" + (seconds <= 9 ? "0" : "") + seconds;
		}
}

countdown();
setInterval(countdown, 1000);

//push通知
function push(minutes) {
      Push.create('通知するよ', {
        body:minutes + '分後に終電が出発します。',
        icon: "/syuudeen/img/ham-menu-close.png",
        timeout: 8000, // 通知が消えるタイミング
        vibrate: [200], // モバイル端末でのバイブレーション秒数
        onClick: function () {
          // 通知がクリックされた場合の設定
          window.open('http://localhost:8080/syuudeen/HomeServlet', '_self');
		  this.close();
        }
      })
    }

//終電30分前から10分おきに３回通知
function monitorTime(){
	//30分前
	if(hours==0 && minutes == 30 && seconds == 0){
	push(minutes);
	}
	//20分前
	else if(hours==0 && minutes == 20 && seconds == 0){
	push(minutes);
	}
	//10分前
	else if(hours==0 && minutes == 10 && seconds == 0){
	push(minutes);
	}
}

monitorTime();
setInterval(monitorTime, 1000);
