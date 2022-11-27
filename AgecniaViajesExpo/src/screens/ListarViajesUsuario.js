import { StyleSheet, Text, View, SafeAreaView, Alert, TouchableOpacity, FlatList } from 'react-native';
import { TextInput, Button } from 'react-native-paper';
import React from 'react'
import { useState, useEffect } from 'react';
import AgenciaCartaViajeCliente from '../components/AgenciaCartaViajeCliente';


const ListarViajesUsuario = ({ navigation, route }) => {
    const usuario = route.params.usuario;
    const direccion = route.params.direccion;
    const [viajes, setViajes] = useState([]);
    const [fechaActual, setFechaActual] = useState(new Date().toISOString().slice(0, 10));
    const [fechaBusqueda, setFechaBusqueda] = useState(new Date().toISOString().slice(0, 10));
    
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

    function cargarViajes(data) {
      // creo un array vacio para guardar los viajes que se van a mostrar
        let viajesMostrar = [];
        // recorro el array de viajes que me llega
        data.forEach(viaje => {
            // si la fecha de salida es mayor a la fecha actual, lo agrego al array de viajes a mostrar
            if (viaje.fechaInicio > fechaActual) {
                viajesMostrar.push(viaje);
            }
        });
        // seteo el array de viajes a mostrar
        setViajes(viajesMostrar);
    }
    function cargoPrimerViajeDespuesDeFechaBusqueda() {
      let viajesMostrar = [];
      fetch('http://'+direccion+'/api/clientes/primerViaje/' + usuario.id + '/' + fechaBusqueda)
            .then(response => response.json())
            .then(data => {
              console.log(data);
              if (data.destino) {
                viajesMostrar.push(data);
                setViajes(viajesMostrar);
              } else {
                Alert.alert("No hay viajes disponibles para esa fecha");
              } 
            })
            .catch((error) => {
              Alert.alert("No hay viajes disponibles para esa fecha");
            });
    }
    function eliminarViaje(viaje) {
      Alert.alert(
        "Eliminar viaje",
        "¿Estás seguro de que quieres eliminar este viaje?",
        [
          {
            text: "Cancelar",
            onPress: () => console.log("Cancel Pressed"),
            style: "cancel"
          },
          { text: "OK", onPress: () => eliminar(viaje) }
        ],
        { cancelable: false }
      );
    }
    function eliminar(viaje) {
      const requestOptions = {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({})
      };
      fetch('http://'+direccion+'/api/clientes/eliminarViaje/' + usuario.id + '/' + viaje.id, requestOptions)
          .then(response => response.json())
          .then(result => console.log(result))
          .catch(error => console.log('error', error));
      navigation.navigate('Cliente', { usuario: usuario, direccion: direccion });
    }
   const listarViajes = ({ item }) => {
      return (
        <View >
          <TouchableOpacity onPress={() => eliminarViaje(item)}>
           <AgenciaCartaViajeCliente
              viaje={item}
              navigation={navigation}
            />
          </TouchableOpacity>
        </View>
      );
    };    
  return (
    <SafeAreaView style={styles.container}>
      <View style={styles.viewContainer}>
        <View style={styles.generalView}>
          <Text style={styles.subtitulo}>Ingrese una fecha para saber el siguiente viaje</Text>
          <View style={styles.unaLinea}>
            <TextInput 
              style={styles.input} 
              placeholder="AAAA-MM-DD" 
              onChangeText={setFechaBusqueda} 
              keyboardType="numeric"
              value={fechaBusqueda}
              autoCapitalize='none'
            />
            <Button 
                style={styles.boton}
                labelStyle={styles.textoBoton}
                icon="file-find-outline"
                mode="elevated" 
                buttonColor='blue'
                textColor='white'
                onPress={() => cargoPrimerViajeDespuesDeFechaBusqueda() }/>
          </View>
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

export default ListarViajesUsuario

const styles = StyleSheet.create({
    container: {
      flex: 1,
      backgroundColor: 'yellow',
    },
    unaLinea: {
      flexDirection: 'row',
      justifyContent: 'space-between',
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
      width: 180,
      height: 30,
      margin: 6,
      borderWidth: 1,
      padding: 10,
      backgroundColor: 'white',
    },
    boton: {
      justifyContent: 'center',
      alignItems: 'center',
      width: 20,
      height: 45,
      margin: 6,
      
  },
  textoBoton: {
      fontSize: 20,
      fontWeight: 'bold',
      color: 'white',
  },
})