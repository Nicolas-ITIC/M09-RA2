public class PrincipalEstricta {

    public static void main(String[] args) {

        Control control = new Control("Juan");

        Fil juan = new Fil(
            "Juan",
            Thread.NORM_PRIORITY,
            true,
            control,
            1
        );

        Fil pepe = new Fil(
            "Pepe",
            Thread.NORM_PRIORITY,
            true,
            control,
            1
        );

        // Iniciamos primero Juan y después Pepe
        juan.start();
        pepe.start();

        System.out.println("Termina thread main");
    }
}
