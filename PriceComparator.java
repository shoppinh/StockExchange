
/**
 * A price comparator for trade orders.
 */
import java.lang.reflect.Field;


/**
 * A price comparator for trade orders.
 *
 * @author Siriwan Sereesathien
 * @version Mar 25, 2016
 * @author Period: 5
 * @author Assignment: JMCh19_SafeTrade
 *
 * @author Sources:
 */
public class PriceComparator implements java.util.Comparator<TradeOrder>
{
    private boolean ascending;


    public PriceComparator()
    {
        ascending = true;
    }


    public PriceComparator( boolean asc )
    {
        ascending = asc;
    }


    public int compare( TradeOrder order1, TradeOrder order2 )
    {
        if ( order1.isMarket() && order2.isMarket() )
        {
            return 0;
        }
        if ( order1.isMarket() && order2.isLimit() )
        {
            return -1;
        }
        if ( order1.isLimit() && order2.isMarket() )
        {
            return 1;
        }
        else
        {
            int cents1 = (int)( order1.getPrice() * 100 );
            int cents2 = (int)( order2.getPrice() * 100 );
            if ( ascending )
            {
                return cents1 - cents2; // round to nearest cent
            }
            else
            {
                return cents2 - cents1;// round to nearest cent
            }
        }
    }

    public String toString()
    {
        String str = this.getClass().getName() + "[";
        String separator = "";

        Field[] fields = this.getClass().getDeclaredFields();

        for ( Field field : fields )
        {
            try
            {
                str += separator + field.getType().getName() + " "
                    + field.getName() + ":" + field.get( this );
            }
            catch ( IllegalAccessException ex )
            {
                System.out.println( ex );
            }

            separator = ", ";
        }

        return str + "]";
    }

}
