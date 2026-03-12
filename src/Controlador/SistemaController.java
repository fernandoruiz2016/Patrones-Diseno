package Controlador;

import Modelo.*;
import Modelo.Facade.SistemaCorporativoFacade;
import java.util.Map;

public class SistemaController {
    private final Map<String, Filial> filiales;
    private final SistemaCorporativoFacade facade;

    public SistemaController(Map<String, Filial> filiales, SistemaCorporativoFacade facade) {
        this.filiales = filiales;
        this.facade = facade;
    }

    public void registrarVenta(String region, double monto) {
        Filial f = filiales.get(region.toLowerCase());
        if (f != null) {
            f.registrarVenta(monto);
        } else {
            throw new IllegalArgumentException("Región no soportada");
        }
    }

    public String generarReporte(String tipo) {
        return facade.generarReporte(tipo);
    }
}