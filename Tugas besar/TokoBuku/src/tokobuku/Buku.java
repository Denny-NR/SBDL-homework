/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokobuku;
import com.mysql.jdbc.Connection;
import java.awt.HeadlessException;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author ASUS
 */
public class Buku extends javax.swing.JFrame {
    private Koneksi Koneksi = new Koneksi();
    /**
     * Creates new form Buku
     */
    public Buku() {
        initComponents();
        initDataBuku("select * from buku");
    }
    
        public void reset(){
        field_id_buku.setText("");
        field_judul.setText("");
        field_pengarang.setText("");
        field_penerbit.setText("");
        field_tahun_terbit.setText("");
        field_stok.setText("");
        field_harga.setText("");
    }
    
     private void initDataBuku(String sql){
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("id_buku");
        tableModel.addColumn("judul_buku");
        tableModel.addColumn("pengarang");
        tableModel.addColumn("penerbit");
        tableModel.addColumn("tahun_terbit");
        tableModel.addColumn("stok");
        tableModel.addColumn("harga");
        
        try{
            ResultSet resultSet = Koneksi.statement.executeQuery(sql);
            while (resultSet.next()){
                    tableModel.addRow(new Object[]{
                        resultSet.getString("id_buku"),
                        resultSet.getString("judul_buku"),
                        resultSet.getString("pengarang"),
                        resultSet.getString("penerbit"),
                        resultSet.getString("tahun_terbit"),
                        resultSet.getString("stok"),
                        resultSet.getString("harga"),
                    });
            }
            table_buku.setModel(tableModel);  
        }catch (SQLException e){
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }
     
    private void simpanDataBuku(){
        String sql = "INSERT INTO buku (id_buku, judul_buku, pengarang, penerbit, tahun_terbit, stok, harga)" + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = Koneksi.koneksi.prepareStatement(sql);
            ps.setString(1, field_id_buku.getText());
            ps.setString(2, field_judul.getText());
            ps.setString(3, field_pengarang.getText());
            ps.setString(4, field_penerbit.getText());
            ps.setString(5, field_tahun_terbit.getText());
            ps.setString(6, field_stok.getText());
            ps.setString(7, field_harga.getText());
            ps.executeUpdate();
            reset();
            JOptionPane.showMessageDialog(rootPane, "Data tersimpan");
            initDataBuku("select * from buku");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(rootPane, "Data Gagal DIsimpan" + "\n Error"+e.getMessage());
        }
    }
    
    private void UpdateBuku(){
        String sql = "Update buku set judul_buku=?, pengarang=?, penerbit=?, tahun_terbit=?, stok=?, harga=?"+"WHERE id_buku=?";
        
        try {
            PreparedStatement ps = Koneksi.koneksi.prepareStatement(sql);
            ps.setString(7, field_id_buku.getText());
            ps.setString(1, field_judul.getText());
            ps.setString(2, field_pengarang.getText());
            ps.setString(3, field_penerbit.getText());
            ps.setString(4, field_tahun_terbit.getText());
            ps.setString(5, field_stok.getText());
            ps.setString(6, field_harga.getText());
            System.out.println(ps.toString());
            ps.executeUpdate();
            reset();
            JOptionPane.showMessageDialog(rootPane, "Data tersimpan");
            initDataBuku("select * from buku");
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(rootPane, "Data Gagal Disimpan" + "\n Error"+e.getMessage());
        }
    }
    
    private void hapusDataBuku(){
        String sql = "DELETE FROM buku WHERE id_buku = ?";
        try {
            PreparedStatement ps = Koneksi.koneksi.prepareStatement(sql);
            ps.setString(1, field_id_buku.getText());
            ps.executeUpdate();
            initDataBuku("select * from buku");
            reset();
            JOptionPane.showMessageDialog(rootPane, "Data terhapus");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(rootPane, "Data Gagal Dihapus" + "\n Error"+e.getMessage());
        }
    }
    
