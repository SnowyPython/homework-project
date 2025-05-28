package ru.vatolin.homeworkproject.util;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final DataSaver dataSaver;

    @Override
    public void run(String... args) throws Exception {
        dataSaver.clearAndGenerate();
    }
}
