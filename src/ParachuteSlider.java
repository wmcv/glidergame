import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import javax.swing.*;

public class ParachuteSlider extends JPanel //implements ActionListener, KeyListener
{

    //fix fonts
    //add box around items in shop
    // play btn swap quit btn




    //three tiers for abilties 
    //none, heal 1, heal 2
    //shield, forcefield, hex dome
    //points x 1.5, points x 2, points x 2.5
    
    //for immunity, just have a boolean called immunity = true and have it so health can't go down




    //3 create the abilities and skins
    //make apperance more pleasing for example clouds as the boarders on the left and right
    //the clouds could even cross and cover the whole screen from the left and right when you hit play and then reveal ---
    //--- the sky and the transport plane????
    //make how to play
    //make video on game
    //commit all to github
    //project done?

    int boardWidth = 640;
    int boardHeight = 480;
    Random rand = new Random();

    //images
    Image soldierRightImg;
    Image soldierLeftImg;
    Image playerBulletImg;
    Image enemyBulletImg;
    Image backgroundImg;
    Image planeRightImg;
    Image planeLeftImg;
    Image para1Img;
    Image para2Img;
    Image para3Img;
    Image boomImg;
    Image missileImg;
    Image transportImg;
    Image heartImg;
    Image ballImg;
    Image warningImg;
    Image parachuteIconImg;
    Image shopBackground;



    //soldier
    int soldierX = boardWidth/2;
    int soldierY = boardHeight/6;
    int soldierWidth = 40;
    int soldierHeight = 65;
    int soldierAcc = 9;

    //parachute  6, 60
    int parachuteWidth = 70;
    int parachuteHeight = 100;

    //plane
    int planeWidth = 200;
    int planeHeight = 100;
    int planeVelocityX = 6;
    int planeVelocityY = -1;
    int planeHealth = 2;

    //player bullet
    int pBulletWidth = 10;
    int pBulletHeight = 20;
    int pBulletVelocityY = 3;

    //boom
    int boomWidth = 50;
    int boomHeight = 50;
    int boomLifeTime = 20;
    
    //missile
    int missileWidth = 35;
    int missileHeight = 35;
    int missileVelocityY = -6;
    int explosiveWidth = 140;
    int explosiveHeight = 140;
    int maxExplosionHeight = boardHeight;
    int minExplosionHeight = 30;
    //int chanceExplosion = 20; //out of 1000 per tick;
    int explosionLifeTime = 20;
    //1900
    //1900
    int missileDelay = 1900;
    //0.8
    double missileDifficultyGain = 0.8;


    //transport
    int transportX = -300;
    int transportY = -20;
    int transportWidth = 400;
    int transportHeight = 200;
    int transportSpeed = 7;

    //heart
    int heartWidth = 40;
    int heartHeight = 40;

    //barrage
    int ballWidth = 20;
    int ballHeight = 20;
    int ballSpeedFactor = 60;
    //45
    int ballSeperation = 65;
    int bonusBallCount = 10;
    int minBallCount = 12;
    int barrageDelay = 6500;
    int ballDelay = 12;
    double barrageDifficultyGain = 0.8;


    //warning
    int warningWidth = 125;
    int warningHeight = 110;
    int warningDelay = 100;


    //store
    int storeX = 700;
    int storeY = 0;
    int storeWidth = boardWidth/2 + 60;
    int storeHeight = boardHeight;
    int storeLengthX = 3;
    int storeLengthY = 6;
    int ShopBtnFactorX = 20;
    int ShopBtnFactorY = 10;
    int switchShopWidth = 120;
    int switchShopHeight = 20;
    int switchShopShift = 0;
    int closeShopWidth = 80;
    int closeShopHeight = 20;
    int closeShopShift = 140;
    int shopAcc = -1;
    int shopFinalX = boardWidth/2 - 50;
    int shopTempX = (int)(boardWidth*0.9);
    int shopBounce = 0;
    int shopBounceMax = 10;
    double shopBounceSlowDown = 0.7;
    boolean shopSpeedStop = true;
    int shopTypeWidth = 100;
    int shopTypeHeight = 20;
    int shopTypeShift = 240;
    int JTPaneWidth = 80;
    int JTPaneHeight = 15;


    //item
    int itemBtnWidth = 80; 
    int itemBtnHeight = 15;
    int itemBtnNoDealDelay = 80;
    int itemBtnDrop = 45;
    int itemWidth = 40; 
    int itemHeight = 40; 
    int itemFactorX = 110; //100
    int itemFactorY = 75; //80
    int itemShiftX = 35; //10  55
    int itemShiftY = 42; //40  25











    //logic
    PlayerData PD;
    int health = 3;
    int score = 0;
    //int highScore = 0;
    int creds = 0;
    double difficultyCredsMultiplier = 1.0;
    double difficultyCredsIncrease = 0.3;
    Soldier soldier;
    int velocityX = 0;
    ArrayList<Plane> planes;
    Set<Integer> keyHeld = new HashSet<>();
    Timer gameLoop;
    Parachute parachute;
    ArrayList<PlayerBullet> bullets;
    Timer placePlaneTimer;
    ArrayList<Boom> booms;
    Timer placeMissileTimer;
    ArrayList<Missile> missiles;
    ArrayList<Explosion> explosives;
    ArrayList<Heart> hearts;
    Transport transport;
    boolean start = false;
    boolean beginAnimation = false;
    int transCounter = 0;
    int transCounterMax = 50;
    boolean loadingScreen = true;
    JTextPane credits;
    JTextPane highestScore;
    int statsWidth = 110;
    int statsHeight = 20;
    int statsX = 10;
    int statsY = 10;
    double abilMultiplier;

    //buttons
     JButton playBtn;
     JButton returnHomeBtn;
     JButton quitBtn;
     JButton playAgainBtn;
     JButton shopOpenBtn;
     JButton shopCloseBtn;
     JButton switchShopBtn;
     JTextPane shopType;
     JTextPane JComp;

    //shop
     Shop shop;
    ArrayList<ItemBtn> itemBtns;
     boolean openShop = false;
     boolean closeShop = false;

    //timer for barrage
     ArrayList<Ball> barrage;
     Timer newBarrageTimer;
     ArrayList<Warning> warnings;
    ArrayList<Ball> tempBarrage;
     AppListener appListener;
    

     //save and load buttons
     JButton saveGame;
     JButton loadGame;
     String filename = "Testing12";

    
    //vscode audio is broken :(

    /*
    Clip clip;
    public void PlayMusic()
    {
        try
        {
            File musicPath = new File("machine-gun-O2.wav");
            if (musicPath.exists())
            {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            }
            else
            {
                System.out.println("cant find file");
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    */
    //



    //ABILITIES
    boolean abilActive;
    boolean immunity;
    int abilCountDown;
    boolean finishedAbil;

    Item abilDefault;
    Image abilDefaultImg;
    boolean abilBooDefault;

    Item abilHealth1;
    Image abilHealth1Img;
    boolean abilBooHealth1;

    Item abilHealth2;
    Image abilHealth2Img;
    boolean abilBooHealth2;

    Item abilShield1;
    Image abilShield1Img;
    boolean abilBooShield1;

    Item abilShield2;
    Image abilShield2Img;
    boolean abilBooShield2;

    Item abilShield3;
    Image abilShield3Img;
    boolean abilBooShield3;

    Item abilPoints1;
    Image abilPoints1Img;
    boolean abilBooPoints1;

    Item abilPoints2;
    Image abilPoints2Img;
    boolean abilBooPoints2;

    Item abilPoints3;
    Image abilPoints3Img;
    boolean abilBooPoints3;


    //SKINS
    //Soldier
    Item soldierDefault;
    Image soldierLeftDefault;
    Image soldierRightDefault;

    Item soldierMeme;
    Image soldierLeftMeme;
    Image soldierRightMeme;

