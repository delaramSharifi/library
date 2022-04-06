package ir.delaramsharifi.model;


import lombok.*;

import java.time.Instant;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PersonDto {

    private Integer id;

    private Instant created;

    private Instant edited;

    private String name;

    private String description;
}
