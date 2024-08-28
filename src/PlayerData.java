public class PlayerData {
    private int creds, highScore;
    private Item[][] abilitiesUnlocked;
    private Item[][] skinsUnlocked;
    private int abilEquippedX, abilEquippedY; 

    public PlayerData(int storeLengthX, int storeLengthY)
    {
        abilEquippedX = 0;
        abilEquippedX = 0;
        creds = 0;
        highScore = 0;
        abilitiesUnlocked = new Item[storeLengthX][storeLengthY];
        skinsUnlocked = new Item[storeLengthX][storeLengthY];
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

    public Item[][] getAbilitiesUnlocked()
    {
        return abilitiesUnlocked;
    }

    public Item[][] getSkinsUnlocked()
    {
        return skinsUnlocked;
    }



}
