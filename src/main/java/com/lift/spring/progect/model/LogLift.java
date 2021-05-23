package com.lift.spring.progect.model;

import com.lift.spring.progect.service.Move;

import java.io.*;
import java.util.*;
public class LogLift {
    private final List<List<Object>> result;

    public LogLift() {
        this.result = new ArrayList<>();
    }

    public void addLoggingLiftFloor(House house) throws IOException {
        try {
            List<Object> eventFloor = new ArrayList<>();
            House cloneHouse = serializeDeserialize(house);
            Lift lift = cloneHouse.getLift();
            eventFloor.add(lift);
            Map<Move, Queue<User>> floor = cloneHouse.getUsersHouse().get(cloneHouse.getLift().getPosition());
            eventFloor.add(floor.get(Move.UP));
            eventFloor.add(floor.get(Move.DOWN));
            result.add(eventFloor);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private House serializeDeserialize(House house) throws IOException, ClassNotFoundException {
        try(ByteArrayOutputStream outByte = new ByteArrayOutputStream()) {
            try(ObjectOutputStream outObject = new ObjectOutputStream(outByte)){
                outObject.writeObject(house);
            }
            return deserialize(outByte.toByteArray());
        }
    }

    private House deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        try(ByteArrayInputStream inputByte = new ByteArrayInputStream(bytes)){
            try(ObjectInputStream inputObject = new ObjectInputStream(inputByte)){
                return (House) inputObject.readObject();
            }
        }
    }
    public List<List<Object>> getResult() {
        return result;
    }
}