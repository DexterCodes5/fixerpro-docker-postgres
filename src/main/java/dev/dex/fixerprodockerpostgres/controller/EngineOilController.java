package dev.dex.fixerprodockerpostgres.controller;

import dev.dex.fixerprodockerpostgres.entity.*;
import dev.dex.fixerprodockerpostgres.service.*;
import dev.dex.fixerprodockerpostgres.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.util.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/fixerpro/engine-oil")
public class EngineOilController {
    private final EngineOilService engineOilService;

    // Dependency Injection on the services via Constructor Injection
    @Autowired
    public EngineOilController(EngineOilService engineOilService) {
        this.engineOilService = engineOilService;
    }

    @GetMapping
    public String getEngineOils(Model model) {
        return setupPage(engineOilService.findByPage(1), model);
    }

    @GetMapping("/")
    public String getEngineOils(@RequestParam("page") int page, Model model) {
        // Leave the page like that
        return setupPage(engineOilService.findByPage(page), model);
    }

    @GetMapping("/brand-{brands}")
    public String getEngineOilsByBrand(@PathVariable String brands, Model model) {
        Pair<List<EngineOil>, Long> engineOilsAndCount =
                engineOilService.findByBrandsAndPage(brands.toUpperCase().split("-"), 1);
        return setupPage(engineOilsAndCount, model);
    }

    @GetMapping("/brand-{brands}/")
    public String getEngineOilsByBrand(@RequestParam("page") int page, @PathVariable String brands, Model model) {
        Pair<List<EngineOil>, Long> engineOilsAndCount =
                engineOilService.findByBrandsAndPage(brands.toUpperCase().split("-"), page);
        return setupPage(engineOilsAndCount, model);
    }

    @GetMapping("/liters-{liters}")
    public String getEngineOilsByLiters(@PathVariable String liters, Model model) {
        Pair<List<EngineOil>, Long> engineOilsAndCount =
                engineOilService.findByLitersAndPage(liters.split("-"), 1);
        return setupPage(engineOilsAndCount, model);
    }

    @GetMapping("/liters-{liters}/")
    public String getEngineOilsByLiters(@RequestParam("page") int page, @PathVariable String liters, Model model) {
        Pair<List<EngineOil>, Long> engineOilsAndCount =
                engineOilService.findByLitersAndPage(liters.split("-"), page);
        return setupPage(engineOilsAndCount, model);
    }

    @GetMapping("/brand-{brands}/liters-{liters}")
    public String getEngineOilsByBrandAndLiters(@PathVariable String brands, @PathVariable String liters, Model model) {
        Pair<List<EngineOil>, Long> engineOilsAndCount =
            engineOilService.findByBrandsAndLitersAndPage(
                    brands.toUpperCase().split("-"), liters.split("-"), 1);
        return setupPage(engineOilsAndCount, model);
    }

    @GetMapping("/brand-{brands}/liters-{liters}/")
    public String getEngineOilsByBrandAndLiters(@RequestParam("page") int page, @PathVariable String brands, @PathVariable String liters, Model model) {
        Pair<List<EngineOil>, Long> engineOilsAndCount =
                engineOilService.findByBrandsAndLitersAndPage(
                        brands.toUpperCase().split("-"), liters.split("-"), page);
        return setupPage(engineOilsAndCount, model);
    }

    private String setupPage(Pair<List<EngineOil>, Long> engineOilsAndCount, Model model) {
        model.addAttribute("engineOils", engineOilsAndCount.getFirst());
        model.addAttribute("engineOilsLength", engineOilsAndCount.getSecond());
        return "engine-oil";
    }

    @GetMapping("/{productTitle}")
    public String getProduct(@PathVariable String productTitle, Model model) {
        EngineOil engineOil = engineOilService.findByTitle(productTitle);
        model.addAttribute("product", engineOil);
        model.addAttribute("name", engineOil.getTitle());
        model.addAttribute("brand", Utilities.getEngineOilBrand(engineOil));
        model.addAttribute("description", "Amazing oil, it's the best oil, really cool oil...");
        return "product-page";
    }
}
