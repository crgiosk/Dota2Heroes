package com.example.dotaheroes.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dotaheroes.R
import com.example.dotaheroes.databinding.FragmentHeroesListBinding
import com.example.dotaheroes.view.HeroViewModel
import com.example.dotaheroes.view.adapters.HeroListAdapter


class HeroesListFragment : Fragment() {

    private lateinit var heroesListBinding: FragmentHeroesListBinding

    private val heroViewModel: HeroViewModel by activityViewModels()

    private lateinit var heroesAdapter: HeroListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        heroesListBinding = FragmentHeroesListBinding.inflate(layoutInflater, container, false)
        return heroesListBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        setupViewEvents()
        heroViewModel.getHeroes {
            heroesListBinding.shimmerLayout.isVisible = false
            heroesListBinding.heroesRecyclerView.visibility = View.VISIBLE
            heroesAdapter.setData(it)
        }

    }

    private fun setupViewEvents() {
        heroesListBinding.searchViewHero.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    heroesAdapter.filter.filter(query)
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    heroesAdapter.filter.filter(newText)
                    return false
                }

            }
        )
    }

    private fun setRecyclerView() {
         heroesAdapter = HeroListAdapter {
             heroViewModel.heroApiResponse = it
             findNavController().navigate(R.id.action_heroesList_to_detailHeroFragment)
        }

        heroesListBinding.heroesRecyclerView.run {
            adapter = heroesAdapter
            //layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }
}