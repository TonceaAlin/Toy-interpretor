package Model.Values;

import Model.Types.TBool;
import Model.Types.Type;

public class Boolean implements Value{

    private final boolean value;

    public Boolean(boolean value) {
        this.value = value;
    }

    @Override
    public Type getType() {
        return new TBool();
    }


    public boolean getValue() {
        return value;
    }

    public String toString(){
        return String.valueOf(value);
    }
}
