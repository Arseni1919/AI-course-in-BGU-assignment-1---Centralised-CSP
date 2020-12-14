/*
Each algorithm has following parts:
1. temporary variables - inside main function
2. main process
3. help functions - outside main function
*/
import java.util.ArrayList;

public class Algorithms{
    //--------------------------------------------------------//
    //--------------------------------------------------------//
    //--------------------------------------------------------//
    //--------------------------------------------------------//
    //--------------------------FC----------------------------//
    //--------------------------------------------------------//
    //--------------------------------------------------------//
    //--------------------------------------------------------//
    //--------------------------------------------------------//

    public static Answer forwardChecking(CSProblem problem){
        //--------TEMPORARY VARIABLES--------//
        Variable currVar;
        int currIndex;
        ArrayList<undefinedVars> memoryList = new ArrayList<undefinedVars>();//remembers all removed values
        for(int i = 0; i < problem.vList.size(); i++){
            memoryList.add(new undefinedVars(i));
        }
        ArrayList<Variable> inside = new ArrayList<Variable>();
        ArrayList<Variable> outside = new ArrayList<Variable>();
        for(Variable v : problem.vList){
            outside.add(v);
        }
        int cc = 0;
        //------------------------------------//

        //------------------------------------//
        //----LOOPING UNTIL GETTING ANSWER----//
        //------------------------------------//
        while(inside.size() != problem.vList.size()){ // outside loop of variables with asama 
            currVar = problem.vList.get(failFirst(problem.vList, outside));//getting var with the smallest domain
            currIndex = problem.vList.indexOf(currVar);
            
            currVar.asama = currVar.domain.get(0);
            currVar.domain.remove(0);
            
            inside.add(currVar);
            outside.remove(currVar);

            //------------------------
            //----FORWARD CHECHING----
            //------------------------
            String si = currVar.asama;
            for(Variable v : outside){//per each value ofeach variable there is cheching constraint
                int currIndexOfV = problem.vList.indexOf(v);
                
                for(int j = 0; j < v.domain.size(); j++){
                    String sj = v.domain.get(j);
                    //Constraint check
                    //-----------------------------------------------------------------------------
                    for(Constraint c : problem.cList){
                        
                        if(c.getFirstVar() == currIndex && c.getfirstVarAsama().equals(si)){
                            if(c.getSecondVar() == currIndexOfV && c.getSecondVarAsama().equals(sj)){
                                memoryList.get(currIndex).remValues.add(new RemovedValues(currIndexOfV, sj));
                                v.domain.remove(sj); 
                                j--;
                                break;
                            }
                        } 
                        if(c.getSecondVar() == currIndex && c.getSecondVarAsama().equals(si)){
                            if(c.getFirstVar() == currIndexOfV && c.getfirstVarAsama().equals(sj)){
                                memoryList.get(currIndex).remValues.add(new RemovedValues(currIndexOfV, sj));
                                v.domain.remove(sj); 
                                j--;
                                break;
                            }
                        }  
                    }
                    //-----------------------------------------------------------------------------
                    cc++;
                } 
            }
            //------------------------
            //------------------------
            //------------------------

            //--------------------------------
            //-----CHECKING FOR EMPTY DOMAINS-
            //--------------------------------
            
            if(thereIsEnptyDomain(outside)){
                if(inside.get(0).domain.isEmpty()){
                    //System.out.println("FC:: There is NO solution! ");//Printing[][][][][]
                    return new Answer(cc, new ArrayList<String>());
                }
                bringBackAllValues(problem.vList, memoryList, inside, outside);
            }

            //--------------------------------
            //--------------------------------
            //--------------------------------
        }
        //------------------------------------//
        //------------------------------------//
        //------------------------------------//
        return new Answer(cc, problem.getAsama());
    }

    //-----------------------HELP FUNCTIONS--------------------------//

    //brings the index of variable with smallest domain (first of them)
    public static int failFirst(ArrayList<Variable> vList, ArrayList<Variable> outside){
        int index = vList.indexOf(outside.get(0));
        int min = outside.get(0).domain.size();
        for(Variable v : outside){
            if(v.domain.size() < min){
                index = vList.indexOf(v);
                min = v.domain.size();
            }
        }
        return index;
    }

    //checks for empty domains in "outside" list
    public static boolean thereIsEnptyDomain(ArrayList<Variable> outside){
        if(outside.isEmpty()){
            return false;
        }
        for(Variable v : outside){
            if(v.domain.isEmpty()){
                return true;
            }
        }
        return false;
    }

