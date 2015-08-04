package demo.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSale is a Querydsl query type for Sale
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSale extends EntityPathBase<Sale> {

    private static final long serialVersionUID = -1943278650L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSale sale = new QSale("sale");

    public final QCustomer customer;

    public final DateTimePath<java.util.Date> date = createDateTime("date", java.util.Date.class);

    public final ListPath<Lineorder, QLineorder> lineorders = this.<Lineorder, QLineorder>createList("lineorders", Lineorder.class, QLineorder.class, PathInits.DIRECT2);

    public final NumberPath<Integer> saleid = createNumber("saleid", Integer.class);

    public QSale(String variable) {
        this(Sale.class, forVariable(variable), INITS);
    }

    public QSale(Path<? extends Sale> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSale(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSale(PathMetadata<?> metadata, PathInits inits) {
        this(Sale.class, metadata, inits);
    }

    public QSale(Class<? extends Sale> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.customer = inits.isInitialized("customer") ? new QCustomer(forProperty("customer")) : null;
    }

}

