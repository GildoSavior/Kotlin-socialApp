package com.example.models

import org.jetbrains.exposed.sql.Table

object UserRow: Table(name = "users") {
     val id =  integer(name = "user_id").autoIncrement()
     val name = varchar(name = "user_name", length = 250)
     val email = varchar(name = "user_email", length = 250)
     val bio = text(name = "user_bio").default(
          defaultValue = "Hey, what's up? Welcome"
     )
     val password = varchar("user_password", length = 100)
     val avatar  = text("user_avatar").nullable()

     override  val primaryKey: PrimaryKey
          get() = PrimaryKey(id)
}


data class User(
     val id: Int,
     val name : String,
     val email : String,
     val bio : String,
     val password : String,
     val avatar : String?
)