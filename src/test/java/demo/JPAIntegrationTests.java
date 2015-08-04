package demo;

import demo.domain.Customer;
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
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SampleApplication.class)
@TestPropertySource(properties = {"spring.jpa.show-sql=true"})
@Transactional
public class JPAIntegrationTests {
    @Autowired
    private EntityManager em;

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Before
    public void before() {
        SampleDbCreator.createDatabase(entityManagerFactory);
        System.out.println("테스트 데이터 준비 완료!!");
    }

    @Test
    public void JPQL로_조회() {
        TypedQuery<Customer> query = em.createQuery(
                "SELECT A \n" +
                "  FROM Customer A \n" +
                " WHERE A.country = 'UK' \n" +
                "   AND A.slary > 100", Customer.class);

        List<Customer> customers = query.getResultList();

        customers.forEach(System.out::println);
    }

    @Test
    public void JPQL로_조회_OK() {
        TypedQuery<Customer> query = em.createQuery(
                "SELECT A \n" +
                "  FROM Customer A \n" +
                " WHERE A.country = 'UK' \n" +
                "   AND A.salary > 100", Customer.class);

        List<Customer> customers = query.getResultList();

        customers.forEach(System.out::println);
    }

    @Test
    public void 크리테리아() {
      CriteriaBuilder cb = em.getCriteriaBuilder();

      CriteriaQuery<Customer> q = cb.createQuery(Customer.class);
      Root<Customer> c = q.from(Customer.class);
      ParameterExpression<String> p = cb.parameter(String.class);
      q.select(c).where(cb.equal(c.get("country"), p));

      TypedQuery<Customer> query = em.createQuery(q);
      query.setParameter(p, "UK");

      List<Customer> customers = query.getResultList();
      customers.forEach(System.out::println);
    }
}
