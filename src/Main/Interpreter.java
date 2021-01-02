package Main;

import Controller.Runner;
import Model.Expression.ReadHeap;
import Model.Expression.RelationalExpression;
import Model.Expression.ValueExpression;
import Model.Expression.VariableExpression;
import Model.Expression.arithmetic.Addition;
import Model.Expression.arithmetic.Multiplication;
import Model.Expression.arithmetic.Subtraction;
import Model.ProgramState.*;
import Model.Statem.*;
import Model.Types.TBool;
import Model.Types.TNumber;
import Model.Types.TRef;
import Model.Types.TString;
import Model.Values.Boolean;
import Model.Values.Integer;
import Model.Values.Ref;
import Model.Values.VString;
import Repository.Repo;
import View.ExitCommand;
import View.RunExample;
import View.TextMenu;

public class Interpreter {
    public static void main(String[] args){
        ExecutionStack executionStack = new ExecutionStack();
        SymbolTable symbolTable = new SymbolTable();
        OutputStream output = new OutputStream();
        FileTable fileTable = new FileTable();
        Heap heap = new Heap();
        Statement state1 = fileTest();
        ProgramState programState1 = new ProgramState(executionStack, symbolTable, output, fileTable,heap, state1);
        Repo repository1 = new Repo("log1.txt");
        repository1.addProgram(programState1);
        Runner controller1 = new Runner(repository1);

        Statement state2 = example2();
        ProgramState programState2 = new ProgramState(new ExecutionStack(), new SymbolTable(), new OutputStream(), new FileTable(), new Heap(), state2);
        Repo repository2 = new Repo("log2.txt");
        repository2.addProgram(programState2);
        Runner controller2 = new Runner(repository2);

        Statement state3 = example3();
        ProgramState programState3 = new ProgramState(new ExecutionStack(), new SymbolTable(), new OutputStream(), new FileTable(),new Heap(), state3);
        Repo repository3 = new Repo("log3.txt");
        repository3.addProgram(programState3);
        Runner controller3 = new Runner(repository3);

        Statement state4 = relation2();
        ProgramState programState4 =  new ProgramState(new ExecutionStack(), new SymbolTable(), new OutputStream(), new FileTable(), new Heap(), state4);
        Repo repository4 = new Repo("log4.txt");
        repository4.addProgram(programState4);
        Runner controller4 = new Runner(repository4);

        Statement state5 = ref();
        ProgramState programState5 =  new ProgramState(new ExecutionStack(), new SymbolTable(), new OutputStream(), new FileTable(), new Heap(), state5);
        Repo repository5 = new Repo("log5.txt");
        repository5.addProgram(programState5);
        Runner controller5 = new Runner(repository5);

        Statement state6 = readHeap();
        ProgramState programState6 =  new ProgramState(new ExecutionStack(), new SymbolTable(), new OutputStream(), new FileTable(), new Heap(), state6);
        Repo repository6 = new Repo("log6.txt");
        repository6.addProgram(programState6);
        Runner controller6 = new Runner(repository6);

        Statement state7 = writeHeap();
        ProgramState programState7 = new ProgramState(new ExecutionStack(), new SymbolTable(), new OutputStream(), new FileTable(), new Heap(), state7);
        Repo repository7 = new Repo("log7.txt");
        repository7.addProgram(programState7);
        Runner controller7 = new Runner(repository7);

        Statement state8 = gar();
        ProgramState programState8 = new ProgramState(new ExecutionStack(), new SymbolTable(), new OutputStream(), new FileTable(), new Heap(), state8);
        Repo repository8 = new Repo("log8.txt");
        repository8.addProgram(programState8);
        Runner controller8 = new Runner(repository8);

        Statement state9 = whil();
        ProgramState programState9 = new ProgramState(new ExecutionStack(), new SymbolTable(), new OutputStream(), new FileTable(), new Heap(), state9);
        Repo repository9 = new Repo("log9.txt");
        repository9.addProgram(programState9);
        Runner controller9 = new Runner(repository9);

        Statement state10 = thr2();
        ProgramState programState10 = new ProgramState(new ExecutionStack(), new SymbolTable(), new OutputStream(), new FileTable(), new Heap(), state10);
        Repo repository10 = new Repo("log10.txt");
        repository10.addProgram(programState10);
        Runner controller10 = new Runner(repository10);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", state1.toString(), controller1));
        menu.addCommand(new RunExample("2", state2.toString(), controller2));
        menu.addCommand(new RunExample("3", state3.toString(), controller3));
        menu.addCommand(new RunExample("4", state4.toString(), controller4));
        menu.addCommand(new RunExample("5", state5.toString(), controller5));
        menu.addCommand(new RunExample("6", state6.toString(), controller6));
        menu.addCommand(new RunExample("7", state7.toString(), controller7));
        menu.addCommand(new RunExample("8", state8.toString(), controller8));
        menu.addCommand(new RunExample("9", state9.toString(), controller9));
        menu.addCommand(new RunExample("10", state10.toString(), controller10));
        menu.show();
    }

