package Modelo.Facade;

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
        Producto nuevo = new Producto(nombre, precio, stock, false); // Creamos producto directo
        if (region.equalsIgnoreCase("España")) {
            espana.agregarProducto(nuevo);
        } else if (region.equalsIgnoreCase("Latam")) {
            latam.agregarProducto(nuevo);
        }
    }
    
    public String generarReporteInventario() {
        return "--- STOCK ACTUAL ---\n" +
               "España: " + espana.getInventario().size() + " tipos de productos.\n" +
               "Latam: " + latam.getInventario().size() + " tipos de productos.";
    }

    public String generarReporte(String tipo) {
        Reporte reporte = ReporteFactory.crearReporte(tipo, espana, latam);
        return reporte.generar();
    }
}
