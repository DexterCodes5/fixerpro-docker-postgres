/* get html elements via document */
const paginationNumbers = document.getElementById("pagination-numbers");
const nextButton = document.getElementById("next-button");
const prevButton = document.getElementById("prev-button");

const paginationLimit = 20;

const products = document.getElementById("oils-length").innerHTML;
const pageCount = Math.ceil(products / paginationLimit);

const urlParams = new URLSearchParams(window.location.search);
const getCurrentPage = () => {
  if (urlParams.get("page") === null) {
    return 1;
  }
  return Number(urlParams.get("page"));
}

const currentPage = getCurrentPage();

const disableButton = function(button) {
    button.classList.add("disabled");
    button.setAttribute("disabled", true);
};

const enableButton = function(button) {
    button.classList.remove("disabled");
    button.removeAttribute("disabled");
};

const handlePageButtonStatus = function() {
    if (currentPage === 1 || pageCount === 0) {
      disableButton(prevButton);
    }
    else {
      enableButton(prevButton);
    }
    if (currentPage === pageCount) {
      disableButton(nextButton)
    }
    else {
      enableButton(nextButton);
    }
};

const handleActivePageNumber = function() {
    document.querySelectorAll(".pagination-number").forEach(function(button) {
        button.classList.remove("active");
        const index = Number(button.getAttribute("page-index"));
        if (index === currentPage) {
            button.classList.add("active");
        }
    });
};

// storing a function
const appendPageNumber = function(index) {
    // document creates html element
    const pageNumber = document.createElement("button");
    pageNumber.className = "pagination-number";
    pageNumber.innerHTML = index;
    // creating a htm; attribute
    pageNumber.setAttribute("page-index", index);
    pageNumber.setAttribute("aria-label", "Page " + index);

    paginationNumbers.appendChild(pageNumber);
};

// storing a function
const getPaginationNumbers = function() {
    for (let i = 1; i <= pageCount; i++) {
      appendPageNumber(i);
    }
};

const goToNextPage = (page) => {
  let url = window.location.href;
  if (urlParams.get("page")) {
    url = url.substring(0, url.length - (7 + urlParams.get("page").length));
  }
  window.location.href = `${url}/?page=${page}`;
}

/* WINDOW ON LOAD */
export const paginationOnLoad = function() {
    getPaginationNumbers();
        handlePageButtonStatus();
        handleActivePageNumber();

        nextButton.addEventListener("click", function() {
            goToNextPage(currentPage + 1);
        });

        prevButton.addEventListener("click", function() {
            goToNextPage(currentPage - 1);
        });

        document.querySelectorAll(".pagination-number").forEach(function(button) {
          const pageIndex = Number(button.getAttribute("page-index"));

          button.addEventListener("click", function() {
            if (currentPage === pageIndex) return;
            goToNextPage(pageIndex);
          });
        });
}