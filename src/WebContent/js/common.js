const MenuContainer = document.querySelector('.menu_container');
const MenuOpen = document.querySelector('.menu_open');
const MenuClose = document.querySelector('.menu_close');

const Menu = document.querySelector('.menu');
	MenuContainer.addEventListener('click', () => {
		MenuOpen.classList.toggle('active');
		MenuClose.classList.toggle('active');
		Menu.classList.toggle('active');
	});