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
import DTO.DangKyDTO;
import DAO.DangKyDAO;
import java.util.ArrayList;
public class DangKyBLL {
    DangKyDAO tk = new DangKyDAO();
    public ArrayList<DangKyDTO> getAllTK()
    {
        return tk.getAllTK();
    }
    public String addTaiKhoan(DangKyDTO TaiKhoan)
    {
        if(tk.hasTK(TaiKhoan.getTenDN()))
        {
            return "Tài khoản tồn tại";
        }
        if(tk.addTK(TaiKhoan))
        {
            return "Đăng ký thành công";
        }
        else
        {
            return "Đăng ký thất bại!!!";
        }
        
    }
}
