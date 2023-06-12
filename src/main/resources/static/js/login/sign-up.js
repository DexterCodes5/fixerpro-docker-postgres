import {
  URI_BASE
} from "../functions/common.js";

const signUpForm = document.getElementById("sign-up-form");

const emailField = document.getElementById("sign-up-email-field");
const usernameField = document.getElementById("sign-up-username-field");
const passwordField = document.getElementById("sign-up-password-field");
const confirmPasswordField = document.getElementById("sign-up-confirm-password-field");

const emailErrorText = document.getElementById("email-error-text");
const usernameErrorText = document.getElementById("username-error-text");
const passwordErrorText = document.getElementById("password-error-text");
const confirmPasswordErrorText = document.getElementById("confirm-password-error-text");

// check the user input against regex
const emailRegex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
const usernameRegex = /^(?=.{6,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$/;

// Minimum eight characters, at least one letter and one number
const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;

const checkUserInput = (isValid, field, errorText) => {
  if (!isValid) {
    field.style.borderColor = "red";
    errorText.style.display = "block";
    return;
  }
  field.style.borderColor = "#ccc";
  errorText.style.display = "none";
}

const postAccount = async (account) => {
  await grecaptcha.ready(async () => {
    
    const token = await grecaptcha
      .execute("YOUR_SITE_KEY", {
        action: "contact"
      });

    const response = await fetch(`${URI_BASE}/sign-up`, {
      method: "POST",
      headers: {
        "recaptcha": token,
        "Content-Type": "application/json"
      },
      redirect: "follow",
      body: JSON.stringify(account)
    });
    window.location.href = response.url;
  });
}

window.addEventListener("load", () => {
  
  signUpForm.addEventListener("submit", e => {
    e.preventDefault();

    const isEmailValid = emailRegex.test(emailField.value);
    const isUsernameValid = usernameRegex.test(usernameField.value);
    const isPasswordValid = passwordRegex.test(passwordField.value);
    const isConfirmPasswordValid = passwordField.value === confirmPasswordField.value;

    checkUserInput(isEmailValid, emailField, emailErrorText);
    checkUserInput(isUsernameValid, usernameField, usernameErrorText);
    checkUserInput(isPasswordValid, passwordField, passwordErrorText);
    checkUserInput(isConfirmPasswordValid, confirmPasswordField, confirmPasswordErrorText);

    if (!isEmailValid || !isUsernameValid || !isPasswordValid || !isConfirmPasswordValid) {
      return;
    }

    // POST the account info to the server
    const account = {
      email: emailField.value,
      username: usernameField.value,
      password: passwordField.value
    };

    postAccount(account);
  });


});