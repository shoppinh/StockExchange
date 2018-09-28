import java.lang.reflect.*;


/**
 * Represents a buy or sell order for trading a given number of shares of a
 * specified stock.
 */
/**
 *
 *  @author  Siriwan Sereesathien
 *  @version Mar 25, 2016
 *  @author  Period: 5
 *  @author  Assignment: JMCh19_SafeTrade
 *
 *  @author  Sources: 
 */
public class TradeOrder
{
    private Trader trader;

    private String symbol;

    private boolean buyOrder;

    private boolean marketOrder;

    private int numShares;

    private double price;


    /**
     * @param trader
     *            person who would like to buy/sell
     * @param symbol
     *            the key for the stock
     * @param buyOrder
     *            is it a buy order?
     * @param marketOrder
     *            is it a market order?
     * @param numShares
     *            amount willing to buy/sell
     * @param price
     *            price that they are selling/buying at
     */
    public TradeOrder(
            Trader trader,
            String symbol,
            boolean buyOrder,
            boolean marketOrder,
            int numShares,
            double price )
    {
        this.trader = trader;
        this.symbol = symbol;
        this.buyOrder = buyOrder;
        this.marketOrder = marketOrder;
        this.numShares = numShares;
        this.price = price;
    }


    /**
     * Get the price of the order
     *
     * @return price
     */
    public double getPrice()
    {
        return price;
    }


    /**
     * Get the number of shares
     *
     * @return numShares
     */
    public int getShares()
    {
        return numShares;
    }


    /**
     * Get the symbol of the stock
     *
     * @return symbol
     */
    public String getSymbol()
    {
        return symbol;
    }


    /**
     * Get the person who is trading
     *
     * @return trader
     */

    public Trader getTrader()
    {
        return trader;
    }


    /**
     * Is it a buy order? If not, a sell order it is
     *
     * @return buyOrder
     */
    public boolean isBuy()
    {
        return buyOrder;
    }


    /**
     * Is it a limit order? if not, it is a market order
     *
     * @return !marketOrder
     */
    public boolean isLimit()
    {
        return !marketOrder;
    }


    /**
     * Is it a market order? Otherwise it is a limit order
     *
     * @return marketOrder
     */
    public boolean isMarket()
    {
        return marketOrder;
    }


    /**
     * Is it selling or buying?
     *
     * @return !buyOrder
     */
    public boolean isSell()
    {
        return !buyOrder;
    }


    /**
     * Subtracts shares from the total number if trade made
     *
     * @param shares
     *            number of shares sold
     */
    public void subtractShares( int shares )
    {
        if ( shares > numShares )
        {
            throw new IllegalArgumentException();
        }
        numShares = numShares - shares;
    }


    //
    // The following are for test purposes only
    //
    /**
     * <p>
     * A generic toString implementation that uses reflection to print names and
     * values of all fields <em>declared in this class</em>. Note that
     * superclass fields are left out of this implementation.
     * </p>
     * 
     * @return a string representation of this TradeOrder.
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
