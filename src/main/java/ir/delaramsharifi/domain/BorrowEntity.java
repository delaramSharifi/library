package ir.delaramsharifi.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tbl_borrow",schema = "library")
public class BorrowEntity extends BaseEntity{

    @Column(name = "due_date", nullable = false, length = 10)
    private String dueDate;

    @Column(name = "return_date", nullable = false, length = 10)
    private String returnDate;

    @Column(name = "issue", nullable = false, length = 500)
    private String issue;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "book_id")
    private  BookEntity book;

}
