import java.lang.reflect.*;
import java.util.*;


/**
 * Represents a stock trader.
 */
/**
 * @author Siriwan Sereesathien
 * @version Mar 25, 2016
 * @author Period: 5
 * @author Assignment: JMCh19_SafeTrade
 *
 * @author Sources:
 */
/**
 * Represents a stock trader.
 */
public class Trader implements Comparable<Trader>
{
    private Brokerage brokerage;

    private String screenName, password;

    private TraderWindow myWindow;

    private Queue<String> mailbox;


    public Trader( Brokerage brokerage, String name, String pswd )
    {
        this.brokerage = brokerage;
        screenName = name;
        password = pswd;
        mailbox = new PriorityQueue<String>();
    }


    /**
     * Compares this trader to another by comparing their screen names case
     * blind.
     */
    public int compareTo( Trader other )
    {
        return this.screenName.compareToIgnoreCase( other.getName() );
    }


    /**
     * Indicates whether some other trader is "equal to" this one, based on
     * comparing their screen names case blind.
     */
    public boolean equals( Object other )
    {
        // if ( !( other instanceof Trader ) )
        // {
        // throw new ClassCastException();
        // }
        return compareTo( (Trader)other ) == 0;
    }


    /**
     * Returns the screen names for this trader
     *
     * @return the screen name for this trader
     */
    public String getName()
    {
        return this.screenName;
    }


    /**
     * Returns the password for this trader
     *
     * @return the password for this trader
     */
    public String getPassword()
    {
        return this.password;
    }


    /**
     * Requests a quote for a given stock symbol from the brokerage by calling
     * brokerage's getQuote.
     *
     * @param symbol
     *            string
     */
    public void getQuote( String symbol )
    {
        brokerage.getQuote( symbol, this );
    }


    /**
     * Returns true if this trader has any messages in its mailbox.
     *
     * @return
     */
    public boolean hasMessages()
    {
        return mailbox.peek() != null;
    }


    /**
     * Creates a new TraderWindow for this trader and saves a reference to it in
     * myWindow.
     */
    public void openWindow()
    {
        myWindow = new TraderWindow( this );
        while ( mailbox.peek() != null )
        {
            myWindow.showMessage( mailbox.remove() );

        }
    }


    /**
     * Places a given order with the brokerage by calling brokerage's
     * placeOrder.
     *
     * @param order
     */
    public void placeOrder( TradeOrder order )
    {
        if ( order == null )
            return;
        brokerage.placeOrder( order );
    }


    /**
     * Logs out this trader.
     */
    public void quit()
    {
        brokerage.logout( this );
        myWindow = null;
    }


    /**
     * Adds msg to this trader's mailbox and displays all messages.
     *
     * @param msg
     *            messsage
     */
    public void receiveMessage( String msg )
    {
        mailbox.add( msg );
        if ( myWindow != null )
        {
            while ( mailbox.peek() != null )
            {
                myWindow.showMessage( mailbox.remove() );
            }
        }
    }


    //
    // The following are for test purposes only
    //
    protected Queue<String> mailbox()
    {
        return mailbox;
    }


    /**
     * <p>
     * A generic toString implementation that uses reflection to print names and
     * values of all fields <em>declared in this class</em>. Note that
     * superclass fields are left out of this implementation.
     * </p>
     *
     * @return a string representation of this Trader.
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
                if ( field.getType().getName().equals( "Brokerage" ) )
                    str += separator + field.getType().getName() + " "
                            + field.getName();
                else
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
