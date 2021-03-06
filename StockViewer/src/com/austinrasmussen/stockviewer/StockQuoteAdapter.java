package com.austinrasmussen.stockviewer;
 
import java.util.List;
 
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class StockQuoteAdapter extends ArrayAdapter<StockQuote> {
    private final Activity activity;
    private final List<StockQuote> stocks;
 
    public StockQuoteAdapter(Activity activity, List<StockQuote> objects) {
        super(activity, R.layout.stock_quote_list_item , objects);
        this.activity = activity;
        this.stocks = objects;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        StockQuoteView sqView = null;
 
        if(rowView == null)
        {
            // Get a new instance of the row layout view
            LayoutInflater inflater = activity.getLayoutInflater();
            rowView = inflater.inflate(R.layout.stock_quote_list_item, null);
 
            // Hold the view objects in an object,
            // so they don't need to be re-fetched
            sqView = new StockQuoteView();
            sqView.ticker = (TextView) rowView.findViewById(R.id.ticker_symbol);
            sqView.quote = (TextView) rowView.findViewById(R.id.ticker_price);
 
            // Cache the view objects in the tag,
            // so they can be re-accessed later
            rowView.setTag(sqView);
        } else {
            sqView = (StockQuoteView) rowView.getTag();
        }
 
        // Transfer the stock data from the data object
        // to the view objects
        StockQuote currentStock = stocks.get(position);
        sqView.ticker.setText(currentStock.getTickerSymbol());
        sqView.quote.setText(currentStock.getQuote().toString());
 
        return rowView;
    }
 
    protected static class StockQuoteView {
        protected TextView ticker;
        protected TextView quote;
    }
}