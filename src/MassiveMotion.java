/**
 * @author Ty Van Heerden
 * @since Oct. 10, 2025
 * @version 1.0
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import java.util.Properties;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * This class simulates a 2-dimensional space with celestial objects in motion within a 2D canvas
 * 
 * WARNING: if you think the stars are "colliding" with each other, that's just an illusion. It's actually a 3d space, so these stars are overlapping as they pass different depths
 */
public class MassiveMotion extends JPanel implements ActionListener {

    protected Timer tm; 

    protected int timer_delay;
    protected String listRealisations;
    protected int window_size_x;
    protected int window_size_y;
    protected int star_position_x;
    protected int star_position_y;
    protected int star_size;
    protected int star_velocity_x;
    protected int star_velocity_y;
    protected double gen_x;
    protected double gen_y;
    protected int body_size;
    protected int body_velocity;
    protected List<CelestialBody> bodies;
    protected Random random;

    /**
     * Reference Kode Java on reading configuration file (https://kodejava.org/how-do-i-read-a-configuration-file-using-java-util-properties/)
     * 
     * @param propfile the name of the configuration file (MassiveMotion.config)
     */
    
    public MassiveMotion(String propfile) {
        Properties prop = new Properties();
        try {
            // Load configuration file
            String fileName = propfile;
            ClassLoader classLoader = MassiveMotion.class.getClassLoader();

            //Make sure that the configuration file exists
            URL res = Objects.requireNonNull(classLoader.getResource(fileName), "Can't find configuration file MassiveMotion.config");

            InputStream is = new FileInputStream(res.getFile());
            prop.load(is);

            // Get and assign values that was read from .config file
            this.timer_delay = Integer.parseInt(prop.getProperty("timer_delay"));
            this.window_size_x = Integer.parseInt(prop.getProperty("window_size_x"));
            this.window_size_y = Integer.parseInt(prop.getProperty("window_size_y"));
            this.star_position_x = Integer.parseInt(prop.getProperty("star_position_x"));
            this.star_position_y = Integer.parseInt(prop.getProperty("star_position_y"));
            this.star_size = Integer.parseInt(prop.getProperty("star_size"));
            this.star_velocity_x = Integer.parseInt(prop.getProperty("star_velocity_x"));
            this.star_velocity_y = Integer.parseInt(prop.getProperty("star_velocity_y"));
            this.gen_x = Double.parseDouble(prop.getProperty("gen_x"));
            this.gen_y = Double.parseDouble(prop.getProperty("gen_y"));
            this.body_size = Integer.parseInt(prop.getProperty("body_size"));
            this.body_velocity = Integer.parseInt(prop.getProperty("body_velocity"));
            this.random = new Random();

            // Initialize timer
            this.tm = new Timer(timer_delay, this);

            // Initialize the list structure based on what's in the configuration file
            this.listRealisations = prop.getProperty("list");
            if (listRealisations.equalsIgnoreCase("arraylist")){
                this.bodies = new ArrayList<>();
            }else if (listRealisations.equalsIgnoreCase("linkedlist")){
                this.bodies = new LinkedList<>();
            }else if (listRealisations.equalsIgnoreCase("doublylinkedlist")){
                this.bodies = new DoublyLinkedList<>();
            }else{
                this.bodies = new DummyHeadLinkedList<>();
            }

            // Adding the stationary star to the center of the canvas at bodies(0)
            CelestialBody star = new CelestialBody(this.star_position_x, this.star_position_y, this.star_velocity_x, this.star_velocity_y, this.star_size, Color.RED);
            this.bodies.add(star);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Paint each of the celestial bodies stored in the list
     * Each celestial body is drawn as a filled Oval with "fillOval" using its position and color
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Probably best you leave this as is.
        try {
            for (int i = 0; i < this.bodies.size(); i++){
                CelestialBody body = this.bodies.get(i);

                g.setColor(body.color);
                g.fillOval(body.x, body.y, body.size, body.size);
            }
        } catch (Exception e) {
            System.err.println("An error has occurred trying to paint components in body");
        }
        
        // Recommend you leave the next line as is
        tm.start();
    }

    /**
     * Helper spawnComets method that randomly spawns new comets along the top/bottom or left/right edges of the canvas
     * Each comet is assigned random values for its' velocity then added to the list of bodies
     */
    private void spawnComets(){
        if (this.bodies == null || this.bodies.size() == 0){
            return;
        }

        double randx = this.random.nextDouble();
        int canvasWidth = this.window_size_x;
        int canvasHeight = this.window_size_y;
        boolean spawnX;

        if (randx <= this.gen_x){
            spawnX = true;
        }else{
            spawnX = false;
        }
        
        // Spawn either at the top or bottom of the canvas
        if (spawnX){
            boolean top = this.random.nextBoolean();
            int x = this.random.nextInt(canvasWidth);
            int y;

            if (top){
                y = 0;
            }else{
                y = canvasHeight - this.body_size;
            }

            int velocityX = 0;
            int velocityY = 0;
            
            while (velocityX == 0){
                velocityX = this.random.nextInt((this.body_velocity * 2) + 1) - this.body_velocity;
            }

            while (velocityY == 0){
                velocityY = this.random.nextInt((this.body_velocity * 2) + 1) - this.body_velocity;
            }

            CelestialBody comet = new CelestialBody(x, y, velocityX, velocityY, this.body_size, Color.BLACK);
            this.bodies.add(comet);
        }

        double randy = this.random.nextDouble();
        boolean spawnY;

        if (randy <= gen_y){
            spawnY = true;
        }else{
            spawnY = false;
        }

        // Spawn either at the left or right of the canvas
        if (spawnY){
            boolean left = this.random.nextBoolean();
            int y = this.random.nextInt(canvasHeight);
            int x;

            if(left){
                x = 0;
            }else{
                x = canvasWidth - this.body_size;
            }

            int velocityX = 0;
            int velocityY = 0;

            while (velocityX == 0){
                velocityX = this.random.nextInt((this.body_velocity * 2) + 1) - this.body_velocity;
            }

            while(velocityY == 0){
                velocityY = this.random.nextInt((this.body_velocity * 2) + 1) - this.body_velocity;
            }

            CelestialBody comet = new CelestialBody(x, y, velocityX, velocityY, this.body_size, Color.BLACK);
            this.bodies.add(comet);
        }
    }

    /**
     * actionPerformed method ensures that it updates the position of each comet spawned on the screen
     * Remove any comet that have moved beyond the canvas
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        spawnComets();

        try {
            for (int i = this.bodies.size() - 1; i >= 1; i--){
                CelestialBody comets = this.bodies.get(i);
                comets.x += comets.velocityX;
                comets.y += comets.velocityY;

                // Remove comets that moved beyond the canvas
                if (comets.x < 0 || comets.x > this.window_size_x || comets.y < 0 || comets.y > this.window_size_y){
                    this.bodies.remove(i);
                }
            }
        } catch (Exception e) {
            System.err.println("Error occured while action performed on moving or removing celestial bodies");
        }

        // Keep this at the end of the function (no matter what you do above):
        repaint();
    }

    public static void main(String[] args) {
        System.out.println("Massive Motion starting...");
        // MassiveMotion mm = new MassiveMotion(args[0]);
        MassiveMotion mm = new MassiveMotion("MassiveMotion.config");

        JFrame jf = new JFrame();
        jf.setTitle("Massive Motion");
        jf.setSize(mm.window_size_x, mm.window_size_y); //Fixed: Replaced to fit with config.
        jf.add(mm);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
