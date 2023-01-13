package com.example.springstatemachinetest0;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

@Configuration
@EnableStateMachine(name = "stringStateMachine")
public class StringStateMachineConfig
        extends StateMachineConfigurerAdapter<String, String> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<String, String> config)
            throws Exception {
        config
            .withConfiguration()
                .autoStartup(true)
                .listener(stringEventsListener());
    }

    @Override
    public void configure(StateMachineStateConfigurer<String, String> states)
            throws Exception {
        states
            .withStates()
                .initial("START")
                    .state("STEP1")
                    .state("STEP2");
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<String, String> transitions)
            throws Exception {
        transitions
            .withExternal()
                .source("START").target("STEP1").event("toFirstStep")
                .and()
            .withExternal()
                .source("STEP1").target("STEP2").event("toSecondStep");
    }

    @Bean
    public StateMachineListener<String, String> stringEventsListener() {
        return new StateMachineListenerAdapter<String, String>() {


            @Override
            public void stateChanged(State<String, String> from, State<String, String> to) {
                String fromId = "none";
                if (from != null) {
                     fromId = from.getId();
                }
                System.out.println("State changed from " + fromId +" to " + to.getId());
            }
        };
    }
}
