package dev.rockyj.springapi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_cities")
public class UserCity {

    @Id
    private UUID id;

    @Column("user_id")
    private UUID userId;

    @Column("city_name")
    private String cityName;

}