    //bringing back all removed values using memoryList
    public static void bringBackAllValues(ArrayList<Variable> vList, 
                                         ArrayList<undefinedVars> memoryList, 
                                         ArrayList<Variable> inside, 
                                         ArrayList<Variable> outside){
        
        RemovedValues rv;
        
        //adding back all taken values
        while(thereIsEnptyDomain(outside) && !inside.isEmpty()){
            Variable var = inside.get(inside.size()-1);
            inside.remove(var);
            outside.add(var);
            //put a constraint in previos variable to prevent same entry
            if(!inside.isEmpty()){
                int indexOfPreviousVar = vList.indexOf(inside.get(inside.size()-1));// the previos one
                int indexOfCurrVar = vList.indexOf(var);//for constraint
                memoryList.get(indexOfPreviousVar).remValues.add(new RemovedValues(indexOfCurrVar, var.asama));
            }
            while(!memoryList.get(vList.indexOf(var)).remValues.isEmpty()){
                rv = memoryList.get(vList.indexOf(var)).remValues.remove(0);
                vList.get(rv.getVarIndex()).domain.add(rv.getValue());
            }
            
        }

    }

    //---------------------printing functions------------------------//
    //full print of problem
    public static void bigPrint(int cc, 
                                CSProblem problem, 
                                ArrayList<undefinedVars> memoryList, 
                                ArrayList<Integer> order,
                                ArrayList<Variable> inside){
        //----------
        System.out.print("Number of constraint checks: " + cc);
        //----------
        System.out.print(problem.toStringAsama());
        //memoryList
        for(undefinedVars uv : memoryList){
            System.out.print((uv.getVarIndex()+1) + ":: ");
            for(int i = 0; i < uv.remValues.size(); i++){
                System.out.print(" " + (uv.remValues.get(i).getVarIndex()+1) + ":" + uv.remValues.get(i).getValue());
            }
            System.out.println();
        }
        //----------
        System.out.println(problem.toStringDomain());
        //----------
        System.out.println();
        printOrder(inside, problem.vList);
    }

    //printing the order according to position of variables in "inside" list
    public static void printOrder(ArrayList<Variable> inside, ArrayList<Variable> vList){
        System.out.print("Order:");
        for(Variable v : inside){
            System.out.print(" " + (vList.indexOf(v)+1));
        }
        System.out.print(" ");
    }
    //---------------------------------------------------------------//
    //---------------------------------------------------------------//
    //---------------------------------------------------------------//
    //---------------------------------------------------------------//
    //---------------------------------------------------------------//
    //---------------------------------------------------------------//
    //---------------------------------------------------------------//
    //---------------------------------------------------------------//
    //---------------------------------------------------------------//
    //---------------------------------------------------------------//
    //---------------------------------------------------------------//
    //---------------------------------------------------------------//
    //---------------------------------------------------------------//
    //---------------------------------------------------------------//
    //---------------------------------------------------------------//
    //---------------------------------------------------------------//
    //---------------------------------------------------------------//
    //---------------------------------------------------------------//
    //---------------------------------------------------------------//
    //---------------------------------------------------------------//
    //---------------------------------------------------------------//
    //---------------------------------------------------------------//
    //---------------------------------------------------------------//
    //---------------------------------------------------------------//
    //---------------------------------------------------------------//
    //---------------------------------------------------------------//
    //---------------------------------------------------------------//
    //---------------------------------------------------------------//
    //---------------------------------------------------------------//
    //---------------------------------------------------------------//
    //---------------------------------------------------------------//
    //---------------------------------------------------------------//
    //---------------------------------------------------------------//
    //---------------------------MAC-3-------------------------------//
    //---------------------------------------------------------------//
    //---------------------------------------------------------------//
    //---------------------------------------------------------------//

    public static Answer mac3(CSProblem problem){
        //--------TEMPORARY VARIABLES--------//
        Variable currVar;
        ArrayList<String> restDomainAside;
        Boolean thereIsNoWipeOut;
        ArrayList<undefinedVars> memoryList = new ArrayList<undefinedVars>();//remembers all removed values
        for(int i = 0; i < problem.vList.size(); i++){
            memoryList.add(new undefinedVars(i));
        }
        //consList helps to indicate which variables have which connections in constraints list
        ArrayList<Connections> consList = getConnectionsFrom(problem.vList, problem.cList);
        //the L-list for ac-3 process
        ArrayList<Pair> lList = new ArrayList<Pair>();
        ArrayList<Variable> inside = new ArrayList<Variable>();
        ArrayList<Variable> outside = new ArrayList<Variable>();
        for(Variable v : problem.vList){
            outside.add(v);
        }
        Counter cc = new Counter();
        //------------------------------------//

        //------------------------------------//
        //----LOOPING UNTIL GETTING ANSWER----//
        //------------------------------------//
        while(inside.size() != problem.vList.size()){ // outside loop of variables with asama 
            currVar = problem.vList.get(failFirst(problem.vList, outside));
            if(inside.isEmpty() && currVar.domain.isEmpty()){
                //System.out.println("MAC3:: There is NO solution! ");//Printing[][][][][]
                return new Answer(cc.getCc(), new ArrayList<String>());
            }
            currVar.asama = currVar.domain.remove(0);
            restDomainAside = getRestDomainAside(currVar);//only asama is inside the domain
            
            lList = getLList(outside, consList, problem.vList);
            thereIsNoWipeOut = ac3(lList, memoryList, problem.cList, problem.vList, currVar, consList, outside, inside, cc);
            
            //return domain and removing asama from it
            currVar.domain.remove(0);
            while(!restDomainAside.isEmpty()){
                currVar.domain.add(restDomainAside.remove(0));
            }
            
            if(thereIsNoWipeOut){
                inside.add(currVar);
                outside.remove(currVar);
            } else {
                bringBackAllValuesMAC3(currVar, problem.vList, memoryList, inside, outside);
            }
        }
        //------------------------------------//
        //------------------------------------//
        //------------------------------------//

        return new Answer(cc.getCc(), problem.getAsama());
    }


