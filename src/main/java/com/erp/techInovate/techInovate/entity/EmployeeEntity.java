package com.erp.techInovate.techInovate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@Setter
@Table(name = "Employee")
// id
//- 사원번호 ( 자동 생성)
//- name (이름)
//- 주민번호
//- position (직급)
//- status ( 직원 구분 [ 정규 or 계약 or 퇴사 ] )
//- department (부서)
//- hire_date (입사일)
//- contact_info (연락처)
//- email (이메일)
//- birth_date ( 생년월일)
//- address ( 주소 )
//- 경력
//- 계좌 번호
//- 은행
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId; // 기본키

    private String employeeNumber; // 사원번호 (자동 생성)

    private String name; // 이름
    private String ssn; // 주민번호
    private String position; // 직급
    private String status; // 직원 구분 (정규, 계약, 퇴사)
    private String department; // 부서
    private LocalDate hireDate; // 입사일
    private String contactInfo; // 연락처
    private String email; // 이메일
    private LocalDate birthDate; // 생년월일
    private String address; // 주소
    private String experience; // 경력
    private String accountNumber; // 계좌 번호
    private String bank; // 은행


    @PostPersist
    public void generateEmployeeNumber() {
        // employeeId가 설정된 후에 사원번호를 생성할 수 있도록 함
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMM");
        String yearMonth = hireDate.format(formatter);
        this.employeeNumber = yearMonth + this.employeeId; // employeeId는 이제 존재하므로 올바르게 설정됨
    }

    // 추가: 업데이트 시 employeeNumber가 사라지지 않도록 방지
    @PreUpdate
    public void retainEmployeeNumber() {
        if (this.employeeNumber == null || this.employeeNumber.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMM");
            String yearMonth = hireDate.format(formatter);
            this.employeeNumber = yearMonth + this.employeeId; // employeeId가 존재하므로 올바르게 설정됨
        }
    }
}
