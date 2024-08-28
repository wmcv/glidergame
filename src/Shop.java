import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Shop implements ActionListener
    {
        private final AppListener appListener;

        private final int ShopBtnFactorX;
        private final int ShopBtnFactorY;

        private final int switchShopShift;
        private final int switchShopWidth;
        private final int switchShopHeight;

        private final int closeShopShift;
        private final int closeShopWidth;
        private final int closeShopHeight;

        private final int shopTypeShift;
        private final int shopTypeWidth;
        private final int shopTypeHeight;


        //0 for abilities
        //1 for skins
        private int storeType = 0;
        private int x;
        private int y;
        private int width;
        private int height;
        private int velocity;
        private int acc;
        private Image img;

        private Item[][] abilities;
        private Item[][] skins;
        private JButton switchShopBtn;
        private JButton shopCloseBtn;
        private JTextPane shopType;
        private JPanel JPane;
        private ParachuteSlider paraSlider;
        public Shop(Image img, int x, int y, int width, int height, int acc, int ShopBtnFactorX, int ShopBtnFactorY,
        int switchShopShift, int switchShopWidth, int switchShopHeight, int closeShopShift, int closeShopWidth, int closeShopHeight,
        int shopTypeShift, int shopTypeWidth, int shopTypeHeight, int storeLengthX, int storeLengthY, JPanel JPane, AppListener appListener, ParachuteSlider paraSlider)
        {
            this.paraSlider = paraSlider;
            this.appListener = appListener;
            this.JPane = JPane;
            velocity = 0;
            this.img = img;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.acc = acc;
            this.ShopBtnFactorX = ShopBtnFactorX;
            this.ShopBtnFactorY = ShopBtnFactorY;
            this.switchShopShift = switchShopShift;
            this.switchShopWidth = switchShopWidth;
            this.switchShopHeight = switchShopHeight;
    
            this.closeShopShift = closeShopShift;
            this.closeShopWidth = closeShopWidth;
            this.closeShopHeight = closeShopHeight;
    
            this.shopTypeShift = shopTypeShift;
            this.shopTypeWidth = shopTypeWidth;
            this.shopTypeHeight = shopTypeHeight;

            abilities = new Item[storeLengthX][storeLengthY];
            skins = new Item[storeLengthX][storeLengthY];
            switchShopBtn = new JButton("Switch Shops");
            switchShopBtn.addActionListener((ActionListener) this);
            switchShopBtn.setBounds(this.x + ShopBtnFactorX + switchShopShift, this.y + ShopBtnFactorY, switchShopWidth, switchShopHeight);
            switchShopBtn.setFocusable(false);
            JPane.add(switchShopBtn);
            shopCloseBtn = new JButton("Exit Shop");
            shopCloseBtn.addActionListener((ActionListener) this);
            shopCloseBtn.setBounds(this.x + ShopBtnFactorX + closeShopShift, this.y + ShopBtnFactorY, closeShopWidth, closeShopHeight);
            shopCloseBtn.setFocusable(false);
            JPane.add(shopCloseBtn);
            shopType = new JTextPane();
            shopType.setBackground(Color.GRAY);
            shopType.setForeground(Color.BLACK);
            shopType.setText("      Abilities");
            shopType.setBounds(this.x + ShopBtnFactorX + shopTypeShift, this.y + ShopBtnFactorY, shopTypeWidth, shopTypeHeight);
            JPane.add(shopType);
        }


        public Image getImg()
        {
            return img;
        }

        public void setImg(Image img)
        {
            this.img = img;
        }
    
        public int getX()
        {
            return x;
        }

        public void setX(int x)
        {
            this.x = x;
        }

        public int getY()
        {
            return y;
        }

        public void setY(int y)
        {
            this.y = y;
        }

        public int getWidth()
        {
            return width;
        }

        public void setWidth(int width)
        {
            this.width = width;
        }

        public int getHeight()
        {
            return height;
        }

        public void setHeight(int height)
        {
            this.height = height;
        }

        public ParachuteSlider getParaSlider()
        {
            return paraSlider;
        }

        public int getStoreType()
        {
            return storeType;
        }

        public void setStoreType(int storeType)
        {
            this.storeType = storeType;
        }

        public int getVelocity()
        {
            return velocity;
        }

        public void setVelocity(int velocity)
        {
            this.velocity = velocity;
        }

        public int getAcc()
        {
            return acc;
        }

        public void setAcc(int acc)
        {
            this.acc = acc;
        }

        public Item[][] getAbilities()
        {
            return abilities;
        }

        public void setAbilities(Item[][] abils)
        {
            abilities = abils;
        }

        public void setSkins(Item[][] skis)
        {
            skins = skis;
        }

        public Item[][] getSkins()
        {
            return skins;
        }


        public JTextPane getShopType()
        {
            return shopType;
        }

        public void setShopType(JTextPane shopType)
        {
            this.shopType = shopType;
        }




        public void updateValues()
        {
            switchShopBtn.setBounds(x + ShopBtnFactorX + switchShopShift, y + ShopBtnFactorY, switchShopWidth, switchShopHeight);
            shopCloseBtn.setBounds(x + ShopBtnFactorX + closeShopShift, y + ShopBtnFactorY, closeShopWidth, closeShopHeight);
            shopType.setBounds(x + ShopBtnFactorX + shopTypeShift, y + ShopBtnFactorY, shopTypeWidth, shopTypeHeight);

            //if (storeType == 0)
            //{
                for (Item[] abils : abilities)
                {
                    for (Item abil : abils)
                    {
                        if (abil != null)
                        {
                            abil.updateValues(this);
                        }
                    }
                }
            //}
            //else
            //{
                for (Item[] skis : skins)
                {
                    for (Item ski : skis)
                    {
                        if (ski != null)
                        {
                            ski.updateValues(this);
                        }
                    }
                }
            //}
        }


        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == switchShopBtn) 
            {
                //this method will be called if button clicked
                appListener.onShopButtonClick(e, shopCloseBtn, switchShopBtn);
            }
            if (e.getSource() == shopCloseBtn)
            {
                appListener.onShopButtonClick(e, shopCloseBtn, switchShopBtn);
            }
        }




    }
