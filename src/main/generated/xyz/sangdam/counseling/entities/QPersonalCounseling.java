package xyz.sangdam.counseling.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPersonalCounseling is a Querydsl query type for PersonalCounseling
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPersonalCounseling extends EntityPathBase<PersonalCounseling> {

    private static final long serialVersionUID = -1415955629L;

    public static final QPersonalCounseling personalCounseling = new QPersonalCounseling("personalCounseling");

    public final QCounseling _super = new QCounseling(this);

    public final EnumPath<xyz.sangdam.counseling.constants.PersonalCategory> category = createEnum("category", xyz.sangdam.counseling.constants.PersonalCategory.class);

    //inherited
    public final StringPath counselingName = _super.counselingName;

    //inherited
    public final NumberPath<Long> counselingNo = _super.counselingNo;

    //inherited
    public final EnumPath<xyz.sangdam.counseling.constants.CounselingType> counselingType = _super.counselingType;

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
    public final StringPath gid = _super.gid;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    //inherited
    public final DatePath<java.time.LocalDate> reservationEdate = _super.reservationEdate;

    //inherited
    public final DatePath<java.time.LocalDate> reservationSdate = _super.reservationSdate;

    public QPersonalCounseling(String variable) {
        super(PersonalCounseling.class, forVariable(variable));
    }

    public QPersonalCounseling(Path<? extends PersonalCounseling> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPersonalCounseling(PathMetadata metadata) {
        super(PersonalCounseling.class, metadata);
    }

}

