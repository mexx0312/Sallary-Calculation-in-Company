package Model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Yevhenii on 5/3/15.
 */
public class AutoFillDB {
    private Company company;
    private ArrayList<Department> departmentList;
    private Department[] departmentArray = null;
    private Employee[] employeeArray;
    PreparedQueries queries = new PreparedQueries();

    protected void createCompany(String organizationName, int departmentCount, String departmentKeyForName,
                                 int numberOfEmployee, String employeeKeyName, String employeeKeySurname){

        Company company = new Company(organizationName);

        double switchDepChecher = 0;
        int switcher = 0;
        departmentList = company.getListDepartments();

        departmentArray = new Department[departmentCount];
        employeeArray = new Employee[numberOfEmployee];

        switchDepChecher = employeeArray.length / departmentArray.length;

        employeeArray[0] = null;
        departmentArray[0] = null;

        for (int i = 0; i < departmentCount; i++){
            queries.insertNewDepartment(i, departmentKeyForName + i);
            departmentArray[i] = new Department(departmentKeyForName + i, 10000, i);
            departmentList.add(departmentArray[i]);
        }

        Map<String, Employee> employeeMap = departmentArray[0].getListEmployee();

        for (int i = 0; i < numberOfEmployee; i++){
            queries.insertEmployee(i, employeeKeyName + i, employeeKeySurname + i, switcher, 200 );
            employeeArray[i] = new Employee(i, employeeKeyName + i, employeeKeySurname + i, i );
            employeeMap.put(employeeKeyName + i, employeeArray[i]);

            departmentArray[switcher].setListEmployee(employeeMap);

            departmentArray[switcher].setListEmployee(employeeMap);
            if (Math.round(switchDepChecher) == i){
                switcher++;
            }
        }
//        System.out.println(company.toString());
    }

    public void printCompany(){
        for (int i = 0; i < departmentArray.length; i++){
            System.out.println("DEPARTMENT" + departmentArray[i].getDepartmentName());
            System.out.println(queries.getListOfEmloyeeByDepartmentID(i));
        }
    }

    protected void printSaleryWithBonusDirectly() throws SQLException {
        PreparedQueries queries1 = new PreparedQueries();
        SallaryCalculation calcSalery = new SallaryCalculation(departmentArray[0]);
        if (departmentArray.length > 0){
            for (int i = 0; i < employeeArray.length; i++){
                calcSalery.setEmpl(employeeArray[i]);
                employeeArray[i].setRestBalance(calcSalery.calculateDirectly());
                queries1.updateBonus(employeeArray[i]);
//                employeeArray[i].setTotalSalery(calcSalery.calculateTotalsalery());
            }
        }
        printCompany();
    }

}