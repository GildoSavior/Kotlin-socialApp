package com.example.repositories.user

import com.example.dao.user.UserDao
import com.example.models.AuthResponse
import com.example.models.AuthResponseData
import com.example.models.SignInParams
import com.example.models.SignUpParams
import com.example.plugins.generateToken
import com.example.security.hashPassword
import com.example.util.Response
import io.ktor.http.*

class UserRepositoryImpl (
    private val userDao: UserDao
): UserRepository {

    override suspend fun singUp(params: SignUpParams): Response<AuthResponse> {
        return  if(userAlreadyExist(params.email)) {
            Response.Error(
                code = HttpStatusCode.Conflict,
                data = AuthResponse(
                    errorMessage = "This user already exist!!"
                )
            )
        } else {
            var insertedUser = userDao.insert(params)

            if(insertedUser == null){
                Response.Error(
                    code = HttpStatusCode.InternalServerError,
                    data = AuthResponse(
                        errorMessage = "Error when signUp"
                    )
                )
            } else {
                Response.Success(
                    data = AuthResponse(
                        data = AuthResponseData(
                            id = insertedUser.id,
                            name = insertedUser.name,
                            bio = insertedUser.bio,
                            token = generateToken(insertedUser.email),
                            avatar = ""
                        )
                    )
                )
            }
        }
    }

    override suspend fun singIn(params: SignInParams): Response<AuthResponse> {
        val user = userDao.findByEmail(params.email)

       return if(user == null) {
            Response.Error(
                code = HttpStatusCode.NotFound,
                data = AuthResponse(
                    errorMessage = "Invalid credentials, no user with this email"
                )
            )
        } else {
            val hashPassword = hashPassword(params.password)

            if(user.password == hashPassword) {
                Response.Success(
                    data = AuthResponse(
                        data = AuthResponseData(
                            id = user.id,
                            name = user.name,
                            bio = user.bio,
                            avatar = user.avatar,
                            token = generateToken(user.email)
                        )
                    )
                )
            } else {
                Response.Error (
                    code =  HttpStatusCode.Forbidden,
                    data =  AuthResponse(
                        errorMessage =  "Invalid credential, wrong password!"
                    )
                )
            }
        }
    }

    private  suspend fun userAlreadyExist(email: String):Boolean {
        return userDao.findByEmail(email) != null
    }
}