package test1;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Module1 {

	public static WebDriver driver;

	String url = "jdbc:mysql://localhost:3306/";
	String driver1 = "com.mysql.jdbc.Driver";
	String dbName = "workshop";     //database name

	String username1 = "root";
	String password1 = "";

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	@BeforeTest
	public void connect () {

		try {
			Class.forName(driver1).newInstance();   //create object of driver
			conn = DriverManager.getConnection(url+dbName,username1,password1);
		}catch(Exception e){
			System.out.println("Could not establish connection");
			e.printStackTrace();
			//Assert,fail("Could not establish connection");
			throw new SkipException("Could not establish connection");
		}

	}

	@Test
	public void testDB() throws Exception {

		WebDriverManager.chromedriver().setup();

		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		options.addArguments("--disable-extensions");
		options.addArguments("--disable-infobars");
		options.addArguments("--remote-allow-origins=*");

		driver = new ChromeDriver(options);

		driver.manage().window().maximize();
		driver.get("https://demowebshop.tricentis.com/");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		Thread.sleep(1000);
		

		try {
			//pstmt = conn.prepareStatement("select * from employee where Name = ?");
			pstmt = conn.prepareStatement("select * from login");
			//pstmt.setString(1, "abc3");
			//pstmt.setInt(2,30);
			rs =pstmt.executeQuery();

			while(rs.next()){
				//System.out.println(rs.getString(1));
				System.out.println(rs.getString(1) + " --- " + rs.getString(2));
			
				String username2 = rs.getString("uname");
 				String password2 = rs.getString("pwd");
 				
 				driver.findElement(By.xpath("//a[@class='ico-login']")).click();
 				Thread.sleep(1000);

				
 				driver.findElement(By.xpath("//input[@id='Email']")).clear();
				driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(username2);
 				
 				driver.findElement(By.xpath("//input[@id='Password']")).clear();
				driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(password2);
				driver.findElement(By.xpath("//input[@value='Log in']")).click();
				Thread.sleep(2000);
				
				try {
				driver.findElement(By.xpath("//a[@class='ico-logout']")).click();
				Thread.sleep(2000);
				}catch(Exception e){
					
				}
			
			}
		}catch(Exception e){
			System.out.println("Exception in firing query");
			e.printStackTrace();
			Assert.fail("Exception in firing query" + e.getMessage());
		}

	}

	@AfterTest
	public void disConnect() {

		try {
			if(rs!=null)
				rs.close();

			if(pstmt!=null)
				pstmt.close();

			if((conn!=null) && (!conn.isClosed()))
				conn.close();

		}catch (SQLException e) {
			e.printStackTrace();
		}

	}
}