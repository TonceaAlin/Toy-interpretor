package Model.Values;

import Model.Types.TNumber;
import Model.Types.Type;

public class Integer implements Value {
    int value;
    public Integer(int val){
        this.value = val;
    }

    public int getValue(){
        return this.value;
    }


    @Override
    public Type getType() {
        return new TNumber();
    }

    @Override
    public String toString() {
        return String.valueOf(value) + " ";
    }
}
