package Model;

import DAO.ConnectorDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yevhenii on 5/5/15.
 */
public class PreparedQueries {

    public void selectEmployee(int idInTable){
        Connection myConnection = Main.getConnection();
        try( PreparedStatement query2 = myConnection.prepareStatement("select * from employee where id = ?" ) ) {

            query2.setInt(1,idInTable);

            ResultSet result = query2.executeQuery();
            while(result.next()){
                int id = result.getInt("id");
                String name = result.getString("name");
                String surname = result.getString("surname");
                String department = result.getString("id_department");
                int sallary = result.getInt("sallary");
                System.out.println(id + "  " + name + "   " + surname + "  " + department + "   " + sallary );
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    public void insertEmployee(int id, String name, String surname, int id_department, int sallary){
        Connection myConnection;
        try {
            myConnection = Main.getConnection();

            PreparedStatement query = myConnection.prepareStatement("insert into employee (id , name, surname," +
                    " id_department, sallary, bonus) values ( ? , ?, ?, ?, ?, 0)" , Statement.RETURN_GENERATED_KEYS);

            myConnection.setAutoCommit(false);
            try{
                query.setInt(1, id) ;
                query.setString(2, name);
                query.setString(3, surname);
                query.setInt(4, id_department);
                query.setInt(5, sallary);
                query.executeUpdate();

                ResultSet rs = query.getGeneratedKeys();
                if( rs.next() ){
                    System.out.println( "Serial value for inserted record "+rs.getInt(1) ); ;
                }
                query.close();

                myConnection.commit();
                myConnection.close();
            }catch( SQLException ex){
                System.out.println("Trtansaction is rolled back!");
                myConnection.rollback();
            }

        } catch (SQLException ex) {
            System.out.println();
        }
    }

    public void delateEmployee(int idInTable){
        Connection myConnection = Main.getConnection();
        try( PreparedStatement query2 = myConnection.prepareStatement("delete from employee where id = ?" ) ) {

            query2.setInt(1,idInTable);
            query2.executeUpdate();
            System.out.println("employee with index " + idInTable + " deleted" );
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    public void selectUsersByDepartmentId(int departmentId){
        Connection myConnection = Main.getConnection();
        try( PreparedStatement query2 = myConnection.prepareStatement("select * from employee where id_department = ?" ) ) {

            query2.setInt(1,departmentId); //.setInt(parameterIndex, x);

            ResultSet result = query2.executeQuery();
            while(result.next()){
                int id = result.getInt("id");
                String name = result.getString("name");
                String surname = result.getString("surname");
                String department = result.getString("id_department");
                int sallary = result.getInt("sallary");
                System.out.println(id + "  " + name + "   " + surname + "  " + department + "   " + sallary );
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    public void insertNewDepartment(int id, String depName){
        Connection myConnection;
        try {
            myConnection = Main.getConnection();

            PreparedStatement query = myConnection.prepareStatement("insert into department (id_department, department_name) values (?, ?)" , Statement.RETURN_GENERATED_KEYS);

            myConnection.setAutoCommit(false);
            try{
                query.setInt(1, id) ;
                query.setString(2, depName);
                query.executeUpdate();

                ResultSet rs = query.getGeneratedKeys();
                if( rs.next() ){
                    System.out.println( "Serial value for inserted record "+rs.getInt(1) ); ;
                }
                query.close();

                myConnection.commit();
                myConnection.close();
            }catch( SQLException ex){
                System.out.println("Trtansaction is rolled back!");
                myConnection.rollback();
            }

        } catch (SQLException ex) {
            System.out.println();
        }
    }

    public void delateDepartment(int id){
        Connection myConnection = Main.getConnection();
        try( PreparedStatement query2 = myConnection.prepareStatement("delete from department where id_department = ?" ) ) {

            query2.setInt(1,id);
            query2.executeUpdate();
            System.out.println("employee with index " + id + " deleted" );
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    public void selectAllDepartments(){
        Connection myConnection = Main.getConnection();
        try( PreparedStatement query2 = myConnection.prepareStatement("select * from department" ) ) {

            ResultSet result = query2.executeQuery();
            while(result.next()){
                int idDepartment = result.getInt("id_department");
                String departmentName = result.getString("department_name");
                String surname = result.getString("surname");
                String department = result.getString("department");
                int sallary = result.getInt("sallary");
                System.out.println(idDepartment + "  " + departmentName);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    public List<Employee> getListOfEmployee(){
        List<Employee> employees = new ArrayList<>();
        try{
            Connection myConnection = Main.getConnection();
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

    public List<Employee> getListOfEmloyeeByDepartmentID(int departmentID){
        List<Employee> employees = new ArrayList<>();
        try{
            Connection myConnection = Main.getConnection();
            java.sql.Statement stmt = null;
            try{
                stmt = myConnection.createStatement();
                ResultSet rs = stmt.executeQuery("select * from employee where id_department = " + departmentID );
                while(rs.next()){
                    employees.add(new Employee(rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("surname"),
                            rs.getInt("sallary"),
                            rs.getInt("bonus")));
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

    public void updateBonus(Employee s){
        try{
            Connection myConnection = Main.getConnection();
            java.sql.Statement stmt = null;
            try{
                stmt = myConnection.createStatement();
                PreparedStatement query = myConnection.prepareStatement("update employee set bonus = ? where id  = ?");
                query.setInt(1, (int) s.getRestBalance());
                query.setInt(2, s.getId());
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

    public void clearDatabase(){
        Connection myConnection = Main.getConnection();
        try( PreparedStatement query2 = myConnection.prepareStatement("delete from employee" ) ) {
            query2.executeUpdate();
            System.out.println("employee table cleared");
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        Connection myConnection1 = Main.getConnection();
        try( PreparedStatement query2 = myConnection1.prepareStatement("delete from department" ) ) {
            query2.executeUpdate();
            System.out.println("department table cleared");
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
}

