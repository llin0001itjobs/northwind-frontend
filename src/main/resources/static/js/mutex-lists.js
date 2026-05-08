const listButtons = document.querySelectorAll('.my-list-group-button');

listButtons.forEach(b => {
	b.addEventListener('click', () => {

		// Close all lists by removing active
		listButtons.forEach(bb => {
			bb.setAttribute('aria-expanded', 'false');
			listGroup = document.querySelector(bb.getAttribute('data-bs-target'));
			listGroup.classList.remove('show');
		});

	});
});