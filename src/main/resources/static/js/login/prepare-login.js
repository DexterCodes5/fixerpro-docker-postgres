// Sign in / Sign up
const signInRadio = document.getElementById("sign-in-radio");
const signUpRadio = document.getElementById("sign-up-radio");

const handleSignOption = sign => {
    const signInForm = document.getElementById("sign-in-form");
    const signUpForm = document.getElementById("sign-up-form");

    if (sign === "signIn") {
        signInForm.style.display = "block";
        signUpForm.style.display = "none";
    }
    else if (sign === "signUp") {
        signInForm.style.display = "none";
        signUpForm.style.display = "block";
    }
}

const handleErrorSignUp = () => {
    const urlParams = new URLSearchParams(window.location.search);
    if (urlParams.get("emailExists") === null && urlParams.get("usernameExists") === null) {
        return;
    }

    signUpRadio.checked = true;
    handleSignOption("signUp");
    const signUpError = document.getElementById("sign-up-error-text");
    signUpError.style.display = "flex";
    if (urlParams.get("emailExists") !== null) {
        signUpError.firstElementChild.innerHTML = "Email address is already taken";
    }
    else if (urlParams.get("usernameExists") !== null) {
        signUpError.firstElementChild.innerHTML = "Username is already taken";
    }
}


window.addEventListener("load", function() {

    signInRadio.checked = true;

    // Sign in / Sign up
    signInRadio.addEventListener("click", () => handleSignOption("signIn"));
    signUpRadio.addEventListener("click", () => handleSignOption("signUp"));
    handleErrorSignUp();

    // Show / Hide Password
    this.document.querySelectorAll(".password-container").forEach(passwordContainer => {
        const passwordShowBtn = passwordContainer.getElementsByClassName("password-show-btn")[0];
 
        passwordShowBtn.addEventListener("click", () => {
            const passwordInput = passwordContainer.getElementsByClassName("login-user-input")[0];
            if (passwordInput.type === "password") {
                passwordInput.type = "text";
                return;
            }
            passwordInput.type = "password";
        });
    });

});