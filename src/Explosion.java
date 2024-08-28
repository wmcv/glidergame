import java.awt.Image;

public class Explosion
    {
        private int x;
        private int y;
        private int width;
        private int height;
        private int time = 0;
        private boolean hitTarget = false;
        private Image img;

        public Explosion(Image img, int x, int y, int width, int height)
        {
            this.img = img;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
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

        public int getTime()
        {
            return time;
        }

        public void setTime(int time)
        {
            this.time = time;
        }

        public boolean getHitTarget()
        {
            return hitTarget;
        }

        public void setHitTarget(boolean hitTarget)
        {
            this.hitTarget = hitTarget;
        }

    }
