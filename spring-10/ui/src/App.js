import React from 'react';
import './App.css';
import Home from './Home';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import BookList from "./BookList";
import BookEdit from "./BookEdit";
import CommentList from "./CommentList";
import CommentEdit from "./CommentEdit";

const App = () => {
    return (
        <Router>
            <Routes>
                <Route exact path="/" element={<Home/>}/>
                <Route path='/books' exact={true} element={<BookList/>}/>
                <Route path='/books/:id' element={<BookEdit/>}/>
                <Route path='/commentList/:id' exact={true} element={<CommentList/>} />
                <Route path='/comments/:bookId/:id' element={<CommentEdit/>}/>
            </Routes>
        </Router>
    )
}

export default App;