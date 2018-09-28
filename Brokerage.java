import java.lang.reflect.*;
import java.util.*;


/**
 * this is the brokerage class that acts as the place that traders can place orders, login,
 * recieve messages, etc
 *
 * @author Brain Wang
 * @version Mar 25, 2016
 * @author Period: 5
 * @author Assignment: JMCh19_SafeTrade
 *
 * @author Sources:
 */
public class Brokerage implements Login
{
    private Map<String, Trader> traders;

    private Set<Trader> loggedTraders;

    private StockExchange exchange;


    /**
     * Creates a Brokerage object
     *
     * @param exchange
     *            the StockExchange of this Brokerage
     */
    public Brokerage( StockExchange exchange )
    {
        this.exchange = exchange;
        traders = new TreeMap<String, Trader>();
        loggedTraders = new TreeSet<Trader>();

    }


    /*
     * (non-Javadoc)
     *
     * @see Login#addUser(java.lang.String, java.lang.String)
     */
    public int addUser( String name, String password )
    {
        if ( traders.containsKey( name ) )
        {
            return -3;
        }
        else if ( name.length() > 10 || name.length() < 4 )
        {
            return -1;
        }
        else if ( password.length() < 2 || password.length() > 10 )
        {
            return -2;
        }
        else
        {
            traders.put( name, new Trader( this, name, password ) );
            return 0;
        }
    }


    /**
     * gets the quote of a certain stock and gives it to the trader
     *
     * @param symbol
     *            key to get the stock
     * @param trader
     *            someone who wants to buy
     */
    public void getQuote( String symbol, Trader trader )
    {
        String quote = exchange.getQuote( symbol );
        trader.receiveMessage( quote );
    }


    /*
     * (non-Javadoc)
     *
     * @see Login#login(java.lang.String, java.lang.String)
     */
    public int login( String name, String password )
    {
        if ( !traders.containsKey( name ) )// screen name not found
        {
            return -1;
        }
        else if ( !traders.get( name ).getPassword().equals( password ) )// invalid
        // password
        {
            System.out.println( password );
            System.out.println( "correct password: " + traders.get(name).getPassword() );
            return -2;
        }
        else if ( loggedTraders.contains( traders.get(name) ) )// if already logged in
        {
            return -3;
        }
        else
        {
            Trader t = traders.get( name );
            if ( !t.hasMessages() )
            {
                t.receiveMessage( "Welcome to SafeTrade!" );

            }
            t.openWindow();
            loggedTraders.add( t );
            return 0;

        }
    }


    /**
     * Logs out the trader
     *
     * @param trader
     *            person logging out
     */
    public void logout( Trader trader )
    {
        loggedTraders.remove( trader );
    }


    /**
     * Puts an order in to buy/sell
     *
     * @param order
     *            What the person is offering/selling
     */
    public void placeOrder( TradeOrder order )
    {
        exchange.placeOrder( order );
    }


    //
    // The following are for test purposes only
    //
    /**
     * Testing purposes, gets all Traders
     *
     * @return traders
     */
    protected Map<String, Trader> getTraders()
    {
        return traders;
    }


    /**
     * Testing purposes, gets all logged Traders
     *
     * @return
     */
    protected Set<Trader> getLoggedTraders()
    {
        return loggedTraders;
    }


    /**
     * For Testing purposes, gets the stock exchange
     *
     * @return
     */
    protected StockExchange getExchange()
    {
        return exchange;
    }


    /**
     * <p>
     * A generic toString implementation that uses reflection to print names and
     * values of all fields <em>declared in this class</em>. Note that
     * superclass fields are left out of this implementation.
     * </p>
     *
     * @return a string representation of this Brokerage.
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
                str += separator + field.getType().getName() + " " + field.getName() + ":" + field.get( this );
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