package com.example.myapplication

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.tabs.TabLayout
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)

        // Configurar la barra de acción con los destinos de nivel superior
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_nosotros,
                R.id.navigation_testimonios,
                R.id.navigation_productos
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

        // Configurar la navegación superior con TabLayout
        val tabLayout = binding.topNavigation

        // Agregar las pestañas
        tabLayout.addTab(tabLayout.newTab().setText(R.string.nosotros_title))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.testimonios_title))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.productos_title))

        // Configurar el listener para la navegación
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> navController.navigate(R.id.navigation_nosotros)
                    1 -> navController.navigate(R.id.navigation_testimonios)
                    2 -> navController.navigate(R.id.navigation_productos)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        // Mostrar un Snackbar cuando se hace clic en el FAB
        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Contáctanos para más información sobre apiterapia", Snackbar.LENGTH_LONG)
                .setAction("OK", null)
                .setBackgroundTint(resources.getColor(R.color.hany_black, theme))
                .setTextColor(resources.getColor(R.color.white, theme))
                .setActionTextColor(resources.getColor(R.color.hany_yellow, theme))
                .setAnchorView(R.id.fab).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}