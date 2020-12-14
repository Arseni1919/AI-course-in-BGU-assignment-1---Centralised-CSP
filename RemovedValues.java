

public class RemovedValues{
    private int varIndex;
    private String value;

    public RemovedValues(int varIndex, String value){
        this.varIndex = varIndex;
        this.value = value;
    }

    public int getVarIndex(){
        return this.varIndex;
    }

    public String getValue(){
        return this.value;
    }
}