    //-----------------------HELP FUNCTIONS--------------------------//

    //this function saves for each variable other variables that it has constraints with them
    public static ArrayList<Connections> getConnectionsFrom(ArrayList<Variable> vList, ArrayList<Constraint> cList){
        ArrayList<Connections> consList = new ArrayList<Connections>();
        for(Variable v : vList){
            consList.add(new Connections(v));
        }
        Connections cons;
        Variable var;
        for(Constraint c : cList){
            cons = consList.get(c.getFirstVar());
            var = vList.get(c.getSecondVar());
            if(!cons.tiedWith.contains(var)){
                cons.tiedWith.add(var);
            }
            cons = consList.get(c.getSecondVar());
            var = vList.get(c.getFirstVar());
            if(!cons.tiedWith.contains(var)){
                cons.tiedWith.add(var);
            }
        }
        return consList;
    }

    //get outside rest of domain and put back only the asama - needed for ac3 process
    public static ArrayList<String> getRestDomainAside(Variable currVar){
        ArrayList<String> domainAside = new ArrayList<String>();
        while(!currVar.domain.isEmpty()){
            domainAside.add(currVar.domain.remove(0));
        }
        currVar.domain.add(currVar.asama);
        return domainAside;
    }

    //getting L-list according to laws of ac3 process
    public static ArrayList<Pair> getLList(ArrayList<Variable> outside, 
                                           ArrayList<Connections> consList, 
                                           ArrayList<Variable> vList){
        ArrayList<Pair> lList = new ArrayList<Pair>();
        ArrayList<Variable> cons;
        for(Variable v : outside){
            cons = consList.get(vList.indexOf(v)).tiedWith;
            for(Variable v2 : cons){
                if(outside.contains(v2)){
                    lList.add(new Pair(v, v2));
                }
            }
        }
        return lList;
    }

    //actual ac3 process
    public static boolean ac3(ArrayList<Pair> lList, 
                              ArrayList<undefinedVars> memoryList, 
                              ArrayList<Constraint> cList,
                              ArrayList<Variable> vList,
                              Variable currVar,
                              ArrayList<Connections> consList,
                              ArrayList<Variable> outside,
                              ArrayList<Variable> inside,
                              Counter cc){                     //returns boolean to thereIsNoWipeOut
        Variable v1, v2;
        Pair p;
        String si, sj;
        Boolean thereIsConstraint, thereIsSupport;
        while(!lList.isEmpty()){
            p = lList.remove(0);
            v1 = p.getV1();
            v2 = p.getV2();

            for(int i = 0; i < v1.domain.size(); i++){
                si = v1.domain.get(i);
                thereIsSupport = false;
                for(int j = 0; j < v2.domain.size(); j++){
                    sj = v2.domain.get(j);
                    thereIsConstraint = false;
                    //Constraint check
                    //-----------------------------------------------------------------------------
                    for(Constraint c : cList){
                        if(c.getFirstVar() == vList.indexOf(v1) && c.getfirstVarAsama().equals(si)){
                            if(c.getSecondVar() == vList.indexOf(v2) && c.getSecondVarAsama().equals(sj)){
                                
                                thereIsConstraint = true;
                                break;
                            }
                        } 
                        if(c.getSecondVar() == vList.indexOf(v1) && c.getSecondVarAsama().equals(si)){
                            if(c.getFirstVar() == vList.indexOf(v2) && c.getfirstVarAsama().equals(sj)){
                                thereIsConstraint = true;
                                break;
                            }
                        }  
                    }
                    //-----------------------------------------------------------------------------
                    cc.plusOne();
                    if(!thereIsConstraint){
                        thereIsSupport = true;
                        break;
                    } 
                }

                if(!thereIsSupport){// checking for wipeout
                    for(Variable v : consList.get(vList.indexOf(v1)).tiedWith){
                        if(outside.contains(v)){lList.add(new Pair(v, v1));}
                    }
                    if(currVar.equals(v1)){
                        if(inside.isEmpty()){return false;}
                        int indexOfPreviousVar = vList.indexOf(inside.get(inside.size()-1));
                        memoryList.get(indexOfPreviousVar).remValues.add(new RemovedValues(vList.indexOf(currVar), si));
                        return false;
                    }
                    v1.domain.remove(si);
                    i--;
                    memoryList.get(vList.indexOf(currVar)).remValues.add(new RemovedValues(vList.indexOf(v1), si));

                    if(v1.domain.isEmpty()){
                        return false;
                    }
                }
            }
        
        }
        return true;
    } 

