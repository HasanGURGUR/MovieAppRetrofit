package hasan.gurgur.movieappexample.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hasan.gurgur.movieappexample.BaseListAdapter

class CharacterListAdapter (
    private val characterClickCallback: ((hasan.gurgur.movieappexample.model.Result?) -> Unit)?
) : BaseListAdapter<hasan.gurgur.movieappexample.model.Result>(
    itemsSame = { old, new -> old.id == new.id },
    contentsSame = { old, new -> old == new }
) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CharacterItemHolder -> {
                holder.bind(
                    character = getItem(position),
                    characterClickCallback = characterClickCallback
                )
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return CharacterItemHolder(parent, inflater)
    }

}