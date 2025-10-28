import java.util.ArrayList;
import java.util.List;

//Clase que filtra el contenido de las playlists
public class PlaylistFilter {
    //Método que cumple con OCP y DIP
    /*El anterior violaba OCP porque cada nueva extensión del contenido (tal vez un nuevo tipo de
    multimedia) obligaba a modificar el código ya existente en este método.
     * Y violaba DIP porque dependía de clases concretas (Music, Podcast) y no de abstracciones. */
    public static List<SpootifyContent> filterBy(SpootifyPlaylist playlist, List<String> types) {
        List<SpootifyContent> filtered = new ArrayList<>();

        for (SpootifyContent c : playlist.getContentList()) {
            if (types.contains(c.getType())) {
                filtered.add(c);
            }
        }

        return filtered;
    }
}

