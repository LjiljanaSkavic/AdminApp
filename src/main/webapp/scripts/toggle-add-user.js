document.addEventListener('DOMContentLoaded', function() {
	const addUserButton = document.getElementById('add-user-button');
	const saveUserButton = document.getElementById('save-user-button');
	const addUserWrapper = document.querySelector('.add-user-wrapper');

	addUserButton.addEventListener('click', function() {
		if (addUserWrapper.style.display === 'none') {
			addUserWrapper.style.display = 'block';
			document.querySelector('.table').style.display = 'none';
			addUserButton.style.display = 'none';
		} else {
			addUserWrapper.style.display = 'none';
			document.querySelector('.table').style.display = 'table';
			addUserButton.style.display = 'block';
		}
	});

	saveUserButton.addEventListener('click', function() {
		addUserWrapper.style.display = 'none';
		document.querySelector('.table').style.display = 'table';
	});
});