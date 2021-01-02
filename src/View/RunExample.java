package View;

import Controller.Runner;

public class RunExample extends Command {
    private Runner controller;

    public RunExample(String k, String d, Runner controller) {
        super(k, d);
        this.controller = controller;
    }

    @Override
    public void execute() {
        try{
            controller.typeCheck();
            controller.allSteps();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
