import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class ItemBtn implements ActionListener, Serializable
    {
        private int x;
        private int y;
        private int width;
        private int height;
        private int JTWidth;
        private int JTHeight;
        private JButton itemBtn;
        private JTextPane JTPane;
        private int cost;
        private int timeDelay;
        private boolean owned = false;
        private Item item;
        private JPanel JPane;
        private PlayerData PD;
        private Shop shop;
        private Soldier soldier;
        private SimpleAttributeSet center;
        public ItemBtn(int x, int y, int cost, Item item, int width, int height, int JTWidth, int JTHeight, JPanel JPane, PlayerData PD, Shop shop, Soldier soldier)
        {
            center = new SimpleAttributeSet();
            StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
            this.shop = shop;
            this.soldier = soldier;
            this.PD = PD;
            this.JPane = JPane;
            timeDelay = 0;
            this.item = item;
            this.cost = cost;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.JTWidth = JTWidth;
            this.JTHeight = JTHeight;
            //System.out.println("w "+JTWidth+"      h "+JTHeight);
            Font fItemBtn = new Font(Font.SANS_SERIF, 3, 10);
            itemBtn = new JButton(""+this.cost);
            itemBtn.addActionListener((ActionListener) this);
            itemBtn.setBounds(this.x, this.y, width, height);
            itemBtn.setFocusable(false);
            itemBtn.setFont(fItemBtn);
            //itemBtn.setAlignment
            JPane.add(itemBtn);
            Font fJT = new Font(Font.SANS_SERIF, 3, 8);
            JTPane = new JTextPane();
            JTPane.setText(item.getSpecialItem().getName());
            JTPane.setBackground(Color.GRAY);
            JTPane.setForeground(Color.BLACK);

            JTPane.setBounds(this.x, this.y-20, JTWidth, JTHeight);
            JTPane.setEditable(false);
            JTPane.setFont(fJT);
            JPane.add(JTPane);
            //itemBtn.setBounds(200, 200, 60, 60);
        }


        public void buyItem()
        {
            if (PD.getCreds() >= cost && owned != true)
            {
                itemBtn.setText("Equip");
                PD.removeCreds(cost);
                owned = true;
                if (item.getSpecialItem().getType() == 0)
                {
                    PD.getAbilitiesUnlocked()[item.getItemX()][item.getItemY()] = 1;
                }
                else
                {
                    PD.getSkinsUnlocked()[item.getItemX()][item.getItemY()] = 1;
                }
            }
            else
            {
                itemBtn.setText("Missing Creds");
                shop.getParaSlider().itemBtns.add(this);
            }
        }


        private void equipItem()
        {
            //see if bought
            //unequip same-typed skin, I.E. unequip plane skin if this is a new plane skin
            //equip this skin
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            AppListener.onItemButtonClick(shop, item, soldier);
            //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
        }


        //public void updateValues(int xx, int yy, int itemBtnDrop)
        //{
        //    //System.out.println("heryy");
        //    itemBtn.setBounds(xx, yy + 50, width, height);
        //}


        //@Override
        //public void actionPerformed(ActionEvent e) {
        //    onItemButtonClick(item);
        //    throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
        //}


    
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


        public int getJTWidth()
        {
            return JTWidth;
        }

        public void setJTWidth(int JTWidth)
        {
            this.JTWidth = JTWidth;
        }

        public int getJTHeight()
        {
            return JTHeight;
        }

        public void setJTHeight(int JTHeight)
        {
            this.JTHeight = JTHeight;
        }

        public JButton getItemBtn()
        {
            return itemBtn;
        }

        public void setItemBtn(JButton itemBtn)
        {
            this.itemBtn = itemBtn;
        }
        

        public JTextPane getJTPane()
        {
            return JTPane;
        }

        public void setJTPane(JTextPane JTPane)
        {
            this.JTPane = JTPane;
        }

        public int getCost()
        {
            return cost;
        }

        public void setCost(int cost)
        {
            this.cost = cost;
        }

        public int getTimeDelay()
        {
            return timeDelay;
        }

        public void setTimeDelay(int timeDelay)
        {
            this.timeDelay = timeDelay;
        }

        public boolean getOwned()
        {
            return owned;
        }

        public void setOwned(boolean owned)
        {
            this.owned = owned;
        }

        public Item getItem()
        {
            return item;
        }

        public void setWidth(Item item)
        {
            this.item = item;
        }




    }
