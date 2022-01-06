package och06_aop1;

import org.aspectj.lang.ProceedingJoinPoint;

public class LogAop {
	//Around Advice에서 사용할 공통기능 메서드는, 대부분 파라미터로 전달받은 ProceedingJoinPoint의 proceed() 메서드만 호출
	//Proxy
	public Object loggerAop(ProceedingJoinPoint joinpoint) throws Throwable{ //핵심관심사와 횡단관심사의 결합 위치를 정해주는 거가 조인포인트 
		String signatureStr = joinpoint.getSignature().toString();
		
		//핵심관심사의 수행 메소드
		System.out.println(signatureStr +" is start.");
		long st = System.currentTimeMillis();
		
		try {
			//핵심관심사 수행
			Object obj = joinpoint.proceed();
			return obj;
		}finally {
			long et = System.currentTimeMillis();
			System.out.println(signatureStr+ " is finished.");
			System.out.println(signatureStr +" 경과시간: "+(et-st));
		}
		
	}
}
