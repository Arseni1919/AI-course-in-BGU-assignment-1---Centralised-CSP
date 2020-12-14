import java.util.ArrayList;

public class Variable{

    ArrayList<String> domain;
    String asama;

    public Variable(int domainSize){
        this.asama = "";
        this.domain = new ArrayList<String>();
        
        for(int i = 0; i < domainSize; i++){
            this.domain.add(Character.toString((char)(97+i)));
        }
        //for(int i = 0; i < domainSize; i++){
        //    System.out.print(domain.get(i));
        //}
    }

    public Variable(String str){
        this.domain = new ArrayList<String>();
        this.domain.add(str);
        this.asama = "";
    }
    
//----------------------------GETTERS----------------------------//
//----------------------------GETTERS----------------------------//
//---------------------------------------------------------------//
//---------------------------------------------------------------//
//---------------------------------------------------------------//
//---------------------------------------------------------------//
}