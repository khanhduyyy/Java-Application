/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import BLL.NhanvienBLL;
import static DAO.NhanvienDAO.getMaTaiKhoan;
import DTO.NhanvienDTO;
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
public class NhanvienGUI extends javax.swing.JFrame {
    NhanvienBLL nv1=new NhanvienBLL();
    public NhanvienGUI() {
        initComponents();
        this.setLocationRelativeTo(null);
        loadKHList();
        xulymaTK();
        txtmnv.setEditable(false);
        //txtmtk.setEditable(false);
        //txtmtk.setEnabled(false);
        jButton2.setEnabled(false);
        jButton3.setEnabled(false);
        jComboBox1.setSelectedItem("0");
        jComboBox1.setEditable(false);
        jTable1.setDefaultEditor(Object.class, null);
    }
    public void xulymaTK()
    {
        int a[]=getMaTaiKhoan();
        for(int i=0;i<a.length;i++)
        {
            jComboBox1.addItem(""+a[i]);
        }
    }
    public void tim()
    {
     
        DefaultTableModel dtm1 = new DefaultTableModel();
        jTable1.setModel(dtm1);
         dtm1.addColumn("Mã nhân viên");
                dtm1.addColumn("Mã tài khoản");
                dtm1.addColumn("Tên nhân viên");
                dtm1.addColumn("Giới tính");
                dtm1.addColumn("Số điện thoại");
                dtm1.addColumn("Lương");

        ArrayList<NhanvienDTO> arr1 = new ArrayList<>();
        if(txtTIM.getText().equals(""))
        {
            JOptionPane.showMessageDialog(rootPane, "Điền mã nhân viên cần tìm:");
        }
        else{
            try {
                                if(!txtTIM.getText().equals(""))
                {
                arr1= nv1.timTenNV(txtTIM.getText());
               
                for(int i = 0 ; i< arr1.size();i++)
                {
                    NhanvienDTO nv = arr1.get(i);
                    int manv = nv.getMaNV() ;
                    int matk = nv.getMATK();
                    String tennv = nv.getTenNV();
                    String gioitinh = nv.getGioitinh();
                    String sdt = nv.getSDT();
                    int luong = nv.getLuong();
                    Object[] row={manv,matk,tennv,gioitinh,sdt,luong};
                    dtm1.addRow(row);
                    
                    }
                }
                if(Integer.parseInt(txtTIM.getText())>0)
                {
                arr1= nv1.timNV(Integer.parseInt(txtTIM.getText()));
                for(int i = 0 ; i< arr1.size();i++)
                {
                    NhanvienDTO nv = arr1.get(i);
                    int manv = nv.getMaNV() ;
                    int matk = nv.getMATK();
                    String tennv = nv.getTenNV();
                    String gioitinh = nv.getGioitinh();
                    String sdt = nv.getSDT();
                    int luong = nv.getLuong();
                    Object[] row={manv,matk,tennv,gioitinh,sdt,luong};
                    dtm1.addRow(row);
                    
                }
                }

                
            } catch (Exception e) {
                
                              }
        
        }
    }
    public void them()
    {
        try{
            String a=txtten.getText();;
            String b=jComboBox2.getSelectedItem().toString();
            String c=txtl.getText();
            int e=Integer.parseInt(c);
            String d=txtsdt.getText();
            int sdt = Integer.parseInt(d);
            int r=Integer.parseInt(d);
            if(e<0)
                throw new ArithmeticException();
            if(a.length()<1||b.length()<1||c.length()<1||d.length()<1)
            {
                JOptionPane.showMessageDialog(rootPane,"Vui lòng nhập đầy đủ thông tin :v");
                    return;
            }
            else
            {
                if(sdt<0)
                {
                    JOptionPane.showMessageDialog(this, "Thông tin không hợp lệ"); 
                }
                else
                {
            NhanvienDTO nv = new NhanvienDTO();
            int t=Integer.parseInt(jComboBox1.getSelectedItem().toString());
            nv.setMaTK(t);
            nv.setTenNV(txtten.getText());
            nv.setGioitinh(b);
            nv.setLuong(Integer.parseInt(txtl.getText()));
            nv.setSDT(txtsdt.getText());
            JOptionPane.showMessageDialog(this, nv1.addNhanvien(nv));
            }
            }
        }
        catch ( Exception e) {
            JOptionPane.showMessageDialog(this, "Thông tin không hợp lệ"); 
        }
    }
     public void loadKHList()
    {
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Mã nhân viên");
        dtm.addColumn("Mã tài khoản");
        dtm.addColumn("Tên nhân viên");
        dtm.addColumn("Giới tính");
        dtm.addColumn("Số điện thoại");
        dtm.addColumn("Lương");
        jTable1.setModel(dtm);
        ArrayList<NhanvienDTO> arr = new ArrayList<>();
       
        arr= nv1.getAllNhanvien();
        for(int i =0;i<arr.size();i++)
        {
            NhanvienDTO nv = arr.get(i);
            int idnv = nv.getMaNV();
            int idtk=nv.getMATK();
            String ten = nv.getTenNV();
            String gt = nv.getGioitinh();
            String sdt= nv.getSDT();
            int luong= nv.getLuong();
            Object[] row = {idnv,idtk,ten,gt,sdt,luong};
            dtm.addRow(row);
            
        }
    }
     public void xoa()
    {
        try{
            int a=Integer.parseInt(txtmnv.getText());
            JOptionPane.showMessageDialog(this, nv1.xoaNhanvien(a));
        }
        catch(Exception e){
            System.out.println(e);
                }

    }
     public void sua()
    {
        try{
            NhanvienDTO nv = new NhanvienDTO();
            String b=jComboBox2.getSelectedItem().toString();
            String c=txtl.getText();
            int e=Integer.parseInt(c);
            String d=txtsdt.getText();
            int r=Integer.parseInt(d);
            if(e<0)
                throw new ArithmeticException();
            int sdt = Integer.parseInt(d);
            nv.setTenNV(txtten.getText());
            nv.setGioitinh(b);
            nv.setSDT(txtsdt.getText());
            nv.setLuong(Integer.parseInt(txtl.getText()));
            int id=Integer.parseInt(txtmnv.getText());
            if(sdt<0)
            {
                JOptionPane.showMessageDialog(this, "Thông tin không hợp lệ");
            }
            else
            {
            JOptionPane.showMessageDialog(this, nv1.suaKhachhang(nv,id));
            }
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "Thông tin không hợp lệ");
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtten = new javax.swing.JTextField();
        txtsdt = new javax.swing.JTextField();
        txtl = new javax.swing.JTextField();
        txtmnv = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        txtTIM = new javax.swing.JTextField();
        btnTIM = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Nhân viên");

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

        jLabel2.setText("Tên nhân viên:");

        jLabel3.setText("Giới tính:");

        jLabel4.setText("Số điện thoại:");

        jLabel5.setText("Lương:");

        jLabel6.setText("Mã nhân viên:");

        jLabel7.setText("Mã tài khoản:");

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

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        btnTIM.setText("Tìm");
        btnTIM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTIMActionPerformed(evt);
            }
        });

        jButton7.setText("Thoát");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel8.setText("Nhập tên NV:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));

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
                .addGap(285, 285, 285)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtmnv, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel3))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtten, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(4, 4, 4)
                                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addGap(42, 42, 42)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtsdt)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtl, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 129, Short.MAX_VALUE)))))
                                .addGap(90, 90, 90))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(txtTIM, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton3)
                                    .addComponent(jButton2)
                                    .addComponent(jButton1)
                                    .addComponent(jButton4))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnTIM)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 675, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jButton2)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtsdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtmnv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTIM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(btnTIM)
                    .addComponent(jLabel7)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7))
                .addGap(29, 29, 29))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        them();
        loadKHList();
        txtmnv.setText("");
        //txtmtk.setText("");
        txtten.setText("");
        txtsdt.setText("");
        txtl.setText("");
        jButton2.setEnabled(false);
        jButton3.setEnabled(false);
        jComboBox1.setSelectedItem("0");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
         //System.out.print(jTable1.getSelectedRow());
        txtmnv.setText(""+jTable1.getValueAt(jTable1.getSelectedRow(),0));
        //txtmtk.setText(""+jTable1.getValueAt(jTable1.getSelectedRow(),1));
        txtten.setText(""+jTable1.getValueAt(jTable1.getSelectedRow(),2));
        jComboBox2.setSelectedItem(""+jTable1.getValueAt(jTable1.getSelectedRow(),3));
        txtsdt.setText(""+jTable1.getValueAt(jTable1.getSelectedRow(),4));
        txtl.setText(""+jTable1.getValueAt(jTable1.getSelectedRow(),5));
        String a=""+jTable1.getValueAt(jTable1.getSelectedRow(),1);
        jButton1.setEnabled(false);
        jButton2.setEnabled(true);
        jButton3.setEnabled(true);
        //txtmtk.setEnabled(true);
        jComboBox1.setEnabled(false);
        jComboBox1.setSelectedItem(a);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int kt=JOptionPane.showConfirmDialog(rootPane, "Bạn chắc chắn xóa không?");
        if(kt==JOptionPane.YES_OPTION){
        xoa();
        loadKHList();
        txtmnv.setText("");
        //txtmtk.setText("");
        txtten.setText("");
        txtsdt.setText("");
        txtl.setText("");
        //txtmtk.setEnabled(false);
        jButton1.setEnabled(true);
        jButton2.setEnabled(false);
        jButton3.setEnabled(false);
        jComboBox1.setEnabled(true);
        jComboBox1.setSelectedItem("0");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       int kt=JOptionPane.showConfirmDialog(rootPane, "Bạn chắc chắn sửa không?");
        if(kt==JOptionPane.YES_OPTION){
        sua();
        }
        loadKHList();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        loadKHList();
        txtmnv.setText("");
        //txtmtk.setText("");
        txtten.setText("");
        txtsdt.setText("");
        txtl.setText("");
        jButton1.setEnabled(true);
        jButton2.setEnabled(false);
        jButton3.setEnabled(false);
        //txtmtk.setEnabled(false);
        jComboBox1.setEnabled(true);
        jComboBox1.setSelectedItem("0");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
     
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void btnTIMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTIMActionPerformed
       tim();
    }//GEN-LAST:event_btnTIMActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        HomeGUI h=new HomeGUI(null);
        h.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton7ActionPerformed

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
                model.addColumn("Mã nhân viên");
                model.addColumn("Mã tài khoản");
                model.addColumn("Tên nhân viên");
                model.addColumn("Giới tính");
                model.addColumn("Số điện thoại");
                model.addColumn("Lương");
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
            java.util.logging.Logger.getLogger(NhanvienGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NhanvienGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NhanvienGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NhanvienGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NhanvienGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTIM;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtTIM;
    private javax.swing.JTextField txtl;
    private javax.swing.JTextField txtmnv;
    private javax.swing.JTextField txtsdt;
    private javax.swing.JTextField txtten;
    // End of variables declaration//GEN-END:variables
}