    private void cariBuku(String key) {
        try {
            Object[] judul_kolom = {"id_buku","judul_buku","pengarang","penerbit","tahun_terbit","stok","harga"};
            DefaultTableModel tablemodel = new DefaultTableModel(null, judul_kolom);
            table_buku.setModel(tablemodel);
            tablemodel.getDataVector().removeAllElements();
            ResultSet rs = Koneksi.statement.executeQuery("SELECT*FROM buku WHERE judul_buku LIKE '%"+key+"%'");
            while(rs.next()){
                Object[] data = {
                    rs.getString("id_buku"),
                    rs.getString("judul_buku"),
                    rs.getString("pengarang"),
                    rs.getString("penerbit"),
                    rs.getString("tahun_terbit"),
                    rs.getString("stok"),
                    rs.getString("harga")
                };
                tablemodel.addRow(data);
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
    
    //view
    private void stokKosong(){
        try {
            Object[] judul_kolom = {"id_buku","judul_buku","pengarang","penerbit","tahun_terbit","stok","harga"};
            DefaultTableModel tablemodel = new DefaultTableModel(null, judul_kolom);
            table_buku.setModel(tablemodel);
            tablemodel.getDataVector().removeAllElements();
            ResultSet rs = Koneksi.statement.executeQuery("SELECT*FROM stok_habis"); //call view
            while(rs.next()){
                Object[] data = {
                    rs.getString("id_buku"),
                    rs.getString("judul_buku"),
                    rs.getString("pengarang"),
                    rs.getString("penerbit"),
                    rs.getString("tahun_terbit"),
                    rs.getString("stok"),
                    rs.getString("harga")
                };
                tablemodel.addRow(data);
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        field_judul = new javax.swing.JTextField();
        field_pengarang = new javax.swing.JTextField();
        field_penerbit = new javax.swing.JTextField();
        field_stok = new javax.swing.JTextField();
        save_buku = new javax.swing.JButton();
        update_buku = new javax.swing.JButton();
        hapus_buku = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_buku = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        field_cari = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        field_harga = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        field_id_buku = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        field_tahun_terbit = new javax.swing.JTextField();
        Back = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 0, 36)); // NOI18N
        jLabel1.setText("BUKU");

        jLabel2.setText("Judul Buku");

        jLabel3.setText("Pengarang");

        jLabel4.setText("Penerbit");

        jLabel5.setText("Tahun Terbit");

        jLabel6.setText("Stok");

        field_judul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                field_judulActionPerformed(evt);
            }
        });

        save_buku.setText("Save");
        save_buku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_bukuActionPerformed(evt);
            }
        });

        update_buku.setText("Update");
        update_buku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_bukuActionPerformed(evt);
            }
        });

        hapus_buku.setText("Delete");
        hapus_buku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapus_bukuActionPerformed(evt);
            }
        });

        table_buku.setModel(new javax.swing.table.DefaultTableModel(
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
        table_buku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_bukuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_buku);

        jLabel7.setText("Cari :");

        field_cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                field_cariKeyReleased(evt);
            }
        });

        jLabel8.setText("Harga");

        jLabel9.setText("Id Buku");

        jButton4.setText("Stok Habis");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });

        Back.setText("Back");
        Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackActionPerformed(evt);
            }
        });

        jButton1.setText("Transaksi");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Laporan Transaksi");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Beranda");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3))))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(field_id_buku, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(field_judul, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(field_pengarang, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(field_penerbit, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(108, 108, 108)
                                        .addComponent(save_buku, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(341, 341, 341)
                                        .addComponent(jButton4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel7)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(field_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(Back))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(update_buku)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(hapus_buku, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(414, 414, 414)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(field_tahun_terbit, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(field_stok, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(field_harga, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(358, 358, 358))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addGap(28, 28, 28))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(field_judul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(field_pengarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(field_penerbit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(field_id_buku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(field_tahun_terbit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(field_stok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(field_harga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(update_buku)
                            .addComponent(hapus_buku)
                            .addComponent(save_buku))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jLabel7)
                    .addComponent(field_cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Back))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(7, 7, 7))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void field_judulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_field_judulActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_field_judulActionPerformed

    private void save_bukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_bukuActionPerformed
        // TODO add your handling code here:
        simpanDataBuku();
    }//GEN-LAST:event_save_bukuActionPerformed

    private void update_bukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_bukuActionPerformed
        // TODO add your handling code here:
        UpdateBuku();
    }//GEN-LAST:event_update_bukuActionPerformed

    private void hapus_bukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapus_bukuActionPerformed
        // TODO add your handling code here:
        hapusDataBuku();
    }//GEN-LAST:event_hapus_bukuActionPerformed

    private void field_cariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_field_cariKeyReleased
        // TODO add your handling code here:
        String key = field_cari.getText();
        System.out.print(key);
        
        if(key!=""){
            cariBuku(key);
        } else {
            initDataBuku("select * from buku");
        }
    }//GEN-LAST:event_field_cariKeyReleased

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        // TODO add your handling code here:
        stokKosong();
    }//GEN-LAST:event_jButton4MouseClicked

    private void BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackActionPerformed
        // TODO add your handling code here:
        initDataBuku("select * from buku");
    }//GEN-LAST:event_BackActionPerformed

    private void table_bukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_bukuMouseClicked
        // TODO add your handling code here:
        int tabel = table_buku.getSelectedRow();
        String a = table_buku.getValueAt(tabel, 0).toString();
        String b = table_buku.getValueAt(tabel, 1).toString();
        String c = table_buku.getValueAt(tabel, 2).toString();
        String d = table_buku.getValueAt(tabel, 3).toString();
        String e = table_buku.getValueAt(tabel, 4).toString();
        String f = table_buku.getValueAt(tabel, 5).toString();
        String g = table_buku.getValueAt(tabel, 6).toString();
        
        field_id_buku.setText(a);
        field_judul.setText(b);
        field_pengarang.setText(c);
        field_penerbit.setText(d);
        field_tahun_terbit.setText(e);
        field_stok.setText(f);
        field_harga.setText(g);
        
    }//GEN-LAST:event_table_bukuMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Transaksi n = new Transaksi();
        n.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        transaction_Report m = new transaction_Report();
        m.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        Beranda o = new Beranda();
        o.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(Buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Buku().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Back;
    private javax.swing.JTextField field_cari;
    private javax.swing.JTextField field_harga;
    private javax.swing.JTextField field_id_buku;
    private javax.swing.JTextField field_judul;
    private javax.swing.JTextField field_penerbit;
    private javax.swing.JTextField field_pengarang;
    private javax.swing.JTextField field_stok;
    private javax.swing.JTextField field_tahun_terbit;
    private javax.swing.JButton hapus_buku;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton save_buku;
    private javax.swing.JTable table_buku;
    private javax.swing.JButton update_buku;
    // End of variables declaration//GEN-END:variables
}
