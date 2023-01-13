package com.example.springstatemachinetest0;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;

@SpringBootApplication
public class SpringStatemachineTest0Application implements CommandLineRunner {

    @Autowired
    private StateMachine<MyStates, MyEvents> enumStateMachine;
    @Autowired
    private StateMachine<String,String> stringStateMachine;

    public static void main(String[] args) {
        SpringApplication.run(SpringStatemachineTest0Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("===================");
        enumStateMachine.sendEvent(MyEvents.E1);
        enumStateMachine.sendEvent(MyEvents.E2);
        System.out.println("===================");
        stringStateMachine.sendEvent("toFirstStep");
        stringStateMachine.sendEvent("toSecondStep");
        System.out.println("===================");
    }
}
