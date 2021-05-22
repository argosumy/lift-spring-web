package com.lift.spring.progect.model;

import com.lift.spring.progect.service.Move;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;
@Component
public class Event {
    private List<Lift> eventLift;
    private List<Map<Move, Queue<User>>> eventFloor;

    public Event() {
        this.eventLift = new ArrayList<>();
        this.eventFloor = new ArrayList<>();
    }


    public void addLoggingLiftFloor(House house) throws IOException {
        House cloneHouse;
        try {
            cloneHouse = serializeDeserialize(house);
            eventLift.add(cloneHouse.getLift());
            eventFloor.add(cloneHouse.getUsersHouse().get(cloneHouse.getLift().getPosition()));
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

    public List<Lift> getEventLift() {
        return eventLift;
    }

    public List<Map<Move, Queue<User>>> getEventFloor() {
        return eventFloor;
    }
}
