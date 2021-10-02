package peaksoft;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import peaksoft.entity.Department;
import peaksoft.entity.Detail;
import peaksoft.entity.Employee;
import peaksoft.util.HibernateUtil;

import javax.persistence.Query;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Detail detail = new Detail("Bishkek", "+996700010101", "sample@gmail.com");
        Employee employee = new Employee("Aliaskar", 1000);

//        employee.setDetail(detail);
//        create(employee);

//        create(detail);

//        delete(1);

//        detail.setEmployee(employee);
//        employee.setDetail(detail);
//        create(detail);

//        deleteDetail(1);

        //ManyToOne - OneToMany
        Employee employee1 = new Employee("Aliaskar", 1000);
        Employee employee2 = new Employee("Beksultan", 1000);
        Employee employee3 = new Employee("Azamat", 1000);

        Department department = new Department("IT", 300, 2800);

        department.addEmployeeToDepartment(employee1);
        department.addEmployeeToDepartment(employee2);
        department.addEmployeeToDepartment(employee3);

        create(department);
        Detail detail1 = new Detail("Osh","0772444299","Kyrgyzstan");


//        deleteDepartment(1);
//        delete(2);

//        Child child1 = new Child("Bob", 5);
//        Child child2 = new Child("Bill", 3);
//        Child child3 = new Child("Alisa", 4);
//
//        Section section1 = new Section("Painting");
//        Section section2 = new Section("Chess");
//
//        section1.addChildToSection(child1);
//        section1.addChildToSection(child3);

        HibernateUtil.shutDown();
    }


    public static int create(Department e) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(e);
        session.getTransaction().commit();
        session.close();
        System.out.println("Added successfully " + e);
        return e.getId();
    }

    public static int create1(Detail e) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(e);
        session.getTransaction().commit();
        session.close();
        System.out.println("Added successfully " + e);
        return e.getId();
    }

    public static Employee getById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Employee employee = session.get(Employee.class, id);
        session.getTransaction().commit();
        session.close();
        return employee;
    }

    public static List<Employee> getAllEmployees() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Employee> employees = session.createQuery("FROM Employee").getResultList();
        session.getTransaction().commit();
        session.close();
        System.out.println("Found " + employees.size() + " employees");
        return employees;
    }

    public static Employee update(int id, String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Employee em = session.get(Employee.class, id);
        em.setName(name);
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully updated!");
        return em;
    }

    public static void delete(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Employee e = session.get(Employee.class, id);
        session.delete(e);
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted " + e);
    }

    public static void deleteDepartment(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Department e = session.get(Department.class, id);
        session.delete(e);
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted " + e);
    }

    public static void deleteDetail(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Detail e = session.get(Detail.class, id);
        session.delete(e);
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted " + e);
    }

    public static void deleteAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("DELETE FROM Employee");
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted all data in Employee");
    }
}
