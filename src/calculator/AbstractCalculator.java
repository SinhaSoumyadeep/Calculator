package calculator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * This Abstract class abstracts the operations of a calculator that works only with whole numbers.
 * It supports only three arithmetic operations: addition, subtraction and multiplication. To
 * simulate the button press functionality, the calculator accepts inputs one character at a time.
 * For example, to compute 32+243 the inputs would be ’3’, ’2’, ’+’, ’2’, ’4’, ’3’, ’=’ in that
 * sequence. Our calculator will also support displaying the “current” state of the calculator.
 * Along with methods which helps calculate the result of the expression.
 */
public abstract class AbstractCalculator implements Calculator {

  protected String res = "";
  protected boolean isResultNegative;
  protected String originalExpression = "";
  protected String expression = "";
  private ArrayList<String> calArray = new ArrayList<String>();
  private ArrayList<String> operator = new ArrayList<String>() {
    {
      add("+");
      add("-");
      add("*");
    }
  };


  /**
   * This is an abstract method which will be implemented by the sub classes. This is method is a
   * trigger to calculate the actual expression.
   */
  protected abstract void triggerCalculateExpression();


  /**
   * This is an abstract method which is used to detect the missing operand case. This is method is
   * implemented by the subclass which uses different pattern to get the result.
   */
  protected abstract void missingOperand();


  /**
   * This method is called when "C" is in the input. This resets the entire calculator to its
   * initial form.
   */
  protected void resetEverything() {
    res = "";
    isResultNegative = false;
    originalExpression = "";
    expression = "";
    calArray.clear();

  }

  /**
   * This method is used to check if there are any invalid characters in the expression.
   *
   * @param res takes in a String from the input.
   */
  protected void checkForValidInput(String res) {

    Pattern checkInput = Pattern.compile("[C0-9\\+\\*\\-\\=]{1}");
    Matcher checkInputMatcher = checkInput.matcher(res);

    if (!checkInputMatcher.find()) {
      throw new IllegalArgumentException("Invalid Input");
    }

  }

  /**
   * This method checks and handles operator that precedes the actual expression. Example: if the
   * first keystroke is an operator. eg: +3+... then remove the preceding operator.
   */
  protected void isFirstKeyOperator() {

    Pattern pattern = Pattern.compile("^[\\+\\-\\*\\=]+");
    Matcher match = pattern.matcher(originalExpression);

    if (match.find()) {
      if (this instanceof SimpleCalculator) {
        throw new IllegalArgumentException();
      } else if (this instanceof SmartCalculator) {

        originalExpression = "";
        res = "";

      }

    }
  }

  /**
   * This method removes the redundant "=" from the expression. Example: remove equalto from
   * expression eg: 8=.
   */
  protected void removeEquals() {

    Pattern removeEqualto = Pattern.compile("^[\\+\\-\\*]*[0-9]+([\\=]+)");
    Matcher removeEqualtoMat = removeEqualto.matcher(expression);

    if (removeEqualtoMat.find()) {
      expression = expression.replace(removeEqualtoMat.group(1), "");
    }
  }

  /**
   * This method handles the preceding zeros in an operand. Example: It removes the "0" from "02"
   * and displays it as "2".
   */
  protected void handlePrecedingZeroCase() {
    Pattern patternHandleZero = Pattern.compile("([0-9]{2,})");
    Matcher matcherHandleZero = patternHandleZero.matcher(expression);

    while (matcherHandleZero.find()) {
      expression = expression.replace(matcherHandleZero.group(),
              Integer.parseInt(matcherHandleZero.group()) + "");
    }
  }

  /**
   * This method checks if the operand overflows.
   */
  protected void overFlowCheck() {

    Pattern patternOverflow = Pattern.compile("([0-9]+)");
    Matcher matcherOverflow = patternOverflow.matcher(originalExpression);

    if (matcherOverflow.find()) {

      String number = matcherOverflow.group(1);
      Double numberCompare = Double.parseDouble(number);

      if (numberCompare > Integer.MAX_VALUE) {
        throw new RuntimeException("Operand Overflowed");
      }
      if (numberCompare < Integer.MIN_VALUE) {
        throw new RuntimeException("Operand Overflowed");
      }


    }

  }

