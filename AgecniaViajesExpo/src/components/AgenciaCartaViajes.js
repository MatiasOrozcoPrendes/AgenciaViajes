import { StyleSheet, Text, View, Alert } from 'react-native'
import React from 'react'
import { useState, useEffect } from 'react';
import { Avatar, Button } from 'react-native-paper';

const AgenciaCartaViajes = (props) => {
    const direccion = props.direccion;
    const [icon, setIcon] = useState('account');
    useEffect(() => {
        if (props.viaje.modalidad == 'A') {
            setIcon('airplane');
        } else if (props.viaje.modalidad == 'T') {
            setIcon('bus');
        } else if (props.viaje.modalidad == 'M') {
            setIcon('anchor');
        }
    }, []);

    const navigator = props.navigation;
    function eliminar (viaje){
      const requestOptions = {
          method: 'DELETE',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({
              "id": viaje.id,
          })
      };
      fetch(('http://'+direccion+'/api/viajes/' + viaje.id), requestOptions)
          .then(response => response.json())
          .then(result => console.log(result))
          .catch(error => console.log('error', error));
      Alert.alert('Exito', 'Viaje eliminado con exito');
      navigator.navigate('Administrador', {direccion: direccion});
    }
    function editar (viaje){
      navigator.navigate('AgregarViaje', {viaje: viaje, direccion: direccion});
    }

  return (
    <View style={[styles.contenedor, props.style]}>
        <View style={styles.unaLinea}>
            <Avatar.Icon size={50} icon={icon} />
            <View>
                <Text style={styles.texto}>{props.viaje.destino}</Text>
                <Text style={styles.texto}>{props.viaje.fechaInicio}</Text>
            </View>
            

        </View>
        <View style={styles.unaLinea}>
            <Button 
                style={styles.boton}
                labelStyle={styles.textoBoton}
                icon="pen"
                mode="elevated" 
                buttonColor='blue'
                textColor='white'
                onPress={() => editar(props.viaje)}/>

            <Button 
                style={styles.boton}
                labelStyle={styles.textoBoton}
                icon="trash-can"
                mode="elevated" 
                buttonColor='red'
                textColor='white'
                onPress={() => eliminar(props.viaje)} />
        </View>

    </View>    
  )
}

export default AgenciaCartaViajes

const styles = StyleSheet.create({
  unaLinea: {
    flexDirection: 'row',
    justifyContent: 'space-between',
},
contenedor: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    width: '100%',
    backgroundColor: 'white',
    borderRadius: 10,
    padding: 10,
    margin: 10,
},
texto: {
    fontSize: 20,
    fontWeight: 'bold',
    color: 'black',
},
boton: {
    justifyContent: 'center',
    alignItems: 'center',
    width: 20,
    height: 45,
    margin: 5,
    
},
textoBoton: {
    fontSize: 20,
    fontWeight: 'bold',
    color: 'white',
},
avatar: {
    margin: 5,
}

})