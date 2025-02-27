package com.example.android3.assignment.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android3.assignment.Acitivities.MainActivity;
import com.example.android3.assignment.Acitivities.WebViewActivity;
import com.example.android3.assignment.Model.FeedItem;
import com.example.android3.assignment.R;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter_ListView_Rss extends BaseAdapter {
    Context context;
    int layout;
    List<FeedItem> feedItemList;
    ShareLinkContent shareLinkContent;
    ShareDialog shareDialog;

    public Adapter_ListView_Rss(Context context, int layout, List<FeedItem> feedItemList) {
        this.context = context;
        this.layout = layout;
        this.feedItemList = feedItemList;
    }

    @Override
    public int getCount() {
        return feedItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout, null);
        final FeedItem feedItem = feedItemList.get(position);

        final TextView txt_Title = (TextView) convertView.findViewById(R.id.rawFeedItem_TxtTitle);
        TextView txt_PubDate = (TextView) convertView.findViewById(R.id.rawFeedItem_TxtPubDate);
        ImageView img_Share = (ImageView) convertView.findViewById(R.id.rawFeedItem_ImgShare);

        txt_Title.setText(feedItem.getTitle());
        txt_PubDate.setText(feedItem.getPubDate());
        shareDialog = new ShareDialog(((MainActivity)context));

        txt_Title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra("Link", feedItem.getLink());
                context.startActivity(intent);
            }
        });
        img_Share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ShareDialog.canShow(ShareLinkContent.class)){
                    shareLinkContent = new ShareLinkContent.Builder()
                        .setContentTitle(feedItem.getTitle())
                        .setContentUrl(Uri.parse(feedItem.getLink()))
                        .build();
                }
                shareDialog.show(shareLinkContent);
            }
        });
        return convertView;
    }
}
