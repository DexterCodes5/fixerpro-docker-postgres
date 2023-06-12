export const URI_BASE1 = "http://localhost:8080";
export const URI_BASE = "http://localhost:8080/fixerpro";

export const homeButton = function() {
    document.getElementsByClassName("logo_fixerpro")[0].addEventListener("click", function() {
        window.location.href = `${URI_BASE}/engine-oil`;
    });
}

/* Choose Section */
export const chooseSection = function() {
    document.querySelectorAll(".choose-btn").forEach(function(button) {
      const url = `${URI_BASE}/${button.innerHTML.toLowerCase().replaceAll(" ", "-")}`;
      if (window.location.href === url) {
        button.style.backgroundColor = "grey";
        return;
      }
      button.addEventListener("click", function() {
        window.location.href = url;
      });
    });
};

export const hideShowChooseSection = function() {
    document.querySelector(".hamburger-menu-icon").addEventListener("click", function() {
        const chooseSection = document.querySelector(".choose-section");
        if (chooseSection.style.display === "" || chooseSection.style.display === "none") {
            chooseSection.style.display = "block";
            return;
        }
        chooseSection.style.display = "none";
    });
};
