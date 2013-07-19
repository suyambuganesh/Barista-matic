package com.pearson.baristamatic.controller;

import com.pearson.baristamatic.entity.Drink;
import com.pearson.baristamatic.service.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/drink")
public class DrinkController {

    @Autowired
    private DrinkService drinkService;

    @RequestMapping(value="", method=RequestMethod.GET)
    public @ResponseBody List<Drink> showAllDrinks() {
        return drinkService.findDrinks();
    }

    @RequestMapping(value="/{drinkId}", method=RequestMethod.GET)
    public @ResponseBody Drink showDrink(@PathVariable long drinkId) throws IOException {
        Drink drink = drinkService.findDrink(drinkId);

        if (drink == null)
            throw new IOException("The specified drink does not exist.");
        else
            return drink;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{drinkId}", method=RequestMethod.POST)
    public @ResponseBody String buyDrink(@PathVariable long drinkId) throws IOException {
        boolean success = drinkService.buyDrink(drinkId);
        if (success)
            return "Purchased Drink: " + drinkService.findDrink(drinkId).getDrinkName();
        else
            throw new IOException("Could not purchase drink.");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IOException.class)
    public @ResponseBody Map<String, String> handleDrinkNotFound(IOException ex) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("Error", ex.getMessage());
        return map;
    }
}