/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAO.SanPhamDAO;
import DTO.SanPhamDTO;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Home
 */
public class SanPhamBLL {
    SanPhamDAO spDAO = new SanPhamDAO();
    public ArrayList<SanPhamDTO> getAllSanPham()
    {
        return spDAO.getAllSanPham();
    }
    
    public ArrayList<SanPhamDTO> getAllTenSPByID(int id)
    {
       
            return spDAO.findbyID(id);
        
     
    }
     public ArrayList<SanPhamDTO> getAllTenSPByTen(String ten)
    {
       
            return spDAO.timAllSP(ten);
        
     
    }
    public String addSanPham(SanPhamDTO sp)
    {
        if(spDAO.hasMaSanPham(sp.getIDSP()))
        {
            return "ID đã tồn tại";
        }
        if(spDAO.addSanPham(sp))
        {
            return "thêm sản phẩm thành công ^^";
        }
        else
        {
            return "thêm sản phẩm không thành công";
        }
    }
    public String deleteSanPham(SanPhamDTO sp)
    {

            if(spDAO.deleteSanPham(sp.getIDSP()))
                return "xóa sản phẩm thành công";
            else
                return "xóa sản phẩm không thành công";
    }
    public String suaSanPham(SanPhamDTO sp)
    {
        if(spDAO.hasMaSanPham(sp.getIDSP())){
            if(spDAO.hasMaTL(sp.getMaLoai()))
            {
            spDAO.suaSanPham(sp);
            return "sửa sản phẩm thành công";
            }
            else
            {
                return "mã thể loại chưa đc chọn";
            }
    }
        else
            return "sửa sản phẩm không thành công";
    }
    public int[] getMaLoai()
    {
        int[]a=spDAO.getMaLoai();
        return a;
    }
    public String[] getTenLoai()
    {
        String[]a=spDAO.getTenLoai();
        return a;
    }
    public int getAllMSP()
    {
        int a =spDAO.getAllMSP();
        return a;
    }

    
}
