package elfimova.calculator;

/**
 * Main class arithmetic expression calculation
 * @autor elfimova Luda on 02.12.2017.
 */

public class MainProgram {

    /**
     * Here the starting point of the program
     * Calculates the expression written in the Reverse Polish Notation
     * @param args
     *
     */

    public static void main(String[] args) throws Exception{
        Calculate calc= new Calculate();
        String s=InputExpression.input();
        double result=calc.calculate(ReversePolishNotation.getReversePolishNotation(s));
        ShowResult outResult=new ShowResult();
        outResult.showResult(result);
    }
}
