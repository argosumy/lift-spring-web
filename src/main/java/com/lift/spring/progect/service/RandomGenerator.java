package com.lift.spring.progect.service;

import com.lift.spring.progect.model.User;

import java.util.Map;
import java.util.Queue;

public interface RandomGenerator {
    Map<Integer, Map<Move, Queue<User>>> generateHouse(int amountFlorMin, int amountFloorMax, int amountUsers);
    int generatePositionForUser (int position, int amountFloor);
}