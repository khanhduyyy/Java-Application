/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import DTO.PhieunhapDTO;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author KhanhDuy
 */
public class PhieunhapDAO {
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
    public boolean XoaPN(int a,int b)
    {
        if(checkConnection())
        {
            boolean result = false;
            try{
                String sql="DELETE FROM chitietphieunhap WHERE MaPN='"+a+"' AND MaSP='"+b+"'";
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
     public boolean addPN(PhieunhapDTO pn)
    {
        boolean result = false;
        if(checkConnection()){
            try {
                int c=1;
                String sql = "insert into phieunhap (MaNCC,DatePN) values(?,?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery("SELECT * FROM phieunhap");
                while(rs.next())
                {
                    c++;
                }
                stmt.setInt(1, pn.getMaNCC());
                stmt.setDate(2, pn.getDate());
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
     public boolean addCTPN(PhieunhapDTO pn)
    {
        boolean result = false;
        if(checkConnection()){
            try {
                int c=1;
                String sql = "insert into Chitietphieunhap (MaPN,MaSP,Soluong) values(?,?,?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery("SELECT * FROM Chitietphieunhap");
                while(rs.next())
                {
                    c++;
                }
                stmt.setInt(1, pn.getMaPN());
                stmt.setInt(2, pn.getMaSP());
                stmt.setInt(3, pn.getSoluong());
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
     public int Soluong1SP(int a)
     {
         int b=0;   
         try {
              String sql="select Soluong from Sanpham where MaSP='"+a+"'";
                Statement stm1 = conn.createStatement();
                ResultSet rs = stm1.executeQuery(sql);
                while(rs.next())
                {
                    b=rs.getInt(1);
                }
                return b;
         }catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);
         }
         return b;
     }
     public void UpdateSoluong(int a,int b)
     {
         try {
             conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt=conn.createStatement();
             int c=Soluong1SP(a);
             int d=b+c;
             String sql="UPDATE sanpham SET Soluong='"+d+"' WHERE  MaSP='"+a+"'";
             stmt.executeUpdate(sql);
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);
         }
     }
     public ArrayList<PhieunhapDTO> getAllPN()
    {
        ArrayList<PhieunhapDTO> arr= new ArrayList<>();
        if(checkConnection())
        {
             try {
                String sql="SELECT phieunhap.MaPN,nhacungcap.TenNCC,phieunhap.DatePN FROM phieunhap,nhacungcap WHERE nhacungcap.MaNCC=phieunhap.MaNCC";
                Statement stm1 = conn.createStatement();
                ResultSet rs = stm1.executeQuery(sql);
                while(rs.next())
                {
                    PhieunhapDTO pnDTO = new PhieunhapDTO();
                    pnDTO.setMaPN(rs.getInt("MaPN"));
                    pnDTO.setTenNCC(rs.getString("TenNCC"));
                    pnDTO.setDate(rs.getDate("DatePN"));
                    arr.add(pnDTO);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return arr;
    }
     public  int getSoluongTenNCC()
    {
        int c=0;
        try {
                String sql="select TenNCC from nhacungcap";
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
     public  String[] getTenNhacungcap()
    { 
         int n=getSoluongTenNCC();
         String []a=new String[n];
         int i=0;
          try {
                String sql="select TenNCC from nhacungcap";
                Statement stm1 = conn.createStatement();
                ResultSet rs = stm1.executeQuery(sql);
                while(rs.next())
                {
                    a[i]=rs.getString("TenNCC");
                    i++;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
          return a;
    }
      public  int getMaNhacungcap(String a)
    {
        int b = 0;
        try{
        String sql="select MaNCC from nhacungcap where TenNCC='"+a+"'";
        Statement stm1 = conn.createStatement();
        ResultSet rs = stm1.executeQuery(sql);
        while(rs.next())
        {
            b=rs.getInt(1);
        }
        }catch(Exception e){
            System.out.println(e);
        }
        return b;
    }
       public  int getSoluongTenSP()
    {
        int c=0;
        try {
                String sql="select TenSP from Sanpham";
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
    public  String[] getTenSanPham()
    { 
         int n=getSoluongTenSP();
         String []a=new String[n];
         int i=0;
          try {
                String sql="select TenSP from Sanpham";
                Statement stm1 = conn.createStatement();
                ResultSet rs = stm1.executeQuery(sql);
                while(rs.next())
                {
                    a[i]=rs.getString("TenSP");
                    i++;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
          return a;
    }
    public  int getMaSanPham(String a)
    {
        int b = 0;
        try{
        String sql="select MaSP from Sanpham where TenSP='"+a+"'";
        Statement stm1 = conn.createStatement();
        ResultSet rs = stm1.executeQuery(sql);
        while(rs.next())
        {
            b=rs.getInt(1);
        }
        }catch(Exception e){
            System.out.println(e);
        }
        return b;
    } 
     public ArrayList<PhieunhapDTO> getAllCTPN()
    {
        ArrayList<PhieunhapDTO> arr= new ArrayList<>();
        if(checkConnection())
        {
             try {
                String sql="SELECT chitietphieunhap.MaPN,sanpham.TenSP,chitietphieunhap.Soluong FROM chitietphieunhap,sanpham WHERE sanpham.MaSP=chitietphieunhap.MaSP";
                Statement stm1 = conn.createStatement();
                ResultSet rs = stm1.executeQuery(sql);
                while(rs.next())
                {
                    PhieunhapDTO pnDTO = new PhieunhapDTO();
                    pnDTO.setMaPN(rs.getInt("MaPN"));
                    pnDTO.setTenSP(rs.getString("TenSP"));
                    pnDTO.setSoluong(rs.getInt("Soluong"));
                    arr.add(pnDTO);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return arr;
    }
     public  boolean KiemtraSPtrung(int a,int b)
      {
          try {
              conn = DriverManager.getConnection(URL, USER, PASS);
              Statement stmt=conn.createStatement();
              ResultSet rs = stmt.executeQuery("SELECT MaSP FROM Chitietphieunhap Where MaPN="+b);
              while(rs.next())
              {
                 if(a==rs.getInt(1))
                 {
                      return true;
                 }
              }
              return false;
          } catch (Exception e) {
              System.out.println(e);
          }
          return false;
      }
}
