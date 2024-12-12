package com.example.proyectofinal

//Librerias utilizadas para las funciones
import androidx.appcompat.app.AppCompatActivity
import android.content.ContentValues
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.Toast
import com.example.proyectofinal.databinding.ActivitySecondBinding
import com.example.proyectofinal.databinding.ActivityFifthBinding
import com.example.proyectofinal.databinding.ActivityFourthBinding
import com.example.proyectofinal.databinding.ActivityMainBinding
import com.example.proyectofinal.databinding.ActivityThirdBinding

class MainActivity : AppCompatActivity() {
    //se le asigana un nombre a los layouts a utilizar
    private lateinit var binding: ActivityMainBinding
    private lateinit var bindingSecundario: ActivitySecondBinding
    private  lateinit var bindingTercero: ActivityThirdBinding
    private  lateinit var bindingCuarto: ActivityFourthBinding
    private lateinit var bindingQuinto: ActivityFifthBinding
    private lateinit var textWatcher: TextWatcher //función para habilitar elementos en secuencia
    private lateinit var recibecontrasenia: EditText
    private lateinit var vercontrasenia: ImageButton//icono para ver contraseña
    private lateinit var recurso: Uri
    private var isPasswordVisible = false
    private lateinit var rootView: View
    private lateinit var sharedPreferences: SharedPreferences//para al macenar valores temporalmente


    override fun onCreate ( savedInstanceState: Bundle? ) {
        super.onCreate ( savedInstanceState )
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate ( layoutInflater )
        setContentView ( binding.root )
        recibecontrasenia = findViewById(R.id.recibecontrasenia)
        vercontrasenia = findViewById(R.id.vercontrasenia)
        rootView = findViewById(android.R.id.content)
        bindingSecundario = ActivitySecondBinding.inflate ( layoutInflater )
        bindingTercero = ActivityThirdBinding.inflate ( layoutInflater )
        bindingCuarto = ActivityFourthBinding.inflate ( layoutInflater )
        bindingQuinto= ActivityFifthBinding.inflate ( layoutInflater )

        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)

        //llama a la función PasswordVisibility para ver contraseña del registro
        vercontrasenia.setOnClickListener {
            PasswordVisibility()
        }

        //se llaman las funciones desde la clase principal

        iniciarsesion()
        registrarse()
        cambiarainiciodesesion()
        PasswordVisibility2()
        PasswordVisibility3()
        PasswordVisibility4()
        PasswordVisibility5()
        subirfotos()
        menu()
        menu2()
        cambiarFondo1()
        cambiarFondo2()
        cambiarFondo3()
        cambiarFondo4()
        tomadatos3()
        guardarDatosTercerLayout()
        mostrarDatosCuartoLayout()
        mostrarDatosTercerLayout()
        verificarPassword()


        //se desactivan los botones
        binding.registrarse.isEnabled = false
        bindingQuinto.botonGuarda5.isEnabled = false

        textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
               //Se activan los botones si cumple con las funciones de validar
                binding.registrarse.isEnabled = validarCampos()
                bindingQuinto.botonGuarda5.isEnabled = validarContrasenias()
            }

            override fun afterTextChanged(s: Editable?) {}
        }
        //secuencia de campos para que se activen los botones de registro y cambiar contraseña
        binding.recibenombre.addTextChangedListener(textWatcher)
        binding.recibeusuario.addTextChangedListener(textWatcher)
        binding.recibefechanacimiento.addTextChangedListener(textWatcher)
        binding.recibecorreo.addTextChangedListener(textWatcher)
        binding.recibecontrasenia.addTextChangedListener(textWatcher)

        bindingQuinto.recibeCA.addTextChangedListener(textWatcher)
        bindingQuinto.recibeCN.addTextChangedListener(textWatcher)
        bindingQuinto.recibeCNR.addTextChangedListener(textWatcher)



    }
   //función para validar que los campos contengan texto
    private fun validarCampos(): Boolean {
        val name = binding.recibenombre.text.toString()
        val username = binding.recibeusuario.text.toString()
        val age = binding.recibefechanacimiento.text.toString()
        val email = binding.recibecorreo.text.toString()
        val password = binding.recibecontrasenia.text.toString()

        return !name.isNullOrEmpty() &&
                !username.isNullOrEmpty() &&
                !age.isNullOrEmpty() &&
                !email.isNullOrEmpty() &&
                !password.isNullOrEmpty()
    }

