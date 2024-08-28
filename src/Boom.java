import java.awt.Image;

class Boom
    {
        private int x;
        private int y;
        private int width;
        private int height;
        private int time;
        private Image img;

        public Boom(Image img, int x, int y, int width, int height)
        {
            time = 0;
            this.img = img;
            this.width = width;
            this.height = height;
            this.x = x;
            this.y = y;
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
    }
