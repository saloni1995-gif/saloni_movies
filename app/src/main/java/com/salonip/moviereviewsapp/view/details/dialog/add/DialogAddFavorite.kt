package com.salonip.moviereviewsapp.view.details.dialog.add

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.activityViewModels
import com.salonip.moviereviewsapp.R
import com.salonip.moviereviewsapp.data.domain.Review
import com.salonip.moviereviewsapp.view.home.HomeViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DialogAddFavorite(
    private val review: Review
) : AppCompatDialogFragment() {

    private val homeViewModel: HomeViewModel by activityViewModels()

    // Here create a dialog
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(this.activity)
        val layoutInflater = activity?.layoutInflater
        val view = layoutInflater?.inflate(R.layout.dialog_confirm_favorite, null)!!
        builder.setView(view)
        buttons(view)

        val dialog = builder.create()
        dialog.setCanceledOnTouchOutside(true)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return dialog
    }

    private fun buttons(view: View) {
        val btnCancel = view.findViewById(R.id.btn_cancel_dialog_add) as Button
        val btnAddFavorite = view.findViewById(R.id.btn_add_favorite_dialog_add) as Button

        btnCancel.setOnClickListener{
            dismiss()
        }

        //here adding review image into favorite
        btnAddFavorite.setOnClickListener {
            GlobalScope.launch {
                homeViewModel.addReviewFavorite(review)
                dismiss()
            }
        }
    }

}