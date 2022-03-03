package com.jdb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

@Configuration
@PropertySource("logger.properties")
public class App {
    @Bean
    public LoggerConfig myLoggerConfig(@Value("${root.logger.level}") String rootLoggerLevel,
            @Value("${printed.logger.level}") String printedLoggerLevel) {

        LoggerConfig myLoggerConfig = new LoggerConfig(rootLoggerLevel, printedLoggerLevel);

        return myLoggerConfig;
    }

    @Bean
    public FortuneService badFortuneService() {
        return new BadFortuneService();
    }

    @Bean
    public FortuneService goodFortuneService() {
        return new GoodFortuneService();
    }

    @Bean
    @Scope("prototype")
    public Coach coach() {
        return new SwimCoach();
    }

    public static void main(String[] args) {
        // ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("app-context.xml");
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(App.class);

        Coach coach = ctx.getBean("coach", Coach.class);
        System.out.println(coach.getDailyFortune());

        ctx.close();
    }
}