    public static Statement thr(){
        return new Compound(new VariableDeclaration("v", new TNumber()),
                new Compound(new VariableDeclaration("a", new TRef(new TNumber())),
                        new Compound(new Assignment("v", new ValueExpression(new Integer(10))),
                                new Compound(new New("a", new ValueExpression(new Integer(22))),
                                        new Compound(
                                                new Fork(new Compound( new WriteHeap("a", new ValueExpression(new Integer(30))),
                                                        new Compound(new Assignment("v", new ValueExpression(new Integer(30))),
                                                                new Compound(new Print(new VariableExpression("v")),
                                                                        new Print(new ReadHeap(new VariableExpression("a"))))
                                                                ))),
                                                new Compound(new Print(new VariableExpression("v")),
                                                        new Print(new ReadHeap(new VariableExpression("a"))))
                                        )))));
    }
    public static Statement thr2(){
        return new Compound(
                new VariableDeclaration(
                        "v",
                        new TNumber()
                ),
                new Compound(
                        new VariableDeclaration(
                                "a",
                                new TRef(
                                        new TNumber()
                                )
                        ),
                        new Compound(
                                new Assignment(
                                        "v",
                                        new ValueExpression(
                                                new Integer(10)
                                        )
                                ),
                                new Compound(
                                        new New(
                                                "a",
                                                new ValueExpression(
                                                        new Integer(22)
                                                )
                                        ),
                                        new Compound(
                                                new Fork(
                                                        new Compound(
                                                                new WriteHeap(
                                                                        "a",
                                                                        new ValueExpression(
                                                                                new Integer(30)
                                                                        )
                                                                ),
                                                                new Compound(
                                                                        new Assignment(
                                                                                "v",
                                                                                new ValueExpression(
                                                                                        new Integer(32)
                                                                                )
                                                                        ),
                                                                        new Compound(
                                                                                new Print(
                                                                                        new VariableExpression("v")
                                                                                ),
                                                                                new Print(
                                                                                        new ReadHeap(
                                                                                                new VariableExpression(
                                                                                                        "a"
                                                                                                )
                                                                                        )
                                                                                )
                                                                        )
                                                                )
                                                        )
                                                ),
                                                new Compound(
                                                        new Print(
                                                                new VariableExpression(
                                                                        "v"
                                                                )
                                                        ),
                                                        new Print(
                                                                new ReadHeap(
                                                                        new VariableExpression("a")
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );
    }
    public static Statement whil(){
        return new Compound(new VariableDeclaration("v", new TNumber()),
                new Compound(new Assignment("v", new ValueExpression(new Integer(4))),
                        new Compound(new While(new RelationalExpression(new VariableExpression("v"), new ValueExpression(new Integer(0)), ">"),
                                new Compound(new Print(new VariableExpression("v")), new Assignment("v", new Subtraction(
                                        new VariableExpression("v"), new ValueExpression(new Integer(1)))))),
                                new Print(new VariableExpression("v")))));
    }
    public static Statement gar(){
        return new Compound(new VariableDeclaration("v", new TRef(new TNumber())),
                new Compound(new New("v", new ValueExpression(new Integer(20))),
                        new Compound(new VariableDeclaration("a", new TRef(new TRef(new TNumber()))),
                                new Compound(new New("a", new VariableExpression("v")),
                                        new Compound(new New("v", new ValueExpression(new Integer(30))),
                                                new Print(new ReadHeap(new ReadHeap(new VariableExpression("a")))))))));
    }
    public static Statement writeHeap(){
        return new Compound(new VariableDeclaration("v", new TRef(new TNumber())),
                new Compound(new New("v", new ValueExpression(new Integer(20))),
                        new Compound(new Print(new ReadHeap(new VariableExpression("v"))),
                                new Compound(new WriteHeap("v", new ValueExpression(new Integer(30))),
                                        new Print(new Addition(new ReadHeap(new VariableExpression("v")), new ValueExpression(new Integer(5))))))));
    }

    public static Statement readHeap(){
        return new Compound(new VariableDeclaration("v", new TRef(new TNumber())),
                new Compound(new New("v", new ValueExpression(new Integer(20))),
                        new Compound(new VariableDeclaration("a", new TRef(new TRef(new TNumber()))),
                                new Compound(new New("a", new VariableExpression("v")),
                                        new Compound(new Print(new ReadHeap(new VariableExpression("a"))),
                                                new Print(new ReadHeap(new VariableExpression("v"))))))));
    }

    public static Statement ref(){
        return new Compound(new VariableDeclaration("v", new TRef(new TNumber())),
                new Compound(new New("v", new ValueExpression(new Integer(20))),
                        new Compound(new VariableDeclaration("a", new TRef(new TRef(new TNumber()))),
                                new Compound(new New("a", new VariableExpression("v")),
                                        new Compound(new Print(new VariableExpression("a")),
                                                new Print(new VariableExpression("v")))))));

    }
    public static Statement relational(){
        return new Compound(new VariableDeclaration("x", new TBool()),
                new Compound(new Assignment("x", new RelationalExpression(new ValueExpression(new Boolean(false)),
                new ValueExpression(new Boolean(true)), "==")), new Print(new VariableExpression("x"))));
    }
    public static Statement relation2(){
        return new Compound(new VariableDeclaration("x", new TNumber()),
                new Compound(new Assignment("x", new ValueExpression(new Integer(2))), new Print(new RelationalExpression(new VariableExpression("x"),
                        new ValueExpression(new Integer(2)), "=="))));
    }
    public static Statement example2(){
        return new Compound(new VariableDeclaration("v",new TNumber()),
                new Compound(new Assignment("v", new ValueExpression(new Integer(2))),
                        new Print(new VariableExpression("v"))));
    }
    public static Statement example3(){
        return new Compound(new VariableDeclaration("a", new TNumber()),
                new Compound(new VariableDeclaration("b", new TNumber()),
                        new Compound(new Assignment("a",
                                new Addition(new ValueExpression(new Integer(2)),
                                        new Multiplication(new ValueExpression(new Integer(3)),
                                                new ValueExpression(new Integer(5))))),
                                new Compound(new Assignment("b",
                                        new Addition(new VariableExpression("a"), new ValueExpression(new Integer(1)))),
                                        new Print(new VariableExpression("b"))))));
    }

    public static Statement fileTest(){
        return new Compound(new VariableDeclaration("varf", new TString()),
                new Compound(new Assignment("varf", new ValueExpression(new VString("test.in"))),
                        new Compound(new VariableDeclaration("varc", new TNumber()),
                                new Compound(new OpenReadFile(new VariableExpression("varf")),
                                        new Compound(new ReadFile(new VariableExpression("varf"), "varc"),
                                                new Compound(new Print(new VariableExpression("varc")),
                                                        new Compound(new ReadFile(new VariableExpression("varf"),"varc"),
                                                                new Compound(new Print(new VariableExpression("varc")),
                                                                        new CloseReadFile(new VariableExpression("varf"))))))))));
    }


}
