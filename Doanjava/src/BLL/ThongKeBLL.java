/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DTO.ThongKeDTO;
import DAO.ThongKeDAO;
import java.util.ArrayList;

/**
 *
 * @author Home
 */
public class ThongKeBLL {
    ThongKeDAO tkDAO= new ThongKeDAO();
    public ArrayList<ThongKeDTO> getTK(String a, String b)
    {
        return tkDAO.thongke(a,b);
    }
    public ArrayList<ThongKeDTO> getTL(String a, String b, String c )
    {
        return tkDAO.getTL(a,b,c);
    }
    public String[] getMaLoai()
    {
        String [] a = tkDAO.getMaloai();
        return a;
    }
    public int getTongTien(int a)
    {
        return tkDAO.getTongDoanhThu(a);
    }
    public ArrayList<ThongKeDTO> getSPKHO()
    {
        return tkDAO.getSPKHO();
    }
    public ArrayList<ThongKeDTO> getThongKe(int a)
    {
        return tkDAO.getThongke(a);
    }
}
