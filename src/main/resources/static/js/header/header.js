// common
import {
  homeButton,
  chooseSection,
  hideShowChooseSection
} from "../functions/common.js";

// Cart
import {
  cartOnLoad
} from "../functions/cart.js";

window.addEventListener("load", function() {
  // HomeButton
  homeButton();

  // Choose Section
  chooseSection();
  hideShowChooseSection();

  // Cart
  cartOnLoad();
});