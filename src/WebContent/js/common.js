const Menu = document.querySelector('.menu');
const MenuOpen = document.querySelector('.menu_open');
const MenuClose = document.querySelector('.menu_close');

const MenuList = document.querySelector('.menu_list');
Menu.addEventListener('click', () => {
	MenuOpen.classList.toggle('active');
	MenuClose.classList.toggle('active');
	MenuList.classList.toggle('active');
});

//push通知
function push(minutes) {
	Push.create('通知するよ', {
		body: minutes + '分後に終電が出発します。',
		icon: "/syuudeen/img/ham-menu-close.png",
		timeout: 8000, // 通知が消えるタイミング
		vibrate: [200], // モバイル端末でのバイブレーション秒数
		onClick: function() {
			//通知が消える
			this.close();
		}
	})
}

//終電30分前から10分おきに３回通知
function monitorTime() {
	// cookieから終電時刻超過フラグを取得する
	const overFlag = document.cookie
		.split('; ')
		.find(row => row.startsWith('overFlag'))
		.split('=')[1];

	if (overFlag.equals('false')) {
		// cookieから終電時刻を取得する(xx:xx のフォーマット)
		const startTime = document.cookie
			.split('; ')
			.find(row => row.startsWith('startTime'))
			.split('=')[1];

		// 取得した文字列を時と分に切り分けて整数型にする
		const lastTrainHours = parseInt(startTime.split(':')[0]);
		const lastTrainMinutes = parseInt(startTime.split(':')[1]);

		// 現在時刻取得
		var tmpDate = new Date();
		const nowHours = tmpDate.getHours();
		const nowMinutes = tmpDate.getMinutes();
		const nowSeconds = tmpDate.getSeconds();

		// 残り時間計算と負の時間を補正
		var hours = lastTrainHours - nowHours;
		var minutes = lastTrainMinutes - nowMinutes;
		var seconds = (60 - nowSeconds) % 60;
		if (hours < 0) {
			hours = hours + 24;
		}
		if (minutes < 0) {
			minutes += 60;
		}

		//30分前
		if (hours == 0 && minutes == 30 && seconds == 0) {
			push(minutes);
		}
		//20分前
		else if (hours == 0 && minutes == 20 && seconds == 0) {
			push(minutes);
		}
		//10分前
		else if (hours == 0 && minutes == 10 && seconds == 0) {
			push(minutes);
		}
	}
}

monitorTime();
setInterval(monitorTime, 1000);
