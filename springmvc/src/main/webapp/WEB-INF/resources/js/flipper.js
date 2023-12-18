const addButton = document.querySelector(".add-button");
const flipContainerList = document.getElementById("flip-container-list");

function showPopupMessage(message) {
  const popup = document.createElement("div");
  popup.classList.add("popup-message");
  popup.textContent = message;

  document.body.appendChild(popup);

  // Remove the popup after a certain duration
  const duration = 2000; // milliseconds
  setTimeout(() => {
    popup.classList.add("hidden");
    setTimeout(() => {
      popup.remove();
    }, 300);
  }, duration);
}

function generateStrongPassword(length = 14) {
  const characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_+";
  let password = "";
  for (let i = 0; i < length; i++) {
    const randomIndex = Math.floor(Math.random() * characters.length);
    password += characters[randomIndex];
  }
  return password;
}

function copyToClipboard(text) {
  const dummyElement = document.createElement("textarea");
  dummyElement.value = text;
  dummyElement.style.position = "absolute";
  dummyElement.style.left = "-9999px";
  document.body.appendChild(dummyElement);
  dummyElement.select();
  document.execCommand("copy");
  document.body.removeChild(dummyElement);
}

function createFlipContainer(faviconUrl, websiteUrl, password, accId) {
  const flipContainer = document.createElement("div");

  flipContainer.setAttribute("title", "Click To Copy Password");

  flipContainer.classList.add("flip-container");

  const flipper = document.createElement("div");
  flipper.classList.add("flipper");
  flipper.classList.add("visible");

  const front = document.createElement("div");
  front.classList.add("front");

  const img = document.createElement("img");
  img.src = faviconUrl;
  img.alt = "Loading Icon ...";
  img.style.width = "100%";
  img.style.height = "100%";

  const back = document.createElement("div");
  back.classList.add("back");

  const passwordElement = document.createElement("div");
  passwordElement.classList.add("password");
  const passStr = password;
  passwordElement.textContent = "\u2022".repeat(passStr.length);
  passwordElement.style.fontFamily = "monospace";
  passwordElement.style.letterSpacing = "4px";
  passwordElement.style.webkitTextSecurity = "disc";
  passwordElement.style.MozTextSecurity = "disc";
  passwordElement.style.msTextSecurity = "disc";
  passwordElement.style.textSecurity = "disc";

  const showButton = document.createElement("button");
  showButton.setAttribute("title", "Click To Toggle Password");
  showButton.textContent = "Show Password";
  showButton.classList.add("show-button");

  showButton.addEventListener("click", function () {
    if (passwordElement.style.webkitTextSecurity === "disc") {
      passwordElement.textContent = passStr;
      passwordElement.style.webkitTextSecurity = "none";
      passwordElement.style.MozTextSecurity = "none";
      passwordElement.style.msTextSecurity = "none";
      passwordElement.style.textSecurity = "none";
      this.textContent = "Hide Password";
    } else {
      passwordElement.textContent = "\u2022".repeat(passStr.length);
      passwordElement.style.webkitTextSecurity = "disc";
      passwordElement.style.MozTextSecurity = "disc";
      passwordElement.style.msTextSecurity = "disc";
      passwordElement.style.textSecurity = "disc";
      this.textContent = "Show Password";
    }
  });

  const websiteLink = document.createElement("a");
  websiteLink.href = "https://" + websiteUrl;
  websiteLink.textContent = websiteUrl;
  websiteLink.target = "_blank";
  websiteLink.style.display = "flex";
  websiteLink.style.justifyContent = "center";
  websiteLink.style.alignItems = "center";
  websiteLink.style.marginTop = "20px";

  const closeButton = document.createElement("button");
  closeButton.setAttribute("title", "Remove Website!");
  closeButton.innerHTML = "&times;";
  closeButton.classList.add("close-button");

closeButton.addEventListener("click", function () {
    const del = confirm("Are you sure you want to remove this website?");

    if (del) {
		if(accId != -1) {
			// Assuming you have an accId to identify the account you want to remove
	        const accIdToRemove = accId;
	
	        // Make an asynchronous request to remove the account
	        fetch(`removeAccount/${accIdToRemove}`, {
	            method: 'DELETE',
	            headers: {
	                'Content-Type': 'application/json',
	            },
	        })
	        .then(response => {
	            if (response.ok) {
	                console.log('Account removed successfully');
	                // Optionally, you can update the UI or perform other actions
	            } else {
	                console.error('Error removing account:', response);
	            }
	        })
	        .catch(error => {
	            console.error('Error during fetch:', error);
	        });

		}
        // Optional: Add code to visually remove the element from the UI immediately
        flipContainer.classList.add("closed");
        setTimeout(() => {
            flipContainer.remove();
        }, 300);
    }
});


  back.appendChild(passwordElement);
  back.appendChild(showButton);
  back.appendChild(websiteLink);
  back.appendChild(closeButton);

  front.appendChild(img);

  flipper.appendChild(front);
  flipper.appendChild(back);

  flipContainer.appendChild(flipper);

  passwordElement.addEventListener("click", function () {
    copyToClipboard(passStr);
    showPopupMessage("Password Copied");
  });

  return flipContainer;
}

addButton.addEventListener("click", function () {
  
  const url = prompt("Enter the website URL:", "example.com");
  if (url) {
    const faviconUrl = `https://icon.horse/icon/${url}`;
    const passwordStr = generateStrongPassword();
    const flipContainer = createFlipContainer(faviconUrl, url, passwordStr, -1);
    flipContainerList.appendChild(flipContainer);
    callController(url, passwordStr);
  }
});




function callController(url, password) {
	console.log("HERE")
	console.log(userEmail)
	console.log(url)
	
	const accountObject = {
		url: url,
        userId: 0,
        email: userEmail,
        password: password,
    };
    
    fetch('account', {
        method: 'POST', // or 'GET' depending on your server setup
        headers: {
            'Content-Type': 'application/json',
            // Add any additional headers as needed
        },
        body: JSON.stringify(accountObject),
    })
    .then(response => {
        if (response.ok) {
            console.log('Controller action invoked successfully');
        } else {
            console.error(response);
        }
    })
    .catch(error => {
        console.error('Error during fetch:', error);
    });
}

// Initialize Sortable on the container element
const sortable = new Sortable(flipContainerList, {
  animation: 150,
});


const searchInput = document.getElementById("searchInput");

searchInput.addEventListener("input", function () {
  const searchTerm = this.value.trim().toLowerCase();

  const flipContainers = document.querySelectorAll(".flip-container");

  flipContainers.forEach(function (flipContainer) {
    const websiteLink = flipContainer.querySelector(".back a");
    const websiteName = websiteLink.textContent.trim().toLowerCase();

    if (websiteName.includes(searchTerm)) {
      flipContainer.style.display = "block";
    } else {
      flipContainer.style.display = "none";
    }
  });
});

    document.addEventListener("DOMContentLoaded", function () {
        // Get the container element
        const flipContainerList = document.getElementById("flip-container-list");

        // Get the websites from the JSP and convert it to a JavaScript array
        console.log(websitesString);
        const websites = JSON.parse(websitesString);

        // Iterate over the list of websites and create flipper elements
        websites.forEach(website => {
            const faviconUrl = `https://icon.horse/icon/${website.url}`;
            const flipContainer = createFlipContainer(faviconUrl, website.url, website.password, website.accId);
            flipContainerList.appendChild(flipContainer);
        });
    });

