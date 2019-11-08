package hiram.liverpool

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import hiram.liverpool.databinding.ProductCardViewBinding
import hiram.liverpool.model.Item

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.ItemAdapter>() {
//    private val itemEX = Item("XBOX", "$10,000", "IMAGEN")
//    private val items = arrayOf(itemEX, itemEX.copy(), itemEX.copy())
    private val items = ArrayList<Item>()

    fun submitItems(items : List<Item>){
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemAdapter(ProductCardViewBinding.inflate(
        LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ItemAdapter, position: Int) {
        holder.bind(items[position])
        Glide.with(holder.itemView.context).load(items[position].lgImage)
                .into(holder.binding.imageViewProduct)
    }

    inner class ItemAdapter(val binding : ProductCardViewBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : Item){
            binding.apply {
                search = item
            }
        }
    }
}