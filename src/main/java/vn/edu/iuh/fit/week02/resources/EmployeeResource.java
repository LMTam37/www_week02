package vn.edu.iuh.fit.week02.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.iuh.fit.week02.models.Employee;
import vn.edu.iuh.fit.week02.services.EmployeeService;
import vn.edu.iuh.fit.week02.services.impl.EmployeeServiceImpl;

import java.util.List;
import java.util.Optional;

@Path("employees")
public class EmployeeResource {
    private final EmployeeService employeeService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public EmployeeResource() {
        this.employeeService = new EmployeeServiceImpl();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<Employee> employees = employeeService.findAll();
        return Response.ok(employees).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        Optional<Employee> employee = employeeService.findById(id);
        return employee
                .map(e -> Response.ok(e))
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(Employee employee) {
        Optional<Employee> existingEmployee = employeeService.findById(employee.getEmpId());
        return existingEmployee
                .map(e -> Response.status(Response.Status.FOUND))
                .orElseGet(() -> {
                    employeeService.save(employee);
                    return Response.ok(employee);
                })
                .build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Employee employee) {
        Optional<Employee> updatedEmployee = employeeService.findById(employee.getEmpId());
        return updatedEmployee
                .map(e -> {
                    employeeService.save(employee);
                    return Response.ok(employee);
                })
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        Optional<Employee> employee = employeeService.findById(id);
        return employee
                .map(e -> {
                    employeeService.delete(id);
                    return Response.ok(employee);
                })
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND))
                .build();
    }
}
