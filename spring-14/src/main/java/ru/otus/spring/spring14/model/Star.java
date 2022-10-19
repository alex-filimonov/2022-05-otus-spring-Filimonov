package ru.otus.spring.spring14.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Id;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stars")
public class Star {
    @Id
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "distance")
    private Float distance;

}
