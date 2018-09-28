import java.lang.reflect.*;
import java.util.*;


/**
 * Represents a stock exchange. A <code>StockExchange</code> keeps a
 * <code>HashMap</code> of stocks, keyed by a stock symbol. It has methods to
 * list a new stock, request a quote for a given stock symbol, and to place a
 * specified trade order.
 * 
 * @author Siriwan Sereesathien
 * 
 * @version Mar 25, 2016
 * @author Period: 5
 * @author Assignment: JMCh19_SafeTrade
 *
 * @author Sources: None
 */
public class StockExchange
{
    private Map<String, Stock> listedStocks;


    public StockExchange()
    {
        listedStocks = new HashMap<String, Stock>();
    }


    public String getQuote( String symbol )
    {
        if ( symbol == null )
        {
            return "";
        }
        if ( listedStocks.get( symbol ) != null )
        {
            return listedStocks.get( symbol ).getQuote();
        }
        else
        {
            return symbol + "not found";
        }
    }


    public void listStock( String symbol, String name, double price )
    {
        if ( symbol == null )
        {
            return;
        }
        listedStocks.put( symbol, new Stock( symbol, name, price ) );
    }


    public void placeOrder( TradeOrder order )
    {
        if ( order == null )
        {
            return;
        }
        Stock s = listedStocks.get( order.getSymbol() );
        if ( s != null )
        {
            s.placeOrder( order );
        }
        else
        {
            order.getTrader()
                .receiveMessage( order.getSymbol() + " not found" );
        }
    }


    //
    // The following are for test purposes only
    //
    protected Map<String, Stock> getListedStocks()
    {
        return listedStocks;
    }


    /**
     * <p>
     * A generic toString implementation that uses reflection to print names and
     * values of all fields <em>declared in this class</em>. Note that
     * superclass fields are left out of this implementation.
     * </p>
     * 
     * @return a string representation of this StockExchange.
     */
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