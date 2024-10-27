package ru.clevertec.edu.ykv.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.clevertec.edu.ykv.entity.RocketType;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RocketResponse {

    private UUID uuid;
    private String name;
    private RocketType rocketType;
    private String country;
    private LocalDate startTestPeriod;
    private LocalDate endTestPeriod;
}
