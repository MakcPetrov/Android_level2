package ru.k113.kotlintest

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.ContextMenu
import android.view.MenuInflater
import android.view.View
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class StartPage : AppCompatActivity() {

    internal lateinit var toolbar: Toolbar
    internal lateinit var cityName: TextView
    internal var prefKeys = arrayOf("cityName", "localTemp", "debug")//сохраняемые настройки

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.startpage)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        cityName = findViewById(R.id.cityID)
        cityName.text = BackEnd.getCity()
        BackEnd.isLog(cityName.text.toString())
        registerForContextMenu(cityName)

        val fab = findViewById<FloatingActionButton>(R.id.fab)// Большая кнопка
        fab.setOnClickListener {
            Snackbar.make(toolbar, resources.getString(R.string.environment), Snackbar.LENGTH_LONG)
                    .setAction(resources.getString(R.string.con_text)) {
                        //     BackEnd.isLog("start secondpage");
                        startActivity(Intent(this@StartPage, SecondPage::class.java))//Окно датчиков
                    }.show()
        }//onClick
//fab

        val buttonStartService = findViewById<Button>(R.id.buttonStartService)
        buttonStartService.setOnClickListener { startService(Intent(this@StartPage, ReqSrv::class.java)) }

    }//onCreate

    // сохраняем настройки
    private fun savePreferences(sharedPref: SharedPreferences) {
        // для сохранения настроек надо воспользоваться классом Editor
        val editor = sharedPref.edit()
        // теперь в Editor установим значения
        editor.putString(prefKeys[0], BackEnd.getCity())
        editor.putFloat(prefKeys[1], BackEnd.getLtms())
        editor.putBoolean(prefKeys[2], BackEnd.isDebug())
        BackEnd.isLog("пишем настройки")
        // и сохраним файл настроек
        editor.apply()
    }//savePreferences

    private fun loadPreferences(sharedPref: SharedPreferences) {
        // для получения настроек нет необходимости в Editor, получаем их прямо из SharedPreferences
        BackEnd.isLog("читаем настройки")
        BackEnd.setCity(sharedPref.getString(prefKeys[0], "Noname"))
        BackEnd.setLtms(sharedPref.getFloat(prefKeys[1], -273f))
        BackEnd.debugLog(sharedPref.getBoolean(prefKeys[2], false))
    }//loadPreferences

    // ContextMenu
    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.context_menu, menu)
    }//onCreateContextMenu

    override fun onContextItemSelected(item: MenuItem): Boolean {
        BackEnd.setCity("Другой город")
        cityName.text = BackEnd.getCity()//перерисовать поле
        return true
    }//onContextItemSelected

    // AppBarMenu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {//Меню в АппБаре - создать
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_start_page, menu)
        return true
    }//onCreateOptionsMenu

    override fun onOptionsItemSelected(item: MenuItem): Boolean {//Меню в АппБаре - реакции
        val id = item.itemId

        when (id) {
            R.id.action_savesettings -> {
                return true
            }//action_savesettings

            R.id.action_loadsettings -> {
                return true
            }//action_loadsettings

            R.id.action_preferences -> {
                Snackbar.make(toolbar, resources.getString(R.string.action_preferences),
                        Snackbar.LENGTH_LONG).show()
                BackEnd.setLtms(113f)
                return true
            }//action_preferences
            R.id.exit -> {
                Snackbar.make(toolbar, resources.getString(R.string.end), Snackbar.LENGTH_LONG)
                        .setAction("EXIT?") {
                            Toast.makeText(this@StartPage,
                                    resources.getString(R.string.on_exit), Toast.LENGTH_LONG).show()
                            //   BackEnd.isLog("end StartPage");
                            finishAndRemoveTask()
                            //                                System.exit(0);
                        }.show()
                return true
            }//exit
        }
        return super.onOptionsItemSelected(item)
    }//onOptionsItemSelected
}
