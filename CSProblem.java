import java.util.ArrayList;

public class CSProblem{
    ArrayList<Variable> vList;
    ArrayList<Constraint> cList;

    public CSProblem(int numOfVar, int domainSize, double p1, double p2) throws Exception{

        if(p1 < 0 || p1 > 1 || p2 < 0.1 || p2 > 0.98){
            throw new RuntimeException("not valid input of p1 or p2");
        }

        this.vList = new ArrayList<Variable>();
        this.cList = new ArrayList<Constraint>();
        Double chance;

        for(int i = 0; i < numOfVar; i++){
            this.vList.add(new Variable(domainSize));
        }

        for(int i = 0; i < this.vList.size() - 1; i++){
            for(int j = i+1; j < this.vList.size(); j++){
                chance = Math.random();
                //chance of two variables to appear in cList
                if(chance < p1){
                    Variable v1 = this.vList.get(i);
                    Variable v2 = this.vList.get(j);
                    for(int k = 0; k < v1.domain.size(); k++){
                        for(int l = 0; l < v2.domain.size(); l++){
                            chance = Math.random();
                            //chance of two values of these variables to appear in cList
                            if(chance < p2){
                                String v1a = v1.domain.get(k);
                                String v2a = v2.domain.get(l);
                                this.cList.add(new Constraint(i, v1a, j, v2a));
                            }
                        }
                    }
                }
                
            }
        }

        
    }

    public CSProblem(){
        this.vList = new ArrayList<Variable>();
        this.cList = new ArrayList<Constraint>();
    }

    public ArrayList<String> getAsama(){
        ArrayList<String> asama = new ArrayList<String>();
        for(Variable v : this.vList){
            asama.add(v.asama);
        }
        return asama;
    }

    //---------------------toString functions------------------------//
    @Override
    public String toString(){
        String str = "";
        for(int i = 0; i < vList.size(); i++){
            str += Integer.toString(i + 1) + ": ";
            for(int j = 0; j < vList.get(i).domain.size(); j++){
                str += "" + vList.get(i).domain.get(j) + " ";
            }
            str += "\n";
        }
        str += "Constraints: \n";
        for(Constraint c : this.cList){
            str += "<" + (c.getFirstVar() + 1) + " = " + c.getfirstVarAsama() + ", " 
                       + (c.getSecondVar() + 1) + " = " + c.getSecondVarAsama() + ">";
            str += "\n";
        }
        return str;
    }

    public String toStringAsama(){
        String str = "\n";
        int index = 1;
        for(Variable v : vList){
            str += index + ": " + v.asama + "\n";
            index++;
        }
        return str;
    }

    public String toStringDomain(){
        String str = "";
        for(int i = 0; i < vList.size(); i++){
            str += Integer.toString(i + 1) + ": ";
            for(int j = 0; j < vList.get(i).domain.size(); j++){
                str += "" + vList.get(i).domain.get(j) + " ";
            }
            str += "\n";
        }
        return str;
    }
    //--------------------------------------------------------------//

    //actual copy of this problem
    public CSProblem toCopy() throws Exception{
        CSProblem p = new CSProblem();
        for(Variable v : this.vList){
            p.vList.add(new Variable(v.domain.size()));
        }
        for(Constraint c : this.cList){
            p.cList.add(new Constraint(c.getFirstVar(), c.getfirstVarAsama(), c.getSecondVar(), c.getSecondVarAsama()));
        }
        return p;
    }
}