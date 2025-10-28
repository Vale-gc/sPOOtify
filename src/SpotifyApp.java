import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class SpotifyApp {
    

    public static void addToPlaylist(Scanner dataScanner, String playlistTitle, SpootifyMenu menu){
        boolean wannaAdd = true;
        int actualCommand;
        while(wannaAdd){
            clearTerminal();
            printSpootifyLogo();
            System.out.println("Que tipo de conteúdo deseja adicionar?");
            System.out.println("==================================================================================+++---");
            System.out.println("1. Música | 2. Podcast | 3. Audiobook");
            actualCommand = dataScanner.nextInt();
            dataScanner.nextLine();
            
            switch (actualCommand) {
                case 1:
                    try{
                        menu.getPlaylist(playlistTitle).addContent(createMusic(dataScanner));
                        System.out.println("Música adicionada com sucesso!");
                    }catch(InputMismatchException e){
                        clearTerminal();
                        System.out.println("Entrada inválida!\nNão foi possível adicionar o conteúdo!\nPressione ENTER para continuar");
                        dataScanner.nextLine();
                        clearTerminal();
                    }
                    break;
                    
                
                case 2:
                    try{
                        menu.getPlaylist(playlistTitle).addContent(createPodcast(dataScanner));
                        System.out.println("Podcast adicionado com sucesso!");
                    }catch(InputMismatchException e){
                        clearTerminal();
                        System.out.println("Entrada inválida!\nNão foi possível adicionar o conteúdo!\nPressione ENTER para continuar");
                        dataScanner.nextLine();
                        clearTerminal();   
                    }
                    break;
                
                case 3:
                    try{
                        menu.getPlaylist(playlistTitle).addContent(createAudioook(dataScanner));
                        System.out.println("Audiobook adicionado com sucesso!");
                    }catch(InputMismatchException e){
                        clearTerminal();
                        System.out.println("Entrada inválida!\nNão foi possível adicionar o conteúdo!\nPressione ENTER para continuar");
                        dataScanner.nextLine();
                        clearTerminal();
                    }
                    break;
                }
                
            System.out.println("==================================================================================+++---");
            System.out.println("Deseja adicionar mais um conteúdo?");
            System.out.println("==================================================================================+++---");
            System.out.println("1. Sim | 2. Voltar ao menu");
            actualCommand = dataScanner.nextInt();
            dataScanner.nextLine();
            if(actualCommand != 1) wannaAdd = false;
        }
    }

    public static void showPlaylistContent(Scanner dataScanner, String playlistTitle, SpootifyMenu menu){
        List<SpootifyContent> filteredList = new ArrayList<SpootifyContent>();
        int actualCommand;
        int counter;

        clearTerminal();
        System.out.println("O que deseja listar?");
        System.out.println("==================================================================================+++---");
        System.out.println("1. Músicas | 2. Podcasts | 3. Audiobooks | 4. Todos");
        System.out.println("==================================================================================+++---");
        actualCommand = dataScanner.nextInt();
        dataScanner.nextLine();

        switch(actualCommand){
            case 1:
                filteredList = PlaylistFilter.filterBy(menu.getPlaylist(playlistTitle), List.of("music"));
                break;
            case 2:
                filteredList = PlaylistFilter.filterBy(menu.getPlaylist(playlistTitle), List.of("podcast", "audiobook"));
                break;
            case 3:
                filteredList = PlaylistFilter.filterBy(menu.getPlaylist(playlistTitle), List.of("audiobook"));
                break;
            case 4:
                filteredList = menu.getPlaylist(playlistTitle).getContentList();
                break;
        }

        counter = 1;

        clearTerminal();
        if(!filteredList.isEmpty()){
            System.out.println("==================================================================================+++---");
            System.out.println("Esses são seus conteúdos");
            System.out.println("==================================================================================+++---");
            for (SpootifyContent spootifyContent : filteredList){
                System.out.printf("%d | %s\n", counter, spootifyContent.toString());
                counter += 1;
            }
        }else{
            System.out.println("Não existem conteúdos que atendam esse requisito :(");
        }
        pressEnter(dataScanner);

    }

    public static void createPlaylist(Scanner dataScanner, SpootifyMenu menu){
        String playlistTitle;

        printSpootifyLogo();
        System.out.println("==================================================================================+++---");
        System.out.println("Digite o nome da playlist");
        System.out.println("==================================================================================+++---");
        playlistTitle = dataScanner.nextLine();
        
        if(!menu.playlistExists(playlistTitle)){
            menu.addPlaylist(playlistTitle);
            System.out.println("A playlist " + playlistTitle + " foi criada com sucesso!\n ");
        } else{
            System.out.println("Já existe uma playlist chamada " + playlistTitle + "!");
        }

        pressEnter(dataScanner);
    }
    
    public static void deletePlaylist(Scanner dataScanner, SpootifyMenu menu){
        String playlistTitle;

        printSpootifyLogo();
        System.out.println("==================================================================================+++---");
        System.out.println("Digite o nome da playlist");
        System.out.println("==================================================================================+++---");
        playlistTitle = dataScanner.nextLine();
        
        if(menu.playlistExists(playlistTitle) && playlistTitle != "library"){
            menu.removePlaylist(playlistTitle);
            System.out.println("A playlist " + playlistTitle + " foi removida com sucesso!\n ");
        } else if(playlistTitle == "library")
            System.out.println("Não é possivel apagar a biblioteca!");
        else
            System.out.println("Não existe uma playlist chamada " + playlistTitle + "!");

        pressEnter(dataScanner);
    }

    public static void showPlaylists(Scanner dataScanner, SpootifyMenu menu){
        int counter =1;
        
        if(menu.getPlaylists().size() > 1){
            System.out.println("==================================================================================+++---");
            System.out.println("Essas são suas playlists");
            System.out.println("==================================================================================+++---");


            for(String playlistTitle : menu.getPlaylists().keySet()){
                if(playlistTitle != "library"){
                    System.out.printf("%d | %s\n", counter, playlistTitle);
                    counter += 1;
                }
            }
        }else{
            System.out.println("Você não possui playlists :(");
        }

        pressEnter(dataScanner);
    }

    public static void main(String[] args){
        SpootifyMenu myMenu = new SpootifyMenu();
        Scanner dataScanner = new Scanner(System.in);
        int actualCommand = 0;
        String playlistTitle;

        // Criando playlist com dados do usuário
        SpootifyUI.clearTerminal();
        SpootifyUI.printSpootifyLogo();
        System.out.println("Olá, seja bem vindo ao sPOOtify!\n");
        SpootifyUI.pressEnter(dataScanner);

        while(actualCommand != 8){
            SpootifyUI.clearTerminal();
            SpootifyUI.printSpootifyLogo();
            System.out.println("Selecione uma opção");
            System.out.println("==================================================================================+++---");
            System.out.println("1. Preencher a sua Biblioteca           | 2. Listar os conteúdos da Bilioteca\n3. Exibir informações da Biblioteca     | 4. Criar uma nova Playlist");
            System.out.println("5. Excluir uma Playlist                 | 6. Adicionar conteúdo a Playlist\n7. Listar Playlists                     | 8. Sair");
            System.out.println("==================================================================================+++---");
            actualCommand = dataScanner.nextInt();
            dataScanner.nextLine();

            SpootifyUI.clearTerminal();
            SpootifyUI.printSpootifyLogo();

            switch(actualCommand){
                // Adicionar conteúdo a biblioteca
                case 1:
                    addToPlaylist(dataScanner, "library", myMenu);
                    break;

                // Listar conteúdos da biblioteca
                case 2:
                    showPlaylistContent(dataScanner, "library", myMenu);
                    break;

                // Exibir informações da biblioteca
                case 3:
                    System.out.println(PlaylistFormatter.getDescription(myMenu.getPlaylist("library")));
                    pressEnter(dataScanner);
                    break;
                
                // Criar nova playlist
                case 4:
                    try{
                        createPlaylist(dataScanner, myMenu);
                    }catch(InputMismatchException e){
                        System.out.println("Entrada inválida!\nNão foi possível adicionar o conteúdo!\nPressione ENTER para continuar");
                        dataScanner.nextLine();
                        clearTerminal();
                    }
                    break;
                
                // Criar nova playlist
                case 5:
                    deletePlaylist(dataScanner, myMenu);
                    break;
                
                // Adicionar a uma playlist
                case 6:
                    System.out.println("==================================================================================+++---");
                    System.out.println("Digite o nome da playlist");
                    System.out.println("==================================================================================+++---");
                    playlistTitle = dataScanner.nextLine();
                    if(myMenu.playlistExists(playlistTitle)) addToPlaylist(dataScanner, playlistTitle, myMenu);
                    else{
                        System.out.println("Essa playlist não existe!");
                        pressEnter(dataScanner);
                        clearTerminal();
                    }
                    break;

                // Listando playlists
                case 7:
                    showPlaylists(dataScanner, myMenu);
                    break;

                // Sair
                case 8:
                    System.out.println("Saindo...");
                    break;
            }
        }
        closeScanner(dataScanner);
    }
}
