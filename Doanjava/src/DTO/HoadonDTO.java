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
public class HoadonDTO {
    int MaHD;
    int MaKH;
    int MaNV;
    int MaSP;
    int tongtien;
    int soluong;
    int dongia;
    int Thanhtien;
    String TenKH;
    String TenNV;
    String TenSP;
    Date DateHD;
    public Date getDate()
    {
       return DateHD;
    }
    public int getDonGia()
    {
        return dongia;
    }
    public int getMaHD()
    {
        return MaHD;
    }
    public int getMaKH()
    {
        return MaKH;
    }
    public int getMaNV()
    {
        return MaNV;
    }
    public int gettongtien()
    {
        return tongtien;
    }
    public String getTenKH()
    {
        return TenKH;
    }
    public String getTenNV()
    {
        return TenNV;
    }
    public int getMaSP()
    {
        return MaSP;
    }
    public String getTenSP()
    {
        return TenSP;
    }
    public int getSoLuong()
    {
        return soluong;
    }
    public int getThanhtien()
    {
        return Thanhtien;
    }
    public void setDate(Date d)
    {
        DateHD=d;
    }
    public void setDonGia(int m)
    {
        dongia=m;
    }
    public void setMaHD(int m)
    {
        MaHD=m;
    }
    public void setMaKH(int m)
    {
        MaKH=m;
    }
    public void setMaNV(int m)
    {
       MaNV=m;
    }
    public void settongtien(int t)
    {
        tongtien=t;
    }
    public void setTenKH(String t)
    {
        TenKH=t;
    }
    public void setTenNV(String t)
    {
        TenNV=t;
    }
    public void setMaSP(int m)
    {
        MaSP=m;
    }
    public void setTenSP(String t)
    {
        TenSP=t;
    }
    public void setSoLuong(int s)
    {
        soluong=s;
    }  
    public void setThanhtien(int t)
    {
        Thanhtien=t;
    }
}

