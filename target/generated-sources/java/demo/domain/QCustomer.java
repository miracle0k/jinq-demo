package demo.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCustomer is a Querydsl query type for Customer
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCustomer extends EntityPathBase<Customer> {

    private static final long serialVersionUID = 705156061L;

    public static final QCustomer customer = new QCustomer("customer");

    public final StringPath country = createString("country");

    public final NumberPath<Integer> customerid = createNumber("customerid", Integer.class);

    public final NumberPath<Integer> debt = createNumber("debt", Integer.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> salary = createNumber("salary", Integer.class);

    public final ListPath<Sale, QSale> sales = this.<Sale, QSale>createList("sales", Sale.class, QSale.class, PathInits.DIRECT2);

    public QCustomer(String variable) {
        super(Customer.class, forVariable(variable));
    }

    public QCustomer(Path<? extends Customer> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCustomer(PathMetadata<?> metadata) {
        super(Customer.class, metadata);
    }

}

