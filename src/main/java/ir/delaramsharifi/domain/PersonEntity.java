package ir.delaramsharifi.domain;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tbl_person",schema = "library")
public class PersonEntity extends BaseEntity {


    @Column(name = "name", nullable = false, length = 40, unique = true)
    private String name;

    @Column(name = "description", nullable = false, length = 150)
    private String description;
}
