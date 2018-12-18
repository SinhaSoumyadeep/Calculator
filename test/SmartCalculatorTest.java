import org.junit.Before;
import org.junit.Test;

import calculator.SmartCalculator;
import calculator.Calculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * This class is used to test the correctness of the Smart Calculator Implementation.
 */
public class SmartCalculatorTest {

  Calculator sm;


  /**
   * This method sets up the Calculator object.
   */
  @Before
  public void setUp() {
    sm = new SmartCalculator();
  }


  /**
   * This method checks the Normal Multi Operator, Multi Operator Expression.
   */
  @Test
  public void testNormalMultiOperatorCombo() {

    sm.input('3');
    sm.input('0');
    sm.input('+');
    sm.input('2');
    sm.input('5');
    sm.input('*');
    sm.input('1');
    sm.input('0');
    sm.input('=');
    assertEquals("550", sm.getResult());
  }

  /**
   * This method checks the preceding operator and multiple equals combo.
   */
  @Test
  public void testPrecedingOperatorAndMultiEquals() {

    sm.input('+');
    sm.input('3');
    sm.input('6');
    sm.input('9');
    sm.input('+');
    sm.input('2');
    sm.input('8');
    sm.input('1');
    sm.input('*');
    sm.input('5');
    sm.input('6');
    sm.input('8');
    sm.input('-');
    sm.input('5');
    sm.input('6');
    sm.input('=');
    sm.input('+');
    sm.input('2');
    sm.input('8');
    sm.input('=');
    sm.input('=');

    assertEquals("369200", sm.getResult());
  }

  /**
   * This method tests the Multiple Missing operand case.
   */
  @Test
  public void testMultiMissingOperand() {

    sm.input('+');
    sm.input('3');
    sm.input('+');
    sm.input('-');
    sm.input('-');
    sm.input('*');
    sm.input('3');
    sm.input('*');
    sm.input('*');
    sm.input('=');
    sm.input('*');
    sm.input('3');
    sm.input('+');
    sm.input('3');
    sm.input('=');
    sm.input('=');
    sm.input('=');
    sm.input('=');
    assertEquals("255", sm.getResult());

  }

  /**
   * This method tests expression that evaluates to Negative result.
   */
  @Test
  public void testEasyNegativeResult() {

    sm.input('3');
    sm.input('-');
    sm.input('1');
    sm.input('0');
    sm.input('+');
    sm.input('4');
    sm.input('=');

    assertEquals("-3", sm.getResult());
  }

  /**
   * This method tests the Missing operand case.
   */
  @Test
  public void testEasyMissingOperand() {

    sm.input('3');
    sm.input('+');
    sm.input('*');
    sm.input('2');
    sm.input('=');

    assertEquals("6", sm.getResult());
  }

  /**
   * This method tests Expression where multiple operator is followed By Equals.
   */
  @Test
  public void testOperatorFollowedByEquals() {

    sm.input('3');
    sm.input('*');
    sm.input('*');
    sm.input('*');
    sm.input('=');
    sm.input('=');
    sm.input('=');
    sm.input('+');
    sm.input('1');
    sm.input('0');
    sm.input('=');

    assertEquals("91", sm.getResult());
  }

  /**
   * This method tests Expression where single operator is followed By multiple Equals.
   */
  @Test
  public void testSingleOperatorFollowedByMultipleEquals() {

    sm.input('5');
    sm.input('*');
    sm.input('-');
    sm.input('+');
    sm.input('2');
    sm.input('+');
    sm.input('=');
    sm.input('=');
    sm.input('=');

    assertEquals("28", sm.getResult());

  }

  /**
   * This method tests Expression where muliple Operator expression includes missing operands and
   * multiple equals.
   */
  @Test
  public void testMultiOperatorMultiEqualsMissingOperandCombo() {

    sm.input('2');
    sm.input('*');
    sm.input('-');
    sm.input('+');
    sm.input('1');
    sm.input('1');
    sm.input('-');
    sm.input('3');
    sm.input('-');
    sm.input('5');
    sm.input('*');
    sm.input('-');
    sm.input('+');
    sm.input('2');
    sm.input('+');
    sm.input('=');
    sm.input('=');
    sm.input('=');
    assertEquals("28", sm.getResult());
  }

