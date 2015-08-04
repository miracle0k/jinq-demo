package demo;

import demo.domain.Customer;
import demo.domain.Lineorder;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.jinq.jpa.JPAJinqStream;
import org.jinq.jpa.JPAQueryLogger;
import org.jinq.jpa.JinqJPAStreamProvider;
import org.jinq.orm.stream.JinqStream;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.io.Serializable;
import java.util.Map;

@Component
public class JinqSource {
    private JinqJPAStreamProvider streams;

    @PersistenceUnit
    public void setEntityManagerFactory(EntityManagerFactory emf) throws Exception {
        streams = new JinqJPAStreamProvider(emf);

        // Hibernate seems to generate incorrect metamodel data for some types of
        // associations (specifically, the @ManyToOne relation), so we have to
        // manually supply the correct information to Jinq here. This is not needed
        // for EclipseLink.
        streams.registerAssociationAttribute(Lineorder.class.getMethod("getItem"), "item", false);
        streams.registerAssociationAttribute(Lineorder.class.getMethod("getSale"), "sale", false);

        // Configure Jinq to output the queries it executes
        streams.setHint("queryLogger", new JPAQueryLogger() {
            @Override
            public void logQuery(String query, Map<Integer, Object> positionParameters, Map<String, Object> namedParameters) {
                System.out.println("JPQL : " + query);
            }
        });
    }

    /**
     * JinqStream 생성!
     */
    public <U> JinqStream<U> streamAll(EntityManager em, Class<U> entity) {
        return streams.streamAll(em, entity);
    }

    public JinqStream<Customer> customers(EntityManager em) {
        return streams.streamAll(em, Customer.class);
    }
}
