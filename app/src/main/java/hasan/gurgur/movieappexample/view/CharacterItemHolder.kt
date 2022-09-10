package hasan.gurgur.movieappexample.view

import android.view.LayoutInflater
import android.view.ViewGroup
import hasan.gurgur.movieappexample.BaseViewHolder
import hasan.gurgur.movieappexample.databinding.ItemCharacterListLayoutBinding
import hasan.gurgur.movieappexample.model.Result

class CharacterItemHolder (
    parent: ViewGroup,
    inflater: LayoutInflater
) : BaseViewHolder<ItemCharacterListLayoutBinding>(
    binding = ItemCharacterListLayoutBinding.inflate(inflater, parent, false)
) {

    fun bind(
        character : Result,
        characterClickCallback : ((Result) -> Unit)? = null
    ) {
        with(binding) {
            binding.character = character
            binding.mainLayout.setOnClickListener{
                characterClickCallback?.invoke(character)
            }
            executePendingBindings()
        }
    }


}
