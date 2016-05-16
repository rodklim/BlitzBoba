package com.blitzboba.blitzboba;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainPage extends Activity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    int lastExpandedPosition = -1;
    String yelpUri = "https://yelp.com/biz/the-sentinel-san-francisco";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandedPosition != -1
                        && groupPosition != lastExpandedPosition) {
                    expListView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = groupPosition;
                if(groupPosition == 2){
                    Toast.makeText(getApplicationContext(),
                            "Click on each item to see a description!",
                            Toast.LENGTH_SHORT).show();
                }
//                Toast.makeText(getApplicationContext(),
//                        listDataHeader.get(groupPosition) + " Expanded",
//                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        listDataHeader.get(groupPosition) + " Collapsed",
//                        Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                if(groupPosition == 2){
                    switch(childPosition){
                        case 0:
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainPage.this);
                            builder.setTitle(listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition));
                            builder.setMessage("OG milk tea with a hint of mint");
                            builder.setPositiveButton("OK", null);
                            AlertDialog dialog = builder.show();

// Must call show() prior to fetching text view
                            TextView messageView = (TextView)dialog.findViewById(android.R.id.message);
                            messageView.setGravity(Gravity.CENTER);
//                            new AlertDialog.Builder(MainPage.this)
//                                    .setTitle(listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition))
//                                    .setMessage("OG milk tea with a hint of mint")
//                                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//                                        public void onClick(DialogInterface dialog, int which) {
//                                            //close
//                                        }
//                                    })
//                                    .setIcon(android.R.drawable.ic_menu_info_details)
//                                    .show();
//                            Toast.makeText(
//                                    getApplicationContext(),
//                                    listDataChild.get(
//                                            listDataHeader.get(groupPosition)).get(
//                                            childPosition)
//                                            + " : OG milk tea with a hint of mint" , Toast.LENGTH_LONG)
//                                            .show();
                            break;
                        case 1:
                           Toast t = Toast.makeText(
                                   getApplicationContext(),
                                   listDataChild.get(
                                           listDataHeader.get(groupPosition)).get(
                                           childPosition) + " : Thai tea with all-natural coconut milk", Toast.LENGTH_LONG);
                            TextView message = (TextView) t.getView().findViewById(android.R.id.message);
                            if( v != null)
                                message.setGravity(Gravity.CENTER);
                            t.show();
                            break;
                        case 2:
                            Toast horchata = Toast.makeText(
                                    getApplicationContext(),
                                    listDataChild.get(
                                            listDataHeader.get(groupPosition)).get(
                                            childPosition) + " : Traditional Mexican cinnamon rice water", Toast.LENGTH_LONG);
                            TextView horchataMessage = (TextView) horchata.getView().findViewById(android.R.id.message);
                            if( v != null)
                                horchataMessage.setGravity(Gravity.CENTER);
                            horchata.show();
                            break;
                        case 3:
                            Toast oreoMilkTea = Toast.makeText(
                                    getApplicationContext(),
                                    listDataChild.get(
                                            listDataHeader.get(groupPosition)).get(
                                            childPosition) + " : OG milk tea with Oreo crumbles", Toast.LENGTH_LONG);
                            TextView oreoMilkTeaMessage = (TextView) oreoMilkTea.getView().findViewById(android.R.id.message);
                            if( v != null)
                                oreoMilkTeaMessage.setGravity(Gravity.CENTER);
                            oreoMilkTea.show();
                            break;
                    }
                }

                if(groupPosition == 3){
                    switch (childPosition){
                        case 0:
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.blitzboba.com/contactus")));
                            break;
                        case 1:
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(yelpUri)));
                            break;
                    }
                }
                return false;
            }
        });
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("About Us");
        listDataHeader.add("Find Us");
        listDataHeader.add("Menu");
        listDataHeader.add("Contact");

        // Adding child data
        List<String> bio= new ArrayList<String>();
        bio.add(this.getString(R.string.large_text));
        List<String> findUs = new ArrayList<String>();



        List<String> Menu = new ArrayList<String>();
        Menu.add("Mint Mojito Milk Tea");
        Menu.add("Coconut Thai tea");
        Menu.add("Horchata");
        Menu.add("Oreo Milk Tea");
        Menu.add("Rare Candy");
        Menu.add("Caramel Apple");
        Menu.add("YEET-che");
        Menu.add("Honeydew");
        Menu.add("Strawberry");
        Menu.add("Peach");
        Menu.add("Orange Creamsicle");
        Menu.add("Taro");

        List<String> contactUs = new ArrayList<String>();
        contactUs.add("Contact Us");
        contactUs.add("Yelp");

        listDataChild.put(listDataHeader.get(0), bio); // Header, Child data
        listDataChild.put(listDataHeader.get(1), findUs);
        listDataChild.put(listDataHeader.get(2), Menu);
        listDataChild.put(listDataHeader.get(3), contactUs);
    }
}