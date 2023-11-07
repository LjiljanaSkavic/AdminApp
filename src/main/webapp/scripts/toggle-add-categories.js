document.addEventListener('DOMContentLoaded', function() {
	const addCategoryButton = document.getElementById('add-category-button');
	const saveCategoryButton = document.getElementById('save-category-button');
	const saveSubcategoryButton = document.getElementById('save-subcategory-button');
	const addCategoryWrapper = document.querySelector('.add-categories-wrapper');

	addCategoryButton.addEventListener('click', function() {
		if (addCategoryWrapper.style.display === 'none') {
			addCategoryWrapper.style.display = 'block';
			document.querySelector('.table').style.display = 'none';
			addCategoryButton.style.display = 'none';
		} else {
			addCategoryWrapper.style.display = 'none';
			document.querySelector('.table').style.display = 'table';
			addCategoryButton.style.display = 'block';
		}
	});

	saveCategoryButton.addEventListener('click', function() {
		addCategoryWrapper.style.display = 'none';
		document.querySelector('.table').style.display = 'table';
	});
	
	saveSubcategoryButton.addEventListener('click', function() {
		addCategoryWrapper.style.display = 'none';
		document.querySelector('.table').style.display = 'table';
	});
});