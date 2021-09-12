package com.refrigerator2k.blueprinthelper.markupbuilder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.refrigerator2k.blueprinthelper.R

class LettersAdapter(items: List<Letter>) : RecyclerView.Adapter<LettersAdapter.ViewHolder>() {
    var items = ArrayList(items)
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.letter_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setLetter(items[position])
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(letterItemView: View) : RecyclerView.ViewHolder(letterItemView) {
        val letterText: TextView = letterItemView.findViewById(R.id.letterText)
        val startLetterText: TextView = letterItemView.findViewById(R.id.startLetterText)
        val endLetterText: TextView = letterItemView.findViewById(R.id.endLetterText)

        fun setLetter(letter: Letter) {
            letterText.text = letter.letter.toString()
            startLetterText.text = "${letter.start} мм."
            endLetterText.text = "${letter.end} мм."
        }
    }
}