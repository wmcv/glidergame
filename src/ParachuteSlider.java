import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import javax.swing.*;

public class ParachuteSlider extends JPanel implements ActionListener, KeyListener
{

    //add ammo limit?
    //add buttons for "play" "play again" "how to play" maybe others
    
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
    int chanceExplosion = 20; //out of 1000 per tick;
    int explosionLifeTime = 20;
    //1900
    int missileDelay = 1900;
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
    int storeX = 600;
    int storeY = 0;
    int storeWidth = 300;
    int storeHeight = 400;
    int storeLengthX = 3;
    int storeLengthY = 4;


    //item
    int itemBtnWidth;
    int itemBtnHeight;
    int itemBtnNoDealDelay = 100;
    int itemBtnDrop = 30;
    int itemWidth = 60;
    int itemHeight = 60;
    int itemFactorX = -50;
    int itemFactorY = -50;


    class Soldier
    {
        int x = soldierX;
        int y = soldierY;
        int width = soldierWidth;
        int height = soldierHeight;
        Image img;

        public Soldier(Image img)
        {
            this.img = img;
        }
        public void setSold(Image img){this.img = img;}
    }

    class Parachute
    {
        int width = parachuteWidth;
        int height = parachuteHeight;
        Image img;
        
        public Parachute(Image img)
        {
            this.img = img;
        }

        public void setPara(Image img){this.img = img;}
    }

    class PlayerBullet
    {
        int x;
        int y;
        int width = pBulletWidth;
        int height = pBulletHeight;
        Image img;

        public PlayerBullet(Image img)
        {
            this.img = img;
        }
    }


    class Plane
    {
        int velocityX = planeVelocityX;
        int velocityY = planeVelocityY;
        int x;
        int y;
        int width = planeWidth;
        int height = planeHeight;
        Image img;
        int health = planeHealth;
        int index;

        public Plane(Image img, int n)
        {
            this.img = img;
            if (n == 0)
            {
                x = -100;
                y = boardHeight - rand.nextInt(boardHeight/2)-20;
            }
            else
            {
                velocityX *= -1;
                x = boardWidth;
                y = boardHeight - rand.nextInt(boardHeight/2)-20;
            }

        }
    }


    class Boom
    {
        int x;
        int y;
        int width = boomWidth;
        int height = boomHeight;
        int time = 0;
        Image img;

        public Boom(Image img, int x, int y)
        {
            this.img = img;
            this.x = x;
            this.y = y;
        }
    }

    class Missile
    {
        int x;
        int y = boardHeight;
        int width = missileWidth;
        int height = missileHeight;
        int velocityY = missileVelocityY;
        int explodeHeight = rand.nextInt(maxExplosionHeight)-minExplosionHeight;
        Image img;

        public Missile(Image img)
        {
            this.img = img;
            int xPos = rand.nextInt(boardWidth);
            x = xPos;
        }
    }


    class Explosion
    {
        int x;
        int y;
        int width = explosiveWidth;
        int height = explosiveHeight;
        int time = 0;
        boolean hitTarget = false;
        Image img;

        public Explosion(Image img, int x, int y)
        {
            this.img = img;
            this.x = x;
            this.y = y;
        }
    }


    class Transport
    {
        int x = transportX;
        int y = transportY;
        int width = transportWidth;
        int height = transportHeight;
        int speed = transportSpeed;
        Image img;

        public Transport(Image img)
        {
            this.img = img;
        }
    }

    class Heart
    {
        int x;
        int y;
        int width = heartWidth;
        int height = heartHeight;
        Image img;

        public Heart(int x, int y, Image img)
        {
            this.x = x;
            this.y = y;
            this.img = img;
        }
    }

    class Ball
    {
        double x;
        double y;
        int width = ballWidth;
        int height = ballHeight;
        int delay;
        int counter = 0;
        double speedX;
        double speedY;
        boolean hitTarget = false;
        Image img;

        public Ball(Image img)
        {
            this.img = img;
        }
    }

    class Warning
    {
        int x;
        int y;
        int width = warningWidth;
        int height = warningHeight;
        int warningCounter = 0;
        Image img;

        public Warning(int x, int y, Image img)
        {
            this.x = x;
            this.y = y;
            this.img = img;
        }
    }



    //shop to hold all the items
    class Shop
    {
        //0 for abilities
        //1 for skins
        int storeType = 0;
        int x = storeX;
        int y = storeY;
        Image img;

