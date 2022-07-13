package ru.otus.spring.spring05.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Genre {
    private int id;
    private String name;
}
