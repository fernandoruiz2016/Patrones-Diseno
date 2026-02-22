package Modelo;
import Modelo.Observer.Observador;

public class SedeCentral implements Observador{
    @Override
    public void actualizar(String mensaje) {
        System.out.println("SEDE CENTRAL: " + mensaje);
    }
}
