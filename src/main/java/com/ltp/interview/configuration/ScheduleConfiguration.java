package com.ltp.interview.configuration;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class ScheduleConfiguration {

    private final ApplicationContext applicationContext;
    private final HikariDataSource hikariDataSource;

    @Value("${interview.boot.shutdown}")
    private String timeToShutdown;
    private LocalDateTime shutdownAfter;

    @Scheduled(cron = "0 */10 * * * *")
    public void shutdownAfter() {
        final LocalDateTime now = LocalDateTime.now();
        if(now.isAfter(shutdownAfter)) {
            hikariDataSource.close();
            SpringApplication.exit(applicationContext, () -> 0);
        }
    }

    @PostConstruct
    public void init() {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm:ss", Locale.ENGLISH);
        shutdownAfter = LocalDateTime.parse(timeToShutdown, formatter);
    }

}
