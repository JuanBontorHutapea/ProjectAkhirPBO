/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Asus
 */
public class Model {

    String DBurl = "jdbc:mysql://localhost/hotel";
    String DBusername = "root";
    String DBpassword = "";
    Connection koneksi;
    Statement statement;

    public Model() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            koneksi = (Connection) DriverManager.getConnection(DBurl, DBusername, DBpassword);
            System.out.println("Koneksi Berhasil");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Koneksi Gagal");
            System.out.println(ex);
        }
    }

    public String[][] getAdminByUsername(String username) {
        String data[][];
        data = new String[getBanyakDataAdmin()][3];
        int jmlData = 0;
        try {
            String query = "SELECT * FROM admin Where username='" + username + "'";
            statement = koneksi.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) { // konversi tabel ke string
                data[jmlData][0] = resultSet.getString("id");
                data[jmlData][1] = resultSet.getString("username");
                data[jmlData][2] = resultSet.getString("password");
                jmlData++;
            }
            return data;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Execute Failed");
            return null;
        }
    }

    public boolean passwordMatch(String password, String passwordDB) throws NoSuchAlgorithmException {
        return password.equals(passwordDB);
    }

    public String[][] getDataPengunjung() {
        String[][] data;
        data = new String[getBanyakDataPengunjung()][6];
        int jmlData = 0;
        try {
            String query = "SELECT * FROM pengunjung";
            statement = koneksi.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) { // konversi tabel ke string
                data[jmlData][0] = resultSet.getString("id");
                data[jmlData][1] = resultSet.getString("nama_pengunjung");
                data[jmlData][2] = resultSet.getString("no_kamar");
                data[jmlData][3] = resultSet.getString("tanggal_masuk");
                data[jmlData][4] = resultSet.getString("tanggal_keluar");
                data[jmlData][5] = resultSet.getString("harga");
                jmlData++;
            }
            return data;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Execute Failed");
            return null;
        }
    }

    public int getBanyakDataAdmin() {
        int jmlData = 0;
        try {
            statement = koneksi.createStatement();
            String query = "Select * from admin";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                jmlData++;
            }
            return jmlData;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
            return 0;
        }
    }

    public int getBanyakDataPengunjung() {
        int jmlData = 0;
        try {
            statement = koneksi.createStatement();
            String query = "Select * from pengunjung";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                jmlData++;
            }
            return jmlData;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
            return 0;
        }
    }

    public void insertPengunjung(String nama_pengunjung, String no_kamar, String tanggal_keluar, String harga) {
        int jmlData = 0;
        try {
            String query = "Select * from pengunjung WHERE nama_pengunjung = '" + nama_pengunjung + "' ";
            System.out.println(nama_pengunjung + " " + no_kamar + " " + tanggal_keluar + " " + harga);
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                jmlData++;
            }

            if (jmlData == 0) {
                query = "INSERT INTO pengunjung (nama_pengunjung,no_kamar,tanggal_keluar,harga) " + "VALUES('" + nama_pengunjung + "','" + no_kamar + "','" + tanggal_keluar + "','" + harga + "')";

                statement = (Statement) koneksi.createStatement();
                statement.executeUpdate(query);
                System.out.println("Berhasil ditambahkan");
                JOptionPane.showMessageDialog(null, "Data Berhasil ditambahkan");
            } else {
                JOptionPane.showMessageDialog(null, "Data sudah ada");
            }
        } catch (HeadlessException | SQLException sql) {
            System.out.println(sql.getMessage());
            JOptionPane.showMessageDialog(null, sql.getMessage());
        }

    }
    
    public void updatePengunjung(String nama_pengunjung, String no_kamar, String tanggal_keluar, String harga) {
        int jmlData = 0;
        try {
            String query = "Select * from pengunjung WHERE nama_pengunjung= '" + nama_pengunjung + "' ";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                jmlData++;
            }

            if (jmlData == 1) {
                query = "UPDATE pengunjung SET no_kamar='" + no_kamar + "', tanggal_keluar='" + tanggal_keluar + "', harga='" + harga + "' WHERE nama_pengunjung='" + nama_pengunjung + "'";
                statement = (Statement) koneksi.createStatement();
                statement.executeUpdate(query);
                System.out.println("Berhasil diupdate");
                JOptionPane.showMessageDialog(null, "Data Berhasil diupdate");
            } else {
                JOptionPane.showMessageDialog(null, "Data Tidak Ada");
            }

        } catch (HeadlessException | SQLException sql) {
            System.out.println(sql.getMessage());
            JOptionPane.showMessageDialog(null, sql.getMessage());
        }
    }
    
    public void deletePengunjung(String nama_pengunjung) {
        try {
            String query = "DELETE FROM pengunjung WHERE nama_pengunjung = '" + nama_pengunjung + "'";
            statement = koneksi.createStatement();
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Berhasil Dihapus");

        } catch (SQLException sql) {
            System.out.println(sql.getMessage());
        }
    }

}
