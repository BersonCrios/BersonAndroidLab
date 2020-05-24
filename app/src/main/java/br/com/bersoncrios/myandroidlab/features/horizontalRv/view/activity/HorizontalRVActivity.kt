package br.com.bersoncrios.myandroidlab.features.horizontalRv.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.bersoncrios.myandroidlab.R
import br.com.bersoncrios.myandroidlab.features.horizontalRv.view.adapter.ItemAdapter
import br.com.bersoncrios.myandroidlab.features.horizontalRv.model.Item
import kotlinx.android.synthetic.main.activity_horizontal_r_v.*

class HorizontalRVActivity : AppCompatActivity() {

    private val itemAdapter by lazy {
        ItemAdapter { position: Int, item: Item ->
            item_list.smoothScrollToPosition(position)
        }
    }

    private val possibleItems = listOf(
        Item(
            "Facebook",
            R.drawable.ic_facebook
        ),
        Item(
            "Instagram",
            R.drawable.ic_instagram
        ),
        Item(
            "Whatsapp",
            R.drawable.ic_whatsapp
        ),
        Item(
            "Linkedin",
            R.drawable.ic_linkedin
        ),
        Item(
            "Youtube",
            R.drawable.ic_youtube
        ),
        Item(
            "Twitter",
            R.drawable.ic_twitter
        )
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horizontal_r_v)

        item_list.initialize(itemAdapter)
        item_list.setViewsToChangeColor(listOf(R.id.list_item_background, R.id.list_item_text))
        itemAdapter.setItems(getLargeListOfItems())
    }

    private fun getLargeListOfItems(): List<Item> {
        val items = mutableListOf<Item>()
        (0..40).map { items.add(possibleItems.random()) }
        return items
    }
}