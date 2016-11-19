package com.maverick.patterns.memnto;

import com.maverick.domain.Book;

public class Memento {

    private Book state;

    public Memento(Book state) {
        this.state = state;
    }

    public Book getState() {
        return state;
    }

}
