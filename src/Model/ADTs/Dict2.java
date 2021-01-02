package Model.ADTs;

import java.util.*;

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

    public void remove(T1 key){
        dict.remove(key);
    }

    public Dict2<T1, T2> copy(){
        Dict2<T1, T2> newMap = new Dict2<>();
        for(var key: dict.keySet()){
            newMap.dict.put(key, dict.get(key));
        }
        return newMap;
    }
    public void setContent(Map<T1, T2> newDict){
        dict.clear();
        for(var each: newDict.keySet()){
            dict.put(each, newDict.get(each));
        }
    }
    public HashMap<T1, T2> getContent(){
        return dict;
    }

}
