/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author KhanhDuy
 */
public class NhanvienDTO {
    int MaNV;
    int MaTK;
    String TenNV;
    String Gioitinh;
    String SDT;
    int Luong;
    public int getMaNV()
    {
        return MaNV;
    }
    public int getMATK()
    {
        return MaTK;
    }
    public String getTenNV()
    {
        return TenNV;
    }
    public String getGioitinh()
    {
        return Gioitinh;
    }
    public String getSDT()
    {
        return SDT;
    }
    public int getLuong()
    {
        return Luong;
    }
    public void setMaNV(int m)
    {
        MaNV=m;
    }
    public void setMaTK(int m)
    {
        MaTK=m;
    }
    public void setTenNV(String t)
    {
        TenNV=t;
    }
    public void setGioitinh(String g)
    {
        Gioitinh=g;
    }
    public void setSDT(String s)
    {
        SDT=s;
    }
    public void setLuong(int l)
    {
        Luong=l;
    }
}
