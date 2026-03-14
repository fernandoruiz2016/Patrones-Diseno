package Controlador;

import Modelo.*;
import Modelo.Facade.SistemaCorporativoFacade;
import java.util.ArrayList;
import java.util.List;
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
        if (f != null) {
            // Buscamos el producto en el inventario de la filial
            for (Producto p : f.getInventario()) {
                if (p.getNombre().equalsIgnoreCase(nombreProducto)) {
                    // Validamos que haya stock suficiente antes de restar
                    if (p.getStock() >= cantidad) {
                        p.setStock(p.getStock() - cantidad); // Restamos el stock
                        f.registrarVenta(montoTotal); // Registramos el monto financiero
                        return;
                    } else {
                        throw new IllegalArgumentException("Stock insuficiente en " + region);
                    }
                }
            }
            throw new IllegalArgumentException("Producto no encontrado");
        } else {
            throw new IllegalArgumentException("Región no soportada");
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
        List<Object[]> filas = new ArrayList<>();

        if (region.equalsIgnoreCase("Consolidado")) {
            // Recorremos todas las filiales y guardamos su nombre junto al producto
            for (Map.Entry<String, Filial> entry : filiales.entrySet()) {
                String nombreFilial = entry.getValue().getNombre(); // O entry.getKey() si prefieres la clave
                for (Producto p : entry.getValue().getInventario()) {
                    filas.add(new Object[]{nombreFilial, p.getNombre(), p.getPrecio(), p.getStock()});
                }
            }
        } else {
            // Buscamos la filial específica
            Filial f = filiales.get(region.toLowerCase());
            if (f != null) {
                String nombreFilial = f.getNombre();
                for (Producto p : f.getInventario()) {
                    filas.add(new Object[]{nombreFilial, p.getNombre(), p.getPrecio(), p.getStock()});
                }
            }
        }
        // Convertimos la lista de filas en la matriz final de Object[][]
        Object[][] matriz = new Object[filas.size()][4];
        for (int i = 0; i < filas.size(); i++) {
            matriz[i] = filas.get(i);
        }
        return matriz;
    }

    public List<Producto> obtenerProductosPorRegion(String region) {
        Filial f = filiales.get(region.toLowerCase());
        return (f != null) ? f.getInventario() : new ArrayList<>();
    }
}
