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

    public static final QProductInventory productInventory = new QProductInventory("productInventory");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    public final DateTimePath<java.util.Date> updatedDate = createDateTime("updatedDate", java.util.Date.class);

    public QProductInventory(String variable) {
        super(ProductInventory.class, forVariable(variable));
    }

    public QProductInventory(Path<? extends ProductInventory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProductInventory(PathMetadata<?> metadata) {
        super(ProductInventory.class, metadata);
    }

}

