package Model.Statem;

import Model.ADTs.Dict2;
import Model.Exceptions.TypeException;
import Model.ProgramState.ProgramState;
import Model.Types.Type;

public interface Statement {
        ProgramState evaluate(ProgramState state) throws RuntimeException;
        Dict2<String, Type>typeChecker(Dict2<String, Type> typeEnv) throws TypeException;
}
