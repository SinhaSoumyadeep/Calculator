package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * This a Concrete Class that implements the functionality of a Simple Calculator. A Simple
 * calculator accepts inputs like a normal calculator. A correct basic sequence of inputs is the
 * first operand, followed by the operator, followed by the second operand, followed by “=” (e.g. 3
 * 2 + 2 4 =). Each operand may have multiple digits.
 */
public class SimpleCalculator extends AbstractCalculator {


  /**
   * This method takes a single character as input and tries to calculate the result of the
   * expression.
   *
   * @param input takes in a character as input.
   * @return a Calculator Object.
   */
  @Override
  public Calculator input(char input) {

    res = input + "";
    checkForValidInput(res);
    originalExpression = originalExpression + res;
    isFirstKeyOperator();
    if (res.equals("C")) {
      resetEverything();
    }

    expression = expression + res;
    overFlowCheck();
    handlePrecedingZeroCase();
    missingOperand();
    removeEquals();
    triggerCalculateExpression();
    return this;

  }


  /**
   * This method the current state of the calculator which might be result of the expression or the
   * current state of the inputted expression. Our calculator will also support displaying the
   * current state of the calculator.
   *
   * @return the current result of the calculator.
   */
  @Override
  public String getResult() {

    return this.expression;
  }


  /**
   * This is an overriden method that trigger to calculate the actual expression.
   */
  protected void triggerCalculateExpression() {
    //trigger pattern
    Pattern triggerPattern = Pattern.compile("[0-9]+[\\+\\-\\*@]{1}[0-9]+[\\+\\-\\*\\=]{1}");
    Matcher trigger = triggerPattern.matcher(expression);

    if (trigger.find()) {

      expression = calculateResult(expression.substring(0, expression.length() - 1) + "=");
      if (!res.equals("=")) {
        expression = expression + res;
      }

    }
  }


  /**
   * This is an overriden method which is used to detect the missing operand case.
   */
  @Override
  protected void missingOperand() {

    Pattern patternMissing1 = Pattern.compile("[0-9]+[\\+\\-\\*\\=]{2,}");
    Matcher matchMissing1 = patternMissing1.matcher(expression);

    if (matchMissing1.find()) {
      expression = expression.substring(0, expression.length() - 1);
      throw new IllegalArgumentException("Invalid Input");
    }

  }


}
