package co.edu.udea.lis.pruebat.persistence.mapper;

import co.edu.udea.lis.pruebat.domain.dto.ActivityLogDTO;
import co.edu.udea.lis.pruebat.persistence.entity.ActivityLog;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ActivityLogMapper {

    public static ActivityLogDTO mapToActivityLogDTO(ActivityLog activityLog) {
        ActivityLogDTO activityLogDTO = new ActivityLogDTO();
        activityLogDTO.setId(activityLog.getId());
        activityLogDTO.setDevDocument(activityLog.getDevDocument());
        activityLogDTO.setDevName(activityLog.getDevName());
        activityLogDTO.setStartedAt(activityLog.getStartedAt());
        activityLogDTO.setEndedAt(activityLog.getEndedAt());
        activityLogDTO.setActivityDescription(activityLog.getActivityDescription());
        activityLogDTO.setActivityState(activityLog.getActivityState());
        return activityLogDTO;
    }

    public static ActivityLog mapToActivityLog(ActivityLogDTO activityLogDTO) {
        ActivityLog activityLog = new ActivityLog();
        activityLog.setDevDocument(activityLogDTO.getDevDocument());
        activityLog.setDevName(activityLogDTO.getDevName());
        activityLog.setStartedAt(activityLogDTO.getStartedAt());
        activityLog.setEndedAt(activityLogDTO.getEndedAt());
        activityLog.setActivityDescription(activityLogDTO.getActivityDescription());
        activityLog.setActivityState(activityLogDTO.getActivityState());
        return activityLog;
    }
}
