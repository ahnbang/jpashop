package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

/*
About Address
Address is Value Type
city, street, zipcode
*/
@Embeddable // ㅇ어딘가에 내장 가능하게끔 Embeddable 어노테이션 선언
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address(){} // JPA 특성때문에 만들어 놓은것!

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}


