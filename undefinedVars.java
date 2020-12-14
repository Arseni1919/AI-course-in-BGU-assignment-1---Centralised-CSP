import java.util.ArrayList;

public class undefinedVars{
    private int varIndex;
    ArrayList<RemovedValues> remValues;

    public undefinedVars(int varIndex){
        this.varIndex = varIndex;
        this.remValues = new ArrayList<RemovedValues>();
    }

    public int getVarIndex(){
        return this.varIndex;
    }
}