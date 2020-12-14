import java.util.ArrayList;

public class Connections{
    Variable v;
    ArrayList<Variable> tiedWith;

    public Connections(Variable v){
        this.v = v;
        tiedWith = new ArrayList<Variable>();
    }

}