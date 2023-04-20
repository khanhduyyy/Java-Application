/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DTO.KhachhangDTO;
import DAO.KhachhangDAO;
import java.util.ArrayList;
public class KhachhangBLL {
    KhachhangDAO kh = new KhachhangDAO();
    public ArrayList<KhachhangDTO> getAllKhachhang()
    {
        return kh.getAllKH();
    }
    public ArrayList<KhachhangDTO> timKH(int maKH)
    {
        return kh.timKH(maKH);
    }
     public ArrayList<KhachhangDTO> timTenKH(String tenKH)
    {
        return kh.timTenKH(tenKH);
    }
    public String addKhachhang(KhachhangDTO Khachhang)
    {
        if(kh.addKH(Khachhang))
        {
            return "Thêm khách hàng thành công ^^";
        }
        else
        {
            return "Thêm khách hàng không thành công :<";
        }
    }
    public String xoaKhachhang(int a)
      {
        if(kh.XoaKH(a))
        {
            return "Xóa khách hàng thành công ^^";
        }
        else
        {
            return "Xóa khách hàng không thành công :<";
        }
    }  
    public String suaKhachhang(KhachhangDTO Khachhang,int a)
    {
        if(kh.SuaKH(Khachhang, a))
        {
            return "Sửa khách hàng thành công ^^";
        }
        else
            return "Sửa khách hàng không thành công :<";
    }
}
