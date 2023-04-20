/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;
import DTO.NhanvienDTO;
import DAO.NhanvienDAO;
import java.util.ArrayList;
public class NhanvienBLL {
    NhanvienDAO nv = new NhanvienDAO();
    public ArrayList<NhanvienDTO> getAllNhanvien()
    {
        return nv.getAllNV();
    }
    public ArrayList<NhanvienDTO> timNV(int id)
    {
        return nv.timNV(id);
    }
       public ArrayList<NhanvienDTO> timTenNV(String ten)
    {
        return nv.timNVbyTen(ten);
    }
    public String addNhanvien(NhanvienDTO Nhanvien)
    {
        
        if(nv.addNV(Nhanvien))
        {
            return "Thêm nhân viên thành công ^^";
        }
        else
        {
            return "Thêm nhân viên không thành công :<";
        }
    }
     public String xoaNhanvien(int a)
      {
        if(nv.XoaNV(a))
        {
            return "Xóa nhân viên thành công ^^";
        }
        else
        {
            return "Xóa nhân viên không thành công :<";
        }
    }  
     public String suaKhachhang(NhanvienDTO Nhanvien,int a)
    {
        if(nv.SuaNV(Nhanvien, a))
        {
            return "Sửa nhân viên thành công ^^";
        }
        else
            return "Sửa nhân viên không thành công :<";
    }
}
