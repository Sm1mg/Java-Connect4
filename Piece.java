
public class Piece{
    
    private Player owner;   // the owner of this piece

    // constructor
    public Piece(Player p){
        owner = p;
    }
    
    // owner getter
    public Player getOwner(){
        return owner;
    }

    // toString override
    @Override
    public String toString(){
        return owner.getSymbol();
    }

    // piece comparator
    @Override
    public boolean equals(Object other){
        if(this == other){
            return true;
        }

        if(other == null){
            return false;
        }

        if(owner == ((Piece)other).getOwner()){ 
            return true;
        }

        return false;
    }
}
