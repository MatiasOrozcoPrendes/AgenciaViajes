import React from "react";
import { createStackNavigator } from "@react-navigation/stack";
import { NavigationContainer } from "@react-navigation/native";
const Stack = createStackNavigator();

//Se importan los componentes de las diferentes pantallas
import Principal from "../screens/Principal";
import Cliente from "../screens/Cliente";
import Administrador from "../screens/Administrador";
import GestionViajes from "../screens/GestionViajes";
import GestionUsuarios from "../screens/GestionUsuarios";
import AgregarUsuario from "../screens/AgregarUsuario";
import AgregarViaje from "../screens/AgregarViaje";
import ListarViajes from "../screens/ListarViajes";
import ListarViajesUsuario from "../screens/ListarViajesUsuario";
import ListarViajesAnteriores from "../screens/ListarViajesAnteriores";

const RootStack = () => {
  return (
    <NavigationContainer>
        {/* Se indica en que pantalla se inicia la app */}
        <Stack.Navigator initialRouteName="Principal">
            {/* Se indica el nombre de la pantalla y el componente que se va a renderizar 
            ademas de los parametros que se le pasan a la pantalla 
            como el color del header y el titulo */}
            <Stack.Screen
                name="Inicio"
                component={Principal}
                options={{
                title: "Agencia de Viajes",
                headerStyle: {
                    backgroundColor: "#f4511e",
                },
                headerTintColor: "#210B61",
                headerTitleStyle: {
                    color: 'black',
                    fontSize: 25,
                    fontWeight: 'bold',
                }
            }}/>
            <Stack.Screen
                name="Cliente"
                component={Cliente}
                options={{
                title: "Cliente",
                headerStyle: {
                    backgroundColor: "#f4511e",
                },
                headerTintColor: "#210B61",
                headerTitleStyle: {
                    color: 'black',
                    fontSize: 25,
                    fontWeight: 'bold',
                }
            }}/>
            <Stack.Screen
                name="Administrador"
                component={Administrador}
                options={{
                title: "Administrador",
                headerStyle: {
                    backgroundColor: "#f4511e",
                },
                headerTintColor: "#210B61",
                headerTitleStyle: {
                    color: 'black',
                    fontSize: 25,
                    fontWeight: 'bold',
                }
            }}/>
            <Stack.Screen
                name="GestionViajes"
                component={GestionViajes}
                options={{
                title: "Gestion de Viajes",
                headerStyle: {
                    backgroundColor: "#f4511e",
                },
                headerTintColor: "#210B61",
                headerTitleStyle: {
                    color: 'black',
                    fontSize: 25,
                    fontWeight: 'bold',
                }
            }}/>
            <Stack.Screen
                name="GestionUsuarios"
                component={GestionUsuarios}
                options={{
                title: "Gestion de Usuarios",
                headerStyle: {
                    backgroundColor: "#f4511e",
                },
                headerTintColor: "#210B61",
                headerTitleStyle: {
                    color: 'black',
                    fontSize: 25,
                    fontWeight: 'bold',
                }
            }}/>
            <Stack.Screen
                name="AgregarUsuario"
                component={AgregarUsuario}
                options={{
                title: "Agregar Usuario",
                headerStyle: {
                    backgroundColor: "#f4511e",
                },
                headerTintColor: "#210B61",
                headerTitleStyle: {
                    color: 'black',
                    fontSize: 25,
                    fontWeight: 'bold',
                }
            }}/>
            <Stack.Screen
                name="AgregarViaje"
                component={AgregarViaje}
                options={{
                title: "Agregar Viaje",
                headerStyle: {
                    backgroundColor: "#f4511e",
                },
                headerTintColor: "#210B61",
                headerTitleStyle: {
                    color: 'black',
                    fontSize: 25,
                    fontWeight: 'bold',
                }
            }}/>
            <Stack.Screen
                name="ListarViajes"
                component={ListarViajes}
                options={{
                title: "Listar Viajes",
                headerStyle: {
                    backgroundColor: "#f4511e",
                },
                headerTintColor: "#210B61",
                headerTitleStyle: {
                    color: 'black',
                    fontSize: 25,
                    fontWeight: 'bold',
                }
            }}/>
            <Stack.Screen
                name="ListarViajesUsuario"
                component={ListarViajesUsuario}
                options={{
                title: "Listar Viajes del Cliente",
                headerStyle: {
                    backgroundColor: "#f4511e",
                },
                headerTintColor: "#210B61",
                headerTitleStyle: {
                    color: 'black',
                    fontSize: 25,
                    fontWeight: 'bold',
                }
            }}/>
            <Stack.Screen
                name="ListarViajesAnteriores"
                component={ListarViajesAnteriores}
                options={{
                title: "Listar Viajes Anteriores",
                headerStyle: {
                    backgroundColor: "#f4511e",
                },
                headerTintColor: "#210B61",
                headerTitleStyle: {
                    color: 'black',
                    fontSize: 25,
                    fontWeight: 'bold',
                }
            }}/>
            
            

        </Stack.Navigator>
    </NavigationContainer>

  );
}

export default RootStack
