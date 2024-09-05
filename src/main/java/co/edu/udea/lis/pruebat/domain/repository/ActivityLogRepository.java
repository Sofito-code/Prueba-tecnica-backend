package co.edu.udea.lis.pruebat.domain.repository;

import co.edu.udea.lis.pruebat.persistence.entity.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ActivityLogRepository extends JpaRepository<ActivityLog, UUID> {
    @Query(value = "SELECT * FROM activity_Log WHERE started_at::text LIKE :startedAt%", nativeQuery = true)
    List<ActivityLog> findActivityLogByStartedAt(@Param("startedAt") String startedAt);

    @Query(value = "SELECT * FROM activity_log WHERE dev_document = :devDocument", nativeQuery = true)
    List<ActivityLog> findActivityLogByDevDocument(@Param("devDocument") BigDecimal devDocument);

    @Query(value = "SELECT * FROM activity_log WHERE id_activity = :id", nativeQuery = true)
    List<ActivityLog> findActivityLogByActivityId(@Param("id") UUID id);

    @Query(value = "SELECT * FROM activity_log WHERE id_activity = :id", nativeQuery = true)
    Optional<ActivityLog> findById(UUID id);
}
