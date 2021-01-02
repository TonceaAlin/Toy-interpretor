package Model.Values;

import Model.Types.TRef;
import Model.Types.Type;

public class Ref implements Value{
    private final int address;
    private final Type locationType;

    public Ref(int address, Type locationType) {
        this.address = address;
        this.locationType = locationType;
    }

    @Override
    public Type getType() {
        return new TRef(locationType);
    }
    public String toString(){
        return "Ref(" + String.valueOf(address) + ", "  + locationType.toString() + ")";
    }
    public int getAddress() {
        return address;
    }

    public Type getLocationType() {
        return locationType;
    }


}
