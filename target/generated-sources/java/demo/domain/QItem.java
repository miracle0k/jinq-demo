package demo.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QItem is a Querydsl query type for Item
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QItem extends EntityPathBase<Item> {

    private static final long serialVersionUID = -1943558510L;

    public static final QItem item = new QItem("item");

    public final NumberPath<Integer> itemid = createNumber("itemid", Integer.class);

    public final ListPath<Lineorder, QLineorder> lineorders = this.<Lineorder, QLineorder>createList("lineorders", Lineorder.class, QLineorder.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final NumberPath<Double> purchaseprice = createNumber("purchaseprice", Double.class);

    public final NumberPath<Double> saleprice = createNumber("saleprice", Double.class);

    public final ListPath<Supplier, QSupplier> suppliers = this.<Supplier, QSupplier>createList("suppliers", Supplier.class, QSupplier.class, PathInits.DIRECT2);

    public final EnumPath<ItemType> type = createEnum("type", ItemType.class);

    public QItem(String variable) {
        super(Item.class, forVariable(variable));
    }

    public QItem(Path<? extends Item> path) {
        super(path.getType(), path.getMetadata());
    }

    public QItem(PathMetadata<?> metadata) {
        super(Item.class, metadata);
    }

}