  /**
   * This method is to test Single Operator followedBy Single Equals.
   */
  @Test
  public void testSingleOperatorFollowedBySingleEquals() {

    sm.input('3');
    sm.input('2');
    sm.input('-');
    sm.input('=');

    assertEquals("0", sm.getResult());

  }


  /**
   * This method test single operator followed by and operand.
   */
  @Test
  public void testSingleOperatorFollowedByOperand() {

    sm.input('+');
    sm.input('3');
    sm.input('2');

    assertEquals("32", sm.getResult());
  }

  /**
   * This method test single operator followed by and operand followed By Single Equals.
   */
  @Test
  public void testSingleOperatorFollowedByOperandFollowedBySingleEquals() {

    sm.input('+');
    sm.input('3');
    sm.input('2');
    sm.input('=');

    assertEquals("32", sm.getResult());
  }


  /**
   * This method tests Expression where in the result is followed by an equals and multiple
   * Operator.
   */
  @Test
  public void testMultipleOperatorsAfterResultEquals() {

    sm.input('6');
    sm.input('-');
    sm.input('1');
    sm.input('1');
    sm.input('=');
    sm.input('*');
    sm.input('*');
    sm.input('*');
    sm.input('=');

    assertEquals("25", sm.getResult());


  }

  /**
   * This method tests Expression where in the result is followed by an equals and single Operator.
   */
  @Test
  public void testResultFollowedBySingleOperator() {

    sm.input('3');
    sm.input('*');
    sm.input('*');
    sm.input('2');

    sm.input('*');

    sm.input('=');

    sm.input('*');
    sm.input('=');

    assertEquals("1296", sm.getResult());
  }

  /**
   * This method tests Multiple Equals in Expression.
   */
  @Test
  public void testMultipleEquals() {

    sm.input('3');
    sm.input('+');
    sm.input('2');
    sm.input('=');
    sm.input('=');
    sm.input('=');

    assertEquals("9", sm.getResult());


  }


  /**
   * This method tests Result Followed By Operator Followed By Multiple Equals.
   */
  @Test
  public void testResultFollowedByOperatorFollowedByMultipleEquals() {

    sm.input('5');
    sm.input('-');
    sm.input('1');
    sm.input('2');
    sm.input('*');
    sm.input('=');
    sm.input('=');
    sm.input('=');
    sm.input('=');

    assertEquals("-16807", sm.getResult());


  }

  /**
   * This method tests Multiple Operator Followed By Multiple Equals Followed By Multiple
   * Operators.
   */
  @Test
  public void testMultipleOperatorFollowedByMultipleEqualsFollowedByMultipleOperators() {

    sm.input('3');
    sm.input('*');
    sm.input('2');
    sm.input('*');
    sm.input('2');
    sm.input('*');
    sm.input('*');
    sm.input('*');
    sm.input('=');
    sm.input('=');
    sm.input('=');
    sm.input('=');
    sm.input('*');
    sm.input('*');
    sm.input('*');
    sm.input('4');
    sm.input('=');

    assertEquals("995328", sm.getResult());

  }

  /**
   * This method tests Negative Results.
   */
  @Test
  public void testComplicatedNegativeResult() {

    sm.input('3');
    sm.input('-');
    sm.input('1');
    sm.input('3');
    sm.input('*');
    sm.input('2');
    sm.input('*');
    sm.input('*');
    sm.input('*');
    sm.input('=');
    sm.input('=');
    sm.input('=');
    sm.input('=');
    sm.input('*');
    sm.input('*');
    sm.input('*');
    sm.input('4');
    sm.input('=');

    assertEquals("-12800000", sm.getResult());

  }


