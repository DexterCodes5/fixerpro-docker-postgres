import { URI_BASE } from "../functions/common.js";

let brandFilters = "brand";
let literFilters = "liters";

const getFiltersFromUri = () => {
  if (window.location.href.length < 42) {
    return;
  }

  const uri = window.location.href.substring(42);
  const filters = uri.split("/");
  for (let i = 0; i < filters.length; i++) {
    const filterFirstWord = filters[i].split("-")[0];
    if (filterFirstWord === "brand") {
      brandFilters = filters[i];
    }
    else if (filterFirstWord === "liters") {
      literFilters = filters[i];
    }
  }
}

const makeFilteredEngineOilUri = () => {
  let filteredEngineOilURI = `${URI_BASE}/engine-oil`;
  if (brandFilters !== "brand") {
    filteredEngineOilURI += `/${brandFilters}`;
  }
  if (literFilters !== "liters") {
    filteredEngineOilURI += `/${literFilters}`;
  }
  return filteredEngineOilURI;
}

/* Window On Load Listener */
window.addEventListener("load", () => {
  getFiltersFromUri();

  // When using querySelector/All I have to use dot before the class name
  document.querySelectorAll(".filter-brand").forEach(filter => {
    // this if statement checks the filters wich are in the URL
    if (brandFilters.split("-").includes(filter.value)) {
      filter.checked = true;
    }

    filter.addEventListener("change", () => {
      if (filter.checked) {
        brandFilters += "-" + filter.value; 
      }
      else {
        let brandFiltersTemp = "brand";
        const brandFiltersArr = brandFilters.split("-");
        for (let i = 1; i < brandFiltersArr.length; i++) {
          if (brandFiltersArr[i] === filter.value) {
            continue;
          }
          brandFiltersTemp += "-" + brandFiltersArr[i];
        }
        brandFilters = brandFiltersTemp;
      }
      window.location.href = makeFilteredEngineOilUri();
    });
  });

  document.querySelectorAll(".filter-liter").forEach(filter => {
    if (literFilters.split("-").includes(filter.value)) {
      filter.checked = true;
    }

    filter.addEventListener("change", () => {
      if (filter.checked) {
        literFilters += "-" + filter.value; 
      }
      else {
        let literFiltersTemp = "liters";
        const literFiltersArr = literFilters.split("-");
        for (let i = 1; i < literFiltersArr.length; i++) {
          if (literFiltersArr[i] === filter.value) {
            continue;
          }
          literFiltersTemp += "-" + literFiltersArr[i];
        }
        literFilters = literFiltersTemp;
      }
      window.location.href = makeFilteredEngineOilUri();
    });
  });
});