/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DangNhapDAO {
   private static Connection conn=null;
    private static Statement stmt=null;
    public static boolean checkConnection()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/doanjava?zeroDateTimeBehavior=convertToNull&useUnicode=true&characterEncoding=UTF-8";
            String user = "root";
            String pass = "123";
            conn = DriverManager.getConnection(url, user, pass);
            stmt = conn.createStatement();
            return true;
            
            
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
    public static void closeConnection()
    {
        try {
            if(conn!=null)
            {
                conn.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public boolean checkDN(String a , String b)
    {
        boolean result = false;
        if(checkConnection())
        {
            try {
                String sql="SELECT * FROM Taikhoan";
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next())
                {
                    if(a.equals(rs.getString(3)) && b.equals(rs.getString(4)))
                    {
                        result =true;
                    }
                }
                
            } catch (Exception e) {
                System.out.println(e);
            }
            finally{
                closeConnection();
                }
        }
        return result;
        
    }
    public String tao(String a, String b)
    {
        String c=null;
        if(checkConnection())
        {
            try {
                
                if(checkDN(a, b))
                {
                    c=a;
                }
                
            } catch (Exception e) {
                System.out.println(e);
            }
            finally{
                closeConnection();
                }
        }
        return c;
    }
    
}
