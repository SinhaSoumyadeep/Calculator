import org.junit.Before;
import org.junit.Test;

import calculator.SimpleCalculator;
import calculator.Calculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


/**
 * This class is used to test the correctness of the Simple Calculator Implementation.
 */
public class SimpleCalculatorTest {

  private Calculator si;

  /**
   * This method sets up the Calculator object.
   */
  @Before
  public void setUp() {
    si = new SimpleCalculator();
  }

  /**
   * This method tests Multiple Equals After Three Valid Inputs.
   */
  @Test
  public void testSimpleCalculatorMultipleEqualsAfterThreeValidInputs() {
    String exp = "3+2+2===";

    for (int i = 0; i < exp.length(); i++) {
      si.input(exp.charAt(i));
    }
    assertEquals("7", si.getResult());

  }

  /**
   * This method tests Subtraction.
   */
  @Test
  public void testSimpleCalculatorSubtraction() {
    String exp = "3-2-10=";

    for (int i = 0; i < exp.length(); i++) {
      si.input(exp.charAt(i));
    }
    assertEquals("-9", si.getResult());

  }

  /**
   * This method tests Multiply OverFlow.
   */
  @Test
  public void testSimpleCalculatorOverflowMultiply() {
    String exp = "11111111*11111111=";

    for (int i = 0; i < exp.length(); i++) {
      si.input(exp.charAt(i));
    }
    assertEquals("0", si.getResult());

  }

  /**
   * This method tests Add OverFlow.
   */
  @Test
  public void testSimpleCalculatorOverflowAdd() {
    String exp = Integer.MAX_VALUE + "+5=";

    for (int i = 0; i < exp.length(); i++) {
      si.input(exp.charAt(i));
    }
    assertEquals("0", si.getResult());

  }

