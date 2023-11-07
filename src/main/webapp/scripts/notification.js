function showNotification(message, type) {
    const notificationElement = document.getElementById('notification');
    if (message) {
        notificationElement.innerText = message;

        // Set the background color based on the type
        switch (type) {
            case 'error':
                notificationElement.style.backgroundColor = '#FF5733';
                break;
            case 'warning':
                notificationElement.style.backgroundColor = '#FFD700'; // Yellow for warning
                break;
            case 'success':
                notificationElement.style.backgroundColor = '#4CAF50'; // Green for success
                break;
            default:
                notificationElement.style.backgroundColor = '#FF5733'; // Default to error
        }

        notificationElement.style.display = 'block';

        // Hide the notification after a few seconds (adjust the timeout as needed)
        setTimeout(function () {
            notificationElement.style.display = 'none';
        }, 3000); // 3 seconds
    } else {
        notificationElement.style.display = 'none';
    }
}