package demo.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QLineorder is a Querydsl query type for Lineorder
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QLineorder extends EntityPathBase<Lineorder> {

    private static final long serialVersionUID = 1287052219L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLineorder lineorder = new QLineorder("lineorder");

    public final QItem item;

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    public final QSale sale;

    public final NumberPath<java.math.BigDecimal> total = createNumber("total", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigInteger> transactionConfirmation = createNumber("transactionConfirmation", java.math.BigInteger.class);

    public QLineorder(String variable) {
        this(Lineorder.class, forVariable(variable), INITS);
    }

    public QLineorder(Path<? extends Lineorder> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QLineorder(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QLineorder(PathMetadata<?> metadata, PathInits inits) {
        this(Lineorder.class, metadata, inits);
    }

    public QLineorder(Class<? extends Lineorder> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.item = inits.isInitialized("item") ? new QItem(forProperty("item")) : null;
        this.sale = inits.isInitialized("sale") ? new QSale(forProperty("sale"), inits.get("sale")) : null;
    }

}

