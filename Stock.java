import java.util.*;
import java.lang.reflect.*;
import java.text.DecimalFormat;


/**
 * Represents a stock in the SafeTrade project. A Stock object holds the stock
 * symbol, the company name, the lowest and highest sell prices, and the volume
 * for the day.It also has a priority queue for sell orders and another
 * priority queue for buy orders for that stock.
 *
 * has execute method where all the sell/buy orders are adjusted if trade is made
 * 
 * @author Siriwan Sereesathien
 * @version Mar 25, 2016
 * @author Period: 5
 * @author Assignment: JMCh19_SafeTrade
 *
 * @author Sources: None
 */
public class Stock
{
    public static DecimalFormat money = new DecimalFormat( "0.00" );

    private String stockSymbol;

    private String companyName;

    private double loPrice, hiPrice, lastPrice;

    private int volume;

    private PriorityQueue<TradeOrder> buyOrders, sellOrders;


    /**
     * Constructs a new stock with a given symbol, company name, and starting
     * price.
     *
     * @param symbol
     * @param name
     * @param price
     */
    public Stock( java.lang.String symbol, java.lang.String name, double price )
    {
        stockSymbol = symbol;
        companyName = name;
        loPrice = price;
        hiPrice = price;
        lastPrice = price;
        volume = 0;
        PriceComparator up = new PriceComparator( true );
        PriceComparator down = new PriceComparator( false );
        sellOrders = new PriorityQueue<TradeOrder>( up );
        buyOrders = new PriorityQueue<TradeOrder>( down );
    }


    /**
     * Returns a quote string for this stock.
     *
     * @return quote
     */
    public java.lang.String getQuote()
    {
        String quote = companyName + " (" + stockSymbol + ")\nPrice: " + money.format( lastPrice ) + " hi: "
                + money.format( hiPrice ) + " lo: " + money.format( loPrice ) + " vol: " + volume + "\nAsk: ";
        String sell = "";
        String buy = " Bid: ";
        if ( sellOrders.isEmpty() )
        {
            sell = "none";
        }
        else
        {
            TradeOrder order = sellOrders.peek();
            //isLimit the asking price
            if ( order.isLimit() )
            {
                sell = money.format( order.getPrice() ) + " size: " + order.getShares();
            }
            else if ( order.isMarket() )
            {
                //open to bid without asking price?
                sell = "market" + " size: " + order.getShares();
            }
        }
        quote = quote + sell;
        if ( buyOrders.isEmpty() )
        {
            buy = buy + "none";
        }
        else
        {
            TradeOrder order = buyOrders.peek();
            if ( order.isLimit() )
            {
                sell = money.format( order.getPrice() ) + " size: " + order.getShares();
            }
            else if ( order.isMarket() )
            {
                sell = "market" + " size: " + order.getShares();
            }
        }
        quote = quote + buy;
        return quote;
    }


    /**
     * Returns a quote string for this stock.
     *
     * @param order
     *            a tradeOrder
     */
    public void placeOrder( TradeOrder order )
    {
        String price;
        if ( order.isMarket() )
        {
            price = "market";
        }
        else
        {
            price = "$" + money.format( order.getPrice() );
        }
        if ( order.isBuy() )
        {
            buyOrders.add( order );
            order.getTrader().receiveMessage( "New order: Buy " + order.getSymbol() + " (" + companyName + ")\n"
                    + order.getShares() + " shares at " + price );
        }
        else if ( order.isSell() )
        {
            sellOrders.add( order );
            order.getTrader().receiveMessage( "New order: Sell " + order.getSymbol() + " (" + companyName + ")\n"
                    + order.getShares() + " shares at " + price );
        }
        executeOrders();
    }

    /**
     * Executes as many pending orders as possible.
     */
    protected void executeOrders()
    {
        // asking price: the price that the seller is asking for his stock
        // selling price: the price that the stock sold for/ the buyer bought it
        // for
        while ( !buyOrders.isEmpty() && !sellOrders.isEmpty() )
        {
            TradeOrder topSell = sellOrders.peek();
            TradeOrder topBuy = buyOrders.peek();
            double price = 0.0;

            if ( ( topSell.isLimit() && topBuy.isLimit() ) &&topBuy.getPrice() >= topSell.getPrice()  )
            {
                price = topSell.getPrice();
            }
            else if ( ( topSell.isLimit() && topBuy.isMarket() ) || ( topBuy.isLimit() && topSell.isMarket() ) )
            {
                if ( topSell.isLimit() )
                {
                    price = topSell.getPrice();
                }
                else
                {
                    price = topBuy.getPrice();
                }
            }
            else if ( topSell.isMarket() && topBuy.isMarket() )
            {
                price = lastPrice;
            }
            else
            {
                return;
            }
            int sharesToTrade = java.lang.Math.min( topSell.getShares(), topBuy.getShares() );
            topSell.subtractShares( sharesToTrade );
            topBuy.subtractShares( sharesToTrade );
            topBuy.getTrader().receiveMessage( "You bought: " + sharesToTrade + " " + topBuy.getSymbol() + " at "
                    + money.format( price ) + " amt " + sharesToTrade * price );
            topSell.getTrader().receiveMessage( "You sold: " + sharesToTrade + " " + topSell.getSymbol() + " at "
                    + money.format( price ) + " amt " + sharesToTrade * price );
            volume = volume + sharesToTrade;
            lastPrice = price;
            if ( price <= loPrice )
            {
                loPrice = price;
            }
            else if ( price >= hiPrice )
            {
                hiPrice = price;
            }
            if ( topSell.getShares() == 0 )
            {
                sellOrders.remove();
            }
            if ( topBuy.getShares() == 0 )
            {
                buyOrders.remove();
            }

        }

    }


    /**
     * Gets the stock symbol
     *
     * @return stock symbol
     */
    protected String getStockSymbol()
    {
        return stockSymbol;
    }


    /**
     * Gets the company name
     *
     * @return the company name
     */
    protected String getCompanyName()
    {
        return companyName;
    }


    /**
     * Gets the low price
     *
     * @return the lowest price
     */
    protected double getLoPrice()
    {
        return loPrice;
    }


    /**
     * Gets the high price
     *
     * @return the highest price
     */
    protected double getHiPrice()
    {
        return hiPrice;
    }


    /**
     * gets the last stock price
     *
     * @return the last price
     */
    protected double getLastPrice()
    {
        return lastPrice;
    }


    /**
     * Gets the most recent volume
     *
     * @return the volume of stocks traded today
     */
    protected int getVolume()
    {
        return volume;
    }


    /**
     * Returns the queue of buy orders
     *
     * @return buyOrders
     */
    protected PriorityQueue<TradeOrder> getBuyOrders()
    {
        return buyOrders;
    }


    /**
     * Returns the queue of sell orders
     *
     * @return sellOrders
     */
    protected PriorityQueue<TradeOrder> getSellOrders()
    {
        return sellOrders;
    }


    /**
     * <p>
     * A generic toString implementation that uses reflection to print names and
     * values of all fields <em>declared in this class</em>. Note that
     * superclass fields are left out of this implementation.
     * </p>
     *
     * @return a string representation of this Stock.
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