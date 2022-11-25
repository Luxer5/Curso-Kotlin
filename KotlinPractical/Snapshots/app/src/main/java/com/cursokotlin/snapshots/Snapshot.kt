package com.cursokotlin.snapshots

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Snapshot(var id: String = "",
                    var title : String= "",
                    var photoUrl: String = "", var likeList: Map<String, Boolean > = mutableMapOf() )
