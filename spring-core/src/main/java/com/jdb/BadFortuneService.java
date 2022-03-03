package com.jdb;

public class BadFortuneService implements FortuneService {
    @Override
    public String getFortune() {
        return "Inside BadFortuneService";
    }
}
