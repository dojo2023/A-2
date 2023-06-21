const Menu = document.querySelector('.menu');
const MenuOpen = document.querySelector('.menu_open');
const MenuClose = document.querySelector('.menu_close');

const MenuList = document.querySelector('.menu_list');
	Menu.addEventListener('click', () => {
		MenuOpen.classList.toggle('active');
		MenuClose.classList.toggle('active');
		MenuList.classList.toggle('active');
	});