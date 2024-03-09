package com.spring.restapi.board.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBoard is a Querydsl query type for Board
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoard extends EntityPathBase<Board> {

    private static final long serialVersionUID = -529653856L;

    public static final QBoard board = new QBoard("board");

    public final com.spring.restapi.core.entity.QBaseTimeEntity _super = new com.spring.restapi.core.entity.QBaseTimeEntity(this);

    public final StringPath content = createString("content");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath modBy = createString("modBy");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDt = _super.modDt;

    public final StringPath regBy = createString("regBy");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDt = _super.regDt;

    public final StringPath title = createString("title");

    public QBoard(String variable) {
        super(Board.class, forVariable(variable));
    }

    public QBoard(Path<? extends Board> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoard(PathMetadata metadata) {
        super(Board.class, metadata);
    }

}

