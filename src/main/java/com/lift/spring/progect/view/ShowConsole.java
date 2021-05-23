package com.lift.spring.progect.view;

import com.lift.spring.progect.model.House;
import com.lift.spring.progect.model.Lift;
import com.lift.spring.progect.model.LogLift;
import com.lift.spring.progect.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("console")
public class ShowConsole implements View {
    House house;

    public ShowConsole(House house) {
        this.house = house;
    }

    @Override
    public List<List<Object>> showHouse(LogLift logLift) {
        for(List<Object> floor : logLift.getResult()) {
            for(int i = 0; i < 100; i++) {
                System.out.print("-");
            }
            Lift lift = (Lift)floor.get(0);
            List<User> usersUp = (List<User>)floor.get(1);
            List<User> usersDown = (List<User>)floor.get(2);
            System.out.println("\r\nЭтаж " + lift.getPosition() + " Лифт едет " + lift.getMove());
            System.out.format("%16s %55s \r\n %50s %35s \r\n","В лифте","На площадке", "UP", "DOWN");
            boolean iter = true;
            int i = 0;
            while (iter) {
                if (lift.getUsersIntoLift().size() > i && lift.getUsersIntoLift().get(i) != null) {
                    System.out.format("%-35s",lift.getUsersIntoLift().get(i));
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
                if (lift.getUsersIntoLift().size() < i & usersUp.size() < i) {
                    iter = false;
                }
            }
        }
        return logLift.getResult();
    }
}
