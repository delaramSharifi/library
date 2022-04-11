package ir.delaramsharifi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ErrorMessage {


    private String errorDateTime;

    private String errorCode;

    private String errorMessage;
}

