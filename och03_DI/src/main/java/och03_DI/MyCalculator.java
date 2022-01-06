package och03_DI;

public class MyCalculator {
	Calculator calculator;
	private int firstNum;
	private int secondNum;
	
	public void add() {
		calculator.addition(firstNum, secondNum);
	}
	public void sub() {
		calculator.substraction(firstNum, secondNum);
	}
	public void mul() {
		calculator.multiplication(firstNum, secondNum);
	}
	public void div() {
		calculator.division(firstNum, secondNum);
	}
	
	public void setCalculator(Calculator calculator) {
		this.calculator = calculator;
	}
	public void setFirstNum(int firstNum) {
		this.firstNum = firstNum;
	}
	public void setSecondNum(int secondNum) {
		this.secondNum = secondNum;
	}
}
