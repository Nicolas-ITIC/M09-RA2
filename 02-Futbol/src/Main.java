public class Main {

    public static void main(String[] args) throws InterruptedException {

        String[] noms = {"Piqué", "Vinicius", "Torres", "Ramos", "Ronaldo",
                         "Lewan", "Belli", "Arnau", "Aspas", "Messi", "MBapé"};

        Futbolista[] equip = new Futbolista[Futbolista.NUM_JUGADORS];
        for (int i = 0; i < equip.length; i++) {
            equip[i] = new Futbolista(noms[i]);
        }

        System.out.println("Inici dels xuts --------------------");

        for (Futbolista f : equip) {
            f.start();
        }

        for (Futbolista f : equip) {
            f.join();
        }

        System.out.println("Fi dels xuts -----------------------");

        System.out.println("--- Estadístiques ------");
        for (Futbolista f : equip) {
            System.out.printf("%-8s -> %2d gols%n", f.getName(), f.getNgols());
        }
    }
}