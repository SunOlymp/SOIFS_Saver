import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class GameProgress implements Serializable {
    private static final long serialVersionUID = 1L;

    private int health;
    private int weapons;
    private int lvl;
    private double distance;

    public GameProgress(int health, int weapons, int lvl, double distance) {
        this.health = health;
        this.weapons = weapons;
        this.lvl = lvl;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "GameProgress{" +
                "health=" + health +
                ", weapons=" + weapons +
                ", lvl=" + lvl +
                ", distance=" + distance +
                '}';
    }

    public void saveGame(String directory, GameProgress gameProgress) {
        File saveProgress = new File(directory);
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            fos = new FileOutputStream(saveProgress);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(gameProgress);
        } catch (IOException ex) {
            System.out.println(ex.getStackTrace());
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException ex) {
                System.out.println(ex.getStackTrace());
            }
        }
    }

    public void fileToZip(String directory, List<String> nameZip) {

        try (FileOutputStream fileOut = new FileOutputStream(directory);
             ZipOutputStream zipOut = new ZipOutputStream(fileOut)) {
            for (String fileToZip : nameZip) {
                File file = new File(fileToZip);
                FileInputStream fileIn = new FileInputStream(file);
                ZipEntry zipEntry = new ZipEntry(file.getName());
                zipOut.putNextEntry(zipEntry);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = fileIn.read(buffer)) > 0) {
                    zipOut.write(buffer, 0, length);
                }
                fileIn.close();
                zipOut.closeEntry();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fileToDelete(List<String> fileListToDelete) {

        File savegamesFolder = new File("/Users/olymp/Games/savegames/");
        File[] files = savegamesFolder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (fileListToDelete.contains(file.getAbsolutePath())) {
                    if (file.delete()) {
                        System.out.println("Файл " + file.getName() + " успешно удалён");
                    } else {
                        System.out.println("Ошибка удаления файла " + file.getName());
                    }
                }
            }
        }
    }
}