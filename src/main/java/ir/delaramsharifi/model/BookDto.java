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
public class BookDto {


    private Integer id;

    private Instant created;

    private Instant edited;

    private String title;

    private String author;

    private int year;

    private double cost;

    private String category;

    private Boolean activityStatus;


    private List<BorrowDto> borrowDtos;
}
