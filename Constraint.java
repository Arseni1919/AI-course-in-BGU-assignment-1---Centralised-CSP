

public class Constraint{
    private int firstVar;
    private String firstVarAsama;
    private int secondVar;
    private String secondVarAsama;

    public Constraint(int v1, String v1a, int v2, String v2a){
        this.firstVar = v1;
        this.firstVarAsama = v1a;
        this.secondVar = v2;
        this.secondVarAsama = v2a;
    }

//----------------------------GETTERS----------------------------//
    public int getFirstVar(){
        return this.firstVar;
    }

    public String getfirstVarAsama(){
        return this.firstVarAsama;
    }

    public int getSecondVar(){
        return this.secondVar;
    }

    public String getSecondVarAsama(){
        return this.secondVarAsama;
    }

//----------------------------GETTERS----------------------------//
//---------------------------------------------------------------//
//---------------------------------------------------------------//
//---------------------------------------------------------------//
//---------------------------------------------------------------//
}