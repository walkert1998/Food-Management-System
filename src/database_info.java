
public class database_info
{
    public database_info()
    {
        
    }
    
    public static String getDBPAth()
    {
        return "jdbc:postgresql://35.203.96.50:5432/postgres";
    }
    
    public static String getUsername()
    {
        return "postgres";
    }
    
    public static String getPassword()
    {
        return "password";
    }

}