//función para validar que los campos de contraseña contengan texto
private fun validarContrasenias(): Boolean {
    val password = bindingQuinto.recibeCA.text.toString()
    val passwordN = bindingQuinto.recibeCN.text.toString()
    val passwordR= bindingQuinto.recibeCNR.text.toString()

    return !password.isNullOrEmpty() &&
            !passwordN.isNullOrEmpty() &&
            !passwordR.isNullOrEmpty()

}

    //función para ver contraseña de registro
    private fun PasswordVisibility() {

        isPasswordVisible = !isPasswordVisible

        if (isPasswordVisible) {
            // Mostrar la contraseña en texto plano
            recibecontrasenia.transformationMethod = HideReturnsTransformationMethod.getInstance()

        } else {
            // Ocultar la contraseña
            recibecontrasenia.transformationMethod = PasswordTransformationMethod.getInstance()

        }


// Establecer el OnClickListener en el botón vercontrasenia
    vercontrasenia.setOnClickListener {
        PasswordVisibility()
    }
}
    //función para ver contraseña del inicio de sesión
    private fun PasswordVisibility2() {

        isPasswordVisible = !isPasswordVisible

        if (isPasswordVisible) {
            // Mostrar la contraseña en texto plano
            bindingSecundario.recibecontrasenia2.transformationMethod = HideReturnsTransformationMethod.getInstance()

        } else {
            // Ocultar la contraseña
            bindingSecundario.recibecontrasenia2.transformationMethod = PasswordTransformationMethod.getInstance()

        }


// Establecer el OnClickListener en el botón vercontrasenia
        bindingSecundario.vercontrasenia2.setOnClickListener {
            PasswordVisibility2()
        }
    }

    //función para ver contraseña de la contraseña actual
    private fun PasswordVisibility3() {

        isPasswordVisible = !isPasswordVisible

        if (isPasswordVisible) {
            // Mostrar la contraseña en texto plano
            bindingQuinto.recibeCA.transformationMethod = HideReturnsTransformationMethod.getInstance()

        } else {
            // Ocultar la contraseña
            bindingQuinto.recibeCA.transformationMethod = PasswordTransformationMethod.getInstance()

        }


// Establecer el OnClickListener en el botón vercontrasenia
        bindingQuinto.ojo1.setOnClickListener {
            PasswordVisibility3()
        }
    }

    //funcionn para ver contraseña de la contraseña nueva
    private fun PasswordVisibility4() {

        isPasswordVisible = !isPasswordVisible

        if (isPasswordVisible) {
            // Mostrar la contraseña en texto plano
            bindingQuinto.recibeCN.transformationMethod = HideReturnsTransformationMethod.getInstance()

        } else {
            // Ocultar la contraseña
            bindingQuinto.recibeCN.transformationMethod = PasswordTransformationMethod.getInstance()

        }


// Establecer el OnClickListener en el botón vercontrasenia
        bindingQuinto.ojo2.setOnClickListener {
            PasswordVisibility4()
        }
    }


    //funcionn para ver contraseña de la contraseña repetida
    private fun PasswordVisibility5() {

        isPasswordVisible = !isPasswordVisible

        if (isPasswordVisible) {
            // Mostrar la contraseña en texto plano
            bindingQuinto.recibeCNR.transformationMethod = HideReturnsTransformationMethod.getInstance()

        } else {
            // Ocultar la contraseña
            bindingQuinto.recibeCNR.transformationMethod = PasswordTransformationMethod.getInstance()

        }


// Establecer el OnClickListener en el botón vercontrasenia
        bindingQuinto.ojo3.setOnClickListener {
            PasswordVisibility5()
        }
    }


