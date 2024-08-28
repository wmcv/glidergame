import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import javax.swing.JPanel;

public class Item implements ActionListener, Serializable
    {
        //itemX and itemY are the coords to its place in the 2d array
        private int itemX;
        private int itemY;
        private int x;
        private int y;
        private int width;
        private int height;
        private int itemFactorX;
        private int itemFactorY;
        private int itemShiftX;
        private int itemShiftY;
        private int itemBtnDrop;
        //int cost;
        //boolean owned = false;
        private Image img;
        private ItemSpecial specialItem;
        private ItemBtn btnItem;
        private Shop shop;
        private Soldier soldier;
        //default testing ability
        //public Item(Image img, int shopX, int width, int height, int itemFactorX, int itemFactorY, int itemShiftX, int itemShiftY)
        //{
        //    this.width = width;
        //    this.height = height;
        //    this.img = ballImg;
        //    this.itemX = 0;
        //    this.itemY = 0;
        //    specialItem = new Ability(ballImg, "none", 0);
        //    btnItem = new ItemBtn(x, y+itemBtnDrop, 100, this);
        //    x = shopX + itemFactorX*itemX + itemShiftX;
        //    y = shopX + itemFactorY*itemY + itemShiftY;
        //}

        //constructor for abilites
        public Item(Image img, String name, int usageTime, int itemX, int itemY, int cost, Shop shop, Soldier soldier, 
        int shopX, int shopY, int width, int height, int itemFactorX, int itemFactorY, int itemShiftX, 
        int itemShiftY, int itemBtnDrop, int itemBtnWidth, int itemBtnHeight, int JTPaneWidth, int JTPaneHeight, JPanel JPane, PlayerData PD)
        {
            this.shop = shop;
            this.soldier = soldier;
            this.img = img;
            this.itemX = itemX;
            this.itemY = itemY;
            this.width = width;
            this.height = height;
            x = shopX + itemFactorX*itemX + itemShiftX;
            y = shopY + itemFactorY*itemY + itemShiftY;
            specialItem = new Ability(img, name, usageTime);
            btnItem = new ItemBtn(x, y+itemBtnDrop, cost, this, itemBtnWidth, itemBtnHeight, JTPaneWidth, JTPaneHeight, JPane, PD, shop, soldier);
            this.itemFactorX = itemFactorX;
            this.itemFactorY = itemFactorY;
            this.itemShiftX = itemShiftX;
            this.itemShiftY = itemShiftY;
            this.itemBtnDrop = itemBtnDrop;
        }

        //constructor for skins
        public Item(Image imgL, Image imgR, String name, String type, int itemX, int itemY, int cost, Shop shop, Soldier soldier,
        int shopX, int shopY, int width, int height, int itemFactorX, int itemFactorY, int itemShiftX, 
        int itemShiftY, int itemBtnDrop, int itemBtnWidth, int itemBtnHeight, int JTPaneWidth, int JTPaneHeight, JPanel JPane, PlayerData PD)
        {
            this.shop = shop;
            this.soldier = soldier;
            img = imgR;
            this.itemX = itemX;
            this.itemY = itemY;
            this.width = width;
            this.height = height;
            x = shopX + itemFactorX*itemX + itemShiftX;
            y = shopY + itemFactorY*itemY + itemShiftY;
            specialItem = new Skin(imgL, imgR, name, type);
            //System.out.println("w "+JTPaneWidth+"      h "+JTPaneHeight);
            btnItem = new ItemBtn(x, y+itemBtnDrop, cost, this, itemBtnWidth, itemBtnHeight, JTPaneWidth, JTPaneHeight, JPane, PD, shop, soldier);
            this.itemFactorX = itemFactorX;
            this.itemFactorY = itemFactorY;
            this.itemShiftX = itemShiftX;
            this.itemShiftY = itemShiftY;
            this.itemBtnDrop = itemBtnDrop;
        }

        private void buyItem()
        {
            btnItem.buyItem();
        }

        //work on fixing the text pane for each abil/skin
        public void updateValues(Shop shop)
        {
            x = shop.getX() + itemFactorX*itemX + itemShiftX;
            y = shop.getY() + itemFactorY*itemY + itemShiftY;
            //btnItem.updateValues(x, y, itemBtnDrop);
            if (shop.getStoreType() == this.specialItem.getType())
            {
                //System.out.println("w "+btnItem.getJTWidth()+"      h "+btnItem.getJTHeight());
                btnItem.getItemBtn().setBounds(x, y+itemBtnDrop, btnItem.getWidth(), btnItem.getHeight());
                btnItem.getJTPane().setBounds(x, y-5, btnItem.getJTWidth(), btnItem.getJTHeight());
            }
            else
            {
                btnItem.getItemBtn().setBounds(x+500, y+itemBtnDrop, btnItem.getWidth(), btnItem.getHeight());
                btnItem.getJTPane().setBounds(x+500, y, btnItem.getJTWidth(), btnItem.getJTHeight());
            }
        }

        //public void actionPerformed(ActionEvent e) 
        //{
        //    if (e.getSource() == btnItem) 
        //    {
        //        //this method will be called if button clicked
        //        onItemButtonClick(this);
        //    }
        //}

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnItem) 
            {
                //this method will be called if button clicked
                AppListener.onItemButtonClick(shop, this, soldier);
            }
            // TODO Auto-generated method stub
            //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
        }


        public int getItemX()
        {
            return itemX;
        }

        public void setItemX(int itemX)
        {
            this.itemX = itemX;
        }

        public int getItemY()
        {
            return itemY;
        }

        public void setItemY(int itemY)
        {
            this.itemY = itemY;
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

        public int getItemFactorX()
        {
            return itemFactorX;
        }

        public void setItemFactorX(int itemFactorX)
        {
            this.itemFactorX = itemFactorX;
        }

        public int getItemFactorY()
        {
            return itemFactorY;
        }

        public void setItemFactorY(int itemFactorY)
        {
            this.itemFactorY = itemFactorY;
        }

        public int getItemShiftX()
        {
            return itemShiftX;
        }

        public void setItemShiftX(int itemShiftX)
        {
            this.itemShiftX = itemShiftX;
        }

        public int getItemShiftY()
        {
            return itemShiftY;
        }

        public void setItemShiftY(int itemShiftY)
        {
            this.itemShiftY = itemShiftY;
        }

        public int getItemBtnDrop()
        {
            return itemBtnDrop;
        }

        public void setItemBtnDrop(int itemBtnDrop)
        {
            this.itemBtnDrop = itemBtnDrop;
        }




        public Image getImg()
        {
            return img;
        }

        public void setImg(Image img)
        {
            this.img = img;
        }

        public ItemSpecial getSpecialItem()
        {
            return specialItem;
        }

        public void setSpecialItem(ItemSpecial specialItem)
        {
            this.specialItem = specialItem;
        }

        public ItemBtn getBtnItem()
        {
            return btnItem;
        }

        public void setBtnItem(ItemBtn btnItem)
        {
            this.btnItem = btnItem;
        }


















    }
