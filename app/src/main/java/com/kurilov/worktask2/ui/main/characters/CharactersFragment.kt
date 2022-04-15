package com.kurilov.worktask2.ui.main.characters

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kurilov.worktask2.databinding.FragmentCharactersBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class CharactersFragment : Fragment() {


    private lateinit var viewModel: CharactersViewModel

    private lateinit var charactersAdapter: CharactersAdapter

    private var _binding: FragmentCharactersBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCharactersBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)
            .get(CharactersViewModel::class.java)
        viewModel.loadCharacters(1)

        charactersAdapter = CharactersAdapter(object : CharactersAdapter.ActionListener {
            override fun onClickItem(id : Int) {
                val action = CharactersFragmentDirections.actionCharactersFragmentToInfoFragment(id)
                findNavController().navigate(action)
            }

            override fun onLoadMore(id : Int) {
                viewModel.loadCharacters(id)
            }
        })

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupUI() {

        with(binding) {

            charactersRecyclerView.layoutManager = LinearLayoutManager(activity)
            charactersRecyclerView.adapter = charactersAdapter

            viewModel.dataLoading.observe(viewLifecycleOwner) { loading ->
                if (loading) {
                    progressBar.visibility = View.VISIBLE
                    charactersRecyclerView.visibility = View.INVISIBLE
                }
                else {
                    progressBar.visibility = View.INVISIBLE
                    charactersRecyclerView.visibility = View.VISIBLE
                }
            }

            viewModel.characters.observe(viewLifecycleOwner, {
                charactersAdapter.addItems(it)
            })

            viewModel.error.observe(viewLifecycleOwner, {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            })
        }
    }
}