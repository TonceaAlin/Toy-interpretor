package Model.ProgramState;

import Model.ADTs.List2;
import Model.Values.Value;

import java.util.ArrayList;

public class OutputStream {
    private final List2<Value>output;

    public OutputStream() {
        this.output = new List2<Value>();
    }

    public void print(Value value){
        output.add(value);
    }

    public String toString(){
        String values = "";
        for(var each: output)
            values += each.toString();
        return values;
    }

    public String toFile(){
        String result = " Output: ";
        for (var element: output.getAll()){
            result += "\n" + element;
        }
        return result;
    }
}
