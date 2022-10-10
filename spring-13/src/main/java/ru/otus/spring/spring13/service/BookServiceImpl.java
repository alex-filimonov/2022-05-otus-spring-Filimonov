package ru.otus.spring.spring13.service;

import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.acls.model.Sid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.otus.spring.spring13.domain.Author;
import ru.otus.spring.spring13.domain.Book;
import ru.otus.spring.spring13.domain.Genre;
import ru.otus.spring.spring13.dto.BookDto;
import ru.otus.spring.spring13.repository.AuthorRepository;
import ru.otus.spring.spring13.repository.BookRepository;
import ru.otus.spring.spring13.repository.GenreRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;
    private GenreRepository genreRepository;
    private AuthorRepository authorRepository;

    protected MutableAclService mutableAclService;

    BookServiceImpl(BookRepository bookRepository, GenreRepository genreRepository, AuthorRepository authorRepository, MutableAclService mutableAclService){
        this.bookRepository=bookRepository;
        this.authorRepository=authorRepository;
        this.genreRepository=genreRepository;
        this.mutableAclService=mutableAclService;
    }

    @Override
    public List<Book> getAll(){
        return bookRepository.findAll();
    }

    @Override
    public Book findById(int id){
        Book book=bookRepository.findById(id);
        return book;
    }

    @Override
    @Transactional
    public Book add(BookDto bookDto){
        Author author=authorRepository.findById(bookDto.getAuthor().getId()).get();
        Genre genre=genreRepository.findById(bookDto.getGenre().getId()).get();
        Book book=new Book();
        book.setName(bookDto.getName());
        book.setAuthor(author);
        book.setGenre(genre);
        Book newBook=bookRepository.save(book);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final Sid owner = new PrincipalSid( authentication );
        ObjectIdentity oid = new ObjectIdentityImpl( newBook.getClass(), newBook.getId() );

        final Sid admin = new GrantedAuthoritySid("ROLE_ADMIN");
        MutableAcl acl = mutableAclService.createAcl( oid );
        acl.setOwner( owner );
        acl.insertAce( acl.getEntries().size(), BasePermission.READ, admin, true );
        acl.insertAce( acl.getEntries().size(), BasePermission.WRITE, admin, true );
        mutableAclService.updateAcl( acl );

        return newBook;
    }


    @Override
    @Transactional
    public void update(BookDto bookDto){
        Author author=authorRepository.findById(bookDto.getAuthor().getId()).get();
        Genre genre=genreRepository.findById(bookDto.getGenre().getId()).get();
        Book book=bookRepository.findById(bookDto.getId());
        book.setName(bookDto.getName());
        book.setAuthor(author);
        book.setGenre(genre);
        bookRepository.save(book);
    }

    @Override
    @Transactional
    public void delete(int bookId){
        Book book=bookRepository.findById(bookId);
        bookRepository.delete(book);

    }
}
