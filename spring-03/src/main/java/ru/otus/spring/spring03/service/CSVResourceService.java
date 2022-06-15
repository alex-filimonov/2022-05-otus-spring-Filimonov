package ru.otus.spring.spring03.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface CSVResourceService {
    InputStream getCSVResourceStream();

    List<String> getLineListByResourceStream(Boolean skipFirstLine, InputStream inputStream);
}
