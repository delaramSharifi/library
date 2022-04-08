package ir.delaramsharifi.model;

import lombok.*;

import java.time.Instant;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MemberDto {


    private Integer id;

    private Instant created;

    private Instant edited;

    private String firstName;

    private String lastName;

    private String nationalCode;

    private String phoneNumber;

    private String email;

    private String memberDate;

    private String expiryDate;


    private List<BorrowDto> borrowDtos;
}
