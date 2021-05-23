package com.lift.spring.progect.view;

import com.lift.spring.progect.model.LogLift;

import java.util.List;

public interface View {
    List<List<Object>> showHouse(LogLift logLift);
}