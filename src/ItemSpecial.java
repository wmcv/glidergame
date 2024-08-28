import java.io.Serializable;

public class ItemSpecial implements Serializable
    {

        private int type;
        private String name;
        
        public ItemSpecial(String name, int type)
        {
            this.name = name;
            this.type = type;
        }

        public int getType()
        {
            return type;
        }

        public void setType(int type)
        {
            this.type = type;
        }

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public Ability getAbil()
        {
            Ability abil = (Ability) this;
            return abil;
        }

    }

    


    
