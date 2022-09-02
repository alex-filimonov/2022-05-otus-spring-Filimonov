import React, { useEffect, useState } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import {Button, ButtonGroup, Container, Form, FormGroup, Input, Label} from 'reactstrap';
import AppNavbar from './AppNavbar';
import BookEdit from "./BookEdit";

const CommentEdit = () => {
    const initialFormState = {
        data: ''
    };

    const [comment, setComment] = useState(initialFormState);
    const navigate = useNavigate();
    const { bookId, id } = useParams();

    useEffect(() => {
        if (id !== 'new') {
            fetch(`/api/comment/${id}`)
                .then(response => response.json())
                .then(data => setComment(data));
        }
    }, [id, setComment]);

    const title = <h2>{comment.id ? 'Edit Comment' : 'Add Comment'}</h2>;

    const handleChange = (event) => {
        const { name, value } = event.target
        setComment({...comment, [name]: value})

    }

    const handleSubmit = async (event) => {
        event.preventDefault();
        await fetch('/api/comment/'+bookId+'/' + (comment.id ? '/' + comment.id : ''), {
            method: (comment.id) ? 'PUT' : 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(comment)
        });
        setComment(initialFormState);
        navigate('/commentList/'+bookId);
    }



    return (<div>
            <AppNavbar/>
            <Container>
                {title}
                <Form onSubmit={handleSubmit}>
                    <FormGroup>
                        <Label for="data">Comment</Label>
                        <Input type="text" name="data" id="data" value={comment.data || ''}
                               onChange={handleChange} autoComplete="data"/>
                    </FormGroup>
                    <FormGroup>
                        <Button color="primary" type="submit">Save</Button>{' '}
                        <Button color="secondary" tag={Link} to={"/commentList/"+bookId}>Cancel</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    )
};


export default CommentEdit;