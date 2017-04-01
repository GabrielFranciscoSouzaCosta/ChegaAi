
package recurso;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "operacao")
public class Operacao {
    int x;
    int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
    
}
