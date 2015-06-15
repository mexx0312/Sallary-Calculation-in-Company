package Model;

import java.util.Map;

/**
 * Created by Yevhenii on 5/6/15.
 */
public class SallaryCalculation {
    private Department departmentEmpl;
    private int restBalance;
    private Employee empl;
    boolean checkToChange = true;
    private int totalMoneyForDepartment;

    public SallaryCalculation(Department departmentEmpl){
        this.departmentEmpl = departmentEmpl;
    }

    public Department getDepartmentEmpl() {
        return departmentEmpl;
    }

    public void setDepartmentEmpl(Department departmentEmpl) {
        this.departmentEmpl = departmentEmpl;
    }

    public Employee getEmpl() {
        return empl;
    }

    public void setEmpl(Employee empl) {
        this.empl = empl;
    }

    protected void isMoneyEnuf(){
        int totalSalery=0;
        for (Map.Entry entry : departmentEmpl.getListEmployee().entrySet()){
            totalSalery  += ((Employee)entry.getValue()).getSallery();
        }
        if (totalSalery > departmentEmpl.getMoneyForDepartment()){
            System.out.println("Salary of all employee is more than salary for all department!");
        }

    }

    protected boolean isMoneyEnufBoll(){
        int totalSalery=0;
        for (Map.Entry entry : departmentEmpl.getListEmployee().entrySet()){
            totalSalery  += ((Employee)entry.getValue()).getSallery();
        }
        if (totalSalery > departmentEmpl.getMoneyForDepartment()){
            return false;
        }
        return true;
    }

    protected int calculateRestBalance(){
        int totSalery=0;
        for (Map.Entry entry : departmentEmpl.getListEmployee().entrySet()){
            totSalery  += ((Employee)entry.getValue()).getSallery();
        }
        if (isMoneyEnufBoll()) {
            restBalance = departmentEmpl.getMoneyForDepartment() - totSalery;
        }
        return restBalance;
    }

    protected double calculateDirectly(){
        int numberOfPeopleInList = 0;
        double premiusForPerson;

        for (Map.Entry entry: departmentEmpl.getListEmployee().entrySet()){
            numberOfPeopleInList++;
        }
        return premiusForPerson = calculateRestBalance()/numberOfPeopleInList;
    }

    protected double calculateProportionally(){
        double currentEmplRestBal;
        int totalSalery = 0;

        if (checkToChange) {
            totalMoneyForDepartment = departmentEmpl.getMoneyForDepartment();

        }
        checkToChange = false;
        for (int i = 0; i < departmentEmpl.getListEmployee().size(); i++){
            totalSalery  += empl.getSallery();
        }
        currentEmplRestBal = ((double)empl.getSallery() / (double)totalMoneyForDepartment)
                * (departmentEmpl.getMoneyForDepartment() - totalSalery);

        return currentEmplRestBal;
    }

    protected double calculateTotalsalery(){
        return  empl.getSallery() + empl.getRestBalance();
    }
}
