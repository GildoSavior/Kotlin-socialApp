package com.example.dao.user

import com.example.dao.DataBaseFactory.dbQuery
import com.example.models.SignUpParams
import com.example.models.User
import com.example.models.UserRow
import com.example.security.hashPassword
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class UserDaoImp: UserDao {
    override suspend fun insert(params: SignUpParams): User? {
        return dbQuery {
            val insertStatement = UserRow.insert {
                it[name] = params.name
                it[email] = params.email
                it[password] = hashPassword(params.password)
            }
            insertStatement.resultedValues?.singleOrNull()?.let {
                rowToUser(it)
            }
        }
    }

    override suspend fun findByEmail(email: String): User? {
        return dbQuery {
            UserRow.select { UserRow.email eq email }
                .map { rowToUser(it) }
                .singleOrNull()
        }
    }


    private  fun rowToUser(row: ResultRow): User {
        return User(
            id = row[UserRow.id],
            name = row[UserRow.name],
            email = row[UserRow.email],
            bio = row[UserRow.bio],
            password = row[UserRow.password],
            avatar = row[UserRow.avatar]
        )
    }
}