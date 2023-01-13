package com.example.springstatemachinetest0;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import java.util.EnumSet;

@Configuration
@EnableStateMachine(name = "enumStateMachine")
public class EnumStateMachineConfig
        extends EnumStateMachineConfigurerAdapter<MyStates, MyEvents> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<MyStates, MyEvents> config)
            throws Exception {
        config
            .withConfiguration()
                .autoStartup(true)
                .listener(enumEventsListener());
    }

    @Override
    public void configure(StateMachineStateConfigurer<MyStates, MyEvents> states)
            throws Exception {
        states
            .withStates()
                .initial(MyStates.INITIAL)
                    .states(EnumSet.allOf(MyStates.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<MyStates, MyEvents> transitions)
            throws Exception {
        transitions
            .withExternal()
                .source(MyStates.INITIAL).target(MyStates.FIRST_STATE).event(MyEvents.E1)
                .and()
            .withExternal()
                .source(MyStates.FIRST_STATE).target(MyStates.SECOND_STATE).event(MyEvents.E2);
    }

    @Bean
    public StateMachineListener<MyStates, MyEvents> enumEventsListener() {
        return new StateMachineListenerAdapter<MyStates, MyEvents>() {
            @Override
            public void stateChanged(State<MyStates, MyEvents> from, State<MyStates, MyEvents> to) {
                String fromId = "none";
                if (from != null) {
                     fromId = from.getId().name();
                }
                System.out.println("State changed from " + fromId +" to " + to.getId());
            }
        };
    }
}
