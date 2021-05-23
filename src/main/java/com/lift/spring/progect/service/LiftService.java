package com.lift.spring.progect.service;

import com.lift.spring.progect.model.LogLift;
import com.lift.spring.progect.model.User;

import java.io.IOException;
import java.util.Map;
import java.util.Queue;

public interface LiftService {
    void firstStart(LogLift event) throws IOException;
    void usersComeInLift(Map<Move, Queue<User>> allUsersFloor);
    void usersGoOutLift();
    void changeMove();
    void run ();
}