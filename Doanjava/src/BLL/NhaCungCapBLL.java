/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAO.NhaCungCapDAO;
import DTO.NhaCungCapDTO;
import java.util.ArrayList;


public class NhaCungCapBLL {
    NhaCungCapDAO nccDAO = new NhaCungCapDAO();
    public ArrayList<NhaCungCapDTO> getAll()
    {
        return nccDAO.getAllNCC();
    }
    public int getSoluongMa()
    {
        int a = nccDAO.getSoluong();
        return a;
    }
    public String xoa(NhaCungCapDTO a)
    {
       if(nccDAO.xoa(a))
       {
           return "xóa thành công";
       }
       else
       {
           return "xóa không thành công";
       }
    }
    public String them(NhaCungCapDTO a)
    {
        if(nccDAO.them(a))
        {
            return "Thêm thành công";
        }
        else
        {
            return "thêm thất bại";
        }
    }
    public String sua(NhaCungCapDTO ncc)
    {
        if(nccDAO.sua(ncc))
        {
            return "sửa thành công";
        }
        else
        {
            return "sửa không thành công";
        }
    }
    
}
