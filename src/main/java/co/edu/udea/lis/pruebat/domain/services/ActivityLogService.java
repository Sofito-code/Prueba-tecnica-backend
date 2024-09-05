package co.edu.udea.lis.pruebat.domain.services;

import co.edu.udea.lis.pruebat.domain.dto.ActivityLogDTO;
import co.edu.udea.lis.pruebat.persistence.entity.ActivityLog;
import java.util.List;

public interface ActivityLogService {
    List<ActivityLogDTO> findActivityLogByStartedAt(String startedAt);
    List<ActivityLogDTO> findActivityLogByDevID(String inputId);
    List<ActivityLogDTO> findActivityLogByActivityID(String inputId);
    List<ActivityLogDTO> findAllActivityLogs();
    ActivityLogDTO saveActivityLog(ActivityLogDTO activityLogDTO);
    ActivityLog updateActivityState(String id, String activityState);
}