    Item soldierHell;
    Image soldierLeftHell;
    Image soldierRightHell;

    //Plane
    Item planeDefault;
    Image planeLeftDefault;
    Image planeRightDefault;

    Item planeMeme;
    Image planeLeftMeme;
    Image planeRightMeme;

    Item planeHell;
    Image planeLeftHell;
    Image planeRightHell;

    //Cannon
    Item cannonDefault;
    Image cannonLeftDefault;

    Item cannonMeme;
    Image cannonLeftMeme;

    Item cannonHell;
    Image cannonLeftHell;

    //Turret
    Item turretDefault;
    Image turretLeftDefault;

    Item turretMeme;
    Image turretLeftMeme;

    Item turretHell;
    Image turretLeftHell;

    //Explosives
    Item explosiveDefault;
    Image explosiveLeftDefault;
    Image explosiveRightDefault;

    Item explosiveMeme;
    Image explosiveLeftMeme;
    Image explosiveRightMeme;

    Item explosiveHell;
    Image explosiveLeftHell;
    Image explosiveRightHell;

    //Sky
    Item skyDefault;
    Image skyLeftDefault;

    Item skyMeme;
    Image skyLeftMeme;

    Item skyHell;
    Image skyLeftHell;








    

    ParachuteSlider()
    {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        appListener = new AppListener(this, this);
        //if revert undo these below
        setFocusable(true);
        addKeyListener(appListener);

        //load images 

        //DEFAULT IMAGES
        backgroundImg = new ImageIcon(getClass().getResource("./skyLine.png")).getImage();
        soldierRightImg = new ImageIcon(getClass().getResource("./soldierRight.png")).getImage();
        soldierLeftImg = new ImageIcon(getClass().getResource("./soldierLeft.png")).getImage();
        playerBulletImg = new ImageIcon(getClass().getResource("./bullety.png")).getImage();
        enemyBulletImg = new ImageIcon(getClass().getResource("./cannonBall.png")).getImage();
        planeRightImg = new ImageIcon(getClass().getResource("./planeRight.png")).getImage();
        planeLeftImg = new ImageIcon(getClass().getResource("./planeLeft.png")).getImage();
        para1Img = new ImageIcon(getClass().getResource("./para1.png")).getImage();
        para2Img = new ImageIcon(getClass().getResource("./para2.png")).getImage();
        para3Img = new ImageIcon(getClass().getResource("./para3.png")).getImage();
        boomImg = new ImageIcon(getClass().getResource("./boom.png")).getImage();
        missileImg = new ImageIcon(getClass().getResource("./missile.png")).getImage();
        transportImg = new ImageIcon(getClass().getResource("./transport.png")).getImage();
        heartImg = new ImageIcon(getClass().getResource("./heart.png")).getImage();
        ballImg = new ImageIcon(getClass().getResource("./ball.png")).getImage();
        warningImg = new ImageIcon(getClass().getResource("./warning.png")).getImage();
        parachuteIconImg = new ImageIcon(getClass().getResource("./parachuteIcon.png")).getImage();
        shopBackground = new ImageIcon(getClass().getResource("./gray.png")).getImage();



        //ABILITIES

        abilDefaultImg = new ImageIcon(getClass().getResource("./abilNone.png")).getImage();
        abilHealth1Img = new ImageIcon(getClass().getResource("./abilHealth1.png")).getImage();
        abilHealth2Img = new ImageIcon(getClass().getResource("./abilHealth2.png")).getImage();
        abilShield1Img = new ImageIcon(getClass().getResource("./abilShield1.png")).getImage();
        abilShield2Img = new ImageIcon(getClass().getResource("./abilShield2.png")).getImage();
        abilShield3Img = new ImageIcon(getClass().getResource("./abilShield3.png")).getImage();
        abilPoints1Img = new ImageIcon(getClass().getResource("./abilPoints1.png")).getImage();
        abilPoints2Img = new ImageIcon(getClass().getResource("./abilPoints2.png")).getImage();
        abilPoints3Img = new ImageIcon(getClass().getResource("./abilPoints3.png")).getImage();


        









        //SKINS

        //Soldier
        soldierLeftDefault = new ImageIcon(getClass().getResource("./soldierLeft.png")).getImage();
        soldierRightDefault = new ImageIcon(getClass().getResource("./soldierRight.png")).getImage();

        soldierLeftMeme = new ImageIcon(getClass().getResource("./soldierLeftMeme.png")).getImage();
        soldierRightMeme = new ImageIcon(getClass().getResource("./soldierRightMeme.png")).getImage();

        soldierLeftHell = new ImageIcon(getClass().getResource("./soldierLeftHell.png")).getImage();
        soldierRightHell = new ImageIcon(getClass().getResource("./soldierRightHell.png")).getImage();

        //Plane
        planeLeftDefault = new ImageIcon(getClass().getResource("./planeLeft.png")).getImage();
        planeRightDefault = new ImageIcon(getClass().getResource("./planeRight.png")).getImage(); 

        planeLeftMeme = new ImageIcon(getClass().getResource("./planeLeftMeme.png")).getImage();
        planeRightMeme = new ImageIcon(getClass().getResource("./planeRightMeme.png")).getImage();

        planeLeftHell = new ImageIcon(getClass().getResource("./planeLeftHell.png")).getImage();
        planeRightHell = new ImageIcon(getClass().getResource("./planeRightHell.png")).getImage();

        //Cannon
        cannonLeftDefault = new ImageIcon(getClass().getResource("./cannonBall.png")).getImage();
        
        cannonLeftMeme = new ImageIcon(getClass().getResource("./cannonLeftMeme.png")).getImage();
        
        cannonLeftHell = new ImageIcon(getClass().getResource("./cannonLeftHell.png")).getImage();
        
        //Turret
        turretLeftDefault = new ImageIcon(getClass().getResource("./ball.png")).getImage();
        
        turretLeftMeme = new ImageIcon(getClass().getResource("./turretLeftMeme.png")).getImage();

        turretLeftHell = new ImageIcon(getClass().getResource("./turretLeftHell.png")).getImage();

        //Explosives
        explosiveLeftDefault = new ImageIcon(getClass().getResource("./boom.png")).getImage();
        explosiveRightDefault = new ImageIcon(getClass().getResource("./missile.png")).getImage();

        explosiveLeftMeme = new ImageIcon(getClass().getResource("./explosiveLeftMeme.png")).getImage();
        explosiveRightMeme = new ImageIcon(getClass().getResource("./explosiveRightMeme.png")).getImage();

        explosiveLeftHell = new ImageIcon(getClass().getResource("./explosiveLeftHell.png")).getImage();
        explosiveRightHell = new ImageIcon(getClass().getResource("./explosiveRightHell.png")).getImage();

        //Sky
        skyLeftDefault = new ImageIcon(getClass().getResource("./skyLine.png")).getImage();
        
        skyLeftMeme = new ImageIcon(getClass().getResource("./skyLeftMeme.png")).getImage();

        skyLeftHell = new ImageIcon(getClass().getResource("./skyLeftHell.png")).getImage();






        //testing
        
        //shop.abilities[0][0] = item1;
        




        //buttons
        this.setLayout(null);

        
        
        //play button
        playBtn = new JButton("Play");
        playBtn.addActionListener(new AppListener(this, this));
        playBtn.setBounds(boardWidth - 410, boardHeight -120, 180, 35);
        playBtn.setFocusable(false);
        add(playBtn);
        

        //return home button
        returnHomeBtn = new JButton("Return Home");
        returnHomeBtn.addActionListener(new AppListener(this, this));
        returnHomeBtn.setBounds(boardWidth/2 -70, boardHeight/2, 140, 35);
        returnHomeBtn.setFocusable(false);
        add(returnHomeBtn);
        returnHomeBtn.setVisible(false);

        //quit button
        quitBtn = new JButton("Quit");
        quitBtn.addActionListener(new AppListener(this, this));
        quitBtn.setBounds(boardWidth - 405-5, boardHeight -50 , 180, 35);
        quitBtn.setFocusable(false);
        add(quitBtn);
        //boardWidth - 405-5, boardHeight -50 , 180, 40
        //boardWidth - 357, boardHeight-120 , 75, 35
        //boardWidth - 410, boardHeight -120, 180, 40

        //play again button
        playAgainBtn = new JButton("Play Again");
        playAgainBtn.addActionListener(new AppListener(this, this));
        playAgainBtn.setBounds(boardWidth/2 - 80, boardHeight/2 - 35, 160, 35);
        playAgainBtn.setFocusable(false);
        add(playAgainBtn);
        playAgainBtn.setVisible(false);

        //shop open button
        shopOpenBtn = new JButton("Shop");
        shopOpenBtn.addActionListener(new AppListener(this, this));   //110
        shopOpenBtn.setBounds(boardWidth - 410, boardHeight - 85, 180, 35);
        shopOpenBtn.setFocusable(false);
        add(shopOpenBtn);


        //saveGame
        saveGame = new JButton("Save Game");
        saveGame.addActionListener(new AppListener(this, this));   //110
        saveGame.setBounds(boardWidth - 110, boardHeight - 35, 100, 35);
        saveGame.setFocusable(false);
        add(saveGame);

        //loadGame
        loadGame = new JButton("Load Game");
        loadGame.addActionListener(new AppListener(this, this));   //110
        loadGame.setBounds(boardWidth - 110, boardHeight - 70, 100, 35);
        loadGame.setFocusable(false);
        add(loadGame);



        //shop close button
        /* 
        shopCloseBtn = new JButton("Close Shop");
        shopCloseBtn.addActionListener(this);
        shopCloseBtn.setBounds(boardWidth/2 - 80, boardHeight/2 - 35, 160, 35);
        shopCloseBtn.setFocusable(false);
        add(shopCloseBtn);
        */


        
        shop = new Shop(shopBackground, storeX, storeY, storeWidth, storeHeight, shopAcc, ShopBtnFactorX, ShopBtnFactorY, switchShopShift, switchShopWidth,
        switchShopHeight, closeShopShift, closeShopWidth, closeShopHeight, shopTypeShift, shopTypeWidth, shopTypeHeight, storeLengthX, storeLengthY, this, appListener, this);
        //item1 = new Item();
        //item2 = new Item(missileImg, "balls", 10, 1, 0, 100);
        //shop.abilities[0][0] = item1;
        //shop.abilities[1][0] = item2;

        PD = new PlayerData(storeLengthX, storeLengthY);
        soldier = new Soldier(soldierRightImg, soldierX, soldierY, soldierWidth, soldierHeight, soldierAcc);
        parachute = new Parachute(para1Img, parachuteWidth, parachuteHeight);
        planes = new ArrayList<>();
        bullets = new ArrayList<>();
        booms = new ArrayList<>();
        missiles = new ArrayList<>();
        explosives = new ArrayList<>();
        transport = new Transport(transportImg, transportX, transportY, transportWidth, transportHeight, transportSpeed);
        hearts = new ArrayList<>();
        barrage = new ArrayList<>();
        warnings = new ArrayList<>();
        tempBarrage = new ArrayList<>();
        itemBtns = new ArrayList<>();


        credits = new JTextPane();
        credits.setText("Credits: "+creds);
        credits.setBackground(Color.LIGHT_GRAY);
        credits.setForeground(Color.BLACK);
        credits.setBounds(statsX, statsY, statsWidth, statsHeight);
        add(credits);

        highestScore = new JTextPane();
        highestScore.setText("High Score: "+PD.getHighScore());
        highestScore.setBackground(Color.LIGHT_GRAY);
        highestScore.setForeground(Color.BLACK);
        highestScore.setBounds(statsX, statsY + 30, statsWidth, statsHeight);
        add(highestScore);




        abilActive = false;
        immunity = false;
        abilCountDown = 0;
        finishedAbil = false;
        abilMultiplier = 1.0;
        //Abilities
        abilDefault = new Item(abilDefaultImg, "None", -1, 0, 0, 0, shop, soldier, storeX, storeY, itemWidth, itemHeight,
        itemFactorX, itemFactorY, itemShiftX, itemShiftY, itemBtnDrop, itemBtnWidth, itemBtnHeight, JTPaneWidth, JTPaneHeight, this, PD);

        abilHealth1 = new Item(abilHealth1Img, "Heart Gain", 1, 1, 0, 150, shop, soldier, storeX, storeY, itemWidth, itemHeight,
        itemFactorX, itemFactorY, itemShiftX, itemShiftY, itemBtnDrop, itemBtnWidth, itemBtnHeight, JTPaneWidth, JTPaneHeight, this, PD);

        abilHealth2 = new Item(abilHealth2Img, "Double Heart Gain", 1, 2, 0, 300, shop, soldier, storeX, storeY, itemWidth, itemHeight,
        itemFactorX, itemFactorY, itemShiftX, itemShiftY, itemBtnDrop, itemBtnWidth, itemBtnHeight, JTPaneWidth, JTPaneHeight, this, PD);

        abilShield1 = new Item(abilShield1Img, "Shield", 180, 0, 1, 150, shop, soldier, storeX, storeY, itemWidth, itemHeight,
        itemFactorX, itemFactorY, itemShiftX, itemShiftY, itemBtnDrop, itemBtnWidth, itemBtnHeight, JTPaneWidth, JTPaneHeight, this, PD);

        abilShield2 = new Item(abilShield2Img, "Force Field", 360, 1, 1, 300, shop, soldier, storeX, storeY, itemWidth, itemHeight,
        itemFactorX, itemFactorY, itemShiftX, itemShiftY, itemBtnDrop, itemBtnWidth, itemBtnHeight, JTPaneWidth, JTPaneHeight, this, PD);

        abilShield3 = new Item(abilShield3Img, "Hex Dome", 540, 2, 1, 500, shop, soldier, storeX, storeY, itemWidth, itemHeight,
        itemFactorX, itemFactorY, itemShiftX, itemShiftY, itemBtnDrop, itemBtnWidth, itemBtnHeight, JTPaneWidth, JTPaneHeight, this, PD);

        abilPoints1 = new Item(abilPoints1Img, "x1.5 Points", -1, 0, 2, 150, shop, soldier, storeX, storeY, itemWidth, itemHeight,
        itemFactorX, itemFactorY, itemShiftX, itemShiftY, itemBtnDrop, itemBtnWidth, itemBtnHeight, JTPaneWidth, JTPaneHeight, this, PD);

        abilPoints2 = new Item(abilPoints2Img, "x2.0 Points", -1, 1, 2, 300, shop, soldier, storeX, storeY, itemWidth, itemHeight,
        itemFactorX, itemFactorY, itemShiftX, itemShiftY, itemBtnDrop, itemBtnWidth, itemBtnHeight, JTPaneWidth, JTPaneHeight, this, PD);

        abilPoints3 = new Item(abilPoints3Img, "x2.5 Points", -1, 2, 2, 500, shop, soldier, storeX, storeY, itemWidth, itemHeight,
        itemFactorX, itemFactorY, itemShiftX, itemShiftY, itemBtnDrop, itemBtnWidth, itemBtnHeight, JTPaneWidth, JTPaneHeight, this, PD);

        shop.getAbilities()[0][0] = abilDefault;
        shop.getAbilities()[1][0] = abilHealth1;
        shop.getAbilities()[2][0] = abilHealth2;
        shop.getAbilities()[0][1] = abilShield1;
        shop.getAbilities()[1][1] = abilShield2;
        shop.getAbilities()[2][1] = abilShield3;
        shop.getAbilities()[0][2] = abilPoints1;
        shop.getAbilities()[1][2] = abilPoints2;
        shop.getAbilities()[2][2] = abilPoints3;

        
        ItemBtn btnItem = shop.getAbilities()[0][0].getBtnItem();
        btnItem.setOwned(true);
        btnItem.getItemBtn().setText("Equipped");







        //Skins

        //soldier
        soldierDefault = new Item(soldierLeftDefault, soldierRightDefault, "Default Plane", "Soldier", 0, 0, 0, shop, soldier, storeX, storeY, itemWidth, itemHeight,
        itemFactorX, itemFactorY, itemShiftX, itemShiftY, itemBtnDrop, itemBtnWidth, itemBtnHeight, JTPaneWidth, JTPaneHeight, this, PD);

        soldierMeme = new Item(soldierLeftMeme, soldierRightMeme, "Toy Soldier", "Soldier", 1, 0, 100, shop, soldier, storeX, storeY, itemWidth, itemHeight,
        itemFactorX, itemFactorY, itemShiftX, itemShiftY, itemBtnDrop, itemBtnWidth, itemBtnHeight, JTPaneWidth, JTPaneHeight, this, PD);

        soldierHell = new Item(soldierLeftHell, soldierRightHell, "Hell Soldier", "Soldier", 2, 0, 200, shop, soldier, storeX, storeY, itemWidth, itemHeight,
        itemFactorX, itemFactorY, itemShiftX, itemShiftY, itemBtnDrop, itemBtnWidth, itemBtnHeight, JTPaneWidth, JTPaneHeight, this, PD);

        shop.getSkins()[0][0] = soldierDefault;
        shop.getSkins()[1][0] = soldierMeme;
        shop.getSkins()[2][0] = soldierHell;


        //planes
        planeDefault = new Item(planeLeftDefault, planeRightDefault, "Default Plane", "Plane", 0, 1, 0, shop, soldier, storeX, storeY, itemWidth, itemHeight,
        itemFactorX, itemFactorY, itemShiftX, itemShiftY, itemBtnDrop, itemBtnWidth, itemBtnHeight, JTPaneWidth, JTPaneHeight, this, PD);

        planeMeme = new Item(planeLeftMeme, planeRightMeme, "Neon Cat Plane", "Plane", 1, 1, 100, shop, soldier, storeX, storeY, itemWidth, itemHeight,
        itemFactorX, itemFactorY, itemShiftX, itemShiftY, itemBtnDrop, itemBtnWidth, itemBtnHeight, JTPaneWidth, JTPaneHeight, this, PD);

        planeHell = new Item(planeLeftHell, planeRightHell, "Hell Plane", "Plane", 2, 1, 200, shop, soldier, storeX, storeY, itemWidth, itemHeight,
        itemFactorX, itemFactorY, itemShiftX, itemShiftY, itemBtnDrop, itemBtnWidth, itemBtnHeight, JTPaneWidth, JTPaneHeight, this, PD);

        shop.getSkins()[0][1] = planeDefault;
        shop.getSkins()[1][1] = planeMeme;
        shop.getSkins()[2][1] = planeHell;

        //Cannon
        cannonDefault = new Item(cannonLeftDefault, cannonLeftDefault, "Default Cannon", "Cannon", 0, 2, 0, shop, soldier, storeX, storeY, itemWidth, itemHeight,
        itemFactorX, itemFactorY, itemShiftX, itemShiftY, itemBtnDrop, itemBtnWidth, itemBtnHeight, JTPaneWidth, JTPaneHeight, this, PD);

        cannonMeme = new Item(cannonLeftMeme, cannonLeftMeme, "Cake Cannon", "Cannon", 1, 2, 100, shop, soldier, storeX, storeY, itemWidth, itemHeight,
        itemFactorX, itemFactorY, itemShiftX, itemShiftY, itemBtnDrop, itemBtnWidth, itemBtnHeight, JTPaneWidth, JTPaneHeight, this, PD);

        cannonHell = new Item(cannonLeftHell, cannonLeftHell, "Hell Cannon", "Cannon", 2, 2, 200, shop, soldier, storeX, storeY, itemWidth, itemHeight,
        itemFactorX, itemFactorY, itemShiftX, itemShiftY, itemBtnDrop, itemBtnWidth, itemBtnHeight, JTPaneWidth, JTPaneHeight, this, PD);

        shop.getSkins()[0][2] = cannonDefault;
        shop.getSkins()[1][2] = cannonMeme;
        shop.getSkins()[2][2] = cannonHell;


        //Turret
        turretDefault = new Item(turretLeftDefault, turretLeftDefault, "Default Turret", "Turret", 0, 3, 0, shop, soldier, storeX, storeY, itemWidth, itemHeight,
        itemFactorX, itemFactorY, itemShiftX, itemShiftY, itemBtnDrop, itemBtnWidth, itemBtnHeight, JTPaneWidth, JTPaneHeight, this, PD);

        turretMeme = new Item(turretLeftMeme, turretLeftMeme, "Cup Cake Turret", "Turret", 1, 3, 100, shop, soldier, storeX, storeY, itemWidth, itemHeight,
        itemFactorX, itemFactorY, itemShiftX, itemShiftY, itemBtnDrop, itemBtnWidth, itemBtnHeight, JTPaneWidth, JTPaneHeight, this, PD);

        turretHell = new Item(turretLeftHell, turretLeftHell, "Hell Turret", "Turret", 2, 3, 200, shop, soldier, storeX, storeY, itemWidth, itemHeight,
        itemFactorX, itemFactorY, itemShiftX, itemShiftY, itemBtnDrop, itemBtnWidth, itemBtnHeight, JTPaneWidth, JTPaneHeight, this, PD);

        shop.getSkins()[0][3] = turretDefault;
        shop.getSkins()[1][3] = turretMeme;
        shop.getSkins()[2][3] = turretHell;

        //Explosive
        explosiveDefault = new Item(explosiveLeftDefault, explosiveRightDefault, "Default Explosive", "Explosive", 0, 4, 0, shop, soldier, storeX, storeY, itemWidth, itemHeight,
        itemFactorX, itemFactorY, itemShiftX, itemShiftY, itemBtnDrop, itemBtnWidth, itemBtnHeight, JTPaneWidth, JTPaneHeight, this, PD);

        explosiveMeme = new Item(explosiveLeftMeme, explosiveRightMeme, "Cake Explosive", "Explosive", 1, 4, 100, shop, soldier, storeX, storeY, itemWidth, itemHeight,
        itemFactorX, itemFactorY, itemShiftX, itemShiftY, itemBtnDrop, itemBtnWidth, itemBtnHeight, JTPaneWidth, JTPaneHeight, this, PD);

        explosiveHell = new Item(explosiveLeftHell, explosiveRightHell, "Hell Explosive", "Explosive", 2, 4, 200, shop, soldier, storeX, storeY, itemWidth, itemHeight,
        itemFactorX, itemFactorY, itemShiftX, itemShiftY, itemBtnDrop, itemBtnWidth, itemBtnHeight, JTPaneWidth, JTPaneHeight, this, PD);

        shop.getSkins()[0][4] = explosiveDefault;
        shop.getSkins()[1][4] = explosiveMeme;
        shop.getSkins()[2][4] = explosiveHell;

        //Turret
        skyDefault = new Item(skyLeftDefault, skyLeftDefault, "Default Sky", "Sky", 0, 5, 0, shop, soldier, storeX, storeY, itemWidth, itemHeight,
        itemFactorX, itemFactorY, itemShiftX, itemShiftY, itemBtnDrop, itemBtnWidth, itemBtnHeight, JTPaneWidth, JTPaneHeight, this, PD);

        skyMeme = new Item(skyLeftMeme, skyLeftMeme, "Candy Sky", "Sky", 1, 5, 100, shop, soldier, storeX, storeY, itemWidth, itemHeight,
        itemFactorX, itemFactorY, itemShiftX, itemShiftY, itemBtnDrop, itemBtnWidth, itemBtnHeight, JTPaneWidth, JTPaneHeight, this, PD);

        skyHell = new Item(skyLeftHell, skyLeftHell, "Hell Sky", "Sky", 2, 5, 200, shop, soldier, storeX, storeY, itemWidth, itemHeight,
        itemFactorX, itemFactorY, itemShiftX, itemShiftY, itemBtnDrop, itemBtnWidth, itemBtnHeight, JTPaneWidth, JTPaneHeight, this, PD);

        shop.getSkins()[0][5] = skyDefault;
        shop.getSkins()[1][5] = skyMeme;
        shop.getSkins()[2][5] = skyHell;


        JComp = new JTextPane();
        JComp.setText("");
        JComp.setBackground(Color.WHITE);
        JComp.setForeground(Color.BLACK);
        JComp.setBounds(0, 0, 640, 80);
        JComp.setFocusable(false);
        add(JComp);
        JComp.setVisible(false);

        for (int i = 0; i < storeLengthY; i++)
        {
            ItemBtn btnItemm = shop.getSkins()[0][i].getBtnItem();
            btnItemm.setOwned(true);
            btnItemm.getItemBtn().setText("Equipped");
        }




        for (int i = 1; i <= 3; i++)
        {
            Heart heart = new Heart(heartImg, boardWidth-(heartWidth*(i-1) + (heartWidth/4)*i) -35, 8, heartWidth, heartHeight);
            hearts.add(heart);
        }

        newBarrageTimer = new Timer(barrageDelay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                newBarrage();
                warningCalc();
            }
        });
        newBarrageTimer.stop();



        placeMissileTimer = new Timer(missileDelay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                placeMissile();
            }
            });
        placeMissileTimer.stop();


        placePlaneTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                placePlanes();
            }
        });
        placePlaneTimer.stop();
            

            
        gameLoop = new Timer(1000/60, new AppListener(this, this));
        gameLoop.start();

        //add cloud and birds?
        
    }




  

    //add barrage method

    public void newBarrage()
    {
        for (int i = 0; i < rand.nextInt(bonusBallCount)+minBallCount; i++)
        {
            Ball ball = new Ball(ballImg, ballWidth, ballHeight);
            ball.setDelay(ballDelay*i);// = ballDelay*i;
            tempBarrage.add(ball);
        }
    }

    public void warningCalc()
    {
        int objX = soldier.getX();
        int objY = soldier.getY();
        int randomXSpawn = rand.nextInt(boardWidth+200)-100;
        int randomYSpawn = rand.nextInt(100)+boardHeight;
        double speedX;
        double speedY;
        if (randomXSpawn >= objX)
        {
            speedX = -1.0*(randomXSpawn - objX)/ballSpeedFactor;
        }
        else if (randomXSpawn<0)
        {
            speedX = 1.0*(Math.abs(randomXSpawn) + objX)/ballSpeedFactor;
        }
        else
        {
            speedX = 1.0*(objX - randomXSpawn)/ballSpeedFactor;
        }
        speedY = -1.0*(randomYSpawn - objY)/ballSpeedFactor;
        Warning warning = new Warning(warningImg, objX, objY, warningWidth, warningHeight);
        warnings.add(warning);
        //warning.x = objX;
        //warning.y = objY;
        
        int counter = 0;
        
        for (int i = 0; i < tempBarrage.size(); i++)
        {
            Ball ball = tempBarrage.get(i);
            int randX = rand.nextInt(ballSeperation);
            int randY = rand.nextInt(ballSeperation);
            if (counter%2 == 0)
            {
                ball.setX(randomXSpawn + randX);// = randomXSpawn + randX;
                ball.setY(randomYSpawn + randY);// = randomYSpawn + randY;
            }
            else
            {
                ball.setX(randomXSpawn - randX);// = randomXSpawn - randX;
                ball.setY(randomYSpawn - randY);// = randomYSpawn - randY;
            }
            ball.setSpeedX(speedX);// = speedX;
            ball.setSpeedY(speedY);// = speedY;
        }
        barrage.addAll(tempBarrage);
        tempBarrage.clear();
        //System.out.println(speedX + " speed " +speedY);
    }





    public  void playerShootBullet()
    {
        PlayerBullet bullet = new PlayerBullet(playerBulletImg, pBulletWidth, pBulletHeight);
        bullet.setX(soldier.getX() + soldier.getWidth()/2);// = soldier.x + soldier.width/2;
        bullet.setY(soldier.getY() + soldier.getHeight());// = soldier.y + soldier.height;
        bullets.add(bullet);
    }



    public  void beginGame()
    {
        beginAnimation = false;
        start = true;
        placeMissileTimer.start();
        placePlaneTimer.start();
        newBarrageTimer.start();
    }


    public  void collision()
    {
        try {
            for (int i = 0; i < bullets.size(); i++)
            {
                for (int j = 0; j < planes.size(); j++)
                {


                    try {    
                        PlayerBullet a = bullets.get(i);
                        Plane b = planes.get(j);
                        if (a.getX() < b.getX() + b.getWidth() &&
                            a.getX() + a.getWidth() > b.getX() &&
                            a.getY() < b.getY() + b.getHeight() &&
                            a.getY() + a.getHeight() > b.getY())
                        {
                            bullets.remove(i);
                            b.setHealth(b.getHealth()-1);//--;
                            if (b.getHealth() <= 0)
                            {
                                score++;
                                if (score%10 == 0)
                                {
                                    barrageDelay *= barrageDifficultyGain;
                                    newBarrageTimer.setDelay(barrageDelay);
                                    missileDelay *= missileDifficultyGain;
                                    placeMissileTimer.setDelay(missileDelay);
                                    difficultyCredsMultiplier += difficultyCredsIncrease;
                                }
                                booms.add(new Boom(boomImg, b.getX(), b.getY(), boomWidth, boomHeight));
                                planes.remove(j);
                                if (score > PD.getHighScore()){PD.setHighScore(score);}
                            }
                        }
                        } catch (Exception e)
                        {
                            //System.err.println("error during inner loop "+e.getMessage());
                            //e.printStackTrace();
                        }
                
            
                }
            }
        }
        catch (Exception e)
        {
            //System.err.println("Error during outer loop: "+e.getMessage());
            //e.printStackTrace();
        }



        for (int i = 0; i < bullets.size(); i++)
        {
        if (bullets.get(i).getY() > boardHeight) {bullets.remove(i);}
        }

        for (int i = 0; i < planes.size(); i++)
        {
        if (planes.get(i).getX() > boardWidth + 225 || planes.get(i).getX() < -225)
            {
                planes.remove(i);
            }
        }

        for (Explosion a : explosives)
        {
            
            if (a.getX() < soldier.getX() + soldier.getWidth() &&
                a.getX() + a.getWidth() > soldier.getX() &&
                a.getY() < soldier.getY() + soldier.getHeight() &&
                a.getY() + a.getHeight() > soldier.getY() && (a.getHitTarget() == false) && (immunity == false))
            {
                a.setHitTarget(true);// = true;
                if (health != 0)
                {
                    hearts.remove(hearts.size()-1);
                }

                health--;
                if (health == 2) {parachute.setPara(para2Img);}
                if (health == 1) {parachute.setPara(para3Img);}
            }
        }


        //barrage
        for (Ball a : barrage)
        {
            
            if (a.getX() < soldier.getX() + soldier.getWidth() &&
            a.getX() + a.getWidth() > soldier.getX() &&
            a.getY() < soldier.getY() + soldier.getHeight() &&
            a.getY() + a.getHeight() > soldier.getY() && (a.getHitTarget() == false) && (immunity == false))
            {
                a.setHitTarget(true);// = true;
                if (health != 0)
                {
                    hearts.remove(hearts.size()-1);
                }

                health--;
                if (health == 2) {parachute.setPara(para2Img);}
                if (health == 1) {parachute.setPara(para3Img);}
            }
        }
    }





    public void placePlanes()
    {
        int randomSideX = rand.nextInt(2);
        //System.out.println(randomSideX);
        Plane plane;

        if (randomSideX == 0)
        {
            //System.out.println("R");
            plane = new Plane(planeRightImg, randomSideX, planeVelocityX, planeVelocityY, planeWidth, planeHeight, planeHealth, boardWidth, boardHeight);
        }
        else
        {
            //System.out.println("L");
            plane = new Plane(planeLeftImg, randomSideX, planeVelocityX, planeVelocityY, planeWidth, planeHeight, planeHealth, boardWidth, boardHeight);
        }
        planes.add(plane);
    }


    public void placeMissile()
    {
        Missile missile = new Missile(enemyBulletImg, boardWidth, boardHeight, missileWidth, missileHeight, missileVelocityY, maxExplosionHeight, minExplosionHeight);
        missiles.add(missile);
    }










    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        draw(g);
    }


    public void draw(Graphics g)
    {
        g.drawImage(backgroundImg, 0,0, boardWidth,boardHeight, null);
        //add statement with start here
        if (beginAnimation)
        {
            g.drawImage(transportImg, transport.getX(), transport.getY(), transport.getWidth(), transport.getHeight(), null);
        }
        else if (start)
        {
            g.drawImage(parachute.getPara(), soldier.getX()-14, soldier.getY()-60, parachute.getWidth(), parachute.getHeight(), null);
            g.drawImage(soldier.getImg(), soldier.getX(), soldier.getY(), soldier.getWidth(), soldier.getHeight(), null);
            
            if (transCounter <= transCounterMax)
            {
                g.drawImage(transportImg, transport.getX(), transport.getY(), transport.getWidth(), transport.getHeight(), null);
            }
            transCounter++;
        }   


        //shop
        if (openShop)
        {
            g.drawImage(shop.getImg(), shop.getX(), shop.getY(), shop.getWidth(), shop.getHeight(), null);
            shop.updateValues();
            if (shopBounce <= shopBounceMax)
            {
                if (shopFinalX < shop.getX() && shopSpeedStop)
                {
                    shop.setVelocity(shop.getVelocity()+shop.getAcc());// += shop.acc;
                    shop.setX(shop.getX() + shop.getVelocity());// += shop.velocity;
                }
                else
                {
                    if (shopSpeedStop)
                    {
                        shop.setVelocity(0);// = 0;
                        shopBounce++;
                        shopSpeedStop = false;
                        shopTempX *= shopBounceSlowDown;
                    }
                    shop.setVelocity(shop.getVelocity()+-1*shop.getAcc());// += -1*shop.acc;
                    shop.setX(shop.getX() + shop.getVelocity());// += shop.velocity;
                    if (shop.getX() >= shopTempX)
                    {
                        shopSpeedStop = true;
                    }
                }
            }
            else if (shopTempX != shopFinalX)
            {
                shopTempX = (int)(boardWidth*shopBounceSlowDown);
                shop.setX(shopFinalX);// = shopFinalX;
            }
        }


        if (closeShop)
        {
            g.drawImage(shop.getImg(), shop.getX(), shop.getY(), shop.getWidth(), shop.getHeight(), null);
            shop.updateValues();
            if (shop.getX() <= 700)
            {
                shop.setX(shop.getX() + shop.getVelocity());// += shop.velocity;
            }
            else
            {
                shopSpeedStop = true;
                shopTempX = (int)(boardWidth*0.9);
                shop.setVelocity(0);// = 0;
                shop.setX(700);// = 700;
                shopBounce = 0;
                closeShop = false;
            }
        }


        //add looping feature to draw all abilities/skins
        //g.drawImage(shop.abilities[0][0].img, shop.abilities[0][0].x, shop.abilities[0][0].y, shop.abilities[0][0].width, shop.abilities[0][0].height, null);
        //g.drawImage(shop.abilities[1][0].img, shop.abilities[1][0].x, shop.abilities[1][0].y, shop.abilities[1][0].width, shop.abilities[1][0].height, null);
        initializeItems(g);








        for (int i = 0; i < itemBtns.size(); i++)
        {
            ItemBtn iBtn = itemBtns.get(i);
            if (iBtn.getTimeDelay() < itemBtnNoDealDelay)
            {
                iBtn.setTimeDelay(iBtn.getTimeDelay() + 1);//++;
            }
            else
            {
                iBtn.getItemBtn().setText(""+iBtn.getCost());
                iBtn.setTimeDelay(0);// = 0;
                itemBtns.remove(i);
            }

        }

        if (start)
        {
            for (int i = 0; i < hearts.size(); i++)
            {
                Heart heart = hearts.get(i);
                g.drawImage(heart.getImg(), heart.getX(), heart.getY(), heart.getWidth(), heart.getHeight(), null);

            }
        }

        for (int i = 0; i < barrage.size(); i++)
        {
            Ball ball = barrage.get(i);
            g.drawImage(ballImg, (int)ball.getX(), (int)ball.getY(), ball.getWidth(), ball.getHeight(), null);
        }
        
        
        //try
        //{
            //if (barrage.size() > 0 && barrage.get(barrage.size()-1).y < 0)
            //{
            //    barrage.clear();
            //}

            for (int i = 0; i < barrage.size(); i++)
            {   
                Ball ball = barrage.get(i);
                if (ball.getY() < 0)
                {
                    barrage.remove(i);
                }
            }



        //}
        //catch(Exception e)
        //{
            //System.out.println(e);
        //}
        

        
        for (int i = 0; i < warnings.size(); i++)
        {
            Warning warning = warnings.get(i);
            g.drawImage(warning.getImg(), warning.getX(), warning.getY(), warning.getWidth(), warning.getHeight(), null);
            if (warning.getWarningCounter() != warningDelay)
            {
                warning.setWarningCounter(warning.getWarningCounter() + 1);//++;
            }
            else
            {
            warnings.remove(i);
            }
        }
        
        useAbil(g);


        for (int i = 0; i < bullets.size(); i++)
        {
            PlayerBullet bullet = bullets.get(i);
            g.drawImage(bullet.getImg(), bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight(), null);
        }
        for (int i = 0; i < planes.size(); i++)
        {
            Plane plane = planes.get(i);
            g.drawImage(plane.getImg(), plane.getX(), plane.getY(), plane.getWidth(), plane.getHeight(), null);
        }

        for (int i = 0; i < booms.size(); i++)
        {
            Boom boom = booms.get(i);
            g.drawImage(boom.getImg(), boom.getX(), boom.getY(), boom.getWidth(), boom.getHeight(), null);
            boom.setTime(boom.getTime() + 1);// += 1;
            if (boom.getTime() == boomLifeTime) {booms.remove(i);}
        }

        for (int i = 0; i < missiles.size(); i++)
        {
            Missile missile = missiles.get(i);
            g.drawImage(missile.getImg(), missile.getX(), missile.getY(), missile.getWidth(), missile.getHeight(), null);
            if (missile.getY() <= missile.getExplodeHeight()) 
            {
                
                Explosion explosive = new Explosion(missileImg, missile.getX() - explosiveWidth/2 + missile.getWidth()/2, missile.getY() - explosiveHeight/2, explosiveWidth, explosiveHeight);
                missiles.remove(i);
                explosives.add(explosive);
            }   
            
        }

        for (int i = 0; i < explosives.size(); i++)
        {
            Explosion explosive = explosives.get(i);
            g.drawImage(explosive.getImg(), explosive.getX(), explosive.getY(), explosive.getWidth(), explosive.getHeight(), null);
            explosive.setTime(explosive.getTime() + 1);// += 1;
            if (explosive.getTime() == explosionLifeTime) {explosives.remove(i);}
        }

        g.setColor(Color.GRAY);
        g.setFont(new Font("Calibri", Font.PLAIN, 32));
        //if ((start == false|| beginAnimation == false && transport.x <= 0) || openShop != true)
        if (loadingScreen)
        {
            g.drawImage(parachuteIconImg, 250-25, boardHeight/2-60-60, 150+50, 150+50, null);
            g.setFont(new Font("Calibri",Font.PLAIN, 32));
            g.drawString("Parachute Sliders",190,boardHeight/3-60);
            //g.setFont(new Font("Calibri",Font.PLAIN, 20));
            //g.drawString("Press \"Up Arrow\" to Begin",10,boardHeight-70);
            //g.drawString("Press \"Left Arrow\" to Move Left",10,boardHeight-50);
            //g.drawString("Press \"Right Arrow\" to Move Right",10,boardHeight-30);
            //g.drawString("Press \"Down Arrow\" to Shoot",10,boardHeight-10);
        }

        //if (health <= 0)
        //{
        //    g.drawString("Score: "+ String.valueOf((int) score), 10, 35);
        //    g.drawString("Press \"Up Arrow\" to Play Again", 90, boardHeight/2);
        //    g.drawString("High Score: "+String.valueOf(highScore), 220, 35);
        //    g.drawString("Game Over!",235,boardHeight/3+40);
        //}
        if (start)
        {
            g.drawString("Score: "+String.valueOf((int)score), 10, 35);
            g.drawString("High Score: "+String.valueOf(PD.getHighScore()), 220, 35);
            //g.drawString("Health: "+String.valueOf((int)health),boardWidth-140, 35);
        }

        

    }


    public void move()
    {
        appListener.checkKeyHeld();
        soldier.setX(soldier.getX() + velocityX);// += velocityX;
        soldier.setX(Math.min(soldier.getX(), boardWidth-soldier.getWidth()));// = Math.min(soldier.x, boardWidth-soldier.width);
        soldier.setX(Math.max(soldier.getX(), 0));// = Math.max(soldier.x, 0);


        if (transCounter <= transCounterMax && start)
        {
            transport.setX(transport.getX() + transport.getSpeed());// += transport.speed;
        }

        if (transport.getX() >= boardWidth/2 && start)
        {
            beginGame();
        }


        //barrage
        for (int i = 0; i < barrage.size(); i++)
        {
            Ball ball = barrage.get(i);
            if (ball.getDelay() != ball.getCounter()) 
            {
                ball.setCounter(ball.getCounter() + 1);//++;
            }
            else
            {    
                ball.setX(ball.getX() + ball.getSpeedX());// += ball.speedX;
                ball.setY(ball.getY() + ball.getSpeedY());// += ball.speedY;
            }
        }

        //p bullet
        for (int i = 0; i < bullets.size(); i++)
        {
            PlayerBullet bullet = bullets.get(i);
            bullet.setY(bullet.getY() + pBulletVelocityY);// += pBulletVelocityY;
            //System.out.println("x: "+bullet.x+"   y: "+bullet.y);
        }

        for (int i = 0; i < planes.size(); i++)
        {
            Plane plane = planes.get(i);
            plane.setX(plane.getX() + plane.getVelocityX());// += plane.velocityX;
            plane.setY(plane.getY() + plane.getVelocityY());// += plane.velocityY;
        }

        for (int i = 0; i < missiles.size(); i++)
        {
            Missile mis = missiles.get(i);
            mis.setY(mis.getY() + mis.getVelocityY());// += mis.velocityY;
        }


        collision();

    }







    public void equipSkins(Skin skin, String skinType)
    {
        if (skinType.equals("Soldier"))
        {
            soldierLeftImg = skin.getImgL();
            soldierRightImg = skin.getImgR();
            soldier.setImg(soldierRightImg);
        }

        if (skinType.equals("Plane"))
        {
            planeLeftImg = skin.getImgL();
            planeRightImg = skin.getImgR();
        }

        if (skinType.equals("Cannon"))
        {
            enemyBulletImg = skin.getImgL();
        }

        if (skinType.equals("Turret"))
        {
            ballImg = skin.getImgL();
        }

        if (skinType.equals("Explosive"))
        {
            boomImg = skin.getImgL();
            missileImg = skin.getImgR();
        }

        if (skinType.equals("Sky"))
        {
            backgroundImg = skin.getImgL();
        }
    }




