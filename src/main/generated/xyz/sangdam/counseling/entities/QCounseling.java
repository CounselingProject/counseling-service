package xyz.sangdam.counseling.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCounseling is a Querydsl query type for Counseling
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCounseling extends EntityPathBase<Counseling> {

    private static final long serialVersionUID = 52809459L;

    public static final QCounseling counseling = new QCounseling("counseling");

    public final EnumPath<xyz.sangdam.counseling.constants.PersonalCategory> category = createEnum("category", xyz.sangdam.counseling.constants.PersonalCategory.class);

    public final NumberPath<Long> cNo = createNumber("cNo", Long.class);

    public final DatePath<java.time.LocalDate> counselingDate = createDate("counselingDate", java.time.LocalDate.class);

    public final StringPath counselingDes = createString("counselingDes");

    public final NumberPath<Integer> counselingLimit = createNumber("counselingLimit", Integer.class);

    public final StringPath counselingName = createString("counselingName");

    public final StringPath counselorEmail = createString("counselorEmail");

    public final StringPath counselorName = createString("counselorName");

    public final StringPath gid = createString("gid");

    public final StringPath programNm = createString("programNm");

    public final DatePath<java.time.LocalDate> reservationEdate = createDate("reservationEdate", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> reservationSdate = createDate("reservationSdate", java.time.LocalDate.class);

    public final EnumPath<xyz.sangdam.counseling.constants.CounselingType> type = createEnum("type", xyz.sangdam.counseling.constants.CounselingType.class);

    public QCounseling(String variable) {
        super(Counseling.class, forVariable(variable));
    }

    public QCounseling(Path<? extends Counseling> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCounseling(PathMetadata metadata) {
        super(Counseling.class, metadata);
    }

}