        Item[][] abilities;
        Item[][] skins;

        public Shop(Image img)
        {
            this.img = img;
            abilities = new Item[storeLengthX][storeLengthY];
            skins = new Item[storeLengthX][storeLengthY];
        }


    }






    //item class to hold all of the item
    class Item
    {
        int itemX;
        int itemY;
        int x = shop.x + itemFactorX*itemX;
        int y = shop.y + itemFactorY*itemY;
        int width = itemWidth;
        int height = itemHeight;
        //int cost;
        //boolean owned = false;
        Image img;

        itemBtn btnItem;

        public Item(Image img, int itemX, int itemY, int cost)
        {
            this.img = img;
            this.itemX = itemX;
            this.itemY = itemY;
            //this.cost = cost;
            btnItem = new itemBtn(x, y+itemBtnDrop, cost);
        }

        private void buyItem()
        {
            btnItem.buyItem();
        }

    }



    //part of item, includes the button
    class itemBtn
    {
        int x;
        int y;
        int width = itemBtnWidth;
        int height = itemBtnHeight;
        JButton itemBtn;
        int cost;
        int timeDelay = 0;
        boolean owned = false;

        public itemBtn(int x, int y, int cost)
        {
            this.cost = cost;
            this.x = x;
            this.y = y;
            itemBtn = new JButton(""+this.cost);
            itemBtn.addActionListener((ActionListener) this);
            itemBtn.setBounds(this.x, this.y, width, height);
            itemBtn.setFocusable(false);
            add(itemBtn);
        }

        private void buyItem()
        {
            if (creds >= cost && owned != true)
            {
                itemBtn.setText("Equip");
                creds-=cost;
                owned = true;
            }
            else
            {
                itemBtn.setText("Not Enough Creds");
                itemBtns.add(this);
            }
        }


        private void equipItem()
        {
            //see if bought
            //unequip same-typed skin, I.E. unequip plane skin if this is a new plane skin
            //equip this skin
        }

    }



    //a part of item, ability and skin, is gonna hold the skill/feature of the item
    class Ability
    {
        
    }


    class Skin
    {
        
    }















    //logic
    int health = 3;
    int score = 0;
    int highScore = 0;
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
    
    //buttons
    JButton playBtn;
    JButton howToPlayBtn;
    JButton returnHomeBtn;
    JButton quitBtn;
    JButton playAgainBtn;
    JButton shopOpenBtn;
    JButton shopCloseBtn;



    //shop
    Shop shop;
    ArrayList<itemBtn> itemBtns;

    //timer for barrage
    ArrayList<Ball> barrage;
    Timer newBarrageTimer;
    ArrayList<Warning> warnings;
    ArrayList<Ball> tempBarrage;
    
    
    
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





