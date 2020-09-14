package com.example.gamecenter.games.math24game.strategy;

public interface Iterator {

    // return true if the iteration has more elements
    public boolean hasNext();

    // return true if the iteration has more elements while traversing backward
    public boolean hasPrevious();

    //return the next item in the iteration
    public Object next();

    //return the previous item in the iteration
    public Object previous();

    //return the next element index or list size if the list iterator is at the end of the list
    public int nextIndex();

    //returns the previous element in the iteration and can throw NoSuchElementExeception if no more
    //element present
    public int previousIndex();

    //remove the element
    public void remove();

    //replaces the last element returned by next() or previous() with the specified element
    public void set(Object obj);

    //inserts the specified element into the list at position before the element that would be returned
    //by next()
    public void add(Object obj);

}
