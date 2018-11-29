package info.seleniumcucumber.methods;

import env.DriverUtil;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.text.html.HTMLDocument;
import java.io.*;
import java.util.List;

public class SelectElementByType 
{
	protected WebDriver driver;
	protected WebDriverWait wait;
	public SelectElementByType() {
		driver = DriverUtil.getDefaultDriver();
		wait = new WebDriverWait(driver, 30);
	}
	/**Method to select element 'by' type
	 * @param type : String : 'By' type
	 * @param access_name : String : Locator value
	 * @return By
	 */
	public By getelementbytype(String type,String access_name)
	{
		File staticHtml = new File("staticHtml.html");
		if (!staticHtml.exists()) {
			try {
				FileUtils.touch(staticHtml);
			}catch (IOException e){
				System.out.println("Caught exception during file creation...");
			}
		}
		//List<WebElement> list = driver.findElements(By.xpath("//*[@id='row']/td/b"));
		System.out.println(type + " " + access_name);

		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter out = null;
		try {
			fw = new FileWriter("staticHtml.html", true);
			bw = new BufferedWriter(fw);
			out = new PrintWriter(bw);

		}catch (IOException f) {
			System.out.println("Caught Exception during write to file");
		}

		switch(type)
		{
			case "id" :
					WebElement idElement = driver.findElement(By.id(access_name));
					WebElement parent = idElement.findElement(By.xpath(".."));
					//document.getElementById('username').parent.getElementsByTagName('label')[0];
					WebElement label = parent.findElement(By.tagName("label"));
					out.print(label.getText());
					out.print("<br/>");
					out.println(idElement.getAttribute("outerHTML"));
					out.print("<br/>");
					out.close();
				return By.id(access_name);
			case "name" : return By.name(access_name);
			case "class" :
					WebElement classElement = driver.findElement(By.className(access_name));
					out.println(classElement.getAttribute("outerHTML"));
					out.println();
					out.close();
				return By.className(access_name);
			case "xpath" : return By.xpath(access_name);
			case "css" : return By.cssSelector(access_name);
			case "linkText" : return By.linkText(access_name);
			case "partialLinkText" : return By.partialLinkText(access_name);
			case "tagName" : return By.tagName(access_name);
		default : return null;
						
		}
		/*if(type.equals("id"))
			return By.id(access_name);
		else if (type.equals("name"))
			return By.name(access_name);
		else if (type.equals("class"))
			return By.className(access_name);
		else if (type.equals("xpath"))
			return By.xpath(access_name);
		else if (type.equals("css"))
			return By.cssSelector(access_name);
		else if(type.equals("linkText"))
			return By.linkText(access_name);
		else if(type.equals("partialLinkText"))
			return By.partialLinkText(access_name);
		else if(type.equals("tagName"))
			return By.tagName(access_name);
		else
			return null;*/
	}
}
