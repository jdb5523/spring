package com.jdb;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class BasketballCoach implements Coach {    
    public FortuneService fortuneService;

    public BasketballCoach(FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }

    public String getWorkout() {
        return "30 minute dribbling";
    }

    @Override
    public String getDailyFortune() {
        return this.fortuneService.getFortune();
    }

    @PostConstruct
    public void init() {
        System.out.println("Constructing bbCoach");
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("About to destroy bbCoach");
    }
}
