/* common */
import { URI_BASE } from "../functions/common.js";

/* Pagination */
import { paginationOnLoad } from "../functions/pagination.js";

/* Cart */
import { productsInCart, addToCart } from "../functions/cart.js";

/* Handle Product Title and Thumbnail click */
const openProductPage = (productThumbnail) => {
  const productTitle = productThumbnail.substring(36, productThumbnail.length - 4);
  window.location.href = `${URI_BASE}/engine-oil/${productTitle}`;
};

/* Window On Load Listener */
window.addEventListener("load", function () {

  /* Pagination */
  paginationOnLoad();

  /* Products */
  document.querySelectorAll(".product").forEach(function (product) {
    // Can't put this in the event listeners of the buttons
    const id = product.getElementsByClassName("id")[0].innerHTML;
    const button = product.getElementsByClassName("btn-add-to-cart")[0];
    const productTitle = product.getElementsByClassName("product-title")[0];
    const productThumbnailContainer = product.getElementsByClassName("thumbnail-container")[0];
    const productThumbnail = product.getElementsByClassName("thumbnail")[0].src;
    let productPrice = product.getElementsByClassName("price")[0].innerHTML;
    productPrice = productPrice.substring(0, productPrice.length - 4);

    // Create a new object for every product, if product exists in cart use the old product
    let productObject = {
      id: id,
      title: productTitle.innerHTML,
      thumbnail: productThumbnail,
      price: productPrice,
      quantity: 0,
    };
    for (let i = 0; i < productsInCart.length; i++) {
      if (productsInCart[i].title === productTitle.innerHTML) {
        productObject = productsInCart[i];
      }
    }

    button.addEventListener("click", function () {
      addToCart(productObject, 1);
    });

    // Add Event Listener for Thumbnail Container and Product Title
    productThumbnailContainer.addEventListener("click", function () {
      // I have access to the products title because I made this whole function starting at
      // querySelectorAll(."product") for all products, this means all these functions are different
      openProductPage(productThumbnail);
    });
    productTitle.addEventListener("click", function () {
      openProductPage(productThumbnail);
    });
  });
});
