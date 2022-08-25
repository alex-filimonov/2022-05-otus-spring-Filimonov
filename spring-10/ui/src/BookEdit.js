import React, { useEffect, useState } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import {Button, ButtonGroup, Container, Form, FormGroup, Input, Label} from 'reactstrap';
import AppNavbar from './AppNavbar';

const BookEdit = () => {
    const initialFormState = {
        name: '',
        author: { id: 0},
        genre: {id: 0}
    };
    const [book, setBook] = useState(initialFormState);
    const navigate = useNavigate();
    const { id } = useParams();

    useEffect(() => {
        if (id !== 'new') {
            fetch(`/api/book/${id}`)
                .then(response => response.json())
                .then(data => setBook(data));
        }
    }, [id, setBook]);

    const handleChange = (event) => {
        const { name, value } = event.target
        setBook({...book, [name]: value})
    }

    const authorSelect= (event) => {
        setBook({...book, author:{ id:event.target.value} });
    }
    const [authors, setAuthors] = useState([]);
    useEffect(() => {

        fetch('/api/authors')
            .then(response => response.json())
            .then(data => {
                setAuthors(data);
            })
    }, []);

    const [genres, setGenres] = useState([]);
    useEffect(() => {
        fetch('/api/genres')
            .then(response => response.json())
            .then(data => {
                setGenres(data);
            })
    }, []);
    const genreSelect= (event) => {
        setBook({...book, genre:{ id:event.target.value} });
    }

    const handleSubmit = async (event) => {
        event.preventDefault();

        await fetch('/api/book' + (book.id ? '/' + book.id : ''), {
            method: (book.id) ? 'PUT' : 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(book)
        });
        setBook(initialFormState);
        navigate('/books');
    }

    const title = <h2>{book.id ? 'Edit Book' : 'Add Book'}</h2>;

    const authorSelectList = authors.map(author => {
        return <option value={author.id}>{author.name}</option>
    });

    const genreSelectList = genres.map(genre => {
        return <option value={genre.id}>{genre.name}</option>
    });

    return (<div>
            <AppNavbar/>
            <Container>
                {title}
                <Form onSubmit={handleSubmit}>
                    <FormGroup>
                        <Label for="name">Name</Label>
                        <Input type="text" name="name" id="name" value={book.name || ''}
                               onChange={handleChange} autoComplete="name"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="author">Author</Label>
                        <Input type={"select"}
                               name={"author.id"}
                               id={"author.id"}
                               size="1"
                               value={book.author.id}
                               onChange={authorSelect}>
                            {authorSelectList}
                        </Input>
                    </FormGroup>
                    <FormGroup>
                        <Label for="genre">Genre</Label>
                        <Input type={"select"} name="genre.id" id="genre.id"
                               value={book.genre.id}
                               onChange={genreSelect}>
                            {genreSelectList}
                        </Input>
                    </FormGroup>
                    <FormGroup>
                        <Button color="primary" type="submit">Save</Button>{' '}
                        <Button color="secondary" tag={Link} to="/books">Cancel</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    )
};

export default BookEdit;