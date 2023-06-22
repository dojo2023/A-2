/**
 * 
 */

//スイッチの外枠とスイッチの要素を取得
const toggle = document.querySelector(".toggle");
const toggleSwitch = document.querySelector(".toggle_switch");

//クリックでacitveクラスを追加/削除
toggle.addEventListener("click", () => {
    toggle.classList.toggle("active");
    toggleSwitch.classList.toggle("active");
});
