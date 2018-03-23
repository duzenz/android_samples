package reader.duzenz.com.rss.rss;

import java.util.List;

import reader.duzenz.com.rss.base.AsyncCallbackView;
import reader.duzenz.com.rss.base.BaseMvpPresenter;
import reader.duzenz.com.rss.base.BaseView;
import reader.duzenz.com.rss.model.Feed;
import reader.duzenz.com.rss.model.RssItem;


/**
 * Created by faruktoptas on 29/01/17.
 */

public interface RssContract {

    // User actions. Presenter will implement
    interface Presenter extends BaseMvpPresenter<View> {
        void loadRssItems(Feed feed, boolean fromCache);

        void browseRssUrl(RssItem rssItem);
    }

    // Action callbacks. Activity/Fragment will implement
    interface View extends BaseView, AsyncCallbackView {
        void onRssItemsLoaded(List<RssItem> rssItems);

        void onBrowse(RssItem rssItem);
    }
}
