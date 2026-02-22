package main;

import Controlador.SistemaController;
import Vista.VistaMenu;

public class Main {
    public static void main(String[] args) {
        SistemaController controller = new SistemaController();

        java.awt.EventQueue.invokeLater(() -> {
            new VistaMenu(controller).setVisible(true);
        });
    }
}
