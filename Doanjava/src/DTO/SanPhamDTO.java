/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Home
 */
public class SanPhamDTO {
    int MaSP;
    String TenSP;
    int MaLoai;
    String TenLoai;
    int Giaban;
    int Soluong;
    String Hinh;
    public SanPhamDTO()
    {
        MaSP=0;
        TenSP=null;
        MaLoai=0;
        Giaban=0;
        Soluong=0;
        Hinh=null;
        TenLoai=null;
    }
    public String getTenLoai()
    {
        return TenLoai;
    }
    public void setTenLoai(String a)
    {
        TenLoai=a;
    }
    public int getIDSP()
    {
        return MaSP;
    }
    public String getTenSP()
    {
        return TenSP;
    }
    public int getGiaBan()
    {
        return Giaban;
    }
    public int getMaLoai()
    {
        return MaLoai;
    }
    public int getSoLuong()
    {
        return Soluong;
    }
    public String getHinh()
    {
        return Hinh;
    }
    public void setMaSP(int masp)
    {
        MaSP=masp;
    }
    public void setTenSP(String tensp)
    {
        TenSP=tensp;
    }
    public void setMaLoai(int maloai)
    {
        MaLoai=maloai;
    }
    public void setHinh(String hinh)
    {
        Hinh=hinh;
    }
    public void setSoLuong(int soluong)
    {
        Soluong=soluong;
    }
    public void setGiaBan(int giaban)
    {
        Giaban=giaban;
    }
    
}
