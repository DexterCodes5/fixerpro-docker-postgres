// Cart
import {
    productsInCart,
    addToCart
} from "./functions/cart.js";

const quantityInput = document.getElementsByClassName("quantity-input")[0];

const getProductObject = function(quantity) {
    let id = document.getElementsByClassName("under-line-col")[0].innerHTML;
    id = id.substring(7);
    const productTitle = document.getElementsByClassName("product-name")[0].innerHTML;
    const productThumbnail = document.getElementsByClassName("product-image")[0].src;
    let productPrice = document.getElementById("product-price").innerHTML;
    productPrice = productPrice.substring(0, productPrice.length - 4);
    
    let productObject = {
        id: id,
        title: productTitle,
        thumbnail: productThumbnail,
        price: productPrice,
        quantity: 0
    };

    for (let i = 0; i < productsInCart.length; i++) {
        if (productsInCart[i].title === productTitle) {
            productObject = productsInCart[i];
        }
    }

    addToCart(productObject, quantity);
}

window.addEventListener("load", function() {

    // Quantity And Buy button
    document.getElementById("minus-button").addEventListener("click", function() {
        if (quantityInput.value <= 1) return;
        quantityInput.value--;
    });

    document.getElementById("plus-button").addEventListener("click", function() {
        if (quantityInput.value > 100) return;
        quantityInput.value++;
    });

    document.getElementsByClassName("quantity-buy")[0].addEventListener("click", function() {
        const quantity = document.getElementsByClassName("quantity-input")[0];
        if (quantity.value < 1) {
            quantity.value = 1;
            return;
        }
        getProductObject(Number(quantity.value));
    });
});