package Modelo.Facade;

import Modelo.Reporte;
import Modelo.Filial;
import Modelo.Producto;
import Modelo.Factory.*;

public class SistemaCorporativoFacade {

    private Filial espana;
    private Filial latam;

    public SistemaCorporativoFacade(Filial e, Filial l) {
        this.espana = e;
        this.latam = l;
    }

    public void registrarProducto(String region, String nombre, double precio, int stock) {
        Producto nuevo = new Producto(nombre, precio, stock);
        if (region.equalsIgnoreCase("España")) {
            espana.agregarProducto(nuevo);
        } else if (region.equalsIgnoreCase("Latam")) {
            latam.agregarProducto(nuevo);
        }
    }

    public String generarReporteInventario(String tipo) {
        StringBuilder sb = new StringBuilder();
        double valorTotalGeneral = 0;

        sb.append(String.format("%-12s | %-15s | %-6s | %-10s | %-10s\n", 
                  "REGIÓN", "PRODUCTO", "STOCK", "PRECIO", "SUBTOTAL"));
        sb.append("----------------------------------------------------------------------\n");

        if (tipo.equalsIgnoreCase("Consolidado")) {
            valorTotalGeneral += agregarAFila(sb, espana);
            valorTotalGeneral += agregarAFila(sb, latam);
        } else if (tipo.equalsIgnoreCase("España")) {
            valorTotalGeneral += agregarAFila(sb, espana);
        } else if (tipo.equalsIgnoreCase("Latam")) {
            valorTotalGeneral += agregarAFila(sb, latam);
        } else {
            return "Región no válida.";
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
        Reporte reporte = ReporteFactory.crearReporte(tipo, espana, latam);
        return reporte.generar();
    }
}
