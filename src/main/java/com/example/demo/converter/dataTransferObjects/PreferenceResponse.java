package com.example.demo.converter.dataTransferObjects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PreferenceResponse {

    private Long id;

    private Boolean isEmailReceived;

    private Boolean isSmsReceived;

}
