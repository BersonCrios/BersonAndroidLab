package br.com.bersoncrios.myandroidlab.features.starwars.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import br.com.bersoncrios.myandroidlab.R
import kotlinx.android.synthetic.main.layout_activity_sw.*

class SwActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_activity_sw)

        setSupportActionBar(toolbar)
        setupActionBarAndNavController()
    }

    fun setupActionBarAndNavController() {
        appBarConfiguration = AppBarConfiguration(setOf(R.id.peoplesFragment))
        setupActionBarWithNavController(navHostFragment.findNavController(), appBarConfiguration)
    }
}
