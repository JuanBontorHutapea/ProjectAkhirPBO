/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.*;
import java.awt.event.*;
import java.security.NoSuchAlgorithmException;
import java.util.logging.*;

/**
 *
 * @author Asus
 */
public class LoginForm extends JFrame implements ActionListener {
    Model mod = new Model();
    //Component Declaration

    public JLabel lHeader = new JLabel("Halaman Login");
    public JLabel lUname = new JLabel("Username ");
    public JLabel lPass = new JLabel("Password ");

    public JTextField tfUname = new JTextField();
    public JTextField tfPass = new JTextField();

    public JButton btnLogin = new JButton("Login");
    public JButton btnCancel = new JButton("Cancel");

    public LoginForm() {
        //Set Window
        setTitle("Login");
        setLayout(null);
        setSize(350, 230);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);

        //Add Component & Placement
        add(lHeader);
        add(lUname);
        add(lPass);

        add(tfUname);
        add(tfPass);

        add(btnLogin);
        add(btnCancel);

        //Atur Tata Letak
        lHeader.setBounds(110, 12, 120, 20);
        lUname.setBounds(5, 45, 120, 20);
        lPass.setBounds(5, 70, 120, 20);

        tfUname.setBounds(85, 45, 125, 20);
        tfPass.setBounds(85, 70, 125, 20);

        btnLogin.setBounds(65, 110, 80, 20);
        btnCancel.setBounds(175, 110, 80, 20);
        
        btnLogin.addActionListener((ActionEvent arg0) -> {
            String data[][] = new String[1][3];
            data = mod.getAdminByUsername(getUname()); // mengambil data dari database
            
            if (data[0][0] == null) { // mengecek ada atau tidak username tersebut di database
                // jika tidak ada maka tampilkan pesan kesalahan
                JOptionPane.showMessageDialog(null, "Username Anda tidak terdaftar dalam aplikasi kami");
                JOptionPane.showMessageDialog(null, "Mohon cek kembali username Anda");
            } else { // jika ada maka kerjakan perintah berikut
                String password = getPass(); // mengambil inputan password user
                String passwordDB = data[0][2]; // mengambil password yang didapat dari query getUserByUsername
                try {
                    if (mod.passwordMatch(password, passwordDB)) { // mengecek password inputan dengan password di
                        // database
                        // lebih jelasnya bisa lihat method
                        // passwordMatch di model users
                        JOptionPane.showMessageDialog(null, "Login Berhasil");
                        View view = new View();
                        Controller cont = new Controller(mod, view);
                        setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Login gagal, password Anda salah");
                        JOptionPane.showMessageDialog(null, "Tolong masukkan password Anda dengan benar");
                    }
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });

    }

    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancel) {
            System.exit(0);
        }
    }

    public String getUname() {
        return tfUname.getText();
    }

    public String getPass() {
        return tfPass.getText();
    }
}
