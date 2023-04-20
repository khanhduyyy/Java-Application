/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;


public class KhachhangDTO {
    private int MaKH;
    private String TenKH;
    private String Gioitinh;
    private String Diachi;
    private String SDT;

    public int getMaKH()
    {
        return MaKH;
    }
    public String getGioitinh()
    {
        return Gioitinh;
    }
    public String getDiachi()
    {
        return Diachi;
    }
    public String getSDT()
    {
        return SDT;
    }
    public String getTenKH()
    {
        return TenKH;
    }
    public void setMaKH(int m)
    {
         MaKH=m;
    }
    public void setTenKH(String t)
    {
        TenKH=t;
    }
    public void setGioitinh(String g)
    {
        Gioitinh=g;
    }
    public void setDiachi(String d)
    {
        Diachi=d;
    }
    public void setSDT(String s)
    {
        SDT=s;
    }
}
