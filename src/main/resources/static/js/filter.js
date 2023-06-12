/* common */
import {
  URI_BASE
} from "./functions/common.js";

/* Cart */
import {
  productsInCart,
  addToCart
} from "./functions/cart.js";

/* Handle Product Title and Thumbnail click */
const openProductPage = (productNumber) => {
  window.location.href = URI_BASE + `/filters/${productNumber.replaceAll("/", "--")}`;
};

/* Brand Model and Engine selects */
const brandSelect = document.querySelectorAll("select")[0];
const modelSelect = document.querySelectorAll("select")[1];
const engineSelect = document.querySelectorAll("select")[2];
const searchButton = document.querySelector(".brand-model-engine-search");

const brandModelMap = new Map();
brandModelMap.set("BMW", new Array("3 (E46) (1998 - 2005)", "5 (E39) (1995 - 2003)", "7 (E38) (1994 - 2001)"));
brandModelMap.set("MERCEDES-BENZ", new Array("C-Class (W203) (2000 - 2007)", "E-Class (W210) (1995 - 2003)", "S-Class (W220) (1998 - 2005)"));
brandModelMap.set("VW", new Array("Golf IV (1997 - 2005)", "Bora (1998 - 2005)", "Passat B5 (2000 - 2005)"));

const modelEngineMap = new Map();
// BMW
modelEngineMap.set("3 (E46) (1998 - 2005)", new Array("320i (163 HP) (2000 - 2005)", "325i (192 HP) (2000 - 2005)", "330i (231 HP) (2000 - 2005)"));
modelEngineMap.set("5 (E39) (1995 - 2003)", new Array("520i (163 HP) (2000 - 2003)", "525i (192 HP) (2000 - 2003)", "530i (231 HP) (2000 - 2003)"));
modelEngineMap.set("7 (E38) (1994 - 2001)", new Array("730i (218 HP) (1994 - 2001)", "740i (286 HP) (1996 - 2001)"));

// MERCEDES-BENZ
modelEngineMap.set("C-Class (W203) (2000 - 2007)", new Array("C 280 (231 HP) (2005 - 2007)", "C 320 (218 HP) (2000 - 2007)", "C 350 (272 HP) (2005 - 2007)"));
modelEngineMap.set("E-Class (W210) (1995 - 2003)", new Array("E 280 (204 HP) (1996 - 2002)", "E 320 (224 HP) (1997 - 2002)", "E 430 (279 HP) (1997 - 2002"));
modelEngineMap.set("S-Class (W220) (1998 - 2005)", new Array("S 350 (245 HP) (2002 - 2005)", "S 430 (279 HP) (1998 - 2005", "S 500 (306 HP) (1998 - 2005)"));

// VW
modelEngineMap.set("Golf IV (1997 - 2005)", new Array("1.6 (102 HP) (2000 - 2005)", "1.8 T (180 HP) (2001 - 2005)", "2.8 VR6 (200 HP) (2002 - 2005)"));
modelEngineMap.set("Bora (1998 - 2005)", new Array("1.6 (102 HP) (2000 - 2005)", "1.8 T (180 HP) (2002 - 2005)", "2.8 VR6 (200 HP) (2002 - 2005)"));
modelEngineMap.set("Passat B5 (2000 - 2005)", new Array("1.6 (102 HP) (2000 - 2005)", "1.8 T (150 HP) (2002 - 2005)", "2.8 (193 HP) (2000 - 2005"));

const fillInModels = function() {
    const models = brandModelMap.get(brandSelect.value);
    let html = "";
    for (let i = 0; i < models.length; i++) {
        html += `<option value="${models[i]}">${models[i]}</option>\n`;
    }
    modelSelect.innerHTML = html;
};

const fillInEngines = function() {
    const engines = modelEngineMap.get(modelSelect.value);
    let html = "";
    for (let i = 0; i < engines.length; i++) {
      html += `<option value="${engines[i]}">${engines[i]}</option>\n`
    }
    engineSelect.innerHTML = html;
};

const enableSearchButton = function() {
  if (searchButton.disabled) {
    searchButton.disabled = false;
    searchButton.style.backgroundColor = "rgb(255, 128, 0)";
    searchButton.style.cursor = "pointer";
  }
};

const createBrandModelEngineString = function() {
  let model = modelSelect.value;
  model = model.substring(0, model.length - 14);
  let engine = engineSelect.value;
  engine = engine.substring(0, engine.length - 18);
  let brandModelEngine = (brandSelect.value + "/" + model + "/" + engine).toLowerCase().replaceAll(/[\(\)]/ig, "").replaceAll(" ", "-");
  return brandModelEngine;
}

const displayFilterTypes = function() {
  if (document.getElementById("displayFilterTypes").innerHTML !== "true") {
    return;
  }
    
  const brandModelEngineObject = JSON.parse(localStorage.getItem("brandModelEngine"));
  brandSelect.value = brandModelEngineObject.brand;
  fillInModels();
  modelSelect.value = brandModelEngineObject.model;
  fillInEngines();
  engineSelect.value = brandModelEngineObject.engine;
  enableSearchButton();

  const filterBox = document.getElementsByClassName("choose-filter-link")[0];
  filterBox.style.display = "inline-block";
  document.querySelectorAll(".filter-link").forEach(function(filterLink) {
    filterLink.addEventListener("click", function() {
      window.location.href = URI_BASE + "/filters/" + createBrandModelEngineString() + "/" + filterLink.innerHTML.toLowerCase().replaceAll(" ", "-");
    });
  });
};

/* Window On Load Listener */
window.addEventListener("load", function() {

  document.querySelectorAll(".product").forEach(function(product) {
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
          quantity: 0
      };
      for (let i = 0; i < productsInCart.length; i++) {
          if (productsInCart[i].title === productTitle.innerHTML) {
              productObject = productsInCart[i];
          }
      }

      button.addEventListener("click", function() {
          addToCart(productObject, 1);
      });

      let titleSplited = productTitle.innerHTML.split(" ");
      let number = "";
      for (let i = 1; i < titleSplited.length; i++) {
        number += titleSplited[i];
      }

      // Add Event Listener for Thumbnail Container and Product Title
      productThumbnailContainer.addEventListener("click", function() {
          // I have access to the products title because I made this whole function starting at
          // querySelectorAll(."product") for all products, this means all these functions are different
          
          openProductPage(number);
      });
      productTitle.addEventListener("click", function() {
          openProductPage(number);
      });
  });

  /* Brand Model and Engine selects */
  displayFilterTypes();

  brandSelect.addEventListener("change", function() {
    fillInModels();
    fillInEngines();
    enableSearchButton();
  });

  modelSelect.addEventListener("change", function() {
    fillInEngines();
  });

  searchButton.addEventListener("click", function() {
    const brandModelEngineObject = {
      brand: brandSelect.value,
      model: modelSelect.value,
      engine: engineSelect.value
    }
    localStorage.setItem("brandModelEngine", JSON.stringify(brandModelEngineObject));
    window.location.href = URI_BASE + "/filters/" + createBrandModelEngineString();
  });

});