import { StyleSheet, Text, View, SafeAreaView } from 'react-native';
import { Button } from 'react-native-paper';
import React from 'react'

const Cliente = ({ navigation, route }) => {
  const usuario = route.params.usuario;
  const direccion = route.params.direccion;

  return (
    <SafeAreaView style={styles.container}>
      <View style={styles.viewContainer}>
        <View style={styles.generalView}>
          <Text style={styles.titulo}>Bienvenido {usuario.nombre}</Text>
            <Button 
              style={styles.boton}
              labelStyle={styles.textoBoton}
              mode="elevated" 
              buttonColor='blue' 
              textColor='white'
              onPress={() => navigation.navigate("ListarViajes", { usuario: usuario, direccion: direccion })}>
            Disponibles
            </Button>
            <Button 
              style={styles.boton}
              labelStyle={styles.textoBoton}
              mode="elevated" 
              buttonColor='blue' 
              textColor='white'
              onPress={() => navigation.navigate("ListarViajesUsuario", { usuario: usuario, direccion: direccion })}>
            Proximos
            </Button>
            <Button
              style={styles.boton}
              labelStyle={styles.textoBoton}
              mode="elevated"
              buttonColor='blue'
              textColor='white'
              onPress={() => navigation.navigate("ListarViajesAnteriores", { usuario: usuario, direccion: direccion })}>
              Anteriores
            </Button>

        </View>
      </View>
   </SafeAreaView>
  )
}

export default Cliente

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
  boton: {
    height: 80,
    width: 200,
    margin: 12,
    justifyContent: 'center',
    alignItems: 'center',
},
textoBoton: {
    fontSize: 20,
    fontWeight: 'bold',
},
})