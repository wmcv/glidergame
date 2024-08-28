import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class AppListener implements ActionListener, KeyListener {
    private final ParachuteSlider paraSlider;
    private static ParachuteSlider paraSlide;
    public AppListener(ParachuteSlider paraSlider, ParachuteSlider paraSlide)
    {
        this.paraSlide = paraSlide;
        this.paraSlider = paraSlider;
        //setFocusable(true);
        //addKeyListener(this);
    }
    public void checkKeyHeld()
    {
        if (paraSlider.keyHeld.contains(KeyEvent.VK_RIGHT) && paraSlider.start)
        {
            paraSlider.soldier.setSold(paraSlider.soldierRightImg);
            paraSlider.velocityX = paraSlider.soldier.getAcc();
        }
        
        if (paraSlider.keyHeld.contains(KeyEvent.VK_LEFT) && paraSlider.start)
        {
            paraSlider.soldier.setSold(paraSlider.soldierLeftImg);
            paraSlider.velocityX = paraSlider.soldier.getAcc()*-1;
        }
        
        if (paraSlider.keyHeld.isEmpty())
        {
            paraSlider.velocityX = 0;
        }


    }






    @Override
    public void keyTyped(KeyEvent e) {}




    @Override
    public void keyPressed(KeyEvent e) {
        if ((e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_LEFT) && paraSlider.start && paraSlider.transport.getX() >= paraSlider.boardWidth/2) 
        {
            paraSlider.keyHeld.add(e.getKeyCode());
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN && paraSlider.start && paraSlider.transport.getX() >= (paraSlider.boardWidth)/2)
        {
            paraSlider.playerShootBullet();
        }    

        if (e.getKeyCode() == KeyEvent.VK_UP && paraSlider.start && paraSlider.transport.getX() >= (paraSlider.boardWidth)/2)
        {
            if (paraSlider.abilBooDefault && (paraSlider.abilDefault.getSpecialItem().getAbil().getOffCooldown()))
            {
                paraSlider.abilActive = false;
                paraSlider.abilCountDown = -1;
            }

            if (paraSlider.abilBooHealth1 && (paraSlider.abilHealth1.getSpecialItem().getAbil().getOffCooldown()) && paraSlider.health < 3)
            {
                paraSlider.abilActive = true;
                paraSlider.abilCountDown = 900;
            }

            if (paraSlider.abilBooHealth2 && (paraSlider.abilHealth2.getSpecialItem().getAbil().getOffCooldown()) && paraSlider.health < 3)
            {
                paraSlider.abilActive = true;
                paraSlider.abilCountDown = 1200;
            }

            if (paraSlider.abilBooShield1 && (paraSlider.abilShield1.getSpecialItem().getAbil().getOffCooldown()))
            {
                paraSlider.abilActive = true;
                paraSlider.abilCountDown = 1000;
            }

            if (paraSlider.abilBooShield2 && (paraSlider.abilShield2.getSpecialItem().getAbil().getOffCooldown()))
            {
                paraSlider.abilActive = true;
                paraSlider.abilCountDown = 1200;
            }

            if (paraSlider.abilBooShield3 && (paraSlider.abilShield3.getSpecialItem().getAbil().getOffCooldown()))
            {
                paraSlider.abilActive = true;
                paraSlider.abilCountDown = 1500;
            }

            if (paraSlider.abilBooPoints1 && (paraSlider.abilPoints1.getSpecialItem().getAbil().getOffCooldown()))
            {
                paraSlider.abilActive = true;
                paraSlider.abilCountDown = -1;
            }

            if (paraSlider.abilBooPoints2 && (paraSlider.abilPoints2.getSpecialItem().getAbil().getOffCooldown()))
            {
                paraSlider.abilActive = true;
                paraSlider.abilCountDown = -1;
            }

            if (paraSlider.abilBooPoints3 && (paraSlider.abilPoints3.getSpecialItem().getAbil().getOffCooldown()))
            {
                paraSlider.abilActive = true;
                paraSlider.abilCountDown = -1;
            }


        }

        //if (e.getKeyCode() == KeyEvent.VK_UP && health <= 0)
        //{
        //}

        //else if (e.getKeyCode() == KeyEvent.VK_UP && start == false)
        //{
        //    start = true;
        //    beginAnimation = true;
        //}

    }


    //for item buttons for buying/equiping
    public static void onItemButtonClick(Shop shop, Item item, Soldier soldier)
    {  
        ItemBtn iBtn = item.getBtnItem();
        if (iBtn.getOwned() == true)
        {
            //code to equip (unequip same type skin/unequip all other abilities)
            int type = item.getSpecialItem().getType();

            //if ability
            if (type == 0)
            {
                //Ability abil = (Ability)item.getSpecialItem();
                int abilX = soldier.getAbilX();
                int abilY = soldier.getAbilY();
                shop.getAbilities()[abilX][abilY].getBtnItem().getItemBtn().setText("Equip");
                item.getBtnItem().getItemBtn().setText("Equipped");
                soldier.setAbilX(item.getItemX());//) = item.itemX;
                soldier.setAbilY(item.getItemY());// = item.itemY;
                abilBooActivator(iBtn.getItem());

                //equip method


            }
            //if skin
            else
            {
                Skin skin = (Skin)item.getSpecialItem();
                String skinType = skin.getSkinType();

                for (Item[] skis : shop.getSkins())
                {
                    for (Item ski : skis)
                    {
                        if (ski != null)
                        {
                            Skin sk = (Skin)ski.getSpecialItem();
                            if (sk.getSkinType().equals(skinType) && ski.getBtnItem().getOwned())
                            {
                                ski.getBtnItem().getItemBtn().setText("Equip");
                            }
                        }
                    }
                }
                item.getBtnItem().getItemBtn().setText("Equipped");
                paraSlide.equipSkins(skin, skinType);
            }
        }
        else
        {
            //code to buy
            iBtn.buyItem();
        }
    }



    //for shop buttons such as switch type and close shop
    public void onShopButtonClick(ActionEvent e, JButton shopCloseBtn, JButton switchShopBtn)
    {   
        
        if (e.getSource() == shopCloseBtn)
        {   
            paraSlider.shopBounce = 0;
            paraSlider.loadingScreen = true;
            paraSlider.playBtn.setVisible(true);
            paraSlider.shopOpenBtn.setVisible(true);
            paraSlider.quitBtn.setVisible(true);
            paraSlider.closeShop = true;
            paraSlider.openShop = false;
        }
        if (e.getSource() == switchShopBtn)
        {
            paraSlider.shop.setStoreType((paraSlider.shop.getStoreType()+1)%2);
            if (paraSlider.shop.getStoreType() == 0)
            {
                paraSlider.shop.getShopType().setText("      Abilities");
            }
            else
            {
                paraSlider.shop.getShopType().setText("         Skins");
            }
        }




    }



    @Override
    public void keyReleased(KeyEvent e) 
    {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            paraSlider.keyHeld.remove(e.getKeyCode());
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == paraSlider.playBtn)
        {
            paraSlider.credits.setVisible(false);
            paraSlider.highestScore.setVisible(false);
            paraSlider.loadingScreen = false;
            paraSlider.soldier.setY(paraSlider.soldierY);// = paraSlider.soldierY;
            paraSlider.playBtn.setVisible(false);
            paraSlider.shopOpenBtn.setVisible(false);
            paraSlider.quitBtn.setVisible(false);
            paraSlider.score = 0;
            paraSlider.health = 3;
            paraSlider.parachute.setPara(paraSlider.para1Img);
            paraSlider.bullets.clear();
            paraSlider.planes.clear();
            paraSlider.missiles.clear();
            paraSlider.booms.clear();
            paraSlider.barrage.clear();
            paraSlider.explosives.clear();
            paraSlider.soldier.setX(paraSlider.boardWidth/2); //= paraSlider.boardWidth/2;
            paraSlider.gameLoop.start();
            paraSlider.warnings.clear();
            paraSlider.missileDelay = 1900;
            paraSlider.placeMissileTimer.setDelay(paraSlider.missileDelay);
            paraSlider.barrageDelay = 6500;
            paraSlider.newBarrageTimer.setDelay(paraSlider.barrageDelay);
            paraSlider.start = true;
            paraSlider.beginAnimation = true;
            paraSlider.transCounter = 0;
            paraSlider.transport.setX(paraSlider.transportX);// = paraSlider.transportX;
            paraSlider.hearts.clear();
            for (int i = 1; i <= 3; i++)
            {
                Heart heart = new Heart(paraSlider.heartImg, paraSlider.boardWidth-(paraSlider.heartWidth*(i-1) + (paraSlider.heartWidth/4)*i) -35, 8, paraSlider.heartWidth, paraSlider.heartHeight);
                paraSlider.hearts.add(heart);
            }
        }


        if (e.getSource() == paraSlider.shopOpenBtn)
        {
            paraSlider.loadingScreen = false;
            paraSlider.playBtn.setVisible(false);
            paraSlider.shopOpenBtn.setVisible(false);
            paraSlider.quitBtn.setVisible(false);
            paraSlider.shop.setX(700);// = 700;
            paraSlider.openShop = true;
        }

        if (e.getSource() == paraSlider.returnHomeBtn)
        {
            paraSlider.credits.setVisible(true);
            paraSlider.highestScore.setVisible(true);
            paraSlider.start = false;
            paraSlider.health = 3;
            paraSlider.loadingScreen = true;
            paraSlider.quitBtn.setVisible(true);
            paraSlider.shopOpenBtn.setVisible(true);
            paraSlider.playBtn.setVisible(true);
            paraSlider.bullets.clear();
            paraSlider.planes.clear();
            paraSlider.missiles.clear();
            paraSlider.booms.clear();
            paraSlider.barrage.clear();
            paraSlider.explosives.clear();
            paraSlider.soldier.setY(-150);// = -150;
            paraSlider.warnings.clear();
            paraSlider.hearts.clear();
            paraSlider.gameLoop.start();
            paraSlider.placeMissileTimer.stop();
            paraSlider.placePlaneTimer.stop();
            paraSlider.newBarrageTimer.stop();
            paraSlider.playAgainBtn.setVisible(false);
            paraSlider.returnHomeBtn.setVisible(false);
        }

        if (e.getSource() == paraSlider.quitBtn)
        {
            System.exit(0);
        }

        if (e.getSource() == paraSlider.playAgainBtn)
        {
            paraSlider.returnHomeBtn.setVisible(false);
            paraSlider.playAgainBtn.setVisible(false);
            paraSlider.score = 0;
            paraSlider.health = 3;
            paraSlider.parachute.setPara(paraSlider.para1Img);
            paraSlider.bullets.clear();
            paraSlider.planes.clear();
            paraSlider.missiles.clear();
            paraSlider.booms.clear();
            paraSlider.barrage.clear();
            paraSlider.explosives.clear();
            paraSlider.soldier.setX(paraSlider.boardWidth/2);// = paraSlider.boardWidth/2;
            paraSlider.gameLoop.start();
            paraSlider.warnings.clear();
            paraSlider.missileDelay = 1900;
            paraSlider.placeMissileTimer.setDelay(paraSlider.missileDelay);
            paraSlider.barrageDelay = 6500;
            paraSlider.newBarrageTimer.setDelay(paraSlider.barrageDelay);
            paraSlider.start = true;
            paraSlider.beginAnimation = true;
            paraSlider.transCounter = 0;
            paraSlider.transport.setX(paraSlider.transportX);// = paraSlider.transportX;
            paraSlider.hearts.clear();
            for (int i = 1; i <= 3; i++)
            {
                Heart heart = new Heart(paraSlider.heartImg, paraSlider.boardWidth-(paraSlider.heartWidth*(i-1) + (paraSlider.heartWidth/4)*i) -35, 8, paraSlider.heartWidth, paraSlider.heartHeight);
                paraSlider.hearts.add(heart);
            }
        }

        paraSlider.credits.setText("Credits: "+paraSlider.PD.getCreds());
        paraSlider.highestScore.setText("High Score: "+paraSlider.PD.getHighScore());
        
        paraSlider.move();
        paraSlider.repaint();
        if (paraSlider.health <= 0)
        {
            paraSlider.abilActive = false;
            paraSlider.finishedAbil = false;
            paraSlider.abilHealth1.getSpecialItem().getAbil().setOffCooldown(true);
            paraSlider.abilHealth2.getSpecialItem().getAbil().setOffCooldown(true);
            paraSlider.abilShield1.getSpecialItem().getAbil().setOffCooldown(true);
            paraSlider.abilShield1.getSpecialItem().getAbil().setDelay(0);
            paraSlider.abilShield2.getSpecialItem().getAbil().setOffCooldown(true);
            paraSlider.abilShield2.getSpecialItem().getAbil().setDelay(0);
            paraSlider.abilShield3.getSpecialItem().getAbil().setOffCooldown(true);
            paraSlider.abilShield3.getSpecialItem().getAbil().setDelay(0);
            paraSlider.abilPoints1.getSpecialItem().getAbil().setOffCooldown(true);
            paraSlider.abilPoints2.getSpecialItem().getAbil().setOffCooldown(true);
            paraSlider.abilPoints3.getSpecialItem().getAbil().setOffCooldown(true);

            paraSlider.playAgainBtn.setVisible(true);
            paraSlider.returnHomeBtn.setVisible(true);
            paraSlider.PD.addCreds((int)(paraSlider.score*paraSlider.difficultyCredsMultiplier*paraSlider.abilMultiplier));
            //paraSlider.creds += paraSlider.score*paraSlider.difficultyCredsMultiplier;
            paraSlider.difficultyCredsMultiplier =  1.0;
            paraSlider.placeMissileTimer.stop();
            paraSlider.placePlaneTimer.stop();
            paraSlider.newBarrageTimer.stop();
            paraSlider.gameLoop.stop();
        }
    }



    public static void abilBooActivator(Item item)
    {
        String abilName = item.getSpecialItem().getName();
        
        paraSlide.abilBooDefault = false;
        paraSlide.abilBooHealth1 = false;
        paraSlide.abilBooHealth2 = false;
        paraSlide.abilBooShield1 = false;
        paraSlide.abilBooShield2 = false;
        paraSlide.abilBooShield3 = false;
        paraSlide.abilBooPoints1 = false;
        paraSlide.abilBooPoints2 = false;
        paraSlide.abilBooPoints3 = false;


        if (abilName.equals("None"))
        {
            paraSlide.abilBooDefault = true;
            paraSlide.abilMultiplier = 1;
        }

        if (abilName.equals("Heart Gain"))
        {
            paraSlide.abilBooHealth1 = true;
            paraSlide.abilMultiplier = 1;
        }

        if (abilName.equals("Double Heart Gain"))
        {
            paraSlide.abilBooHealth2 = true;
            paraSlide.abilMultiplier = 1;
        }

        if (abilName.equals("Shield"))
        {
            paraSlide.abilBooShield1 = true;
            paraSlide.abilMultiplier = 1;
        }

        if (abilName.equals("Force Field"))
        {
            paraSlide.abilBooShield2 = true;
            paraSlide.abilMultiplier = 1;
        }

        if (abilName.equals("Hex Dome"))
        {
            paraSlide.abilBooShield3 = true;
            paraSlide.abilMultiplier = 1;
        }

        if (abilName.equals("x1.5 Points"))
        {
            paraSlide.abilBooPoints1 = true;
            paraSlide.abilMultiplier = 1.5;
        }

        if (abilName.equals("x2.0 Points"))
        {
            paraSlide.abilBooPoints2 = true;
            paraSlide.abilMultiplier = 2;
        }

        if (abilName.equals("x2.5 Points"))
        {
            paraSlide.abilBooPoints3 = true;
            paraSlide.abilMultiplier = 2.5;
        }
    }



}
