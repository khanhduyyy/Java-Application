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
public class DangNhapDTO {
    String tk;
    String mk;
    public DangNhapDTO()
    {
        tk=null;
        mk=null;
    }
    public String getTK()
    {
        return tk;
    }
    public String getMK()
    {
        return mk;
    }
    public void setTK(String a)
    {
        tk=a;
    }
    public void setMK(String a)
    {
        mk=a;
    }
    
}
