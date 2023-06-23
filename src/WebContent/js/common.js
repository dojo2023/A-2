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
function push(bodyStr) {
	Push.create('通知するよ', {
		body: bodyStr,
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
	const checkFlag = document.cookie
		.split('; ')
		.find(row => row.startsWith('check'))
		.split('=')[1];

	if (checkFlag === 'true') {
		// cookieから終電時刻を取得する(xx:xx のフォーマット)
		const startTime = document.cookie
			.split('; ')
			.find(row => row.startsWith('startTime'))
			.split('=')[1];

		const goalTime = document.cookie
			.split('; ')
			.find(row => row.startsWith('goalTime'))
			.split('=')[1];

		// 取得した文字列を時と分に切り分けて整数型にする
		const lastTrainHours = parseInt(startTime.split(':')[0]);
		const lastTrainMinutes = parseInt(startTime.split(':')[1]);

		const goalStationHours = parseInt(goalTime.split(':')[0]);
		const goalStationMinutes = parseInt(goalTime.split(':')[1]);

		// 現在時刻取得
		var tmpDate = new Date();
		const nowHours = tmpDate.getHours();
		const nowMinutes = tmpDate.getMinutes();
		const nowSeconds = tmpDate.getSeconds();

		// 残り時間計算と負の時間を補正
		var startHours = lastTrainHours - nowHours;
		var startMinutes = lastTrainMinutes - nowMinutes;
		var startSeconds = (60 - nowSeconds) % 60;
		if (startHours < 0) {
			startHours = startHours + 24;
		}
		if (startMinutes < 0) {
			startMinutes += 60;
		}
		var goalHours = goalTrainHours - nowHours;
		var goalMinutes = goalTrainMinutes - nowMinutes;
		var goalSeconds = (60 - nowSeconds) % 60;
		if (goalHours < 0) {
			goalHours = goalHours + 24;
		}
		if (goalMinutes < 0) {
			goalMinutes += 60;
		}


		//30分前
		if (startHours == 0 && startMinutes == 30 && startSeconds == 0) {
			push('30分後に終電が出発します。');
		}
		//20分前
		else if (startHours == 0 && startMinutes == 20 && startSeconds == 0) {
			push('20分後に終電が出発します。');
		}
		//10分前
		else if (startHours == 0 && startMinutes == 10 && startSeconds == 0) {
			push('10分後に終電が出発します。');
		}
		else if (goalHours == 0 && goalMinutes == 10 && goalSeconds == 0) {
			push('10分後に到着します。');
		}

	}
}

monitorTime();
setInterval(monitorTime, 1000);
