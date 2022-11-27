import { StyleSheet, Text, View, SafeAreaView } from 'react-native';
import { Button } from 'react-native-paper';
import React from 'react'

const Administrador = ({ navigation, route }) => {
  const direccion = route.params.direccion;
  return (
    <SafeAreaView style={styles.container}>
      <View style={styles.viewContainer}>
        <View style={styles.generalView}>
          <Text style={styles.titulo}>Bienvenido</Text>
          <Button 
            style={styles.boton}
            labelStyle={styles.textoBoton}
            icon="account-reactivate"
            mode="elevated" 
            buttonColor='blue' 
            textColor='white'
            onPress={() => navigation.navigate('GestionUsuarios', {direccion: direccion})}
          >
            Usuarios
          </Button>
          <Button 
            style={styles.boton}
            labelStyle={styles.textoBoton}
            icon="airplane-plus"
            mode="elevated" 
            buttonColor='blue' 
            textColor='white'
            onPress={() => navigation.navigate('GestionViajes', {direccion: direccion})}
          >
            Viajes
          </Button>
        </View>
      </View>
   </SafeAreaView>
  )
}

export default Administrador

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
    borderWidth: 1,
    padding: 10,
    backgroundColor: 'white',
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