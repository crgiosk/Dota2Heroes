package com.example.dotaheroes.view.fragments

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dotaheroes.api.RetrofitHelper
import com.example.dotaheroes.api.requests.HeroApiClient
import com.example.dotaheroes.databinding.FragmentHeroesListBinding
import com.example.dotaheroes.view.HeroesViewModel
import com.example.dotaheroes.view.adapters.HeroListAdapter
import kotlinx.coroutines.GlobalScope


class HeroesListFragment : Fragment() {

    private lateinit var heroesListBinding: FragmentHeroesListBinding

    private val heroViewModel: HeroesViewModel by viewModels()

    private lateinit var heroesAdapter: HeroListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        heroesListBinding = FragmentHeroesListBinding.inflate(layoutInflater, container, false)
        return heroesListBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        heroViewModel.getHeroes {
            heroesListBinding.shimmerLayout.isVisible = false
            heroesListBinding.heroesRecyclerView.visibility = View.VISIBLE
            heroesAdapter.setData(it)
        }

    }

    private fun setRecyclerView() {
         heroesAdapter = HeroListAdapter {

        }

        heroesListBinding.heroesRecyclerView.run {
            adapter = heroesAdapter
            //layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    companion object {

    }
}