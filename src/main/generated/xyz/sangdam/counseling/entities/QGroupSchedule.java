package xyz.sangdam.counseling.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGroupSchedule is a Querydsl query type for GroupSchedule
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGroupSchedule extends EntityPathBase<GroupSchedule> {

    private static final long serialVersionUID = 1705446160L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGroupSchedule groupSchedule = new QGroupSchedule("groupSchedule");

    public final QGroupCounseling counseling;

    public final DatePath<java.time.LocalDate> counselingDate = createDate("counselingDate", java.time.LocalDate.class);

    public final StringPath record = createString("record");

    public final NumberPath<Long> scheduleNo = createNumber("scheduleNo", Long.class);

    public QGroupSchedule(String variable) {
        this(GroupSchedule.class, forVariable(variable), INITS);
    }

    public QGroupSchedule(Path<? extends GroupSchedule> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGroupSchedule(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGroupSchedule(PathMetadata metadata, PathInits inits) {
        this(GroupSchedule.class, metadata, inits);
    }

    public QGroupSchedule(Class<? extends GroupSchedule> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.counseling = inits.isInitialized("counseling") ? new QGroupCounseling(forProperty("counseling")) : null;
    }

}

