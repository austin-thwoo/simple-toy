package com.sims.soft.domain.board.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLikee is a Querydsl query type for Likee
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLikee extends EntityPathBase<Likee> {

    private static final long serialVersionUID = -959080361L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLikee likee = new QLikee("likee");

    public final com.sims.soft.global.domain.QBaseTimeEntity _super = new com.sims.soft.global.domain.QBaseTimeEntity(this);

    public final QBoard board;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedDate = _super.deletedDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final com.sims.soft.domain.user.domain.QUser User;

    public QLikee(String variable) {
        this(Likee.class, forVariable(variable), INITS);
    }

    public QLikee(Path<? extends Likee> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLikee(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLikee(PathMetadata metadata, PathInits inits) {
        this(Likee.class, metadata, inits);
    }

    public QLikee(Class<? extends Likee> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new QBoard(forProperty("board"), inits.get("board")) : null;
        this.User = inits.isInitialized("User") ? new com.sims.soft.domain.user.domain.QUser(forProperty("User"), inits.get("User")) : null;
    }

}

