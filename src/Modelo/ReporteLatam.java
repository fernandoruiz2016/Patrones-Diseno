package Modelo;

import Modelo.Reporte;
import Modelo.Filial;

public class ReporteLatam implements Reporte {

    private Filial latam;

    public ReporteLatam(Filial latam) {
        this.latam = latam;
    }

    @Override
    public String generar() {
        return "Total Latam: " + latam.getVentas();
    }
}
