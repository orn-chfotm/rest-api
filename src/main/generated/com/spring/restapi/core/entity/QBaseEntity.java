package com.spring.restapi.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseEntity is a Querydsl query type for BaseEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QBaseEntity extends EntityPathBase<BaseEntity> {

    private static final long serialVersionUID = 1001724684L;

    public static final QBaseEntity baseEntity = new QBaseEntity("baseEntity");

    public final StringPath modBy = createString("modBy");

    public final DateTimePath<java.time.LocalDateTime> modDt = createDateTime("modDt", java.time.LocalDateTime.class);

    public final StringPath regBy = createString("regBy");

    public final DateTimePath<java.time.LocalDateTime> regDt = createDateTime("regDt", java.time.LocalDateTime.class);

    public QBaseEntity(String variable) {
        super(BaseEntity.class, forVariable(variable));
    }

    public QBaseEntity(Path<? extends BaseEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseEntity(PathMetadata metadata) {
        super(BaseEntity.class, metadata);
    }

}

