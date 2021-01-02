package Model.Statem;

import Model.ADTs.Dict2;
import Model.Exceptions.TypeException;
import Model.ProgramState.ProgramState;
import Model.Types.Type;

public class Nop implements Statement{
    public Nop(){

    }
    @Override
    public ProgramState evaluate(ProgramState state) throws RuntimeException {

        return null;
    }

    @Override
    public Dict2<String, Type> typeChecker(Dict2<String, Type> typeEnv) throws TypeException {
        return typeEnv;
    }
}
