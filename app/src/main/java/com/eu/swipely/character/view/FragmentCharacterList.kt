package com.eu.swipely.character.view

import androidx.fragment.app.viewModels
import com.eu.swipely.R
import com.eu.swipely.base.BaseFragment
import com.eu.swipely.character.view.adapter.CharacterAdapter
import com.eu.swipely.character.viewmodel.CharacterViewModel
import com.eu.swipely.databinding.FragmentCharacterListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentCharacterList :
    BaseFragment<FragmentCharacterListBinding>(R.layout.fragment_character_list) {
    private val characterAdapter by lazy {
        CharacterAdapter({
            navController.navigate(
                FragmentCharacterListDirections.actionFragmentCharacterListToFragmentCharacterDetail(
                    it
                )
            )
        }, { character ->
            characterListViewModel.addOrRemoveItemFromFav(character.copy(isFav = !character.isFav))
        })
    }
    private val characterListViewModel: CharacterViewModel by viewModels()

    override fun onViewCreationCompleted() {
        binding.lifecycleOwner = this
        binding.manViewModel = characterListViewModel
        binding.characterAdapter = characterAdapter
    }
}