package hrlovecraft;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Employee extends Person {
    private final long employeeId;
    private Department department;
    private LocalDateTime hireDate;
    private LocalDateTime terminationDate;
    private SalaryTier salaryTier;
    private Benefits benefits;

    private Position position;
    private TimeCardWarehouse TCW;
    private PaystubWarehouse PSW;

    public Employee(long employeeId, String name, String addr, String city, String state, String phone, String email) {
        super(name, addr, city, state, phone, email);
        this.employeeId = employeeId;
        this.TCW = new TimeCardWarehouse();
        this.PSW = new PaystubWarehouse();
        hireDate = LocalDateTime.now();
        terminationDate = null;
        salaryTier=SalaryTier.LOW;
        benefits=new Benefits(salaryTier);
        department=Department.New_Hire;
        position=Position.New_Hire;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public LocalDateTime getHireDate() {

        return hireDate;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department dept) {
        department = dept;
    }



    public void setSalaryTier(SalaryTier salaryTier){
        this.salaryTier=salaryTier;
        setBenefitsBySalaryTier(salaryTier);
    }

    public ArrayList<TimeCard> getTimeCards(String fromDate, String toDate) {
        return TCW.get(fromDate, toDate);
    }

    public TimeCard getTimeCard(String fromDate) {
        return TCW.get(fromDate);
    }

    public PaystubWarehouse getPSW() {
        return PSW;
    }


    public Paystub getPaystub(String fromDate) {
        return PSW.get(fromDate);
    }


    public ArrayList<Paystub> getPaystubs(String fromDate, String toDate) {
        return PSW.get(fromDate, toDate);
    }

    public void setBenefitsBySalaryTier(SalaryTier salaryTier) {
        benefits.setBenefitPackageBySalaryTier(salaryTier);
    }

    public Benefits getBenefits() {
        return benefits;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void submitTimeCard(TimeCard timeCard) {
        TCW.add(timeCard);
    }

    public Position getPosition() {
        return position;
    }

    public SalaryTier getSalaryTier() {
        return salaryTier;
    }

    public String[] getAllPaystubRecords() {

        ArrayList<Paystub> paystubs = this.PSW.getList();
        String[] paystubList = new String[this.PSW.getSize()];

        for (int i = 0; i < paystubList.length; i++) {
            Paystub paystub = paystubs.get(i);
            String line = paystub.toString();
            paystubList[i] = line;
        }
        return paystubList;
    }
}
