package br.com.bersoncrios.myandroidlab.features.starwars.view.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.system.Os.close
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager

import br.com.bersoncrios.myandroidlab.R
import br.com.bersoncrios.myandroidlab.core.view.BaseFragment
import br.com.bersoncrios.myandroidlab.core.viewmodel.ViewState
import br.com.bersoncrios.myandroidlab.features.starwars.view.adapter.PessoaAdapter
import br.com.bersoncrios.myandroidlab.features.starwars.viewmodel.StarWarsViewModel
import kotlinx.android.synthetic.main.fragment_peoples.*

class PeoplesFragment : BaseFragment<StarWarsViewModel>(true) {

    private lateinit var pessoaAdapter: PessoaAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_peoples, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        pessoaAdapter = PessoaAdapter()

        rvPessoas.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = pessoaAdapter
        }

        initViewModel()
    }

    private fun initViewModel() {

        viewModel.viewState.observe(viewLifecycleOwner, Observer { viewState ->
            when (viewState) {
                is ViewState.Error -> {
                    val builder = AlertDialog.Builder(context)
                    builder.setMessage(viewState.error)
                        .setPositiveButton(
                            R.string.close
                        ) { dialog, _ ->
                            dialog.dismiss()
                            requireActivity().finish()
                        }
                    builder.create().show()
                }
            }
        })

        viewModel.PeopleList.observe(viewLifecycleOwner, Observer { peopleList ->
            Log.e("AAA", peopleList.get(0).results.toString())
            pessoaAdapter.updateData(peopleList!!.get(0).results)
        })
    }
}
