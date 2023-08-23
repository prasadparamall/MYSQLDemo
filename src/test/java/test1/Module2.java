package test1;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.SkipException;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Module2 {

	public static WebDriver driver1;

	public static void main(String [] args) throws SQLException {


//		String url = "jdbc:mysql://localhost:3306/";
//		String driver = "com.mysql.jdbc.Driver";
//		String dbName = "workshop";     //database name
//
//		String username1 = "root";
//		String password1 = "";
//		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
		
		
//		WebDriverManager.chromedriver().setup();
//
//		Map<String, Object> prefs = new HashMap<String, Object>();
//		prefs.put("profile.default_content_setting_values.notifications", 2);
//		prefs.put("credentials_enable_service", false);
//		prefs.put("profile.password_manager_enabled", false);
//		ChromeOptions options = new ChromeOptions();
//		options.setExperimentalOption("prefs", prefs);
//		options.addArguments("--disable-extensions");
//		options.addArguments("--disable-infobars");
//		options.addArguments("--remote-allow-origins=*");
//
//		driver = new ChromeDriver(options);
//
//		driver.get("https://demowebshop.tricentis.com/");



//		try {
//			Class.forName(driver).newInstance();   //create object of driver
//			con = DriverManager.getConnection(url+dbName,username1,password1);
//		}catch(Exception e){
//			System.out.println("Could not establish connection");
//			e.printStackTrace();
//			//Assert,fail("Could not establish connection");
//			throw new SkipException("Could not establish connection");
//		}
		
		//step 1
		//Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1512/pdborcl","hr","hr");
		
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/workshop","root","");
		
		
		//step 2
		Statement stmt = con.createStatement();
		//step3 : execute sql statement
		String s = "seelect * frome login";
		//step4
		ResultSet rs = stmt.executeQuery(s);  // additional
		
		while (rs.next()) {
            String name = rs.getString("username");
            System.out.println(name);
        }

//		while(rs.next())
//		{
//			String username = rs.getString("username");
//			String password = rs.getString("password");
//
//			System.out.print(username+" ");
//			System.out.println(password);

			
//			driver1.findElement(By.name("")).sendKeys(username);
//			driver1.findElement(By.name("")).sendKeys(password);
//			driver1.findElement(By.name("")).click();
//
//			if(driver1.getTitle().equals(""));
//			{
//				System.out.println("test case faild");
//			}
//			System.out.println("test case passed");
	//	}

		//step5
		con.close();

		System.out.println("program is exited");


	}
}
