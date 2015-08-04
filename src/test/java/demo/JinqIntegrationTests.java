package demo;

import com.mysema.query.jpa.impl.JPAQuery;
import demo.domain.Customer;
import demo.domain.QCustomer;
import demo.domain.Sale;
import org.jinq.jpa.JPAJinqStream;
import org.jinq.orm.stream.JinqStream;
import org.jinq.tuples.Pair;
import org.jinq.tuples.Tuple3;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.io.Serializable;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SampleApplication.class)
@TestPropertySource(properties = {"spring.jpa.show-sql=true"})
public class JinqIntegrationTests implements Serializable {
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private EntityManager em;

    @Autowired
    private JinqSource jinqSource;

    @Before
    public void before() {
        SampleDbCreator.createDatabase(entityManagerFactory);
        System.out.println("테스트 데이터 준비 완료!!");
    }

    @Test
    public void jinq_쿼리() {
        List<Customer> customers = jinqSource.customers(em)  // Jinq 스트림 생성
                .where(c -> c.getCountry().equals("UK")) // 중간 연산
                .where(c -> c.getSalary() >= 100)
                .toList();

        customers.forEach(c -> System.out.println(c));
    }

    @Test
    public void queryDSL_쿼리() {
        QCustomer c = QCustomer.customer;

        List<Customer> customers = new JPAQuery(em).from(c)
                .where(
                        c.country.eq("UK"),
                        c.salary.goe(100)
                ).list(c);


        customers.forEach(customer -> System.out.println(customer));
    }

    @Test
    public void 파라미터() {
        int salary = 100;
        String country = "UK";
        List<Customer> customers = jinqSource.customers(em)  // Jinq 스트림 생성
                .where(c -> c.getCountry().equals(country)) // 중간 연산
                .where(c -> c.getSalary() >= salary)
                .toList();

        customers.forEach(c -> System.out.println(c));
    }

    @Test
    public void 런타임_조합() {
    Integer salary = 100;
    String country = null;

    JinqStream<Customer> stream = jinqSource.customers(em);

    if (country != null) {
        stream = stream.where(c -> c.getCountry().equals(country));
    }

    if (salary != null) {
        stream = stream.where(c -> c.getSalary() >= salary);
    }

    List<Customer> customers = stream.toList();
    customers.forEach(c -> System.out.println(c));
}

    @Test
    public void where_OR() {
        jinqSource.customers(em)
                .where(c -> c.getCountry().equals("Switzerland")
                        || c.getSalary() > 250)
                .toList().forEach(System.out::println);
    }

    @Test
    public void where_if() {
        List<Customer> list = jinqSource.customers(em)
                .where(c -> {
                    if (c.getCountry().equals("Switzerland")) {
                        return c.getSalary() > 250;
                    } else {
                        return c.getSalary() < 250;
                    }
                }).toList();

        list.forEach(System.out::println);
    }

    @Test
    public void queryDSL_if() {
        QCustomer c = QCustomer.customer;

        List<Customer> customers = new JPAQuery(em).from(c)
                .where(c.country.eq("Switzerland").and(c.salary.gt(250))
                        .or(c.country.ne("Switzerland").and(c.salary.lt(250))))
                .list(c);

        customers.forEach(System.out::println);
    }

    @Test
    public void select_1() {
        jinqSource.customers(em)
                .select(c -> c.getCountry())
                .forEach(System.out::println);
    }

    @Test
    public void select_if() {
        jinqSource.customers(em)
        .select(c -> {
            if (c.getSalary() > 250) {
                return "부자";
            } else {
                return c.getName();
            }
        }).forEach(System.out::println);
    }

    @Test
    public void select_연산() {
        jinqSource.customers(em)
            .select(c -> c.getDebt() - c.getSalary())
            .forEach(System.out::println);
    }

    @Test
    public void pair_1() {
        jinqSource.customers(em)
                .select(c -> {
                    String rich = "--";
                    if (c.getSalary() > 250) {
                        rich = "부자";
                    }
                    return new Pair<>(c.getName(), rich);
                }).forEach(System.out::println);
    }
    @Test
    public void pair_프로젝션과_필터링조합() {
        jinqSource.customers(em)
                .where(c -> c.getSalary() > 200)
                .select(c -> new Pair<>(c.getName(), c.getSalary() - c.getDebt()))
                .where(p -> p.getTwo() < 0)
                .forEach(System.out::println);
    }

    @Test
    public void tuple3() {
        jinqSource.customers(em)
                .select(c -> {
                    String aa = "--";
                    if (c.getSalary() > 250) {
                        aa = "부자";
                    }

                    return new Tuple3<>(c.getName(), aa, c.getSalary());

                }).forEach(System.out::println);
    }

    @Test
    @Transactional
    public void joinFetch_N_1() {
        List<Customer> customers = jinqSource.customers(em).toList();

        customers.forEach(c -> {
            System.out.println(c.getName() + ":" + c.getSales().size());
        });
    }

    @Test
    public void join() {
        JinqStream<Customer> customers =  jinqSource.customers(em);

        JinqStream<Pair<Customer, Sale>> pairs =
                customers.join(c -> JinqStream.from(c.getSales()));

        pairs.forEach(System.out::println);
    }


    @Test
    @Transactional
    public void joinFetch() {
        List<Customer> customers =
                ((JPAJinqStream<Customer>)jinqSource.customers(em))
                .joinFetchList(c -> c.getSales())
                .toList();

        customers.forEach(c -> {
            System.out.println(c.getName() + ":" + c.getSales().size());
        });
    }

    @Test
    public void 집계_SUM() {
        Long sum = jinqSource.customers(em)
                .where(c -> c.getCountry().equals("Switzerland"))

                .sumInteger(c -> c.getSalary());

        System.out.println("SUM : " + sum);
    }

    @Test
    public void 집계_여러개() {
        Pair<Integer, Double> aggregate = jinqSource.customers(em)
                .aggregate(
                        s -> s.max(c -> c.getSalary()),
                        s -> s.avg(c -> c.getSalary())
                );

        System.out.println("MAX : " + aggregate.getOne());
        System.out.println("AVG : " + aggregate.getTwo());
    }

    @Test
    public void soring_정렬() {
        jinqSource.customers(em)
            .sortedBy(c -> c.getSalary())
            .sortedDescendingBy(c -> c.getCountry())
            .forEach(System.out::println);
    }

    @Test
    public void limitAndSkip() {
        jinqSource.customers(em)
            .skip(1)
            .limit(3)
            .forEach(System.out::println);
    }

    @Test
    public void 그룹핑() {
        List<Tuple3<String, Long, Long>> tuple3s = jinqSource.customers(em)
                .group(
                        c -> c.getCountry(),
                        (customer, stream) -> stream.count(),
                        (customer, stream) -> stream.sumInteger(c -> c.getSalary())
                ).toList();

        tuple3s.forEach(System.out::println);
    }

    @Test
    public void  DTO_에러() {
        TestDTO dto = new TestDTO();
        dto.setName("Bob");
        String name = dto.getName();
        long count = jinqSource.customers(em)
                .where(c -> c.getName().equals(name))
                .count();

        System.out.println(count);
    }
}
