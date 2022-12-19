package il.cshaifasweng.OCSFMediatorExample.server;

import org.hibernate.Session;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    private static Session session;
	private static SimpleServer server;
    public static void main( String[] args ) throws IOException
    {
        server = new SimpleServer(3000);
        server.listen();
        server.Connectdata();
        System.out.println("Listening to 3000");
    }

}
