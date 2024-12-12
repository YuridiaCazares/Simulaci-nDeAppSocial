# Simulaci-nDeAppSocial
Programa en Android Studio, utilizando el lenguaje Kotlin, haciendo uso de funciones ConstraintLayout, View Binding.  App para Android que simula una red social existente, que registra a un usuario de forma permanente.


Características del App

- La aplicación se diseñó con varios layouts para brindar una experiencia de usuario completa.
- El primer layout es un formulario de registro donde el usuario puede ingresar su nombre completo, nombre de usuario, fecha de nacimiento, correo electrónico y contraseña.
- Después del campo de contraseña, hay un elemento con una imagen personalizada que, al presionar, muestra la contraseña en texto plano.
- También hay un elemento que permite alternar entre el formulario de registro y el segundo layout, que es para iniciar sesión.
- En la parte final del formulario de registro, hay un botón que, al presionarlo, permite acceder al segundo layout, siempre y cuando todos los campos estén llenos correctamente.
- El segundo layout es para iniciar sesión y tiene dos campos: uno para el nombre de usuario o correo electrónico y otro para la contraseña.
- Si los campos están vacíos o la información ingresada es incorrecta, se muestra un mensaje de error.
- Junto al campo de contraseña, hay un elemento que muestra la contraseña en texto plano al presionar.
- También hay un elemento que permite regresar al primer layout.
- El tercer layout muestra la información ingresada en el primer layout, que puede ser modificada directamente en un EditText.
- También hay un ImageView y un botón para subir una foto de perfil.
- El botón cambia de texto a "Cambiar Foto" cuando el ImageView no está vacío.
- En la parte final, hay un botón que envía al cuarto layout.
- El cuarto layout muestra el perfil completo con la foto y la información capturada en los layouts anteriores.
- En la esquina superior, hay un elemento personalizado que muestra un menú popup con opciones como "Opciones", "Modificar Perfil" y "Cerrar Sesión".
- No se puede modificar la información en este layout, pero se puede hacer a través del menú popup.
- Se puede acceder al quinto layout arrastrando el dedo en pantalla o a través del menú popup.
- Al presionar "Cerrar Sesión", se regresa al primer layout y los campos se limpian.
- El quinto layout tiene dos opciones: "Cambiar Contraseña" y "Cambiar Fondo" para el cuarto layout.
- Para cambiar la contraseña, se solicita la contraseña actual, se valida y luego se pide la nueva contraseña dos veces. Si coinciden, se guarda el cambio.
