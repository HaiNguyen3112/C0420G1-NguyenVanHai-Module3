package Controller;

import dao.CustomerDAO;
import dao.EmployeeDAO;
import dao.ServiceDAO;
import model.Customer;
import model.Employee;
import model.Service;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ControlServlet", urlPatterns = "/furama")
public class ControlServlet extends HttpServlet {
    private CustomerDAO customerDAO;
    private EmployeeDAO employeeDAO;
    private ServiceDAO serviceDAO;
    public void init(){
        customerDAO = new CustomerDAO();
        employeeDAO = new EmployeeDAO();
        serviceDAO = new ServiceDAO();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action ="";
        }
        switch (action){
            case "createcustomer":
                addNewCustomer(request,response);
                break;
            case "createemployee":
                addNewEmployee(request,response);
                break;
            case "createservice":
                addNewService(request,response);
                break;
            case "editcustomer":
                editCustomer(request,response);
                break;
            case "editemployee":
                editEmployee(request,response);
                break;
            default:
                showHome(request,response);
                break;

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){
            action ="";
        }
        switch (action){
            case "createcustomer":
                showAddNewCustomerForm(request,response);
                break;
            case "createemployee":
                showAddNewEmployeeForm(request,response);
                break;
            case "createservice":
                showAddNewServiceForm(request,response);
                break;
            case "customer":
                listCustomer(request,response);
                break;
            case "employee":
                listEmployee(request,response);
                break;
            case "editcustomer":
                showEditCustomerForm(request,response);
                break;
            case "editemployee":
                showEditEmployeeForm(request,response);
                break;
            case "deletecustomer":
                deleteCustomer(request,response);
                break;
            case "deleteemployee":
                deleteEmployee(request,response);
                break;
            default:
                showHome(request,response);
                break;
        }
    }


    private void showAddNewCustomerForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/createCustomer.jsp");
        dispatcher.forward(request,response);
    }
    private void showAddNewEmployeeForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/createEmployee.jsp");
        dispatcher.forward(request,response);
    }
    private void showAddNewServiceForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/createService.jsp");
        dispatcher.forward(request,response);
    }


    private void showHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/index.jsp");
        dispatcher.forward(request,response);
    }
    private void listCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Customer> customerList = customerDAO.selectAllCustomer();

        request.setAttribute("listCustomer",customerList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/listCustomer.jsp");
        dispatcher.forward(request,response);
    }

    public  void listEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Employee> employeeList = employeeDAO.selectAllEmployee();

        request.setAttribute("listEmployee",employeeList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/listEmployee.jsp");
        dispatcher.forward(request,response);
    }

    public void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id= Integer.parseInt(request.getParameter("id"));
        customerDAO.deleteCustomer(id);

        List<Customer> customerList = customerDAO.selectAllCustomer();
        request.setAttribute("listCustomer",customerList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/listCustomer.jsp");
        dispatcher.forward(request,response);
    }

    public void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        employeeDAO.deleteEmployee(id);

        List<Employee> employeeList = employeeDAO.selectAllEmployee();
        request.setAttribute("listEmployee",employeeList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/listEmployee.jsp");
        dispatcher.forward(request,response);
    }


    public void addNewCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int typeId = Integer.parseInt(request.getParameter("typeId"));
        String name = request.getParameter("name");
        String birthday = request.getParameter("birthday");
        int gender = Integer.parseInt(request.getParameter("gender"));
        String idCard = request.getParameter("idCard");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");

        Customer customer = new Customer(id,typeId,name,birthday,gender,idCard,phone,email,address);
        customerDAO.insertCustomer(customer);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/createCustomer.jsp");
        dispatcher.forward(request,response);
    }

    public void addNewEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String birthday = request.getParameter("birthday");
        String idCard = request.getParameter("idCard");
        double salary = Double.parseDouble(request.getParameter("salary"));
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        int position = Integer.parseInt(request.getParameter("position"));
        int educationDegreeId = Integer.parseInt(request.getParameter("educationDegreeId"));
        int divisionId = Integer.parseInt(request.getParameter("divisionId"));
        String username = request.getParameter("username");

        Employee employee = new Employee(id,name,birthday,idCard,salary,phone,email,address,position,educationDegreeId,divisionId,username);
        employeeDAO.insertEmployee(employee);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/createEmployee.jsp");
        dispatcher.forward(request,response);

    }

    public void addNewService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int area = Integer.parseInt(request.getParameter("serviceArea"));
        double cost = Double.parseDouble(request.getParameter("cost"));
        int maxPeople = Integer.parseInt(request.getParameter("maxPeople"));
        int rentTypeId = Integer.parseInt(request.getParameter("rentTypeId"));
        int serviceType = Integer.parseInt(request.getParameter("serviceTypeId"));
       // String standard = request.getParameter("standardRoom");
        //String description = request.getParameter("description");
       // Double pool = Double.parseDouble(request.getParameter("poolArea"));
       // int number = Integer.parseInt(request.getParameter("numberOfFloors"));

        String standard;
        String description;
        Double pool = 0.0;
        int number;
        if(id==2){
            standard = request.getParameter("standardRoom");
            description = request.getParameter("description");
            pool = 0.0;
            number = Integer.parseInt(request.getParameter("numberOfFloors"));
        } else if(id==3){
             pool = 0.0;
             standard = null;
             description =null;
             number = 0;
        } else {
            standard = request.getParameter("standardRoom");
            description = request.getParameter("description");
            pool = Double.parseDouble(request.getParameter("poolArea"));
            number = Integer.parseInt(request.getParameter("numberOfFloors"));
        }

        Service service = new Service(id,name,area,cost,maxPeople,rentTypeId,serviceType,standard,description,pool,number);
        serviceDAO.insertService(service);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/createService.jsp");
        dispatcher.forward(request,response);
    }

    private void showEditCustomerForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Customer existingCustomer = customerDAO.selectCustomer(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/editCustomer.jsp");
        request.setAttribute("customer",existingCustomer);
        dispatcher.forward(request,response);
    }

    public void showEditEmployeeForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Employee existingEmployee = employeeDAO.selectEmployee(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/editEmployee.jsp");
        request.setAttribute("employee",existingEmployee);
        dispatcher.forward(request,response);
    }

    public void editCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int typeId = Integer.parseInt(request.getParameter("typeId"));
        String name = request.getParameter("name");
        String birthday = request.getParameter("birthday");
        int gender = Integer.parseInt(request.getParameter("gender"));
        String idCard = request.getParameter("idCard");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");

        Customer newCustomer = new Customer(id,typeId,name,birthday,gender,idCard,phone,email,address);
        customerDAO.updateCustomer(newCustomer);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/editCustomer.jsp");
        dispatcher.forward(request,response);
    }

    public void editEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String birthday = request.getParameter("birthday");
        String idCard = request.getParameter("idCard");
        double salary = Double.parseDouble(request.getParameter("salary"));
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        int position = Integer.parseInt(request.getParameter("position"));
        int educationDegreeId = Integer.parseInt(request.getParameter("educationDegreeId"));
        int divisionId = Integer.parseInt(request.getParameter("divisionId"));
        String username = request.getParameter("username");

        Employee employee = new Employee(id,name,birthday,idCard,salary,phone,email,address,position,educationDegreeId,divisionId,username);
        employeeDAO.updateEmployee(employee);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/editEmployee.jsp");
        dispatcher.forward(request,response);
    }

}
