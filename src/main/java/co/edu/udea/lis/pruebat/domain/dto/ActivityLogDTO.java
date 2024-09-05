package co.edu.udea.lis.pruebat.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ActivityLogDTO {
    private UUID id;
    private BigDecimal devDocument;
    private String devName;
    private OffsetDateTime startedAt;
    private OffsetDateTime endedAt;
    private String activityDescription;
    private String activityState;

    @Override
    public String toString() {
        return "ActivityLogDTO {" +
                "devDocument=" + devDocument +
                ", devName='" + devName +
                ", startedAt=" + startedAt +
                ", endedAt=" + endedAt +
                ", activityDescription='" + activityDescription +
                '}';
    }
}