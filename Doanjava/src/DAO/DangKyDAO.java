/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import DTO.DangKyDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author KhanhDuy
 */
public class DangKyDAO {
    static String USER = "root";
    static String PASS = "";
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
    public ArrayList<DangKyDTO> getAllTK()
    {
        ArrayList<DangKyDTO> arr= new ArrayList<>();
        if(checkConnection())
        {
             try {
                String sql="select * from taikhoan";
                Statement stm1 = conn.createStatement();
                ResultSet rs = stm1.executeQuery(sql);
                while(rs.next())
                {
                    DangKyDTO tkDTO = new DangKyDTO();
                    tkDTO.setMaTK(rs.getInt("MaTK"));
                    tkDTO.setMaQuyen(rs.getInt("MaQuyen"));
                    tkDTO.setTenDN(rs.getString("tenDN"));
                    tkDTO.setMatkhau(rs.getString("Matkhau"));
                    arr.add(tkDTO);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return arr;
    }
    public boolean hasTK(String ten)
    {
        boolean result=false;
        if(checkConnection())
        {
            try {
                String sql="select * from taikhoan where Tendn='"+ten+"'";
                ResultSet rs = stm.executeQuery(sql);
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
    public boolean addTK(DangKyDTO tk)
    {
        boolean result = false;
        if(checkConnection()){
            try {
                int c=1;
                String sql = "insert into taikhoan (Maquyen,TenDN,Matkhau) values(?,?,?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery("SELECT * FROM Taikhoan");
                stmt.setInt(1, 2);
                stmt.setString(2, tk.getTenDN());
                stmt.setString(3, tk.getMatkhau());
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
    public static void main(String[] args) {
        /*try {
        checkConnection();
        String sql = "SELECT * FROM `Taikhoan`";
        Statement stm1 = conn.createStatement();
        ResultSet rs = stm1.executeQuery(sql);
        if(rs == null){
            return;
        }
        
            while(rs.next()){
                System.out.println(rs.getInt(1)+" "+rs.getInt(2)+" "+rs.getString(3)+" "+rs.getString(4));
            }
        } catch (Exception e) {
        }*/
        //checkConnection();
    }
}