  /**
   * This method is used to calculate the expression passed as in as a String Object.
   *
   * @param result this mehod takes in as argument an expression.
   * @return the calculated result of the expression.
   */
  protected String calculateResult(String result) {

    if (isResultNegative) {
      result = "0" + result;
    }

    Pattern pattern;
    if (!isResultNegative) {
      pattern = Pattern.compile("([0-9]+)([\\+\\-\\*\\=]+)");
    } else {
      pattern = Pattern.compile("([@]*[\\-]*[0-9]+)([\\+\\-\\*\\=]+)");
    }
    Matcher matcher = pattern.matcher(result);
    while (matcher.find()) {
      String group1 = matcher.group(1);
      String group2 = matcher.group(2);

      if (group1.contains("@")) {
        group1 = group1.replace("@", "");
      }
      calArray.add(group1);

      if (group2.length() > 1) {
        if (group2.charAt(group2.length() - 1) == '=') {
          String res = evaluateInfixToPostfix(calArray);
          calArray.add(group2.charAt(0) + "");
          calArray.add(res);
        } else {
          calArray.add(group2.charAt(group2.length() - 1) + "");
        }

      } else {

        if (!group2.equals("=")) {
          calArray.add(group2);
        }

      }


    }
    String resultvalue = evaluateInfixToPostfix(calArray);

    if (Integer.parseInt(resultvalue) < 0) {
      isResultNegative = true;
    }

    calArray.clear();

    return resultvalue;
  }


  /**
   * This method converts an Infix expression to equivalent postfix expression.
   *
   * @param calArray takes an Infix expression in the form of ArrayList.
   * @return result of the Infix expression as a String Object.
   */
  private String evaluateInfixToPostfix(ArrayList<String> calArray) {

    ArrayList<String> postfixArr = new ArrayList<String>();
    LinkedList<String> stack = new LinkedList<String>();
    for (int i = 0; i < calArray.size(); i++) {

      if (!operator.contains(calArray.get(i))) {

        postfixArr.add(calArray.get(i));
      } else {

        while (!stack.isEmpty()
                && calculatePrecedence(calArray.get(i)) <= calculatePrecedence(stack.peek())) {
          postfixArr.add(stack.peek());
          stack.pop();

        }
        stack.push(calArray.get(i));

      }

    }

    while (!stack.isEmpty()) {

      postfixArr.add(stack.peek());
      stack.pop();
    }
    Integer postfixEval = evaluatePostFix(postfixArr);
    return postfixEval + "";

  }


  /**
   * This method evaluates a postfix expression.
   *
   * @param postfixArr takes in a postfix expression in a form of ArrayList.
   * @return the result of the expression as an Integer Object.
   */
  private Integer evaluatePostFix(ArrayList<String> postfixArr) {
    LinkedList<Integer> stack = new LinkedList<Integer>();


    for (int i = 0; i < postfixArr.size(); i++) {
      if (!operator.contains(postfixArr.get(i))) {
        stack.push(Integer.parseInt(postfixArr.get(i)));
      } else {
        Integer operand1 = stack.pop();
        Integer operand2 = stack.pop();

        if (postfixArr.get(i).equals("+")) {

          try {
            stack.push(Math.addExact(operand2, operand1));
          } catch (Exception e) {
            stack.push(0);

          }
        } else if (postfixArr.get(i).equals("-")) {

          try {
            stack.push(Math.subtractExact(operand2, operand1));
          } catch (Exception e) {
            stack.push(0);
          }
        } else if (postfixArr.get(i).equals("*")) {
          try {
            stack.push(Math.multiplyExact(operand2, operand1));
          } catch (Exception e) {
            stack.push(0);
          }
        }

      }
    }

    return stack.pop();
  }


  /**
   * This method is to make the class scalable if BODMAD/PEMDAS is implemented. Currently all
   * operators are treated the same.
   *
   * @param ch takes in an operator as a String object.
   * @return the precedence of the operator.
   */
  private static final int calculatePrecedence(String ch) {
    switch (ch) {
      case "+":
      case "-":
        return 1;
      case "*":
        return 1; //make it 2 for precedence.
      default:
        return -1;

    }

  }


}
