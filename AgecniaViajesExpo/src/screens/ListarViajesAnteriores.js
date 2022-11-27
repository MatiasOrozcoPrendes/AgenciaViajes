import { StyleSheet, Text, View, SafeAreaView, Alert, TouchableOpacity, FlatList } from 'react-native';
import React from 'react'
import { useState, useEffect } from 'react';
import AgenciaCartaViajeCliente from '../components/AgenciaCartaViajeCliente';

const ListarViajesAnteriores = ({ navigation, route }) => {
  const usuario = route.params.usuario;
  const direccion = route.params.direccion;
  const [viajes, setViajes] = useState([]);
  const [fechaActual, setFechaActual] = useState(new Date().toISOString().slice(0, 10));

  useEffect(() => {
    fetch('http://'+direccion+'/api/clientes/listarViajes/' + usuario.id)
        .then(response => response.json())
        .then(data => {
          cargarViajes(data);
        })
        .catch((error) => {
            console.error('Error:', error);
        });
  }, []);

  const listarViajes = ({ item }) => {
    return (
      <View >
        <AgenciaCartaViajeCliente
          viaje={item}
          navigation={navigation}
        />
      </View>
    );
  }; 

  function cargarViajes(data) {
    // creo un array vacio para guardar los viajes que se van a mostrar
      let viajesMostrar = [];
      // recorro el array de viajes que me llega
      data.forEach(viaje => {
          // si la fecha de salida es menor a la fecha actual, lo agrego al array de viajes a mostrar
          if (viaje.fechaInicio < fechaActual) {
              viajesMostrar.push(viaje);
          }
      });
      // seteo el array de viajes a mostrar
      setViajes(viajesMostrar);
  }

  return (
    <SafeAreaView style={styles.container}>
      <View style={styles.viewContainer}>
        <View style={styles.generalView}>
          <FlatList
            style={styles.flatList}
            data={viajes}
            renderItem={listarViajes}
            keyExtractor={(item) => item.id.toString()}
          />
        </View>
      </View>
   </SafeAreaView>
    )
}

export default ListarViajesAnteriores

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
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: '#DDDDDD',
    padding: 10,
    width: 250,
    height: 50,
    margin: 12,
  },
  textoBoton: {
    fontSize: 20,
    fontWeight: 'bold',
    color: 'black',
  },
})