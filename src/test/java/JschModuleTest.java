import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class JschModuleTest {

    @Test
    public void jschTest() {
        WebDriver driver;
        WebDriverWait wait;

        String host="x.x.x.x";
        String user="username";
        String password="password";
        String command="ls -ltr\t";

        System.setProperty("webdriver.chrome.driver","C:\\Users\\victor.carrillo\\automation\\JschModule\\drivers\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:7400/");

        try{
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            Session session=jsch.getSession(user, host, 22);
            session.setPassword(password);
            session.setConfig(config);
            session.connect();
            System.out.println("Connected");

            ChannelExec channel=(ChannelExec) session.openChannel("exec");
            BufferedReader in=new BufferedReader(new InputStreamReader(channel.getInputStream()));

            channel.setCommand(command);
            channel.connect();
            String msg=null;
            while((msg=in.readLine())!=null){
                System.out.println(msg);
            }
            channel.disconnect();
            session.disconnect();
            System.out.println("DONE");

        }catch(Exception e){
            e.printStackTrace();
        }


        //driver.quit();




    }

}
