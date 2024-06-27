package cl.td.suboch.alkewallet

import cl.td.suboch.alkewallet.model.User
import cl.td.suboch.alkewallet.model.network.LoginService
import cl.td.suboch.alkewallet.model.network.RetrofitInstancia
import com.google.gson.Gson
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit

class UserResponseTest {

    lateinit var user: User
    lateinit var userJson: User

    @Before
    fun setup() {
        user = User (
            id = 3696,
            first_name = "Monica",
            last_name = "T",
            email = "monica@gmail.com",
            password = "1234",
            roleId = 2,
            points = 50
        )
        userJson = User (
            id = 3695,
            first_name = "Paulina",
            last_name = "White",
            email = "Paulina@gmail.cl",
            password = "qwert",
            roleId = 1,
            points = 2070
        )
    }
    @Test
    fun `testear la creacion de entidad`() {

        Assert.assertEquals(3696, user.id)
        Assert.assertEquals("Monica", user.first_name)
        Assert.assertEquals("T", user.last_name)
        Assert.assertEquals("monica@gmail.com", user.email)
        Assert.assertEquals("1234", user.password)
        Assert.assertEquals(2, user.roleId)
        Assert.assertEquals(50, user.points)
    }
    @Test
    fun `testear deserealizacion del JSON`(){
        val json = """
                  {
                    "id": 3695,
                    "first_name": "Paulina",
                    "last_name": "White",
                    "email": "Paulina@gmail.cl",
                    "password": "qwert",
                    "points": 2070,
                    "roleId": 1,
                    "createdAt": "2024-06-27T05:42:24.000Z",
                    "updatedAt": "2024-06-27T08:40:01.000Z"
                  }
               """

        val userJson = Gson().fromJson(json, User::class.java)

        Assert.assertEquals(3695, userJson.id)
        Assert.assertEquals("Paulina", userJson.first_name)
    }

    //Test que verifica que la url de retrofit corresponde a la del servicio
    @Test
    fun testRetrofitInstance() {
        //Obtiene una intancia de nuestro retrofit
        val instance: Retrofit = RetrofitInstancia.retrofit
        //Verifica que la url configurada es la misma del servidor que estamos usando
        assert(instance.baseUrl().toUrl().toString() == "http://wallet-main.eba-ccwdurgr.us-east-1.elasticbeanstalk.com/")
    }
}