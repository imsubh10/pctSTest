package utilities;

import java.io.File;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class screenshots {
	
	public static void takeSnapShot(WebDriver webdriver) throws Exception {
		
		String Filepath="C:/Users/SubhashKumar/eclipse-workspace1/Maven-Project-Subhash/Screenshots";
		 Date date = new Date();

        // display time and date using toString()
        
        String toDate=date.toString().replace(":", "-").replace(" ","-").replace("-IST", "");
        
        Filepath+="/Screenshot "+toDate+".png";

		// Convert web driver object to TakeScreenshot

		TakesScreenshot scrShot = ((TakesScreenshot) webdriver);

		// Call getScreenshotAs method to create image file

		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

		// Move image file to new destination

		File DestFile = new File(Filepath);

		// Copy file at destination

		FileUtils.copyFile(SrcFile, DestFile);

	}

}
