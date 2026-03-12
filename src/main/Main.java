package main;

import Controlador.SistemaController;
import Modelo.Facade.SistemaCorporativoFacade;
import Modelo.Filial;
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
