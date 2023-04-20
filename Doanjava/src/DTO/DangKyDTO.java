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
public class DangKyDTO {
    private int MaTK;
    private int MaQuyen;
    private String TenDN;
    private String Matkhau;
    public DangKyDTO()
    {
        MaTK=0;
        MaQuyen=0;
        TenDN=null;
        Matkhau=null;
    }
    public int getMaTK()
    {
        return MaTK;
    }
    public int getMaQuyen()
    {
        return MaQuyen;
    }
    public String getTenDN()
    {
        return TenDN;
    }
    public String getMatkhau()
    {
        return Matkhau;
    }
    public void setMaTK(int m)
    {
        MaTK=m;
    }
    public void setMaQuyen(int m)
    {
        MaQuyen=m;
    }
    public void setTenDN(String t)
    {
        TenDN=t;
    }
    public void setMatkhau(String m)
    {
        Matkhau=m;
    }
}

