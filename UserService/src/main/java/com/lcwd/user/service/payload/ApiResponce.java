package com.lcwd.user.service.payload;

import lombok.*;
import org.springframework.http.HttpStatus;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ApiResponce {

    private String message;
    private Boolean success;

    private HttpStatus status;

}
