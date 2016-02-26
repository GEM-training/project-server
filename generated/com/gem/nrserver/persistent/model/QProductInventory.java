package com.gem.nrserver.persistent.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QProductInventory is a Querydsl query type for ProductInventory
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QProductInventory extends EntityPathBase<ProductInventory> {

    private static final long serialVersionUID = -891096946L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductInventory productInventory = new QProductInventory("productInventory");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final QInventory inventory;

    public final QProduct product;

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    public final DateTimePath<java.util.Date> updatedDate = createDateTime("updatedDate", java.util.Date.class);

    public QProductInventory(String variable) {
        this(ProductInventory.class, forVariable(variable), INITS);
    }

    public QProductInventory(Path<? extends ProductInventory> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProductInventory(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProductInventory(PathMetadata<?> metadata, PathInits inits) {
        this(ProductInventory.class, metadata, inits);
    }

    public QProductInventory(Class<? extends ProductInventory> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.inventory = inits.isInitialized("inventory") ? new QInventory(forProperty("inventory"), inits.get("inventory")) : null;
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product"), inits.get("product")) : null;
    }

}

