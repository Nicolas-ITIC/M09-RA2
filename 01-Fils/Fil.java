public class Fil extends Thread {

    private String nom;
    private boolean estricte;
    private Control control;
    private int pausa;

    public Fil(String nom, int prioritat,
               boolean estricte, Control control,
               int pausa) {

        this.nom = nom;
        this.estricte = estricte;
        this.control = control;
        this.pausa = pausa;

        setPriority(prioritat);
    }

    @Override
    public void run() {

        try {

            for (int i = 1; i <= 9; i++) {

                int comptador = 0;

                while (comptador < 1000) {
                    comptador++;
                }

                if (estricte) {
                    control.imprimirYAlternar(nom, i);
                } else {
                    System.out.println(nom + " " + i);
                }

                if (pausa > 0) {
                    Thread.sleep(pausa);
                }
            }

            if (estricte) {
                control.terminar(nom);
            } else {
                System.out.println("Acaba el fil " + nom);
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Control {

    private String turno;
    private boolean pepeTerminado = false;

    public Control(String turnoInicial) {
        this.turno = turnoInicial;
    }

        public synchronized void imprimirYAlternar(
            String nom, int numero)
            throws InterruptedException {

        while (!turno.equals(nom)) {
            wait();
        }

        System.out.println(nom + " " + numero);

        if (nom.equals("Juan")) {
            turno = "Pepe";
        } else {
            turno = "Juan";
        }

        notifyAll();
    }

    public synchronized void terminar(String nom)
            throws InterruptedException {

        if (nom.equals("Juan")) {
            while (!pepeTerminado) {
                wait();
            }
        }

        System.out.println("Termina el fil " + nom);

        if (nom.equals("Pepe")) {
            pepeTerminado = true;
            notifyAll();
        }
    }
}
