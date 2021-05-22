/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokobuku;

import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author ASUS
 */
public class Koneksi {
     public Connection koneksi;
    public Statement statement;
    public Koneksi(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            System.err.println("JDBC Driver tidak ditemukan : "+e.getMessage());
        }
        koneksi = null;
        try{
            koneksi = DriverManager.getConnection("jdbc:mysql://localhost/toko_buku","root","");
            statement = koneksi.createStatement();
        } catch (Exception e) {
            System.err.println("Koneksi DB Error : "+e.getMessage());
        }
        if(koneksi!=null){
            System.out.println("Koneksi DB berhasil");
        }
    }
    
}
