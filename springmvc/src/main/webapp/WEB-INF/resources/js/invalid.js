/**
 * 
 */

function showPopupMessage(message) {
  const popup = document.createElement("div");
  popup.classList.add("popup-message");
  popup.textContent = message;

  document.body.appendChild(popup);

  // Shake the popup for 1 second
  const shakeDuration = 1000; // milliseconds
  const shakeInterval = 100; // milliseconds
  const shakeMagnitude = 30; // pixels

  const startX = parseInt(window.getComputedStyle(popup).getPropertyValue("left"));
  let elapsed = 0;

  const shakeIntervalId = setInterval(() => {
    const progress = elapsed / shakeDuration;
    const shakeOffset = Math.round(shakeMagnitude * Math.sin(progress * Math.PI * 2));

    popup.style.left = startX + shakeOffset + "px";

    elapsed += shakeInterval;

    if (elapsed >= shakeDuration) {
      clearInterval(shakeIntervalId);
      popup.style.left = startX + "px";

      // Remove the popup after a certain duration
      const duration = 6000; // milliseconds
      setTimeout(() => {
        popup.classList.add("hidden");
        setTimeout(() => {
          popup.remove();
        }, 300);
      }, duration);
    }
  }, shakeInterval);
}
