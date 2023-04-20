/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import DTO.KhachhangDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
public class KhachhangDAO {
    static String USER = "root";
    static String PASS = "123";
    static String URL ="jdbc:mysql://localhost:3306/doanjava?zeroDateTimeBehavior=convertToNull&useUnicode=true&characterEncoding=UTF-8";
    static Connection conn = null;
    static Statement stm=null;
    public static boolean checkConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASS);
            stm = conn.createStatement();
            //JOptionPane.showMessageDialog(null, "Conected successfully");
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
   
    public ArrayList<KhachhangDTO> timKH(int maKH)
    {
        ArrayList<KhachhangDTO> arr = new ArrayList<>();
        try {
            String sql = "select * from khachhang where MaKH='"+maKH+"'";
            ResultSet rs =stm.executeQuery(sql);
            while(rs.next())
            {
                KhachhangDTO kh = new KhachhangDTO();
                kh.setMaKH(rs.getInt("MaKH"));
                kh.setTenKH(rs.getString("TenKH"));
                kh.setGioitinh(rs.getString("Gioitinh"));
                kh.setDiachi(rs.getString("Diachi"));
                kh.setSDT(rs.getString("SDT"));
                arr.add(kh);
            }
        } catch (Exception e) {
        }
        return arr;
    }
    public ArrayList<KhachhangDTO> timTenKH(String tenkh)
    {
        ArrayList<KhachhangDTO> arr = new ArrayList<>();
        try {
            String sql = "select * from khachhang where TenKH like'%"+tenkh+"%'";
            ResultSet rs =stm.executeQuery(sql);
            while(rs.next())
            {
                KhachhangDTO kh = new KhachhangDTO();
                kh.setMaKH(rs.getInt("MaKH"));
                kh.setTenKH(rs.getString("TenKH"));
                kh.setGioitinh(rs.getString("Gioitinh"));
                kh.setDiachi(rs.getString("Diachi"));
                kh.setSDT(rs.getString("SDT"));
                arr.add(kh);
            }
        } catch (Exception e) {
        }
        return arr;
    }
    public ArrayList<KhachhangDTO> getAllKH()
    {
        ArrayList<KhachhangDTO> arr= new ArrayList<>();
        if(checkConnection())
        {
             try {
                String sql="select * from khachhang";
                Statement stm1 = conn.createStatement();
                ResultSet rs = stm1.executeQuery(sql);
                while(rs.next())
                {
                    KhachhangDTO khDTO = new KhachhangDTO();
                    khDTO.setMaKH(rs.getInt("MaKH"));
                    khDTO.setTenKH(rs.getString("TenKH"));
                    khDTO.setGioitinh(rs.getString("Gioitinh"));
                    khDTO.setDiachi(rs.getString("Diachi"));
                    khDTO.setSDT(rs.getString("SDT"));
                    arr.add(khDTO);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return arr;
    }
    public boolean addKH(KhachhangDTO kh)
    {
        boolean result = false;
        if(checkConnection()){
            try {
                int c=1;
                String sql = "insert into Khachhang (TenKH,Gioitinh,Diachi,SDT) values(?,?,?,?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery("SELECT * FROM Khachhang");
                while(rs.next())
                {
                    c++;
                }
                stmt.setString(1, kh.getTenKH());
                stmt.setString(2, kh.getGioitinh());
                stmt.setString(3, kh.getDiachi());
                stmt.setString(4, kh.getSDT());
                if(stmt.executeUpdate()>=1)
                {
                    return true;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
                return result;
        }
    public boolean XoaKH(int a)
    {
        if(checkConnection())
        {
            boolean result = false;
            try{
                String sql="DELETE FROM Khachhang WHERE MaKH='"+a+"'";
                conn = DriverManager.getConnection(URL, USER, PASS);
                Statement stmt=conn.createStatement();
                if(stmt.executeUpdate(sql)>=1)
                {
                    return true;
                }
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
        return false;
    }
    public boolean SuaKH(KhachhangDTO kh,int a)
    {
        if(checkConnection())
        {
            boolean result = false;
            try{
                String sql="UPDATE Khachhang SET TenKH='"+kh.getTenKH()+"', Gioitinh='"+kh.getGioitinh()+"', Diachi='"+kh.getDiachi()+"', SDT='"+kh.getSDT()+"' WHERE MaKH='"+a+"'";
                conn = DriverManager.getConnection(URL, USER, PASS);
                Statement stmt=conn.createStatement();
                if(stmt.executeUpdate(sql)>=1)
                {
                    return true;
                }
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
        return false;
    }
    public static void main(String[] args) {
        int a =123;
        String b = String.valueOf(a);
        System.out.println(a);
    }
}
