/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.*;
import java.awt.event.*;

/**
 *
 * @author Asus
 */
public class Controller {

    Model mod;
    View view;
    LoginForm lf;

    public String dataSelect;

    public Controller(Model mod, View view) {
        this.mod = mod;
        this.view = view;
        this.lf = lf;

        if (mod.getBanyakDataPengunjung() != 0) {
            String dataPengunjung[][] = mod.getDataPengunjung();
            view.tabel.setModel((new JTable(dataPengunjung, view.namaKolom)).getModel());
        } else {
            JOptionPane.showMessageDialog(null, "Data Masih Belum Terisi");
        }

        view.btnTambah.addActionListener((ActionEvent ae) -> {
            String namaPengunjung = view.getNamaPengunjung();
            String nomorKamar = view.getNomorKamar();
            String tanggalKeluar = view.getTanggalKeluar();
            String harga = view.getHarga();
            mod.insertPengunjung(namaPengunjung, nomorKamar, tanggalKeluar, harga);

            String dataPengunjung[][] = mod.getDataPengunjung();
            view.tabel.setModel((new JTable(dataPengunjung, view.namaKolom)).getModel());
        });

        view.btnUpdate.addActionListener((ActionEvent ae) -> {    
            String namaPengunjung = view.getNamaPengunjung();
            String nomorKamar = view.getNomorKamar();
            String tanggalKeluar = view.getTanggalKeluar();
            String harga = view.getHarga();
            mod.updatePengunjung(namaPengunjung, nomorKamar, tanggalKeluar, harga);

            String dataPengunjung[][] = mod.getDataPengunjung();
            view.tabel.setModel((new JTable(dataPengunjung, view.namaKolom)).getModel());
            
        });

        view.tabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mousePressed(e);
                int baris = view.tabel.getSelectedRow();

                dataSelect = view.tabel.getValueAt(baris, 0).toString();
                System.out.println(dataSelect);

            }

        });

        view.btnDelete.addActionListener((ActionEvent ae) -> {
            int input = JOptionPane.showConfirmDialog(null,
                    "Apa anda ingin menghapus Data Pengunjung " + dataSelect + "?", "Pilih Opsi...", JOptionPane.YES_NO_OPTION);

            if (input == 0) {
                mod.deletePengunjung(dataSelect);
                String dataPengunjung[][] = mod.getDataPengunjung();
                view.tabel.setModel((new JTable(dataPengunjung, view.namaKolom)).getModel());
            } else {
                JOptionPane.showMessageDialog(null, "Tidak Jadi Dihapus");
            }
        });
    }

}
