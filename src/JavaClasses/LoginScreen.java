import sun.rmi.runtime.Log;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by jeff on 3/25/17.
 */
public class LoginScreen {

    private JFrame frame;

    private ArrayList<Employee> employeeList;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {


        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginScreen window = new LoginScreen();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    LoginScreen() {
        employeeList = new ArrayList<>();
        ResultSet rs = DBConnection.dbGetColumnDataFromTable("employees", "employeeid");
        try {
            while(rs.next()) {
                employeeList.add(new Employee(rs.getInt("employeeid")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        frame = new JFrame();
        frame.setBounds(400, 100, 900, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JList list = new JList(employeeList.toArray());
        list.setBounds(50,50,800,500);
        frame.getContentPane().add(list);

    }
}
