package org.example.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Statistic {

    @PastOrPresent(message = "only past or present time")
    LocalDate date;

    @Email
    String email;

    @Min(value = 0, message = "not less than 0")
    @Max(value = 100, message = "not be greater than 100")
    int age;
}
