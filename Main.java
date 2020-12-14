import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;


public class Main{
    public static void main(String[] args) throws Exception{

        ArrayList<Point> graphFC = new ArrayList<Point>();
        ArrayList<Point> graphMAC3 = new ArrayList<Point>();
        ArrayList<Answer> answersFC = new ArrayList<Answer>();
        ArrayList<Answer> answersMAC3 = new ArrayList<Answer>();

        // CSProblem prob1 = new CSProblem(10, 10, 0.5, 0.6);
        // CSProblem prob2 = prob1.toCopy();
        // // // System.out.println(prob1.toString());
        // // // System.out.println(prob1.toStringAsama());
        // System.out.println("---------------------");
        // // System.out.println(prob2.toString());
        // // // System.out.println(prob2.toStringAsama());
        // Answer ans1 = Algorithms.mac3(prob2);
        // Answer ans2 = Algorithms.forwardChecking(prob1);
        // System.out.println("---------------------");
        // System.out.println("MAC3:");
        // System.out.println(ans1.toString());
        // System.out.println("FC:");
        // System.out.println(ans2.toString());
        // System.out.println("---------------------");
        
        //System.out.println(p.toStringAsama());
        //--------------------------------------------------------//
        //--------------------------------------------------------//
        //--------------------------------------------------------//
        //--------------------------------------------------------//
        //----------------------P1 = 0.4--------------------------//
        //--------------------------------------------------------//
        //--------------------------------------------------------//
        //--------------------------------------------------------//
        //--------------------------------------------------------//

        //------------------------Settings------------------------//
        double p1 = 0.4;
        int amountPerEachP2 = 100;
        double jumpConst = 0.02;
        int numOfVar = 10;
        int domainSize = 10;
        //--------------------------------------------------------//
        //----------------------Calculations----------------------//

        // for(double p2 = 0.1; p2 <= 0.98; p2 += jumpConst){
        //     for(int k = 0; k < amountPerEachP2; k++){
        //         CSProblem prob1 = new CSProblem(numOfVar, domainSize, p1, p2);
        //         CSProblem prob2 = prob1.toCopy();//same problem
        //         answersFC.add(Algorithms.forwardChecking(prob1));
        //         answersMAC3.add(Algorithms.mac3(prob2));
        //     }
        //     graphFC.add(new Point(p2, averageCC(answersFC)));
        //     graphMAC3.add(new Point(p2, averageCC(answersMAC3)));
        //     answersFC = new ArrayList<Answer>();
        //     answersMAC3 = new ArrayList<Answer>();
        // }
        // PrintStream out = new PrintStream(new FileOutputStream("outputFC.txt"));
        // System.setOut(out);
        // printGraph(graphFC);//print in external file named "outputFC.txt"
        // out = new PrintStream(new FileOutputStream("outputMAC.txt"));
        // System.setOut(out);
        // printGraph(graphMAC3);//print in external file named "outputMAC.txt"

        //--------------------------------------------------------//
        //--------------------------------------------------------//
        //--------------------------------------------------------//
        //--------------------------------------------------------//
        //--------------------------------------------------------//
        //--------------------------------------------------------//
        //--------------------------------------------------------//
        //--------------------------------------------------------//
        //--------------------------------------------------------//

        //--------------------------------------------------------//
        //--------------------------------------------------------//
        //--------------------------------------------------------//
        //--------------------------------------------------------//
        //-----------------------P1 = 0.7-------------------------//
        //--------------------------------------------------------//
        //--------------------------------------------------------//
        //--------------------------------------------------------//
        //--------------------------------------------------------//

        //------------------------Settings------------------------//
        p1 = 0.7;
        amountPerEachP2 = 100;
        jumpConst = 0.02;
        numOfVar = 10;
        domainSize = 10;

        //--------------------------------------------------------//
        //----------------------Calculations----------------------//

        for(double p2 = 0.1; p2 <= 0.98; p2 += jumpConst){
            for(int k = 0; k < amountPerEachP2; k++){
                CSProblem prob1 = new CSProblem(numOfVar, domainSize, p1, p2);
                CSProblem prob2 = prob1.toCopy();
                answersFC.add(Algorithms.forwardChecking(prob1));
                answersMAC3.add(Algorithms.mac3(prob2));
            }
            graphFC.add(new Point(p2, averageCC(answersFC)));
            graphMAC3.add(new Point(p2, averageCC(answersMAC3)));
            answersFC = new ArrayList<Answer>();
            answersMAC3 = new ArrayList<Answer>();
        }
        PrintStream out = new PrintStream(new FileOutputStream("outputFC.txt"));
        System.setOut(out);
        printGraph(graphFC);
        out = new PrintStream(new FileOutputStream("outputMAC.txt"));
        System.setOut(out);
        printGraph(graphMAC3);

        //--------------------------------------------------------//
        //--------------------------------------------------------//
        //--------------------------------------------------------//
        //--------------------------------------------------------//
        //--------------------------------------------------------//
        //--------------------------------------------------------//
        //--------------------------------------------------------//
        //--------------------------------------------------------//
        //--------------------------------------------------------//
    }
    //-----------------------HELP FUNCTIONS--------------------------//

    //calculates average cc number from array of answers
    public static double averageCC(ArrayList<Answer> answers){
        double sum = 0;
        for(Answer a : answers){
            sum += (double)(a.getCC());
        }
        return (sum/(double)(answers.size()));
    }

    //printing points
    public static void printGraph(ArrayList<Point> graph){
        System.out.print("x: ");
        for(Point p : graph){
            System.out.print(" " + Math.round(p.getP2()*100));
        }
        System.out.println();
        System.out.print("y: ");
        for(Point p : graph){
            System.out.print(" " + Math.round(p.getCC()));
            
        }
    }
}

/////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////code for testing///////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////

        // CSProblem prob1 = new CSProblem(10, 10, 0.5, 0.6);
        // CSProblem prob2 = prob1.toCopy();
        // // System.out.println(prob1.toString());
        // // System.out.println(prob1.toStringAsama());
        // System.out.println("---------------------");
        // System.out.println(prob2.toString());
        // // System.out.println(prob2.toStringAsama());
        // Answer ans1 = Algorithms.mac3(prob2);
        // Answer ans2 = Algorithms.forwardChecking(prob1);
        // System.out.println("---------------------");
        // System.out.println("MAC3:");
        // System.out.println(ans1.toString());
        // System.out.println("FC:");
        // System.out.println(ans2.toString());
        // System.out.println("---------------------");
        
        //System.out.println(p.toStringAsama());

/////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////code for testing///////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////
