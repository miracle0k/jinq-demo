package demo.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSupplier is a Querydsl query type for Supplier
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSupplier extends EntityPathBase<Supplier> {

    private static final long serialVersionUID = -1564324405L;

    public static final QSupplier supplier = new QSupplier("supplier");

    public final StringPath country = createString("country");

    public final BooleanPath hasFreeShipping = createBoolean("hasFreeShipping");

    public final ListPath<Item, QItem> items = this.<Item, QItem>createList("items", Item.class, QItem.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final NumberPath<Long> revenue = createNumber("revenue", Long.class);

    public final ArrayPath<byte[], Byte> signature = createArray("signature", byte[].class);

    public final NumberPath<Integer> supplierid = createNumber("supplierid", Integer.class);

    public QSupplier(String variable) {
        super(Supplier.class, forVariable(variable));
    }

    public QSupplier(Path<? extends Supplier> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSupplier(PathMetadata<?> metadata) {
        super(Supplier.class, metadata);
    }

}

