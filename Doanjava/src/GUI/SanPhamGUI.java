/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BLL.SanPhamBLL;
import DTO.SanPhamDTO;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Home
 */
public class SanPhamGUI extends javax.swing.JFrame {

   SanPhamBLL spBLL = new SanPhamBLL();
    public SanPhamGUI() {
        initComponents();
        this.setLocationRelativeTo(null);
        loadListSanPham();
        loadTK();
        xulyidSP();
        txtMASP.setEnabled(false);
        jTable1.setDefaultEditor(Object.class, null);
        xulyML();
    }
    public void xulyML()
    {
        String[]a=spBLL.getTenLoai();
        for(int i =0 ;i<a.length;i++)
        {
            jComboBox1.addItem(""+a[i]);
        }
    }
    public void xulyidSP()
    {
        txtMASP.setEnabled(false);
        int a = spBLL.getAllMSP();
        txtMASP.setText(String.valueOf(a+1));
        
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
    public void loadTK()
    {
              DefaultTableModel dtm = new DefaultTableModel();
             dtm.addColumn("Mã sản phẩm");
             dtm.addColumn("Tên sản phẩm");
             dtm.addColumn("Mã loại");
             dtm.addColumn("Giá bán ");
             dtm.addColumn("Số lượng");
       jTable2.setModel(dtm);
    }
    public void clear()
    {
        
        txtGIABAN.setText("");
        txtHINH.setText("");
        txtTimbyTen.setText("");
        txtTIM.setText("");
        txtSOLUONG.setText("");
        txtTENSP.setText("");
        
    }
    public void timByten()
    {
        try {
            if("".equals(txtTimbyTen.getText()))
            {
                JOptionPane.showMessageDialog(rootPane, "Điền Vào Chỗ Trống");
            }
            else
            {
             DefaultTableModel dtm = new DefaultTableModel();
            dtm.addColumn("Mã sản phẩm");
            dtm.addColumn("Tên sản phẩm");
            dtm.addColumn("Mã loại");
            dtm.addColumn("Giá bán ");
            dtm.addColumn("Số lượng");
            jTable2.setModel(dtm);
            
               ArrayList<SanPhamDTO> arr = new ArrayList<>();
               arr = spBLL.getAllTenSPByTen(txtTimbyTen.getText());
       for(int i =0;i<arr.size();i++)
       {
           SanPhamDTO spDTO = arr.get(i);
           int id = spDTO.getIDSP();
           String ten = spDTO.getTenSP();
           int ml = spDTO.getMaLoai();
           int giaban = spDTO.getGiaBan();
           int sl = spDTO.getSoLuong();
           String hinh = spDTO.getHinh();
           Object row[] = {id,ten,ml,giaban,sl,hinh};
           dtm.addRow(row);
           
       }
       
            }
        }
       catch(Exception e){
               System.out.println(e);
               
               }
    }
               


       
            
      
    
    public void timByID()
    {
         
        
            try{
                if("".equals(txtTIM.getText()))
            {
                JOptionPane.showMessageDialog(rootPane, "Điền Vào Chỗ Trống");
            }
                else{
            
             DefaultTableModel dtm = new DefaultTableModel();
            dtm.addColumn("Mã sản phẩm");
            dtm.addColumn("Tên sản phẩm");
            dtm.addColumn("Mã loại");
            dtm.addColumn("Giá bán ");
            dtm.addColumn("Số lượng");
            jTable2.setModel(dtm);
       
       
           ArrayList<SanPhamDTO> arr1 = new ArrayList<>();
           arr1 = spBLL.getAllTenSPByID(Integer.parseInt(txtTIM.getText()));
       for(int i =0;i<arr1.size();i++)
       {
           SanPhamDTO spDTO = arr1.get(i);
           int id = spDTO.getIDSP();
           String ten = spDTO.getTenSP();
           int ml = spDTO.getMaLoai();
           int giaban = spDTO.getGiaBan();
           int sl = spDTO.getSoLuong();
           String hinh = spDTO.getHinh();
           Object row[] = {id,ten,ml,giaban,sl,hinh};
           dtm.addRow(row);
          
       }
                }
       }
            
         catch (Exception e) {
            System.out.println("loi!!!  "+e);
            JOptionPane.showMessageDialog(rootPane, "Điền Sai Nha");
        }
    
    }

    public void them()
    {
        try {
            int h=Integer.parseInt(txtGIABAN.getText());
            int j=Integer.parseInt(txtSOLUONG.getText());
            if(h<0||j<0)
                throw new ArithmeticException();
            if(txtMASP.equals("")||txtGIABAN.equals("")||txtHINH.equals("")||txtSOLUONG.equals("")||txtTENSP.equals(""))
            {
                JOptionPane.showMessageDialog(rootPane, "Điền Vào Chỗ Trống");
            }
            else
            {
                SanPhamDTO spDTO = new SanPhamDTO();      
                spDTO.setTenSP(txtTENSP.getText());
                String t=jComboBox1.getSelectedItem().toString();
                spDTO.setTenLoai(t);
                spDTO.setGiaBan(Integer.parseInt(txtGIABAN.getText()));
                spDTO.setSoLuong(Integer.parseInt(txtSOLUONG.getText()));
                spDTO.setHinh(txtHINH.getText());
                JOptionPane.showMessageDialog(rootPane, spBLL.addSanPham(spDTO));
                loadListSanPham();
                xulyidSP();
                clear();
                loadEmptyCTHDList();
            }
        } catch (Exception e) {
           JOptionPane.showMessageDialog(this, "Thông tin không hợp lệ");
        }
    }
    public void xoa()
    {
        try {
            if(txtMASP.getText().equals(""))
            {
                JOptionPane.showMessageDialog(rootPane, "Chọn MaSP cần xoá");
            }
            else
            {
                int a = JOptionPane.showConfirmDialog(rootPane, "Bạn có chắc là muốn xóa không");
                if(a==JOptionPane.YES_OPTION){
                    SanPhamDTO spDTO = new SanPhamDTO();
                    int b = spBLL.getAllMSP();
                    spDTO.setMaSP(Integer.parseInt(txtMASP.getText()));
                    
                    JOptionPane.showMessageDialog(rootPane, spBLL.deleteSanPham(spDTO));
                    loadListSanPham();
                    
                   txtMASP.setText(String.valueOf(b+1));
                   btnThem.setEnabled(true);
                    clear();
                    
                    
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void reset()
    {
        int a = spBLL.getAllMSP();
        clear();
        txtMASP.setText(String.valueOf(a+1));
        
    }
    public void sua()
    {
        try {
            int h=Integer.parseInt(txtGIABAN.getText());
            int j=Integer.parseInt(txtSOLUONG.getText());
            if(h<0||j<0)
                throw new ArithmeticException();
            if(txtMASP.getText().equals(""))
            {
                JOptionPane.showMessageDialog(rootPane, "Chọn MaSP cần sửa");
            }
            else{
                 int a = JOptionPane.showConfirmDialog(rootPane, "ban có chắc là sửa không");
                if(a==JOptionPane.YES_OPTION){
            SanPhamDTO sp = new SanPhamDTO();
            int t=Integer.parseInt(jComboBox1.getSelectedItem().toString());
            sp.setMaLoai(t);
            sp.setGiaBan(Integer.parseInt(txtGIABAN.getText()));
            sp.setHinh(txtHINH.getText());
            sp.setMaSP(Integer.parseInt(txtMASP.getText()));
            sp.setSoLuong(Integer.parseInt(txtSOLUONG.getText()));
            sp.setTenSP(txtTENSP.getText());
            JOptionPane.showMessageDialog(rootPane, spBLL.suaSanPham(sp));
            loadListSanPham();
            
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Thông tin không hợp lệ");
        }
    }

   public void loadListSanPham()
   {
       DefaultTableModel dtm = new DefaultTableModel();
       dtm.addColumn("Mã sản phẩm");
       dtm.addColumn("Tên sản phẩm");
       dtm.addColumn("Tên loại");
       dtm.addColumn("Giá bán ");
       dtm.addColumn("Số lượng");
       dtm.addColumn("Hình");
       jTable1.setModel(dtm);
       ArrayList<SanPhamDTO> arr = new ArrayList<>();
       arr = spBLL.getAllSanPham();
       for(int i =0;i<arr.size();i++)
       {
           SanPhamDTO spDTO = arr.get(i);
           int id = spDTO.getIDSP();
           String ten = spDTO.getTenSP();
           String tl = spDTO.getTenLoai();
           int giaban = spDTO.getGiaBan();
           int sl = spDTO.getSoLuong();
           String hinh = spDTO.getHinh();
           Object row[] = {id,ten,tl,giaban,sl,hinh};
           dtm.addRow(row);
          
       }
       
   }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMASP = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTENSP = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtGIABAN = new javax.swing.JTextField();
        txtSOLUONG = new javax.swing.JTextField();
        txtHINH = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        txtTIM = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        btnThem = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btnRS = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtTimbyTen = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Mã sản phẩm:");

        jLabel2.setText("Tên sản phẩm:");

        jLabel3.setPreferredSize(new java.awt.Dimension(40, 0));

        jLabel4.setText("Giá bán:");

        jLabel5.setText("Số lượng");

        jLabel6.setText("Hình");

        jLabel7.setText("Tên loại:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTENSP, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                    .addComponent(txtMASP))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtGIABAN, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(59, 59, 59)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSOLUONG)
                    .addComponent(txtHINH, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtMASP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(txtSOLUONG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(20, 20, 20)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(txtGIABAN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHINH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTENSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jButton3.setText("Tìm");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTable2);

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        jButton2.setText("Xóa");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("Sửa");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnRS.setText("Reset");
        btnRS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRSActionPerformed(evt);
            }
        });

        jLabel8.setText("Nhập ID:");

        jLabel9.setText("Nhập tên sản phẩm");

        jButton7.setText("Tìm");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Thoát");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("Sản phẩm");

        jMenu1.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/export.png"))); // NOI18N
        jMenuItem1.setText("Export Excel");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/import.png"))); // NOI18N
        jMenuItem2.setText("Import Excel");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(166, 166, 166)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1028, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(423, 423, 423)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(253, 253, 253)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnThem)
                                .addGap(58, 58, 58)
                                .addComponent(jButton2)
                                .addGap(49, 49, 49)
                                .addComponent(jButton1)
                                .addGap(41, 41, 41)
                                .addComponent(btnRS))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTIM, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTimbyTen, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton7))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(416, 416, 416)
                        .addComponent(jLabel10)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(jButton2)
                    .addComponent(jButton1)
                    .addComponent(btnRS))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(txtTIM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton3)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTimbyTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton7)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton8)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        them();
    }//GEN-LAST:event_btnThemActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
      xoa();
    }//GEN-LAST:event_jButton2MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
             txtMASP.setText(""+jTable1.getValueAt(jTable1.getSelectedRow(),0));
             txtMASP.setEnabled(false);
        txtTENSP.setText(""+jTable1.getValueAt(jTable1.getSelectedRow(),1));
        String a=""+jTable1.getValueAt(jTable1.getSelectedRow(),2);
     
        jComboBox1.setSelectedItem(a);
        txtGIABAN.setText(""+jTable1.getValueAt(jTable1.getSelectedRow(),3));
        txtSOLUONG.setText(""+jTable1.getValueAt(jTable1.getSelectedRow(),4));
        txtHINH.setText(""+jTable1.getValueAt(jTable1.getSelectedRow(),5));
        btnThem.setEnabled(false);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        sua();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
      timByID();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnRSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRSActionPerformed
        btnThem.setEnabled(true);
        reset();
        loadEmptyCTHDList();
        
    }//GEN-LAST:event_btnRSActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        timByten();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        HomeGUI h=new HomeGUI(null);
        h.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
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
                model.addColumn("Tên sản phẩm");
                model.addColumn("Mã thể loại");
                model.addColumn("Giá Bán");
                model.addColumn("Số lượng");
                model.addColumn("Hình");
                
                for(int row=0;row<=excelSheet.getLastRowNum();row++)
                {
                    XSSFRow excelRow=excelSheet.getRow(row);
                    XSSFCell excel1=excelRow.getCell(0);
                    XSSFCell excel2=excelRow.getCell(1);
                    XSSFCell excel3=excelRow.getCell(2);
                    XSSFCell excel4=excelRow.getCell(3);
                    XSSFCell excel5=excelRow.getCell(4);
                    XSSFCell excel6=excelRow.getCell(5);
                
                    
                    
                    
                    model.addRow(new Object[]{excel1,excel2,excel3,excel4,excel5,excel6});
                    
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
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
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
    }//GEN-LAST:event_jMenuItem1ActionPerformed

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
            java.util.logging.Logger.getLogger(SanPhamGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SanPhamGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SanPhamGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SanPhamGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SanPhamGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRS;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox<String> jComboBox1;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField txtGIABAN;
    private javax.swing.JTextField txtHINH;
    private javax.swing.JTextField txtMASP;
    private javax.swing.JTextField txtSOLUONG;
    private javax.swing.JTextField txtTENSP;
    private javax.swing.JTextField txtTIM;
    private javax.swing.JTextField txtTimbyTen;
    // End of variables declaration//GEN-END:variables
}
