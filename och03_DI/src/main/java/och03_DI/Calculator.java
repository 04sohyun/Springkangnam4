package och03_DI;

public class Calculator {
	public void addition(int f, int s) { //call by value(기본형에의한 호출)
		System.out.println("addtion()");
		int result = f+s;
		System.out.println(f+"+"+s+"="+result);
	}
	public void substraction(int f, int s) { //call by value(기본형에의한 호출)
		System.out.println("substraction()");
		int result = f-s;
		System.out.println(f+"-"+s+"="+result);
	}
	public void multiplication(int f, int s) { //call by value(기본형에의한 호출)
		System.out.println("multiplication()");
		int result = f*s;
		System.out.println(f+"*"+s+"="+result);
	}
	public void division(int f, int s) { //call by value(기본형에의한 호출)
		System.out.println("division()");
		int result = f/s;
		System.out.println(f+"/"+s+"="+result);
	}
}
