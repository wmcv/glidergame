import java.awt.Image;

public class Ability extends ItemSpecial
    {
        private boolean active;
        private boolean offCooldown;
        private final int usageTime;
        private int delay;
        private Image img; //set null if passive?

        public Ability(Image img, String name, int usageTime)
        {
            super(name, 0);
            this.img = img;
            active = false;
            offCooldown = true;
            this.usageTime = usageTime;
            delay = 0;
        }

        public int getUsageTime()
        {
            return usageTime;
        }

        public int getDelay()
        {
            return delay;
        }

        public void setDelay(int delay)
        {
            this.delay = delay;
        }

        public boolean getOffCooldown()
        {
            return offCooldown;
        }

        public void setOffCooldown(boolean offCooldown)
        {
            this.offCooldown = offCooldown;
        }

        public boolean getActive()
        {
            return active;
        }

        public void setActive(boolean active)
        {
            this.active = active;
        }

        public Image getImg()
        {
            return img;
        }

        public void setImg(Image img)
        {
            this.img = img;
        }


    }