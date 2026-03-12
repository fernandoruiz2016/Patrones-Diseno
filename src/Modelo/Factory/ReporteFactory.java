package Modelo.Factory;

import Modelo.Filial;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class ReporteFactory {
    private static final Map<String, BiFunction<Filial, Filial, Reporte>> REGISTRY = new HashMap<>();

    static {
        REGISTRY.put("España", (esp, lat) -> new ReporteEspana(esp));
        REGISTRY.put("Latam", (esp, lat) -> new ReporteLatam(lat));
        REGISTRY.put("Consolidado", ReporteConsolidado::new);
    }

    public static Reporte crearReporte(String tipo, Filial espana, Filial latam) {
        BiFunction<Filial, Filial, Reporte> constructor = REGISTRY.get(tipo);
        if (constructor == null) throw new IllegalArgumentException("Tipo no válido");
        return constructor.apply(espana, latam);
    }
}