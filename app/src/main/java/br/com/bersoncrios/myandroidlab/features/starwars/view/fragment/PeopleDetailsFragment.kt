package br.com.bersoncrios.myandroidlab.features.starwars.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import br.com.bersoncrios.myandroidlab.R
import br.com.bersoncrios.myandroidlab.core.view.BaseFragment
import br.com.bersoncrios.myandroidlab.core.viewmodel.ViewState
import br.com.bersoncrios.myandroidlab.features.starwars.viewmodel.StarWarsViewModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout_people_details_fragment.*

class PeopleDetailsFragment : BaseFragment<StarWarsViewModel>(true) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_people_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }
    private fun initViewModel() {
        val id = PeopleDetailsFragmentArgs.fromBundle(
            requireArguments()
        ).peopleId



        viewModel.viewState.observe(viewLifecycleOwner, Observer { viewState ->
            when (viewState) {
                is ViewState.Error -> {
                    Toast.makeText(context, viewState.error, Toast.LENGTH_SHORT).show()
                    view?.let { Navigation.findNavController(it).popBackStack() }
                }
            }
        })

        viewModel.peopleDetail.observe(viewLifecycleOwner, Observer { people ->
            tv_nome.text = people.name
            tv_altura.text = people.height
            tv_cor.text = people.skinColor
            tv_cordocabelo.text = people.hairColor
            tv_cordosolhos.text = people.eyeColor
            tv_peso.text = people.mass
            tv_sexo.text = people.gender
        })



        viewModel.fetchPeople(id)
    }
}
