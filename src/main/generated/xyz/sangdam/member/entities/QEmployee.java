package xyz.sangdam.member.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QEmployee is a Querydsl query type for Employee
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEmployee extends EntityPathBase<Employee> {

    private static final long serialVersionUID = -431711647L;

    public static final QEmployee employee = new QEmployee("employee");

    public final QMember _super = new QMember(this);

    //inherited
    public final StringPath address = _super.address;

    //inherited
    public final StringPath addresssub = _super.addresssub;

    //inherited
    public final DatePath<java.time.LocalDate> birth = _super.birth;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    //inherited
    public final StringPath deptNm = _super.deptNm;

    //inherited
    public final StringPath deptNo = _super.deptNo;

    //inherited
    public final StringPath email = _super.email;

    public final StringPath empNo = createString("empNo");

    //inherited
    public final EnumPath<xyz.sangdam.member.constants.Gender> gender = _super.gender;

    //inherited
    public final StringPath gid = _super.gid;

    //inherited
    public final StringPath mobile = _super.mobile;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    //inherited
    public final StringPath password = _super.password;

    //inherited
    public final NumberPath<Long> seq = _super.seq;

    //inherited
    public final EnumPath<xyz.sangdam.member.constants.Status> status = _super.status;

    public final StringPath subject = createString("subject");

    //inherited
    public final StringPath userName = _super.userName;

    //inherited
    public final EnumPath<xyz.sangdam.member.constants.UserType> userType = _super.userType;

    //inherited
    public final StringPath zonecode = _super.zonecode;

    public QEmployee(String variable) {
        super(Employee.class, forVariable(variable));
    }

    public QEmployee(Path<? extends Employee> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEmployee(PathMetadata metadata) {
        super(Employee.class, metadata);
    }

}

