/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package craftmine2;

/**
 *
 * @author joseph
 */
public class ClientData {
    String username;
    String password;
    int id;
    double X;
    double Y;
    double Z;
    float Rot;
    float Yaw;

    public ClientData() {
    }

    public float getRot() {
        return Rot;
    }

    public void SetPosition(double x, double y, double z){
        X=x;
        Y=y;
        Z=z;
    }
    
    public void SetAngle(float rot, float yaw){
        Rot=rot;
        Yaw=yaw;
    }

    public void setRot(float Rot) {
        this.Rot = Rot;
    }

    public double getX() {
        return X;
    }

    public void setX(double X) {
        this.X = X;
    }

    public double getY() {
        return Y;
    }

    public void setY(double Y) {
        this.Y = Y;
    }

    public float getYaw() {
        return Yaw;
    }

    public void setYaw(float Yaw) {
        this.Yaw = Yaw;
    }

    public double getZ() {
        return Z;
    }

    public void setZ(double Z) {
        this.Z = Z;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    

    
}
