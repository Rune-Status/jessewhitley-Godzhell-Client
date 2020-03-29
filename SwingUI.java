
import sign.signlink;
import javax.swing.*;
import java.awt.*;
import java.lang.String;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.plaf.metal.MetalLookAndFeel;
import sign.signlink;
import javax.sound.midi.*;
import java.util.List;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;



import javax.swing.*;
import java.awt.*;
import java.lang.String;
import javax.swing.JTabbedPane;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import javax.swing.JTabbedPane;
import java.awt.event.KeyEvent;
import java.math.BigInteger;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


import javax.swing.JPanel;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.plaf.metal.MetalLookAndFeel;
import sign.signlink;
import javax.sound.midi.*;
import java.util.List;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.*;
import sign.signlink;
import java.net.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;


public class SwingUI extends client implements ActionListener {

    private static JMenuItem menuItem;
	private JFrame frame;
    private MP3Player player;
	    public int midiCount;
   public Sequencer sequencer;
   private static boolean isApplet = false;

	public SwingUI(String args[]) {
		super();
		try {
			sign.signlink.startpriv(InetAddress.getByName(server));
			initUI();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void initUI() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			JFrame.setDefaultLookAndFeelDecorated(true);
			JPopupMenu.setDefaultLightWeightPopupEnabled(false);
			frame = new JFrame("GodzHell.com 3D MMO MMORPG RPG - massive multiplayer online role playing game.");
			frame.setLayout(new BorderLayout());
			frame.setResizable(false);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			JPanel gamePanel = new JPanel();

			gamePanel.setLayout(new BorderLayout());
			gamePanel.add(this);
			gamePanel.setPreferredSize(new Dimension(765, 503));

			initMenubar();
			frame.getContentPane().add(gamePanel, BorderLayout.CENTER);
			frame.pack();

			frame.setVisible(true); // can see the client
			frame.setResizable(false); // resizeable frame

			init();
			midiPlayer("Play");
		} catch (Exception e) {
				e.printStackTrace();
		}
	}

	public void initMenubar() {
			JMenu fileMenu = new JMenu("File");
			JMenu musicmenu = new JMenu("Music");
			JMenu toolsMenu = new JMenu("Lists");
			JMenu linksMenu = new JMenu("Links");
			String[] mainButtons = new String[] {"About", "Exit" };
			String[] musicButtons = new String[]
					{
						"Play", "Stop", "Next", "Previous"

	};
				String[] toolsButtons = new String[]
				{
					 "Item list", "NPC list", "Object list", "Custom Item list"
				};
				String[] LinkButtons = new String[]
				{
					 "Wikia", "Store", "Hiscores", "Vote"
				};
			for (String name : mainButtons) {
				JMenuItem menuItem = new JMenuItem(name);
				if (name.equalsIgnoreCase("-")) {
					fileMenu.addSeparator();
				} else {
					menuItem.addActionListener(this);
					fileMenu.add(menuItem);
				}
			}
			for (String name : musicButtons)
			{
				JMenuItem menuItem = new JMenuItem(name);
				if (name.equalsIgnoreCase("-"))
					musicmenu.addSeparator();
				else
				{
					menuItem.addActionListener(this);
					musicmenu.add(menuItem);
				}
}
			for (String name : toolsButtons)
			{
				JMenuItem menuItem = new JMenuItem(name);
				if (name.equalsIgnoreCase("-"))
					toolsMenu.addSeparator();
				else
				{
					toolsMenu.add(menuItem);
					menuItem.addActionListener(this);
				}
}
			for (String name : LinkButtons)
			{
				JMenuItem menuItem = new JMenuItem(name);
				if (name.equalsIgnoreCase("-"))
					linksMenu.addSeparator();
				else
				{
					linksMenu.add(menuItem);
					menuItem.addActionListener(this);
				}
}
			JMenuBar menuBar = new JMenuBar();
			JMenuBar jmenubar = new JMenuBar();
			JButton screenshot = new JButton("Screenshot");
			screenshot.setActionCommand("Screenshot");
			screenshot.addActionListener(this);

			frame.add(jmenubar);
			menuBar.add(screenshot);
			menuBar.add(fileMenu);
			menuBar.add(musicmenu);
			menuBar.add(toolsMenu);
			menuBar.add(linksMenu);
			frame.getContentPane().add(menuBar, BorderLayout.NORTH);
	}

