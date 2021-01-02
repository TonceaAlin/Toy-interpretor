package Model.ProgramState;

import Model.ADTs.Dict2;
import Model.Values.Value;

import java.util.Map;

public class Heap {
    private final Dict2<Integer, Value> heap;

    public Heap() {
        this.heap = new Dict2<>();
    }
    public boolean defined(Integer id){
        return this.heap.contains(id);
    }

    public Map<Integer, Value> getContent(){
        return heap.getContent();
    }
    public void setContent(Map<Integer, Value> newDict){
        heap.setContent(newDict);
    }
    public void set(Integer id, Value value){
        heap.set(id, value);
    }
    public String toString(){
        String all= "";
        var elements = heap.getAll();
        for(var each : elements){
            all += each.toString() +  " -> " + heap.get((Integer) each).toString();
            all += ", ";
        }
        return all;
    }

    public Value get(Integer id){
        return heap.get(id);
    }

    public String toFile(){
        String result = "Heap: ";
        for(var each: heap.getAll()){
            result += "\n" + each.toString() +  " -> " + heap.get((Integer) each).toString();
        }
        return result;
    }
}
