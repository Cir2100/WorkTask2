package com.kurilov.worktask2.ui.main.characters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.kurilov.worktask2.R
import com.kurilov.worktask2.databinding.FragmentCharatersBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class CharatersFragment : Fragment() {

    private var _binding: FragmentCharatersBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCharatersBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //binding.buttonFirst.setOnClickListener {
        //    findNavController().navigate(R.id.action_CharatersFragment_to_InfoFragment)
        //}
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}