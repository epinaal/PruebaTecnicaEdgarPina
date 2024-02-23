package com.example.pruebatecnicaedgarpina.ui.characters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.pruebatecnicaedgarpina.data.entities.RickAndMortyCharacter
import com.example.pruebatecnicaedgarpina.databinding.UpdatedItemBinding
import timber.log.Timber
import java.util.Locale

class CharactersAdapter(private val listener: CharacterItemListener) :
    RecyclerView.Adapter<CharacterViewHolder>(), Filterable {

    interface CharacterItemListener {
        fun onItemClicked(id: Int)
    }

    private val items = ArrayList<RickAndMortyCharacter>()
    private var listRef: ArrayList<RickAndMortyCharacter>? = null
    private var mFilteredList: ArrayList<RickAndMortyCharacter>? = null

    fun setItems(items: ArrayList<RickAndMortyCharacter>) {
        if (listRef == null) {
            listRef = items
        }
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding: UpdatedItemBinding =
            UpdatedItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) =
        holder.bind(items[position])

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val charString = charSequence.toString()

                if (charString.isEmpty()) {
                    mFilteredList = listRef
                } else {
                    listRef?.let {
                        val filteredList = arrayListOf<RickAndMortyCharacter>()
                        for (dataItem in listRef!!) {
                            if (dataItem is RickAndMortyCharacter) {
                                if (charString.toLowerCase(Locale.getDefault()) in dataItem.name.toLowerCase(Locale.getDefault())) {
                                    filteredList.add(dataItem)
                                }
                            }
                        }
                        mFilteredList = filteredList
                    }
                }
                val filterResults = FilterResults()
                filterResults.values = mFilteredList
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, filterResults: FilterResults?) {
                mFilteredList = filterResults?.values as ArrayList<RickAndMortyCharacter>?
                mFilteredList?.let { setItems(it) }
            }
        }
    }
}

class CharacterViewHolder(
    private val itemBinding: UpdatedItemBinding,
    private val listener: CharactersAdapter.CharacterItemListener
) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var rickAndMortyCharacter: RickAndMortyCharacter

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: RickAndMortyCharacter) {
        this.rickAndMortyCharacter = item
        itemBinding.name.text = item.name
        itemBinding.speciesAndStatus.text = """${item.species} - ${item.status}"""
        Glide.with(itemBinding.root)
            .load(item.image)
            .transform(CircleCrop())
            .into(itemBinding.image)
    }

    override fun onClick(v: View?) {
        listener.onItemClicked(rickAndMortyCharacter.id)
    }
}