  /**
   * This method tests Operator Followed By Multiple Equals.
   */
  @Test
  public void testOperatorFollowedByMultipleEquals() {
    sm.input('3');
    sm.input('2');
    sm.input('+');
    sm.input('=');
    sm.input('=');
    sm.input('=');
    sm.input('=');
    assertEquals("160", sm.getResult());
  }

  /**
   * This method test Starting Operator and Multiple Equals Combo.
   */
  @Test
  public void testStartingOperatorMultiEquals() {
    sm.input('+');
    sm.input('3');
    sm.input('2');
    sm.input('-');
    sm.input('2');
    sm.input('4');
    sm.input('=');
    sm.input('=');
    sm.input('=');
    assertEquals("-40", sm.getResult());
  }

  /**
   * This method tests long operands expression.
   */
  @Test
  public void testExpressionWith() {
    sm.input('+');
    sm.input('3');
    sm.input('2');
    sm.input('1');
    sm.input('6');
    sm.input('7');
    sm.input('8');
    sm.input('9');
    sm.input('0');
    sm.input('0');
    sm.input('*');
    sm.input('1');
    sm.input('2');
    sm.input('3');
    sm.input('4');
    sm.input('1');
    sm.input('2');
    sm.input('-');
    sm.input('1');
    sm.input('0');
    sm.input('=');
    assertEquals("-10", sm.getResult());

  }

  /**
   * This method tests the Overflow of the Operand.
   */
  @Test
  public void testOverFlowOpearand() {

    try {
      sm.input('1');
      sm.input('1');
      sm.input('1');
      sm.input('1');
      sm.input('1');
      sm.input('1');
      sm.input('1');
      sm.input('1');
      sm.input('1');
      sm.input('1');
      sm.input('1');

    } catch (RuntimeException e) {
      assertEquals("Operand Overflowed", e.getMessage());
      assertEquals("1111111111", sm.getResult());
    }

  }


  /**
   * This method tests the just equals case.
   */
  @Test
  public void testJustEquals() {

    sm.input('=');
    assertEquals("", sm.getResult());
  }

  /**
   * This method tests if the implementation can handle Preceding Zero.
   */
  @Test
  public void testZeroPrecedingAnOperand() {

    sm.input('0');
    sm.input('1');
    assertEquals("1", sm.getResult());
  }


  /**
   * This method tests if Multiple Operators can be ignored when at the beginning of the
   * Expression.
   */
  @Test
  public void testIgnoreFirstOperators() {

    sm.input('+');
    sm.input('-');
    sm.input('+');
    sm.input('3');
    sm.input('2');
    sm.input('-');
    sm.input('2');
    sm.input('4');
    sm.input('=');
    sm.input('=');
    sm.input('=');
    assertEquals("-40", sm.getResult());
  }


  /**
   * This method tests Multiple Equals After Three Valid Inputs.
   */
  @Test
  public void testSmartCalculatorMultipleEqualsAfterThreeValidInputs() {
    String exp = "3+2+2===";

    for (int i = 0; i < exp.length(); i++) {
      sm.input(exp.charAt(i));
    }
    assertEquals("11", sm.getResult());

  }

  /**
   * This method tests Subtraction.
   */
  @Test
  public void testSmartCalculatorSubtraction() {
    String exp = "3-2-10=";

    for (int i = 0; i < exp.length(); i++) {
      sm.input(exp.charAt(i));
    }
    assertEquals("-9", sm.getResult());

  }

  /**
   * This method tests Multiply OverFlow.
   */
  @Test
  public void testSmartCalculatorOverflowMultiply() {
    String exp = "11111111*11111111=";

    for (int i = 0; i < exp.length(); i++) {
      sm.input(exp.charAt(i));
    }
    assertEquals("0", sm.getResult());

  }

  /**
   * This method tests Add OverFlow.
   */
  @Test
  public void testSmartCalculatorOverflowAdd() {
    String exp = Integer.MAX_VALUE + "+5=";

    for (int i = 0; i < exp.length(); i++) {
      sm.input(exp.charAt(i));
    }
    assertEquals("0", sm.getResult());

  }

