package me.rachid.core.fixingitemoverlap

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_first, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewManager = LinearLayoutManager(context)
        viewAdapter = MyAdapter(getList())
        view.findViewById<RecyclerView>(R.id.list).apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    private fun getList() = (1..15).map { "Item $it" }.toTypedArray()
}

class MyAdapter(private val myDataset: Array<String>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(
        viewGroup: View
    ) : RecyclerView.ViewHolder(viewGroup) {
        val button: Button = viewGroup.findViewById(R.id.button)
        val firstView: View = viewGroup.findViewById(R.id.firstView)
        val secondView: View = viewGroup.findViewById(R.id.secondView)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = MyViewHolder(
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.button.apply {
            text = myDataset[position]
            setOnClickListener {
                holder.itemView.handler?.postDelayed({
                    holder.firstView.toggleVisibility()
                    holder.secondView.toggleVisibility()
                }, 400)
            }
        }
    }

    override fun getItemCount() = myDataset.size
}

fun View.toggleVisibility() {
    visibility = if (visibility == View.GONE) View.VISIBLE else View.GONE
}
