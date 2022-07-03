package com.example.demo.converter.dataTransferObjects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



@Getter
@Setter
@ToString
public class PreferenceRequest {

    private Boolean isEmailReceived;

    private Boolean isSmsReceived;
}
