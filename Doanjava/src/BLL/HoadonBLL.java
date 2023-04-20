/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DTO.HoadonDTO;
import DAO.HoadonDAO;
import java.util.ArrayList;
public class HoadonBLL {
    HoadonDAO hd = new HoadonDAO();
    public ArrayList<HoadonDTO> getAllHoaDon()
    {
        return hd.getAllHD();
    }
    public ArrayList<HoadonDTO> getAllChitietHD()
    {
        return hd.getAllCTHD();
    }
     public ArrayList<HoadonDTO> tim(int a)
    {
        return hd.tim(a);
    }
     public ArrayList<HoadonDTO> timCTHD(int a)
    {
        return hd.timCTHD(a);
    }
    public String addHoadon(HoadonDTO Hoadon)
    {
        if(hd.addHD(Hoadon))
        {
            return "Thêm hóa đơn thành công ^^";
        }
        else
        {
            return "Thêm hóa đơn không thành công :<";
        }
    }
    public String xoaHoadon(int a)
      {
        if(hd.XoaHD(a))
        {
            return "Xóa hóa đơn thành công ^^";
        }
        else
        {
            return "Xóa hóa đơn không thành công :<";
        }
    }  
    public String suaHoadon(HoadonDTO Hoadon,int a)
    {
        if(hd.SuaHD(Hoadon, a))
        {
            return "Sửa hóa đơn thành công ^^";
        }
        else
            return "Sửa hóa đơn không thành công :<";
    }
    public String addCTHoadon(HoadonDTO Hoadon)
    {
        if(hd.addCTHD(Hoadon))
        {
            return "Thêm chi tiết hóa đơn thành công ^^";
        }
        else
        {
            return "Thêm chi tiết hóa đơn không thành công :<";
        }
    }
    public String suaChitietHoadon(HoadonDTO Hoadon,int a,int b)
    {
        if(hd.SuaCTHD(Hoadon,a,b))
        {
            return "Sửa chi tiết hóa đơn thành công ^^";
        }
        else
            return "Sửa chi tiết hóa đơn không thành công :<";
    }
    public String xoaCTHoadon(int a,int b)
      {
        if(hd.XoaCTHD(a,b))
        {
            return "Xóa hóa đơn thành công ^^";
        }
        else
        {
            return "Xóa hóa đơn không thành công :<";
        }
    }
    public String[] getTenKhachHang()
    {
        String[]a=hd.getTenKhachHang();
        return a;
    }
    public int[] getMaHoaDon()
    {
        int[]a=hd.getMaHoaDon();
        return a;
    }
    public String[] getTenSanPham()
    {
        String[]a=hd.getTenSanPham();
        return a;
    }
    public int getMaKhachhang(String t)
    {
        int a = hd.getMaKhachhang(t);
        return a;
    }
    public int getMaNhanVien(String t)
    {
        int a = hd.getMaNhanVien(t);
        return a;
    }
    public int getMaSanPham(String t)
    {
        int a = hd.getMaSanPham(t);
        return a;
    }
    public int getThanhTien(int b,int h)
    {
        int a = hd.getThanhTien(b, h);
        return a;
    }
    public boolean KiemtraSPtrung(int b,int a)
    {
        return hd.KiemtraSPtrung(b, a);
    }
    public boolean ktsoluongsp(int b,int h)
    {
        return hd.ktsoluongsp(b, h);
    }
    public void UpdateTongtien(int a,int tien)
    {
        hd.UpdateTongtien(a, tien);
    }
    public int getTongTien(int a)
    {
        int b = hd.getTongTien(a);
        return b;
    }
    public void XoaCTHDinHD(int a)
    {
        hd.XoaCTHDinHD(a);
       
    }
    public String[] getTenNhanvien()
    {
        String[]a=hd.getTenNhanvien();
        return a;
    }
    
}
