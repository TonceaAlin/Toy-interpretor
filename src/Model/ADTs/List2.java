package Model.ADTs;

import java.util.ArrayList;
import java.util.Iterator;

public class List2<T> implements Iterable<T>{
    private final ArrayList<T> list;

    public List2(){

        this.list = new ArrayList<T>();
    }


    public int size(){
        return list.size();
    }
    public void add(T element){
        list.add(element);
    }
    public T pop(){
        return list.get(list.size()-1);
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}
