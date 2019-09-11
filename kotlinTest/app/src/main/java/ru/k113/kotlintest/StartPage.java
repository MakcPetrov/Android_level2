package ru.k113.kotlintest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StartPage extends AppCompatActivity {

    Toolbar toolbar;
    TextView cityName;
    String[] prefKeys = {"cityName","localTemp","debug"};//сохраняемые настройки

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

    // сохраняем настройки
    private void savePreferences(SharedPreferences sharedPref){
        // для сохранения настроек надо воспользоваться классом Editor
        SharedPreferences.Editor editor = sharedPref.edit();
        // теперь в Editor установим значения
        editor.putString(prefKeys[0], BackEnd.getCity());
        editor.putFloat(prefKeys[1],BackEnd.getLtms());
        editor.putBoolean(prefKeys[2],BackEnd.isDebug());
        BackEnd.isLog("пишем настройки");
        // и сохраним файл настроек
        editor.apply();
    }//savePreferences

    private void loadPreferences(SharedPreferences sharedPref){
        // для получения настроек нет необходимости в Editor, получаем их прямо из SharedPreferences
        BackEnd.isLog("читаем настройки");
        BackEnd.setCity(sharedPref.getString(prefKeys[0], "Noname"));
        BackEnd.setLtms(sharedPref.getFloat(prefKeys[1], -273));
        BackEnd.debugLog(sharedPref.getBoolean(prefKeys[2], false));
    }//loadPreferences

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
    // AppBarMenu
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
            case R.id.action_savesettings: {
                Snackbar.make(toolbar, getResources().getString(R.string.action_savesettings), Snackbar.LENGTH_LONG)
                        .setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
                                BackEnd.isLog("save");
                                savePreferences(sharedPref);    // сохранить настройки
                            }
                        }).show();
                return true;
            }//action_savesettings

            case R.id.action_loadsettings: {
                Snackbar.make(toolbar, getResources().getString(R.string.action_loadsettings), Snackbar.LENGTH_LONG)
                        .setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
                                BackEnd.isLog("load");
                                loadPreferences(sharedPref);    // загрузить настройки
                            }
                        }).show();
                return true;
            }//action_loadsettings

            case R.id.action_preferences:
            {Snackbar.make(toolbar, getResources().getString(R.string.action_preferences),
                    Snackbar.LENGTH_LONG).show();
                BackEnd.setLtms(113);
                return true;
            }//action_preferences
            case R.id.exit: {
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
            }//exit
        }
        return super.onOptionsItemSelected(item);
    }//onOptionsItemSelected
}
