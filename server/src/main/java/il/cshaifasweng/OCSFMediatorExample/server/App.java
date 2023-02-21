package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.HelperMethods.SendEmailMethod;
import org.hibernate.Session;

/**
 * Hello world!
 *
 */
public class App 
{
    private static Session session;
	private static SimpleServer server;
    public static SendEmailMethod sendEmailMethod;
    public static void main( String[] args ) throws Exception
    {
        sendEmailMethod=new SendEmailMethod();
        server = new SimpleServer(3000);
        server.listen();
        server.Connectdata();
        System.out.println("Listening to 3000");

    }

}
