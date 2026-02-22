package Modelo.Observer;

public interface Observable {
    void agregarObservador(Observador o);
    void notificarObservadores(String mensaje);
}
