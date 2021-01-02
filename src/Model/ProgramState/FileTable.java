package Model.ProgramState;

import Model.ADTs.Dict2;
import Model.Types.TString;
import Model.Values.VString;

import java.io.BufferedReader;

public class FileTable {

    private final Dict2<VString, BufferedReader> fileTable;

    public FileTable() {
        this.fileTable = new Dict2<VString, BufferedReader>();
    }

    public String toString(){
        return "file";
    }
    public void add(VString filename, BufferedReader file){
        fileTable.set(filename, file);
    }

    public String toFile(){
        String result = "File table: ";
        for(var each: fileTable.getAll()){
            result += "\n" + each.toString();
        }
        return result;
    }
    public boolean defined(VString id){
        return fileTable.contains(id);
    }

    public BufferedReader get(VString id){
        return fileTable.get(id);
    }

    public void remove(VString id){
        fileTable.remove(id);
    }
}