	public URL getCodeBase() {
		try {
			return new URL("http://" + server + "/cache");
		} catch (Exception e) {
			return super.getCodeBase();
		}
	}

	public URL getDocumentBase() {
		return getCodeBase();
	}

	public void loadError(String s) {
		System.out.println("loadError: " + s);
	}

	public String getParameter(String key) {
			return "";
	}

	private static void openURL(String url) {
		Desktop d = Desktop.getDesktop();
		try {
			d.browse(new URI(url)); 	
		} catch (Exception e) {
		}
	}

	public void actionPerformed(ActionEvent evt) {
		String cmd = evt.getActionCommand();
		try {
			if (cmd != null) {
				if (cmd.equalsIgnoreCase("exit")) {
					System.exit(0);
				} else if (cmd.equalsIgnoreCase("Wikia")) {
					openURL("https://godzhell.fandom.com/wiki/Godzhell_Wiki");
				} else if (cmd.equalsIgnoreCase("Store")) {
					openURL("https://godzhell.everythingrs.com/services/store");
				} else if (cmd.equalsIgnoreCase("Hiscores")) {
					openURL("https://godzhell.everythingrs.com/services/Hiscores");
				} else if (cmd.equalsIgnoreCase("Vote")) {
					openURL("https://godzhell.everythingrs.com/services/vote");
				} else if (cmd.equalsIgnoreCase("Screenshot")) {
					screeny();
				}
		        if(cmd.equalsIgnoreCase("Previous"))
		        {
		            midiPlayer("Previous");
		        }
		        if(cmd.equalsIgnoreCase("Next"))
		        {
		            midiPlayer("Next");
		        }
	            if(cmd.equalsIgnoreCase("Play"))
	            {
	                midiPlayer("Play");
	            }
	            if(cmd.equalsIgnoreCase("Stop"))
	            {
	                midiPlayer("Stop");
	            }
				if (cmd.equalsIgnoreCase("About"))
				{
								showMessageDialog("Welcome to Project GodzHell. Where we bring back the feel of old 2009-2010 GodzHell", "Project GodzHell", JOptionPane.INFORMATION_MESSAGE);
				}
	            if(cmd.equals("Item list"))
	            {
	                if(isApplet)
	                {
	                    checkIDs();
	                    new Xml$((new StringBuilder()).append(signlink.findcachedir()).append("all_IDs/Items.xml").toString());
	                } else
	                {
	                    new Xml$(signlink.findcachedir()+"all_IDs/Items.xml");
	                }
	            }
	            if(cmd.equals("NPC list"))
	            {
	                if(isApplet)
	                {
	                    checkIDs();
	                    new Xml$((new StringBuilder()).append(signlink.findcachedir()).append("all_IDs/NPCs.xml").toString());
	                } else
	                {
	                    new Xml$(signlink.findcachedir()+"all_IDs/NPCs.xml");
	                }
	            }
	            if(cmd.equals("Custom Item list"))
							            {
							                if(isApplet)
							                {
							                    checkIDs();
							                    new Xml$((new StringBuilder()).append(signlink.findcachedir()).append("all_IDs/NewItems.xml").toString());
							                } else
							                {
							                    new Xml$(signlink.findcachedir()+"all_IDs/NewItems.xml");
							                }
	            }
	            if(cmd.equals("Object list"))
	            {
	                if(isApplet)
	                {
	                    checkIDs();
	                    new Xml$((new StringBuilder()).append(signlink.findcachedir()).append("all_IDs/Objects.xml").toString());
	                } else
	                {
	                    new Xml$(signlink.findcachedir()+"all_IDs/Objects.xml");
	                }
	            }
			}
		} catch (Exception e) {
		}
	}
	private int screenshot;
	
	private boolean takeScreenshot = true;
	
