/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.NhaCungCapDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import org.apache.xmlbeans.impl.regex.REUtil;


public class NhaCungCapDAO {
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
    public int getSoluong()
    {
        int a = 0 ; 
        if(checkConnection())
        {
            try {
                String sql="select MaNCC from nhacungcap";
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next())
        {
            a=rs.getInt(1);
        }
            } catch (Exception e) {
            }
            finally{
                closeConnection();
            }
        }
        
          return a;  
    }
    public boolean them(NhaCungCapDTO a)
    {
        boolean result = false;
        if(checkConnection())
        {
            try {
                int c=1;
                String sql = "insert into nhacungcap value(?,?,?,?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery("SELECT * FROM nhacungcap");
                while(rs.next())
                {
                    c++;
                }
                pstmt.setInt(1, a.getMaNCC());
                pstmt.setString(2, a.getTenNCC());
                pstmt.setString(3,a.getDiaChiNCC());
                pstmt.setString(4,a.getSDT());
                if(pstmt.executeUpdate()>=1)
                {
                    result=true;
                }
            } catch (Exception e) {
            }
            finally{
                closeConnection();
            }
        }
        return result;
    }
    public ArrayList<NhaCungCapDTO> getAllNCC()
    {
        ArrayList<NhaCungCapDTO> arr = new ArrayList<NhaCungCapDTO>();
        if(checkConnection())
        {
            try {
                String sql="select * from nhacungcap";
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next())
                {
                    NhaCungCapDTO ncc = new NhaCungCapDTO();
                    ncc.setMaNCC(rs.getInt("MaNCC"));
                    ncc.setTenNCC(rs.getString("TenNCC"));
                    ncc.setDiaChiNCC(rs.getString("DiaChi"));
                    ncc.setSDT(rs.getString("SDT"));
                    arr.add(ncc);
                }
                
            } catch (Exception e) {
            }
            finally{
                closeConnection();
            }
        }
        return arr;
    }
    public static void main(String[]args)
    {
        ArrayList<NhaCungCapDTO> arr = new ArrayList<>();
        NhaCungCapDAO ncc = new NhaCungCapDAO();
        arr=ncc.getAllNCC();
        for(int i =0; i<arr.size();i++)
        {
            System.out.println(arr.get(i));
        }
    }
    public boolean xoa(NhaCungCapDTO nc)
    {
        boolean result=false;
        if(checkConnection())
        {
            try {
                String sql="delete from nhacungcap where MaNCC='"+nc.getMaNCC()+"'";
                if( stmt.executeUpdate(sql)>=1)
                {
                    result=true;
                }
                
            } catch (Exception e) {
            }
            finally{
                closeConnection();
            }
        }
        return result;
    }
    public boolean sua(NhaCungCapDTO nc)
    {
        boolean result = false;
        if(checkConnection())
        {
            try {
                String sql = "update nhacungcap set TenNCC='"+nc.getTenNCC()+"', DiaChi='"+nc.getDiaChiNCC()+"', SDT='"+nc.getSDT()+"' where MaNCC='"+nc.getMaNCC()+"'";
                 if(stmt.executeUpdate(sql)>=1)
                 {
                     result = true;
                 }
                
                
            } catch (Exception e) {
            }
            finally{
                closeConnection();
            }
        }
        return result;
    }
}
