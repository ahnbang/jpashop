package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*
About Member
id : Long
name : String
address : Address
orders : List
*/



@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String name;
    @Embedded
    private Address address;
    @OneToMany(mappedBy = "member") // mappedBy: 연관관계의 하위임을 선언 : 누구에 의해서? -> Order table에 있는 Member필드에 의해 맵핑된것임을 선언
    private List<Order> orders = new ArrayList<>();


}
