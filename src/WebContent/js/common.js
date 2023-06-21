const MenuContainer = document.querySelector('.menu_container');
const MenuOpen = document.querySelector('.menu_open');
const MenuClose = document.querySelector('.menu_close');

const MenuList = document.querySelector('.menu_list');
	MenuContainer.addEventListener('click', () => {
		MenuOpen.classList.toggle('active');
		MenuClose.classList.toggle('active');
		MenuList.classList.toggle('active');
	});