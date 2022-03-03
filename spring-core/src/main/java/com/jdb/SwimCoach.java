package com.jdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class SwimCoach implements Coach {
    @Autowired
    @Qualifier("goodFortuneService")
    private FortuneService fortuneService;

    @Override
    public String getWorkout() {
        return "400m butterfly";
    }

    @Override
    public String getDailyFortune() {
        return this.fortuneService.getFortune();
    }
}
