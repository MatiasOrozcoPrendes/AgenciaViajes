import { StyleSheet, Text, View, SafeAreaView, Alert } from 'react-native';
import { RadioButton, TextInput, Button } from 'react-native-paper';
import React, { useEffect, useState } from 'react'

const AgregarViaje = ({navigation, route }) => {
    const direccion = route.params.direccion;
    const [id, setId] = useState('');
    const [destino, setDestino] = React.useState('');
    const [fecha, setFecha] = React.useState('');
    const [precio, setPrecio] = React.useState('');
    const [modalidad, setModalidad] = React.useState('');
    const [isAereo, setIsAereo] = React.useState(false);
    const [isTerrestre, setIsTerrestre] = React.useState(false);
    const [isMaritimo, setIsMaritimo] = React.useState(false);
    const [esEdicion, setEsEdicion] = useState(false);

    useEffect(() => {
        // si route.params.usuario no es undefined, entonces es una edicion
        // si route.params.usuario es undefined, entonces es un alta
        if (route.params?.viaje) {
            const viaje = route.params.viaje;
            setId(viaje.id);
            setDestino(viaje.destino);
            setFecha(viaje.fechaInicio);
            setPrecio(viaje.precio.toString());
            setModalidad(viaje.modalidad);
            // dependiendo de la modalidad, setear el radio button correspondiente
            if (viaje.modalidad == 'A') {
                setIsAereo(true);
            } else if (viaje.modalidad == 'T') {
                setIsTerrestre(true);
            } else if (viaje.modalidad == 'M') {
                setIsMaritimo(true);
            }
            setEsEdicion(true);
        }
    }, [route.params?.viaje]);
    
    function Agregar() {
        if (destino == '' || fecha == '' || precio == '' || modalidad == '' || EsDouble()) {
            Alert.alert('Error', 'Debe completar todos los campos');
        } else if (FechaPosterior()) {
            if (esEdicion) {
                ModificarViaje();
                Alert.alert('Exito', 'Viaje modificado con exito');
            } else {
                AgregarViaje();
                Alert.alert('Exito', 'Viaje agregado con exito');
            }
            navigation.navigate('Administrador', {direccion: direccion});
            } else {
                Alert.alert('Error', 'La fecha debe ser posterior a la actual');
            }
    }
    function FechaPosterior() {
        var fechaActual = new Date();
        // Le doy formato a la fecha ingresada teniendo en cuenta el desfase horario
        var fechaViaje = new Date(fecha + 'T00:00:00-03:00');
        if (fechaViaje > fechaActual) {
            return true;
        } else {
            return false;
        }
    }
    function EsDouble() {
        if (isNaN(precio)) {
            return true;
        } else {
            return false;
        }
    }
    function AgregarViaje() {
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ 
              "destino": destino,
              "fechaInicio": fecha,
              "modalidad": modalidad,
              "precio": precio,
            })
        };
        fetch('http://'+direccion+'/api/viajes', requestOptions)
            .then(response => response.json())
            .then(result => console.log(result))
            .catch(error => console.log('error', error));
    }
    function ModificarViaje() {
        const requestOptions = {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                "id": id,
                "destino": destino,
                "fechaInicio": fecha,
                "modalidad": modalidad,
                "precio": precio,
            })
        };
        fetch('http://'+direccion+'/api/viajes/' + id, requestOptions)
            .then(response => response.json())
            .then(result => console.log(result))
            .catch(error => console.log('error', error));
    }



  return (
    <SafeAreaView style={styles.container}>
      <View style={styles.viewContainer}>
        <View style={styles.generalView}>
            <TextInput
                style={styles.input}
                placeholder="Destino"
                onChangeText={setDestino}
                value={destino}
            />
           <TextInput
                style={styles.input}
                placeholder="Fecha"
                onChangeText={setFecha}
                value={fecha}
                keyboardType="numeric"
                
            />
            <TextInput 
                style={styles.input}
                placeholder="Precio"
                onChangeText={setPrecio}
                value={precio}
                keyboardType="numeric"
            />
            <View style={styles.unalinea}>
                <View  style={styles.unalinea}>
                    <RadioButton
                        style={styles.radio}
                        value="aereo"
                        status={ isAereo ? 'checked' : 'unchecked' }
                        onPress={() => {setIsAereo(!isAereo); setIsTerrestre(false); setIsMaritimo(false); setModalidad('A')}}
                    />
                    <Text style={styles.label}>Aereo</Text>
                </View>
                <View style={styles.unalinea}>
                    <RadioButton
                        style={styles.radio}
                        value="terrestre"
                        status={ isTerrestre ? 'checked' : 'unchecked' }    
                        onPress={() => {setIsTerrestre(!isTerrestre); setIsAereo(false); setIsMaritimo(false); setModalidad('T')}}
                    />
                    <Text style={styles.label}>Terrestre</Text>
                </View>
                <View style={styles.unalinea}>
                    <RadioButton
                        style={styles.radio}
                        value="maritimo"
                        status={ isMaritimo ? 'checked' : 'unchecked' }
                        onPress={() => {setIsMaritimo(!isMaritimo); setIsAereo(false); setIsTerrestre(false); setModalidad('M')}}
                    />
                    <Text style={styles.label}>Maritimo</Text>
                </View>
            </View>    
            <Button 
                style={styles.boton}
                labelStyle={styles.textoBoton}
                icon="plus"
                mode="elevated" 
                buttonColor='blue'
                textColor='white'
                onPress={() => Agregar()
            }>
                Agregar
            </Button>
            
        </View>
      </View>
   </SafeAreaView>
  )
}

export default AgregarViaje

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
        padding: 10,
        
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
    radio: {
        marginRight: 10,
        marginLeft: 10,
    },
    label: {
        margin: 8,
        fontSize: 16,
        fontWeight: 'bold',
    },
    unalinea: {
        flexDirection: "row",
        alignItems: 'center',
        justifyContent: 'center',
        marginBottom: 20,

    },
})