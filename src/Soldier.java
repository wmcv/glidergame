import java.awt.Image;

public class Soldier
    {
        private int x;
        private int y;
        private int width;
        private int height;
        private int abilX;
        private int abilY;
        private int acc;
        private Image img;

        public Soldier(Image img, int x, int y, int width, int height, int acc)
        {
            this.img = img;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            abilX = 0;
            abilY = 0;
            this.acc = acc;
        }
        public void setSold(Image img){this.img = img;}

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


        public int getAbilX()
        {
            return abilX;
        }

        public void setAbilX(int abilX)
        {
            this.abilX = abilX;
        }

        public int getAbilY()
        {
            return abilY;
        }

        public void setAbilY(int abilY)
        {
            this.abilY = abilY;
        }

        public int getAcc()
        {
            return acc;
        }

        public void setAcc(int acc)
        {
            this.acc = acc;
        }

    }
