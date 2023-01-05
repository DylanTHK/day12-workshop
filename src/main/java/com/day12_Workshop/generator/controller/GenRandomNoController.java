package com.day12_Workshop.generator.controller;

import java.security.SecureRandom;
import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


// importing sub packages
import com.day12_Workshop.generator.models.Generate;
import com.day12_Workshop.generator.exception.RandNumException;

// set as spring controller
@Controller
// @RequestMapping detects /rand in URL
@RequestMapping(path = "/rand")
public class GenRandomNoController {
    
    // @GetMapping detects /form in URL
    @GetMapping(path = "/form")  
    public String showGenerateForm(Model model) {
        // creating instance of value
        Generate num = new Generate();
        model.addAttribute("generatedObj", num);
        
        // System.out.println("MODEL: " + model);
        return "generate"; // links to generate.html
    }

    // @PostMapping detects change in URL (from spring)
    @PostMapping(path = "/results") // linked from generate.html 's form
    public String postResult(@ModelAttribute Generate newNum, Model model) {
        System.out.println("number: " + newNum.getNumValue());
        System.out.println("model: " + model);
        System.out.println("Opening result.html");

        // calls method to generate random values to model
        generateRandoms(model, newNum.getNumValue()); 
        return "result"; // linking to result.html
    }

    // method to generate n number of random numbers between certain value
    private void generateRandoms(Model model, int n) {
        
        // check for exceptions
        System.out.println("Generating randoms");

        // set arrays
        List<Integer> randomList = new ArrayList<Integer>();
        List<String> modelList = new ArrayList<String>();

        // generate array of numbers 0 ~ 30
        for (int i = 0; i <= 30; i++) {
            randomList.add(i);
        }

        // randomise order of list
        Collections.shuffle(randomList);

        // add first n numbers of array in empty array
        for (int i = 0; i < n; i++) {
            String tempText = "number" + randomList.get(i) + ".jpg";
            modelList.add(tempText);
        }
        System.out.println("modelLsit: " + modelList); // can remove
        
        // update values in new values in model with model.addAttributes(name, value) 1. array 2. n
        model.addAttribute("numOfRandoms", n);
        model.addAttribute("randList", modelList);
    }
}
