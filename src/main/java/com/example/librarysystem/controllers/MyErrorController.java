package com.example.librarysystem.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class MyErrorController {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("errorMessage", "An error has occurred:: " + ex.getMessage());
        modelAndView.setViewName("error");
        return modelAndView;
    }
}




