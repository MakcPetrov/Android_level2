package k113.dweather;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


public class StartPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    Toolbar toolbar;
    String [] cityes = {"Москва", "Питер", "Новосибирск", "Екатеринбург", "Урюпинск", "Гадюкино"};//города TODO: обмен с BackEnd

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.startpage);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.listCityes);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

//        CityAdapter adapter = new CityAdapter (cityes);
//        recyclerView.setAdapter(adapter);

//        final Activity that = this;
//        adapter.SetOnItemClickListener(new CityAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Snackbar.make(toolbar, String.format("%s - %d", ((TextView)view).getText(), position),
//                    Snackbar.LENGTH_LONG).show();
//            }
//        });//SetOnItemClickListener

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }//onCreate

// скрипач не нужен
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

//    @Override
//    public void onCreateContextMenu()

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
            {
                Snackbar.make(toolbar, getResources().getString(R.string.action_settings), Snackbar.LENGTH_LONG)
                        .setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(StartPage.this,
                                        "OK", Toast.LENGTH_LONG).show();
                            }
                        }).show();

                return true;
            }
            case R.id.action_preferences:
                Snackbar.make(toolbar, getResources().getString(R.string.action_preferences),
                        Snackbar.LENGTH_LONG).show();
                return true;
            case R.id.end:
                Snackbar.make(toolbar, getResources().getString(R.string.end), Snackbar.LENGTH_LONG)
                        .setAction("EXIT?", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(StartPage.this,
                                        getResources().getString(R.string.on_exit), Toast.LENGTH_LONG).show();
                                        //finish();
                                        //finishAndRemoveTask();
                                        System.exit(0);
                            }
                        }).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
