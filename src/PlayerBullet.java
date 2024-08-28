import java.awt.Image;

class PlayerBullet
    {
        private int x;
        private int y;
        private int width;
        private int height;
        private Image img;

        public PlayerBullet(Image img, int width, int height)
        {
            this.img = img;
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








    }
