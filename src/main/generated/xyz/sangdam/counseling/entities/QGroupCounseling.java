package xyz.sangdam.counseling.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QGroupCounseling is a Querydsl query type for GroupCounseling
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGroupCounseling extends EntityPathBase<GroupCounseling> {

    private static final long serialVersionUID = -2115150906L;

    public static final QGroupCounseling groupCounseling = new QGroupCounseling("groupCounseling");

    public final DatePath<java.time.LocalDate> counselingEdate = createDate("counselingEdate", java.time.LocalDate.class);

    public final NumberPath<Integer> counselingLimit = createNumber("counselingLimit", Integer.class);

    public final StringPath counselingName = createString("counselingName");

    public final DatePath<java.time.LocalDate> counselingSdate = createDate("counselingSdate", java.time.LocalDate.class);

    public final StringPath counsellingDes = createString("counsellingDes");

    public final StringPath counselorEmail = createString("counselorEmail");

    public final StringPath counselorName = createString("counselorName");

    public final StringPath gid = createString("gid");

    public final NumberPath<Long> groupSeq = createNumber("groupSeq", Long.class);

    public final DatePath<java.time.LocalDate> reservationEdate = createDate("reservationEdate", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> reservationSdate = createDate("reservationSdate", java.time.LocalDate.class);

    public QGroupCounseling(String variable) {
        super(GroupCounseling.class, forVariable(variable));
    }

    public QGroupCounseling(Path<? extends GroupCounseling> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGroupCounseling(PathMetadata metadata) {
        super(GroupCounseling.class, metadata);
    }

}

