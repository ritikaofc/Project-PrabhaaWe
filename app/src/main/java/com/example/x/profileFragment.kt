package com.example.x




import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth


class profileFragment : Fragment() {

    private lateinit var mAuth: FirebaseAuth
  //  private lateinit var logout:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        mAuth= FirebaseAuth.getInstance()
       // setHasOptionsMenu(true)

//        Logout.setOnClickListener(object : View.OnClickListener {
//            override fun onClick(v: View?) {
//                if (mAuth.currentUser != null) mAuth.signOut()
//                val intent = Intent(activity, login_activity::class.java)
//                startActivity(intent)
//            }
//        })


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    companion object {


        @JvmStatic
        fun newInstance() = profileFragment()

    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        menu.clear();
//        // Add the new menu items
//        inflater.inflate(R.menu.menu_dots, menu)
//        super.onCreateOptionsMenu(menu, inflater)
//    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//      if(item.itemId==R.id.logOut){
//          //Write the logic for logout
//          mAuth.signOut()
//          moveToNewActivity()
//          return true
//      }
//        return true;
//    }


//    private fun moveToNewActivity() {
//        val i = Intent(activity, login_activity::class.java)
//        startActivity(i)
//        (activity as Activity?)!!.overridePendingTransition(0, 0)
//    }
}