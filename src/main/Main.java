package main;

import Controlador.SistemaController;
import Modelo.Facade.SistemaCorporativoFacade;
import Modelo.Filial;
import Modelo.Producto;
import Modelo.SedeCentral;
import Vista.VistaMenu;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Filial espana = new Filial("España");
        Filial latam = new Filial("Latam");
        SedeCentral sede = new SedeCentral();

        espana.agregarObservador(sede);
        latam.agregarObservador(sede);

        espana.getInventario().add(new Producto("Laptop Asus", 1500.00, 10));
        espana.getInventario().add(new Producto("Monitor LG", 300.50, 25));
        espana.getInventario().add(new Producto("Teclado Razer", 120.00, 50));

        // Productos para Latam
        latam.getInventario().add(new Producto("Smartphone Samsung", 900.00, 15));
        latam.getInventario().add(new Producto("Tablet Lenovo", 450.00, 20));
        latam.getInventario().add(new Producto("Mouse Logitech", 60.00, 100));
        
        Map<String, Filial> mapaFiliales = new HashMap<>();
        mapaFiliales.put("españa", espana);
        mapaFiliales.put("latam", latam);

        SistemaCorporativoFacade facade = new SistemaCorporativoFacade(espana, latam);

        SistemaController controller = new SistemaController(mapaFiliales, facade);

        java.awt.EventQueue.invokeLater(() -> {
            new VistaMenu(controller).setVisible(true);
        });
    }
}
