import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SavePlayerData 
{   
    public static void savePlayerData(PlayerData PD, String filename)
    {
        try (FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

        ) {
            out.writeObject(PD);
            System.out.println("saved to "+filename);
        }
        catch (IOException i)
        {
            i.printStackTrace();
        }
    }

}
