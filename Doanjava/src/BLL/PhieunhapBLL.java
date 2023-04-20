/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

/**
 *
 * @author KhanhDuy
 */
import DTO.PhieunhapDTO;
import DAO.PhieunhapDAO;
import java.util.ArrayList;
public class PhieunhapBLL {
    PhieunhapDAO pn = new PhieunhapDAO();
    public String addPhieunhap(PhieunhapDTO phieunhap)
    {
        if(pn.addPN(phieunhap))
        {
            return "Thêm phiếu nhập thành công ^^";
        }
        else
        {
            return "Thêm phiếu nhập không thành công :<";
        }
    }
    public String addCTPhieunhap(PhieunhapDTO Phieunhap)
    {
        if(pn.addCTPN(Phieunhap))
        {
            return "Thêm chi tiết phiếu nhập thành công ^^";
        }
        else
        {
            return "Thêm chi tiết phiếu nhập không thành công :<";
        }
    }
    public String xoaPhieuNhap(int a,int b)
      {
        if(pn.XoaPN(a,b))
        {
            return "Xóa phiếu nhập thành công ^^";
        }
        else
        {
            return "Xóa chi tiết không thành công :<";
        }
    }  
    public ArrayList<PhieunhapDTO> getAllPhieuNhap()
    {
        return pn.getAllPN();
    }
    public ArrayList<PhieunhapDTO> getAllCTPhieuNhap()
    {
        return pn.getAllCTPN();
    }
    public String[] getTenKhachHang()
    {
        String[]a=pn.getTenNhacungcap();
        return a;
    }
     public int getMaNhacungcap(String t)
    {
        int a = pn.getMaNhacungcap(t);
        return a;
    }
      public String[] getTenSanPham()
    {
        String[]a=pn.getTenSanPham();
        return a;
    }
       public int getMaSanPham(String t)
    {
        int a = pn.getMaSanPham(t);
        return a;
    }
       public void UpdateSLSP(int a,int b)
       {
           pn.UpdateSoluong(a, b);
       }
       public boolean KiemtraSPtrung(int b,int a)
    {
        return pn.KiemtraSPtrung(b, a);
    }
}
