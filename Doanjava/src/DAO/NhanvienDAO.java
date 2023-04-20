/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import DTO.NhanvienDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
public class NhanvienDAO {
    static String USER = "root";
    static String PASS = "123";
    static String URL = "jdbc:mysql://localhost:3306/doanjava?zeroDateTimeBehavior=convertToNull&useUnicode=true&characterEncoding=UTF-8";
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
    public ArrayList<NhanvienDTO> timNVbyTen(String tennv)
    {
        ArrayList<NhanvienDTO> arr = new ArrayList<NhanvienDTO>();
        if(checkConnection())
        {
            try {
                String sql="select * from nhanvien where TenNV like '%"+tennv+"%'";
                ResultSet rs = stm.executeQuery(sql);
                while(rs.next())
                {
                    NhanvienDTO nv = new NhanvienDTO();
                    nv.setMaNV(rs.getInt("MaNV"));
                    nv.setMaTK(rs.getInt("MaTK"));
                    nv.setTenNV(rs.getString("TenNV"));
                    nv.setGioitinh(rs.getString("Gioitinh"));
                    nv.setSDT(rs.getString("SDT"));
                    nv.setLuong(rs.getInt("Luong"));
                    arr.add(nv);
                }
                
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return arr;
    }
    public ArrayList<NhanvienDTO> timNV(int id)
    {
        ArrayList<NhanvienDTO> arr = new ArrayList<NhanvienDTO>();
        if(checkConnection())
        {
            try {
                String sql="select * from nhanvien where MaNV='"+id+"'";
                ResultSet rs = stm.executeQuery(sql);
                while(rs.next())
                {
                    NhanvienDTO nv = new NhanvienDTO();
                    nv.setMaNV(rs.getInt("MaNV"));
                    nv.setMaTK(rs.getInt("MaTK"));
                    nv.setTenNV(rs.getString("TenNV"));
                    nv.setGioitinh(rs.getString("Gioitinh"));
                    nv.setSDT(rs.getString("SDT"));
                    nv.setLuong(rs.getInt("Luong"));
                    arr.add(nv);
                }
                
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return arr;
    }
    public static int getSoluongmaTK()
    {
        int c=0;
        try {
                String sql="select MaTK from Taikhoan";
                Statement stm1 = conn.createStatement();
                ResultSet rs = stm1.executeQuery(sql);
                while(rs.next())
                {
                    c++;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
          return c;
    }
    public static int[] getMaTaiKhoan()
    { 
         int n=getSoluongmaTK();
         int []a=new int[n];
         int i=0;
          try {
                String sql="select MaTK from Taikhoan";
                Statement stm1 = conn.createStatement();
                ResultSet rs = stm1.executeQuery(sql);
                while(rs.next())
                {
                    a[i]=rs.getInt("MaTK");
                    i++;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
          return a;
    }
   
    public ArrayList<NhanvienDTO> getAllNV()
    {
        ArrayList<NhanvienDTO> arr= new ArrayList<>();
        if(checkConnection())
        {
             try {
                String sql="select * from Nhanvien";
                Statement stm1 = conn.createStatement();
                ResultSet rs = stm1.executeQuery(sql);
                while(rs.next())
                {
                    NhanvienDTO nvDTO = new NhanvienDTO();
                    nvDTO.setMaNV(rs.getInt("MaNV"));
                    nvDTO.setMaTK(rs.getInt("MaTK"));
                    nvDTO.setTenNV(rs.getString("TenNV"));
                    nvDTO.setGioitinh(rs.getString("Gioitinh"));
                    nvDTO.setSDT(rs.getString("SDT"));
                    nvDTO.setLuong(rs.getInt("Luong"));
                    arr.add(nvDTO);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return arr;
    }
    public boolean addNV(NhanvienDTO nv)
    {
        boolean result = false;
        if(checkConnection()){
            try {
                int c=1;
                String sql = "insert into Nhanvien (MaTK,TenNV,Gioitinh,SDT,Luong) values(?,?,?,?,?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery("SELECT * FROM Nhanvien");
                while(rs.next())
                {
                    c++;
                }
                stmt.setInt(1,nv.getMATK());
                stmt.setString(2, nv.getTenNV());
                stmt.setString(3, nv.getGioitinh());
                stmt.setString(4, nv.getSDT());
                stmt.setInt(5,nv.getLuong());
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
    public boolean XoaNV(int a)
    {
        if(checkConnection())
        {
            boolean result = false;
            try{
                String sql="DELETE FROM Nhanvien WHERE MaNV='"+a+"'";
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
    public boolean SuaNV(NhanvienDTO nv,int a)
    {
        if(checkConnection())
        {
            boolean result = false;
            try{
                String sql="UPDATE Nhanvien SET TenNV='"+nv.getTenNV()+"', Gioitinh='"+nv.getGioitinh()+"', SDT='"+nv.getSDT()+"', Luong='"+nv.getLuong()+"' WHERE MaNV='"+a+"'";
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
    /*public int[] laymaTK()
    {
        try{
                int[] a=new int[50];
                int i=0;
                String sql="SELECT MaTK FROM taikhoan";
                conn = DriverManager.getConnection(URL, USER, PASS);
                Statement stmt=conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM SinhVien");
                while(rs.next())
                    a[i]=rs.getInt(1);
                return a;
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
            
    }*/
}

