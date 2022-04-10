package ir.delaramsharifi;

import ir.delaramsharifi.service.MemberService;
import ir.delaramsharifi.utils.DateTimeUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class LibraryApplicationTests {

    @Autowired
    MemberService memberService;

    @Autowired
    DateTimeUtils dateTimeUtils;

    @Disabled
    @Test
    void contextLoads() {
    }

    @Test
    void testDate(){
        boolean returnValue = memberService.isMemberDateValid("1400/01/22");
        String expiryDate = "1401/01/22";

        Date date2 = DateTimeUtils.fromPersianDateToDate(1400, 0, 22, 2, 0, 0);
        System.out.println(DateTimeUtils.isTodayAfterPersianDate("1401/01/21"));

        Assertions.assertEquals(returnValue,false);
    }

}
