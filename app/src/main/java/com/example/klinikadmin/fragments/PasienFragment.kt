package com.example.klinikadmin.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.klinikadmin.R
import com.example.klinikadmin.activities.DetailsPasienActivity
import com.example.klinikadmin.activities.FormPasienActivity
import com.example.klinikadmin.adapters.PasienAdapter
import com.example.klinikadmin.models.PasienModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class PasienFragment : Fragment() {
    private lateinit var fab: FloatingActionButton
    private lateinit var pasienRecyclerView: RecyclerView
    private lateinit var pasienList: ArrayList<PasienModel>
    private lateinit var dbRef : DatabaseReference



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setToolbarTitle("Pasien")
        return inflater.inflate(R.layout.fragment_pasien, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fab = view.findViewById(R.id.fab)
        fab.setOnClickListener {
            activity?.let {
                val intent = Intent(it, FormPasienActivity::class.java)
                it.startActivity(intent)
            }
        }
        pasienRecyclerView = view.findViewById(R.id.rv_pasien)
        pasienRecyclerView.layoutManager = LinearLayoutManager(activity)
        pasienRecyclerView.setHasFixedSize(true)

        pasienList = arrayListOf(PasienModel())

        getPasienData()

    }

    private fun getPasienData() {
        dbRef = FirebaseDatabase.getInstance().getReference("Pasien")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                pasienList.clear()
                if (snapshot.exists()){
                    for (pasienSnap in snapshot.children){
                        val pasienData = pasienSnap.getValue(PasienModel::class.java)
                        pasienList.add(pasienData!!)
                    }
                    val mAdapter = PasienAdapter(pasienList)
                    pasienRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : PasienAdapter.onItemCLickListener{
                        override fun onItemCLick(position: Int) {
                            activity?.let {
                                val intent = Intent(it, DetailsPasienActivity::class.java)

                                intent.putExtra("pasienID",pasienList[position].pasienId)
                                intent.putExtra("pasienName",pasienList[position].pasienName)
                                intent.putExtra("pasienNik",pasienList[position].pasienNik)
                                intent.putExtra("pasienTanggal",pasienList[position].pasienTanggal)
                                intent.putExtra("pasienKelamin",pasienList[position].pasienKelamin)
                                intent.putExtra("pasienAlamat",pasienList[position].pasienAlamat)
                                intent.putExtra("pasienNoHp",pasienList[position].pasienNoHp)
                                it.startActivity(intent)

                            }
                        }

                    })

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    @Suppress("SameParameterValue")
    private fun setToolbarTitle(title: String) {
        val activity = activity as? AppCompatActivity
        activity?.supportActionBar?.title = title
    }

}