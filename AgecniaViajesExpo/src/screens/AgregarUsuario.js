import { StyleSheet, Text, View, SafeAreaView, Alert } from 'react-native';
import React, { useEffect, useState } from 'react'
import { TextInput, Button, Checkbox } from 'react-native-paper';

const AgregarUsuario = ({navigation, route }) => {
  const direccion = route.params.direccion;
    const [id, setId] = useState('');
    const [ci, setCi] = useState('');
    const [nombre, setNombre] = useState('');
    const [apellido, setApellido] = useState('');
    const [email, setEmail] = useState('');
    const [contraseña, setContraseña] = useState('');
    const [checked, setChecked] = useState(false);
    const [checkDisabled, setCheckDisabled] = useState(false);
    const [ciDisabled, setCiDisabled] = useState(false);
    const [esEdicion, setEsEdicion] = useState(false);

    useEffect(() => {
          // si route.params.usuario no es undefined, entonces es una edicion
          // si route.params.usuario es undefined, entonces es un alta
          if (route.params?.usuario) {
            const usuario = route.params.usuario;

            setId(usuario.id);
            setCi(usuario.ci.toString());
            setNombre(usuario.nombre);
            setApellido(usuario.apellido);
            setEmail(usuario.email);
            setContraseña(usuario.password);
            setChecked(usuario.esAdministrador);
            setCheckDisabled(true);
            setCiDisabled(true);
            setEsEdicion(true);
         }
        }, [route.params?.usuario]);
    function AgregarUsuario() {
      if (ci == '' || nombre == '' || apellido == '' || email == '' || contraseña == '') {
        Alert.alert('Error', 'Debe completar todos los campos');
      } else {
        if (checked) {
          if (esEdicion) {
            ModificarAdministrador();
            Alert.alert('Exito', 'Administrador modificado con exito');
          } else {
            AgregarAdministrador();
            Alert.alert('Exito', 'Administrador agregado con exito');
          }
          navigation.navigate('Administrador', {direccion: direccion});
        } else {
          if (esEdicion) {
            ModificarCliente();
            Alert.alert('Exito', 'Cliente modificado con exito');
          } else {
            AgregarCliente();
            Alert.alert('Exito', 'Cliente agregado con exito');
          }
          navigation.navigate('Administrador', {direccion: direccion});
        }
      }
    }
    function AgregarAdministrador() {
      const requestOptions = {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({ 
            "ci": ci,
            "nombre": nombre,
            "apellido": apellido,
            "email": email,
            "password": contraseña, 
          })
      };
      fetch('http://'+direccion+'/api/usuarios', requestOptions)
          .then(response => response.json())
          .then(result => console.log(result))
          .catch(error => console.log('error', error));
    }
    function AgregarCliente() {
      const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ 
          "ci": ci,
          "nombre": nombre,
          "apellido": apellido,
          "email": email,
          "password": contraseña, 
        })
      };
      fetch('http://'+direccion+'/api/clientes', requestOptions)
          .then(response => response.json())
          .then(result => console.log(result))
          .catch(error => console.log('error', error));
    }
    function ModificarAdministrador() {
      const requestOptions = {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ 
          "id": id,
          "ci": ci,
          "nombre": nombre,
          "apellido": apellido,
          "email": email,
          "password": contraseña, 
        })
      };
      fetch('http://'+direccion+'/api/usuarios/' + id, requestOptions)
          .then(response => response.json())
          .then(result => console.log(result))
          .catch(error => console.log('error', error));
    }
    function ModificarCliente() {
      const requestOptions = {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          "id": id,
          "ci": ci,
          "nombre": nombre,
          "apellido": apellido,
          "email": email,
          "password": contraseña,
        })
      };
      fetch('http://'+direccion+'/api/clientes/' + id, requestOptions)
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
                placeholder="CI"
                onChangeText={setCi}
                keyboardType="numeric"
                value={ci}
                disabled={ciDisabled}
            />
            <TextInput
                style={styles.input}
                placeholder="Nombre"
                onChangeText={setNombre}
                value={nombre}
            />
            <TextInput
                style={styles.input}
                placeholder="Apellido"
                onChangeText={setApellido}
                value={apellido}
            />
            <TextInput 
                style={styles.input} 
                placeholder="Email" 
                onChangeText={setEmail} 
                value={email}
                keyboardType="email-address"
                autoCapitalize='none'
            />
            <TextInput 
                style={styles.input} 
                placeholder="Contraseña" 
                onChangeText={setContraseña} 
                value={contraseña} 
                secureTextEntry={true}
                autoCapitalize='none'
            />
            <View style={styles.unalinea}>
              <Checkbox
                status={checked ? 'checked' : 'unchecked'}
                disabled={checkDisabled}
                onPress={() => {
                  setChecked(!checked);
                }}
              />
              <Text style={styles.label}>Administrador</Text>
            </View>
           <Button 
                style={styles.boton}
                labelStyle={styles.textoBoton}
                icon="content-save-all"
                mode="elevated" 
                buttonColor='blue' 
                textColor='white'
                onPress={() => AgregarUsuario()}
            >
                Agregar
            </Button>
        </View>
        
      </View>
   </SafeAreaView>
  )
}

export default AgregarUsuario

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
    unalinea: {
      flexDirection: 'row',
      alignItems: 'center',
      justifyContent: 'center',
    },
    input: {
      width: 250,
      height: 30,
      margin: 10,
      padding: 8,
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
})