import java.awt.Image;

class Transport
    {
        private int x;
        private int y;
        private int width;
        private int height;
        private int speed;
        private Image img;

        public Transport(Image img, int x, int y, int width, int height, int speed)
        {
            this.img = img;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.speed = speed;
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

        public int getSpeed()
        {
            return speed;
        }

        public void setSpeed(int speed)
        {
            this.speed = speed;
        }
    }