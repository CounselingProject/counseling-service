package xyz.sangdam.reservation.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReservation is a Querydsl query type for Reservation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReservation extends EntityPathBase<Reservation> {

    private static final long serialVersionUID = -1906783685L;

    public static final QReservation reservation = new QReservation("reservation");

    public final xyz.sangdam.global.entities.QBaseEntity _super = new xyz.sangdam.global.entities.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final StringPath email = createString("email");

    public final SimplePath<xyz.sangdam.member.entities.Member> member = createSimple("member", xyz.sangdam.member.entities.Member.class);

    public final StringPath mobile = createString("mobile");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath name = createString("name");

    public final NumberPath<Long> orderNo = createNumber("orderNo", Long.class);

    public final StringPath payBankAccount = createString("payBankAccount");

    public final StringPath payBankName = createString("payBankName");

    public final StringPath payLog = createString("payLog");

    public final StringPath payTid = createString("payTid");

    public final NumberPath<Integer> persons = createNumber("persons", Integer.class);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final StringPath rAddress = createString("rAddress");

    public final DateTimePath<java.time.LocalDateTime> rDateTime = createDateTime("rDateTime", java.time.LocalDateTime.class);

    public final StringPath rName = createString("rName");

    public final StringPath rTel = createString("rTel");

    public final EnumPath<xyz.sangdam.reservation.constants.ReservationStatus> status = createEnum("status", xyz.sangdam.reservation.constants.ReservationStatus.class);

    public QReservation(String variable) {
        super(Reservation.class, forVariable(variable));
    }

    public QReservation(Path<? extends Reservation> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReservation(PathMetadata metadata) {
        super(Reservation.class, metadata);
    }

}

