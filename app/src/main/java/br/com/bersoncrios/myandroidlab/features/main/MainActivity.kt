package br.com.bersoncrios.myandroidlab.features.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.com.bersoncrios.myandroidlab.R
import br.com.bersoncrios.myandroidlab.features.horizontalRv.view.activity.HorizontalRVActivity
import br.com.bersoncrios.myandroidlab.features.starwars.view.activity.SwActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.title = getString(R.string.title_menu)
        toolbar.setTitleTextColor(resources.getColor(R.color.branco))

        btn_sw.setOnClickListener{view->
            gotoSW(view)
        }

        horizontalrv.setOnClickListener{view ->
            gotoHorizontalRv(view)
        }
    }

    private fun gotoHorizontalRv(view: View?) {
        startActivity(Intent(this, HorizontalRVActivity::class.java))
        finish()
    }

    private fun gotoSW(view:View){
        startActivity(Intent(this, SwActivity::class.java))
        finish()
    }
}
