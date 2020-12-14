

public class Counter{
    private int cc;

    public Counter(){
        cc = 0;
    }

    public int getCc() {
        return this.cc;
    }

    public boolean plusOne(){
        this.cc++;
        return true;
    }
}