import {
    URI_BASE
} from "./common.js";

export let productsInCart;
export let cartQuantity;
export let total;
const cart = document.getElementById("cart");
const order = document.getElementsByClassName("order")[0];

const prepeareCart = function() {
    productsInCart = JSON.parse(localStorage.getItem("productsInCart"));
    if (productsInCart === null) {
        productsInCart = [];
    }
    else {
        productsInCart.forEach(addCartItemsToCart);
    }
    cartQuantity = Number(localStorage.getItem("cartQuantity"));
    if (cartQuantity === null) {
        cartQuantity = 0;
    }
    updateCartQuantity(cartQuantity);
    updateTotal();
};

const updateCartQuantity = function(quantity) {
    cartQuantity = quantity;
    document.getElementById("cart-quantity").innerHTML = quantity;
};

const updateTotal = function() {
    total = 0;
    productsInCart.forEach(product => {
        total += product.price * product.quantity;
    });

    const cartTotalPrice = document.getElementsByClassName("cart-total-price")[0];
    cartTotalPrice.innerHTML = total.toFixed(2) + " BGN";
};

const storeProductsAndQuantityInLocalStorage = function(productsInCart, cartQuantity) {
    localStorage.setItem("productsInCart", JSON.stringify(productsInCart));
    localStorage.setItem("cartQuantity", JSON.stringify(cartQuantity));
};

const addCartItemsToCart = function(product) {
    const cartItemsContainer = document.getElementsByClassName("cart-items-container")[0];
    cartItemsContainer.innerHTML += `<div class="cart-item">
                                        <img class="cart-item-thumbnail" src="${product.thumbnail}">
                                        <div class="cart-item-info">
                                            <p class="cart-item-quantity" id="cart-item-quantity">${product.quantity} x </p>
                                            <p class="cart-item-title">${product.title}</p>
                                            <p class="cart-item-price">${product.price} BGN</p>
                                        </div>
                                        <img class="thrash" src="/icons/thrash.png">
                                    </div>`;

    // add event handler for the trash icon REMOVE ITEM
    cartItemsContainer.querySelectorAll(".thrash").forEach(function(thrash) {
        thrash.addEventListener("click", function() {
            // remove the product from the cart's HTML
            const cartItem = thrash.closest(".cart-item");
            const currentProductTitle = cartItem.getElementsByClassName("cart-item-title")[0].innerHTML;

            for (let i = 0; i < productsInCart.length; i++) {
                if (productsInCart[i].title === currentProductTitle) {
                    updateCartQuantity(cartQuantity - productsInCart[i].quantity);

                    // Change the value of the product before removing, because it still exists.
                    // These are made in the window eventListener, for every product on the page.
                    productsInCart[i].quantity = 0;

                    // the second argument specifies to remove 1 element
                    productsInCart.splice(i, 1);
                }
            }
            cartItem.remove();
            updateTotal();
            storeProductsAndQuantityInLocalStorage(productsInCart, cartQuantity);
            makeOrderOrangeOrGray();
            if (window.location.href === URI_BASE + "/checkout") {
                setProductsValueAndTotalCost(productsValueInCart, totalCostInCart);
            }
        });
    });
};

const updateProductQuantityInCartItem = function(product) {
    const cartItemsContainer = document.getElementsByClassName("cart-items-container")[0];
    const cartItemTitles = cartItemsContainer.getElementsByClassName("cart-item-title");
    let cartItem;
    for (let i = 0; i < cartItemTitles.length; i++) {
        if (cartItemTitles[i].innerHTML === product.title) {
            cartItem = cartItemTitles[i].closest(".cart-item-info");
            break;
        }
    }
    const cartItemQuantity = cartItem.getElementsByClassName("cart-item-quantity")[0];
    cartItemQuantity.innerHTML = product.quantity + " x";
};

const makeOrderOrangeOrGray = function() {
    if (cartQuantity > 0) {
        order.style.backgroundColor = "rgb(255, 128, 0)";
        order.style.cursor = "pointer";
        return;
    }
    order.style.backgroundColor = "rgb(179, 179, 179)";
    order.style.cursor = "not-allowed";
};

export const addToCart = function(productObject, quantity) {
    productObject.quantity += quantity;
    updateCartQuantity(cartQuantity + quantity);
    
    const index = productsInCart.indexOf(productObject);
    if (index === -1) {
        productsInCart.push(productObject);
        addCartItemsToCart(productObject);
        storeProductsAndQuantityInLocalStorage(productsInCart, cartQuantity);
        updateTotal();
        makeOrderOrangeOrGray();
        return;
    }
    updateProductQuantityInCartItem(productObject);
    storeProductsAndQuantityInLocalStorage(productsInCart, cartQuantity);
    updateTotal();
};


// WINDOW ON LOAD
export const cartOnLoad = () => {

    prepeareCart();

    // Make cart block visible and invisible
    cart.addEventListener("click", function() {
        const cartBlock = document.getElementById("cart-block");
        cartBlock.style.display = "block";

        const closeCart = cartBlock.getElementsByClassName("close")[0];
        closeCart.addEventListener("click", function() {
            cartBlock.style.display = "none";
        });
    });

    makeOrderOrangeOrGray();

    order.addEventListener("click", function() {
        if (cartQuantity > 0) {
            window.location.href = URI_BASE + "/checkout";
        }
    });
};

// Checkout function
let productsValueInCart;
let totalCostInCart;
export const setProductsValueAndTotalCost = function(productsValue, totalCost) {
    productsValueInCart = productsValue;
    totalCostInCart = totalCost;
    if (cartQuantity === 0) {
        window.location.href = URI_BASE + "/engine-oil";
    }
    productsValue.innerHTML = cartQuantity;
    totalCost.innerHTML = total.toFixed(2) + " BGN";
}