import java.awt.Image;
import java.util.Random;

class Plane
    {
        private Random rand;
        private int velocityX;
        private int velocityY;
        private int x;
        private int y;
        private int width;
        private int height;
        private Image img;
        private int health;
        private int index;

        public Plane(Image img, int n, int velocityX, int velocityY, int width, int height, int health, int boardWidth, int boardHeight)
        {
            rand = new Random();
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

            this.velocityX = velocityX;
            this.velocityY = velocityY;
            this.width = width;
            this.height = height;
            this.health = health;
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



        public int getVelocityX()
        {
            return velocityX;
        }

        public void setVelocityX(int velocityX)
        {
            this.velocityX = velocityX;
        }
    
        public int getVelocityY()
        {
            return velocityY;
        }

        public void setVelocityY(int velocityY)
        {
            this.velocityY = velocityY;
        }

        public int getHealth()
        {
            return health;
        }

        public void setHealth(int health)
        {
            this.health = health;
        }

        public int getIndex()
        {
            return index;
        }

        public void setIndex(int index)
        {
            this.index = index;
        }

    }
