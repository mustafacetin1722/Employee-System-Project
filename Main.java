import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

abstract class Employee{
    private String name;
    private int id;

    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
    public abstract double calculateSalary();
    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", id=" + id +'\''+
                ", salary="+ calculateSalary() +
                '}';
    }
}
class FullTimeEmployee extends Employee{
    private double monthlySalary;

    public FullTimeEmployee(String name, int id, double monthlySalary) {
        super(name, id);
        this.monthlySalary = monthlySalary;
    }
    @Override
    public double calculateSalary() {
        return monthlySalary;
    }
}


class PartTimeEmployee extends Employee{
    private int houseWorked;
    private double hourlyRate;
    public PartTimeEmployee(String name, int id,int houseWorked, double hourlyRate){
        super(name, id);
        this.houseWorked=houseWorked;
        this.hourlyRate=hourlyRate;
    }
    @Override
    public double calculateSalary() {
        return houseWorked * hourlyRate;
    }
}
class PayrollSystem{
    private ArrayList<Employee> employeeList;
    public PayrollSystem(){
        employeeList=new ArrayList<>();
    }
    public void addEmployee(Employee employee){
        employeeList.add(employee);
    }
    public void removeEmployee(int id){
        Employee employeeToRemove=null;
        for (Employee employee : employeeList){
            if (employee.getId()==id){
                employeeToRemove = employee;
                break;
            }
        }
        if (employeeToRemove!=null){
            employeeList.remove(employeeToRemove);
        }
    }
    public void displayEmployees() {
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
    }
}



public class Main {
    public static void main(String[] args) {
        PayrollSystem payrollSystem= new PayrollSystem();
        Scanner scanner=new Scanner(System.in);
        while (true){
            System.out.println("Seçeneklerden birini seçiniz.");
            System.out.println("1. Çalışan ekle.");
            System.out.println("2. Çalışan sil.");
            System.out.println("3. Çalışanları görüntüle.");
            int choce=scanner.nextInt();
            switch (choce){
                case 1:
                    System.out.println("Çalışma sistemi FullTime ise 1, PartTime ise 2'ye basınız");
                    int time=scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Lütfen isminizi giriniz.");
                    String name=scanner.nextLine();
                    System.out.println("Lütfen ID numarısı oluşturun.");
                    int id=scanner.nextInt();
                    if (time==1){
                        System.out.println("Lütfen maaşı giriniz.");
                        double salary=scanner.nextDouble();
                        payrollSystem.addEmployee(new FullTimeEmployee(name,id,salary));
                    }
                    if (time==2){
                        System.out.println("Lütfen saatlik ücretini giriniz.");
                        int salary2=scanner.nextInt();
                        System.out.println("Lütfen çalışma saatini giriniz.");
                        double hours= scanner.nextDouble();
                        payrollSystem.addEmployee(new PartTimeEmployee(name,id,salary2,hours));
                    }
                    break;
                case 2:
                    System.out.println("Lütfen silmek istediğiniz hesabın ID numarasını giriniz.");
                    int id2=scanner.nextInt();
                    payrollSystem.removeEmployee(id2);
                    break;
                case 3:
                    payrollSystem.displayEmployees();
                    break;
                case 4:
                    System.exit(0);
            }


        }

    }
}