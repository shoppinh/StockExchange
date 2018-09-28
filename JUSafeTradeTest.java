import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.regex.*;

import org.junit.*;

import static org.junit.Assert.*;
import junit.framework.JUnit4TestAdapter;


/**
 * SafeTrade tests: TradeOrder PriceComparator Trader Brokerage StockExchange
 * Stock
 *
 * @author Brian Wang
 * 
 * @author Kevin Chen
 * @author Siriwan Sereesathien
 * @version 3/25/16
 * @author Assignment: JM Chapter 19 - SafeTrade
 * 
 * @author Sources:
 *
 */
public class JUSafeTradeTest
{
    // --Test TradeOrder
    /**
     * TradeOrder tests: TradeOrderConstructor - constructs TradeOrder and then
     * compare toString TradeOrderGetTrader - compares value returned to
     * constructed value TradeOrderGetSymbol - compares value returned to
     * constructed value TradeOrderIsBuy - compares value returned to
     * constructed value TradeOrderIsSell - compares value returned to
     * constructed value TradeOrderIsMarket - compares value returned to
     * constructed value TradeOrderIsLimit - compares value returned to
     * constructed value TradeOrderGetShares - compares value returned to
     * constructed value TradeOrderGetPrice - compares value returned to
     * constructed value TradeOrderSubtractShares - subtracts known value &
     * compares result returned by getShares to expected value
     */
    private String symbol = "GGGL";

    private boolean buyOrder = true;

    private boolean marketOrder = true;

    private int numShares = 123;

    private int numToSubtract = 24;

    private double price = 123.45;

    private boolean asc = false;

    private String screenName = "bcdef";

    private String password = "password";


    @Test
    public void tradeOrderConstructor()
    {
        TradeOrder to = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
        String toStr = to.toString();

        assertTrue( "<< Invalid TradeOrder Constructor >>",
            toStr.contains( "TradeOrder[Trader trader:null" )
                && toStr.contains( "java.lang.String symbol:" + symbol )
                && toStr.contains( "boolean buyOrder:" + buyOrder )
                && toStr.contains( "boolean marketOrder:" + marketOrder )
                && toStr.contains( "int numShares:" + numShares )
                && toStr.contains( "double price:" + price ) );
    }


    @Test
    public void TradeOrderToString()
    {
        TradeOrder to = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
        assertNotNull( to.toString() );
    }


    @Test
    public void tradeOrderGetTrader()
    {
        TradeOrder to = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
        assertNull( "<< TradeOrder: " + to.getTrader() + " should be null >>",
            to.getTrader() );
    }


    @Test
    public void tradeOrderGetSymbol()
    {
        TradeOrder to = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
        // changed to.getTrader() to to.getSymbol()???
        assertEquals(
            "<< TradeOrder: " + to.getSymbol() + " should be " + symbol + " >>",
            symbol,
            to.getSymbol() );
    }


    @Test
    public void tradeOrderIsBuy()
    {
        TradeOrder to = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );

