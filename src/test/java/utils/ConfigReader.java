package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    Properties prop;

    public ConfigReader() {
        try {
            String path = System.getProperty("user.dir") + "/src/test/resources/config.properties";
            FileInputStream fis = new FileInputStream(path);
            prop = new Properties();
            prop.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getBrowser() {
        return prop.getProperty("browser");
    }

    public String getUrl() {
        return prop.getProperty("baseUrl");
    }

    public int getTimeout() {
        return Integer.parseInt(prop.getProperty("timeout"));
    }
    
    public String getAdminUsername() {
        return prop.getProperty("admin.username");
    }

    public String getAdminPassword() {
        return prop.getProperty("admin.password");
    }

    public String getEmployeeFirstName() {
        return prop.getProperty("employee.firstname");
    }

    public String getEmployeeMiddleName() {
        return prop.getProperty("employee.middlename");
    }
    
    public String getEmployeeLastName() {
        return prop.getProperty("employee.lastname");
    }

    public String getInvalidEmployee() {
        return prop.getProperty("invalid.employee");
    }
    
 // 🔥 Leave module configs

    public String getLeaveType() {
        return prop.getProperty("leave.type");
    }

    public String getLeaveFromDate() {
        return prop.getProperty("leave.fromDate");
    }

    public String getLeaveToDate() {
        return prop.getProperty("leave.toDate");
    }

    public String getPastDate() {
        return prop.getProperty("leave.pastDate");
    }
    
    public String getUserRole() {
        return prop.getProperty("user.role");
    }

    public String getUserStatus() {
        return prop.getProperty("user.status");
    }

    public String getUserPassword() {
        return prop.getProperty("user.password");
    }

    public String getEmployeeName() {
        return prop.getProperty("employee.name");
    }
}