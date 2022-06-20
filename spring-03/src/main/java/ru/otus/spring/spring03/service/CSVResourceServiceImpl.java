package ru.otus.spring.spring03.service;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import ru.otus.spring.spring03.config.AppConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

@Service
public class CSVResourceServiceImpl implements CSVResourceService {
    private AppConfig appConfig;

    public CSVResourceServiceImpl(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @Override
    public InputStream getCSVResourceStream() {
        try {
            String fileName=String.format(appConfig.getQuestionFileName(),Locale.forLanguageTag(appConfig.getLocaleName()));
            Resource resource = new ClassPathResource(fileName);
            return resource.getInputStream();
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
        return null;
    }

    @Override
    public List<String> getLineListByResourceStream(Boolean skipFirstLine, InputStream inputStream) {
        List<String> lines = new ArrayList<>();
        Scanner scanner = new Scanner(inputStream);
        for (int lineNum = 1; scanner.hasNext(); lineNum++) {
            if (skipFirstLine && lineNum == 1) {
                scanner.nextLine();
                continue;
            }
            lines.add(scanner.nextLine());
        }
        return lines;
    }

}
