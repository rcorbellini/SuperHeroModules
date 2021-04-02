package feature.superhero.presentation.ui.list

import android.view.View
import com.corbellini.hero_detail.R
import com.corbellini.hero_detail.databinding.ItemHeroBinding
import feature.superhero.presentation.models.HeroPresentation
import me.ibrahimyilmaz.kiel.adapterOf
import me.ibrahimyilmaz.kiel.core.RecyclerViewHolder

internal class HeroViewHolder(
    view: View
) : RecyclerViewHolder<HeroPresentation>(view) {
    val binding = ItemHeroBinding.bind(view)
    override fun bind(position: Int, item: HeroPresentation) {
        super.bind(position, item)
        binding.executePendingBindings()
    }
}

internal inline fun createHeoresAdapter( onClick: (HeroPresentation) -> Unit) =
    adapterOf<HeroPresentation> {
        diff(
            areItemsTheSame = { old, new -> old === new },
            areContentsTheSame = { old, new -> old === new}
        )
        register(
            layoutResource = R.layout.item_hero,
            viewHolder = ::HeroViewHolder,
            onBindViewHolder = { vh, _, heroPresentation ->
                vh.binding.hero = heroPresentation
            }
        )
    }