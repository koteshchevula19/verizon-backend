package com.example.vscodepostgres;

import java.sql.ResultSet;

import javax.sql.DataSource;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component("namedParametersJdbcTemplateDemo")
public class NamedParametersJdbcTemplateDemo {
    
    DataSource dataSource;
    JdbcTemplate jdbcTemplate;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
	public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
		namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(dataSource);
		
	}

   


    // counting the number of employees in the table
    int countEmployees(){
        return jdbcTemplate.queryForObject("select count(*) from employeedetails", Integer.class);
    }

    //insert into employee table by using namedparametersjdbctemplate

    int addNewEmployee(Employee employee){
        String qry="insert into employeedetails values (:name,:location,:id) ";
        MapSqlParameterSource source = new MapSqlParameterSource()
        .addValue("name",employee.name())
        .addValue("location", employee.location())
        .addValue("id", employee.id());
        return namedParameterJdbcTemplate.update(qry, source);
    
    }

    // Displaying all the Employees

    List<Employee> getAllEmployees() {

        String qry="select * from employeedetails";
        return jdbcTemplate.query(qry,
                                 (ResultSet rs,int numRows)
                                 -> new Employee(
                                rs.getString("name"), 
                                rs.getString("location"),
                                rs.getInt("id")));

        }

        // Get Employee details By Id

        Employee getEmployeeById(Integer id) {

             String qry="select * from employeedetails where id=:id";

            MapSqlParameterSource source = new MapSqlParameterSource().addValue("id", id);
       return namedParameterJdbcTemplate.queryForObject(qry, source, 
                                 (ResultSet rs,int numRows)-> new Employee(
                                rs.getString("name"), 
                                rs.getString("location"),
                                rs.getInt("id"))
                                );                                                      

    }

    // Updating the Employee Details by Id

    int updateEmployeeDetailsById(Employee employee){
        String qry="update employeedetails set name=:name, location=:location where id=:id";
        MapSqlParameterSource source = new MapSqlParameterSource()
                                        .addValue("name", employee.name())
                                        .addValue("location", employee.location())
                                        .addValue("id", employee.id());
         return namedParameterJdbcTemplate.update(qry, source);
    }

    // Delete the Employee By Id

    int deleteEmployeeById(Integer id){
        String qry="delete from employeedetails where id=:id";
        MapSqlParameterSource source = new MapSqlParameterSource().addValue("id", id);
        return namedParameterJdbcTemplate.update(qry, source);
    }
   

    // List<Employee> getEmployeesFromTable(){
    //     String qry="Selct * from employeedetails";
    //     return namedParameterJdbcTemplate.queryForList(qry,
    //                                     (ResultSet rs,int numRows)-> 
    //                                     new Employee(
    //                             rs.getString("name"), 
    //                             rs.getString("location"),
    //                             rs.getInt("id"))
    //     );
    // }

}