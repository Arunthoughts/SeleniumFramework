package mylearnings.testcomponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{

	@Override
	public boolean retry(ITestResult result) {
		int count =0;
		int maxCount =0;
		
		if(count<maxCount) {
			count++;
			return true;
		}
		return false;
	}

}
