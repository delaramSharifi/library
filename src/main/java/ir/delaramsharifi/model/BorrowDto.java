package ir.delaramsharifi.model;

import lombok.*;

import java.time.Instant;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BorrowDto {


    private Integer id;

    private Instant created;

    private Instant edited;

    private String dueDate;

    private String returnDate;

    private String issue;

    private MemberDto memberDto;

    private BookDto bookDto;
}
