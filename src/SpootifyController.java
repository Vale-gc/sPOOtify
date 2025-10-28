import java.util.*;

public class SpootifyController {
    private SpootifyUI ui;
    private SpootifyMenu menu;

    private Map<Integer, ContentFactory> factories = new HashMap<>();

    public SpootifyController(SpootifyUI ui, SpootifyMenu menu){
        this.ui = ui;
        this.menu = menu;

        // Registramos las fábricas (OCP)
        factories.put(1, new MusicFactory());
        factories.put(2, new PodcastFactory());
        // factories.put(3, new AudiobookFactory());
    }

    public void addContentToLibrary(){
        int choice = ui.askContentType();
        ContentFactory factory = factories.get(choice);

        if(factory == null){
            System.out.println("Tipo inválido!");
            ui.pressEnter();
            return;
        }

        SpootifyContent content = factory.createContent(ui.getScanner());
        menu.getPlaylist("library").addContent(content);
        System.out.println("✅ Contenido agregado con éxito!");
        ui.pressEnter();
    }

    public void showLibrary(){
        List<SpootifyContent> list = menu.getPlaylist("library").getContentList();
        if(list.isEmpty()){
            System.out.println("Biblioteca vacía :(");
        } else {
            System.out.println("=== Contenidos en la biblioteca ===");
            list.forEach(System.out::println);
        }
        ui.pressEnter();
    }
}