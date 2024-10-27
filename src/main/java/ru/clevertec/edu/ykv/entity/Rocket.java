package ru.clevertec.edu.ykv.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Rocket {

    @Id
    private String id;
    private UUID uuid;
    private String name;
    private RocketType rocketType;
    private String country;
    private LocalDate startTestPeriod;
    private LocalDate endTestPeriod;
}
