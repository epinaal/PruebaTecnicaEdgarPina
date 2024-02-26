package com.example.pruebatecnicaedgarpina.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.pruebatecnicaedgarpina.data.entities.RickAndMortyCharacter
import com.example.pruebatecnicaedgarpina.databinding.CharacterDetailFragmentBinding
import com.example.pruebatecnicaedgarpina.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {

    private lateinit var binding: CharacterDetailFragmentBinding
    private val viewModel: CharacterDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CharacterDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt("id")?.let { viewModel.start(it) }
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.rickAndMortyCharacter.observe(viewLifecycleOwner) { result ->
            when (result.status) {
                Resource.Status.SUCCESS -> {
                    if (result.data != null) {
                        bindCharacter(result.data)
                    }
                    binding.progressBar.visibility = View.GONE
                    binding.characterCl.visibility = View.VISIBLE
                }

                Resource.Status.ERROR ->
                    Toast.makeText(activity, result.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.characterCl.visibility = View.GONE
                }
            }
        }
    }

    private fun bindCharacter(item: RickAndMortyCharacter) {
        binding.name.text = item.name
        binding.species.text = item.species
        binding.status.text = item.status
        binding.gender.text = item.gender
        Glide.with(binding.root)
            .load(item.image)
            //.transform(CircleCrop())
            .into(binding.image)
    }
}