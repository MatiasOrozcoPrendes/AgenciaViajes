import { StyleSheet, Text, View, SafeAreaView, Alert, FlatList } from 'react-native';
import React from 'react'
import { useState, useEffect } from 'react';
import { Button } from 'react-native-paper';
import AgenciaCartaViajes from '../components/AgenciaCartaViajes';

const GestionViajes = ({ navigation, route }) => {
  const direccion = route.params.direccion;
  const [viajes, setViajes] = useState([]);
  const [fechaActual, setFechaActual] = useState(new Date().toISOString().slice(0, 10));
  useEffect(() => {
    fetch('http://'+direccion+'/api/viajes/fechaInicio/'+fechaActual)
      .then((response) => response.json())
      .then((json) => setViajes(json))
      .catch((error) => console.error(error))
  }, []);

  const listarViajes = ({ item }) => {
    return (
      <View >
        <AgenciaCartaViajes
          viaje={item}
          navigation={navigation}
          direccion={direccion}
        />
      </View>
    );
  };
  return (
    <SafeAreaView style={styles.container}>
      <View style={styles.viewContainer}>
        <View style={styles.generalView}>
          <Button icon="plus" mode="contained" onPress={() => navigation.navigate('AgregarViaje', {direccion: direccion})}>
            Agregar Viaje
          </Button>
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

export default GestionViajes

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: 'yellow',
    
  },
  viewContainer: {
    flex: 1,
    width: '100%',
  },
  generalView: {
    alignItems: 'center',
    justifyContent: 'center',
    marginTop: 20,
    marginBottom: 20,
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
  flatList: {
    width: 350,
    height: 600,
    margin: 12,
  },
})