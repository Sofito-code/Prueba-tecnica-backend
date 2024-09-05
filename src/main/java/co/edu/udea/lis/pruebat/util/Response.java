package co.edu.udea.lis.pruebat.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.server.ResponseStatusException;

@Getter
@Setter
@AllArgsConstructor
public class Response {
    private String message;
    private String details;
    private String error;
    private int statusCode;

    public Response(ResponseStatusException ex, String details) {
        this.message = ex.getReason();
        this.details = details;
        this.error = ex.getStatusCode().toString();
        this.statusCode = ex.getStatusCode().value();
    }
}
