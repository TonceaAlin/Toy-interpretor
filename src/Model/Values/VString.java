package Model.Values;

import Model.Types.TString;
import Model.Types.Type;

public class VString implements Value{
    private final String value;

    public VString(String value) {
        this.value = value;
    }

    public String toString(){
        return this.value;
    }
    @Override
    public Type getType() {
        return new TString();
    }

    public String getValue(){
        return this.value;
    }

}
