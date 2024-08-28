import java.awt.Image;
import java.util.Random;

class Missile
    {
        private Random rand;
        private int x;
        private int y;
        private int width;
        private int height;
        private int velocityY;
        private int explodeHeight;
        private Image img;

        public Missile(Image img, int boardWidth, int boardHeight, int width, int height, int velocityY, int maxExplosionHeight, int minExplosionHeight)
        {
            rand = new Random();
            this.img = img;
            int xPos = rand.nextInt(boardWidth);
            x = xPos;
            y = boardHeight;
            this.width = width;
            this.height = height;
            this.velocityY = velocityY;
            explodeHeight = rand.nextInt(maxExplosionHeight)-minExplosionHeight;
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

        public int getVelocityY()
        {
            return velocityY;
        }

        public void setVelocityY(int velocityY)
        {
            this.velocityY = velocityY;
        }

        public int getExplodeHeight()
        {
            return explodeHeight;
        }

        public void setExplodeHeight(int explodeHeight)
        {
            this.explodeHeight = explodeHeight;
        }

    }
