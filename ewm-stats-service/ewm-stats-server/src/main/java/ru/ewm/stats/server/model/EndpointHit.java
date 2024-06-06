package ru.ewm.stats.server.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "endpoint_hits")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EndpointHit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "app_id", nullable = false)
    private ServiceApp serviceApp;

    @Column(name = "uri", nullable = false, length = 250)
    private String uri;

    @Column(name = "ip4", nullable = false, length = 15)
    private String ip4;

    @Column(name = "time_stamp", nullable = false)
    private LocalDateTime timestamp;

}
