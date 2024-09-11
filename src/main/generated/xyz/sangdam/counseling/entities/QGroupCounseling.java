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

    public final QCounseling _super = new QCounseling(this);

    public final DatePath<java.time.LocalDate> counselingEdate = createDate("counselingEdate", java.time.LocalDate.class);

    public final NumberPath<Integer> counselingLimit = createNumber("counselingLimit", Integer.class);

    //inherited
    public final StringPath counselingName = _super.counselingName;

    //inherited
    public final NumberPath<Long> counselingNo = _super.counselingNo;

    public final DatePath<java.time.LocalDate> counselingSdate = createDate("counselingSdate", java.time.LocalDate.class);

    //inherited
    public final StringPath counsellingDes = _super.counsellingDes;

    //inherited
    public final StringPath counselorEmail = _super.counselorEmail;

    //inherited
    public final StringPath counselorName = _super.counselorName;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    //inherited
    public final DatePath<java.time.LocalDate> reservationEdate = _super.reservationEdate;

    //inherited
    public final DatePath<java.time.LocalDate> reservationSdate = _super.reservationSdate;

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

