/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author Home
 */
import DTO.ThongKeDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
public class ThongKeDAO {
    
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
    public int getSoLuongMaLoai()
    {
        int a = 0;
        if (checkConnection()) {
            try {
                String sql="select * from loaisp";
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next())
                {
                    a++;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            finally{
                closeConnection();
            }
            
        }
        return a;
    }
    public String[] getMaloai()
    {
        int n = getSoLuongMaLoai();
        String[]a = new String[n];
        int i=0;
        if(checkConnection())
        {
            try {
                  String sql="select TenLoai from loaisp";
                  ResultSet rs = stmt.executeQuery(sql);
                   
                  while(rs.next())
                  {
                      a[i]=rs.getString(1);
                      i++;
                  }
                
                
            } catch (Exception e) {
            }
      }
        return a;
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
     public ArrayList<ThongKeDTO> getTL(String tenml, String date, String date1)
    {
        ArrayList<ThongKeDTO> arr = new ArrayList<ThongKeDTO>();
        if(checkConnection())
        {
            try {
                String sql = "select sanpham.MaSP, sanpham.TenSP, loaisp.MaLoai, sum(chitiethoadon.SoLuong), sanpham.Giaban, hoadon.DateHD from loaisp, chitiethoadon, sanpham, hoadon where hoadon.MaHD=chitiethoadon.MaHD and sanpham.MaSP=chitiethoadon.MaSP and sanpham.MaLoai=loaisp.MaLoai and loaisp.TenLoai='"+tenml+"' AND hoadon.DateHD BETWEEN '"+date+"' AND '"+date1+"' group by chitiethoadon.MaSP ORDER BY sum(chitiethoadon.SoLuong) DESC";
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next())
                {
                    ThongKeDTO tk = new ThongKeDTO();
                    tk.setMaSP(rs.getInt(1));
                    tk.setTeSP(rs.getString(2));
                    tk.setTheLoai(rs.getInt(3));
                    tk.setSoLuong(rs.getInt(4));
                    tk.setDonGia(rs.getInt(5));
                    int thanhtien = rs.getInt(4)*rs.getInt(5);
                    tk.setThanhTien(thanhtien);
                    SimpleDateFormat date3= new SimpleDateFormat("yyyy-MM-dd");
                    String d = date3.format(rs.getDate(6));
                    tk.setDate1(d);
                    arr.add(tk);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            finally{
                closeConnection();
            }
        }
        return arr;
    }
     public  ArrayList<ThongKeDTO> getThongke(int a)
     {
        ArrayList<ThongKeDTO> arr = new ArrayList<ThongKeDTO>();
        if(checkConnection())
        {
            try {
                String sql = "SELECT * FROM hoadon WHERE hoadon.DateHD BETWEEN '2021-0"+a+"-01' AND '2021-0"+a+"-31'";
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next())
                {
                    ThongKeDTO tk = new ThongKeDTO();
                    tk.setMaHD(rs.getInt(1));
                    tk.setTongtien(rs.getInt(4));
                    tk.setDate(rs.getDate(5));
                    
                    arr.add(tk);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            finally{
                closeConnection();
            }
        }
        return arr;
    }
    public ArrayList<ThongKeDTO> getSPKHO()
    {
        ArrayList<ThongKeDTO> arr = new ArrayList<ThongKeDTO>(); 
        if(checkConnection())
        {
            try {
                String sql = "select * from sanpham ";
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next())
                {
                    ThongKeDTO tk = new ThongKeDTO();
                    tk.setMaSP(rs.getInt(1));
                    tk.setTeSP(rs.getString(2));
                    tk.setTheLoai(rs.getInt(3));
                    tk.setDonGia(rs.getInt(4));
                    tk.setSoLuong(rs.getInt(5));
                    arr.add(tk);
                }
            } catch (Exception e) {
            }
        }
        return arr;
    }
    public ArrayList<ThongKeDTO> thongke(String date, String date1)
    {
        ArrayList<ThongKeDTO> arr = new ArrayList<ThongKeDTO>();
        if(checkConnection())
        {
            int c = 0;
            try {
                String sql = "select chitiethoadon.MaSP, sanpham.TenSP, sanpham.MaLoai, sum(chitiethoadon.SoLuong),sanpham.Giaban, hoadon.DateHD from chitiethoadon, sanpham, hoadon where hoadon.MaHD=chitiethoadon.maHD and sanpham.MaSP=chitiethoadon.MaSP and hoadon.DateHD BETWEEN '"+date+"' and '"+date1+"' GROUP BY chitiethoadon.MaSP ORDER BY sum(chitiethoadon.SoLuong) DESC";
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next())
                {
                    ThongKeDTO tk = new ThongKeDTO();
                    tk.setMaSP(rs.getInt(1));
                    tk.setTeSP(rs.getString(2));
                    tk.setTheLoai(rs.getInt(3));
                    tk.setSoLuong(rs.getInt(4));
                    tk.setDonGia(rs.getInt(5));
                    int a = rs.getInt(4)*rs.getInt(5);
                    tk.setThanhTien(a);
                    SimpleDateFormat date3= new SimpleDateFormat("yyyy-MM-dd");
                    String d = date3.format(rs.getDate(6));
                    tk.setDate1(d);
                    arr.add(tk);
                }
            } catch (Exception e) {
            }
            finally{
                closeConnection();
            }
        }
        return arr;
        
    }
    public int getTongDoanhThu(int a)
    {
  
        if(checkConnection())
        {
            try {
                String sql = "SELECT Sum(Tongtien) FROM hoadon WHERE hoadon.DateHD BETWEEN '2021-0"+a+"-01' AND '2021-0"+a+"-31'";
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next())
                {
                    a = rs.getInt(1);
                }
            } catch (Exception e) {
            }
            finally{
                closeConnection();
            }
        }
        return a;
    }
    public static void main(String[]args)
    {
     
    }
           
            
        
}
