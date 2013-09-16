package za.co.sourlemon.zambies.ems.systems;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import za.co.sourlemon.zambies.ems.nodes.RenderNode;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import za.co.sourlemon.zambies.ems.AbstractSystem;
import za.co.sourlemon.zambies.ems.Engine;
import za.co.sourlemon.zambies.ems.components.CameraLock;
import za.co.sourlemon.zambies.ems.nodes.EventNode;
import static za.co.sourlemon.zambies.App.*;
import za.co.sourlemon.zambies.ems.Event;
import za.co.sourlemon.zambies.ems.EventManager;
import za.co.sourlemon.zambies.ems.components.KeyPress;
import za.co.sourlemon.zambies.ems.components.KeyTap;
import za.co.sourlemon.zambies.ems.components.MousePress;
import za.co.sourlemon.zambies.ems.components.MouseTap;

/**
 *
 * @author daniel
 */
public class AWTRenderSystem extends AbstractSystem
{

    private Frame frame;
    private RenderCanvas canvas;
    private Queue<Event> taps = new ConcurrentLinkedQueue<>();
    private Queue<E> presses = new ConcurrentLinkedQueue<>();
    private float mouseX, mouseY;
    private boolean[] button = new boolean[15];
    private boolean windowClosing = false;
    private float camX, camY, camOX, camOY;
    
    private class E
    {
        Event e;
        boolean value;

        public E(Event e, boolean value)
        {
            this.e = e;
            this.value = value;
        }
        
    }

    private void handleEvents()
    {
        EventNode events = engine.getNode(EventNode.class);
        EventManager eventManager = engine.getEventManager();
        
        while (!taps.isEmpty())
        {
            Event e = taps.remove();
            eventManager.set(e, false);
        }

        // since this is the only place where events are dequeued,
        // there will always be numEvents or more events on the queue
        // when this followin line is executed.
        int numEvents = presses.size();
        for (int i = 0; i < numEvents; i++)
        {
            E e = presses.remove();
            eventManager.set(e.e, e.value);
            if (e.value)
            {
                Event tap = new Event(e.e.getSymbol(), e.e.getSource() == KeyPress.class ? KeyTap.class : MouseTap.class);
                eventManager.set(tap, e.value);
                taps.add(tap);
            }
        }

        events.aim.x = mouseX - camX - camOX;
        events.aim.y = mouseY - camY - camOY;
        events.window.windowClosing = windowClosing;
    }

    class RenderCanvas extends Canvas
    {

        public BufferedImage buffer = null;
        public BufferStrategy strategy;

        public void init()
        {
            createBufferStrategy(2);
            strategy = getBufferStrategy();
        }
    }

    @Override
    public boolean start(Engine engine)
    {
        super.start(engine);

        canvas = new RenderCanvas();

        frame = new Frame(WINDOW_TITLE);
        frame.add(canvas);
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        frame.setVisible(true);
        frame.setResizable(false);
        canvas.init();
        canvas.requestFocus();

        camOX = SCREEN_WIDTH / 2;
        camOY = SCREEN_HEIGHT / 2;

        frame.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                windowClosing = true;
            }
        });

        canvas.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                presses.add(new E(new Event(e.getKeyCode(), KeyPress.class), true));
            }

            @Override
            public void keyReleased(KeyEvent e)
            {
                presses.add(new E(new Event(e.getKeyCode(), KeyPress.class), false));
            }
        });

        MouseAdapter mouse = new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                presses.add(new E(new Event(e.getButton(), MousePress.class), true));
            }

            @Override
            public void mouseReleased(MouseEvent e)
            {
                presses.add(new E(new Event(e.getButton(), MousePress.class), false));
            }

            @Override
            public void mouseMoved(MouseEvent e)
            {
                mouseX = e.getX();
                mouseY = e.getY();
            }

            @Override
            public void mouseDragged(MouseEvent e)
            {
                mouseMoved(e);
            }
        };

        canvas.addMouseListener(mouse);
        canvas.addMouseMotionListener(mouse);

        return true;
    }

    @Override
    public void update(double delta)
    {
        handleEvents();

        Graphics2D g = (Graphics2D) canvas.strategy.getDrawGraphics();

        g.setBackground(Color.WHITE);
        g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        AffineTransform origT = g.getTransform();
        g.transform(AffineTransform.getTranslateInstance(camX + camOX, camY + camOY));

        List<RenderNode> nodes = engine.getNodeList(RenderNode.class);

        for (RenderNode node : nodes)
        {
            if (node.getEntity().has(CameraLock.class))
            {
                camX = -node.position.x;
                camY = -node.position.y;
            }

            AffineTransform oldT = g.getTransform();

            g.transform(AffineTransform.getTranslateInstance(
                    node.position.x, node.position.y));
            g.transform(AffineTransform.getRotateInstance(node.position.theta));
            g.transform(AffineTransform.getScaleInstance(node.position.scaleX, node.position.scaleY));

            g.setColor(node.renderable.color);
            g.fillRect(-1, -1, 2, 2);

            g.setTransform(oldT);
        }

        g.setTransform(origT);

        g.dispose();

        canvas.strategy.show();

        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void end()
    {
        frame.dispose();
    }

    @Override
    public String toString()
    {
        return "AWT";
    }
    
}
