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
import java.text.SimpleDateFormat;  
import java.util.Date;
/**
 *
 * @author ASUS
 */
public class Transaksi extends javax.swing.JFrame {
    int beli, harga, total;
    private Koneksi Koneksi = new Koneksi();
    /**
     * Creates new form Transaksi
     */
    public Transaksi() {
        initComponents();
        initDataTransaksi("select * from transaksi");
    }
    
    public void reset(){
        field_id_buku.setText("");
        field_id_transaksi.setText("");
        field_waktu_transaksi.setDate(null);
        field_jumlah_beli.setText("");
        field_jumlah_transaksi.setText("");
    }
    
    private void initDataTransaksi(String sql){
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("id_transaksi");
        tableModel.addColumn("id_buku");
        tableModel.addColumn("waktu_transaksi");
        tableModel.addColumn("jumlah_beli");
        tableModel.addColumn("jumlah_transaksi");
        
        try{
            ResultSet resultSet = Koneksi.statement.executeQuery(sql);
            while (resultSet.next()){
                    tableModel.addRow(new Object[]{
                        resultSet.getString("id_transaksi"),
                        resultSet.getString("id_buku"),
                        resultSet.getString("waktu_transaksi"),
                        resultSet.getString("jumlah_beli"),
                        resultSet.getString("jumlah_transaksi"),
                    });
            }
            table_transaksi.setModel(tableModel);  
        }catch (SQLException e){
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }
    
    private void simpanDataTransaksi(){
        String sql = "INSERT INTO transaksi (id_transaksi, id_buku, waktu_transaksi, jumlah_beli, jumlah_transaksi)" + "VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = Koneksi.koneksi.prepareStatement(sql);
            ps.setString(1, field_id_transaksi.getText());
            ps.setString(2, field_id_buku.getText());
            ps.setString(3, new SimpleDateFormat("yyyy-MM-dd").format(field_waktu_transaksi.getDate()));
            ps.setString(4, field_jumlah_beli.getText());
            ps.setString(5, field_jumlah_transaksi.getText());
            ps.executeUpdate();
            reset();
            JOptionPane.showMessageDialog(rootPane, "Data tersimpan");
            initDataTransaksi("select * from transaksi");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(rootPane, "Data Gagal DIsimpan" + "\n Error"+e.getMessage());
        }
    }
    
     private void UpdateTransaksi(){
        String sql = "Update transaksi set id_buku=?, waktu_transaksi=?, jumlah_beli=?, jumlah_transaksi=?"+"WHERE id_transaksi=?";
        
        try {
            PreparedStatement ps = Koneksi.koneksi.prepareStatement(sql);
            ps.setString(5, field_id_transaksi.getText());
            ps.setString(1, field_id_buku.getText());
            ps.setString(2, new SimpleDateFormat("yyyy-MM-dd").format(field_waktu_transaksi.getDate()));
            ps.setString(3, field_jumlah_beli.getText());
            ps.setString(4, field_jumlah_transaksi.getText());
            System.out.println(ps.toString());
            ps.executeUpdate();
            reset();
            JOptionPane.showMessageDialog(rootPane, "Data tersimpan");
            initDataTransaksi("select * from transaksi");
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(rootPane, "Data Gagal diupdate" + "\n Error"+e.getMessage());
        }
    }
     
    private void HapusDataTransaksi(){
        String sql = "DELETE FROM transaksi WHERE id_transaksi = ?";
        try {
            PreparedStatement ps = Koneksi.koneksi.prepareStatement(sql);
            ps.setString(1, field_id_transaksi.getText());
            ps.executeUpdate();
            initDataTransaksi("select * from transaksi");
            reset();
            JOptionPane.showMessageDialog(rootPane, "Data terhapus");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(rootPane, "Data Gagal Dihapus" + "\n Error"+e.getMessage());
        }
    }
     
