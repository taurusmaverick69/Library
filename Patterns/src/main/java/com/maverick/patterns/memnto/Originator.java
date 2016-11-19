package com.maverick.patterns.memnto;

import com.maverick.domain.Book;

public class Originator {

    private Book state;

    public Book getState() {
        return state;
    }

    public void setState(Book state) {
        this.state = state;
    }

    public Memento saveStateToMemento() {
        return new Memento(state);
    }

    public void getStateFromMemento(Memento memento) {
        state = memento.getState();
    }
}
