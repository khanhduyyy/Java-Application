/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.SanPhamDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Home
 */
public class SanPhamDAO {
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
    
    public  ResultSet sqlExecute (String qry)
    {
        ResultSet rs =null;
        if(checkConnection()){
        try {
          
            rs=stmt.executeQuery(qry);
            return rs;
        } catch (Exception e) {
            System.out.println(e);
        }
        }
        return null;
    }
    
    public  ResultSet updateQuery(String qry)
        {
            if(checkConnection()){
            try{
            
            int count = stmt.executeUpdate(qry);
            
            }catch(Exception e)
            {
                System.out.println(e);
            }
            }
            return null;
        }
    public static int getSoluongmaLoai()
    {
        int c=0;
        if(checkConnection())
        {
        try {
                String sql="select MaLoai from loaisp";
                Statement stm1 = conn.createStatement();
                ResultSet rs = stm1.executeQuery(sql);
                while(rs.next())
                {
                    c++;
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
    public String[] getTenLoai()
    {
        int n=getSoluongmaLoai();
        String [] a = new String[n];
        int i =0;
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
                System.out.println(e);
            }
            finally{
                closeConnection();
            }
        }
        return a;
    }
    public  int[] getMaLoai()
    { 
         int n=getSoluongmaLoai();
         int []a=new int[n];
         int i=0;
         if(checkConnection())
         {
          try {
                String sql="select MaLoai from loaisp";
                Statement stm1 = conn.createStatement();
                ResultSet rs = stm1.executeQuery(sql);
                while(rs.next())
                {
                    a[i]=rs.getInt("MaLoai");
                    i++;
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
    public boolean suaSanPham(SanPhamDTO sp)
    {
        boolean result =false;
        if(checkConnection())
        {
            try {
                
//                String sql = "UPDATE sanpham SET(?,?,?,?,?) where="+sp.getIDSP();
                String sql1="UPDATE sanpham SET TenSP='"+sp.getTenSP()+"', MaLoai='"+sp.getMaLoai()+"', Giaban='"+sp.getGiaBan()+"', Soluong='"+sp.getSoLuong()+"', Hinh='"+sp.getHinh()+"' WHERE MaSP='"+sp.getIDSP()+"'";
                ResultSet rs = updateQuery(sql1);
                result=true;
//                PreparedStatement prstmt = conn.prepareStatement(sql);
//                prstmt.setString(1,sp.getTenSP());
//                prstmt.setInt(2,sp.getMaLoai());
//                prstmt.setInt(3,sp.getGiaBan());
//                prstmt.setInt(4,sp.getSoLuong());
//                prstmt.setString(5,sp.getHinh());
//                prstmt.executeUpdate();
//                result =true;
            } catch (Exception e) {
                System.out.print(e);
            }
            finally{
                closeConnection();
            }
            
        }
        return result;
    }
    public boolean deleteSanPham(int id)
    {
        boolean result = false;
        if(checkConnection())
        {
            try {
                
                    String sql ="DELETE FROM sanpham WHERE MaSP="+id;
                    ResultSet rs =updateQuery(sql);
                    return true;
                
            } catch (Exception e) {
            }
            finally{
                closeConnection();
            }
        }
        return result;
    }
    public boolean addSanPham(SanPhamDTO sp)
    {
        boolean result = false;
        if(checkConnection()){
        try {
            int c=1;
            String sql="insert into sanpham values(?,?,?,?,?,?)";
            PreparedStatement prstmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery("SELECT * FROM sanpham");
              while(rs.next())
                {
                    c++;
                }
            prstmt.setInt(1,sp.getIDSP() );
            prstmt.setString(2, sp.getTenSP());
            prstmt.setInt(3, sp.getMaLoai());
            prstmt.setInt(4, sp.getGiaBan());
            prstmt.setInt(5, sp.getSoLuong());
            prstmt.setString(6, sp.getHinh());
            if(prstmt.executeUpdate()>=1)
            {
                result = true;
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
    public boolean hasMaTL(int mtl)
    {
        boolean result=false;
        if(checkConnection())
        {
            try {
                String sql = "select * from loaisp where MaLoai="+mtl;
                ResultSet rs = sqlExecute(sql);
                result=rs.next();
            } catch (Exception e) {
                System.out.println(e);
            }
            finally{
                closeConnection();
            }
            
        }
        return result;
    }
    public  int getAllMSP()
    {
        int a = 0;
        if(checkConnection())
        {
            try {
                String sql ="select * from sanpham";
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next())
                {
                    a=rs.getInt(1);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return a;
    }
    public ArrayList<SanPhamDTO> getAllSanPham()
    {
        ArrayList<SanPhamDTO> arr = new ArrayList<>();
        if(checkConnection())
        {
            try {
                String sql="select * from loaisp, sanpham where sanpham.MaLoai=loaisp.MaLoai ";
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next())
                {
                    SanPhamDTO spDTO = new SanPhamDTO();
                    spDTO.setMaSP(rs.getInt("MaSP"));
                    spDTO.setTenSP(rs.getString("TenSP"));
                    spDTO.setMaLoai(rs.getInt("MaLoai"));
                    spDTO.setTenLoai(rs.getString("TenLoai"));
                    spDTO.setGiaBan(rs.getInt("Giaban"));
                    spDTO.setSoLuong(rs.getInt("Soluong"));
                    spDTO.setHinh(rs.getString("Hinh"));
                    arr.add(spDTO);
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
    
    public ArrayList<SanPhamDTO> timAllSP(String tensp)
    {
        ArrayList<SanPhamDTO> arr = new ArrayList<>();
        if(checkConnection())
        {
            try {
                String sql="select * from sanpham where TenSP like '%"+tensp+"%'";
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next())
                {
                    SanPhamDTO spDTO = new SanPhamDTO();
                    spDTO.setMaSP(rs.getInt("MaSP"));
                    spDTO.setTenSP(rs.getString("TenSP"));
                    spDTO.setMaLoai(rs.getInt("MaLoai"));
                    spDTO.setGiaBan(rs.getInt("Giaban"));
                    spDTO.setSoLuong(rs.getInt("soluong"));
                    spDTO.setHinh(rs.getString("Hinh"));
                    arr.add(spDTO);
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
    public ArrayList<SanPhamDTO> findbyID(int id)
    {
        ArrayList<SanPhamDTO> arr = new ArrayList<>();
        if(checkConnection())
        {
            try {
                String sql="select * from sanpham where MaSP ='"+id+"'";
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next())
                {
                    SanPhamDTO spDTO = new SanPhamDTO();
                    spDTO.setMaSP(rs.getInt("MaSP"));
                    spDTO.setTenSP(rs.getString("TenSP"));
                    spDTO.setMaLoai(rs.getInt("MaLoai"));
                    spDTO.setGiaBan(rs.getInt("Giaban"));
                    spDTO.setSoLuong(rs.getInt("soluong"));
                    spDTO.setHinh(rs.getString("Hinh"));
                    arr.add(spDTO);
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
    public boolean hasTenSP(String ten)
    {
        boolean result =false;
        if(checkConnection())
        {
            try {
                String sql = "select * from sanpham where TenSP="+ten;
                ResultSet rs = sqlExecute(sql);
                result= rs.next();
            } catch (Exception e) {
                
                System.out.println(e);
            }
            finally{
                closeConnection();
            }
            
        }
        return result;
    }
    public boolean hasMaSanPham(int msp)
    {
        boolean result=false;
        if(checkConnection())
        {
            try {
                String sql="select * from sanpham where MaSP="+msp;
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                result = rs.next();
            } catch (Exception e) {
                System.out.println(e);
            }
            finally{
                closeConnection();
            }
        }
        return result;
    }
    public static void main(String[]args)
{
    int c=0;
    
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/doanjava?zeroDateTimeBehavior=convertToNull";
            String user = "root";
            String pass = "";
            conn = DriverManager.getConnection(url, user, pass);
            stmt = conn.createStatement();
                String sql="select * from sanpham";
                Statement stm1 = conn.createStatement();
                ResultSet rs = stm1.executeQuery(sql);
                while(rs.next())
                {
                    c = rs.getInt(1);
                    
                }
            } catch (Exception e) {
                System.out.println(e);
            }
          System.out.print(c); 
}
}

