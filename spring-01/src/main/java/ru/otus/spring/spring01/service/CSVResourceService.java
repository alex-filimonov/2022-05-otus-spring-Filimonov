package ru.otus.spring.spring01.service;

import java.io.IOException;
import java.io.InputStream;

public interface CSVResourceService {
    InputStream getCSVResourceStream() throws IOException;
}
