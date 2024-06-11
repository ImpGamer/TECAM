package com.tecnam.api_get.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Error {

    private String developMessage;
    private int status;
    private String errorMessage;
}
