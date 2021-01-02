package Model.Types;

public class TString implements Type{
    @Override
    public boolean equals(Object typo){
        return typo instanceof TString;
    }

    public String toString() {
        return "string";
    }
}
