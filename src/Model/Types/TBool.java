package Model.Types;

public class TBool implements Type{
    @Override
    public boolean equals(Object typo){
        return typo instanceof TBool;
    }

    public String toString(){return "boolean";}
}
