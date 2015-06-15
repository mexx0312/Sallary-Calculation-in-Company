package DAO;

import java.util.List;
import Model.Employee;

/**
 *
 * Created by Yevhenii
 */
public interface EmployeeDAO {
    List<Employee> getAllEmployee();
    Employee find(int id);
    int insert(Employee s );
    void update(Employee s);
    void delete(Employee s);
    void select(int id);
}
