import React from 'react'

const styles = {
    booksTable: {
        border: "1px solid steelblue",
        width: "100%",
        borderCollapse: "collapse",
    },

    booksTableItem: {
        padding: "5px",
        border: "1px solid steelblue"
    }
}

const Header = (props) => (
    <h1>{props.title}</h1>
);

export default class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {books: []};
    }

    componentDidMount() {
        fetch('/api/books')
            .then(response => response.json())
            .then(books => this.setState({books}));
    }

    render() {
        return (
            <React.Fragment>
                <Header title={'Book list'}/>
                <table style={styles.booksTable}>
                    <thead>
                    <tr style={styles.booksTableItem}>
                        <th style={styles.booksTableItem}>ID</th>
                        <th style={styles.booksTableItem}>Name</th>
                        <th style={styles.booksTableItem}>Author</th>
                        <th style={styles.booksTableItem}>Genre</th>
                        <th style={styles.booksTableItem}>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        this.state.books.map((book, i) => (
                            <tr style={styles.booksTableItem} key={i}>
                                <td style={styles.booksTableItem}>{book.id}</td>
                                <td style={styles.booksTableItem}>{book.name}</td>
                                <td style={styles.booksTableItem}>{book.author.name}</td>
                                <td style={styles.booksTableItem}>{book.genre.name}</td>
                                <td style={styles.booksTableItem}>...</td>
                            </tr>
                        ))
                    }
                    </tbody>
                </table>
            </React.Fragment>
        )
    }
};