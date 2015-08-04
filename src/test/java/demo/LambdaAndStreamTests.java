package demo;

import demo.domain.Customer;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;


public class LambdaAndStreamTests {



    public List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        customers.add(createCustomer(1, "Switzerland", 100, "Alice", 200));
        customers.add(createCustomer(2, "Switzerland", 200, "Bob", 300));
        customers.add(createCustomer(3, "USA", 300, "Carol", 250));
        customers.add(createCustomer(4, "UK", 100, "Dave", 500));
        customers.add(createCustomer(5, "Canada", 10, "Eve", 30));

        return customers;
    }

    public class CustomerComparator implements Comparator<Customer> {

        @Override
        public int compare(Customer o1, Customer o2) {
            return o1.getCountry().compareTo(o2.getCountry());
        }
    }

    @Test
    public void Java7() {
        List<Customer> customers = getCustomers();
        customers.sort(new CustomerComparator());

        printCustomers(customers);
    }

    @Test
    public void Java7_익명클래스() {
        List<Customer> customers = getCustomers();

        customers.sort(
                new Comparator<Customer>() {
                    @Override
                    public int compare(Customer c1, Customer c2) {
                        return c1.getCountry().compareTo(c2.getCountry());
                    }
                }
        );

        printCustomers(customers);
    }

    @Test
    public void 람다식_Java8() {
        List<Customer> customers = getCustomers();
        customers.sort(
                (c1, c2) -> c1.getCountry().compareTo(c2.getCountry())
        );

        printCustomers(customers);
    }

    /*
      고객 목록에서 country가 Switzerland인 고객의 목록을 출력하시오.
     */
    @Test
    public void Java7_필터링_1() {
        List<Customer> customers = getCustomers();
        List<Customer> filtered = new LinkedList<>();

        for(Customer c : customers) {
            if ("Switzerland".equals(c.getCountry())) {
                filtered.add(c);
            }
        }

        for(Customer f : filtered) {
            System.out.println(f);
        }

    }

    @Test
    public void 스트림_필터링_1() {
        List<Customer> customers = getCustomers();

        Stream<Customer> customerStream = customers.stream();

        customerStream
                .filter(c -> "Switzerland".equals(c.getCountry())) // Switzerland
                .forEach(c -> System.out.println(c)); // 목록을 출력.

        System.out.println("원래 고객 : ");
        printCustomers(customers);
    }

    @Test
    public void 스트림_필터링_2() {
        List<Customer> customers = getCustomers();

        customers.stream()
                .filter(c -> "Switzerland".equals(c.getCountry()))
                .filter(c -> c.getSalary() > 200)
                .forEach(System.out::println);

        System.out.println("원래 고객 : ");
        printCustomers(customers);
    }

    private Customer createCustomer(int id, String country, int dept, String name, int salary) {
        Customer c = new Customer();
        c.setCustomerid(id);
        c.setCountry(country);
        c.setDebt(dept);
        c.setName(name);
        c.setSalary(salary);
        return c;
    }

    private void printCustomers(List<Customer> customers) {
        customers.forEach(System.out::println);
    }
}
