package model.enumeration;

/**
 * <pre> Provided enum type for Further Programming representing a side (i.e. a face) of a Coin</pre>
 * 
 * @author Caspar Ryan
 */
public enum CoinFace
{
   HEADS{
	   public String value() {
		   return "Heads";
	   }
   }, 
   TAILS{
	   public String value() {
		   return "Tails";
	   }
   };
   
	public abstract String value();
}