	public void screeny() {
		try {
			Window window = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow();
			Point point = window.getLocationOnScreen();
			int x = (int)point.getX();
			int y = (int)point.getY();
			int w = window.getWidth();
			int h = window.getHeight();
			Robot robot = new Robot(window.getGraphicsConfiguration().getDevice());
			Rectangle captureSize = new Rectangle(x, y, w, h);
			java.awt.image.BufferedImage bufferedimage = robot.createScreenCapture(captureSize);
			String fileExtension = "GodzHell";
			for (int i = 1; i <= 1000; i++) {
				File file = new File(System.getProperty("user.home") + "/Documents/GodzHell/"+ fileExtension +" "+ i +".png");
				if (!file.exists()) {
					screenshot = i;
					takeScreenshot = true;
					break;
				}
			}
			File file = new File((new StringBuilder()).append(System.getProperty("user.home") + "/Documents/GodzHell/" + fileExtension + " ").append(screenshot).append(".png").toString());
			if (takeScreenshot == true) {
				method77("" + fileExtension + " "+ screenshot +" was saved in your screenshot folder!", 0, "", aBoolean991);
				ImageIO.write(bufferedimage, "png", file);
			} else {
				method77("Your screenshots folder is full!", 0, "", aBoolean991);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
    private void midiPlayer(String command)
	    {
	        String dir = signlink.findcachedir()+"/Music/";
chkDir(dir);
if(player != null && player.isRunning())
	        {
	            player.close();
	        }
	        if(sequencer != null && sequencer.isRunning())
	        {
	            sequencer.stop();
	            sequencer.close();
	        }
	        if(command.equals("Next"))
	        {
	            midiCount++;
	        } else
	        if(command.equals("Previous"))
	        {
	            midiCount--;
	        }


	        if(!command.equals("Stop"))
	        {
	            File index = new File(dir);
	            String fileList[] = index.list();
	            if(fileList.length > 0)
	            {
	                if(midiCount > fileList.length - 1)
	                {
	                    midiCount = 0;
	                }
	                if(midiCount < 0)
	                {
	                    midiCount = fileList.length - 1;
	                }
	                if(fileList[midiCount].endsWith(".mp3"))
	                {
	                    log((new StringBuilder()).append("Playing ").append(fileList[midiCount]).toString());
	                    player = new MP3Player((new StringBuilder()).append(dir).append(fileList[midiCount]).toString(), true);
	                } else
	                if(fileList[midiCount].endsWith(".mid"))
	                {
	                    log((new StringBuilder()).append("Playing ").append(fileList[midiCount]).toString());
	                    try
	                    {
	                        File midiFile = new File((new StringBuilder()).append(dir).append(fileList[midiCount]).toString());
	                        sequencer = MidiSystem.getSequencer();
	                        sequencer.setSequence(MidiSystem.getSequence(midiFile));
	                        sequencer.setLoopCount(-1);
	                        sequencer.open();
	                        sequencer.start();
	                    }
	                    catch(MidiUnavailableException mue)
	                    {
	                        log("Midi device unavailable!");
	                    }
	                    catch(InvalidMidiDataException imde)
	                    {
	                        log("Invalid Midi data!");
	                    }
	                    catch(IOException ioe)
	                    {
	                        log("I/O Error!");
	                    }
	                }
	            } else
	            {
	                log("no midi or mp3 files in folder");
	            }
	        }

  }
		  public static synchronized void log(Object object)
		    {
		        System.out.println(object.toString());
		    }
		    public static void chkDir(String fileName)
		    {
		        try
		        {
		            File f = new File(fileName);
		            if(!f.exists())
		                f.mkdir();
		        }
		        catch(Exception e)
		        {
		            log((new StringBuilder()).append("Error making file: ").append(e).toString());
		        }
	    }
		    private void checkIDs()
		    {
		        File exist = new File((new StringBuilder()).append(signlink.findcachedir()).append("all_IDs/").toString());
		        if(!exist.exists())
		        {
		            //new Update("http://www.moparscape.org/cache/all_IDs.zip", "all_IDs.zip", signlink.findcachedir(), true, false);
		        }
		    }
		    public void showMessageDialog(String message, String title, int icon)
			{
				showMessageDialog(message, title, icon, frame);
			}
			public void showMessageDialog(String message, String title, int icon, Container target)
			{
				JDialog dialog;
				JOptionPane jop = new JOptionPane(null, icon);
				jop.setMessage(message);
				dialog = jop.createDialog(target, title);		
				dialog.pack();
				dialog.setVisible(true);
				dialog.dispose();
			}
}