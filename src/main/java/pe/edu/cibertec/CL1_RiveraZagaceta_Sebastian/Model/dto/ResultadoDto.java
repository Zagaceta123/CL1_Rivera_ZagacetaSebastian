package pe.edu.cibertec.CL1_RiveraZagaceta_Sebastian.Model.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResultadoDto {
    private Boolean respuesta;
    private String mensaje;
}