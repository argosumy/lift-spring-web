package com.lift.spring.progect.controller;

import com.lift.spring.progect.model.Event;
import com.lift.spring.progect.model.House;
import com.lift.spring.progect.model.Lift;
import com.lift.spring.progect.service.LiftService;
import com.lift.spring.progect.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class Main {
    private House house;
    private LiftService liftService;
    private View view;
    private Event event;
    @Autowired
    public Main(House house, LiftService liftService, View view, Event event) {
        this.house = house;
        this.liftService = liftService;
        this.view = view;
        this.event = event;
    }

    @GetMapping("/")
    public String start() {
        return "main";
    }

    @PostMapping("send-parameter")
    public String setParameterForHouse(@RequestParam("floors")int floors,
                                       @RequestParam("lift-size")int liftSize,
                                       @RequestParam("users")int users) {
        System.out.println(floors + " " + liftSize + " " + users);
        return "redirect:/";
    }
    @GetMapping("generate")
    public String generate(Model model) {
        house.generatedHouse();
        try {
            liftService.firstStart();
            view.showHouse();
            event.addLoggingLiftFloor(house);
            liftService.changeMove();
            liftService.firstStart();
            view.showHouse();
            event.addLoggingLiftFloor(house);
            model.addAttribute("dto", event);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "house";
    }



}
