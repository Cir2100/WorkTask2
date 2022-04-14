package com.kurilov.worktask2.ui.main.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.kurilov.worktask2.databinding.FragmentInfoBinding
import com.squareup.picasso.Picasso

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class InfoFragment : Fragment() {

    private lateinit var viewModel: InfoViewModel

    private var _binding: FragmentInfoBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)
            .get(InfoViewModel::class.java)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        var idCharacter = 0
        arguments?.let{ idCharacter = InfoFragmentArgs.fromBundle(it).idCharacter }
        viewModel.getCharacterInfo(idCharacter)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupUI() {
        with(binding) {

            viewModel.dataLoading.observe(viewLifecycleOwner) { loading ->
                if (loading) {
                    progressBar.visibility = View.VISIBLE
                    characterInfoEpisodes.visibility = View.INVISIBLE
                    characterInfoGender.visibility = View.INVISIBLE
                    characterInfoImageView.visibility = View.INVISIBLE
                    characterInfoName.visibility = View.INVISIBLE
                    characterInfoSpecies.visibility = View.INVISIBLE
                    characterInfoStatus.visibility = View.INVISIBLE
                }
                else {
                    progressBar.visibility = View.INVISIBLE
                    characterInfoEpisodes.visibility = View.VISIBLE
                    characterInfoGender.visibility = View.VISIBLE
                    characterInfoImageView.visibility = View.VISIBLE
                    characterInfoName.visibility = View.VISIBLE
                    characterInfoSpecies.visibility = View.VISIBLE
                    characterInfoStatus.visibility = View.VISIBLE
                }
            }

            viewModel.character.observe(viewLifecycleOwner, {
                it?.let { ch ->
                    characterInfoName.text = ch.name
                    characterInfoSpecies.text = ch.species
                    characterInfoStatus.text = ch.status
                    characterInfoGender.text = ch.gender
                    characterInfoEpisodes.text = ch.episode.size.toString()

                    Picasso.get()
                        .load(ch.image)
                        .into(characterInfoImageView)
                }
            })

            viewModel.error.observe(viewLifecycleOwner, {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            })
        }
    }
}