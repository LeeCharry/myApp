//package sgm_dy_ppep.ppep.shanghaigm.com.mygridrecapplication
//
//import android.os.Bundle
//import android.support.v7.app.AppCompatActivity
//import android.support.v7.widget.GridLayoutManager
//import android.support.v7.widget.RecyclerView
//import android.view.View
//import com.chad.library.adapter.base.BaseQuickAdapter
//import com.chad.library.adapter.base.BaseSectionQuickAdapter
//import com.chad.library.adapter.base.BaseViewHolder
//import com.example.jack.myapp.R
//import com.example.jack.myapp.demo.mutableRV.MySection
//import com.example.jack.myapp.demo.mutableRV.myitem
//import kotlinx.android.synthetic.main.activity_mutable_main.*
//import java.util.*
//
//class MainActivity : AppCompatActivity(), BaseQuickAdapter.OnItemClickListener {
//
//    var adapter1: adapter? = null
//    var lm: RecyclerView.LayoutManager? = null
//    lateinit var list: MutableList<MySection>
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_mutable_main)
//        init()
//    }
//
//    fun init() {
//        list = ArrayList()
//        for (i in 0..30) {
//            var data: MySection? = null
//            if (i == 0 || i == 15) {
//                data = MySection(true, "title", true)
//            } else {
//                var item = myitem()
//                item.name = "A1" + i
//                if (i > 15)
//                    item.type = 2
//                else
//                    item.type = 1
//                data = MySection(item)
//            }
//            list.add(data)
//        }
//        adapter1 = adapter(R.layout.adapterlayout, R.layout.titlelayout, list)
//        lm = GridLayoutManager(this, 4)
//        recyclerView.layoutManager = lm
//        recyclerView.adapter = adapter1
//        adapter1!!.setOnItemClickListener(this)
//    }
//
//    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
////        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//        val mySection = list.get(position)
//        var boo = 0
//        if (mySection.isHeader) {
//
//        } else {
//            if (mySection.t.type == 2) {
//
//                for (item in list.indices) {
//                    if (list.get(item).isHeader) {
//                        boo++
//                    }
//                    if (boo == 2) {
//                        list.removeAt(position)
//                        list.add(item - 1, mySection)
//                    }
//                    adapter1!!.notifyDataSetChanged()
//                }
//            } else {
//
//            }
//        }
//    }
//
//    class adapter(layoutResId: Int, sectionHeadResId: Int, data: MutableList<MySection>?) : BaseSectionQuickAdapter<MySection, BaseViewHolder>(layoutResId, sectionHeadResId, data) {
//        var list: MutableList<MySection>? = null
//
//        init {
//            this.list = data
//        }
//
//        override fun convert(helper: BaseViewHolder?, item: MySection?) {
////            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            var video = item!!.t
//            helper!!.setText(R.id.item, video.name)
//        }
//
//        override fun convertHead(helper: BaseViewHolder?, item: MySection?) {
////            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            helper!!.setText(R.id.title, item!!.header)
//        }
//    }
//}
