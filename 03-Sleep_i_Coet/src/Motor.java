import java.util.Random;

public class Motor extends Thread {

    private final int id;
    private final Random random = new Random();
    private int potenciaObjectiu = 0;
    private int potenciaActual = 0;
    private boolean acabar = false;

    public Motor(int id) {
        this.id = id;
    }

    public synchronized void setPotencia(int p) {
        this.potenciaObjectiu = p;
        notifyAll();
    }

    public synchronized void acaba() {
        this.acabar = true;
        notifyAll();
    }

    @Override
    public void run() {
        try {
            while (true) {
                synchronized (this) {
                    while (potenciaActual == potenciaObjectiu) {
                        if (acabar && potenciaObjectiu == 0) {
                            return;
                        }
                        wait();
                    }
                }

                Thread.sleep(1000 + random.nextInt(1001));

                synchronized (this) {
                    if (potenciaActual < potenciaObjectiu) {
                        potenciaActual++;
                        System.out.println("Motor " + id + ": Incre. Objectiu: "
                                + potenciaObjectiu + " Actual: " + potenciaActual);
                    } else if (potenciaActual > potenciaObjectiu) {
                        potenciaActual--;
                        System.out.println("Motor " + id + ": Decre. Objectiu: "
                                + potenciaObjectiu + " Actual: " + potenciaActual);
                    }
                    if (potenciaActual == potenciaObjectiu) {
                        System.out.println("Motor " + id + ": FerRes Objectiu: "
                                + potenciaObjectiu + " Actual: " + potenciaActual);
                        if (potenciaObjectiu == 0) {
                            return; // objectiu 0 assolit: el motor surt
                        }
                    }
                }
            }
        } catch (InterruptedException e) {
            
        }
    }
}