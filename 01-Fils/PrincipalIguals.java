public class PrincipalIguals {

    public static void main(String[] args) {

        Fil pepe = new Fil(
            "Pepe",
            Thread.NORM_PRIORITY,
            false,
            null,
            0
        );

        Fil juan = new Fil(
            "Juan",
            Thread.NORM_PRIORITY,
            false,
            null,
            0
        );

        pepe.start();
        juan.start();

        System.out.println("Acaba thread main");
    }
}