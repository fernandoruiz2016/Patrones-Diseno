package Modelo.Factory;

import Modelo.Filial;

public class ReporteFactory {

    public static Reporte crearReporte(String tipo, Filial espana, Filial latam) {
        if (tipo.equalsIgnoreCase("España"))
            return new ReporteEspana(espana);
        else if (tipo.equalsIgnoreCase("Latam"))
            return new ReporteLatam(latam);
        else if (tipo.equalsIgnoreCase("Consolidado"))
            return new ReporteConsolidado(espana, latam);
        else
            throw new IllegalArgumentException("Tipo de reporte no válido");
    }
}
