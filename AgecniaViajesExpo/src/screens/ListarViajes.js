import { StyleSheet, Text, View, SafeAreaView, Alert, TouchableOpacity, FlatList } from 'react-native';
import React from 'react'
import { useState, useEffect } from 'react';
import AgenciaCartaViajeCliente from '../components/AgenciaCartaViajeCliente';


const ListarViajes = ({ navigation, route }) => {
    const recibo = route.params.usuario;
    const direccion = route.params.direccion;
    const [viajes, setViajes] = useState([]);
    const [fechaActual, setFechaActual] = useState(new Date().toISOString().slice(0, 10));
    const [usuario, setUsuario] = useState([]);
    
    useEffect(() => {
      fetch('http://'+direccion+'/api/clientes/' + recibo.id)
            .then(response => response.json())
            .then(data => setUsuario(data))
            .catch((error) => console.error(error))
    
      fetch('http://'+direccion+'/api/viajes/fechaInicio/'+fechaActual)
        .then((response) => response.json())
        .then((json) => setViajes(json))
        .catch((error) => console.error(error))
    }, []);

   function agregarViaje(Viaje) {
      //creo una variable para edirar el mensaje dependiendo si el cliente es vip o no.
      let mensaje = '';
      if (usuario.esVip) {
        mensaje = 'Eres un cliente VIP, el viaje a ' + Viaje.destino + ' tiene un descuento del 20%, el precio final es de $' + Viaje.precio * 0.8 + '. ¿Desea agregarlo?';
      } else {
        mensaje = 'El viaje a ' + Viaje.destino + ' tiene un precio de $' + Viaje.precio + '. ¿Desea agregarlo?';
      }
        //pregunto si decea agregar el viaje
        Alert.alert(
            "Agregar Viaje",
            mensaje,
            [
                {
                  //si dice que no, vuelvo a la pantalla cliente
                    text: "Cancelar",
                    onPress: () => navigation.navigate('Cliente', { usuario: usuario, direccion: direccion }),
                    style: "cancel"
                },
                //si dice que si, agrego el viaje a la lista de viajes del cliente sin importar si es vip o no y vuelvo a la pantalla cliente
                //si el cliente es vip el descuento se aplicaria a la hora de pagar el viaje pero se agregara a la lista de viajes como si no lo tuviera
                { text: "OK", onPress: () => fetchAgregarViaje(Viaje) }
            ],
            { cancelable: false }
        );
   }
   function fetchAgregarViaje(Viaje) {
    //si el usuario tiene viajes
    if (usuario.viajes != null) {
      for (let i = 0; i < usuario.viajes.length; i++) {
        if (usuario.viajes[i].id == Viaje.id) {
            Alert.alert(
                "Error",
                "El viaje ya se encuentra en su lista de viajes",
                [
                    {
                        text: "OK",
                        onPress: () => navigation.navigate('Cliente', { usuario: usuario, direccion: direccion }),
                        style: "cancel"
                    }
                ],
                { cancelable: false }
            );
            return;
        }
    }
    } 
    const requestOptions = {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({})
    };
    fetch('http://'+direccion+'/api/clientes/agregarViaje/' + usuario.id + '/' + Viaje.id, requestOptions)
        .then(response => response.json())
        .then(result => setUsuario(result))
        .catch(error => console.log('error', error));
    navigation.navigate('Cliente', { usuario: usuario, direccion: direccion });
   }

    const listarViajes = ({ item }) => {
      return (
        <View >
          <TouchableOpacity onPress={() => agregarViaje(item)}>
           <AgenciaCartaViajeCliente
              viaje={item}
              navigation={navigation}
              cliente={usuario}
            />
          </TouchableOpacity>
        </View>
      );
    };
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

export default ListarViajes

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