    private void cariTransaksi(String key) {
        try {
            Object[] judul_kolom = {"id_transaksi","id_buku","waktu_transaksi","jumlah_beli","jumlah_transaksi"};
            DefaultTableModel tablemodel = new DefaultTableModel(null, judul_kolom);
            table_transaksi.setModel(tablemodel);
            tablemodel.getDataVector().removeAllElements();
            ResultSet rs = Koneksi.statement.executeQuery("SELECT*FROM transaksi WHERE id_buku LIKE '%"+key+"%'");
            while(rs.next()){
                Object[] data = {
                    rs.getString("id_transaksi"),
                    rs.getString("id_buku"),
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
     

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        field_id_transaksi = new javax.swing.JTextField();
        field_id_buku = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        field_jumlah_beli = new javax.swing.JTextField();
        field_harga_satuan = new javax.swing.JTextField();
        field_waktu_transaksi = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_transaksi = new javax.swing.JTable();
        save_transaksi = new javax.swing.JButton();
        update_transaksi = new javax.swing.JButton();
        delete_transaksi = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        cari = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        field_jumlah_transaksi = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel1.setText("Transaksi");

        jLabel2.setText("Id Transaksi");

        jLabel3.setText("Id Buku");

        jLabel4.setText("Tanggal Transaksi");

        jLabel5.setText("Jumlah Beli");

        jLabel6.setText("Harga Satuan");

        field_harga_satuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                field_harga_satuanActionPerformed(evt);
            }
        });

        table_transaksi.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(table_transaksi);

        save_transaksi.setText("Save");
        save_transaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_transaksiActionPerformed(evt);
            }
        });

        update_transaksi.setText("Update");
        update_transaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_transaksiActionPerformed(evt);
            }
        });

        delete_transaksi.setText("Delete");
        delete_transaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_transaksiActionPerformed(evt);
            }
        });

        jLabel7.setText("Cari :");

        cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cariKeyReleased(evt);
            }
        });

        jLabel8.setText("Total Bayar");

        jButton1.setText("Laporan Transaksi");
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(305, 305, 305))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 697, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(field_id_transaksi, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                            .addComponent(field_id_buku)
                            .addComponent(field_waktu_transaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(save_transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(update_transaksi)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(delete_transaksi)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(field_jumlah_transaksi)
                                    .addComponent(field_jumlah_beli)
                                    .addComponent(field_harga_satuan)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(field_id_transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(field_jumlah_beli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(field_id_buku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field_harga_satuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel4)
                        .addComponent(field_waktu_transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(field_jumlah_transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(save_transaksi)
                    .addComponent(update_transaksi)
                    .addComponent(delete_transaksi))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    private void save_transaksiActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here:
        simpanDataTransaksi();
    }                                              

    private void update_transaksiActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        // TODO add your handling code here:
        UpdateTransaksi();
    }                                                

    private void field_harga_satuanActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        // TODO add your handling code here:
        beli = Integer.parseInt(field_jumlah_beli.getText());
        harga = Integer.parseInt(field_harga_satuan.getText());
        total = beli * harga;
        field_jumlah_transaksi.setText(String.valueOf(total));
    }                                                  

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        transaction_Report h = new transaction_Report();
        h.setVisible(true);
        this.setVisible(false);
    }                                        

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        Buku j = new Buku();
        j.setVisible(true);
        this.setVisible(false);
    }                                        

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        Beranda k = new Beranda();
        k.setVisible(true);
        this.setVisible(false);
    }                                        

    private void cariKeyReleased(java.awt.event.KeyEvent evt) {                                 
        // TODO add your handling code here:
        String key = cari.getText();
        System.out.print(key);
        
        if(key!=""){
            cariTransaksi(key);
        } else {
            initDataTransaksi("select * from transaksi");
        }
    }                                

    private void delete_transaksiActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        // TODO add your handling code here:
        HapusDataTransaksi();
    }                                                

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
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Transaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JTextField cari;
    private javax.swing.JButton delete_transaksi;
    private javax.swing.JTextField field_harga_satuan;
    private javax.swing.JTextField field_id_buku;
    private javax.swing.JTextField field_id_transaksi;
    private javax.swing.JTextField field_jumlah_beli;
    private javax.swing.JTextField field_jumlah_transaksi;
    private com.toedter.calendar.JDateChooser field_waktu_transaksi;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton save_transaksi;
    private javax.swing.JTable table_transaksi;
    private javax.swing.JButton update_transaksi;
    // End of variables declaration                   
}
