import java.util.ArrayList;

public class Answer{
    private int cc;
    private ArrayList<String> asama;
    

    public Answer(int cc, ArrayList<String> asama){
        this.cc = cc;
        this.asama = asama;
    }

    //----------------------------GETTERS----------------------------//

    public int getCC(){
        return this.cc;
    }

    public ArrayList<String> getAsama(){
        return this.asama;
    }
    
    @Override
    public String toString(){
        String str = "\n";
        int index = 1;
        for(String s : this.asama){
            str += index + ": " + s + "\n";
            index++;
        }
        return str;
    }

    //----------------------------GETTERS----------------------------//
    //---------------------------------------------------------------//
    //---------------------------------------------------------------//
    //---------------------------------------------------------------//
    //---------------------------------------------------------------//
}
