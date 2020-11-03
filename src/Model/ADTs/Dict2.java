package Model.ADTs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Dict2<T1,T2> {
    private final HashMap<T1,T2> dict;

    public boolean contains(T1 key){
        return dict.containsKey(key);
    }

    public T2 get(T1 key){
        return dict.get(key);
    }

    public void set(T1 key, T2 value){
        dict.put(key, value);
    }

    public Dict2() {
        dict = new HashMap<T1, T2>();
    }

    public Set getAll(){
        return dict.keySet();
    }
}
