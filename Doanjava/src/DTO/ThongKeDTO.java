/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.sql.Date;

/**
 *
 * @author Home
 */
public class ThongKeDTO {
    int masp;
    String tensp;
    int theloai;
    int soluong;
    int dongia;
    int thanhtien;
    int MaHD;
    int tongtien;
    Date dateHD;
    String date;
    public ThongKeDTO()
    {
        masp=0;
        dongia=0;
        soluong=0;
        tensp=null;
        thanhtien=0;
        theloai=0;
        date=null;
    }
    public String getDate1()
    {
        return date;
    }
    public void setDate1(String a)
    {
        date=a;
    }
    public int getMaHD()
    {
        return MaHD;
    }
    public int getTongtien()
    {
        return tongtien;
    }
    public Date getDate()
    {
        return dateHD;
    }
    public int getMaSP()
    {
        return masp;
    }
     public String getTenSP()
    {
        return tensp;
    }
      public int getTheLoai()
    {
        return theloai;
    }
       public int getSoluong()
    {
        return soluong;
    }
        public int getDongia()
    {
        return dongia;
    }
         public int getThanhTien()
    {
        return thanhtien;
    }
    public void setMaSP(int a)
    {
        masp=a;
    }
    public void setTeSP(String a)
    {
        tensp=a;
    }
    public void setTheLoai(int a)
    {
        theloai=a;
    }
    public void setDonGia(int a)
    {
        dongia =a;
    }
    public void setSoLuong(int a)
    {
        soluong =a;
    }
    public void setThanhTien(int a)
    {
        thanhtien=a;
    }
    public void setMaHD(int a)
    {
        MaHD=a;
    }
    public void setTongtien(int a)
    {
        tongtien=a;
    }
    public void setDate(Date a)
    {
        dateHD=a;
    }
    public static void main(String[]args)
    {
       
        ThongKeDTO tk1 = new ThongKeDTO();
   
       tk1.setDate1("2000-06-11");
       System.out.println(tk1);
    }
}
