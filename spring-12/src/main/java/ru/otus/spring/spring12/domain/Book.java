package ru.otus.spring.spring12.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
@NamedEntityGraph(name = "book-genre-author-entity-graph",
        attributeNodes = {@NamedAttributeNode("genre"),@NamedAttributeNode("author"),@NamedAttributeNode("commentList")})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToOne(targetEntity = Genre.class, cascade = CascadeType.DETACH)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @OneToOne(targetEntity = Author.class, cascade = CascadeType.DETACH )
    @JoinColumn(name = "author_id")
    private Author author;

    @OneToMany(targetEntity = Comment.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "book_id")
    private List<Comment> commentList;

}