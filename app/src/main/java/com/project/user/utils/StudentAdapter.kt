package com.project.user.utils

import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.project.user.data.database.Student
import com.project.user.databinding.ItemStudentBinding
import com.project.user.utils.viewmodel.MainViewModel
import com.project.user.view.home.data.CrudDataActivity
import com.project.user.view.home.data.DetailDataActivity

class StudentAdapter(
    private var alumniList: List<Student>,
    private val viewModel: MainViewModel
) :
    RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    inner class StudentViewHolder(private val binding: ItemStudentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(student: Student) {
            binding.alumniName.text = student.name

            binding.root.setOnClickListener {
                showOptionsDialog(student)
            }
        }

        private fun showOptionsDialog(student: Student) {
            val options = arrayOf("Lihat Data", "Edit Data", "Hapus Data")
            val context = binding.root.context

            AlertDialog.Builder(context).apply {
                setTitle("Pilihan")
                setItems(options) { dialog, which ->
                    when (which) {
                        0 -> {
                            val intent = Intent(context, DetailDataActivity::class.java).apply {
                                putExtra("student", student)
                            }
                            context.startActivity(intent)
                        }
                        1 -> {
                            val intent = Intent(context, CrudDataActivity::class.java).apply {
                                putExtra("student", student)
                                putExtra("type", CrudDataActivity.TYPE_EDIT)
                            }
                            context.startActivity(intent)
                        }
                        2 -> {
                            showDeleteConfirmationDialog(student)
                        }
                    }
                }
                setNegativeButton("Batal", null)
                show()
            }
        }

        private fun showDeleteConfirmationDialog(student: Student) {
            AlertDialog.Builder(binding.root.context).apply {
                setTitle("Konfirmasi")
                setMessage("Apakah Anda yakin ingin menghapus data ini?")
                setPositiveButton("Ya") { dialog, _ ->
                    viewModel.removeStudent(student)
                    Toast.makeText(context, "Berhasil Menghapus Data Mahasiswa", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
                setNegativeButton("Tidak", null)
                show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = ItemStudentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StudentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(alumniList[position])
    }

    override fun getItemCount(): Int = alumniList.size

    fun updateData(newStudentList: List<Student>) {
        alumniList = newStudentList
        notifyDataSetChanged()
    }
}