package demo.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QLineorderPK is a Querydsl query type for LineorderPK
 */
@Generated("com.mysema.query.codegen.EmbeddableSerializer")
public class QLineorderPK extends BeanPath<LineorderPK> {

    private static final long serialVersionUID = -93396234L;

    public static final QLineorderPK lineorderPK = new QLineorderPK("lineorderPK");

    public final NumberPath<Integer> item = createNumber("item", Integer.class);

    public final NumberPath<Integer> sale = createNumber("sale", Integer.class);

    public QLineorderPK(String variable) {
        super(LineorderPK.class, forVariable(variable));
    }

    public QLineorderPK(Path<? extends LineorderPK> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLineorderPK(PathMetadata<?> metadata) {
        super(LineorderPK.class, metadata);
    }

}