//función para iniciar sesión y validar las credenciales
    private fun iniciarsesion() {
        bindingSecundario.iniciarsesion3.setOnClickListener {

            //guarda el usuario o correo y la contraseña
            val username = bindingSecundario.recibeusuarioocorreo.text.toString()
            val passwordSesion = bindingSecundario.recibecontrasenia2.text.toString()


            val savedUsername = sharedPreferences.getString("username", "")
            val savedCorreo = sharedPreferences.getString("email", "")
            val savedPassword = sharedPreferences.getString("password", "")

            //valida si la contraseña ingresada es igual a la contraseña de registro
            if (passwordSesion == savedPassword ) {
                //valida si el usuario o correo es igual que la de registro
                if (username == savedUsername || username == savedCorreo ){
                val name = sharedPreferences.getString("name", "")
                val age = sharedPreferences.getString("age", "")
                val email = sharedPreferences.getString("email", "")


                // Realiza las acciones adicionales aquí, por ejemplo:
                binding.recibenombre.setText(name)
                binding.recibefechanacimiento.setText(age)
                binding.recibecorreo.setText(email)
                    //si se cumple muestra el tercer layout y los datos de registro
                    setContentView ( bindingTercero.root )
                    mostrarDatosTercerLayout()
                 //mensajes de retroalimentación
                Toast.makeText ( this, "Iniciando Sesión", Toast.LENGTH_SHORT ).show()
                }
                else{
                    Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
            }
        }

    }
    //función de registro donde almacena los datos ingresados
    private fun registrarse() {
        binding.registrarse.setOnClickListener {

            val name = binding.recibenombre.text.toString()
            val username = binding.recibeusuario.text.toString()
            val age = binding.recibefechanacimiento.text.toString()
            val email = binding.recibecorreo.text.toString()
            val password = binding.recibecontrasenia.text.toString()

            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("name", name)
            editor.putString("username", username)
            editor.putString("age", age)
            editor.putString("email", email)
            editor.putString("password", password)
            editor.apply()

            binding.recibenombre.isEnabled = false
            binding.recibeusuario.isEnabled = false
            binding.recibefechanacimiento.isEnabled = false
            binding.recibecorreo.isEnabled = false
            binding.recibecontrasenia.isEnabled = false
            //cada que se registra un usuario resetea los valores del layout3
                resetearElementos()
            //mensaje de retroalimentación
            Toast.makeText ( this, R.string.alertacorrecta, Toast.LENGTH_SHORT ).show()

        }
    }
    //función para resetear los elementos de cada campo
    private fun resetearElementos() {

    val editor = sharedPreferences.edit()
        editor.remove("ocupation")
        editor.remove("address")
        editor.remove("phone")
        editor.remove("phone")
        editor.remove("gender")
        editor.remove("situation")
        editor.remove("descrip")


        editor.apply()

    }
    //funcion para el 3 layout que retome los datos del 1 layout, esta funcion sirve para mostrar los datos en el 3 layout

    private  fun tomadatos3(){

        val name = sharedPreferences.getString("name", "")
        val username = sharedPreferences.getString("username", "")
        val age = sharedPreferences.getString("age", "")
        val email = sharedPreferences.getString("email", "")


        bindingTercero.recibenombre.setText(name)
        bindingTercero.recibeusuario.setText(username)
        bindingTercero.recibefechanacimiento.setText(age)
        bindingTercero.recibecorreo.setText(email)

    }


    // Función para almacenar los datos del tercer layout
    private fun guardarDatosTercerLayout() {

        bindingTercero.botonfinalizar.setOnClickListener {

            val name = bindingTercero.recibenombre.text.toString()
            val username = bindingTercero.recibeusuario.text.toString()
            val age = bindingTercero.recibefechanacimiento.text.toString()
            val email = bindingTercero.recibecorreo.text.toString()
            val ocupation = bindingTercero.recibeocupacion.text.toString()
            val address = bindingTercero.reciberesidencia.text.toString()
            val phone = bindingTercero.recibetelefono.text.toString()
            val gender = bindingTercero.recibegenero.selectedItem.toString()
            val situation = bindingTercero.recibesituS.selectedItem.toString()
            val descrip = bindingTercero.recibedescrip.text.toString()

            guardarImagen()

            val editor = sharedPreferences.edit()
            editor.putString("name", name)
            editor.putString("username", username)
            editor.putString("age", age)
            editor.putString("email", email)
            editor.putString("ocupation", ocupation)
            editor.putString("address", address)
            editor.putString("phone", phone)
            editor.putString("gender", gender)
            editor.putString("situation", situation)
            editor.putString("descrip", descrip)
            editor.apply()
            //muestra el cuarto layout y llama la funcion mostrar datos
            setContentView (bindingCuarto.root)
            mostrarDatosCuartoLayout()
            //mensaje de retroalimentación
            Toast.makeText(this, "Información almacenada", Toast.LENGTH_SHORT).show()
        }
    }

    // Función para mostrar los datos del tercer layout en el cuarto layout
    private fun mostrarDatosCuartoLayout() {

        val name = sharedPreferences.getString("name", "")
        val username = sharedPreferences.getString("username", "")
        val age = sharedPreferences.getString("age", "")
        val email = sharedPreferences.getString("email", "")
        val ocupation = sharedPreferences.getString("ocupation", "")
        val address = sharedPreferences.getString("address", "")
        val phone = sharedPreferences.getString("phone", "")
        val situation = sharedPreferences.getString("situation", "")
        val gender = sharedPreferences.getString("gender", "")
        val descrip = sharedPreferences.getString("descrip", "")

        bindingCuarto.recibenombre.setText(name)
        bindingCuarto.recibeusuario.setText(username)
        bindingCuarto.recibefechanacimiento.setText(age)
        bindingCuarto.recibecorreo.setText(email)
        bindingCuarto.recibeocupacion.setText(ocupation)
        bindingCuarto.reciberesidencia.setText(address)
        bindingCuarto.recibetelefono.setText(phone)
        bindingCuarto.recibesituS.setText(situation)
        bindingCuarto.recibegenero.setText(gender)
        bindingCuarto.recibedescrip.setText(descrip)
    }
    //función para mostrar datos de 3 layout
    private fun mostrarDatosTercerLayout() {

        val name = sharedPreferences.getString("name", "")
        val username = sharedPreferences.getString("username", "")
        val age = sharedPreferences.getString("age", "")
        val email = sharedPreferences.getString("email", "")
        val ocupation = sharedPreferences.getString("ocupation", "")
        val address = sharedPreferences.getString("address", "")
        val phone = sharedPreferences.getString("phone", "")
        val situation = sharedPreferences.getString("situation", "")
        val gender = sharedPreferences.getString("gender", "")
        val descrip = sharedPreferences.getString("descrip", "")

        bindingTercero.recibenombre.setText(name)
        bindingTercero.recibeusuario.setText(username)
        bindingTercero.recibefechanacimiento.setText(age)
        bindingTercero.recibecorreo.setText(email)
        bindingTercero.recibeocupacion.setText(ocupation)
        bindingTercero.reciberesidencia.setText(address)
        bindingTercero.recibetelefono.setText(phone)

// Obtiene el índice del valor en el array de opciones del Spinner
        val genderOptions = resources.getStringArray(R.array.genero3)
        val selectedGenderIndex = genderOptions.indexOf(gender)

// Crea un ArrayAdapter con las opciones y el estilo deseado
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, genderOptions)

// Especifica el estilo del dropdown
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

// Establece el adaptador en el Spinner
        bindingTercero.recibegenero.adapter = adapter

// Establece el índice seleccionado
        if (selectedGenderIndex != -1) {
            bindingTercero.recibegenero.setSelection(selectedGenderIndex)
        }
        //bindingTercero.recibesituS.setText(situation)

        bindingTercero.recibedescrip.setText(descrip)


    // Obtiene el índice del valor en el array de opciones del Spinner
    val situationOptions = resources.getStringArray(R.array.situacionsentimental3)
    val selectedSituationIndex = situationOptions.indexOf(situation)

    // Crea un ArrayAdapter con las opciones y el estilo deseado
    val adapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item, situationOptions)

