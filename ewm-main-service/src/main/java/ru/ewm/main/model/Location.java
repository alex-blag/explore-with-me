package ru.ewm.main.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "locations")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 120)
    private String name;

    @Column(name = "lat", nullable = false, columnDefinition = "numeric(8,6)")
    private Double lat;

    @Column(name = "lon", nullable = false, columnDefinition = "numeric(9,6)")
    private Double lon;

    @Column(name = "description", nullable = false, length = 7000)
    private String description;

}
