package com.lift.spring.progect.model;

import com.lift.spring.progect.service.Move;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Lift implements Serializable {
    private transient final int maxUser;
    private transient Move move;
    private int position;
    private transient int nextPosition;
    private List<User> usersIntoLift;
    private transient final static Logger logger = Logger.getLogger(Lift.class);

    public Lift(int maxUser) {
        position = 1;
        move = Move.UP;
        usersIntoLift = new ArrayList<>();
        this.maxUser = maxUser;
    }

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }

    public int getNextPosition() {
        return nextPosition;
    }

    public void setNextPosition() {
        usersIntoLift.removeIf(Objects::isNull);
        if (usersIntoLift.size() > 1) {
            usersIntoLift.removeIf(Objects::isNull);
            if (move == Move.UP) {
                logger.info("USERS INTO LIFT" + usersIntoLift + " size " + usersIntoLift.size());
                nextPosition = usersIntoLift.stream().filter(Objects::nonNull).min(User::compareTo).get().getNewPosition();
            } else {
                nextPosition = usersIntoLift.stream().max(User::compareTo).get().getNewPosition();
            }
        } else if (usersIntoLift.size() != 0) {
            nextPosition = usersIntoLift.get(0).getNewPosition();
        }
    }

    public int getMaxUser() {
        return maxUser;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public List<User> getUsersIntoLift() {
        return usersIntoLift;
    }


}