    //bringing back all removed values using memoryList
    public static void bringBackAllValuesMAC3(Variable var,
                                              ArrayList<Variable> vList, 
                                              ArrayList<undefinedVars> memoryList, 
                                              ArrayList<Variable> inside, 
                                              ArrayList<Variable> outside){
        
        RemovedValues rv;
        inside.add(var);
        outside.remove(var);
        Variable currVar = var;
        boolean thereIsEmpty;
        do{
            thereIsEmpty = false;
            while(!memoryList.get(vList.indexOf(currVar)).remValues.isEmpty()){
                rv = memoryList.get(vList.indexOf(currVar)).remValues.remove(0);
                vList.get(rv.getVarIndex()).domain.add(rv.getValue());
            }
            inside.remove(currVar);
            outside.add(currVar);
            if(inside.isEmpty()){
                break;
            }
            for(Variable vs : outside){
                if(vs.domain.isEmpty()){
                    thereIsEmpty = true;
                    break;
                }
            }
            currVar = inside.get(inside.size()-1);
        } while(currVar.domain.isEmpty() || thereIsEmpty); 
    }

    //---------------------------------------------------------------//
    //---------------------------------------------------------------//
    //---------------------------------------------------------------//
    //---------------------------------------------------------------//
    //---------------------------------------------------------------//
    //---------------------------------------------------------------//
    //---------------------------------------------------------------//
    //---------------------------------------------------------------//
    //---------------------------------------------------------------//
    //---------------------------------------------------------------//
    //---------------------------------------------------------------//
    //---------------------------------------------------------------//
    //---------------------------------------------------------------//
    //---------------------------------------------------------------//
    //---------------------------------------------------------------//
    //---------------------------------------------------------------//
    //---------------------------------------------------------------//

}

/////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////code for testing///////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////

            //System.out.println(inside.size());  //Printing[][][][][]
            //System.out.println(outside.size()); //Printing[][][][][]
            //System.out.println("---");          //Printing[][][][][]


            // System.out.println(c.getfirstVarAsama());
            // System.out.println(si);
            // System.out.println(c.getfirstVarAsama().equals(si));
            // if(c.getfirstVarAsama() == si){
            //     System.out.println("!!!!!!!!!!!!!!!!!HERE!!!!!!!!!!!!!!!!!"); //Printing[][][][][]
            // }

            ///////////////////////////////////////////////////////////////////////////
            // System.out.println();//Printing[][][][][]
            // for(Variable vrbl: problem.vList){
            //     System.out.println(vrbl.domain.toString());//Printing[][][][][]
            //     if(outside.contains(vrbl)){
            //         System.out.println("is outside");
            //         if(currVar.equals(vrbl)){
            //             System.out.println("is currVar");
            //         }
            //     }
            // }
            // System.out.println();//Printing[][][][][]
            // //memoryList
            // for(undefinedVars uv : memoryList){
            //     System.out.print((uv.getVarIndex()+1) + ":: ");
            //     for(int i = 0; i < uv.remValues.size(); i++){
            //         System.out.print(" " + (uv.remValues.get(i).getVarIndex()+1) + ":" + uv.remValues.get(i).getValue());
            //     }
            //     System.out.println();
            // }
            //----------
            ///////////////////////////////////////////////////////////////////////////


            // printOrder(inside, problem.vList);  //Printing[][][][][]
            // System.out.println("---");          //Printing[][][][][]
            // System.out.println("inside: " + inside.size());  //Printing[][][][][]
            // System.out.println("outside: " + outside.size()); //Printing[][][][][]
            // System.out.println("---");          //Printing[][][][][]


        // for(int i = 0; i < consList.size(); i++){
        //     System.out.print((i+1)+": ");
        //     for(int j = 0; j< consList.get(i).tiedWith.size(); j++){
        //         System.out.print(" " + (vList.indexOf(consList.get(i).tiedWith.get(j))+1) + " ");
        //     }
        //     System.out.println();
        // }


        // for(Pair p : lList){
        //     System.out.println("<" + (vList.indexOf(p.getV1())+1) + "," + (vList.indexOf(p.getV2())+1) + ">");
        // }
        // System.out.println();

/////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////code for testing///////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////