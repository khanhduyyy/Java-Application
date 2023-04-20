/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//SELECT chitiethoadon.MaHD,sanpham.TenSP,chitiethoadon.SoLuong,chitiethoadon.Thanhtien*Sanpham.giaban AS thanhtien1 FROM chitiethoadon,sanpham WHERE chitiethoadon.MaSP=sanpham.MaSP
package DAO;
import DTO.HoadonDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
public class HoadonDAO {
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
    public  int getTongTien(int a)
    {
        int c=0;
        try {
                String sql="select Sum(Thanhtien) from chitiethoadon where MaHD='"+a+"'";
                Statement stm1 = conn.createStatement();
                ResultSet rs = stm1.executeQuery(sql);
                while(rs.next())
                {
                    c=rs.getInt(1);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        return c;
    }
    public ArrayList<HoadonDTO> timCTHD(int maHD)
    {
        ArrayList<HoadonDTO> arr = new ArrayList<HoadonDTO>();
        if(checkConnection()){
            try {
                String sql="SELECT chitiethoadon.MaHD,sanpham.Giaban,sanpham.TenSP,chitiethoadon.SoLuong,chitiethoadon.Thanhtien FROM chitiethoadon,sanpham where chitiethoadon.MaSP=sanpham.MaSP and MaHD='"+maHD+"'";
                ResultSet rs = stm.executeQuery(sql);
                while(rs.next())
                {
                    HoadonDTO hd = new HoadonDTO();
                    hd.setMaHD(rs.getInt("MaHD"));
                    hd.setTenSP(rs.getString("TenSP"));
                    hd.setSoLuong(rs.getInt("Soluong"));
                    hd.setThanhtien(rs.getInt("Thanhtien"));
                    hd.setDonGia(rs.getInt("Giaban"));
                    arr.add(hd);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return arr;
    }
    public ArrayList<HoadonDTO> tim(int maHD) 
    {
        ArrayList<HoadonDTO> arr = new ArrayList<HoadonDTO>();
        if(checkConnection())
        {
            try {
                String sql = "SELECT hoadon.MaHD,khachhang.TenKH,nhanvien.TenNV,hoadon.Tongtien  FROM hoadon,khachhang,nhanvien WHERE khachhang.MaKH=hoadon.MaKH AND nhanvien.MaNV=hoadon.MaNV AND hoadon.MaHD='"+maHD+"'";
                ResultSet rs = stm.executeQuery(sql);
                
                if(rs.next())
                {
                    HoadonDTO HD = new HoadonDTO();
                    HD.setMaHD(rs.getInt("MaHD"));
                    HD.setTenKH(rs.getString("TenKH"));
                    HD.setTenNV(rs.getString("TenNV"));
                    HD.settongtien(rs.getInt("Tongtien"));
                    arr.add(HD);
                }
                
            } catch (Exception e) {
                System.out.println(e);
            }
          
        }
        return arr;
    }
    public  int getThanhTien(int a,int b)
    {
        int c=0;
        try {
                String sql="select Giaban from Sanpham where MaSP='"+a+"'";
                Statement stm1 = conn.createStatement();
                ResultSet rs = stm1.executeQuery(sql);
                while(rs.next())
                {
                    c=rs.getInt(1);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        return c*b;
    }
     public  int getSoluongTenKH()
    {
        int c=0;
        try {
                String sql="select TenKH from Khachhang";
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
    public  String[] getTenKhachHang()
    { 
         int n=getSoluongTenKH();
         String []a=new String[n];
         int i=0;
          try {
                String sql="select TenKH from Khachhang";
                Statement stm1 = conn.createStatement();
                ResultSet rs = stm1.executeQuery(sql);
                while(rs.next())
                {
                    a[i]=rs.getString("TenKH");
                    i++;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
          return a;
    }
    public  int getSoluongMaHD()
    {
        int c=0;
        try {
                String sql="select MaHD from Hoadon";
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
    public  int[] getMaHoaDon()
    { 
         int n=getSoluongMaHD();
         int []a=new int[n];
         int i=0;
          try {
                String sql="select MaHD from Hoadon ORDER BY `Hoadon`.`MaHD` ASC";
                Statement stm1 = conn.createStatement();
                ResultSet rs = stm1.executeQuery(sql);
                while(rs.next())
                {
                    a[i]=rs.getInt("MaHD");
                    i++;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
          return a;
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
    public  int getSoluongTenNV()
    {
        int c=0;
        try {
                String sql="select TenNV from Nhanvien";
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
    public  String[] getTenNhanvien()
    { 
         int n=getSoluongTenNV();
         String []a=new String[n];
         int i=0;
          try {
                String sql="select TenNV from Nhanvien";
                Statement stm1 = conn.createStatement();
                ResultSet rs = stm1.executeQuery(sql);
                while(rs.next())
                {
                    a[i]=rs.getString("TenNV");
                    i++;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
          return a;
    }
     public  int getMaKhachhang(String a)
    {
        int b = 0;
        try{
        String sql="select MaKH from Khachhang where TenKH='"+a+"'";
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
     public  int getMaNhanVien(String a)
    {
        int b = 0;
        try{
        String sql="select MaNV from Nhanvien where TenNV='"+a+"'";
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
     public ArrayList<HoadonDTO> getAllHD()
    {
        ArrayList<HoadonDTO> arr= new ArrayList<>();
        if(checkConnection())
        {
             try {
                String sql="SELECT hoadon.MaHD,khachhang.TenKH,nhanvien.TenNV,hoadon.Tongtien,hoadon.DateHD FROM hoadon,khachhang,nhanvien WHERE khachhang.MaKH=hoadon.MaKH AND nhanvien.MaNV=hoadon.MaNV ORDER BY `hoadon`.`MaHD` ASC";
                Statement stm1 = conn.createStatement();
                ResultSet rs = stm1.executeQuery(sql);
                while(rs.next())
                {
                    HoadonDTO hdDTO = new HoadonDTO();
                    hdDTO.setMaHD(rs.getInt("MaHD"));
                    hdDTO.setTenKH(rs.getString("TenKH"));
                    hdDTO.setTenNV(rs.getString("TenNV"));
                    hdDTO.settongtien(rs.getInt("Tongtien"));
                    hdDTO.setDate(rs.getDate("DateHD"));
                    arr.add(hdDTO);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return arr;
    }
    public boolean addHD(HoadonDTO hd)
    {
        boolean result = false;
        if(checkConnection()){
            try {
                int c=1;
                String sql = "insert into Hoadon (MaKH,MaNV,Tongtien,DateHD) values(?,?,?,?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery("SELECT * FROM Hoadon");
                while(rs.next())
                {
                    c++;
                }
                stmt.setInt(1, hd.getMaKH());
                stmt.setInt(2, hd.getMaNV());
                stmt.setInt(3, hd.gettongtien());
                stmt.setDate(4, hd.getDate());
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
    public boolean XoaHD(int a)
    {
        if(checkConnection())
        {
            boolean result = false;
            try{
                String sql="DELETE FROM Hoadon WHERE MaHD='"+a+"'";
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
    public boolean SuaHD(HoadonDTO hd,int a)
    {
        if(checkConnection())
        {
            boolean result = false;
            try{
                String sql="UPDATE Hoadon SET Tongtien='"+hd.gettongtien()+"' WHERE MaHD='"+a+"'";
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
    //Dưới đây là code của chi tiết hóa đơn
     public ArrayList<HoadonDTO> getAllCTHD()
    {
        ArrayList<HoadonDTO> arr= new ArrayList<>();
        if(checkConnection())
        {
             try {
                String sql="SELECT chitiethoadon.MaHD,sanpham.Giaban,sanpham.TenSP,chitiethoadon.SoLuong,chitiethoadon.Thanhtien FROM chitiethoadon,sanpham WHERE chitiethoadon.MaSP=sanpham.MaSP";
                Statement stm1 = conn.createStatement();
                ResultSet rs = stm1.executeQuery(sql);
                while(rs.next())
                {
                    HoadonDTO hdDTO = new HoadonDTO();
                    hdDTO.setMaHD(rs.getInt("MaHD"));
                    hdDTO.setTenSP(rs.getString("TenSP"));
                    hdDTO.setSoLuong(rs.getInt("Soluong"));
                    hdDTO.setThanhtien(rs.getInt("Thanhtien"));
                    hdDTO.setDonGia(rs.getInt("Giaban"));
                    arr.add(hdDTO);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return arr;
    }
      public boolean addCTHD(HoadonDTO hd)
    {
        boolean result = false;
        if(checkConnection()){
            try {
                int c=1;
                String sql = "insert into Chitiethoadon (MaHD,MaSP,Soluong,Thanhtien) values(?,?,?,?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery("SELECT * FROM Chitiethoadon");
                while(rs.next())
                {
                    c++;
                }
                stmt.setInt(1, hd.getMaHD());
                stmt.setInt(2, hd.getMaSP());
                stmt.setInt(3, hd.getSoLuong());
                stmt.setInt(4, hd.getThanhtien());
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
      public boolean SuaCTHD(HoadonDTO hd,int a,int b)
    {
        if(checkConnection())
        {
            boolean result = false;
            try{
                int c=getThanhTien(b,hd.getSoLuong());
                String sql="UPDATE chitiethoadon SET Soluong='"+hd.getSoLuong()+"', Thanhtien='"+c+"' WHERE MaHD='"+a+"' AND MaSP='"+b+"'";
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
      public  boolean KiemtraSPtrung(int a,int b)
      {
          try {
              conn = DriverManager.getConnection(URL, USER, PASS);
              Statement stmt=conn.createStatement();
              ResultSet rs = stmt.executeQuery("SELECT MaSP FROM Chitiethoadon Where MaHD="+b);
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
      public boolean XoaCTHD(int a,int b)
    {
        if(checkConnection())
        {
            boolean result = false;
            try{
                String sql="DELETE FROM Chitiethoadon WHERE MaHD='"+a+"' AND MaSP='"+b+"'";
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
      public  void XoaCTHDinHD(int a)
    {
        if(checkConnection())
        {
            boolean result = false;
            try{
                String sql="DELETE FROM Chitiethoadon WHERE MaHD='"+a+"'";
                conn = DriverManager.getConnection(URL, USER, PASS);
                Statement stmt=conn.createStatement();
                if(stmt.executeUpdate(sql)>=1)
                {
                    return;
                }
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
        return;
    }
      public void UpdateTongtien(int a,int b)
      {
          try{
              
          String sql="UPDATE Hoadon SET Tongtien='"+b+"' WHERE MaHD='"+a+"'";
          conn = DriverManager.getConnection(URL, USER, PASS);
          Statement stmt=conn.createStatement();
          stmt.executeUpdate(sql);
          }catch(Exception e)
          {
              
          }
      }
      public boolean ktsoluongsp(int a,int b)
      {
          int t=0;
          boolean result=false;
          try {
              conn = DriverManager.getConnection(URL, USER, PASS);
              Statement stmt=conn.createStatement();
              ResultSet rs = stmt.executeQuery("SELECT Soluong FROM Sanpham Where MaSP="+a);
              while(rs.next()){
                  t=rs.getInt(1);
              }
              if(t<=b)
                  result= false;
              else{
                    int sl=t-b;
                    if(sl<0) sl=sl*(-1);
                    String sql="UPDATE sanpham SET Soluong='"+sl+"' WHERE  MaSP='"+a+"'";   
                    stmt.executeUpdate(sql);
                    result= true;
                   }
          } catch (Exception e) {
              System.out.println(e);
          }
          return result;
      }
    public static void main(String[] args) {
        checkConnection();
        int c=0;
        try {
                String sql="select Sum(Thanhtien) from chitiethoadon where MaHD='"+39+"'";
                Statement stm1 = conn.createStatement();
                ResultSet rs = stm1.executeQuery(sql);
                while(rs.next())
                {
                    c=rs.getInt(1);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        System.out.println(c);
    }
}
