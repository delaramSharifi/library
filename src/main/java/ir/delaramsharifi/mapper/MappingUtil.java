package ir.delaramsharifi.mapper;

import org.mapstruct.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class MappingUtil {

    //    1.define Element Type for class
    @Qualifier
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.CLASS)
    public @interface BookService {
    }

    //    2.define Element Type for method
    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface BookIdToBookEntity {
    }

//    ------------------------

    //    1.define Element Type for class
    @Qualifier
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.CLASS)
    public @interface MemberService {
    }

    //    2.define Element Type for method
    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface MemberIdToMemberEntity {
    }
}
