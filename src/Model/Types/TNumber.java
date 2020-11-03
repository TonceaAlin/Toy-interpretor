package Model.Types;

public class TNumber implements Type {
    @Override
    public boolean equals(Object typo) {
        return typo instanceof TNumber;
    }

    public String toString(){return "integer";}
}
