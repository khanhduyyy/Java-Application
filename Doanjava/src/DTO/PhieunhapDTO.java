/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.sql.Date;

/**
 *
 * @author KhanhDuy
 */
public class PhieunhapDTO {
    int MaPN,MaNCC,MaSP,Soluong;
    Date DatePN;
    String TenNCC,TenSP;
    public int getMaPN()
    {
        return MaPN;
    }
    public int getMaNCC()
    {
        return MaNCC;
    }
    public int getMaSP()
    {
        return MaSP;
    }
    public int getSoluong()
    {
        return Soluong;
    }
    public Date getDate()
    {
        return DatePN;
    }
    public String getTenNCC()
    {
        return TenNCC;
    }
    public String getTenSP()
    {
        return TenSP;
    }
    public void setTenNCC(String a)
    {
        TenNCC=a;
    }
    public void setTenSP(String a)
    {
        TenSP=a;
    }
    public void setMaPN(int a)
    {
        MaPN=a;
    }
    public void setMaNCC(int a)
    {
        MaNCC=a;
    }   
    public void setMaSP(int a)
    {
        MaSP=a;
    }
    public void setSoluong(int a)
    {
        Soluong=a;
    }
    public void setDate(Date a)
    {
        DatePN=a;
    }
}