    ParachuteSlider()
    {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setFocusable(true);
        addKeyListener(this);

        //load images
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
        shopBackground = new ImageIcon(getClass().getResource("./parachuteIcon.png")).getImage();
        
        //buttons
        this.setLayout(null);

        //play button
        playBtn = new JButton("Play");
        playBtn.addActionListener(this);
        playBtn.setBounds(boardWidth - 405-5, boardHeight -50 , 180, 40);
        playBtn.setFocusable(false);
        add(playBtn);
        
        //how to play button
        howToPlayBtn = new JButton("How to Play");
        howToPlayBtn.addActionListener(this);
        howToPlayBtn.setBounds(boardWidth - 375, boardHeight-120 , 110, 35);
        howToPlayBtn.setFocusable(false);
        add(howToPlayBtn);

        //return home button
        returnHomeBtn = new JButton("Return Home");
        returnHomeBtn.addActionListener(this);
        returnHomeBtn.setBounds(boardWidth/2 -70, boardHeight/2, 140, 35);
        returnHomeBtn.setFocusable(false);
        add(returnHomeBtn);

        //quit button
        quitBtn = new JButton("Quit");
        quitBtn.addActionListener(this);
        quitBtn.setBounds(boardWidth - 357, boardHeight-155 , 75, 35);
        quitBtn.setFocusable(false);
        add(quitBtn);
        
        //play again button
        playAgainBtn = new JButton("Play Again");
        playAgainBtn.addActionListener(this);
        playAgainBtn.setBounds(boardWidth/2 - 80, boardHeight/2 - 35, 160, 35);
        playAgainBtn.setFocusable(false);
        add(playAgainBtn);

        //shop open button
        shopOpenBtn = new JButton("Shop");
        shopOpenBtn.addActionListener(this);   //110
        shopOpenBtn.setBounds(boardWidth - 393, boardHeight - 85, 145, 35);
        shopOpenBtn.setFocusable(false);
        add(shopOpenBtn);




        //shop close button
        /* 
        shopCloseBtn = new JButton("Close Shop");
        shopCloseBtn.addActionListener(this);
        shopCloseBtn.setBounds(boardWidth/2 - 80, boardHeight/2 - 35, 160, 35);
        shopCloseBtn.setFocusable(false);
        add(shopCloseBtn);
        */






        soldier = new Soldier(soldierRightImg);
        parachute = new Parachute(para1Img);
        planes = new ArrayList<>();
        bullets = new ArrayList<>();
        booms = new ArrayList<>();
        missiles = new ArrayList<>();
        explosives = new ArrayList<>();
        transport = new Transport(transportImg);
        hearts = new ArrayList<>();
        barrage = new ArrayList<>();
        warnings = new ArrayList<>();
        tempBarrage = new ArrayList<>();
        itemBtns = new ArrayList<>();


        for (int i = 1; i <= 3; i++)
        {
            Heart heart = new Heart(boardWidth-(heartWidth*(i-1) + (heartWidth/4)*i) -35, 8, heartImg);
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
            

            
        gameLoop = new Timer(1000/60, this);
        gameLoop.start();

        //add cloud and birds?
        
    }



  

    //add barrage method

    public void newBarrage()
    {
        for (int i = 0; i < rand.nextInt(bonusBallCount)+minBallCount; i++)
        {
            Ball ball = new Ball(ballImg);
            ball.delay = ballDelay*i;
            tempBarrage.add(ball);
        }
    }

    public void warningCalc()
    {
        int objX = soldier.x;
        int objY = soldier.y;
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
        Warning warning = new Warning(objX, objY, warningImg);
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
                ball.x = randomXSpawn + randX;
                ball.y = randomYSpawn + randY;
            }
            else
            {
                ball.x = randomXSpawn - randX;
                ball.y = randomYSpawn - randY;
            }
            ball.speedX = speedX;
            ball.speedY = speedY;
        }
        barrage.addAll(tempBarrage);
        tempBarrage.clear();
        //System.out.println(speedX + " speed " +speedY);
    }





    public void playerShootBullet()
    {
        PlayerBullet bullet = new PlayerBullet(playerBulletImg);
        bullet.x = soldier.x + soldier.width/2;
        bullet.y = soldier.y + soldier.height;
        bullets.add(bullet);
    }



    public void beginGame()
    {
        beginAnimation = false;
        start = true;
        placeMissileTimer.start();
        placePlaneTimer.start();
        newBarrageTimer.start();
    }


    public void collision()
    {
        try {
            for (int i = 0; i < bullets.size(); i++)
            {
                for (int j = 0; j < planes.size(); j++)
                {


                    try {    
                        PlayerBullet a = bullets.get(i);
                        Plane b = planes.get(j);
                        if (a.x < b.x + b.width &&
                            a.x + a.width > b.x &&
                            a.y < b.y + b.height &&
                            a.y + a.height > b.y)
                        {
                            bullets.remove(i);
                            b.health--;
                            if (b.health <= 0)
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
                                booms.add(new Boom(boomImg, b.x, b.y));
                                planes.remove(j);
                                if (score > highScore){highScore = score;}
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
        if (bullets.get(i).y > boardHeight) {bullets.remove(i);}
        }

        for (int i = 0; i < planes.size(); i++)
        {
        if (planes.get(i).x > boardWidth + 225 || planes.get(i).x < -225)
            {
                planes.remove(i);
            }
        }

        for (Explosion a : explosives)
        {
            
            if (a.x < soldier.x + soldier.width &&
                a.x + a.width > soldier.x &&
                a.y < soldier.y + soldier.height &&
                a.y + a.height > soldier.y && (a.hitTarget == false))
            {
                a.hitTarget = true;
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
            
            if (a.x < soldier.x + soldier.width &&
                a.x + a.width > soldier.x &&
                a.y < soldier.y + soldier.height &&
                a.y + a.height > soldier.y && (a.hitTarget == false))
            {
                a.hitTarget = true;
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
            plane = new Plane(planeRightImg, randomSideX);
        }
        else
        {
            //System.out.println("L");
            plane = new Plane(planeLeftImg, randomSideX);
        }
        planes.add(plane);
    }


    public void placeMissile()
    {
        Missile missile = new Missile(enemyBulletImg);
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
            g.drawImage(transportImg, transport.x, transport.y, transport.width, transport.height, null);
        }
        else if (start)
        {
            g.drawImage(parachute.img, soldier.x-14, soldier.y-60, parachute.width, parachute.height, null);
            g.drawImage(soldier.img, soldier.x, soldier.y, soldier.width, soldier.height, null);
            
            if (transCounter <= transCounterMax)
            {
                g.drawImage(transportImg, transport.x, transport.y, transport.width, transport.height, null);
            }
            transCounter++;
        }   


        for (int i = 0; i < itemBtns.size(); i++)
        {
            itemBtn iBtn = itemBtns.get(i);
            if (iBtn.timeDelay < itemBtnNoDealDelay)
            {
                iBtn.timeDelay++;
            }
            else
            {
                iBtn.itemBtn.setText(""+iBtn.cost);
                iBtn.timeDelay = 0;
                itemBtns.remove(i);
            }

        }

        for (int i = 0; i < hearts.size(); i++)
        {
            Heart heart = hearts.get(i);
            g.drawImage(heart.img, heart.x, heart.y, heart.width, heart.height, null);

        }

        for (int i = 0; i < barrage.size(); i++)
        {
            Ball ball = barrage.get(i);
            g.drawImage(ballImg, (int)ball.x, (int)ball.y, ball.width, ball.height, null);
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
                if (ball.y < 0)
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
            g.drawImage(warning.img, warning.x, warning.y, warning.width, warning.height, null);
            if (warning.warningCounter != warningDelay)
            {
                warning.warningCounter++;
            }
            else
            {
            warnings.remove(i);
            }
        }
        


        for (int i = 0; i < bullets.size(); i++)
        {
            PlayerBullet bullet = bullets.get(i);
            g.drawImage(bullet.img, bullet.x, bullet.y, bullet.width, bullet.height, null);
        }
        for (int i = 0; i < planes.size(); i++)
        {
            Plane plane = planes.get(i);
            g.drawImage(plane.img, plane.x, plane.y, plane.width, plane.height, null);
        }

        for (int i = 0; i < booms.size(); i++)
        {
            Boom boom = booms.get(i);
            g.drawImage(boom.img, boom.x, boom.y, boom.width, boom.height, null);
            boom.time += 1;
            if (boom.time == boomLifeTime) {booms.remove(i);}
        }

        for (int i = 0; i < missiles.size(); i++)
        {
            Missile missile = missiles.get(i);
            g.drawImage(missile.img, missile.x, missile.y, missile.width, missile.height, null);
            if (missile.y <= missile.explodeHeight) 
            {
                Explosion explosive = new Explosion(missileImg, missile.x, missile.y);
                missiles.remove(i);
                explosives.add(explosive);
            }   
            
        }

        for (int i = 0; i < explosives.size(); i++)
        {
            Explosion explosive = explosives.get(i);
            g.drawImage(explosive.img, explosive.x, explosive.y, explosive.width, explosive.height, null);
            explosive.time += 1;
            if (explosive.time == explosionLifeTime) {explosives.remove(i);}
        }

        g.setColor(Color.BLACK);
        g.setFont(new Font("Calibri", Font.PLAIN, 32));
        if (start == false|| beginAnimation == false && transport.x <= 0)
        {
            g.drawImage(parachuteIconImg, 250-25, boardHeight/2-60-60, 150+50, 150+50, null);
            g.setFont(new Font("Calibri",Font.PLAIN, 32));
            g.drawString("Parachute Sliders",190,boardHeight/3-60);
            g.setFont(new Font("Calibri",Font.PLAIN, 20));
            g.drawString("Press \"Up Arrow\" to Begin",10,boardHeight-70);
            g.drawString("Press \"Left Arrow\" to Move Left",10,boardHeight-50);
            g.drawString("Press \"Right Arrow\" to Move Right",10,boardHeight-30);
            g.drawString("Press \"Down Arrow\" to Shoot",10,boardHeight-10);
        }

        if (health <= 0)
        {
            g.drawString("Score: "+ String.valueOf((int) score), 10, 35);
            g.drawString("Press \"Up Arrow\" to Play Again", 90, boardHeight/2);
            g.drawString("High Score: "+String.valueOf(highScore), 220, 35);
            g.drawString("Game Over!",235,boardHeight/3+40);
        }
        else
        {
            g.drawString("Score: "+String.valueOf((int)score), 10, 35);
            g.drawString("High Score: "+String.valueOf(highScore), 220, 35);
            //g.drawString("Health: "+String.valueOf((int)health),boardWidth-140, 35);
        }

        

    }


    public void move()
    {
        checkKeyHeld();
        soldier.x += velocityX;
        soldier.x = Math.min(soldier.x, boardWidth-soldier.width);
        soldier.x = Math.max(soldier.x, 0);


        if (transCounter <= transCounterMax && start)
        {
            transport.x += transport.speed;
        }

        if (transport.x >= boardWidth/2)
        {
            beginGame();
        }


        //barrage
        for (int i = 0; i < barrage.size(); i++)
        {
            Ball ball = barrage.get(i);
            if (ball.delay != ball.counter) 
            {
                ball.counter++;
            }
            else
            {    
                ball.x += ball.speedX;
                ball.y += ball.speedY;
            }
        }

        //p bullet
        for (int i = 0; i < bullets.size(); i++)
        {
            PlayerBullet bullet = bullets.get(i);
            bullet.y += pBulletVelocityY;
            //System.out.println("x: "+bullet.x+"   y: "+bullet.y);
        }

        for (int i = 0; i < planes.size(); i++)
        {
            Plane plane = planes.get(i);
            plane.x += plane.velocityX;
            plane.y += plane.velocityY;
        }

        for (int i = 0; i < missiles.size(); i++)
        {
            Missile mis = missiles.get(i);
            mis.y += mis.velocityY;
        }


        collision();

    }







    private void checkKeyHeld()
    {
        if (keyHeld.contains(KeyEvent.VK_RIGHT) && start)
        {
            soldier.setSold(soldierRightImg);
            velocityX = soldierAcc;
        }
        
        if (keyHeld.contains(KeyEvent.VK_LEFT) && start)
        {
            soldier.setSold(soldierLeftImg);
            velocityX = soldierAcc*-1;
        }
        
        if (keyHeld.isEmpty())
        {
            velocityX = 0;
        }


    }






    @Override
    public void keyTyped(KeyEvent e) {}




    @Override
    public void keyPressed(KeyEvent e) {
        if ((e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_LEFT) & start&& transport.x >= boardWidth/2) 
        {
            keyHeld.add(e.getKeyCode());
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN && start && transport.x >= boardWidth/2)
        {
            playerShootBullet();
        }    

        if (e.getKeyCode() == KeyEvent.VK_UP && health <= 0)
        {
            score = 0;
            health = 3;
            parachute.setPara(para1Img);
            bullets.clear();
            planes.clear();
            missiles.clear();
            booms.clear();
            barrage.clear();
            explosives.clear();
            soldier.x = boardWidth/2;
            gameLoop.start();
            warnings.clear();
            missileDelay = 1900;
            placeMissileTimer.setDelay(missileDelay);
            barrageDelay = 6500;
            newBarrageTimer.setDelay(barrageDelay);
            start = true;
            beginAnimation = true;
            transCounter = 0;
            transport.x = transportX;
            hearts.clear();
            for (int i = 1; i <= 3; i++)
            {
                Heart heart = new Heart(boardWidth-(heartWidth*(i-1) + (heartWidth/4)*i) -35, 8, heartImg);
                hearts.add(heart);
            }


        }
        else if (e.getKeyCode() == KeyEvent.VK_UP && start == false)
        {
            start = true;
            beginAnimation = true;
        }

    }


    @Override
    public void keyReleased(KeyEvent e) 
    {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            keyHeld.remove(e.getKeyCode());
        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playBtn)
        {
            
        }

        if (e.getSource() == howToPlayBtn)
        {
            
        }

        if (e.getSource() == returnHomeBtn)
        {
            
        }

        if (e.getSource() == quitBtn)
        {
            
        }

        if (e.getSource() == playAgainBtn)
        {
            
        }

        
        
        
        move();
        repaint();
        if (health <= 0)
        {
            creds += score*difficultyCredsMultiplier;
            placeMissileTimer.stop();
            placePlaneTimer.stop();
            newBarrageTimer.stop();
            gameLoop.stop();
        }
    }



}
