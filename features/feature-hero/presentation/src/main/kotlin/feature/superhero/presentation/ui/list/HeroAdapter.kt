package feature.superhero.presentation.ui.list

import android.view.View
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.corbellini.presentation.R
import com.corbellini.presentation.databinding.ItemHeroBinding
import feature.superhero.presentation.models.HeroPresentation
import me.ibrahimyilmaz.kiel.adapterOf
import me.ibrahimyilmaz.kiel.core.RecyclerViewHolder
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

import com.bumptech.glide.load.resource.bitmap.CenterCrop
import kotlin.random.Random

internal class HeroViewHolder(
    view: View
) : RecyclerViewHolder<HeroPresentation>(view) {
    val binding = ItemHeroBinding.bind(view)
    override fun bind(position: Int, item: HeroPresentation) {
        super.bind(position, item)
        binding.executePendingBindings()
    }
}

internal fun createHeoresAdapter( onClick: (HeroPresentation) -> Unit) =
    adapterOf<HeroPresentation> {
        diff(
            areItemsTheSame = { old, new ->
                old == new },
            areContentsTheSame = { old, new ->
                old.name == new.name}
        )
        register(
            layoutResource = R.layout.item_hero,
            viewHolder = ::HeroViewHolder,
            onBindViewHolder = { vh, _, heroPresentation ->
                vh.binding.hero = heroPresentation
                vh.binding.heroCard.setOnClickListener{onClick(heroPresentation)}
                vh.binding.heroCard.preventCornerOverlap = false
                val scale: Float = vh.itemView.context.resources.displayMetrics.density
                val random = Random.nextInt(70)
                val pixels = ((300  - random) * scale + 0.5f).toInt()
                vh.binding.heroCard.layoutParams.height = pixels
                val imgUrl = heroPresentation.image.url
                val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()

                Glide.with(vh.itemView.context)
                    .load(imgUri)
                    .diskCacheStrategy( DiskCacheStrategy.ALL )
                    .into(vh.binding.imageView)
            }
        )
    }