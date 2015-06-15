package Model;

import java.util.ArrayList;

/**
 * Created by Yevhenii on 5/3/15.
 */
public class Company {
    private ArrayList<Department> listDepartments = new ArrayList<Department>();
    private String organizationName;
    private int moneyForOrganization;

    public Company(String organizationName){
        this.organizationName = organizationName;
    }

    public Company(String organizationName, int moneyForOrganization){
        if (moneyForOrganization <= 0 ){
            throw new IllegalArgumentException("invalid value");
        }
        this.moneyForOrganization = moneyForOrganization;
    }

    @Override
    public String toString() {
        return "Organization{" + ", organizationName='" +
                organizationName + '\'' +
                "\n" +
                "listDepartments=" + listDepartments
                +
                "\n" +
                '}';
    }

    protected void calculateMoneyForDepartments(){
        int departmentCount;
        double moneyForOneDepartment;

        departmentCount =listDepartments.size();
        moneyForOneDepartment = moneyForOrganization / departmentCount;

        for (int i = 0; i < departmentCount; i++){
            listDepartments.get(i).setMoneyForDepartment((int)Math.round(moneyForOneDepartment));
        }
    }

    public ArrayList<Department> getListDepartments() {
        return listDepartments;
    }

    public void setListDepartments(ArrayList<Department> listDepartments) {
        this.listDepartments = listDepartments;
    }

    public int getMoneyForOrganization() {
        return moneyForOrganization;
    }

    public void setMoneyForOrganization(int moneyForOrganization) {
        this.moneyForOrganization = moneyForOrganization;
    }
}
