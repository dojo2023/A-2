/**
 *
 */

//スイッチの外枠とスイッチの要素を取得
const toggle = document.querySelector(".toggle");
const toggleSwitch = document.querySelector(".toggle_switch");
const check = document.getElementById("alert_check");

//クリックでacitveクラスを追加/削除
toggle.addEventListener("click", () => {
	check.checked = !check.checked;
	check.onchange();
    toggle.classList.toggle("active");
    toggleSwitch.classList.toggle("active");
});
