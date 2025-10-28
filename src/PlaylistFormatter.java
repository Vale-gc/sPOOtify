//Clase que da formato
public class PlaylistFormatter {
    public static String getDurationFormated(int seconds) {
        int hours = seconds / 3600;
        seconds %= 3600;
        int minutes = seconds / 60;
        seconds %= 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public static String getDescription(SpootifyPlaylist playlist) {
        String title = playlist.getTitle();
        if (title.equals("library")) title = "Biblioteca";

        return String.format(
            "Título de la Playlist: %s\nDuración: %s\nContenidos: %d",
            title,
            getDurationFormated(playlist.getDuration()),
            playlist.getContentList().size()
        );
    }
}

