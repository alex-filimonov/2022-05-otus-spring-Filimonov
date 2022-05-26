package ru.otus.spring.spring01.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;

public class CSVResourceServiceImpl implements CSVResourceService {
    private String resourceName;

    CSVResourceServiceImpl(String resourceName) {
        this.resourceName = resourceName;
    }

    public InputStream getCSVResourceStream() throws IOException {
        Resource resource = new ClassPathResource(resourceName);
        return resource.getInputStream();
    }
}
