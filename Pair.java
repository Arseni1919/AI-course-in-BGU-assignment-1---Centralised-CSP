

public class Pair{
    private Variable v1;
    private Variable v2;

    public Pair(Variable v1, Variable v2){
        this.v1 = v1;
        this.v2 = v2;
    }

    public Variable getV1(){
        return this.v1;
    }

    public Variable getV2(){
        return this.v2;
    }
}