  /**
   * This method tests Invalid Second Input.
   */
  @Test
  public void testSmartCalculatorInvalidSecondInput() {
    String exp = "3b+10=";

    try {
      for (int i = 0; i < exp.length(); i++) {
        sm.input(exp.charAt(i));
      }
      fail();
    } catch (Exception e) {
      assertEquals("Invalid Input", e.getMessage());
    }


  }

  /**
   * This method tests Simple Addition.
   */
  @Test
  public void testSmartCalculatorAddition() {
    String exp = "3+10+45+33=";


    for (int i = 0; i < exp.length(); i++) {
      sm.input(exp.charAt(i));
    }

    assertEquals("91", sm.getResult());

  }

  /**
   * This method tests Subtract Overflow.
   */
  @Test
  public void testSmartCalculatorOverflowSubtract() {
    String exp = Integer.MAX_VALUE + "-" + Integer.MAX_VALUE
            + "-" + Integer.MAX_VALUE + "-" + Integer.MAX_VALUE + "=";

    for (int i = 0; i < exp.length(); i++) {
      sm.input(exp.charAt(i));
    }
    assertEquals("0", sm.getResult());

  }

  /**
   * This method tests Operator Operand After Result.
   */
  @Test
  public void testSmartCalculatorOperatorOperandAfterResult() {
    String exp = "32+24+45=+23";

    for (int i = 0; i < exp.length(); i++) {
      sm.input(exp.charAt(i));
    }
    assertEquals("101+23", sm.getResult());

  }


  /**
   * This method tests Overflowing Arguments.
   */
  @Test
  public void testSmartCalculatorOverflowArguments() {
    String exp = "111111111111-12=";
    try {
      for (int i = 0; i < exp.length(); i++) {
        sm.input(exp.charAt(i));
      }
      fail();
    } catch (Exception e) {
      assertEquals("Operand Overflowed", e.getMessage());
    }

  }


  /**
   * This method tests Invalid first Input.
   */
  @Test
  public void testSmartCalculatorInvalidFirstInput() {
    String exp = "c+23-34=";
    try {
      for (int i = 0; i < exp.length(); i++) {
        sm.input(exp.charAt(i));
      }
      fail();
    } catch (Exception e) {
      assertEquals("Invalid Input", e.getMessage());
    }

  }


  /**
   * This method tests Multiplication.
   */
  @Test
  public void testSmartCalculatorMultiplication() {
    String exp = "45*23*34=*32=";

    for (int i = 0; i < exp.length(); i++) {
      sm.input(exp.charAt(i));
    }

    assertEquals("1126080", sm.getResult());


  }

  /**
   * This method tests Operation after Equals.
   */
  @Test
  public void testSmartCalculatorOperationAfterEquals() {
    String exp = "32+24+50=+24+50=";

    for (int i = 0; i < exp.length(); i++) {
      sm.input(exp.charAt(i));
    }
    assertEquals("180", sm.getResult());


  }

  /**
   * This method tests Two Operand and One Operator.
   */
  @Test
  public void testTwoOperandsOneOperator() {
    loopThrough("32+24");
    assertEquals("32+24", sm.getResult());
  }

  /**
   * This method tests Simple Expression.
   */
  @Test
  public void testSimpleExpression() {
    loopThrough("32+24=");
    assertEquals("56", sm.getResult());
  }

  /**
   * This method tests negative second operand.
   */
  @Test
  public void testNegativeSecondOperand() {
    loopThrough("32+-780=");
    assertEquals("-748", sm.getResult());

  }

  /**
   * This method tests Multiple Operands String.
   */
  @Test
  public void testMultipleOperandsString() {
    loopThrough("4567+2-7*5");
    assertEquals("4562*5", sm.getResult());
  }

  /**
   * This method tests just Operand.
   */
  @Test
  public void testOperandString() {
    loopThrough("32");
    assertEquals("32", sm.getResult());
  }

