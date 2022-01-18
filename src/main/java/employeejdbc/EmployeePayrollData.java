package employeejdbc;

import java.time.LocalDate;

public class EmployeePayrollData {
    public int employeeid;
    public String employeename;
    public double employeesalary;
    public LocalDate startDate;
    public double salary;

    public EmployeePayrollData(Integer id, String name, Double salary, LocalDate date) {

        this.employeeid = id;
        this.employeename = name;
        this.employeesalary = salary;
        this.startDate = date;
    }

    public EmployeePayrollData(int id, String s, double v) {

    }

    public String toString() {
        String employeeId = null;
        String employeeName = null;
        String employeeSalary = null;
        return "EmployeePayrollData{" +
                "employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", employeeSalary=" + employeeSalary +
                ", startDate=" + startDate +
                '}';
    }

}
