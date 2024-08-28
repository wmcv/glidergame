import java.io.Serializable;

public class PlayerData implements Serializable
{
    private int creds, highScore;
    private int[][] abilitiesUnlocked;
    private int[][] skinsUnlocked;
    private int abilEquippedX, abilEquippedY; 

    private static final long serialVersionUID = 1L;

    public PlayerData(int storeLengthX, int storeLengthY)
    {
        abilEquippedX = 0;
        abilEquippedX = 0;
        creds = 0;
        highScore = 0;
        abilitiesUnlocked = new int[storeLengthX][storeLengthY];
        skinsUnlocked = new int[storeLengthX][storeLengthY];
    }

    public int getCreds()
    {
        return creds;
    }

    public void setCreds(int creds)
    {
        this.creds = creds;
    }

    public void addCreds(int creds)
    {
        this.creds += creds;
    }

    public void removeCreds(int creds)
    {
        this.creds -= creds;
    }

    public int getHighScore()
    {
        return highScore;
    }

    public void setHighScore(int highScore)
    {
        this.highScore = highScore;
    }

    public int getAbilEquippedX()
    {
        return abilEquippedX;
    }

    public void setAbilEquippedX(int abilEquippedX)
    {
        this.abilEquippedX = abilEquippedX;
    }

    public int getAbilEquippedY()
    {
        return abilEquippedY;
    }

    public void setAbilEquippedY(int abilEquippedY)
    {
        this.abilEquippedY = abilEquippedY;
    }

    public int[][] getAbilitiesUnlocked()
    {
        return abilitiesUnlocked;
    }

    public int[][] getSkinsUnlocked()
    {
        return skinsUnlocked;
    }

    public void setAbilitiesUnlocked(int[][] aU)
    {
        abilitiesUnlocked = aU;
    }

    public void setSkinsUnlocked(int[][] sU)
    {
        skinsUnlocked = sU;
    }





}