// Especifica el estilo del dropdown
    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

// Establece el adaptador en el Spinner
    bindingTercero.recibesituS.adapter = adapter2

// Establece el índice seleccionado
    if (selectedSituationIndex != -1) {
        bindingTercero.recibesituS.setSelection(selectedSituationIndex)
     }
    }

    //función para verificar las contraseñas y actualizarlas
    private fun verificarPassword() {
        bindingQuinto.botonGuarda5.setOnClickListener {
            //recibe los datos ingresados y los guarda temporalmenete
            val passwordA= bindingQuinto.recibeCA.text.toString()
            val passwordNueva = bindingQuinto.recibeCN.text.toString()
            val passwordRepite = bindingQuinto.recibeCNR.text.toString()


            val editor3: SharedPreferences.Editor = sharedPreferences.edit()

            editor3.putString("passwordNueva", passwordNueva)
            editor3.putString("passwordRepite", passwordRepite)
            editor3.putString("passwordR", passwordA)

            editor3.apply()


            val savedPA = sharedPreferences.getString("password", "")

            //verifica si la contraseña actual es la misma que la contraseña de registro
            if (savedPA == passwordA) {
                //verifica si la contraseña nueva es igual a la contraseña repetida
                if (passwordNueva == passwordRepite) {
                bindingQuinto.botonGuarda5.isEnabled = validarContrasenias()
                Toast.makeText(this, R.string.alertacorrecta5, Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(this, "Credenciales Incorrectas", Toast.LENGTH_SHORT).show()
                }


                //se realiza el cambio de contraseña
                val password = bindingQuinto.recibeCN.text.toString()
                val editor = sharedPreferences.edit()
                editor.putString("password", password)
                editor.apply()

            } else {
                Toast.makeText(this, "Credenciales Incorrectas", Toast.LENGTH_SHORT).show()
            }
        }
    }



    //función para que el boton inicio de sesión cambie al segundo layout
    private fun cambiarainiciodesesion() {
        binding.iniciarsesion.setOnClickListener {
            setContentView ( bindingSecundario.root )

        }
    }
    //función de menu del cuarto layout
    private fun menu() {
        bindingCuarto.imagenmenu.setOnClickListener { clickedView ->
            val popupMenu = PopupMenu ( this, clickedView )
            popupMenu.inflate ( R.menu.menu )

            popupMenu.setOnMenuItemClickListener { item ->
                when ( item.itemId ) {
                    R.id.opciones -> {
                        Toast.makeText ( this, R.string.opciones4, Toast.LENGTH_SHORT ).show()
                        setContentView (bindingQuinto.root)
                        //cambia al quinto layout
                        true
                    }
                    R.id.modificar -> {
                        Toast.makeText ( this, R.string.modificarperfil4, Toast.LENGTH_SHORT ).show()
                            //cambia al tercer layout para modificar
                            setContentView (bindingTercero.root)
                        //muestra los datos del tercer layout
                        mostrarDatosTercerLayout()

                        true
                    }
                    R.id.salir -> {
                        finish()
                        //sale del programa
                        true
                    }
                    else -> false
                }
            }
            popupMenu.show()
        }
    }

    //función para el menu del quinto layout
    private fun menu2() {
        bindingQuinto.imagenmenu.setOnClickListener { clickedView ->
            val popupMenu = PopupMenu ( this, clickedView )
            popupMenu.inflate ( R.menu.menu2 )

            popupMenu.setOnMenuItemClickListener { item ->
                when ( item.itemId ) {
                    R.id.opciones -> {
                        Toast.makeText ( this, R.string.opciones4, Toast.LENGTH_SHORT ).show()
                        setContentView (bindingQuinto.root)
                        //cambia al quinto layout
                        true
                    }
                    R.id.modificar -> {
                        Toast.makeText ( this, R.string.modificarperfil4, Toast.LENGTH_SHORT ).show()

                        setContentView (bindingTercero.root)
                        mostrarDatosTercerLayout()
                        //cambia al tercer layout para modificar y muestra sus datos

                        true


                    }
                    R.id.verperfil -> {
                        Toast.makeText ( this, R.string.modificarperfil4, Toast.LENGTH_SHORT ).show()
                        //cambia al cuarto layout y muestra los datos del caurto
                        setContentView (bindingCuarto.root)
                        mostrarDatosCuartoLayout()

                        true


                    }
                    R.id.salir -> {
                        finish()
                        //sale de la aplicación
                        true
                    }
                    else -> false
                }
            }
            popupMenu.show()
        }
    }




    private val PICK_IMAGE_REQUEST = 1
    private var selectedImageUri: Uri? = null // Variable para almacenar la URI de la imagen seleccionada
//función para subir fotos
    private fun subirfotos() {
        bindingTercero.botonsubirfoto.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
            guardarImagen()
            bindingTercero.botonsubirfoto.text = "Cambiar Foto"
        }
    }

