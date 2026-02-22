package Modelo;
import Modelo.Observer.*;
import java.util.ArrayList;
import java.util.List;

public class Filial implements Observable{
    private String nombre;
    private double ventas;
    private List<Observador> observadores = new ArrayList<>();
    
    public Filial(String nombre) {
        this.nombre = nombre;
    }
    
    public void registrarVenta(double monto) {
        ventas += monto;
        notificarObservadores("Nueva venta en " + nombre + ": " + monto);
    }
    
    public double getVentas() {
        return ventas;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public void agregarObservador(Observador o) {
        observadores.add(o);
    }

    @Override
    public void notificarObservadores(String mensaje) {
        for (Observador o : observadores) {
            o.actualizar(mensaje);
        }
    }
}
