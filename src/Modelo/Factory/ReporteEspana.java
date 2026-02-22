package Modelo.Factory;

import Modelo.Filial;

public class ReporteEspana implements Reporte {

    private Filial espana;

    public ReporteEspana(Filial espana) {
        this.espana = espana;
    }

    @Override
    public String generar() {
        return "Total España: " + espana.getVentas();
    }
}
