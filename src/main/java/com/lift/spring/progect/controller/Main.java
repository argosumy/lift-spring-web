package com.lift.spring.progect.controller;

import com.lift.spring.progect.model.LogLift;
import com.lift.spring.progect.model.House;
import com.lift.spring.progect.service.LiftService;
import com.lift.spring.progect.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class Main {
    private final House house;
    private final LiftService liftService;
    private final View viewConsole;
    private final View viewWeb;
    @Autowired
    public Main(House house, LiftService liftService, @Qualifier("console") View viewConsole, @Qualifier("web") View viewWeb) {
        this.house = house;
        this.liftService = liftService;
        this.viewConsole = viewConsole;
        this.viewWeb = viewWeb;
    }

    @GetMapping("/")
    public String start() {
        return "main";
    }

    @GetMapping("generate")
    public String generate(Model model) {
        house.generatedHouse();
        LogLift eventLog = new LogLift();
        try {
            liftService.firstStart(eventLog);
            eventLog.addLoggingLiftFloor(house);
            liftService.changeMove();
            liftService.firstStart(eventLog);
            eventLog.addLoggingLiftFloor(house);
            viewConsole.showHouse(eventLog);
            model.addAttribute("logLift", viewWeb.showHouse(eventLog));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "lift";
    }
}