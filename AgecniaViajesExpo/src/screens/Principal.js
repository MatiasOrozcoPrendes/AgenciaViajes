import { StyleSheet, Text, View, SafeAreaView, Alert, TouchableOpacity } from 'react-native';

import React, { useState } from 'react';
import { TextInput, Button } from 'react-native-paper';

const Principal = ({navigation}) => {
  const [email, setEmail] = useState('');
  const [contraseña, setContraseña] = useState('');
  const [direccion, setDireccion] = useState('192.168.1.9:8080');
  
  function Ingresar(){
    if(email === 'admin' & contraseña === 'admin'){
      navigation.navigate('Administrador', {direccion: direccion})
    } else{
      //si el campo de usuario o contraseña estan vacios muestro un mensaje de error
      if(email === '' || contraseña === ''){
        Alert.alert('Error', 'Debe ingresar un usuario y contraseña');
      } else{
        //llamo a Traigo Cliente y espero que me devuelva un cliente
        TraigoUsuario()
      }
    }
  }
  function limpiar(){
    setEmail('');
    setContraseña('');
  }
  function TraigoUsuario(){
    fetch('http://'+direccion+'/api/clientes/login/'+email+'/'+contraseña)
    .then((response) => response.json())
    .then((json) => {
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
          <TextInput 
                style={styles.input} 
                placeholder="IP del servidor:Puerto" 
                onChangeText={setDireccion} 
                value={direccion}
                autoCapitalize='none'
            />
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