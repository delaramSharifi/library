package ir.delaramsharifi.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tbl_book",schema = "library")
public class BookEntity  extends BaseEntity {

    @Column(name = "title", nullable = false, length = 40)
    private String title;

    @Column(name = "author", nullable = false, length = 160)
    private String author;

    @Column(name = "year", nullable = false, length = 160)
    private int year;

    @Column(name = "cost", nullable = false, length = 160)
    private double cost;

    @Column(name = "category", nullable = false, length = 40)
    private String category;

    @Column(name = "available", nullable = false, length = 160)
    private Boolean available;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "book",cascade = CascadeType.MERGE)
    private List<BorrowEntity> borrows = new ArrayList<>();

    public void addBorrows(BorrowEntity borrow){
        if(null != borrow){
            if(null == borrows){
                borrows = new ArrayList<>();
            }
            borrow.setBook(this);
            borrows.add(borrow);
        }
    }

}
