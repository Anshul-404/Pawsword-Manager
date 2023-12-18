
let usernameRef = document.getElementById("username");
let passwordRef = document.getElementById("password");
let emailRef = document.getElementById("email");
let eyeL = document.querySelector(".eyeball-l");
let eyeR = document.querySelector(".eyeball-r");
let handL = document.querySelector(".hand-l");
let handR = document.querySelector(".hand-r");
let showPasswordBtn = document.getElementById("showPasswordBtn");
var eyeIcon = document.querySelector('#showPasswordBtn i');

function myFunction() {
  if (passwordRef.type === "password") {
    passwordRef.type = "text";
    eyeIcon.classList.remove('fa-eye');
    eyeIcon.classList.add('fa-eye-slash');
  } else {
    passwordRef.type = "password";
    eyeIcon.classList.remove('fa-eye-slash');
    eyeIcon.classList.add('fa-eye');
  }
} 

let normalEyeStyle = () => {
  eyeL.style.cssText = `
    left:0.6em;
    top: 0.6em;
  `;
  eyeR.style.cssText = `
  right:0.6em;
  top:0.6em;
  `;
};

let normalHandStyle = () => {
  handL.style.cssText = `
        height: 2.81em;
        top:8.4em;
        left:7.5em;
        transform: rotate(0deg);
    `;
  handR.style.cssText = `
        height: 2.81em;
        top: 8.4em;
        right: 7.5em;
        transform: rotate(0deg)
    `;
};
//When clicked on username input
if(usernameRef)
{
	usernameRef.addEventListener("focus", () => {
	eyeL.style.cssText = `
	left: 0.75em;
	top: 1.12em;  
	`;
	eyeR.style.cssText = `
	right: 0.75em;
	top: 1.12em;
	  `;
	normalHandStyle();
});
}

//When clicked on email input
emailRef.addEventListener("focus", () => {
	eyeL.style.cssText = `
	left: 0.75em;
	top: 1.12em;  
	`;
	eyeR.style.cssText = `
	right: 0.75em;
	top: 1.12em;
	  `;
	normalHandStyle();
});

//When clicked on password input
passwordRef.addEventListener("focus", () => {
  handL.style.cssText = `
        height: 6.56em;
        top: 3.87em;
        left: 11.75em;
        transform: rotate(-155deg);    
    `;
  handR.style.cssText = `
    height: 6.56em;
    top: 3.87em;
    right: 11.75em;
    transform: rotate(155deg);
  `;
  normalEyeStyle();
});


//When clicked outside username and password input
document.addEventListener("click", (e) => {
  let clickedElem = e.target;
  
  if(usernameRef && clickedElem != usernameRef && clickedElem != passwordRef && 
  clickedElem != emailRef) {
    normalEyeStyle();
    normalHandStyle();
  }
  
  else if(usernameRef == null  && clickedElem != passwordRef && clickedElem != emailRef){
	normalEyeStyle();
    normalHandStyle();
  }
  
});