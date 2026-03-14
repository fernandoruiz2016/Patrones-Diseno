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

    public void registrarVenta(String region, String nombreProducto, int cantidad, double montoTotal) {
        Filial f = filiales.get(region.toLowerCase());

        if (f == null) {
            throw new IllegalArgumentException("Región no soportada: " + region);
        }

        try {
            f.venderProducto(nombreProducto, cantidad);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error en la venta: " + e.getMessage());
        }
    }

    public void registrarProducto(String region, String nombre, double precio, int stock) {
        facade.registrarProducto(region, nombre, precio, stock);
    }

    public String verInventario(String tipo) {
        return facade.generarReporteInventario(tipo);
    }

    public String generarReporte(String tipo) {
        return facade.generarReporte(tipo);
    }

    public Object[][] obtenerMatrizInventario(String region) {
        return facade.obtenerMatrizInventario(region);
    }
}
