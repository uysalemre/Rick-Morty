package com.eu.swipely.character.view

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.eu.swipely.R
import com.eu.swipely.base.BaseFragment
import com.eu.swipely.character.viewmodel.CharacterDetailViewModel
import com.eu.swipely.databinding.FragmentCharacterDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentCharacterDetail :
    BaseFragment<FragmentCharacterDetailBinding>(R.layout.fragment_character_detail) {
    private val characterDetailViewModel: CharacterDetailViewModel by viewModels()
    private val args: FragmentCharacterDetailArgs by navArgs()

    override fun onViewCreationCompleted() {
        binding.viewModel = characterDetailViewModel
        characterDetailViewModel.getCharacterEpisodes(args.characterId)
    }
}