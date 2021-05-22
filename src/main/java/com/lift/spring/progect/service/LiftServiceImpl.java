package com.lift.spring.progect.service;

import com.lift.spring.progect.model.Event;
import com.lift.spring.progect.model.House;
//import com.lift.spring.progect.model.Lift;
import com.lift.spring.progect.model.Lift;
import com.lift.spring.progect.model.User;
import com.lift.spring.progect.view.View;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class LiftServiceImpl implements LiftService {
    private final House house;
    private final RandomGenerator generator;
    private View view;
    private final static Logger logger = Logger.getLogger(LiftServiceImpl.class);
    private Event event;
    @Autowired
    public LiftServiceImpl(RandomGenerator generator, House house, View view, Event event) {
        this.house = house;
        this.generator = generator;
        this.view = view;
        this.event = event;
    }
    @Override
    public void firstStart() throws IOException {
        logger.info("FLOOR - " + house.getLift().getPosition());
        house.getLift().getUsersIntoLift().removeIf(Objects::isNull);
        view.showHouse();
        event.addLoggingLiftFloor(house);
        usersComeInLift(house.getUsersHouse().get(house.getLift().getPosition()));
        run();
        usersGoOutLift();
        if(house.getLift().getUsersIntoLift().size() > 0) {
            firstStart();
        }
    }

    private boolean isFree() {
        return (house.getLift().getMaxUser() > house.getLift().getUsersIntoLift().size());
    }

    @Override
    public void usersComeInLift(Map<Move, Queue<User>> allUsersFloor) {
        while(isFree()) {
            house.getLift().getUsersIntoLift().add(allUsersFloor.get(house.getLift().getMove()).poll());
        }
        logger.info("GO INTO LIFT");
    }

    @Override
    public void usersGoOutLift() {
        ArrayList<User> usersLift = (ArrayList<User>) house.getLift().getUsersIntoLift();
        logger.info(usersLift);
        List<User> goOut = usersLift.stream().filter(Objects::nonNull)
                .filter(x -> x.getNewPosition() == house.getLift().getPosition())
                .map(this::updateUser)
                .collect(Collectors.toList());
        logger.info("RESULT OF STREAM " + goOut);
        for (User user: goOut) {
            house.getLift().getUsersIntoLift().remove(user);
        }
        updateFloor(goOut);
        logger.info("USERS EXIT LIFT  - ");
    }

    private void updateFloor (List <User> usersGoOutLift) {
        if (usersGoOutLift.size() > 0) {
            Move moveUsers = usersGoOutLift.get(0).getMove();
            int floor = usersGoOutLift.get(0).getPosition();
            for(User user : usersGoOutLift) {
                house.getUsersHouse().get(floor).get(moveUsers).offer(user);
            }
        }
        logger.info("UPDATE FLOOR");
    }

    @Override
    public void run () {
        house.getLift().setNextPosition();
        house.getLift().setPosition(house.getLift().getNextPosition());
        logger.info("RUN LIFT");
    }

//    public Lift getLift() {
//        return lift;
//    }
//
//    public void setLift(House.Lift lift) {
//        this.lift = lift;
//    }

    private User updateUser(User userStream) {
        userStream.setPosition(house.getLift().getPosition());
        userStream.setNewPosition(generator.generatePositionForUser(userStream.getPosition(), house.getAmountFlor()));
        return userStream;
    }

    @Override
    public void changeMove() {
        if (house.getLift().getMove() == Move.UP) {
            house.getLift().setMove(Move.DOWN);
        } else {
            house.getLift().setMove(Move.UP);
        }
    }
}