  /**
   * This method tests Invalid Second Input.
   */
  @Test
  public void testSimpleCalculatorInvalidSecondInput() {
    String exp = "3b+10=";

    try {
      for (int i = 0; i < exp.length(); i++) {
        si.input(exp.charAt(i));
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
  public void testSimpleCalculatorAddition() {
    String exp = "3+10+45+33=";


    for (int i = 0; i < exp.length(); i++) {
      si.input(exp.charAt(i));
    }

    assertEquals("91", si.getResult());

  }

  /**
   * This method tests Subtract Overflow.
   */
  @Test
  public void testSimpleCalculatorOverflowSubtract() {
    String exp = Integer.MAX_VALUE + "-" + Integer.MAX_VALUE
            + "-" + Integer.MAX_VALUE + "-" + Integer.MAX_VALUE + "=";

    for (int i = 0; i < exp.length(); i++) {
      si.input(exp.charAt(i));
    }
    assertEquals("0", si.getResult());

  }

  /**
   * This method tests Operator Operand After Result.
   */
  @Test
  public void testSimpleCalculatorOperatorOperandAfterResult() {
    String exp = "32+24+45=+23";

    for (int i = 0; i < exp.length(); i++) {
      si.input(exp.charAt(i));
    }
    assertEquals("101+23", si.getResult());

  }


  /**
   * This method tests Overflowing Arguments.
   */
  @Test
  public void testSimpleCalculatorOverflowArguments() {
    String exp = "111111111111-12=";
    try {
      for (int i = 0; i < exp.length(); i++) {
        si.input(exp.charAt(i));
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
  public void testSimpleCalculatorInvalidFirstInput() {
    String exp = "c+23-34=";
    try {
      for (int i = 0; i < exp.length(); i++) {
        si.input(exp.charAt(i));
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
  public void testSimpleCalculatorMultiplication() {
    String exp = "45*23*34=*32=";

    for (int i = 0; i < exp.length(); i++) {
      si.input(exp.charAt(i));
    }

    assertEquals("1126080", si.getResult());


  }

  /**
   * This method tests Operation after Equals.
   */
  @Test
  public void testSimpleCalculatorOperationAfterEquals() {
    String exp = "32+24+50=+24+50=";

    for (int i = 0; i < exp.length(); i++) {
      si.input(exp.charAt(i));
    }
    assertEquals("180", si.getResult());


  }

  /**
   * This method tests Two Operand and One Operator.
   */
  @Test
  public void testTwoOperandsOneOperator() {
    loopThrough("32+24");
    assertEquals("32+24", si.getResult());
  }

  /**
   * This method tests Simple Expression.
   */
  @Test
  public void testSimpleExpression() {
    loopThrough("32+24=");
    assertEquals("56", si.getResult());
  }

  /**
   * This method tests negative second operand.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeSecondOperand() {
    loopThrough("32+-780=");


  }

  /**
   * This method tests Multiple Operands String.
   */
  @Test
  public void testMultipleOperandsString() {
    loopThrough("4567+2-7*5");
    assertEquals("4562*5", si.getResult());
  }

  /**
   * This method tests just Operand.
   */
  @Test
  public void testOperandString() {
    loopThrough("32");
    assertEquals("32", si.getResult());
  }

  /**
   * This method tests Single Operator After Operand.
   */
  @Test
  public void testSingleOperatorAfterOperandString() {
    loopThrough("32+");
    assertEquals("32+", si.getResult());

  }

  /**
   * This method tests Single Operator Equals Combination.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSingleOperatorString() {
    loopThrough("32+=");

  }

  /**
   * This method tests expression that starts with an operator.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidFirst() {
    loopThrough("+32+24=");

  }

  /**
   * This method tests Addition Combination String Output.
   */
  @Test
  public void testAddComboString() {
    loopThrough("32+3+");
    assertEquals("35+", si.getResult());
  }

  /**
   * This method tests Zero as First Character.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testZeroFirstCharacter() {
    loopThrough("0++6");

  }

  /**
   * This method tests Preceding Zero before an Operand Expression.
   */
  @Test
  public void testZeroComboCharacter() {

    loopThrough("02+3452");
    assertEquals("2+3452", si.getResult());
  }


  /**
   * This method tests Multiple Operators Combination.
   */
  @Test
  public void testMultipleComboOperatos() {
    loopThrough("757+4-3*5=");
    assertEquals("3790", si.getResult());
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
    assertEquals("-104+3", si.getResult());
  }

  /**
   * This method tests Negative Result.
   */
  @Test
  public void testNegativeResult() {
    loopThrough("32-856+365-250=");
    assertEquals("-709", si.getResult());
  }

  /**
   * This method tests Clearing the Beginning of the Expression.
   */
  @Test
  public void testClearBeginningExp() {
    loopThrough("C47-168+33-99=");
    assertEquals("-187", si.getResult());
  }

  /**
   * This method tests Clearing the Middle of the Expression.
   */
  @Test
  public void testClearMiddleExp() {
    loopThrough("50-34C8+2*23=");
    assertEquals("230", si.getResult());
  }

  /**
   * This method tests Clearing the End of the Expression.
   */
  @Test
  public void testClearEndExp() {
    loopThrough("789-898+37-256=C");
    assertEquals("", si.getResult());
  }

  /**
   * This method tests Two Operators Back to back.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBackToBackTwoOperators() {
    loopThrough("789-+898+37");

  }

  /**
   * This method tests Multiple Negative Expression.
   */
  @Test
  public void testMultipleNegativeExpression() {
    loopThrough("3-10-10=");
    assertEquals("-17", si.getResult());
  }

  /**
   * This method tests After Equals Expression.
   */
  @Test
  public void testAfterEqualsExpression() {
    loopThrough("244+56=-200=");
    assertEquals("100", si.getResult());
  }

  /**
   * This method tests Operator After Equals Expression.
   */
  @Test
  public void testAfterEqualsOperatorExpression() {
    loopThrough("244+56=-");
    assertEquals("300-", si.getResult());
  }


  /**
   * This method takes in an Expression and breaks it down into characters and feed it to Calculator
   * Input.
   *
   * @param exp Expression passed.
   */
  private void loopThrough(String exp) {
    for (int i = 0; i < exp.length(); i++) {
      si.input(exp.charAt(i));
    }
  }


}