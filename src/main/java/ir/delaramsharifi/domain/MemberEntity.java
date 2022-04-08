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
@Table(name = "tbl_member",schema = "library")
public class MemberEntity extends BaseEntity{

    @Column(name = "first_name", nullable = false, length = 40, unique = true)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 40)
    private String lastName;

    @Column(name = "national_code", nullable = false, length = 10)
    private String nationalCode;

    @Column(name = "phone_number", nullable = false, length = 11)
    private String phoneNumber;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "member_date", nullable = false, length = 10)
    private String memberDate;

    @Column(name = "expiry_date", nullable = false, length = 10)
    private String expiryDate;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "member",cascade = CascadeType.MERGE)
    private List<BorrowEntity> borrows = new ArrayList<>();

    public void addBorrows(BorrowEntity borrow){
        if(null != borrow){
            if(null == borrows){
                borrows = new ArrayList<>();
            }
            borrow.setMember(this);
            borrows.add(borrow);
        }
    }








}
