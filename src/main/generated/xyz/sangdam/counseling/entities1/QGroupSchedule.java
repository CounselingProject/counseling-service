package xyz.sangdam.counseling.entities1;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QGroupSchedule is a Querydsl query type for GroupSchedule
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGroupSchedule extends EntityPathBase<GroupSchedule> {

    private static final long serialVersionUID = -409114191L;

    public static final QGroupSchedule groupSchedule = new QGroupSchedule("groupSchedule");

    public final DatePath<java.time.LocalDate> groupEdate = createDate("groupEdate", java.time.LocalDate.class);

    public final NumberPath<Long> groupNo = createNumber("groupNo", Long.class);

    public final DatePath<java.time.LocalDate> groupSdate = createDate("groupSdate", java.time.LocalDate.class);

    public final StringPath record = createString("record");

    public QGroupSchedule(String variable) {
        super(GroupSchedule.class, forVariable(variable));
    }

    public QGroupSchedule(Path<? extends GroupSchedule> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGroupSchedule(PathMetadata metadata) {
        super(GroupSchedule.class, metadata);
    }

}

