package com.sherlock.carpark.Exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiException {
    private String msg;
    private Throwable throwable;
    private HttpStatus httpStatus;
}
