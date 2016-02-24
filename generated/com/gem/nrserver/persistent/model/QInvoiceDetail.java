package com.gem.nrserver.persistent.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QInvoiceDetail is a Querydsl query type for InvoiceDetail
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QInvoiceDetail extends EntityPathBase<InvoiceDetail> {

    private static final long serialVersionUID = 593112605L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInvoiceDetail invoiceDetail = new QInvoiceDetail("invoiceDetail");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final QInvoice invoice;

    public final QProduct product;

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    public final DateTimePath<java.util.Date> updatedDate = createDateTime("updatedDate", java.util.Date.class);

    public QInvoiceDetail(String variable) {
        this(InvoiceDetail.class, forVariable(variable), INITS);
    }

    public QInvoiceDetail(Path<? extends InvoiceDetail> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QInvoiceDetail(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QInvoiceDetail(PathMetadata<?> metadata, PathInits inits) {
        this(InvoiceDetail.class, metadata, inits);
    }

    public QInvoiceDetail(Class<? extends InvoiceDetail> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.invoice = inits.isInitialized("invoice") ? new QInvoice(forProperty("invoice"), inits.get("invoice")) : null;
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product"), inits.get("product")) : null;
    }

}

