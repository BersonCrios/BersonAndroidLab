package br.com.bersoncrios.myandroidlab.features.starwars.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.bersoncrios.myandroidlab.R
import kotlinx.android.synthetic.main.item_people.view.*
import kotlin.properties.Delegates
import br.com.bersoncrios.myandroidlab.features.starwars.data.Result

class PessoaAdapter : RecyclerView.Adapter<PessoaAdapter.PessoaViewHolder>() {

    private var peopleList: List<Result> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PessoaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_people, parent, false)
        return PessoaViewHolder(
            view
        )
    }

    override fun getItemCount(): Int = peopleList.size

    override fun onBindViewHolder(holder: PessoaViewHolder, position: Int) {
        if (position != RecyclerView.NO_POSITION) {
            val result: Result = peopleList[position]
            holder.bind(result)
        }
    }

    // Update data
    fun updateData(newPeopleList : List<Result>) {
        peopleList = newPeopleList
    }

    class PessoaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(people: Result) {
            itemView.tv_nome.text = people.name
            itemView.tv_sltura.text = people.height
            itemView.tv_peso.text = people.mass

//            val actionDetail =
//                CoffeeDrinkListFragmentDirections.showCoffeeDrinkDetail(
//                    coffeeDrink.id
//                )

            itemView.setOnClickListener {
                    Log.e("URL", people.url);
//                Navigation.findNavController(itemView).navigate(actionDetail)
            }
        }
    }
}