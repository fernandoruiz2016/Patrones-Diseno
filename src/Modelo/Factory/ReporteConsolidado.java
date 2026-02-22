package Modelo.Factory;

import Modelo.Filial;

public class ReporteConsolidado implements Reporte{
    private Filial espana;
    private Filial latam;

    public ReporteConsolidado(Filial espana, Filial latam) {
        this.espana = espana;
        this.latam = latam;
    }

    @Override
    public String generar() {
        double total = espana.getVentas() + latam.getVentas();
        return "Total Consolidado: " + total;
    }
}
