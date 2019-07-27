package com.vn.edu.poly.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.vn.edu.poly.myapplication.model.category;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private RecyclerView rcCategory;

    private FloatingActionButton fab;
    private NavigationView navView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private String[] myDataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        initView();
        getData();






    }

    private void getData() {

        RequestQueue requestQueue = Volley.newRequestQueue(CategoryActivity.this);
        String url = "http://asian.dotplays.com/wp-json/wp/v2/posts?_embed";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("ketqua", response);
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            //ArrayList<category> exampleList = new ArrayList<>();
                            ArrayList<category> categories = new ArrayList<>();

                            for(int i=0 ; i<jsonArray.length();i++){



                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String slug = jsonObject.getString("slug");
                                Log.d("oo", String.valueOf(jsonArray));
                                Log.d("gggggg", String.valueOf(jsonObject));

                                JSONObject jsonObjectE = jsonObject.getJSONObject("_embedded");
                                Log.d("kk", String.valueOf(jsonObjectE));

                                JSONArray jsonArray1 = jsonObjectE.getJSONArray("wp:featuredmedia");
                                Log.d("hhhhhh", String.valueOf(jsonArray1));

                                for(int j=0 ; j<jsonArray1.length();j++) {
                                    JSONObject jsonObjectA = jsonArray1.getJSONObject(j);
                                    Log.d("cccc", String.valueOf(jsonObjectA));

                                    String source_url = jsonObjectA.getString("source_url");


                                    
                                    categories.add(new category(source_url,slug));
                                    Log.d("sourcURL ", source_url);
                                    Log.d("fffff ", String.valueOf(categories));





                                }
                                mAdapter = new CategoryAdapter(CategoryActivity.this,categories);
                                rcCategory.setHasFixedSize(true);
                                layoutManager = new LinearLayoutManager(CategoryActivity.this);
                                rcCategory.setLayoutManager(layoutManager);
                                rcCategory.setAdapter(mAdapter);



                            }




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(stringRequest);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.category, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_latest) {

            Intent intent = new Intent(CategoryActivity.this,LatestActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_category) {

            Intent intent = new Intent(CategoryActivity.this,CategoryActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_favrite) {

        } else if (id == R.id.nav_gifs) {

        } else if (id == R.id.nav_AboutUs) {

            Intent intent = new Intent(CategoryActivity.this,AboutActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        rcCategory = (RecyclerView) findViewById(R.id.rcCategory);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        navView = (NavigationView) findViewById(R.id.nav_view);

    }
}
