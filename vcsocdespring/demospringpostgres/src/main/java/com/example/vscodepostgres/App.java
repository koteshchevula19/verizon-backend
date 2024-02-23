package com.example.vscodepostgres;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        NamedParametersJdbcTemplateDemo namedParametersJdbcTemplateDemo= context.getBean("namedParametersJdbcTemplateDemo",NamedParametersJdbcTemplateDemo.class);
        Scanner sc = new Scanner(System.in);
        Integer choice;
        String  runOrStop;
       do {
         System.out.println("Enter your choice 1-5");
         System.out.println("1. Count the number of Employees in Table");
         System.out.println("2. Insert a new Employee into the Table");
         System.out.println("3. Display All Employees");
         System.out.println("4. Display the Details By Id");
         System.out.println("5. Update the Details Employee by Id");
         System.out.println("6. Delete the Employee by ID");
         System.out.println("Enter a valid option");
         choice=sc.nextInt();
        
         switch (choice) {
            case 1:System.out.println("Counting the Number of Employees");
                    System.out.println("Number of Employees: "+namedParametersJdbcTemplateDemo.countEmployees()); 
                break;
            case 2  :System.out.println("Registering the New Employee name, location, id");
            sc.nextLine();
            String regName=sc.nextLine();
            String regLocation = sc.nextLine();
            Integer regId=sc.nextInt();
            sc.nextLine();
           System.out.println( namedParametersJdbcTemplateDemo.addNewEmployee(new Employee(regName, regLocation, regId))+" Employee Added Successfully");
            break;
            case 3:System.out.println("Displaying all the Employees");
            namedParametersJdbcTemplateDemo.getAllEmployees().forEach(System.out::println);
            break;
            case 4:namedParametersJdbcTemplateDemo.getAllEmployees().forEach(System.out::println);
            System.out.println("Displaying the Employee By Id Please see above Ids and Enter a valid Id");
            
            Integer id=sc.nextInt();
           
            System.out.println(namedParametersJdbcTemplateDemo.getEmployeeById(id));
            break;
            case 5: 
            namedParametersJdbcTemplateDemo.getAllEmployees().forEach(System.out::println);
            System.out.println("Updating the Employee Details By Id Enter name  location and please enter the matching ID of the above Details");
             sc.nextLine();
            String updateName=sc.nextLine();
            String updateLocation = sc.nextLine();
            Integer updateId=sc.nextInt();
            sc.nextLine();
            namedParametersJdbcTemplateDemo.updateEmployeeDetailsById(new Employee(updateName, updateLocation, updateId));
            System.err.println("After updating the Employee Details");
             namedParametersJdbcTemplateDemo.getAllEmployees().forEach(System.out::println);
             break;
             case 6: System.out.println("Before Deleting the Count: "+namedParametersJdbcTemplateDemo.countEmployees());
             System.out.println("Deleting Employee Details By Id enter a valid Id  ");
             Integer delId=sc.nextInt();
             System.out.println(namedParametersJdbcTemplateDemo.deleteEmployeeById(delId)+" Employee Deleted Successfully...");
             System.out.println("After Deleting the Count: "+namedParametersJdbcTemplateDemo.countEmployees());
             System.err.println("Deleted..");
             break;
         }
         System.out.println("Enter Y to Run  and any other key to Exit");
         runOrStop=sc.next();
        
       } while (runOrStop.equalsIgnoreCase("Y"));
        
        System.out.println(namedParametersJdbcTemplateDemo.countEmployees());
    }
}
