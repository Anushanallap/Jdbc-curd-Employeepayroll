import employeejdbc.EmployeeDataBase;
import employeejdbc.EmployeePayrollData;
import employeejdbc.EmployeePayrollFileIOService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class EmployeePayrollService {
    public enum IOService {
        CONSOLE_IO, FILE_IO, DB_IO, REST_IO
    }

    private List<EmployeePayrollData> employeePayrollList;


    public EmployeePayrollService() {
        Object employeePayrollDataBase = EmployeeDataBase.getInstance();
    }

    public EmployeePayrollService(List<EmployeePayrollData> employeePayrollList) {
        this();
        this.employeePayrollList = employeePayrollList;
    }

    public static void main(String[] args) {
        ArrayList<EmployeePayrollData> employeePayrollList = new ArrayList<>();
        EmployeePayrollService employeePayrollService = new EmployeePayrollService(employeePayrollList);
        Scanner consoleInputReader = new Scanner(System.in);
        employeePayrollService.readEmployeePayrollData(consoleInputReader);
        employeePayrollService.writeEmployeePayrollData(IOService.CONSOLE_IO);
    }

    private void readEmployeePayrollData(Scanner consoleInputReader) {
        System.out.println("Enter Employee ID: ");
        int id = consoleInputReader.nextInt();
        System.out.println("Enter Employee Name: ");
        String name = consoleInputReader.next();
        System.out.println("Enter Employee Salary: ");
        double salary = consoleInputReader.nextDouble();
        employeePayrollList.add(new EmployeePayrollData(id, name, salary));
    }

    public Map<String, Double> readAverageSalaryByGender(IOService ioService) {
        if(ioService.equals(IOService.DB_IO)){
            return EmployeeDataBase.getAverageSalaryByGender();
        }
        return null;
    }


    public List<EmployeePayrollData> readEmployeePayrollData(IOService ioService) {
        if(ioService.equals(IOService.DB_IO))
            this.employeePayrollList = EmployeeDataBase.readData();
        return this.employeePayrollList;
    }

    public List<EmployeePayrollData> readEmployeePayrollForDateRange(IOService ioService, LocalDate startDate, LocalDate endDate) {
        if(ioService.equals(IOService.DB_IO))
            return EmployeeDataBase.getEmployeeForDateRange(startDate, endDate);
        return null;
    }

    public boolean checkEmployeePayrollInSyncWithDB(String name) {
        List<EmployeePayrollData> employeePayrollDataList = EmployeeDataBase.getEmployeePayrollData(name);
        return employeePayrollDataList.get(0).equals(getEmployeePayrollData(name));

    }

    public void updateEmployeeSalary(String name, double salary) {
        int result = EmployeeDataBase.updateEmployeeData(name, salary);
        if (result == 0) return;
        EmployeePayrollData employeePayrollData = this.getEmployeePayrollData(name);
        if(employeePayrollData != null) employeePayrollData.salary = salary;

    }

    private EmployeePayrollData getEmployeePayrollData(String name) {
        return this.employeePayrollList.stream()
                .filter(employeePayrollDataItem -> employeePayrollDataItem.employeename.equals(name))
                .findFirst()
                .orElse(null);
    }

    public void writeEmployeePayrollData(IOService ioService) {
        if (ioService.equals(IOService.CONSOLE_IO))
            System.out.println("\n Writing Employee Payroll Roaster to Console \n" + employeePayrollList);
        else if (ioService.equals(IOService.FILE_IO))
            new EmployeePayrollFileIOService().writeData(employeePayrollList);
    }

    public void printData(IOService ioService) {
        if (ioService.equals(IOService.FILE_IO))
            new EmployeePayrollFileIOService().printData();
    }

    public long countEntries(IOService ioService) {
        if (ioService.equals(IOService.FILE_IO))
            new EmployeePayrollFileIOService().countEntries();
        return 0;
    }
}
