import Controller.Runner;
import Model.Expression.ValueExpression;
import Model.Expression.VariableExpression;
import Model.Expression.arithmetic.Addition;
import Model.Expression.arithmetic.Division;
import Model.Expression.arithmetic.Multiplication;
import Model.ProgramState.ExecutionStack;
import Model.ProgramState.OutputStream;
import Model.ProgramState.ProgramState;
import Model.ProgramState.SymbolTable;
import Model.Statem.*;
import Model.Types.TBool;
import Model.Types.TNumber;
import Model.Values.Boolean;
import Model.Values.Integer;
import Model.Values.Value;

import java.util.Scanner;

public class View {

    public static void main(String[] args){
        runProgram();
    }
    public static void printMenu(){
        String menu = "";
        menu += "1.int v; v=2; print(v);\n";
        menu += "2.int a;int b; a=2+3*5; b=a+1; print(b);\n";
        menu += "3.bool a; int v; a=true; if a then v=2 else v=3; print(v);\n";
        menu += "4.Division by zero exception\n";
        menu += "0. Exit\n";
        System.out.println(menu);
    }
    public static void runProgram(){
        printMenu();
        while(true){
            System.out.println(">>>");
            Scanner in = new Scanner(System.in);
            int userOption = java.lang.Integer.parseInt(in.nextLine());
            Statement stateR = null;
            if(userOption == 0){
                break;
            }else if(userOption == 1){
                stateR = new Compound(new VariableDeclaration("v",new TNumber()),
                        new Compound(new Assignment("v", new ValueExpression(new Integer(2))),
                                new Print(new VariableExpression("v"))));
            }else if(userOption == 2){
                stateR = new Compound(new VariableDeclaration("a", new TNumber()),
                        new Compound(new VariableDeclaration("b", new TNumber()),
                                new Compound(new Assignment("a",
                                        new Addition(new ValueExpression(new Integer(2)),
                                                new Multiplication(new ValueExpression(new Integer(3)),
                                                        new ValueExpression(new Integer(5))))),
                                        new Compound(new Assignment("b",
                                                new Addition(new VariableExpression("a"), new ValueExpression(new Integer(1)))),
                                                new Print(new VariableExpression("b"))))));
            }else if(userOption == 3){
                 stateR = new Compound(new VariableDeclaration("a", new TBool()),
                        new Compound(new VariableDeclaration("v", new TNumber()),
                                new Compound(new Assignment("a", new ValueExpression(new Boolean(true))),
                                        new Compound(
                                                new If(new VariableExpression("a"),
                                                        new Assignment("v", new ValueExpression(new Integer(2))),
                                                        new Assignment("v", new ValueExpression(new Integer(3)))),
                                                new Print(new VariableExpression("v"))))));
            }
            else if(userOption == 4){
                 stateR = new Compound(
                        new VariableDeclaration("a",new TNumber()),
                        new Compound(
                                new VariableDeclaration("b", new TNumber()),
                                new Compound(
                                        new Assignment(
                                                "a",
                                                new Addition(
                                                        new ValueExpression(new Integer(2)),
                                                        new Division(
                                                                new ValueExpression(new Integer(3)),
                                                                new ValueExpression(new Integer(0))))),
                                        new Compound(
                                                new Assignment(
                                                        "b",
                                                        new Addition(
                                                                new VariableExpression("a"),
                                                                new ValueExpression(new Integer(1)))),
                                                new Print(new VariableExpression("b"))))));
            }
            ExecutionStack stack = new ExecutionStack();
            SymbolTable symbolTable = new SymbolTable();
            OutputStream output = new OutputStream();
            ProgramState state = new ProgramState(stack, symbolTable, output, stateR);
            Runner run = new Runner();

            try{
                run.allSteps(state);
                //System.out.println(state.toString());
                System.out.println(state.getOutput().toString());
            }catch(RuntimeException exception){
                System.out.println(exception.getMessage());
            }
        }
    }
}
