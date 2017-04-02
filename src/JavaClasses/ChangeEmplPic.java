import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * Created by admin on 3/31/17.
 */
public class ChangeEmplPic {

    private JFrame frame;
    private JLabel imageLabel;
    private Employee curEmployee;
    private Path curPicPath;
    private Path destinationPicPath;

    ChangeEmplPic(Employee empl) {
        this.curEmployee = empl;

        frame = new JFrame();
        frame.setBounds(600, 200, 525, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        // Load and display employee picture
        int labelWidth = 225;
        int labelHeight = 300;
        ImageIcon image = new ImageIcon(curEmployee.loadEmployeePic().getScaledInstance(labelWidth, labelHeight,
                Image.SCALE_SMOOTH));
        imageLabel = new JLabel(image);
        imageLabel.setBounds(150,25,labelWidth,labelHeight);
        frame.getContentPane().add(imageLabel);
        imageLabel.setVisible(true);

        JButton btnFilePicker = new JButton("Pick New Picture");
        btnFilePicker.setBounds(170,335,175,30);
        btnFilePicker.setToolTipText(".jpg pictures only");
        frame.getContentPane().add(btnFilePicker);

        JLabel btnFileType = new JLabel(".jpg only");
        btnFileType.setBounds(225,357,170,30);
        frame.getContentPane().add(btnFileType);

        JButton btnSave = new JButton("Save");
        btnSave.setBounds(90,400,150,50);
        btnSave.setEnabled(false);
        frame.getContentPane().add(btnSave);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBounds(290,400,150,50);
        frame.getContentPane().add(btnCancel);

        ActionListener buttonListener = new ActionListener() {

            //we have to define this method in order for an Action Listener to work
            public void actionPerformed(ActionEvent e) { //'e' is the Action Event which is a button being clicked in our case

                if (e.getSource() == btnCancel) {
                    this.setVisible(false);
                    MainScreen main = new MainScreen(curEmployee);
                    main.setVisible(true);
                    frame.dispose();
                }
                else if (e.getSource() == btnFilePicker) {
                    FileDialog fd = new FileDialog(frame, "Choose Pic", FileDialog.LOAD);
                    fd.setVisible(true);

                    if ((fd.getFile().substring(fd.getFile().lastIndexOf(".") + 1).trim()).equals("jpg")) {
                        try {
                            BufferedImage img = ImageIO.read(new File(fd.getDirectory() + fd.getFile()));
                            ImageIcon newImage = new ImageIcon(img.getScaledInstance(labelWidth, labelHeight,
                                    Image.SCALE_SMOOTH));
                            imageLabel.setIcon(newImage);
                            curPicPath = Paths.get(fd.getDirectory() + fd.getFile());
                            btnSave.setBackground(new Color(95, 186, 125));
                            btnSave.setEnabled(true);
                        } catch (IOException err) {
                            err.printStackTrace();
                        }
                    }
                }
                else if (e.getSource() == btnSave) {

                    System.out.println(curPicPath);
                    destinationPicPath = Paths.get(curEmployee.getPicFilePath());
                    System.out.println(destinationPicPath);
                    try {
                        Files.copy(curPicPath, destinationPicPath, REPLACE_EXISTING);
                    }
                    catch (FileNotFoundException ex){
                        System.out.println("File not found: " + ex);
                    }
                    catch (IOException ex){
                        ex.printStackTrace();
                    }

                    this.setVisible(false);
                    MainScreen main = new MainScreen(curEmployee);
                    main.setVisible(true);
                    frame.dispose();
                }
            }

            private void setVisible(boolean b) {
                frame.setVisible(b);
            }
        };

        btnCancel.addActionListener(buttonListener);
        btnSave.addActionListener(buttonListener);
        btnFilePicker.addActionListener(buttonListener);
    }

    public void setVisible(boolean b) { this.frame.setVisible(b); }
}
