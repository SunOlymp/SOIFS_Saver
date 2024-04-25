import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        GameProgress gameProgress1 = new GameProgress(34, 4, 4, 32.9);
        GameProgress gameProgress2 = new GameProgress(68, 15,25,635.4);
        GameProgress gameProgress3 = new GameProgress(49, 31, 99,1666);

        gameProgress1.saveGame("/Users/olymp/Games/savegames/save1.dat", gameProgress1);
        gameProgress2.saveGame("/Users/olymp/Games/savegames/save2.dat", gameProgress2);
        gameProgress3.saveGame("/Users/olymp/Games/savegames/save3.dat", gameProgress3);

        List<String> zipArchive = new ArrayList<>();
        zipArchive.add("/Users/olymp/Games/savegames/save1.dat");
        zipArchive.add("/Users/olymp/Games/savegames/save2.dat");
        zipArchive.add("/Users/olymp/Games/savegames/save3.dat");
        
        gameProgress1.fileToZip("/Users/olymp/Games/savegames/zipGames.zip", zipArchive);
        gameProgress1.fileToDelete(zipArchive);

    }


}