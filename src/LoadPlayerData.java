import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LoadPlayerData 
{
    public static PlayerData loadPlayerData(String filename)
    {
        PlayerData PD = null;
        try (FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);)
            {
                PD = (PlayerData) in.readObject();
                System.out.println("loaded from "+filename);
            }
            catch (IOException | ClassNotFoundException i)
            {
                i.printStackTrace();
            }
            return PD;
    }
}
