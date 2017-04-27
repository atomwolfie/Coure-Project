import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jeff on 3/27/17.
 */
public class Employee {
    private int m_id;
    private String m_firstName;
    private String m_lastName;
    private boolean m_isManager;
    private String m_picFilePath;
    private String m_password;

    Employee(int employeeId) {
        ResultSet rs = DBConnection.dbSelectAllFromTableWhere("employees", "employeeid=" + employeeId);
        try {
            if (rs.next()) {
                m_id = employeeId;
                m_firstName = rs.getString("employeefirstname");
                m_lastName = rs.getString("employeelastname");
                m_isManager = rs.getString("employeestatus").equals("Manager");
                m_picFilePath = System.getProperty("user.dir") + rs.getString("employeepicfilepath");
                m_password = rs.getString("employeepassword");
            }
            else {
                m_id = -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage loadEmployeePic() {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(m_picFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    public int getNumberOfEmployees() {
        return DBConnection.dbGetRecordCountForTable("employees");
    }

    public boolean isValidEmployee() {
        return (m_id >= 0);
    }

    public String getPicFilePath() {
        return m_picFilePath;
    }

    public void setPicFilePath(String newPicFilePath) {
        if (DBConnection.dbUpdateRecord("employees", "employeepicfilepath=\"" + newPicFilePath + "\"", "employeeid=" + m_id)) {
            this.m_picFilePath = newPicFilePath;
        }
    }

    public boolean getIsManager() {
        return m_isManager;
    }

    public void setIsManager(boolean isManager) {
        String status;
        if (isManager){
            status = "Manager";
            this.m_isManager = true;
        }
        else {
            status = "Cashier";
            this.m_isManager = false;
        }
        DBConnection.dbUpdateRecord("employees", "employeestatus=\"" + status + "\"", "employeeid=" + m_id);
    }

    public String getLastName() {
        return m_lastName;
    }

    public void setLastName(String newLastName) {
        if (DBConnection.dbUpdateRecord("employees", "employeelastname=\"" + newLastName + "\"", "employeeid=" + m_id)) {
            this.m_lastName = newLastName;
        }
    }

    public String getFirstName() {
        return m_firstName;
    }

    public void setFirstName(String newFirstName) {
        if (DBConnection.dbUpdateRecord("employees", "employeefirstname=\"" + newFirstName + "\"", "employeeid=" + m_id)) {
            this.m_firstName = newFirstName;
        }
    }

    public int getId() {
        return m_id;
    }

    public String getPassword() { return m_password; }
    public void setPassword(String newPass) {
        if (DBConnection.dbUpdateRecord("employees", "employeepassword=\"" + newPass +"\"", "employeeid=" + m_id)) {
            this.m_password = newPass;
        }
    }

    public String toString() {
        if (m_isManager) {
            return m_id + "  " + m_firstName + "  " + m_lastName + "  MANAGER  " + m_picFilePath;
        }
        else {
            return m_id + "  " + m_firstName + "  " + m_lastName + "  CASHIER  " + m_picFilePath;
        }
    }
}
