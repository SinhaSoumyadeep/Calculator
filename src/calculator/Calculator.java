package calculator;

/**
 * This interface simulates the operations of a calculator that works only with whole numbers. It
 * supports only three arithmetic operations: addition, subtraction and multiplication. To simulate
 * the button press functionality, the calculator accepts inputs one character at a time. For
 * example, to compute 32+243 the inputs would be ’3’, ’2’, ’+’, ’2’, ’4’, ’3’, ’=’ in that
 * sequence. Our calculator will also support displaying the “current” state of the calculator.
 */
public interface Calculator {

  /**
   * This method takes a single character as input and tries to calculate the result of the
   * expression.
   *
   * @param input takes a single character as its only argument.
   * @return Calculator object as a result of processing the input.
   */
  public Calculator input(char input);

  /**
   * This method the current state of the calculator which might be result of the expression or the
   * current state of the inputted expression. Our calculator will also support displaying the
   * current state of the calculator.
   *
   * @return the current result of the calculator.
   */
  public String getResult();
}
