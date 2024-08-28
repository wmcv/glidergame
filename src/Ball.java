import java.awt.Image;

class Ball
    {
        private double x;
        private double y;
        private int width;
        private int height;
        private int delay;
        private int counter;
        private double speedX;
        private double speedY;
        private boolean hitTarget;
        private Image img;

        public Ball(Image img, int width, int height)
        {
            this.img = img;
            this.width = width;
            this.height = height;
            counter = 0;
            hitTarget = false;
        }

        public Image getImg()
        {
            return img;
        }

        public void setImg(Image img)
        {
            this.img = img;
        }
    
        public double getX()
        {
            return x;
        }

        public void setX(double x)
        {
            this.x = x;
        }

        public double getY()
        {
            return y;
        }

        public void setY(double y)
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


        public int getDelay()
        {
            return delay;
        }

        public void setDelay(int delay)
        {
            this.delay = delay;
        }
    
        public int getCounter()
        {
            return counter;
        }

        public void setCounter(int counter)
        {
            this.counter = counter;
        }

        public double getSpeedX()
        {
            return speedX;
        }

        public void setSpeedX(double speedX)
        {
            this.speedX = speedX;
        }

        public double getSpeedY()
        {
            return speedY;
        }

        public void setSpeedY(double speedY)
        {
            this.speedY = speedY;
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
