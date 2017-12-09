package elfimova.calculator;

import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * Class for counting the expression written in the Reverse Polish Notation
 * @autor elfimova Luda on 02.12.2017.
 */

public class Calculate {
    /**
     * Calculates the expression written in the Reverse Polish Notation
     *
     * @param inStr
     * @throws NumberFormatException Invalid character or operation in the expression
     * @throws Exception The number of operators does not match the number of operands
     * @throws Exception Invalid operation in the expression
     * @return double result
     */

    public  double calculate(String inStr) throws Exception {
        double dA = 0, dB = 0;
        String sTmp;
        ArrayDeque<Double> stack = new ArrayDeque<>();
        StringTokenizer st = new StringTokenizer(inStr);
        while (st.hasMoreTokens()) {
            sTmp = st.nextToken().trim();
            if ((1 == sTmp.length() && ArithmeticOperators.isOperators(sTmp)) ||
                    ArithmeticFunction.isFunction(sTmp)) {
                if (ArithmeticFunction.isFunction(sTmp)) {
                    dA = stack.pop();
                    switch (sTmp) {
                        case "sin":
                            CountSin s = new CountSin(dA);
                            dA = s.count();
                            break;
                        case "cos":
                            CountCos c = new CountCos(dA);
                            dA = c.count();
                            break;
                        case "tg":
                            CountTg t = new CountTg(dA);
                            dA = t.count();
                            break;
                        case "ctg":
                            CountCtg ct = new CountCtg(dA);
                            dA = ct.count();
                            break;
                        case "max":
                            dB = stack.pop();
                            CountMax m = new CountMax(dA, dB);
                            dA = m.count();
                            break;
                        case "min":
                            dB = stack.pop();
                            CountMin mi = new CountMin(dA, dB);
                            dA = mi.count();
                            break;
                        default:
                            throw new Exception("Invalid operation in the expression " + sTmp);
                    }
                    stack.push(dA);
                }
                if (1 == sTmp.length() && ArithmeticOperators.isOperators(sTmp)) {
                    if (stack.size() < 2){
                        throw new Exception("The number of operators does not match the number of operands: " + sTmp);
                    }
                    dB = stack.pop();
                    dA = stack.pop();
                    stack.push(ArithmeticOperators.countOrerat(sTmp, dB, dA));
                }
            } else {
                try {
                    dA = Double.parseDouble(sTmp);
                    stack.push(dA);
                } catch (NumberFormatException e) {
                    throw new Exception("Invalid character or operation in the expression: " + sTmp );
                }
            }
        }
        if (stack.size() > 1) {
            throw new Exception("The number of operators does not match the number of operands");
        }
        return stack.pop();
    }
}

