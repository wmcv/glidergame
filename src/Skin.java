import java.awt.Image;
import java.io.Serializable;

public class Skin extends ItemSpecial implements Serializable
    {
        private String skinType;
        private Image imgL;
        private Image imgR;
        
        public Skin(Image imgL, Image imgR, String name, String skinType)
        {
            super(name, 1);
            this.imgL = imgL;
            this.imgR = imgR;
            this.skinType = skinType;
        }

        public Image getImgL()
        {
            return imgL;
        }

        public void setImgL(Image imgL)
        {
            this.imgL = imgL;
        }

        public Image getImgR()
        {
            return imgR;
        }

        public void setImgR(Image imgR)
        {
            this.imgR = imgR;
        }

        public String getSkinType()
        {
            return skinType;
        }

        public void setSkinType(String skinType)
        {
            this.skinType = skinType;
        }
    }
