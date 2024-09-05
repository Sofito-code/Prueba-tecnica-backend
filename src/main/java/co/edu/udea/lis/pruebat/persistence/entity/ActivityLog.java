package co.edu.udea.lis.pruebat.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "\"Activity_Log\"")
public class ActivityLog {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @ColumnDefault("gen_random_uuid()")
    @Column(name = "id_activity", nullable = false)
    private UUID id;

    @Column(name = "dev_document", nullable = false)
    private BigDecimal devDocument;

    @Column(name = "dev_name", nullable = false, length = Integer.MAX_VALUE)
    private String devName;

    @Column(name = "started_at", nullable = false)
    private OffsetDateTime startedAt;

    @Column(name = "ended_at", nullable = false)
    private OffsetDateTime endedAt;

    @Column(name = "activity_description", nullable = false, length = Integer.MAX_VALUE)
    private String activityDescription;

    @ColumnDefault("en espera")
    @Column(name = "activity_state", nullable = false, length = Integer.MAX_VALUE)
    private String activityState;

}