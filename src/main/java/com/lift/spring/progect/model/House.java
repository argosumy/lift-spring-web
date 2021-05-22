package com.lift.spring.progect.model;


import com.lift.spring.progect.service.Move;
import com.lift.spring.progect.service.RandomGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Map;
import java.util.Queue;

@Component
public class House implements Serializable {
    @Value("${house.floor.amount.max}")
    private transient String amountFloorMax;
    @Value("${house.floor.amount.min}")
    private transient String amountFloorMin;
    @Value("${house.floor.amount.users}")
    private transient String amountUsersFloor;
    private transient int amountFlor;
    private Map<Integer, Map<Move, Queue<User>>> usersHouse;
    private transient RandomGenerator generator;
    public Lift lift;
    @Value("${lift.size}")
    private transient int liftSize;



    @Autowired
    private House(RandomGenerator generator) {

        this.generator = generator;
    }


    public void generatedHouse () {
        usersHouse = generator.generateHouse(Integer.parseInt(amountFloorMin), Integer.parseInt(amountFloorMax), Integer.parseInt(amountUsersFloor));
        amountFlor = usersHouse.size();
        lift = new Lift(liftSize);
    }

    public Map<Integer, Map<Move, Queue<User>>> getUsersHouse() {
        return usersHouse;
    }

    public int getAmountFlor() {
        return amountFlor;
    }

    public Lift getLift() {
        return lift;
    }
}
