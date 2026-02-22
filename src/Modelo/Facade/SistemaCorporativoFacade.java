package Modelo.Facade;

import Modelo.Filial;
import Modelo.Factory.*;

public class SistemaCorporativoFacade {

    private Filial espana;
    private Filial latam;

    public SistemaCorporativoFacade(Filial e, Filial l) {
        this.espana = e;
        this.latam = l;
    }

    public String generarReporte(String tipo) {
        Reporte reporte = ReporteFactory.crearReporte(tipo, espana, latam);
        return reporte.generar();
    }
}
