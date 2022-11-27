import { StyleSheet, Text, View, SafeAreaView, Alert, FlatList } from 'react-native'
import React from 'react'
import { useState, useEffect } from 'react';
import { Button } from 'react-native-paper';
import AgenciaCartaUsuario from '../components/AgenciaCartaUsuario';



const GestionUsuarios = ({ navigation, route }) => {
  
  const direccion = route.params.direccion;
  const [usuarios, setUsuarios] = useState([]);
  useEffect(() => {
    fetch('http://'+direccion+'/api/usuarios')
      .then((response) => response.json())
      .then((json) => setUsuarios(json))
      .catch((error) => console.error(error))
      .finally(() => fetch('http://'+direccion+'/api/clientes')
                        .then((response) => response.json())
                        .then((json) => setUsuarios(usuarios => [...usuarios, ...json]))
                        .catch((error) => console.error(error))
      );
  }, []);

  const listarUsuarios = ({ item }) => {
    return (
      <View >
        <AgenciaCartaUsuario
          usuario={item}
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
          <Button icon="plus" mode="contained" onPress={() => navigation.navigate('AgregarUsuario', {direccion: direccion})}>
            Agregar Usuario
          </Button>
          <FlatList
            style={styles.flatList}
            data={usuarios}
            renderItem={listarUsuarios}
            keyExtractor={(item) => item.id.toString()}
          />
          
        </View>
      </View>
   </SafeAreaView>
  )
}

export default GestionUsuarios

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
        width: 300,
        height: 600,
        margin: 12,
      },

      
})