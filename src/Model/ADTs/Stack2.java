package Model.ADTs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;

public class Stack2<T> {
    private final Deque<T> innerStack;

    public Stack2() {
        this.innerStack = new ArrayDeque<T>() {
        };
    }

    public T peek(){
        return innerStack.peek();
    }
    public void push(T element){
        innerStack.push(element);
    }

    public T pop(){
        return innerStack.pop();
    }

    public int size() { return this.innerStack.size();}

    public Object[] getAll(){
        return innerStack.toArray();
    }

}
