package com.gem.nrserver.persistent.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QEnterprise is a Querydsl query type for Enterprise
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QEnterprise extends EntityPathBase<Enterprise> {

    private static final long serialVersionUID = -84609150L;

    public static final QEnterprise enterprise = new QEnterprise("enterprise");

    public final StringPath address = createString("address");

    public final SetPath<Company, QCompany> companies = this.<Company, QCompany>createSet("companies", Company.class, QCompany.class, PathInits.DIRECT2);

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final DateTimePath<java.util.Date> updatedDate = createDateTime("updatedDate", java.util.Date.class);

    public QEnterprise(String variable) {
        super(Enterprise.class, forVariable(variable));
    }

    public QEnterprise(Path<? extends Enterprise> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEnterprise(PathMetadata<?> metadata) {
        super(Enterprise.class, metadata);
    }

}

