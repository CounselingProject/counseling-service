package xyz.sangdam.member.entities;

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

    private static final long serialVersionUID = -283930867L;

    public static final QMember member = new QMember("member1");

    public final xyz.sangdam.global.entities.QBaseEntity _super = new xyz.sangdam.global.entities.QBaseEntity(this);

    public final StringPath address = createString("address");

    public final StringPath addresssub = createString("addresssub");

    public final DatePath<java.time.LocalDate> birth = createDate("birth", java.time.LocalDate.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final StringPath deptNm = createString("deptNm");

    public final StringPath deptNo = createString("deptNo");

    public final StringPath email = createString("email");

    public final EnumPath<xyz.sangdam.member.constants.Gender> gender = createEnum("gender", xyz.sangdam.member.constants.Gender.class);

    public final StringPath gid = createString("gid");

    public final StringPath mobile = createString("mobile");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath password = createString("password");

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public final EnumPath<xyz.sangdam.member.constants.Status> status = createEnum("status", xyz.sangdam.member.constants.Status.class);

    public final StringPath userName = createString("userName");

    public final EnumPath<xyz.sangdam.member.constants.UserType> userType = createEnum("userType", xyz.sangdam.member.constants.UserType.class);

    public final StringPath zonecode = createString("zonecode");

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

