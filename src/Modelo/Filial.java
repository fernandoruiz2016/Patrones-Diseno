package Modelo;

import Modelo.Observer.*;
import java.util.ArrayList;
import java.util.List;

public class Filial implements Observable {

    private String nombre;
    private double ventas;
    private List<Observador> observadores = new ArrayList<>();

    private List<Producto> inventario = new ArrayList<>();

    public Filial(String nombre) {
        this.nombre = nombre;
    }

    public void registrarVenta(double monto) {
        ventas += monto;
        notificarObservadores("Nueva venta en " + nombre + ": " + monto);
    }

    public void agregarProducto(Producto p) {
        this.inventario.add(p);
        notificarObservadores("Stock actualizado en " + nombre + ": " + p.getNombre());
    }

    public void venderProducto(String nombreProducto, int cantidad) {
        for (Producto p : inventario) {
            if (p.getNombre().equalsIgnoreCase(nombreProducto)) {
                if (p.getStock() >= cantidad) {
                    p.reducirStock(cantidad);
                    double montoVenta = p.getPrecio() * cantidad;
                    registrarVenta(montoVenta);
                    return;
                } else {
                    notificarObservadores("Error: Stock insuficiente de " + nombreProducto + " en " + nombre);
                    throw new RuntimeException("Stock insuficiente de " + nombreProducto);
                }
            }
        }
        throw new RuntimeException("Producto no encontrado: " + nombreProducto);
    }

    public double getVentas() {
        return ventas;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Producto> getInventario() {
        return inventario;
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
