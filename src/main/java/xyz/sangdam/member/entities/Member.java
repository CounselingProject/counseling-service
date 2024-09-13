package xyz.sangdam.member.entities;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import xyz.sangdam.global.entities.BaseEntity;
import xyz.sangdam.member.constants.Gender;
import xyz.sangdam.member.constants.UserType;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Member extends BaseEntity {

    private Long seq; // 사용자 번호
    private String email; // 로그인 ID
    private String password; // 비밀번호

    @JsonAlias("userType")
    private String _userType; // 사용자 구분

    private String userName; // 성명
    private String mobile; // 핸드폰 번호

    @JsonAlias("gender")
    private String _gender; // 성별

    private String deptNm; // 부서명 이자 학과명
    private String deptNo; // 부서 번호

    /* 직원 관련 추가 정보 */
    private String empNo; // 사번
    private String subject; // 담당 과목

    /* 학생 관련 추가 정보 */
    private String grade; // 학년
    private String stdntNo; // 학번

    private Member professor; // 지도 교수

    @JsonIgnore
    public UserType getUserType() {
        return _userType == null ? null : UserType.valueOf(_userType);
    }

    @JsonIgnore
    public Gender getGender() {
        return _gender == null ? null : Gender.valueOf(_gender);
    }
}