import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PodcastFactory implements ContentFactory {
@Override
    public SpootifyContent createContent(Scanner scanner){
        scanner.nextLine(); // limpiar buffer
        System.out.println("Título:");
        String title = scanner.nextLine();

        System.out.println("Duración:");
        int duration = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Género:");
        String genre = scanner.nextLine();

        System.out.println("Compositores (separa con ';'):");
        List<String> songwriters = Arrays.asList(scanner.nextLine().split(";"));

        System.out.println("Intérpretes (separa con ';'):");
        List<String> interpreters = Arrays.asList(scanner.nextLine().split(";"));

        return new SpootifyMusic(title, duration, songwriters, interpreters, genre);
    }
}
