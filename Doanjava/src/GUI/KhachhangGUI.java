/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import BLL.KhachhangBLL;
import DTO.KhachhangDTO;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author KhanhDuy
 */
public class KhachhangGUI extends javax.swing.JFrame {
    KhachhangBLL kh1=new KhachhangBLL();
    public KhachhangGUI() {
        initComponents();
        this.setLocationRelativeTo(null);
        loadKHList();
        txtma.setEditable(false);
        jButton2.setEnabled(false);
        jButton3.setEnabled(false);
        jTable1.setDefaultEditor(Object.class, null);
    }
    public void tim()
    {
        DefaultTableModel dtm1 = new DefaultTableModel();
        jTable1.setModel(dtm1);
        dtm1.addColumn("Mã khách hàng");
        dtm1.addColumn("Tên khách hàng");
        dtm1.addColumn("Giới tính");
        dtm1.addColumn("Địa chỉ");
        dtm1.addColumn("Số điện thoại");
   
        ArrayList<KhachhangDTO> arr = new ArrayList<>();
        if(txtTIM.getText().equals(""))
        {
            JOptionPane.showMessageDialog(rootPane, "Điền vào chỗ trống");
        }
        else
        {
            try {
                if(!txtTIM.getText().equals(""))
                {
                    arr= kh1.timTenKH(txtTIM.getText());
                for(int i = 0 ; i< arr.size();i++)
                {
                    KhachhangDTO kh = arr.get(i);
                    int makh = kh.getMaKH();
                    String tenkh = kh.getTenKH();
                    String gt = kh.getGioitinh();
                    String dc = kh.getDiachi();
                    String sdt = kh.getSDT();
                    Object[] row = {makh,tenkh,gt,dc,sdt};
                    dtm1.addRow(row);
                    
                }
                }
                else
                {
                arr= kh1.timKH(Integer.parseInt(txtTIM.getText()));
                for(int i = 0 ; i< arr.size();i++)
                {
                    KhachhangDTO kh = arr.get(i);
                    int makh = kh.getMaKH();
                    String tenkh = kh.getTenKH();
                    String gt = kh.getGioitinh();
                    String dc = kh.getDiachi();
                    String sdt = kh.getSDT();
                    Object[] row = {makh,tenkh,gt,dc,sdt};
                    dtm1.addRow(row);
                }
                }
            } catch (Exception e) {
                System.out.print(e);
            }
        }
     
        
        
    }
    public void sua()
    {
        try{
            KhachhangDTO kh = new KhachhangDTO();
            String b=jComboBox1.getSelectedItem().toString();
            String d=txtsdt.getText();
            int e = Integer.parseInt(d);
            kh.setTenKH(txtkh.getText());
            kh.setGioitinh(b);
            kh.setDiachi(txtdc.getText());
            kh.setSDT(txtsdt.getText());
            int id=Integer.parseInt(txtma.getText());
            int a = Integer.parseInt(txtsdt.getText());
            if(a<0)
            {
                JOptionPane.showMessageDialog(rootPane, "Nhập thông tin sai!!!");
            }
            else
            {
            JOptionPane.showMessageDialog(this, kh1.suaKhachhang(kh,id));
            }
        }catch(Exception e)
        {
            System.out.println(e);
            JOptionPane.showMessageDialog(rootPane, "Nhập thông tin sai!!!");
        }
    }
    public void them()
    {
        try{
            String a=txtkh.getText();
            String b=jComboBox1.getSelectedItem().toString();
            String c=txtdc.getText();
            String d=txtsdt.getText();
            int e = Integer.parseInt(d);
            
            if(a.length()<1||b.length()<1||c.length()<1||d.length()<1)
            {
                JOptionPane.showMessageDialog(rootPane,"Vui lòng nhập đầy đủ thông tin :v");
                    return;
            }
           
                else{
                int sdt = Integer.parseInt(d);
                if(sdt>0)
                {
            KhachhangDTO kh = new KhachhangDTO();
            kh.setTenKH(txtkh.getText());
            kh.setGioitinh(b);
            kh.setDiachi(txtdc.getText());
            kh.setSDT(txtsdt.getText());
            JOptionPane.showMessageDialog(this, kh1.addKhachhang(kh));
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Thông tin không hợp lệ");
                }
            }
        }
        catch ( NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Thông tin không hợp lệ"); 
        }
    }
    public void xoa()
    {
        try{
            int a=Integer.parseInt(txtma.getText());
            JOptionPane.showMessageDialog(this, kh1.xoaKhachhang(a));
        }
        catch(Exception e){
            System.out.println(e);
                }

    }
    public void loadKHList()
    {
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Mã khách hàng");
        dtm.addColumn("Tên khách hàng");
        dtm.addColumn("Giới tính");
        dtm.addColumn("Địa chỉ");
        dtm.addColumn("Số điện thoại");
        jTable1.setModel(dtm);
        ArrayList<KhachhangDTO> arr = new ArrayList<>();
       
        arr= kh1.getAllKhachhang();
        for(int i =0;i<arr.size();i++)
        {
            KhachhangDTO kh = arr.get(i);
            int id = kh.getMaKH();
            String ten = kh.getTenKH();
            String gt = kh.getGioitinh();
            String dc = kh.getDiachi();
            String sdt= kh.getSDT();
            Object[] row = {id,ten,gt,dc,sdt};
            dtm.addRow(row);
            
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtkh = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtdc = new javax.swing.JTextField();
        txtsdt = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        txtma = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        txtTIM = new javax.swing.JTextField();
        btnTIM = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Khách hàng");

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

        jLabel2.setText("Tên khách hàng:");

        jLabel3.setText("Giới tính:");

        jLabel4.setText("Địa chỉ:");

        jLabel5.setText("Số điện thoại");

        jButton1.setText("Thêm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel6.setText("Mã khách hàng:");

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

        jLabel7.setText("Nhập tên KH:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));

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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtdc, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                            .addComponent(txtkh))
                        .addGap(112, 112, 112)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3)
                            .addComponent(jButton2)
                            .addComponent(jButton1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtsdt, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(108, 108, 108))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtma, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTIM, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton4)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnTIM)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(28, 28, 28))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 687, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(42, 42, 42)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(270, 270, 270)
                        .addComponent(jLabel1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtkh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jButton2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtdc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtsdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtTIM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTIM)
                    .addComponent(jLabel7)
                    .addComponent(jButton7))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        them();
        loadKHList();
        txtma.setText("");
        txtkh.setText("");
        txtdc.setText("");
        txtsdt.setText("");
        jButton2.setEnabled(false);
        jButton3.setEnabled(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        //System.out.print(jTable1.getSelectedRow());
        txtma.setText(""+jTable1.getValueAt(jTable1.getSelectedRow(),0));
        txtkh.setText(""+jTable1.getValueAt(jTable1.getSelectedRow(),1));
        jComboBox1.setSelectedItem(""+jTable1.getValueAt(jTable1.getSelectedRow(),2));
        txtdc.setText(""+jTable1.getValueAt(jTable1.getSelectedRow(),3));
        txtsdt.setText(""+jTable1.getValueAt(jTable1.getSelectedRow(),4));
        jButton1.setEnabled(false);
        jButton2.setEnabled(true);
        jButton3.setEnabled(true);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int kt=JOptionPane.showConfirmDialog(rootPane, "Bạn chắc chắn xóa không?");
        if(kt==JOptionPane.YES_OPTION){
        xoa();
        loadKHList();
        txtma.setText("");
        txtkh.setText("");
        txtdc.setText("");
        txtsdt.setText("");
        jButton1.setEnabled(true);
        jButton2.setEnabled(false);
        jButton3.setEnabled(false);
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
        txtma.setText("");
        txtkh.setText("");
        txtdc.setText("");
        txtsdt.setText("");
        jButton1.setEnabled(true);
        jButton2.setEnabled(false);
        jButton3.setEnabled(false);
    }//GEN-LAST:event_jButton4ActionPerformed

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
                model.addColumn("Mã khách hàng");
                model.addColumn("Tên khách hàng");
                model.addColumn("Giới tính");
                model.addColumn("Địa chỉ");
                model.addColumn("Số điện thoại");
                for(int row=0;row<=excelSheet.getLastRowNum();row++)
                {
                    XSSFRow excelRow=excelSheet.getRow(row);
                    XSSFCell excel1=excelRow.getCell(0);
                    XSSFCell excel2=excelRow.getCell(1);
                    XSSFCell excel3=excelRow.getCell(2);
                    XSSFCell excel4=excelRow.getCell(3);
                    XSSFCell excel5=excelRow.getCell(4);
                  
                    
                    
                    model.addRow(new Object[]{excel1,excel2,excel3,excel4,excel5});
                    
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
            java.util.logging.Logger.getLogger(KhachhangGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KhachhangGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KhachhangGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KhachhangGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KhachhangGUI().setVisible(true);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtTIM;
    private javax.swing.JTextField txtdc;
    private javax.swing.JTextField txtkh;
    private javax.swing.JTextField txtma;
    private javax.swing.JTextField txtsdt;
    // End of variables declaration//GEN-END:variables
}
