package ee.ksaveri.veebipood.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Hibernate
// automaatselt tekib andmebaasi tabel mis on klassi nimega

//File -> Settins -> Plugins -> JPA Buddy -> Install
@Getter
@Setter //encapsulation
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //int
    private String name;
    private double price;
    private String image; // .jpg
    private boolean active;

}

//kui on vaike tahega:
// long
// char
// double
// boolean
// primitiivsed vaartused. ainult vaartuse hoidmiseks

//kui on suure tahega:
// Long
// String
// Character
// Double
// Boolean
// Suured vaartused.
