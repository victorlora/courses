import java.util.*;
/**
 * Write a description of class URL here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class URL
{
    //***************************************************************
    //***Fields
    private String protocol;
    private String domainName;
    private int    portNumber;
    private String resourcePath;
    private String queryString;
    private String fragmentIdentifier;

    public static final Scanner keyboard = new Scanner(System.in);

    //***************************************************************
    //***Constructor
    //***************************************************************
    //***Methods

    public static void getProtocol(String URL)
    {
        String[] newURL = URL.split("://");
        System.out.println("Protocol: " + newURL[0]);
    }

    public static void getDomain(String URL)
    {
        String tempURL = null;
        if (URL.indexOf("http://") != -1 )  {
            String[] newURL = URL.split("://");
            tempURL = newURL[1];
        }
        String[] newURL = tempURL.split("/");
        System.out.println("Domain: " + newURL[0]);
    }

    public static void getResourcePath(String URL)
    {
        String tempURL = null;
        String path    = "";
        if (URL.indexOf("http://") != -1 )  {
            String[] newURL = URL.split("://");
            tempURL = newURL[1];
        }
        String[] newURL = tempURL.split("/");
        for(int i=1; i<newURL.length; i++) {
            path += "/" + newURL[i];
        }
        System.out.println("Resource Path: " + path);
    }
    
    public static void getPortNumber(String URL)
    {
        String tempURL = null;
        if (URL.indexOf("http://") != -1 )  {
            String[] newURL = URL.split("://");
            tempURL = newURL[1];
        }
        String[] newURL = tempURL.split(":");
        newURL = newURL[1].split("/");
        System.out.print("Port Number: " + newURL[0]);
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public static void main(String[] args)
    {
        System.out.println("Type your URL: ");
        String URL = keyboard.nextLine();
        getProtocol(URL);
    }
}
