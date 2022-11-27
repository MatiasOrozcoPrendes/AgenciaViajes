import { StyleSheet, Text, View } from 'react-native'
import React from 'react'
import { useState, useEffect } from 'react';
import { Avatar } from 'react-native-paper';

const AgenciaCartaViajeCliente = (props) => {
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
    navigator = props.navigation;
  return (
    <View style={[styles.contenedor, props.style]}>
        <View style={styles.unaLinea}>
            <Avatar.Icon size={50} icon={icon} />
            <View>
                <Text style={styles.texto}>{props.viaje.destino}</Text>
                <Text style={styles.texto}>{props.viaje.fechaInicio}</Text>
            </View>
            {/* si el cliente es vip el precio lleva un descuento del 20% */}
            <Text style={styles.texto}>${props.viaje.precio}</Text>
        </View>

    </View>   
  )
}

export default AgenciaCartaViajeCliente

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
    avatar: {
        margin: 10,
    }
})