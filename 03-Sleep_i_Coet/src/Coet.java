import java.util.Scanner;

public class Coet {

    private final Motor[] motors = new Motor[4];

    public Coet() {
        for (int i = 0; i < motors.length; i++) {
            motors[i] = new Motor(i);
        }
    }

    public void passaAPotencia(int p) {
        if (p < 0 || p > 10) {
            throw new IllegalArgumentException("Potència incorrecta: ha d'estar entre 0 i 10");
        }
        System.out.println("Passant a potència " + p);
        for (Motor m : motors) {
            m.setPotencia(p);
        }
    }

    public void arranca() {
        for (Motor m : motors) {
            m.start();
        }
    }

    public void atura() throws InterruptedException {
        for (Motor m : motors) {
            m.acaba();
        }
        for (Motor m : motors) {
            m.join();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Coet coet = new Coet();
        coet.arranca();

        Scanner sc = new Scanner(System.in);
        int p;
        do {
            p = sc.nextInt();
            try {
                coet.passaAPotencia(p);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                p = -1;
            }
        } while (p != 0);

        coet.atura();
    }
}