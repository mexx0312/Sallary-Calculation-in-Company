package DAO;

/**
 * Created by Yevhenii on 6/12/15.
 */
import Model.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MySQLEmployeeDAO implements EmployeeDAO {
    @Override
    public List<Employee> getAllEmployee() {

        List<Employee> employees = new ArrayList();
        try{
            Connection myConnection = ConnectorDAO.getConnection();
            java.sql.Statement stmt = null;
            try{
                stmt = myConnection.createStatement();
                ResultSet rs = stmt.executeQuery("Select * from employee");
                while(rs.next()){
                    employees.add(new Employee(rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("surname"),
                            rs.getInt("sallary")));
                }
                rs.close();
            } finally{
                if( stmt!=null )
                    stmt.close();
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }catch(Exception ex){
            System.out.println(ex);
        }

        return employees;
    }

    @Override
    public Employee find(int id) {
        return null;
    }

    @Override
    public int insert(Employee s) {
        int generatedKey = 0;
        try{
            Connection myConnection = ConnectorDAO.getConnection();
            java.sql.Statement stmt = null;
            try{
                stmt = myConnection.createStatement();

                PreparedStatement query = myConnection.prepareStatement("Insert into employee (id, name, surname," +
                        " department, sallary) values (?, ?, ?, ?, ?)" , java.sql.Statement.RETURN_GENERATED_KEYS);
                query.setInt(1, s.getId());
                query.setString(2, s.getName());
                query.setString(3, s.getSurname());
                query.setInt(5, s.getSallery());


                query.executeUpdate();
                ResultSet rs = query.getGeneratedKeys();
                if( rs.next() )
                    s.setId(generatedKey = rs.getInt(1));
                rs.close();
            } finally{
                if( stmt!=null )
                    stmt.close();
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }catch(Exception ex){
            System.out.println(ex);
        }

        return generatedKey;
    }

    @Override
    public void update(Employee s) {
        try{
            Connection myConnection = ConnectorDAO.getConnection();
            java.sql.Statement stmt = null;
            try{
                stmt = myConnection.createStatement();
                PreparedStatement query = myConnection.prepareStatement("Update employee Set name = ?, " +
                        "surname = ?, department = ?, sallary = ? where id = ?");
                query.setString(1, s.getName());
                query.setString(2, s.getSurname());
                query.setInt(3, s.getSallery());
                query.setInt(5, s.getId());
                query.executeUpdate();
            } finally{
                if( stmt!=null )
                    stmt.close();
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }catch(Exception ex){
            System.out.println(ex);
        }
    }

    @Override
    public void delete(Employee s) {
        try{
            Connection myConnection = ConnectorDAO.getConnection();
            java.sql.Statement stmt = null;
            try{
                stmt = myConnection.createStatement();
                PreparedStatement query = myConnection.prepareStatement(" delete from employee where id =  ? ");
                query.setInt(1, s.getId());
                query.executeUpdate();
            } finally{
                if( stmt!=null )
                    stmt.close();
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }catch(Exception ex){
            System.out.println(ex);
        }
    }

    @Override
    public void select(int id) {
        System.out.println("TBD");
    }
}

