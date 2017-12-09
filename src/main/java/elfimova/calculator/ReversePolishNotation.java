package elfimova.calculator;

import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Class for converting the expression to a Reverse Polish Notation
 * @autor elfimova Luda on 02.12.2017.
 */

public class ReversePolishNotation {

    /**
     * Convert a string to a Reverse Polish Notation
     *
     * @param inStr Input string
     * @throws Exception Error parsing the parentheses
     * @return Output string in the Reverse Polish Notation
     */

    public static String getReversePolishNotation(String inStr) throws Exception {
        StringBuilder outStr = new StringBuilder("");
        // splitting input string into tokens
        StringTokenizer stringTokenizer = new StringTokenizer(inStr,
                ArithmeticOperators.OPERATORS + " ()"+ ",", true);
        Stack<String> sStack = new Stack<>();
        String  cTmp;
        while (stringTokenizer.hasMoreTokens()) {
            String token = stringTokenizer.nextToken();
            if (ArithmeticOperators.isOperators(token)) {
                while (!sStack.empty()) {
                    if (ArithmeticOperators.isOperators(sStack.lastElement()) &&
                            (ArithmeticOperators.opPrior(token) <= ArithmeticOperators.opPrior(sStack.lastElement()))) {
                        outStr.append(" ").append(sStack.pop()); // add a space, the last element of the stack in a outstr
                    } else {
                        break;
                    }
                }
                sStack.push(token);
            }
            else if(token.equals("(")) {
                sStack.push(token);
            }
            else if(token.equals(",")) {
                sStack.push(token);
                sStack.pop();
            }
            else if (token.equals(")")) {
                cTmp = sStack.lastElement(); // the last element of the stack
                while (!cTmp.equals("(")) {
                    if (sStack.size() <= 1) {
                        throw new Exception("Error parsing the parentheses.");
                    }
                    outStr.append(" ").append(sStack.pop());
                    cTmp = sStack.lastElement();
                }
                sStack.pop();
                if ((!sStack.empty()) &&(ArithmeticFunction.isFunction(sStack.lastElement()))) {
                    outStr.append(" ").append(sStack.lastElement());
                    sStack.pop();
                }
            }
            else if (ArithmeticFunction.isFunction(token)){
                sStack.push(token);
            }
            else {
                // if the token is not an operator, add it to the outstr
                outStr.append(" ").append(token);
            }
        }
        while(!sStack.empty()){
            outStr.append(" ").append(sStack.pop());
        }
        return outStr.toString();
    }
}
