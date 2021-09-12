package com.refrigerator2k.blueprinthelper.markupbuilder

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.refrigerator2k.blueprinthelper.R

class MarkupBuilderFragment : Fragment() {
    private lateinit var viewModel: MarkupBuilderViewModel

    private lateinit var text: String
    private var sheetWidth: Float = 0.0f
    private var markupWidth: Float = 0.0f

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_markup_builder, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.lettersRecyclerView)
        val errorText: TextView = view.findViewById(R.id.noSuchCharacterText)

        val args = MarkupBuilderFragmentArgs.fromBundle(requireArguments())
        text = args.text
        sheetWidth = args.sheetWidth

        val adapter = LettersAdapter(emptyList())
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this).get(MarkupBuilderViewModel::class.java)
        viewModel.markup.observe(viewLifecycleOwner, {
            adapter.items = ArrayList(it)
        })

        viewModel.noSuchLetterEvent.observe(viewLifecycleOwner, { letter ->
            if (letter != null) {
                recyclerView.visibility = View.GONE
                errorText.text = getString(R.string.no_such_character_error_format, letter.toString())
                viewModel.noSuchLetterEventHandled()
            }
        })

        viewModel.markupWidth.observe(viewLifecycleOwner, {
            markupWidth = it
        })

        viewModel.showMarkupInfoEvent.observe(viewLifecycleOwner, {
            if (it) {
                findNavController().navigate(
                    MarkupBuilderFragmentDirections
                        .actionMarkupBuilderFragmentToMarkupInfoFragment(text, sheetWidth, markupWidth)
                )
                viewModel.showMarkupInfoEventHandled()
            }
        })

        viewModel.setText(args.text)

        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.markup_builder_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.markupInfoMenuItem) {
            viewModel.infoPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}