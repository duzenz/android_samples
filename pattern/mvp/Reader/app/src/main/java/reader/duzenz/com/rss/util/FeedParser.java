package reader.duzenz.com.rss.util;

import android.content.Context;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

import reader.duzenz.com.rss.model.Feed;

public class FeedParser {

    private static final String RSS_FILE = "rss.json";

    public FeedParser() {
    }

    public List<Feed> parseFeeds(Context context) {
        String jsonString = Utils.readFromAssets(context, RSS_FILE);
        Gson gson = new Gson();
        Feed[] feeds = gson.fromJson(jsonString, Feed[].class);
        return Arrays.asList(feeds);
    }
}