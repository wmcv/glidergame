import java.awt.Image;

class Warning
    {
        private int x;
        private int y;
        private int width;
        private int height;
        private int warningCounter;
        private Image img;

        public Warning(Image img, int x, int y, int width, int height)
        {
            this.x = x;
            this.y = y;
            this.img = img;
            this.width = width;
            this.height = height;
            warningCounter = 0;
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


        public int getWarningCounter()
        {
            return warningCounter;
        }

        public void setWarningCounter(int warningCounter)
        {
            this.warningCounter = warningCounter;
        }

    }
