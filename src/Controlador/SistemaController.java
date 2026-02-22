package Controlador;

import Modelo.*;
import Modelo.Facade.SistemaCorporativoFacade;

public class SistemaController {

    private Filial espana;
    private Filial latam;
    private SistemaCorporativoFacade facade;

    public SistemaController() {

        espana = new Filial("España");
        latam = new Filial("Latam");

        SedeCentral sede = new SedeCentral();

        espana.agregarObservador(sede);
        latam.agregarObservador(sede);

        facade = new SistemaCorporativoFacade(espana, latam);
    }

    public void registrarVenta(String region, double monto) {

        if (region.equalsIgnoreCase("España")) {
            espana.registrarVenta(monto);
        } else {
            latam.registrarVenta(monto);
        }
    }

    public String generarReporte(String tipo) {
        return facade.generarReporte(tipo);
    }
}
