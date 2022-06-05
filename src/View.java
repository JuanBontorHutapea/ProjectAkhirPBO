/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Asus
 */
public class View extends JFrame implements ActionListener{
    //Component Declaration
        //Static Text
    public JLabel lNama_Pengunjung = new JLabel("Nama Pengunjung");
    public JLabel lNo_Kamar = new JLabel("Nomor Kamar");
    public JLabel lTanggal_Keluar = new JLabel("Tanggal Keluar");
    public JLabel lHarga = new JLabel("Harga");
    
        //Kolom Input
    public JTextField tfNama_Pengunjung = new JTextField();
    public JTextField tfNo_Kamar = new JTextField();
    public JTextField tfTanggal_Keluar = new JTextField();
    public JTextField tfHarga = new JTextField();
    
        //Button
    public JButton btnLogin = new JButton("Login");
    public JButton btnCancel = new JButton("Cancel");
    public JButton btnLihat = new JButton("Lihat Pengunjung");
    public JButton btnTambah = new JButton("Tambah");
    public JButton btnUpdate = new JButton("Update");
    public JButton btnDelete = new JButton("Delete");
    public JButton btnReset = new JButton("Clear");
    public JButton btnRefresh = new JButton("Refresh");
    
    public JTable tabel;
    DefaultTableModel dtm;
    JScrollPane scrollPane;
    public Object namaKolom[] = {"ID", "Nama Pengunjung", "Nomor Kamar", "Tanggal Masuk", "Tanggal Keluar", "Harga"};
    
    public View() {
        dtm = new DefaultTableModel(namaKolom, 0);
        tabel = new JTable(dtm);
        scrollPane = new JScrollPane(tabel);
        
        //Set Window
        setTitle("Daftar Pengunjung Hotel");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLayout(null);
        setSize(850, 485);
        setLocationRelativeTo(null);
        
        //Add Component & Placement
        add(scrollPane);
        
        add(lNama_Pengunjung);
        add(lNo_Kamar);
        add(lTanggal_Keluar);
        add(lHarga);
        
        add(tfNama_Pengunjung);
        add(tfNo_Kamar);
        add(tfTanggal_Keluar);
        add(tfHarga);
        
        add(btnLihat);
        add(btnTambah);
        add(btnUpdate);
        add(btnDelete);
        add(btnReset);
        add(btnRefresh);
        
        //Atur Tata Letak
        scrollPane.setBounds(300, 20, 480, 300);
        lNama_Pengunjung.setBounds(20, 20, 120, 20);
        tfNama_Pengunjung.setBounds(150, 20, 100, 20);
        lNo_Kamar.setBounds(20, 50, 120, 20);
        tfNo_Kamar.setBounds(150, 50, 100, 20);
        lTanggal_Keluar.setBounds(20, 80, 120, 20);
        tfTanggal_Keluar.setBounds(150, 80, 100, 20);
        lHarga.setBounds(20, 110, 120, 20);
        tfHarga.setBounds(150, 110, 100, 20);
        btnTambah.setBounds(40, 230, 120, 20);
        btnUpdate.setBounds(40, 260, 120, 20);
        btnDelete.setBounds(40, 290, 120, 20);
        btnReset.setBounds(40, 320, 120, 20);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnReset) {
            tfNama_Pengunjung.setText("");
            tfNo_Kamar.setText("");
            tfTanggal_Keluar.setText("");
            tfHarga.setText("");
        }
    }
    
    public String getNamaPengunjung() {
        return tfNama_Pengunjung.getText();
    }

    public String getNomorKamar() {
        return tfNo_Kamar.getText();
    }

    public String getTanggalKeluar() {
        return tfTanggal_Keluar.getText();
    }

    public String getHarga() {
        return tfHarga.getText();
    }
    
}
