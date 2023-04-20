/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAO.DangNhapDAO;
import javax.swing.JOptionPane;

/**
 *
 * @author Home
 */
public class DangNhapBLL {
    DangNhapDAO dn = new DangNhapDAO();
    public boolean checkDN(String a, String b)
    {
        if(dn.checkDN(a, b))
        {
            JOptionPane.showMessageDialog(null, "đăng nhập thành công");
            return true;
        }
        else
        {
            JOptionPane.showMessageDialog(null, "đăng nhập thất bại");
            return false;
        }
    }
    public String tao(String a,String b)
    {
        String c;
        c=dn.tao(a, b);
        return c;
    }
    
}
