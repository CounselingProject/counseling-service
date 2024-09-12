package xyz.sangdam.counseling.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGroupReservation is a Querydsl query type for GroupReservation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGroupReservation extends EntityPathBase<GroupReservation> {

    private static final long serialVersionUID = 39374003L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGroupReservation groupReservation = new QGroupReservation("groupReservation");

    public final QReservation _super;

    // inherited
    public final QCounseling counseling;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt;

    //inherited
    public final StringPath email;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt;

    //inherited
    public final StringPath reason;

    //inherited
    public final StringPath record;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> reservationDate;

    //inherited
    public final NumberPath<Long> reservationNo;

    public final QGroupSchedule schedule;

    //inherited
    public final EnumPath<xyz.sangdam.counseling.constants.Status> status;

    //inherited
    public final StringPath userName;

    public QGroupReservation(String variable) {
        this(GroupReservation.class, forVariable(variable), INITS);
    }

    public QGroupReservation(Path<? extends GroupReservation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGroupReservation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGroupReservation(PathMetadata metadata, PathInits inits) {
        this(GroupReservation.class, metadata, inits);
    }

    public QGroupReservation(Class<? extends GroupReservation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QReservation(type, metadata, inits);
        this.counseling = _super.counseling;
        this.createdAt = _super.createdAt;
        this.deletedAt = _super.deletedAt;
        this.email = _super.email;
        this.modifiedAt = _super.modifiedAt;
        this.reason = _super.reason;
        this.record = _super.record;
        this.reservationDate = _super.reservationDate;
        this.reservationNo = _super.reservationNo;
        this.schedule = inits.isInitialized("schedule") ? new QGroupSchedule(forProperty("schedule"), inits.get("schedule")) : null;
        this.status = _super.status;
        this.userName = _super.userName;
    }

}

