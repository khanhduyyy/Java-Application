/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;


public class NhaCungCapDTO {
    String tenncc;
    int mancc;
    String diachi;
    String sdt;
    public NhaCungCapDTO()
    {
        tenncc=null;
        mancc=0;
        diachi=null;
        sdt=null;
    }
    public void setTenNCC(String a)
    {
        tenncc=a;
    }
    public void setMaNCC(int a)
    {
        mancc=a;
    }
    public void setSDT(String a)
    {
        sdt=a;
    }
    public void setDiaChiNCC(String a)
    {
        diachi=a;
    }
    public String getTenNCC()
    {
        return tenncc;
    }
    public int getMaNCC()
    {
        return mancc;
    }
    public String getDiaChiNCC()
    {
        return diachi;
    }
    public String getSDT()
    {
        return sdt;
    }
}