  /**
   * This method tests Single Operator After Operand.
   */
  @Test
  public void testSingleOperatorAfterOperandString() {
    loopThrough("32+");
    assertEquals("32+", sm.getResult());

  }

  /**
   * This method tests Single Operator Equals Combination.
   */
  @Test
  public void testSingleOperatorString() {
    loopThrough("32+=");
    assertEquals("64", sm.getResult());
  }

  /**
   * This method tests expression that starts with an operator.
   */
  @Test
  public void testInvalidFirst() {
    loopThrough("+32+24=");
    assertEquals("56", sm.getResult());

  }

  /**
   * This method tests Addition Combination String Output.
   */
  @Test
  public void testAddComboString() {
    loopThrough("32+3+");
    assertEquals("35+", sm.getResult());
  }

  /**
   * This method tests Zero as First Character.
   */
  @Test
  public void testZeroFirstCharacter() {
    loopThrough("0++6");
    assertEquals("0+6", sm.getResult());
  }

  /**
   * This method tests Preceding Zero before an Operand Expression.
   */
  @Test
  public void testZeroComboCharacter() {

    loopThrough("02+3452");
    assertEquals("2+3452", sm.getResult());
  }


  /**
   * This method tests Multiple Operators Combination.
   */
  @Test
  public void testMultipleComboOperatos() {
    loopThrough("757+4-3*5=");
    assertEquals("3790", sm.getResult());
  }

  /**
   * This method tests Invalid Beginning Input.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNonValidInputBeginning() {
    loopThrough("x1230+621");

  }

  /**
   * This method tests Invalid Middle Input.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNonValidInputBetween() {
    loopThrough("3130x+613");

  }

  /**
   * This method tests Invalid End Input.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNonValidInputEnd() {

    loopThrough("331230+1130a");

  }

  /**
   * This method tests the Negative result String.
   */
  @Test
  public void testNegativeResultString() {
    loopThrough("32-136+3");
    assertEquals("-104+3", sm.getResult());
  }

  /**
   * This method tests Negative Result.
   */
  @Test
  public void testNegativeResult() {
    loopThrough("32-856+365-250=");
    assertEquals("-709", sm.getResult());
  }

  /**
   * This method tests Clearing the Beginning of the Expression.
   */
  @Test
  public void testClearBeginningExp() {
    loopThrough("C47-168+33-99=");
    assertEquals("-187", sm.getResult());
  }

  /**
   * This method tests Clearing the Middle of the Expression.
   */
  @Test
  public void testClearMiddleExp() {
    loopThrough("50-34C8+2*23=");
    assertEquals("230", sm.getResult());
  }

  /**
   * This method tests Clearing the End of the Expression.
   */
  @Test
  public void testClearEndExp() {
    loopThrough("789-898+37-256=C");
    assertEquals("", sm.getResult());
  }

  /**
   * This method tests Two Operators Back to back.
   */
  @Test
  public void testBackToBackTwoOperators() {
    loopThrough("789-+898+37");
    assertEquals("1687+37", sm.getResult());
  }

  /**
   * This method tests Multiple Negative Expression.
   */
  @Test
  public void testMultipleNegativeExpression() {
    loopThrough("3-10-10=");
    assertEquals("-17", sm.getResult());
  }

  /**
   * This method tests After Equals Expression.
   */
  @Test
  public void testAfterEqualsExpression() {
    loopThrough("244+56=-200=");
    assertEquals("100", sm.getResult());
  }

  /**
   * This method tests Operator After Equals Expression.
   */
  @Test
  public void testAfterEqualsOperatorExpression() {
    loopThrough("244+56=-");
    assertEquals("300-", sm.getResult());
  }


  /**
   * This method takes in an Expression and breaks it down into characters and feed it to Calculator
   * Input.
   *
   * @param exp Expression passed.
   */
  private void loopThrough(String exp) {
    for (int i = 0; i < exp.length(); i++) {
      sm.input(exp.charAt(i));
    }
  }


}