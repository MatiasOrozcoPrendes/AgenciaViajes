import { StyleSheet, Text, View, Alert } from 'react-native'
import React from 'react';
import { useState, useEffect } from 'react';
import { Avatar, Button } from 'react-native-paper';

const AgenciaCartaUsuario = (props) => {
    const navigator = props.navigation;
    const direccion = props.direccion;
    function eliminar (usuario){
        const requestOptions = {
            method: 'DELETE',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                "id": usuario.id,
            })
        };
        fetch(('http://'+direccion+'/api/' + (usuario.esAdministrador ? 'usuarios' : 'clientes') + '/' + usuario.id), requestOptions)
            .then(response => response.json())
            .then(result => console.log(result))
            .catch(error => console.log('error', error));
        Alert.alert('Exito', 'Usuario eliminado con exito');
        navigator.navigate('Administrador', {direccion: direccion});

    }
    function editar (usuario){
        navigator.navigate('AgregarUsuario', {usuario: usuario, direccion: direccion});
    }
  return (
    <View style={[styles.contenedor, props.style]}>
        <View style={styles.unaLinea}>
            {/* Coloco A o C dependiendo si es administrador o cliente  */}
            <Avatar.Text size={50} label={props.usuario.esAdministrador ? 'A' : 'C'} styles={styles.avatar} />
            <View>
                <Text style={styles.texto}>{props.usuario.nombre}</Text>
                <Text style={styles.texto}>{props.usuario.apellido}</Text>
            </View>
            <Button 
                style={styles.boton}
                labelStyle={styles.textoBoton}
                icon="pen"
                mode="elevated" 
                buttonColor='blue'
                textColor='white'
                onPress={() => editar(props.usuario)}/>

            <Button 
                style={styles.boton}
                labelStyle={styles.textoBoton}
                icon="trash-can"
                mode="elevated" 
                buttonColor='red'
                textColor='white'
                onPress={() => eliminar(props.usuario)} />

        </View>
    </View>
  )
}

export default AgenciaCartaUsuario

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
        height: 100,
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