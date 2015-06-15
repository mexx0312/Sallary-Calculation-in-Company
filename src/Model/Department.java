package Model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Yevhenii on 5/3/15.
 */
public class Department {
    private String departmentName;
    private int moneyForDepartment;
    private int idDepartment;
    private Map<String ,Employee> listEmployee = new HashMap<String, Employee>();

    public Department(String departmentName,int moneyForDepartment, int idDepartment){
        if (moneyForDepartment < 0){
            throw new IllegalArgumentException("invalid value");
        }
        this.departmentName = departmentName;
        this.moneyForDepartment = moneyForDepartment;
        this.idDepartment = idDepartment;
    }

    public Department(String departmentName){
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "\n\n" + "Department{" +
                "departmentName='" + departmentName + '\'' +
                "\n" +
                ", id_department=" + idDepartment +
                "\n" +
                ", listEmployee=" + listEmployee +
                '}';
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getMoneyForDepartment() {
        return moneyForDepartment;
    }

    public void setMoneyForDepartment(int moneyForDepartment) {
        this.moneyForDepartment = moneyForDepartment;
    }

    public Map<String, Employee> getListEmployee() {
        return listEmployee;
    }

    public void setListEmployee(Map<String, Employee> listEmployee) {
        this.listEmployee = listEmployee;
    }

    public int getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }

    public static class Factory {
        private Set<String> seenDepartmentNames = new HashSet<String>();
        public Department create(String departmentName, int moneyForDepartment, int idDepartment) {
            if (!seenDepartmentNames.add(departmentName)) {
                throw new IllegalArgumentException("Department already created");
            }
            return new Department(departmentName, moneyForDepartment, idDepartment);
        }
    }
}
