package dev.dex.fixerprodockerpostgres.controller;

import dev.dex.fixerprodockerpostgres.entity.*;
import dev.dex.fixerprodockerpostgres.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/fixerpro/filters")
public class FilterController {
    private final FilterService filterService;

    @Autowired
    public FilterController(FilterService filterService) {
        this.filterService = filterService;
    }

    @GetMapping
    public String getFiltersBasePage() {
        return "filters";
    }

    @GetMapping("/{brand}/{model}/{engine}")
    public String chooseFilterType(@PathVariable("brand") String brand, @PathVariable("model") String carModel,
                                   @PathVariable("engine") String engine, Model model) {
        model.addAttribute("displayFilterTypes", "true");
        return "filters";
    }

    @GetMapping("/{brand}/{model}/{engine}/{filter}")
    public String getFilters(@PathVariable("brand") String brand, @PathVariable("model") String carModel,
                          @PathVariable("engine") String engine, @PathVariable("filter") String filter, Model model) {
        List<Filter> filters = filterService.findFilters(brand, carModel, filter);
        model.addAttribute("displayFilterTypes", "true");

        if (filters.isEmpty()) {
            model.addAttribute("outOfStock", true);
        }
        else {
            model.addAttribute("filters", filters);
        }
        return "filters";
    }

    @GetMapping("/{filterNumber}")
    public String openFilterPage(@PathVariable String filterNumber, Model model) {
        Filter filter = filterService.findFilterByNumber(filterNumber);
        model.addAttribute("product", filter);
        model.addAttribute("name", filter.getBrand() + " " + filter.getNumber());
        model.addAttribute("brand", filter.getBrand());
        model.addAttribute("description", "Amazing filter, it's the best filter, really cool filter...");
        return "product-page";
    }

}
