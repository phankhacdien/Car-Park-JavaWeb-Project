package com.sherlock.carpark.Entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseObject {
    private String status;
    private String msg;
    private Object data;
}
