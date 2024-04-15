package com.spring.restapi.member.doamin;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 135065780L;

    public static final QMember member = new QMember("member1");

    public final com.spring.restapi.core.entity.QBaseEntity _super = new com.spring.restapi.core.entity.QBaseEntity(this);

    public final StringPath email = createString("email");

    public final StringPath gender = createString("gender");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath modBy = _super.modBy;

    //inherited
    public final StringPath modDt = _super.modDt;

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    //inherited
    public final StringPath regBy = _super.regBy;

    //inherited
    public final StringPath regDt = _super.regDt;

    public final StringPath role = createString("role");

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

