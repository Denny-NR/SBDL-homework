/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokobuku;
import com.mysql.jdbc.Connection;
import java.awt.HeadlessException;
import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class transaction_Report extends javax.swing.JFrame {
    private Koneksi Koneksi = new Koneksi();
    /**
     * Creates new form transaction_Report
     */
    public transaction_Report() {
        initComponents();
    }
    
    private void LeftJoin(){
         try {
            Object[] judul_kolom = {"id_buku","judul_buku","pengarang","penerbit","tahun_terbit","stok","harga", "id_transaksi", "id_buku", "waktu_transaksi", "jumlah_beli", "jumlah_transaksi"};
            DefaultTableModel tablemodel = new DefaultTableModel(null, judul_kolom);
            Report_table.setModel(tablemodel);
            tablemodel.getDataVector().removeAllElements();
            ResultSet rs = Koneksi.statement.executeQuery("SELECT*FROM buku LEFT JOIN transaksi ON buku.id_buku = transaksi.id_buku"); //call join
            while(rs.next()){
                Object[] data = {
                    rs.getString("id_buku"),
                    rs.getString("judul_buku"),
                    rs.getString("pengarang"),
                    rs.getString("penerbit"),
                    rs.getString("tahun_terbit"),
                    rs.getString("stok"),
                    rs.getString("harga"),
                    rs.getString("id_transaksi"),
                    rs.getString("transaksi.id_buku"),
                    rs.getString("waktu_transaksi"),
                    rs.getString("jumlah_beli"),
                    rs.getString("jumlah_transaksi")
                };
                tablemodel.addRow(data);
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
    
    private void RataRataPenjualanBuku(){
        try {
            Object[] judul_kolom = {"rata_rata_penjualan","judul_buku","pengarang"};
            DefaultTableModel tablemodel = new DefaultTableModel(null, judul_kolom);
            Report_table.setModel(tablemodel);
            tablemodel.getDataVector().removeAllElements();
            ResultSet rs;
            CallableStatement stmt = Koneksi.koneksi.prepareCall("{ call rataBarang(?) }");
            stmt.setString(1, jdl.getText());
            rs = stmt.executeQuery();
            while(rs.next()){
                Object[] data = {
                    rs.getString("rata_rata_penjualan"),
                    rs.getString("judul_buku"),
                    rs.getString("pengarang")
                };
                tablemodel.addRow(data);
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
    
    private void JumlahPenjualanPerHari(){
        try {
            CallableStatement CS = Koneksi.koneksi.prepareCall("{?=call total_jual(?)}");
            CS.registerOutParameter(1, Types.INTEGER);
            CS.setString(2, new SimpleDateFormat("yyyy-MM-dd").format(tgl.getDate()));
            CS.execute();
            int rest = CS.getInt(1);
            JOptionPane.showMessageDialog(rootPane, "Jumlah buku yang terjual pada tanggal "+tgl.getDate()+" adalah "+ rest + " buku");
        } catch (Exception e) {
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
        leftjoinButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Report_table = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jdl = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tgl = new com.toedter.calendar.JDateChooser();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel1.setText("Table Laporan");

        leftjoinButton.setText("Buku dan Transaksi");
        leftjoinButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leftjoinButtonActionPerformed(evt);
            }
        });

        Report_table.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(Report_table);

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 0, 36)); // NOI18N
        jLabel2.setText("Laporan Transaksi");

        jdl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jdlActionPerformed(evt);
            }
        });

        jLabel3.setText("Masukkan nama buku :");

        jLabel4.setText("Rata-rata penjualan buku tertentu ");

        jButton1.setText("Transaksi");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Buku");
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

        jLabel5.setText("Jumlah Penjualan Pada hari tertentu ");

        jLabel6.setText("Masukkan tanggal(yyyy-MM-dd) :");

        jButton4.setText("Hitung");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(568, 568, 568))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(112, 112, 112))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(leftjoinButton)
                                .addGap(122, 122, 122)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tgl, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(128, 128, 128)
                                .addComponent(jLabel4))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jdl, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(103, 103, 103))
            .addGroup(layout.createSequentialGroup()
                .addGap(458, 458, 458)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jdl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(32, 32, 32))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)))
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(leftjoinButton)
                            .addComponent(tgl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jButton4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void leftjoinButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leftjoinButtonActionPerformed
        // TODO add your handling code here:
        LeftJoin();
    }//GEN-LAST:event_leftjoinButtonActionPerformed

    private void jdlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jdlActionPerformed
        // TODO add your handling code here:
        RataRataPenjualanBuku();
    }//GEN-LAST:event_jdlActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Buku d = new Buku();
        d.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Transaksi e = new Transaksi();
        e.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        Beranda g = new Beranda();
        g.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        JumlahPenjualanPerHari();
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(transaction_Report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(transaction_Report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(transaction_Report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(transaction_Report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new transaction_Report().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Report_table;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jdl;
    private javax.swing.JButton leftjoinButton;
    private com.toedter.calendar.JDateChooser tgl;
    // End of variables declaration//GEN-END:variables
}
