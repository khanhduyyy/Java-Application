
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//SELECT hoadon.MaHD,khachhang.TenKH,nhanvien.TenNV  FROM hoadon,khachhang,nhanvien WHERE khachhang.MaKH=hoadon.MaKH AND nhanvien.MaNV=hoadon.MaNV
package GUI;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.JOptionPane;
import BLL.HoadonBLL;
import DAO.HoadonDAO;

import DTO.HoadonDTO;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import static org.apache.commons.math3.fitting.leastsquares.LeastSquaresFactory.model;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class HoadonGUI extends javax.swing.JFrame {

    HoadonBLL hd1=new HoadonBLL();
    public HoadonGUI() {
        initComponents();
        this.setLocationRelativeTo(null);
        loadHDList();
        loadEmptyCTHDList();
        xulyTenKH();
        xulyTenNV();
        xulyMaHD();
        xulyTenSP();
        txtmhd.setEditable(false);
        txtt.setEditable(false);
        jTextField4.setEditable(false);
        jButton2.setEnabled(false);
        jButton3.setEnabled(false);
        jButton6.setEnabled(false);
        jButton7.setEnabled(false);
        jTable1.setDefaultEditor(Object.class, null);
        jTable2.setDefaultEditor(Object.class, null);
        jComboBox3.setEnabled(false);
    }
    public int addComboBox()
    {
        int b=0;
        int a[]=hd1.getMaHoaDon();
        for(int i=0;i<a.length;i++)
        {
            b=a[i];
        }
        return b;
    }
    public void xulyTenKH()
    {
        String a[]=hd1.getTenKhachHang();
        for(int i=0;i<a.length;i++)
        {
            jComboBox1.addItem(""+a[i]);
        }
    }
     public void xulyTenNV()
    {
        String a[]=hd1.getTenNhanvien();
        for(int i=0;i<a.length;i++)
        {
            jComboBox2.addItem(""+a[i]);
        }
    }
     public void xulyMaHD()
     {
         int a[]=hd1.getMaHoaDon();
         for(int i=0;i<a.length;i++)
        {
            jComboBox3.addItem(""+a[i]);
        }
     }
     public void xulyTenSP()
    {
        String a[]=hd1.getTenSanPham();
        for(int i=0;i<a.length;i++)
        {
            jComboBox4.addItem(""+a[i]);
        }
    }
    public void them()
    {
        try{
            HoadonDTO hd = new HoadonDTO();
            long millis=System.currentTimeMillis();
            java.sql.Date date=new java.sql.Date(millis);
            String t=jComboBox1.getSelectedItem().toString();
            int b=hd1.getMaKhachhang(t);
            String y=jComboBox2.getSelectedItem().toString();
            int c=hd1.getMaNhanVien(y);
            hd.setMaKH(b);
            hd.setMaNV(c);
            hd.settongtien(0);
            hd.setDate(date);
            JOptionPane.showMessageDialog(this, hd1.addHoadon(hd));
        }
        catch ( NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Thông tin không hợp lệ"); 
        }
    }
     public void themCTHD()
    {
        try{
            HoadonDTO hd = new HoadonDTO();
            int a=Integer.parseInt(jComboBox3.getSelectedItem().toString());
            hd.setMaHD(a);
            String t=jComboBox4.getSelectedItem().toString();
            int h=Integer.parseInt(jTextField3.getText());
            if(h<0)
                throw new ArithmeticException();
            int b=hd1.getMaSanPham(t);
            int tien=hd1.getThanhTien(b,h);
            if(!hd1.KiemtraSPtrung(b,a))
            {
                if(hd1.ktsoluongsp(b,h))
                {
                    hd.setMaSP(b);
                    hd.setSoLuong(Integer.parseInt(jTextField3.getText()));
                    hd.setThanhtien(tien);
                    JOptionPane.showMessageDialog(this, hd1.addCTHoadon(hd));
                    hd1.UpdateTongtien(a,hd1.getTongTien(a));
                }
                else JOptionPane.showMessageDialog(rootPane, "Sản phẩm hiện không đủ số lượng");
            }
            else
                JOptionPane.showMessageDialog(rootPane, "Sản phẩm đã có trong hóa đơn");
        }
        catch ( Exception e) {
            JOptionPane.showMessageDialog(this, "Thông tin không hợp lệ"); 
        }
    }
    public void loadHDList()
    {
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Mã hóa đơn");
        dtm.addColumn("Tên khách hàng");
        dtm.addColumn("Tên nhân viên");
        dtm.addColumn("Tổng tiền");
        dtm.addColumn("Ngày lập HĐ");
        jTable1.setModel(dtm);
        ArrayList<HoadonDTO> arr = new ArrayList<>();
        int tongtien=0;
        arr= hd1.getAllHoaDon();
        for(int i =0;i<arr.size();i++)
        {
            HoadonDTO hd = arr.get(i);
            int idhd = hd.getMaHD();
            tongtien=hd1.getTongTien(idhd);
            String tenkh=hd.getTenKH();
            String tennv=hd.getTenNV();
            Date date=hd.getDate();
            //tongtien= hd.gettongtien();
            //System.out.println(date);
            Object[] row = {idhd,tenkh,tennv,tongtien,date};
            dtm.addRow(row);
           
        }
    }
    public void loadCTHDList()
    {
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Mã hóa đơn");
        dtm.addColumn("Tên sản phẩm");
        dtm.addColumn("Số lượng");
        dtm.addColumn("Đơn Giá");
        dtm.addColumn("Thành tiền");
        jTable2.setModel(dtm);
        ArrayList<HoadonDTO> arr = new ArrayList<>();
        int m=Integer.parseInt(""+jTable1.getValueAt(jTable1.getSelectedRow(),0)); 
        arr= hd1.getAllChitietHD();
        for(int i =0;i<arr.size();i++)
        {
            HoadonDTO hd = arr.get(i);
            int idhd = hd.getMaHD();
            String tensp = hd.getTenSP();
            int Soluong=hd.getSoLuong();
            int dongia=hd.getDonGia();
            int thanhtien= hd.getThanhtien();
            if(m==idhd)
            {
            Object[] row = {idhd,tensp,Soluong,dongia,thanhtien};
            dtm.addRow(row);
            }
        }
    }
    public void loadEmptyCTHDList()
    {
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Mã hóa đơn");
        dtm.addColumn("Tên sản phẩm");
        dtm.addColumn("Số lượng");
        dtm.addColumn("Thành tiền");
        jTable2.setModel(dtm);
    }
    public void xoa()
    {
        try{
            int a=Integer.parseInt(txtmhd.getText());
            hd1.XoaCTHDinHD(a);
            JOptionPane.showMessageDialog(this, hd1.xoaHoadon(a));
        }
        catch(Exception e){
            System.out.println(e);
            }

    }
     public void tim()
    {
        try {
            if(txtTIM.getText().equals(""))
            {
                JOptionPane.showMessageDialog(rootPane, "Điền Vào Chỗ Trống");
            }
            else
            {   
                DefaultTableModel dtm1 = new DefaultTableModel();
                dtm1.addColumn("Mã hóa đơn");
                dtm1.addColumn("Tên khách hàng");
                dtm1.addColumn("Tên nhân viên");
                dtm1.addColumn("Tổng tiền");
                jTable1.setModel(dtm1);
        ArrayList<HoadonDTO> arr = new ArrayList<>();
         
        arr= hd1.tim(Integer.parseInt(txtTIM.getText()));
        for(int i =0;i<arr.size();i++)
        {
            HoadonDTO hd = arr.get(i);
            int idhd = hd.getMaHD();
            String tenkh = hd.getTenKH();
            String tennv=hd.getTenNV();
            int tongtien= hd.gettongtien();

            Object[] row = {idhd,tenkh,tennv,tongtien};
            dtm1.addRow(row);
           
        }
        DefaultTableModel dtm2 = new DefaultTableModel();
         dtm2.addColumn("Mã hóa đơn");
        dtm2.addColumn("Tên sản phẩm");
        dtm2.addColumn("Số lượng");
        dtm2.addColumn("Đơn Giá");
        dtm2.addColumn("Thành tiền");
        jTable2.setModel(dtm2);
        ArrayList<HoadonDTO> arr1 = new ArrayList<>();
         
        arr1= hd1.timCTHD(Integer.parseInt(txtTIM.getText()));
        for(int i =0;i<arr1.size();i++)
        {
            HoadonDTO hd = arr1.get(i);
            int idhd = hd.getMaHD();
            String tensp = hd.getTenSP();
            int soluong =hd.getSoLuong();
            int dongia= hd.getDonGia();
            int thanhtien =hd.getThanhtien();
            Object[] row = {idhd,tensp,soluong,dongia,thanhtien};
            dtm2.addRow(row);
           
        }
        
            }
//                if(txtTIM.getText().equals(""))
//            {
//                JOptionPane.showMessageDialog(rootPane, "Điền Vào Chỗ Trống");
//            }
//            else
//            { 
//                        //int m=Integer.parseInt(""+jTable1.getValueAt(jTable1.getSelectedRow(),0)); 
//                        //System.out.println(jTable1.getValueAt(jTable1.getSelectedRow(),0));
//                        jTable1.getValueAt(0, 0);
//                        System.out.print(jTable1.getValueAt(4, 1));
//                        jTable1.getSelectedRow();
//                        System.out.print(jTable1.getSelectedRow());
//            }
        }catch(Exception e)
        {
            System.out.print(e);
        }
    }
    public void xoaCTHD()
    {
        try{
            int a=Integer.parseInt(txtmhd.getText());
            String t=jComboBox4.getSelectedItem().toString();
            int b=hd1.getMaSanPham(t);
            JOptionPane.showMessageDialog(this, hd1.xoaCTHoadon(a,b));
            int tongtien=hd1.getTongTien(a);
            int h=Integer.parseInt(jTextField3.getText());
            hd1.UpdateTongtien(a,tongtien);
            hd1.ktsoluongsp(b,-h);
        }
        catch(Exception e){
            System.out.println(e);
            }

    }
    public void sua()
    {
        try{
            HoadonDTO hd = new HoadonDTO();
            hd.settongtien(Integer.parseInt(txtt.getText()));
            int id=Integer.parseInt(""+jTable1.getValueAt(jTable1.getSelectedRow(),0)); 
            JOptionPane.showMessageDialog(this, hd1.suaHoadon(hd,id));
        }catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public void suaCTHD()
    {
        try{
            HoadonDTO hd = new HoadonDTO();
            hd.setSoLuong(Integer.parseInt(jTextField3.getText()));
            int id=Integer.parseInt(txtmhd.getText());
            String t=jComboBox4.getSelectedItem().toString();
            int b=hd1.getMaSanPham(t);
            int h=Integer.parseInt(jTextField3.getText());
            if(h<0)
                throw new ArithmeticException();
            if(hd.getSoLuong()>h)
            {
                if(hd1.ktsoluongsp(b,h))
            {
            JOptionPane.showMessageDialog(this, hd1.suaChitietHoadon(hd,id,b));
            int tongtien=hd1.getTongTien(id);
            hd1.UpdateTongtien(id,tongtien);
            }
            }
            else
            {
                JOptionPane.showMessageDialog(this, hd1.suaChitietHoadon(hd,id,b));
                int tongtien=hd1.getTongTien(id);
                hd1.UpdateTongtien(id,tongtien);
                hd1.ktsoluongsp(b,-h);
            }
            
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(rootPane, "Thông tin không hợp lệ");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txtmhd = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtt = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        txtTIM = new javax.swing.JTextField();
        btnTIM = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Hóa đơn");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        txtmhd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmhdActionPerformed(evt);
            }
        });

        jLabel2.setText("Mã hóa đơn:");

        jLabel3.setText("Mã khách hàng:");

        jLabel4.setText("Mã nhân viên:");

        jLabel5.setText("Tổng tiền:");

        jButton1.setText("Thêm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Xóa");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Sửa");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Reset");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jButton5.setText("Thêm");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel6.setText("Mã hóa đơn:");

        jLabel7.setText("Tên sản phẩm:");

        jLabel8.setText("Số lượng:");

        jLabel9.setText("Thành tiền:");

        jButton6.setText("Sửa");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Xóa");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Reset");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        btnTIM.setText("Tìm");
        btnTIM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTIMActionPerformed(evt);
            }
        });

        jButton12.setText("Thoát");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jLabel10.setText("Nhập mã HĐ:");

        jMenu1.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/print.png"))); // NOI18N
        jMenuItem1.setText("Print");
        jMenuItem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem1MouseClicked(evt);
            }
        });
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/export.png"))); // NOI18N
        jMenuItem2.setText("Export Excel");
        jMenuItem2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem2MouseClicked(evt);
            }
        });
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/import.png"))); // NOI18N
        jMenuItem3.setText("Import Excel");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(287, 287, 287)
                        .addComponent(jLabel1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTIM, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(121, 121, 121)
                                .addComponent(btnTIM))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7))
                                .addGap(14, 14, 14)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jButton8)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addGap(5, 5, 5)
                                                .addComponent(jButton2)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton4)))
                                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton5)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton7)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton6))
                                    .addComponent(jLabel6)
                                    .addComponent(jButton1))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtmhd, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtt, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addGap(42, 42, 42))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtmhd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)
                            .addComponent(jButton3)
                            .addComponent(jButton4))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtTIM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTIM))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton5)
                            .addComponent(jButton7)
                            .addComponent(jButton6)
                            .addComponent(jButton8))
                        .addGap(22, 22, 22))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jButton12)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        them();
        int a=addComboBox();
        jComboBox3.addItem(""+a);
        loadHDList();
        txtmhd.setText("");
        txtt.setText("");
        jButton2.setEnabled(false);
        jButton3.setEnabled(false);
        jButton6.setEnabled(false);
        jButton7.setEnabled(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int kt=JOptionPane.showConfirmDialog(rootPane, "Bạn chắc chắn xóa không?");
        if(kt==JOptionPane.YES_OPTION){
        xoa();
        loadHDList();
        loadEmptyCTHDList();
        jComboBox3.removeAllItems();
        xulyMaHD();
        txtmhd.setText("");
        txtt.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jButton1.setEnabled(true);
        jButton2.setEnabled(false);
        jButton3.setEnabled(false);
        jComboBox1.setEnabled(true);
        jComboBox2.setEnabled(true);
        txtmhd.setEditable(false);
        jTextField4.setEditable(false);
        jTable1.setDefaultEditor(Object.class, null);
        jTable2.setDefaultEditor(Object.class, null);
        jButton6.setEnabled(false);
        jButton7.setEnabled(false);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        //System.out.print(jTable1.getSelectedRow());
        txtmhd.setText(""+jTable1.getValueAt(jTable1.getSelectedRow(),0));
        jComboBox1.setSelectedItem(""+jTable1.getValueAt(jTable1.getSelectedRow(),1));
        jComboBox2.setSelectedItem(""+jTable1.getValueAt(jTable1.getSelectedRow(),2));
        txtt.setText(""+jTable1.getValueAt(jTable1.getSelectedRow(),3));
        jTextField3.setText("");
        jTextField4.setText("");
        jButton1.setEnabled(false);
        jButton2.setEnabled(true);
        jButton3.setEnabled(true);
        jComboBox1.setEnabled(false);
        jComboBox2.setEnabled(false);
        loadCTHDList();
        jTextField3.setEnabled(true);
        jTextField4.setEnabled(true);
        jComboBox3.setSelectedItem(""+jTable1.getValueAt(jTable1.getSelectedRow(),0));
        jButton6.setEnabled(false);
        jButton7.setEnabled(false);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int kt=JOptionPane.showConfirmDialog(rootPane, "Bạn chắc chắn sửa không?");
        /*if(kt==JOptionPane.YES_OPTION){
        sua();
        }
        jButton6.setEnabled(false);
        jButton7.setEnabled(false);
        loadHDList();
        loadEmptyCTHDList();*/
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        loadHDList();
        loadEmptyCTHDList();
        txtmhd.setText("");
        txtt.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jButton1.setEnabled(true);
        jButton2.setEnabled(false);
        jButton3.setEnabled(false);
        jComboBox1.setEnabled(true);
        jComboBox2.setEnabled(true);
        txtmhd.setEditable(false);
        jTextField4.setEditable(false);
        jTable1.setDefaultEditor(Object.class, null);
        jTable2.setDefaultEditor(Object.class, null);
        jButton6.setEnabled(false);
        jButton7.setEnabled(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtmhdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmhdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmhdActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        themCTHD();
        loadHDList();
        loadEmptyCTHDList();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        int kt=JOptionPane.showConfirmDialog(rootPane, "Bạn chắc chắn sửa không?");
        if(kt==JOptionPane.YES_OPTION){
        suaCTHD();
        }
        loadHDList();
        loadEmptyCTHDList();
        txtmhd.setText("");
        txtt.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        
        int kt=JOptionPane.showConfirmDialog(rootPane, "Bạn chắc chắn xóa không?");
        if(kt==JOptionPane.YES_OPTION){
        xoaCTHD();
        loadHDList();
        loadEmptyCTHDList();
        txtmhd.setText("");
        txtt.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jButton1.setEnabled(true);
        jButton2.setEnabled(false);
        jButton3.setEnabled(false);
        jComboBox1.setEnabled(true);
        jComboBox2.setEnabled(true);
        txtmhd.setEditable(false);
        jTextField4.setEditable(false);
        jTable1.setDefaultEditor(Object.class, null);
        jTable2.setDefaultEditor(Object.class, null);
        jButton6.setEnabled(false);
        jButton7.setEnabled(false);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        loadHDList();
        loadEmptyCTHDList();
        txtmhd.setText("");
        txtt.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jButton1.setEnabled(true);
        jButton2.setEnabled(false);
        jButton3.setEnabled(false);
        jComboBox1.setEnabled(true);
        jComboBox2.setEnabled(true);
        txtmhd.setEditable(false);
        jTextField4.setEditable(false);
        jTable1.setDefaultEditor(Object.class, null);
        jTable2.setDefaultEditor(Object.class, null);
        jButton6.setEnabled(false);
        jButton7.setEnabled(false);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        //System.out.print(jTable1.getSelectedRow());
        jComboBox3.setSelectedItem(""+jTable2.getValueAt(jTable2.getSelectedRow(),0));
        jComboBox4.setSelectedItem(""+jTable2.getValueAt(jTable2.getSelectedRow(),1));
        jTextField3.setText(""+jTable2.getValueAt(jTable2.getSelectedRow(),2));
        jTextField4.setText(""+jTable2.getValueAt(jTable2.getSelectedRow(),3));
        jButton6.setEnabled(true);
        jButton7.setEnabled(true);
    }//GEN-LAST:event_jTable2MouseClicked

    private void btnTIMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTIMActionPerformed
        tim();
    }//GEN-LAST:event_btnTIMActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        HomeGUI h=new HomeGUI(null);
        h.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jMenuItem1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseClicked

    }//GEN-LAST:event_jMenuItem1MouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        try {
           Class.forName("com.mysql.cj.jdbc.Driver");
            Hashtable map=new Hashtable();
            JasperReport report=JasperCompileManager.compileReport("src/gui/InHoadon.jrxml");
            int id=Integer.parseInt(txtmhd.getText());
            map.put("MaHD", id);
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/doanjava?zeroDateTimeBehavior=convertToNull", "root", "123");
            JasperPrint p=JasperFillManager.fillReport(report, map,conn);
            JasperViewer.viewReport(p,false);
                    
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Không thể in hóa đơn");
            System.out.println(e);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem2MouseClicked
       
    }//GEN-LAST:event_jMenuItem2MouseClicked

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
         FileOutputStream excelFOU=null;
            BufferedOutputStream excelBOU=null;
            XSSFWorkbook excelJTableExporter=null;
            JFileChooser excelFileChooser =new JFileChooser("C:\\Users\\Home\\Desktop");
       excelFileChooser.setDialogTitle("SAVE AS");
        FileNameExtensionFilter fnef=new FileNameExtensionFilter("EXCEL FILES","xls","xlsx","xlsm");
        excelFileChooser.setFileFilter(fnef);
        int excelChooser=excelFileChooser.showSaveDialog(null);
        if(excelChooser==JFileChooser.APPROVE_OPTION)
        {
            
           try {
               excelJTableExporter=new XSSFWorkbook();
               XSSFSheet excelSheet=excelJTableExporter.createSheet("JTable Sheet");
               for(int i=0;i<jTable1.getRowCount();i++)
               {
                   XSSFRow excelRow=excelSheet.createRow(i);
                   for(int j=0;j<jTable1.getColumnCount();j++)
                   {
                       XSSFCell excelCell=excelRow.createCell(j);
                       excelCell.setCellValue(jTable1.getValueAt(i, j).toString());
                   }
                   
               }  excelFOU = new FileOutputStream(excelFileChooser.getSelectedFile() + ".xlsx");
                  excelBOU=new BufferedOutputStream(excelFOU);
               excelJTableExporter.write(excelBOU);
               JOptionPane.showMessageDialog(null,"Export file Excel thành công ^^");
           } catch (FileNotFoundException ex) {
               ex.printStackTrace();
           } catch (IOException ex) {
               ex.printStackTrace();
           } finally {
               try {
                   if(excelBOU != null)
                   {
                       excelBOU.close();
                   }
                   if(excelFOU != null)
                   {
                       excelFOU.close();
                   }
                  if(excelJTableExporter != null)
                   {
                       excelJTableExporter.close();
                   }
               } catch (IOException ex) {
                   ex.printStackTrace();
               }
           }
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
         DefaultTableModel model = new DefaultTableModel();
        File excelFile;
        FileInputStream excelFIS=null;
        BufferedInputStream excelBIS=null;
        XSSFWorkbook excelJtableImport=null;
        JFileChooser excelFileChooser =new JFileChooser("C:\\Users\\Home\\Desktop");
        int excelChooser=excelFileChooser.showOpenDialog(null);
        
        if(excelChooser==JFileChooser.APPROVE_OPTION){
            try {
                excelFile=excelFileChooser.getSelectedFile();
                excelFIS=new FileInputStream(excelFile);
                excelBIS= new BufferedInputStream(excelFIS);
                
                excelJtableImport=new XSSFWorkbook(excelBIS);
                XSSFSheet excelSheet=excelJtableImport.getSheetAt(0);
                jTable1.setModel(model);
                model.addColumn("Mã hóa đơn");
                model.addColumn("Tên khách hàng");
                model.addColumn("Tên nhân viên");
                model.addColumn("Tổng tiền");
                for(int row=0;row<=excelSheet.getLastRowNum();row++)
                {
                    XSSFRow excelRow=excelSheet.getRow(row);
                    XSSFCell excel1=excelRow.getCell(0);
                    XSSFCell excel2=excelRow.getCell(1);
                    XSSFCell excel3=excelRow.getCell(2);
                    XSSFCell excel4=excelRow.getCell(3);
                    
               
                    
                    
                    model.addRow(new Object[]{excel1,excel2,excel3,excel4});
                    
//                    for(int column=0;column<excelRow.getLastCellNum();column++)
 //                   {
 //                       XSSFCell excelCell=excelRow.getCell(column);
 //                       System.out.println(excelCell.getStringCellValue());
                        
 //                   }
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            } 
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HoadonGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HoadonGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HoadonGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HoadonGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HoadonGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTIM;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField txtTIM;
    private javax.swing.JTextField txtmhd;
    private javax.swing.JTextField txtt;
    // End of variables declaration//GEN-END:variables
}
