package calculator;


import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * This a Concrete Class that implements the functionality of a Smart Calculator. A smart calculator
 * accepts inputs like a normal calculator. This calculator is backward compatible with the simple
 * calculator.It can handle multiple "=", skipped operand,consecutive operators, operator that
 * precedes the actual expression and can handle invalid inputs but cannot take negative numbers as
 * input.
 */
public class SmartCalculator extends AbstractCalculator {

  /**
   * This method takes a single character as input and tries to calculate the result of the
   * expression.
   *
   * @param input takes in a character as input.
   * @return a Calculator Object.
   */
  public Calculator input(char input) {

    res = input + "";


    originalExpression = originalExpression + res;
    overFlowCheck();
    checkForValidInput(res);
    isFirstKeyOperator();
    if (res.equals("C")) {
      resetEverything();
    }
    isMultipleEqualsAfterExp();
    isMultipleEqualsFollowedByOperator();
    expression = expression + res;
    handleRedundantOperator();
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
  public String getResult() {
    //handleRedundantOperator();
    return this.expression;
  }


  /**
   * This is an overriden method that trigger to calculate the actual expression.
   */
  @Override
  protected void triggerCalculateExpression() {

    Pattern triggerPattern =
            Pattern.compile("[\\+\\-\\*]*[0-9]+[\\+\\-\\*@]+[0-9]+[\\+\\-\\*\\=]+");
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
    //if there is missing operand between operators: 3++2=
    Pattern patternMissing1 = Pattern.compile("([0-9]*[\\+\\-\\*]*[0-9]+)([\\+\\-\\*]+)[\\=]+$");
    Matcher matchMissing1 = patternMissing1.matcher(expression);

    if (matchMissing1.find()) {

      String a = calculateResult(matchMissing1.group(1) + "=");
      String b = matchMissing1.group(2);


      if (!isResultNegative) {
        expression = expression.substring(0, expression.length() - 1) + b + a + res;
      } else {
        expression = expression.substring(0, expression.length() - 1) + b + "@" + a + res;
      }

    }
  }

  /**
   * This is a method that is used to handle multiple Redundant Operator.
   */
  private void handleRedundantOperator() {

    Pattern pat = Pattern.compile("([\\-]*[0-9]+)([\\*\\+\\-]{2,}$)");
    Matcher mat = pat.matcher(expression);

    if (mat.find()) {


      String operand = mat.group(1);
      String multipleOperator = mat.group(2);

      if (multipleOperator.length() >= 2) {
        String operatorToBeIncluded = multipleOperator.charAt(multipleOperator.length() - 1) + "";
        expression = operand + operatorToBeIncluded;

      }


    }
  }

  /**
   * This method is used to detect and handle the condition where there are multiple equaltos after
   * an expression. Example: 3+2==== case is handled in this function.
   */
  private void isMultipleEqualsAfterExp() {

    Pattern pattern1 = Pattern.compile("([\\+\\-\\*])([0-9]+)=[\\=]+$");
    Matcher match1 = pattern1.matcher(originalExpression);

    if (match1.find()) {
      String a = match1.group(1);
      String b = match1.group(2);

      expression = expression + a + b;

    }
  }


  /**
   * This method is used to detect the case were multiple "=" is followed by an operator.
   */
  private void isMultipleEqualsFollowedByOperator() {

    Pattern patternMultipleEquals = Pattern.compile("(.*[0-9]+)([\\+\\-\\*]+)[\\=]{2,}$");
    Matcher matchMultipleEquals = patternMultipleEquals.matcher(originalExpression);

    if (matchMultipleEquals.find()) {
      String a = calculateResult(matchMultipleEquals.group(1) + "=");
      String b = matchMultipleEquals.group(2);
      if (!isResultNegative) {
        expression = expression + b + a;
      } else {
        expression = expression + b + "@" + a;
      }
    }

  }


}