        assertTrue(
            "<< TradeOrder: " + to.isBuy() + " should be " + buyOrder + " >>",
            to.isBuy() );
    }


    @Test
    public void tradeOrderIsSell()
    {
        TradeOrder to = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
        assertFalse(
            "<< TradeOrder: " + to.isSell() + " should be " + !buyOrder + " >>",
            to.isSell() );
    }


    @Test
    public void tradeOrderIsMarket()
    {
        TradeOrder to = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
        assertTrue( "<< TradeOrder: " + to.isMarket() + " should be "
            + marketOrder + " >>", to.isMarket() );
    }


    @Test
    public void tradeOrderIsLimit()
    {
        TradeOrder to = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );

        assertFalse( "<< TradeOrder: " + to.isLimit() + " should be "
            + !marketOrder + ">>", to.isLimit() );
    }


    @Test
    public void tradeOrderGetShares()
    {
        TradeOrder to = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
        assertTrue(
            "<< TradeOrder: " + to.getShares() + " should be " + numShares
                + ">>",
            numShares == to.getShares()
                || ( numShares - numToSubtract ) == to.getShares() );
    }


    @Test
    public void tradeOrderGetPrice()
    {
        TradeOrder to = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
        assertEquals(
            "<< TradeOrder: " + to.getPrice() + " should be " + price + ">>",
            price,
            to.getPrice(),
            0.0 );
    }


    @Test
    public void tradeOrderSubtractShares()
    {
        TradeOrder to = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
        to.subtractShares( numToSubtract );
        assertEquals(
            "<< TradeOrder: subtractShares(" + numToSubtract + ") should be "
                + ( numShares - numToSubtract ) + ">>",
            numShares - numToSubtract,
            to.getShares() );
    }


    // --Test TraderWindow Stub
    @Test
    public void traderWindowConstructor()
    {
        TraderWindow tw = new TraderWindow( null );
        assertNotNull( tw );

    }


    @Test
    public void traderWindowShowMessage()
    {
        TraderWindow tw = new TraderWindow( null );
        assertNotNull( tw );
        tw.showMessage( null );
    }


    @Test
    public void traderWindowToStringTest()
    {
        TraderWindow tw = new TraderWindow( null );
        assertNotNull( tw.toString() );
    }


    // --Test PriceComparator -- SIRIWAN SEREESATHIEN (WHOLE PRICECOMPARATOR
    // TEST)
    @Test
    public void priceComparatorConstructor()
    {
        PriceComparator p = new PriceComparator();
        assertNotNull( p );
    }


    @Test
    public void priceComparatorConstructorWithBoolean()
    {
        PriceComparator p = new PriceComparator( false );
        assertNotNull( p );
    }


    @Test
    public void priceComparatorCompare()
    {
        boolean limitOrder = false;
        PriceComparator pc = new PriceComparator();
        PriceComparator pc2 = new PriceComparator( false );
        double price2 = 234.56;
        TradeOrder one = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
        TradeOrder two = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
        TradeOrder three = new TradeOrder( null,
            symbol,
            buyOrder,
            limitOrder,
            numShares,
            price );
        TradeOrder four = new TradeOrder( null,
            symbol,
            buyOrder,
            limitOrder,
            numShares,
            price2 );

        assertEquals(
            "<< PriceComparator: compare(market, market) should be 0 >>",
            pc.compare( one, two ),
            0 );

        assertEquals(
            "<< PriceComparator: compare(market, limit) should be -1 >>",
            pc.compare( one, three ),
            -1 );

        assertEquals(
            "<< PriceComparator: compare(limit, market) should be 1 >>",
            pc.compare( three, one ),
            1 );

        assertEquals(
            "<< PriceComparator(asc): compare(limit, limit) should be "
                + (int)( ( price - price2 ) * 100 ) + " >>",
            pc.compare( three, four ),
            (int)( ( price - price2 ) * 100 ) );

        assertEquals(
            "<< PriceComparator(desc): compare(limit, limit) should be "
                + (int)( ( price2 - price ) * 100 ) + " >>",
            pc2.compare( three, four ),
            (int)( ( price2 - price ) * 100 ) );
    }


    @Test
    public void priceComparatortoStringTest()
    {
        PriceComparator p = new PriceComparator();
        assertNotNull( p.toString() );
    }


    // --Test Trader-- SIRIWAN SEREESATHIEN (WHOLE TRADER TEST)

    @Test
    public void traderToStringTest()
    {
        StockExchange ex = new StockExchange();
        Brokerage brokerage = new Brokerage( ex );
        ex.listStock( symbol, "Giggle.com", 10.00 );
        Trader trad = new Trader( brokerage, screenName, password );
        assertNotNull( trad.toString() );
    }


    @Test
    public void traderConstructor()
    {
        StockExchange ex = new StockExchange();
        Brokerage brokerage = new Brokerage( ex );
        ex.listStock( symbol, "Giggle.com", 10.00 );
        Trader trad = new Trader( brokerage, screenName, password );
        String toStr = trad.toString();

        assertTrue( "<< Invalid Trader Constructor >>",
            toStr.contains( "Trader[" ) );
        // Brokerage broker:null" )
        // && toStr.contains( "java.lang.String name:" + screenName )
        // && toStr.contains( "java.lang.String pswd:" + password ) );
    }


    @Test
    public void traderGetName()
    {
        StockExchange ex = new StockExchange();
        Brokerage brokerage = new Brokerage( ex );
        ex.listStock( symbol, "Giggle.com", 10.00 );
        Trader trad = new Trader( brokerage, screenName, password );
        assertTrue( "<< Trader: getName should be " + screenName + " >>",
            trad.getName().equals( screenName ) );
    }


    @Test
    public void traderCompareTo()
    {
        StockExchange ex = new StockExchange();
        Brokerage brokerage = new Brokerage( ex );
        ex.listStock( symbol, "Giggle.com", 10.00 );
        Trader trad = new Trader( brokerage, screenName, password );
        Trader trad2 = new Trader( brokerage, "cdefg", password );
        Trader trad3 = new Trader( brokerage, "abcde", password );

        assertEquals( "<<Trader: compareTo( trader ) should be 0 >>",
            trad.compareTo( trad ),
            0 );

        assertEquals( "<<Trader: compareTo( trader ) should be -1 >>",
            trad.compareTo( trad2 ),
            -1 );

        assertEquals( "<<Trader: compareTo( trader ) should be 1 >>",
            trad.compareTo( trad3 ),
            1 );
    }


    @Test
    public void traderGetPassword()
    {
        StockExchange ex = new StockExchange();
        Brokerage brokerage = new Brokerage( ex );
        ex.listStock( symbol, "Giggle.com", 10.00 );
        Trader trad = new Trader( brokerage, screenName, password );

        assertTrue( "<< Trader: getPassword should be " + password + " >>",
            trad.getPassword().equals( password ) );
    }


    @Test
    public void traderHasMessages()
    {
        StockExchange ex = new StockExchange();
        Brokerage brokerage = new Brokerage( ex );
        ex.listStock( symbol, "Giggle.com", 10.00 );
        Trader trad = new Trader( brokerage, screenName, password );

        assertFalse( "<< Trader should have no messages >>",
            trad.hasMessages() );
        trad.receiveMessage( "message" );
        assertTrue( "<< Trader should have messages>>", trad.hasMessages() );

    }


    @Test
    public void traderEquals()
    {
        StockExchange ex = new StockExchange();
        Brokerage brokerage = new Brokerage( ex );
        ex.listStock( symbol, "Giggle.com", 10.00 );
        Trader trad = new Trader( brokerage, screenName, password );
        Trader trad2 = new Trader( brokerage, "cdefg", password );
        Object o = new Object();

        assertTrue( "<< Traders should be equl >>", trad.equals( trad ) );
        assertFalse( "<< Traders are not equal >>", trad.equals( trad2 ) );

        try
        {
            boolean fail = trad.equals( o );
            fail( "<< not an instance of trader >>" );
        }
        catch ( ClassCastException e )
        {

        }

    }


    //
    @Test
    public void traderOpenWindow()
    {
        StockExchange ex = new StockExchange();
        Brokerage brokerage = new Brokerage( ex );
        ex.listStock( symbol, "Giggle.com", 10.00 );
        Trader trad = new Trader( brokerage, screenName, password );
        trad.receiveMessage( "message" );
        trad.openWindow();
        assertFalse( "<<Trader should have no messages left>>",
            trad.hasMessages() );
    }


    //
    @Test
    public void traderReceiveMessage()
    {
        StockExchange ex = new StockExchange();
        Brokerage brokerage = new Brokerage( ex );
        ex.listStock( symbol, "Giggle.com", 10.00 );
        Trader trad = new Trader( brokerage, screenName, password );
        assertEquals( "<< Trader: " + trad.getPassword() + " should be "
            + password + " >>", password, trad.getPassword() );
    }


    //
    @Test
    public void traderGetQuote()
    {
        StockExchange ex = new StockExchange();
        Brokerage brokerage = new Brokerage( ex );
        ex.listStock( symbol, "Giggle.com", 10.00 );
        Trader trad = new Trader( brokerage, screenName, password );
        trad.getQuote( symbol );
        assertTrue( "<< Invalid Trader getQuote >>", trad.hasMessages() );
        trad.openWindow();
        trad.getQuote( symbol );
        assertFalse( "<< Invalid Trader getQuote >>", trad.hasMessages() );
    }


    //
    @Test
    public void traderPlaceOrder()
    {
        StockExchange ex = new StockExchange();
        Brokerage brokerage = new Brokerage( ex );
        ex.listStock( symbol, "Giggle.com", 10.00 );
        Trader trad = new Trader( brokerage, screenName, password );
        TradeOrder order = new TradeOrder( trad,
            symbol,
            true,
            false,
            10,
            10.0 );
        assertTrue( "<< mailbox isn't empty >>", trad.mailbox().isEmpty() );
        trad.placeOrder( order );
        assertTrue( "<< Invalid Trader placeOrder : should have messages >>",
            trad.hasMessages() );
        trad.openWindow();
        trad.placeOrder( order );
        assertFalse( "<< Invalid Trader placeOrder: should have no messages >>",
            trad.hasMessages() );
    }


    //
    @Test
    public void traderQuit()
    {
        StockExchange ex = new StockExchange();
        Brokerage brokerage = new Brokerage( ex );
        ex.listStock( symbol, "Giggle.com", 10.00 );
        Trader trad = new Trader( brokerage, screenName, password );
        trad.quit();
        assertFalse( "<< invalid recieves messages >>",
            brokerage.getLoggedTraders().contains( trad ) );

    }


    // --Test Brokerage -- BRIAN WANG (WHOLE BROKERAGE TEST)
    public void brokerageConstructor()
    {

        StockExchange exchange = new StockExchange();

        exchange.listStock( "GGGL", "Giggle.com", 10.00 );

        Brokerage b = new Brokerage( exchange );

        String str = b.toString();

        assertTrue( "<< Invalid Brokerage Constructor >>",

            str.contains( "Brokerage[" ) );

    }


    @Test
    public void brokerageToString()

    {
        StockExchange exchange = new StockExchange();

        exchange.listStock( "GGGL", "Giggle.com", 10.00 );

        Brokerage b = new Brokerage( exchange );

        assertNotNull( b.toString() );

    }


    @Test
    public void brokerageAddUser()

    {

        StockExchange exchange = new StockExchange();

        exchange.listStock( "GGGL", "Giggle.com", 10.00 );

        Brokerage b = new Brokerage( exchange );

        assertTrue( "<< invalid name >>", b.addUser( "bob", "pass" ) == -1 );

        assertTrue( "<< invalid name >>",
            b.addUser( "traderUsers", "pass" ) == -1 );

        assertTrue( "<< invalid name >>", b.addUser( "java", "j" ) == -2 );

        assertTrue( "<< invalid name >>",
            b.addUser( "java", "traderUsers" ) == -2 );

        assertTrue( "<< invalid name >>",
            b.addUser( "java", "trader" ) == 0
                && b.getTraders().containsKey( "java" ) );

        assertTrue( "<< invalid name >>", b.addUser( "java", "user" ) == -3 );

    }


    @Test
    public void brokerageGetQuote()

    {

        StockExchange exchange = new StockExchange();

        exchange.listStock( "GGGL", "Giggle.com", 10.00 );

        Brokerage b = new Brokerage( exchange );

        Trader t = new Trader( b, screenName, password );

        b.getQuote( symbol, t );

        assertTrue( "<< Invalid Brokerage getQuote >>", t.hasMessages() );

    }


    @Test
    public void brokerageLogin()

    {
        StockExchange exchange = new StockExchange();

        exchange.listStock( "GGGL", "Giggle.com", 10.00 );

        Brokerage b = new Brokerage( exchange );

        String n = "java";

        String p = "trader";

        b.addUser( n, p );

        assertTrue( "<< invalid brokerage login >>",
            b.login( "user", p ) == -1 );

        assertTrue( "<< invalid brokerage login >>",
            b.login( n, "invalid" ) == -2 );

        assertTrue( "<< invalid brokerage login >>",
            b.login( n, p ) == 0
                && b.getLoggedTraders().contains( b.getTraders().get( n ) )
                && !b.getTraders().get( n ).hasMessages() );

        b.login( n, p );

        assertTrue( "<< invalid brokerage login >>", b.login( n, p ) == -3 );

    }


    @Test
    public void brokerageLogout()

    {
        StockExchange exchange = new StockExchange();

        exchange.listStock( "GGGL", "Giggle.com", 10.00 );

        Brokerage b = new Brokerage( exchange );

        b.addUser( "java", "user" );

        b.login( "java", "user" );

        b.logout( b.getTraders().get( "java" ) );

        assertFalse( "<< Invalid brokerage logout >>",
            b.getLoggedTraders().contains( b.getTraders().get( "java" ) ) );

    }


    @Test
    public void brokeragePlaceOrder()

    {
        StockExchange exchange = new StockExchange();

        exchange.listStock( "GGGL", "Giggle.com", 10.00 );

        Brokerage b = new Brokerage( exchange );

        Trader t = new Trader( b, screenName, password );

        TradeOrder order = new TradeOrder( t, symbol, true, false, 10, 10.0 );

        b.placeOrder( order );

        assertTrue( "<< Invalid Brokerage getQuote >>", t.hasMessages() );

        t.openWindow();

        b.placeOrder( order );

        assertFalse( "<< Invalid Brokerage getQuote >>", t.hasMessages() );

    }


    // --Test StockExchange -- KEVIN CHEN (WHOLE STOCKEXCHANGE)

    @Test
    public void stockExchangeConstructor()
    {
        StockExchange to = new StockExchange();
        String toStr = to.toString();
        assertTrue( "<< Invalid StockExchange Constructor >>",
            toStr.contains( "StockExchange[" ) );
    }


    @Test
    public void stockExchangeToString()
    {
        StockExchange to = new StockExchange();
        assertNotNull( to.toString() );
    }


    @Test
    public void stockExchangeListStock()
    {
        StockExchange to = new StockExchange();
        to.listStock( "AAAA", "apple", 10.0 );
        Map<String, Stock> listedStocks = to.getListedStocks();
        assertTrue( "<< Invalid StockExchange listStock >>",
            listedStocks.containsKey( "AAAA" ) );
    }


    @Test
    public void stockExchangeGetQuote()
    {
        StockExchange to = new StockExchange();
        to.listStock( "AAAA", "apple", 10.0 );
        String quote = to.getQuote( "AAAA" );
        assertTrue( "<< Invalid StockExchange getQuote >>", quote != null );
    }


    @Test
    public void stockExchangePlaceOrder()
    {
        StockExchange to = new StockExchange();
        Brokerage safeTrade = new Brokerage( to );
        safeTrade.addUser( "stockman", "sesame" );
        Trader trade = new Trader( safeTrade, "stockman", "sesame" );
        TradeOrder order = new TradeOrder( trade,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
        to.listStock( "GGGL", "Giggle.com", 10.00 );
        to.placeOrder( order );
        assertTrue( "<< Invalid StockExchange placeOrder >>",
            trade.hasMessages() );
        trade.openWindow();
    }


    // --Test Stock -- KEVIN CHEN (WHOLE STOCK)

    public void stockConstructor()
    {
        PriorityQueue<TradeOrder> sellOrders = new PriorityQueue<TradeOrder>(
            10, new PriceComparator() );
        PriorityQueue<TradeOrder> buyOrders = new PriorityQueue<TradeOrder>( 10,
            new PriceComparator( false ) );
        int volume = 0;
        Stock s = new Stock( symbol, screenName, price );
        String toStr = s.toString();
        assertTrue( "<< Invalid Stock Constructor >>",
            toStr.contains( "String stockSymbol:" + symbol )
                && toStr.contains( "String companyName:" + screenName )
                && toStr.contains( "double loPrice:" + price )
                && toStr.contains( "double hiPrice:" + price )
                && toStr.contains( "double lastPrice:" + price )
                && toStr.contains( "int volume:" + volume )
                && toStr.contains( "PriorityQueue buyOrders:" + buyOrders )
                && toStr.contains( "PriorityQueue sellOrders:" + sellOrders ) );
    }


    @Test
    public void StockGetQuote()
    {
        Stock stock = new Stock( symbol, "name", price );
        assertEquals( "MESSAGE",
            stock.getQuote(),
            "name (" + symbol + ")\nPrice: " + price + " hi: " + price + " lo: "
                + price + " vol: 0\nAsk: none Bid: none" );
    }


    @Test
    public void stockPlaceOrder()
    {
        StockExchange exchange = new StockExchange();
        Stock gggl = new Stock( symbol, "name", price );
        exchange.listStock( symbol, "name", price );
        Brokerage brokerage = new Brokerage( exchange );
        Trader trader = new Trader( brokerage, "name", "password" );
        TradeOrder testing = new TradeOrder( trader,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
        gggl.placeOrder( testing );
        assertEquals( gggl.getBuyOrders().isEmpty(), false );
        assertTrue( trader.hasMessages() );
    }


    @Test
    public void stockToStringTest()
    {
        Stock stock = new Stock( symbol, screenName, price );
        assertNotNull( stock.toString() );
    }


    // Remove block comment below to run JUnit test in console

    public static junit.framework.Test suite()
    {
        return new JUnit4TestAdapter( JUSafeTradeTest.class );
    }


    public static void main( String args[] )
    {
        org.junit.runner.JUnitCore.main( "JUSafeTradeTest" );
    }
}