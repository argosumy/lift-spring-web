package com.lift.spring.progect.service;

import com.lift.spring.progect.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

@Service
public class RandomGeneratorImpl implements RandomGenerator {
    @Override
    public Map<Integer, Map<Move, Queue<User>>> generateHouse(int amountFlorMin, int amountFloorMax, int amountUsersMax) {
        Map<Integer, Map<Move, Queue<User>>> usersHouse = new HashMap<>();
        Queue<User> usersMoveUp;
        Queue<User> usersMoveDown;
        Map<Move, Queue<User>> usersFloor;
        int amountFloor = (int)(Math.random() * (amountFloorMax - amountFlorMin + 1) + amountFlorMin);
        int amountUser;
        User user;
        int id = 1;
        for (int i = 1; i <= amountFloor; i++) {
            amountUser = (int)(Math.random() * (amountUsersMax +1));
            usersMoveUp = new LinkedList<>();
            usersMoveDown = new LinkedList<>();
            for (int j = 1; j <= amountUser; j++) {
                user = new User(id++, i, generatePositionForUser(i, amountFloor));
                if (user.getMove() == Move.UP) {
                    usersMoveUp.offer(user);
                } else {
                    usersMoveDown.offer(user);
                }
            }
            usersFloor = new HashMap<>();
            usersFloor.put(Move.UP, usersMoveUp);
            usersFloor.put(Move.DOWN, usersMoveDown);
            usersHouse.put(i, usersFloor);
        }
        if(usersHouse.get(1).get(Move.UP).size() == 0) {
            usersHouse.get(1).get(Move.UP).offer(new User(0, 1, 2));
        }
        return usersHouse;
    }

    @Override
    public int generatePositionForUser (int position, int amountFloor) {
        int newPosition;
        do {
            newPosition =  (int)(Math.random() * amountFloor + 1);
        } while (newPosition == position);
        return newPosition;
    }
}
