package co.edu.udea.lis.pruebat.domain.services.impl;

import co.edu.udea.lis.pruebat.domain.dto.ActivityLogDTO;
import co.edu.udea.lis.pruebat.domain.repository.ActivityLogRepository;
import co.edu.udea.lis.pruebat.domain.services.ActivityLogService;
import co.edu.udea.lis.pruebat.persistence.entity.ActivityLog;
import co.edu.udea.lis.pruebat.persistence.mapper.ActivityLogMapper;
import co.edu.udea.lis.pruebat.web.validation.ValidationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class ActivityLogServiceImpl implements ActivityLogService {
     private final ActivityLogRepository activityLogRepository;

    @Autowired
     public ActivityLogServiceImpl(ActivityLogRepository activityLogRepository) {
         this.activityLogRepository = activityLogRepository;
     }

    @Override
    public ActivityLogDTO saveActivityLog(ActivityLogDTO activityLogDTO) {
        activityLogDTO.setActivityState("En espera");
        if(ValidationData.isNull(activityLogDTO.getDevDocument())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ingrese el documento del auxiliar");
        }
        if(ValidationData.isNotPositiveBigDecimal(activityLogDTO.getDevDocument())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ingrese un número positivo para el campo documento del auxiliar");
        }
        if(ValidationData.isEmptyString(activityLogDTO.getDevName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ingrese el nombre del auxiliar");
        }
        if(activityLogDTO.getEndedAt().isBefore(activityLogDTO.getStartedAt())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La fecha de finalizacion no puede ser anterior a la fecha de inicio.");
        }
        if(ValidationData.isEmptyString(activityLogDTO.getActivityDescription())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ingrese la actividad del auxiliar");
        }
        ActivityLog activityLog = ActivityLogMapper.mapToActivityLog(activityLogDTO);
        ActivityLog savedActivityLog = activityLogRepository.save(activityLog);
        return ActivityLogMapper.mapToActivityLogDTO(savedActivityLog);
    }

    @Override
    public List<ActivityLogDTO> findAllActivityLogs() {
        List<ActivityLog> activities = activityLogRepository.findAll();
        return activities.stream()
                .map(ActivityLogMapper::mapToActivityLogDTO)
                .toList();
    }

    @Override
    public List<ActivityLogDTO> findActivityLogByStartedAt(String startedAt) {
        if (!ValidationData.isValidDate(startedAt)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Formato de fecha inválido. Use el formato 'yyyy-MM-dd");
        }
        List<ActivityLog> activities = activityLogRepository.findActivityLogByStartedAt(startedAt);
        return activities.stream()
                .map(ActivityLogMapper::mapToActivityLogDTO)
                .toList();
    }

    @Override
    public List<ActivityLogDTO> findActivityLogByActivityID(String inputId) {
        UUID uuid = UUID.fromString(inputId);
        List<ActivityLog> activities = activityLogRepository.findActivityLogByActivityId(uuid);
        return activities.stream()
                .map(ActivityLogMapper::mapToActivityLogDTO)
                .toList();
    }

    @Override
    public List<ActivityLogDTO> findActivityLogByDevID(String inputId) {
        if(!ValidationData.isBigDecimal(inputId) && ValidationData.isNotPositiveBigDecimal(new BigDecimal(inputId))){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ingrese un número positivo para el campo documento del auxiliar");
        }
        BigDecimal id = new BigDecimal(inputId);
        List<ActivityLog> activities = activityLogRepository.findActivityLogByDevDocument(id);
        return activities.stream()
                .map(ActivityLogMapper::mapToActivityLogDTO)
                .toList();
    }

    @Override
    public ActivityLog updateActivityState(String id, String activityState) {
        if (!"Aprobada".equalsIgnoreCase(activityState) && !"Rechazada".equalsIgnoreCase(activityState)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Estado de actividad no válido. Debe ser 'Aprobada' o 'Rechazada'.");
        }
        UUID uuid = UUID.fromString(id);
        List<ActivityLog> activityLogs = activityLogRepository.findActivityLogByActivityId(uuid);
        if(activityLogs.isEmpty()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Activity not found");
        }
        ActivityLog activityLog = activityLogs.get(0);
        activityLog.setActivityState(activityState);
        return activityLogRepository.save(activityLog);
    }
}
