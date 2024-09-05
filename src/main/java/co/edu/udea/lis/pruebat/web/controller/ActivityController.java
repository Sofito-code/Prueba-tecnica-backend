package co.edu.udea.lis.pruebat.web.controller;

import co.edu.udea.lis.pruebat.domain.dto.ActivityLogDTO;
import co.edu.udea.lis.pruebat.domain.services.ActivityLogService;
import co.edu.udea.lis.pruebat.persistence.entity.ActivityLog;
import co.edu.udea.lis.pruebat.util.Response;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/activities/v1")
@CrossOrigin(origins = "*")
@Tag(name = "Activity Controller", description = "Controlador para manejar actividades")
public class ActivityController {
    private final ActivityLogService activityLogService;

    public ActivityController(ActivityLogService activityLogService) {
        this.activityLogService = activityLogService;
    }

    @Operation(summary = "Crear un nuevo registro de actividad", description = "Guarda un nuevo registro de actividad en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registro de actividad creado con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    @PostMapping("/add")
    public ResponseEntity<Object> createActivityLog(@RequestBody ActivityLogDTO activityLogDTO) {
        try {
            ActivityLogDTO savedActivityLogDTO = activityLogService.saveActivityLog(activityLogDTO);
            return new ResponseEntity<>(savedActivityLogDTO, HttpStatus.CREATED);
        } catch (ResponseStatusException ex) {
            Response response = new Response(ex, activityLogDTO.toString());
            return new ResponseEntity<>(response, ex.getStatusCode());
        }
    }

    @Operation(summary = "Obtener todas las actividades", description = "Recupera una lista de todos los registros de actividades.")
    @GetMapping("/findAll")
    public List<ActivityLogDTO> searchActivities() {
        return activityLogService.findAllActivityLogs();
    }

    @Operation(summary = "Buscar actividades por fecha de inicio", description = "Busca y devuelve actividades que comenzaron en la fecha especificada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Actividades recuperadas con éxito"),
            @ApiResponse(responseCode = "404", description = "No se encontraron actividades para la fecha dada")
    })
    @GetMapping("/findByStartedAt/{startedAt}")
    public Object searchActivitiesByStartedAt(@PathVariable String startedAt) {
        try {
            return activityLogService.findActivityLogByStartedAt(startedAt);
        } catch (ResponseStatusException ex) {
            Response response = new Response(ex, "startedAt " + startedAt);
            return new ResponseEntity<>(response, ex.getStatusCode());
        }
    }

    @Operation(summary = "Buscar una actividad por ID", description = "Recupera un registro de actividad específico basado en su ID.")
    @GetMapping("/findActivityLogByActivityID/{id}")
    public Object searchActivitiesByActivityID(@PathVariable String id) {
        try {
            return activityLogService.findActivityLogByActivityID(id);
        } catch (ResponseStatusException ex) {
            Response response = new Response(ex, "ActivityID " + id);
            return new ResponseEntity<>(response, ex.getStatusCode());
        }
    }

    @Operation(summary = "Buscar actividades por ID del desarrollador", description = "Busca y devuelve actividades relacionadas con un desarrollador específico.")
    @GetMapping("/findActivityLogByDevID/{id}")
    public Object searchActivitiesByDevID(@PathVariable String id) {
        try {
            return activityLogService.findActivityLogByDevID(id);
        } catch (ResponseStatusException ex) {
            Response response = new Response(ex, "DevID " + id);
            return new ResponseEntity<>(response, ex.getStatusCode());
        }
    }

    @Operation(summary = "Actualizar el estado de una actividad", description = "Permite actualizar el estado de una actividad a 'Aprobada' o 'Rechazada'.")
    @PutMapping("/{id}/state")
    public ResponseEntity<ActivityLog> updateActivityState(@PathVariable String id, @RequestParam String activityState) {
        ActivityLog updatedActivity = activityLogService.updateActivityState(id, activityState);
        return ResponseEntity.ok(updatedActivity);
    }
}
