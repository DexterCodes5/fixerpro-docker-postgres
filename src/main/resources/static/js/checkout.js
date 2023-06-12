/* Common */
import { URI_BASE } from "./functions/common.js";

/* Cart */
import { total, setProductsValueAndTotalCost } from "./functions/cart.js";

// DELIVERY METHOD
const radioDoor = document.getElementById("radio-door");
const addressDoor = document.getElementById("address-door-row");
const radioOffice = document.getElementById("radio-office");
const addressOffice = document.getElementById("address-office-row");

// Cart Summary
const productsValue = document.getElementsByClassName("bs-value")[0];
const totalCost = document.getElementsByClassName("bs-total-value")[0];

const postCustomerOrder = async (customerOrder) => {
  await grecaptcha.ready(async () => {

    const token = await grecaptcha
      .execute("YOUR_SITE_KEY", {
        action: "contact"
      });
    
      const response = await fetch(URI_BASE + "/checkout/take-order", {
        method: "POST",
        headers: {
          "recaptcha": token,
          "Content-Type": "application/json",
        },
        body: JSON.stringify(customerOrder),
      });
      
      window.location.href = response.url;
  });
};

window.addEventListener("load", function () {
  // DELIVERY METHOD
  radioDoor.addEventListener("click", function () {
    if (radioDoor.checked) {
      addressDoor.style = "display: block;";
      addressOffice.style = "display: none;";
    }
  });

  radioOffice.addEventListener("click", function () {
    if (radioOffice.checked) {
      addressOffice.style = "display: block;";
      addressDoor.style = "display: none;";
    }
  });

  // Cart Summary
  setProductsValueAndTotalCost(productsValue, totalCost);

  // Complete Order
  document.querySelectorAll(".complete-order").forEach(function (completeOrder) {
    completeOrder.addEventListener("click", function () {
      const nameSurname = document.getElementById("name-surname").value;
      const telephone = document.getElementById("telephone").value;
      const deliveryMethod = document.querySelector(".radio-button:checked").value;
      let address = null;
      if (deliveryMethod === "Speedy - to door") {
        address = document.getElementById("address-door").value;
      } else if (deliveryMethod === "Speedy - to office") {
        address = document.getElementById("address-office").value;
      }
      const comment = document.getElementsByClassName("comment")[0].value;

      const customerOrder = {
        nameAndSurname: nameSurname,
        telephone: telephone,
        deliveryMethod: deliveryMethod,
        address: address,
        comment: comment,
        total: total,
        products: localStorage.getItem("productsInCart"),
      };

      // Clear Local Storage after successful order
      localStorage.clear();

      postCustomerOrder(customerOrder);
    });
  });
});
