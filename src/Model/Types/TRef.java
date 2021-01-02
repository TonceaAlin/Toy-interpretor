package Model.Types;

import Model.Values.Ref;
import Model.Values.Value;

public class TRef implements Type {
    private final Type inner;

    public TRef(Type inner) {
        this.inner = inner;
    }

    public Type getInner() {
        return inner;
    }

    @Override
    public boolean equals(Object obj) {
        if( obj instanceof TRef){
            return inner.equals(((TRef) obj).getInner());
        }else{
            return false;
        }
    }
    public Value getDefault(){
        return new Ref(0, inner);
    }
    public String toString(){
        return "Ref(" + inner.toString() + ")";
    }
}
