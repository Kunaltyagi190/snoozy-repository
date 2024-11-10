package com.example.lmsproject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    private Long id;
    private String name;
    private Date birthdate;
}

