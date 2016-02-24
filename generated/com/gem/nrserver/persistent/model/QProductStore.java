package com.gem.nrserver.persistent.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QProductStore is a Querydsl query type for ProductStore
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QProductStore extends EntityPathBase<ProductStore> {

    private static final long serialVersionUID = -365086445L;

    public static final QProductStore productStore = new QProductStore("productStore");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Float> price = createNumber("price", Float.class);

    public final DateTimePath<java.util.Date> updatedDate = createDateTime("updatedDate", java.util.Date.class);

    public QProductStore(String variable) {
        super(ProductStore.class, forVariable(variable));
    }

    public QProductStore(Path<? extends ProductStore> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProductStore(PathMetadata<?> metadata) {
        super(ProductStore.class, metadata);
    }

}

