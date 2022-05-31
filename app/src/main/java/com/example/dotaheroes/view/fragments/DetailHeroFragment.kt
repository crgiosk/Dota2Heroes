package com.example.dotaheroes.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.dotaheroes.R
import com.example.dotaheroes.api.RetrofitHelper
import com.example.dotaheroes.api.models.HeroApiResponse.Companion.transformAttribute
import com.example.dotaheroes.core.Extensions.ifNull
import com.example.dotaheroes.databinding.FragmentDetailHeroBinding
import com.example.dotaheroes.view.HeroViewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception
import kotlin.random.Random
import kotlin.random.nextInt

class DetailHeroFragment : Fragment() {

    private lateinit var detailHeroBinding: FragmentDetailHeroBinding

    private val heroViewModel: HeroViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        detailHeroBinding = FragmentDetailHeroBinding.inflate(layoutInflater, container, false)
        return detailHeroBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        heroViewModel.heroApiResponse.let { response ->
            if (response != null) {
                heroViewModel.getHeroMatches(response.id) { games ->
                    if (games.isEmpty()){
                        return@getHeroMatches
                    }
                    Picasso.get()
                        .load(RetrofitHelper.BASE_URL + response.image)
                        .into(detailHeroBinding.heroImageView, object : Callback {
                            override fun onSuccess() {

                                detailHeroBinding.includeLoading.shameDetailHero.stopShimmer()
                                detailHeroBinding.includeLoading.shameDetailHero.isVisible = false
                                detailHeroBinding.mainContainer.isVisible = true
                                var roles = String()
                                response.roles.forEach { roles+=it+", " }
                                detailHeroBinding.rolesHeroTextView.text = roles.dropLast(2)
                                detailHeroBinding.difficultHero.rating = Random.nextInt(1..3).toFloat()
                                detailHeroBinding.nameAttributeHeroTextView.text = response.localizedName + " - "  + response.primaryAttr.transformAttribute()
                                detailHeroBinding.attackType.text = response.attackType

                                detailHeroBinding.gamesPlayedTextView.text = games.sumOf { it.gamesPlayed }.toString()
                                detailHeroBinding.gamesWinsTextView.text = games.sumOf { it.wins }.toString()
                            }

                            override fun onError(e: Exception?) {
                                detailHeroBinding.includeLoading.shameDetailHero.stopShimmer()
                                detailHeroBinding.includeLoading.shameDetailHero.isVisible = false
                            }

                        })
                }
            } else {
                requireActivity().onBackPressed()
            }
        }

    }

    companion object {

    }
}