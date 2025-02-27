package com.example.android3.assignment.Model;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android3.assignment.Adapter.Adapter_ListView_Rss;
import com.example.android3.assignment.R;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class ReadRss extends AsyncTask<Void,Void,Void> {
    Context context;
    ListView listView;
    List<FeedItem> feedItemList;

    String address = "https://vnexpress.net/rss/the-gioi.rss";
    URL url;

    public ReadRss(Context context, ListView listView) {
        this.listView= listView;
        this.context = context;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Adapter_ListView_Rss adapter = new Adapter_ListView_Rss(context, R.layout.raw_feeditem,feedItemList);
        listView.setAdapter(adapter);

    }

    @Override
    protected Void doInBackground(Void... voids) {
        processXml(getData());
        return null;
    }

    public Document getData(){
        try {
            url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document xmlDoc = builder.parse(inputStream);
            return xmlDoc;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void processXml(Document data){
        if (data != null){
            feedItemList = new ArrayList<>();
            Element root = data.getDocumentElement();
            Node channel = root.getChildNodes().item(1);
            NodeList items = channel.getChildNodes();
            for (int i =0; i < items.getLength() ; i++){
                Node currentChild = items.item(i);
                if (currentChild.getNodeName().equalsIgnoreCase("item")){
                    FeedItem feedItem = new FeedItem();
                    NodeList itemChild = currentChild.getChildNodes();
                    for (int j = 0; j < itemChild.getLength(); j++){
                        Node current = itemChild.item(j);
                        if (current.getNodeName().equalsIgnoreCase("title")){
                            feedItem.setTitle(current.getTextContent());
                        }else if (current.getNodeName().equalsIgnoreCase("description")){
                            feedItem.setDescription(current.getTextContent());
                        }else if (current.getNodeName().equalsIgnoreCase("pubDate")){
                            feedItem.setPubDate(current.getTextContent());
                        }else if (current.getNodeName().equalsIgnoreCase("link")){
                            feedItem.setLink(current.getTextContent());
                        }else if (current.getNodeName().equalsIgnoreCase("media:thumbnail")){
                            String url = current.getAttributes().item(0).getTextContent();
                            feedItem.setImgUrl(url);
                        }
                    }
                    feedItemList.add(feedItem);
                    Log.d("root",feedItem.getPubDate());
                }
            }
        }
    }
}
