package Ticketera.Servicios;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.nio.file.Path;
public class BaseDeDatosServicios {
    private Connection conexion;
    private String servidor;
    private String usuario;
    private String puerto;
    private String basedato;
    private String password;
    private String motor;
    private String nameClass;
    private boolean conecta;

    public Path getArchivoConfig() {
        return archivoConfig;
    }

    public void setArchivoConfig(Path archivoConfig) {
        this.archivoConfig = archivoConfig;
    }

    private Path archivoConfig;

    public String getNameClass() {
        return nameClass;
    }

    public void setNameClass(String nameClass) {
        this.nameClass = nameClass;
    }

    public String getServidor() {
        return servidor;
    }

    public boolean isConecta() {
        return conecta;
    }

    public void setConecta(boolean conecta) {
        this.conecta = conecta;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    public String getBasedato() {
        return basedato;
    }

    public void setBasedato(String basedato) {
        this.basedato = basedato;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public void conectar() throws Exception {
        try {
            if (archivoConfig != null){
                instanciarPro(archivoConfig);
            } else {
                instanciarPro();
            }
            Class.forName(nameClass);
            if (nameClass.equals("com.mysql.jdbc.Driver")) {
                conexion = DriverManager.getConnection(motor + servidor + ":" + puerto + "/" + basedato + "?useSSL=false", usuario, password);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void instanciarPro() throws FileNotFoundException, IOException {
        try {
            Properties prop = new Properties();
            String home = System.getProperty("user.dir");
            File f = new File(home + "/propertyDB.properties"); // da una ruta absoluta al archivo
            prop.load(new FileInputStream(f));
            servidor = (prop.getProperty("servidor"));
            basedato = (prop.getProperty("database"));
            usuario = (prop.getProperty("usuario"));
            password = (prop.getProperty("password"));
            puerto = (prop.getProperty("puerto"));
            motor = (prop.getProperty("motor"));
            nameClass = (prop.getProperty("nameClass"));
        } catch (Exception e) {
            throw e;
        }
    }
    public void instanciarPro(Path archivocConfiguracion) throws FileNotFoundException, IOException {
        try {
            Properties prop = new Properties();
            File f = new File(String.valueOf(archivocConfiguracion));
            prop.load(new FileInputStream(f));
            servidor = (prop.getProperty("servidor"));
            basedato = (prop.getProperty("database"));
            usuario = (prop.getProperty("usuario"));
            password = (prop.getProperty("password"));
            puerto = (prop.getProperty("puerto"));
            motor = (prop.getProperty("motor"));
            nameClass = (prop.getProperty("nameClass"));
        } catch (Exception e) {
            throw e;
        }
    }


    public void cerrar() throws Exception {
        try {
            if (conexion != null) {
                if (conexion.isClosed() == false) {
                    conexion.close();
                }
            }
        } catch (Exception e) {
        }
    }


}
