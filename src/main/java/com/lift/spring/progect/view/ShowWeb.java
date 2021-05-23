package com.lift.spring.progect.view;

import com.lift.spring.progect.model.LogLift;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("web")
public class ShowWeb implements View {
    @Override
    public List<List<Object>> showHouse(LogLift logLift) {
        return logLift.getResult();
    }
}