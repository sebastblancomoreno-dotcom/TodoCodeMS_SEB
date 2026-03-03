package com.todocodeacademy.ejercicioNotasEscolares.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class CalificationsController {

    //simulamos un repository con un array de notas
    private final int[] califications = {8, 7, 6, 10, 9, 5, 7, 9};

    @GetMapping("/califications")
    public String getCalifications() {
        return "Las notas individuales son: " + Arrays.toString(califications);
    }

    @GetMapping("/califications/average")
    public String getCalificationsAverage() {
        int sum = 0;
        for (int grade : califications) {
            sum += grade;
        }
        double average = sum/califications.length;
        return "El promedio es: " + average;
    }

}
