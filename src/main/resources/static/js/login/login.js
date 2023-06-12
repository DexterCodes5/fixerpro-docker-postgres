// common
import {
  URI_BASE1
} from "../functions/common.js";

const signInForm = document.getElementById("sign-in-form");
const signInUsernameInput = document.getElementById("sign-in-username-input");
const signInPasswordInput = document.getElementById("sign-in-password-input");

const postLogin = async (username, password) => {
  // Get reCAPTCHA Token
  await grecaptcha.ready(async () => {

    const token = await grecaptcha
      .execute("YOUR_SITE_KEY", {
        action: "contact"
      });

    const response = await fetch(`${URI_BASE1}/authenticate-client?username=${username}&password=${password}`, {
      method: "POST",
      headers: {
        "recaptcha": token
      }
    });
    window.location.href = response.url;
  });
}

window.addEventListener("load", () => {

  signInForm.addEventListener("submit", (e) => {
    e.preventDefault();
    postLogin(signInUsernameInput.value, signInPasswordInput.value);
  });

});