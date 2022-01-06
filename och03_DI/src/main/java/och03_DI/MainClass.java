package och03_DI;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		//전통적 
//		MyCalculator myCalculator = new MyCalculator();
//		myCalculator.setCalculator(new Calculator());
//		myCalculator.setFirstNum(10);
//		myCalculator.setSecondNum(2);
//		myCalculator.add();
//		myCalculator.sub();
		
		//DI setting
		String configLocation = "classpath:applicationCTX.xml"; //classpath는 resources파일을 찾아감 
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation);
		MyCalculator myCalculator = ctx.getBean("myCalculator", MyCalculator.class);
		
		//위3줄이 MyCalculator myCalculator = new MyCalculator();와 똑같음 
		
		myCalculator.add();
		myCalculator.sub();
		myCalculator.mul();
		myCalculator.div();
	}

}
