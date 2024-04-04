package mylearnings.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsNG {
	
	public static ExtentReports getReporttObject() {
		
		//ExtentSparkReport is used to set ExtentReport
		
		String filePath = System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(filePath);
		reporter.config().setDocumentTitle("Test Reports");
		reporter.config().setReportName("Web Automation Test Reports");
		
		ExtentReports report = new ExtentReports();
		report.attachReporter(reporter);
		report.setSystemInfo("SDET", "Arun Kumar");
		return report;
	}

}