//función para guardar la imagen subida
    private fun guardarImagen() {
        val imageView = bindingTercero.fotoperfil
        val imagenDibujable = imageView.drawable as? BitmapDrawable
        val mapaDeBits = imagenDibujable?.bitmap

        mapaDeBits?.let { bitmap ->
            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
                put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
            }

            val resolver = contentResolver
            val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

            uri?.let { imageUri ->
                // Guardar la URI de la imagen seleccionada en una variable miembro (selectedImageUri)
                selectedImageUri = imageUri

                val outputStream = resolver.openOutputStream(imageUri)
                outputStream?.use {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, it) // Comprimir y guardar la imagen en el OutputStream
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            val imageUri: Uri? = data.data
            bindingTercero.fotoperfil.setImageURI(imageUri)

            // Guardar la URI de la imagen seleccionada en una variable miembro (selectedImageUri)
            selectedImageUri = imageUri
        }
    }


//funciones para cambiar fondos mediante botones establecidos
    fun cambiarFondo1() {
        bindingQuinto.botonF1.setOnClickListener{
        val contenedorPrincipal = bindingCuarto.cuarto
        contenedorPrincipal.setBackgroundResource(R.drawable.fondo1)
        }
    }

    fun cambiarFondo2() {
        bindingQuinto.botonF2.setOnClickListener{
            val contenedorPrincipal = bindingCuarto.cuarto
            contenedorPrincipal.setBackgroundResource(R.drawable.fondo2)
        }
    }

    fun cambiarFondo3() {
        bindingQuinto.botonF3.setOnClickListener{
            val contenedorPrincipal = bindingCuarto.cuarto
            contenedorPrincipal.setBackgroundResource(R.drawable.fondo3)
        }
    }
    fun cambiarFondo4() {
        bindingQuinto.botonF4.setOnClickListener{
            val contenedorPrincipal = bindingCuarto.cuarto
            contenedorPrincipal.setBackgroundResource(R.drawable.fondo4)
        }
    }
}