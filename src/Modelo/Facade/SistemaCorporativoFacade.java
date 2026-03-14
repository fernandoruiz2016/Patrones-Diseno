package Modelo.Facade;

import Modelo.Reporte;
import Modelo.Filial;
import Modelo.Producto;
import Modelo.Factory.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SistemaCorporativoFacade {

    private final Map<String, Filial> filiales;

    public SistemaCorporativoFacade(Filial e, Filial l) {
        this.filiales = new HashMap<>();
        this.filiales.put("españa", e);
        this.filiales.put("latam", l);
    }

    public void registrarProducto(String region, String nombre, double precio, int stock) {
        Producto nuevo = new Producto(nombre, precio, stock);
        Filial f = filiales.get(region.toLowerCase());

        if (f != null) {
            f.agregarProducto(nuevo);
        } else {
            throw new IllegalArgumentException("Región no reconocida: " + region);
        }
    }

    public String generarReporteInventario(String tipo) {
        StringBuilder sb = new StringBuilder();
        double valorTotalGeneral = 0;

        sb.append(String.format("%-12s | %-15s | %-6s | %-10s | %-10s\n",
                "REGIÓN", "PRODUCTO", "STOCK", "PRECIO", "SUBTOTAL"));
        sb.append("----------------------------------------------------------------------\n");

        if (tipo.equalsIgnoreCase("Consolidado")) {
            for (Filial f : filiales.values()) {
                valorTotalGeneral += agregarAFila(sb, f);
            }
        } else {
            Filial f = filiales.get(tipo.toLowerCase());
            if (f != null) {
                valorTotalGeneral += agregarAFila(sb, f);
            } else {
                return "Región no válida o sin datos.";
            }
        }

        sb.append("----------------------------------------------------------------------\n");
        sb.append(String.format("%-43s TOTAL: $ %.2f", "", valorTotalGeneral));

        return sb.toString();
    }

    private double agregarAFila(StringBuilder sb, Filial f) {
        double subtotalFilial = 0;
        for (Producto p : f.getInventario()) {
            double subtotalProducto = p.getStock() * p.getPrecio();
            sb.append(String.format("%-12s | %-15s | %-6d | $%-9.2f | $%-9.2f\n",
                    f.getNombre(), p.getNombre(), p.getStock(), p.getPrecio(), subtotalProducto));
            subtotalFilial += subtotalProducto;
        }
        return subtotalFilial;
    }

    public String generarReporte(String tipo) {
        Filial e = filiales.get("españa");
        Filial l = filiales.get("latam");
        Reporte reporte = ReporteFactory.crearReporte(tipo, e, l);
        return reporte.generar();
    }

    public Object[][] obtenerMatrizInventario(String region) {
        List<Object[]> filas = new ArrayList<>();

        if (region.equalsIgnoreCase("Consolidado")) {
            for (Filial f : filiales.values()) {
                extraerDatosFilial(filas, f);
            }
        } else {
            Filial f = filiales.get(region.toLowerCase());
            if (f != null) {
                extraerDatosFilial(filas, f);
            }
        }

        return filas.toArray(new Object[0][]);
    }

    private void extraerDatosFilial(List<Object[]> filas, Filial f) {
        for (Producto p : f.getInventario()) {
            filas.add(new Object[]{f.getNombre(), p.getNombre(), p.getPrecio(), p.getStock()});
        }
    }
}
