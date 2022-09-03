import React, { useEffect, useState } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import {Link, useParams} from 'react-router-dom';
import BookList from "./BookList";

const CommentList = () => {
    const [comments, setComments] = useState([]);
    const [loading, setLoading] = useState(false);

    const { id } = useParams();

    useEffect(() => {
        setLoading(true);

        fetch(`/api/comments/${id}`)
            .then(response => response.json())
            .then(data => {
                setComments(data);
                setLoading(false);
            })
    }, [id]);

    const remove = async (commentId) => {
        await fetch(`/api/comment/${id}/${commentId}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedComments = [...comments].filter(i => i.id !== id);
            setComments(updatedComments);
        });
    }

    if (loading) {
        return <p>Loading...</p>;
    }

    const commentList = comments.map(comment => {
        return <tr key={comment.id}>
            <td style={{whiteSpace: 'nowrap'}}>{comment.data}</td>
            <td>
                <ButtonGroup>
                    <Button size="sm" color="primary" tag={Link} to={"/comments/"+id+"/" + comment.id}>Edit</Button>
                    <Button size="sm" color="danger" onClick={() => remove(comment.id)}>Delete</Button>
                </ButtonGroup>
            </td>
        </tr>
    });

    return (
        <div>
            <AppNavbar/>
            <Container fluid>
                <div className="float-end">
                    <Button color="success" tag={Link} to={"/comments/"+id+"/new"}>Add Comment</Button>
                </div>
                <h3>Comment list</h3>
                <Table className="mt-4">
                    <thead>
                    <tr>
                        <th width="90%">Comment</th>
                        <th width="10%">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    {commentList}
                    </tbody>
                </Table>
            </Container>
        </div>
    );


}

export default CommentList;
