package com.lift.spring.progect.model;

import com.lift.spring.progect.service.Move;
import org.apache.log4j.Logger;

import java.io.Serializable;

public class User implements Comparable, Serializable {
    private final int id;
    private int position;
    private int newPosition;
    private Move move;
    private final static Logger logger = Logger.getLogger(User.class);

    public User(int id, int position, int newPosition) {
        this.id = id;
        this.position = position;
        setNewPosition(newPosition);
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getNewPosition() {
        return newPosition;
    }

    public void setNewPosition(int newPosition) {
        this.newPosition = newPosition;
        if (position < newPosition) {
            move = Move.UP;
        } else {
            move = Move.DOWN;
        }
    }

    public Move getMove() {
        return move;
    }

    public int getId() {
        return id;
    }

    @Override
    public int compareTo(Object o) {
        User user = (User)o;
        if (o == null) {
            logger.info("Сравнение юзера " + o + " и " + this);
            return 0;
        }
        return this.getNewPosition() - user.getNewPosition();
    }

    @Override
    public String toString() {
        return "User " + id + " position " + position + " move " + newPosition;
    }
}
