package com.gem.nrserver.persistent.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QProductStore is a Querydsl query type for ProductStore
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QProductStore extends EntityPathBase<ProductStore> {

    private static final long serialVersionUID = -365086445L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductStore productStore = new QProductStore("productStore");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final NumberPath<Double> price = createNumber("price", Double.class);

    public final QProduct product;

    public final QStore store;

    public final DateTimePath<java.util.Date> updatedDate = createDateTime("updatedDate", java.util.Date.class);

    public QProductStore(String variable) {
        this(ProductStore.class, forVariable(variable), INITS);
    }

    public QProductStore(Path<? extends ProductStore> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProductStore(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProductStore(PathMetadata<?> metadata, PathInits inits) {
        this(ProductStore.class, metadata, inits);
    }

    public QProductStore(Class<? extends ProductStore> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product"), inits.get("product")) : null;
        this.store = inits.isInitialized("store") ? new QStore(forProperty("store"), inits.get("store")) : null;
    }

}

