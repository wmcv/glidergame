import java.awt.Image;

public class Parachute
    {
        private int width;
        private int height;
        private Image img;
        
        public Parachute(Image img, int width, int height)
        {
            this.img = img;
            this.width = width;
            this.height = height;
        }

        public void setPara(Image img){this.img = img;}
        
        public Image getPara()
        {
            return img;
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
