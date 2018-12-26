package k113.dweather;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StartPage extends AppCompatActivity  {
//implements NavigationView.OnNavigationItemSelectedListener

    Toolbar toolbar;
    TextView cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.startpage);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        RecyclerView recyclerView = findViewById(R.id.listCityes);
//        recyclerView.setHasFixedSize(true);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);

        //Список городов с выбором
// CityAdapter adapter = new CityAdapter (cityes);
//        recyclerView.setAdapter(adapter);
//
//        final Activity that = this;
//        adapter.SetOnItemClickListener(new CityAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Snackbar.make(toolbar, String.format("%s - %d", ((TextView)view).getText(), position),
//                    Snackbar.LENGTH_LONG).show();
//            }
//        });//SetOnItemClickListener


//        //Боковое меню
//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();//
//        NavigationView navigationView = findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);


        cityName = findViewById(R.id.cityID);
        cityName.setText(BackEnd.getCity());
        BackEnd.isLog(cityName.getText().toString());
        registerForContextMenu(cityName);

        FloatingActionButton fab = findViewById(R.id.fab);// Большая кнопка
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(toolbar, getResources().getString(R.string.environment), Snackbar.LENGTH_LONG)
                        .setAction(getResources().getString(R.string.con_text), new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //     BackEnd.isLog("start secondpage");
                                startActivity(new Intent(StartPage.this, SecondPage.class));//Окно датчиков
                            }
                        }).show();
            }//onClick
        });//fab

        Button buttonStartService = findViewById(R.id.buttonStartService);
        buttonStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(StartPage.this, ReqSrv.class));
            }
        });



    }//onCreate

// ContextMenu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }//onCreateContextMenu
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        BackEnd.setCity("Другой город");
        cityName.setText(BackEnd.getCity());//перерисовать поле
        return true;
    }//onContextItemSelected

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//Меню в АппБаре - создать
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start_page, menu);
        return true;
    }//onCreateOptionsMenu

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {//Меню в АппБаре - реакции
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
                    BackEnd.setLtms(113);
                return true;
            case R.id.end:
                Snackbar.make(toolbar, getResources().getString(R.string.end), Snackbar.LENGTH_LONG)
                        .setAction("EXIT?", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(StartPage.this,
                                        getResources().getString(R.string.on_exit), Toast.LENGTH_LONG).show();
                             //   BackEnd.isLog("end StartPage");
                                finishAndRemoveTask();
//                                System.exit(0);
                            }
                        }).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }//onOptionsItemSelected

}//class StartPage
