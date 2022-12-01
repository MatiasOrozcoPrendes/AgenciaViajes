import { StyleSheet, Text, View, SafeAreaView, Alert, TouchableOpacity } from 'react-native';

import React, { useState } from 'react';
import { TextInput, Button } from 'react-native-paper';

const Principal = ({navigation}) => {
  //Se definen los estados de los campos que se van a utilizar para el login
  const [email, setEmail] = useState('');
  const [contraseña, setContraseña] = useState('');
  //Se define el estado de la variable donde se guarda la direccion del servidor donde se encuentra el backend
  const [direccion, setDireccion] = useState('192.168.1.9:8080');
  
  function Ingresar(){
    //Se verifica si se ingreso el usuario administador por defecto
    if(email === 'admin' & contraseña === 'admin'){
      //Si se ingresa el usuario por defecto se abre la pantalla de administrador y se le pasa la direccion del servidor
      navigation.navigate('Administrador', {direccion: direccion})
    } else{
      //si el campo de usuario o contraseña estan vacios muestro un mensaje de error
      if(email === '' || contraseña === ''){
        Alert.alert('Error', 'Debe ingresar un usuario y contraseña');
      } else{
        //llamo a Traigo Cliente para verificar si el usuario existe
        TraigoUsuario()
      }
    }
  }
  //Limpio los campos de usuario y contraseña
  function limpiar(){
    setEmail('');
    setContraseña('');
  }
  //Funcion que se encarga de traer el usuario o cliente de la base de datos
  function TraigoUsuario(){
    //Se arma la direccion del servidor con la direccion que se ingreso en el campo de direccion
    //y se le agrega el email y la contraseña ingresados
    fetch('http://'+direccion+'/api/clientes/login/'+email+'/'+contraseña)
    .then((response) => response.json())
    .then((json) => {
      //Si se recibe una respuesta del servidor se verifica que el usuario existe y la contraseña es correcta
      //abro la pantalla de cliente y le paso el cliente que me devolvio el fetch
      navigation.navigate('Cliente', {usuario: json, direccion: direccion})
      limpiar();
    })
    .catch((error) => {
      //si no me devuelve un cliente pruebo con traer un empleado
      fetch('http://'+direccion+'/api/usuarios/login/'+email+'/'+contraseña)
      .then((response) => response.json())
      .then((json) => {
        //abro la pantalla de empleado y le paso el empleado que me devolvio el fetch
        navigation.navigate('Administrador', {usuario: json, direccion: direccion})
        limpiar();
      })
      .catch((error) => {
        //si no me devuelve un empleado muestro un mensaje de error
        Alert.alert('Error', 'Usuario o contraseña incorrectos');
      });
    }
    );
 }

  return (
    <SafeAreaView style={styles.container}>
      <View style={styles.viewContainer}>
        <View style={styles.generalView}>
          <Text style={styles.titulo}>Bienvenido</Text>
          <Text style={styles.subtitulo}>Ingrese su email y contraseña</Text>
          {/* Inputs utilizado para ingresar la direccion del servidor de backend */}
          <TextInput 
                style={styles.input} 
                placeholder="IP del servidor:Puerto" 
                onChangeText={setDireccion} 
                value={direccion}
                autoCapitalize='none'
            />
          {/* Inputs de usuario y contraseña para hacer el loginRootStack */}
          <TextInput 
                style={styles.input} 
                placeholder="Email" 
                onChangeText={setEmail} 
                value={email}
                keyboardType="email-address"
                autoCapitalize='none'
            />
          <TextInput 
                style={styles.input} 
                placeholder="Contraseña" 
                onChangeText={setContraseña} 
                value={contraseña} 
                secureTextEntry={true}
                autoCapitalize='none'
          />
          <Button 
            style={styles.boton}
            labelStyle={styles.textoBoton}
            icon="airplane-marker"
            mode="elevated" 
            buttonColor='blue'
            textColor='white'
            onPress={() => Ingresar()}
          >
            Ingresar
          </Button>
        </View>
      </View>

    </SafeAreaView>
  )
}

export default Principal

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: 'yellow',
    
  },
  viewContainer: {
    flex: 1,
    alignItems: 'center',
  },
  generalView: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  titulo: {
    fontSize: 30,
    fontWeight: 'bold',
    color: 'black',
  },
  subtitulo: {
    fontSize: 20,
    fontWeight: 'bold',
    color: 'black',
  },
  input: {
    width: 250,
    height: 40,
    margin: 12,
    padding: 10,
    
},
boton: {
    height: 50,
    width: 180,
    margin: 12,
    justifyContent: 'center',
    alignItems: 'center',
},
textoBoton: {
    fontSize: 20,
    fontWeight: 'bold',
},


})