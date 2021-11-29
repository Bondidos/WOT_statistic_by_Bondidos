package com.bondidos.wotstatisticbybondidos.domain.other

import android.content.Context
import android.widget.Toast
import com.bondidos.wotstatisticbybondidos.data.entiyes.Data
import com.bondidos.wotstatisticbybondidos.domain.entityes.User

fun makeToast(context: Context, message: String){
    Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
}