//g.drawImage(missile.getImg(), missile.getX(), missile.getY(), missile.getWidth(), missile.getHeight(), null);
//g.drawString("Score: "+String.valueOf((int)score), 10, 35);

    public void useAbil(Graphics g)
    {
        g.setFont(new Font("Calibri",Font.PLAIN, 32));
        if (abilBooHealth1 && abilActive)
        {
            g.drawImage(abilHealth1Img, 16, 45, 40, 40, null);
            if (hearts.size() == 2 && abilHealth1.getSpecialItem().getAbil().getOffCooldown())
            {
                hearts.clear();
                for (int i = 1; i <= 3; i++)
                {
                    Heart heart = new Heart(heartImg, boardWidth-(heartWidth*(i-1) + (heartWidth/4)*i) -35, 8, heartWidth, heartHeight);
                    hearts.add(heart);
                }
                health++;
                abilHealth1.getSpecialItem().getAbil().setOffCooldown(false);
            }

            if (hearts.size() == 1 && abilHealth1.getSpecialItem().getAbil().getOffCooldown())
            {
                hearts.clear();
                for (int i = 1; i <= 2; i++)
                {
                    Heart heart = new Heart(heartImg, boardWidth-(heartWidth*(i-1) + (heartWidth/4)*i) -35, 8, heartWidth, heartHeight);
                    hearts.add(heart);
                }
                health++;
                abilHealth1.getSpecialItem().getAbil().setOffCooldown(false);
            }

            if (abilHealth1.getSpecialItem().getAbil().getOffCooldown() == false)
            {
                //System.out.println(abilHealth1.getSpecialItem().getAbil().getDelay());
                abilHealth1.getSpecialItem().getAbil().setDelay(abilHealth1.getSpecialItem().getAbil().getDelay()+1);
                g.drawString(String.valueOf((int)((abilCountDown-abilHealth1.getSpecialItem().getAbil().getDelay())/100)), 64, 75);


                if (abilHealth1.getSpecialItem().getAbil().getDelay() == abilCountDown)
                {
                    abilHealth1.getSpecialItem().getAbil().setDelay(0);
                    abilHealth1.getSpecialItem().getAbil().setOffCooldown(true);
                    abilActive = false;
                }
            }


        }


        if (abilBooHealth2 && abilActive)
        {
            g.drawImage(abilHealth2Img, 16, 45, 40, 40, null);
            if (hearts.size() == 2 && abilHealth2.getSpecialItem().getAbil().getOffCooldown())
            {
                hearts.clear();
                for (int i = 1; i <= 3; i++)
                {
                    Heart heart = new Heart(heartImg, boardWidth-(heartWidth*(i-1) + (heartWidth/4)*i) -35, 8, heartWidth, heartHeight);
                    hearts.add(heart);
                }
                health++;
                abilHealth2.getSpecialItem().getAbil().setOffCooldown(false);
            }

            if (hearts.size() == 1 && abilHealth1.getSpecialItem().getAbil().getOffCooldown())
            {
                hearts.clear();
                for (int i = 1; i <= 3; i++)
                {
                    Heart heart = new Heart(heartImg, boardWidth-(heartWidth*(i-1) + (heartWidth/4)*i) -35, 8, heartWidth, heartHeight);
                    hearts.add(heart);
                }
                health = 3;
                abilHealth2.getSpecialItem().getAbil().setOffCooldown(false);
            }

            if (abilHealth2.getSpecialItem().getAbil().getOffCooldown() == false)
            {
                //System.out.println(abilHealth2.getSpecialItem().getAbil().getDelay());
                abilHealth2.getSpecialItem().getAbil().setDelay(abilHealth2.getSpecialItem().getAbil().getDelay()+1);
                g.drawString(String.valueOf((int)((abilCountDown-abilHealth2.getSpecialItem().getAbil().getDelay())/100)), 64, 75);


                if (abilHealth2.getSpecialItem().getAbil().getDelay() == abilCountDown)
                {
                    abilHealth2.getSpecialItem().getAbil().setDelay(0);
                    abilHealth2.getSpecialItem().getAbil().setOffCooldown(true);
                    abilActive = false;
                }

            }
        }

        if (abilBooShield1 && abilActive)
        {   
            g.drawImage(abilShield1Img, 16, 45, 40, 40, null);
            if (abilShield1.getSpecialItem().getAbil().getOffCooldown())
            {
                immunity = true;
                finishedAbil = false;
                abilShield1.getSpecialItem().getAbil().setOffCooldown(false);
            }

            if (abilShield1.getSpecialItem().getAbil().getOffCooldown() == false && finishedAbil == false)
            {
                abilShield1.getSpecialItem().getAbil().setDelay(abilShield1.getSpecialItem().getAbil().getDelay()+1);
                if (abilShield1.getSpecialItem().getAbil().getDelay() < abilShield1.getSpecialItem().getAbil().getUsageTime())
                {
                    g.drawImage(abilShield1Img, soldier.getX()+10, soldier.getY()+12, 30, 50, null);
                    g.drawString(String.valueOf((int)((abilShield1.getSpecialItem().getAbil().getUsageTime()-abilShield1.getSpecialItem().getAbil().getDelay())/100)), 64, 75);
                }
                else
                {
                    finishedAbil = true;
                    abilShield1.getSpecialItem().getAbil().setDelay(0);
                    immunity = false;
                }
            }


            if (abilShield1.getSpecialItem().getAbil().getOffCooldown() == false && finishedAbil)
            {
                //System.out.println(abilShield1.getSpecialItem().getAbil().getDelay());
                abilShield1.getSpecialItem().getAbil().setDelay(abilShield1.getSpecialItem().getAbil().getDelay()+1);
                g.drawString(String.valueOf((int)((abilCountDown-abilShield1.getSpecialItem().getAbil().getDelay())/100)), 64, 75);

                if (abilShield1.getSpecialItem().getAbil().getDelay() == abilCountDown)
                {
                    finishedAbil = false;
                    abilShield1.getSpecialItem().getAbil().setDelay(0);
                    abilShield1.getSpecialItem().getAbil().setOffCooldown(true);
                    abilActive = false;
                }

            }
            
        }

        if (abilBooShield2 && abilActive)
        {
            g.drawImage(abilShield2Img, 16, 45, 40, 40, null);
            if (abilShield2.getSpecialItem().getAbil().getOffCooldown())
            {
                immunity = true;
                abilShield2.getSpecialItem().getAbil().setOffCooldown(false);
            }



            if (abilShield2.getSpecialItem().getAbil().getOffCooldown() == false && finishedAbil == false)
            {
                abilShield2.getSpecialItem().getAbil().setDelay(abilShield2.getSpecialItem().getAbil().getDelay()+1);
                if (abilShield2.getSpecialItem().getAbil().getDelay() < abilShield2.getSpecialItem().getAbil().getUsageTime())
                {
                    g.drawImage(abilShield2Img, soldier.getX()-soldier.getWidth(), soldier.getY()-soldier.getHeight()/2, 120, 120, null);
                    g.drawString(String.valueOf((int)((abilShield2.getSpecialItem().getAbil().getUsageTime()-abilShield2.getSpecialItem().getAbil().getDelay())/100)), 64, 75);
                }
                else
                {
                    finishedAbil = true;
                    abilShield2.getSpecialItem().getAbil().setDelay(0);
                    immunity = false;
                }
            }





            if (abilShield2.getSpecialItem().getAbil().getOffCooldown() == false && finishedAbil)
            {
                //System.out.println(abilShield2.getSpecialItem().getAbil().getDelay());
                abilShield2.getSpecialItem().getAbil().setDelay(abilShield2.getSpecialItem().getAbil().getDelay()+1);
                g.drawString(String.valueOf((int)((abilCountDown-abilShield2.getSpecialItem().getAbil().getDelay())/100)), 64, 75);

                if (abilShield2.getSpecialItem().getAbil().getDelay() == abilCountDown)
                {
                    abilShield2.getSpecialItem().getAbil().setDelay(0);
                    abilShield2.getSpecialItem().getAbil().setOffCooldown(true);
                    abilActive = false;
                }

            }

            
        }

        if (abilBooShield3 && abilActive)
        {
            g.drawImage(abilShield3Img, 16, 45, 40, 40, null);
            if (abilShield3.getSpecialItem().getAbil().getOffCooldown())
            {
                immunity = true;
                abilShield3.getSpecialItem().getAbil().setOffCooldown(false);
            }



            if (abilShield3.getSpecialItem().getAbil().getOffCooldown() == false && finishedAbil == false)
            {
                abilShield3.getSpecialItem().getAbil().setDelay(abilShield3.getSpecialItem().getAbil().getDelay()+1);
                if (abilShield3.getSpecialItem().getAbil().getDelay() < abilShield3.getSpecialItem().getAbil().getUsageTime())
                {
                    g.drawImage(abilShield3Img, soldier.getX()-soldier.getWidth()-40, soldier.getY()-soldier.getHeight()/2-40, 200, 200, null);
                    g.drawString(String.valueOf((int)((abilShield3.getSpecialItem().getAbil().getUsageTime()-abilShield3.getSpecialItem().getAbil().getDelay())/100)), 64, 75);
                }
                else
                {
                    finishedAbil = true;
                    abilShield3.getSpecialItem().getAbil().setDelay(0);
                    immunity = false;
                }
            }





            if (abilShield3.getSpecialItem().getAbil().getOffCooldown() == false && finishedAbil)
            {
                abilShield3.getSpecialItem().getAbil().setDelay(abilShield3.getSpecialItem().getAbil().getDelay()+1);
                g.drawString(String.valueOf((int)((abilCountDown-abilShield3.getSpecialItem().getAbil().getDelay())/100)), 64, 75);

                if (abilShield3.getSpecialItem().getAbil().getDelay() == abilCountDown)
                {
                    abilShield3.getSpecialItem().getAbil().setDelay(0);
                    abilShield3.getSpecialItem().getAbil().setOffCooldown(true);
                    abilActive = false;
                }

            }

            
        }


        if (abilBooPoints1 && start)
        {
            g.drawImage(abilPoints1Img, 16, 45, 40, 40, null);
        }

        if (abilBooPoints2 && start)
        {
            g.drawImage(abilPoints2Img, 16, 45, 40, 40, null);
        }

        if (abilBooPoints3 && start)
        {
            g.drawImage(abilPoints3Img, 16, 45, 40, 40, null);
        }


    }













    public void initializeItems(Graphics g)
    {
        if (shop.getStoreType() == 0 && openShop)
        {
            for (Item[] abils : shop.getAbilities())
            {
                for (Item abil : abils)
                {
                    if (abil != null)
                    {
                    g.drawImage(abil.getImg(), abil.getX()+18, abil.getY()+12, abil.getWidth(), abil.getHeight(), null);
                    }
                }
            }
        }
        else if (openShop)
        {
            for (Item[] skins : shop.getSkins())
            {
                for (Item skin : skins)
                {
                    if (skin != null)
                    {
                    g.drawImage(skin.getImg(), skin.getX()+18, skin.getY()+12, skin.getWidth(), skin.getHeight(), null);
                    }
                }
            }
        }
    }










}
