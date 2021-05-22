package com.lift.spring.progect.view;

import com.lift.spring.progect.model.House;
import com.lift.spring.progect.model.User;
import com.lift.spring.progect.service.Move;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

@Component
public class ShowConsole implements View {
    House house;

    public ShowConsole(House house) {
        this.house = house;
    }

    @Override
    public void showHouse() {
        List<User> usersLift = house.getLift().getUsersIntoLift();
        Map<Move, Queue<User>> usersFloor = house.getUsersHouse().get(house.getLift().getPosition());
        LinkedList<User> usersUp = (LinkedList<User>) usersFloor.get(Move.UP);
        LinkedList<User> usersDown = (LinkedList<User>) usersFloor.get(Move.DOWN);

        boolean iter = true;
        for(int i = 0; i < 100; i++) {
            System.out.print("-");
        }
        System.out.println();
        System.out.println("Этаж " + house.getLift().getPosition() + " Лифт едет " + house.getLift().getMove());
        System.out.format("%16s %55s \r\n %50s %35s \r\n","В лифте","На площадке", "UP", "DOWN");
        int i = 0;
        while (iter) {
            if (usersLift.size() > i && usersLift.get(i) != null) {
                System.out.format("%-35s",usersLift.get(i));
            } else {
                System.out.format("%-35s", " ");
            }
            if (usersUp.size() > i ) {
                System.out.format("%-35s", usersUp.get(i));
            } else {
                System.out.format("%-35s", " ");
            }
            if (usersDown.size() > i ) {
                System.out.format("%-35s \r\n", usersDown.get(i));
            } else {
                System.out.format("%-35s \r\n", " ");
            }
            i++;
            if (usersLift.size() < i & usersUp.size() < i) {
                iter = false;
            }
        }
    }
}
