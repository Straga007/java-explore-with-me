package ru.practicum.ewm.users.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "email")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Size(min = 1, max = 250)
    @Column(nullable = false)
    String name;

    @Size(min = 1, max = 254)
    @Email(message = "Invalid email format")
    @Column(nullable = false, unique = true)
    String email;

    @Column(name = "likes")
    Integer likes;

    @Column(name = "dislikes")
    Integer dislikes;

    @Column(name = "rate")
    BigDecimal rate;
}