function showNotification(message, type) {
    const notificationElement = document.getElementById('notification');
    if (message) {
        notificationElement.innerText = message;

        switch (type) {
            case 'error':
                notificationElement.style.backgroundColor = '#FF5733';
                break;
            case 'warning':
                notificationElement.style.backgroundColor = '#FFD700';
                break;
            case 'success':
                notificationElement.style.backgroundColor = '#4CAF50'; 
                break;
            default:
                notificationElement.style.backgroundColor = '#FF5733';
        }

        notificationElement.style.display = 'block';

        setTimeout(function () {
            notificationElement.style.display = 'none';
        }, 3000); 
    } else {
        notificationElement.style.display = 'none';
    }
}