

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URISyntaxException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

import org.json.JSONException;

/**
 * A Swing program that demonstrates how to use JRadioButton component.
 * 
 * @author www.codejava.net
 * 
 */
public class Gui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JButton buttonOK = new JButton("Start Scan");

	private JRadioButton proxy = new JRadioButton("Proxy");
	private JRadioButton noproxy = new JRadioButton("Without Proxy");
	
	
	private JRadioButton continuous = new JRadioButton("Continuous");
	private JRadioButton friendlist = new JRadioButton("Friendlist Only");
	private JRadioButton groups = new JRadioButton("Group Search");
	
	private JRadioButton coupon = new JRadioButton("Coupons");
	private JRadioButton gift = new JRadioButton("Gifts");
	private JRadioButton both = new JRadioButton("Both");
	
	int proxyc =0;
	int input = 2;
	int choice = 2;

	public Gui() {
		super("Steam Inventory Scanner");

		ButtonGroup group = new ButtonGroup();
		group.add(proxy);
		group.add(noproxy);
		
		ButtonGroup group1 = new ButtonGroup();
		group1.add(continuous);
		group1.add(friendlist);
		group1.add(groups);
		
		ButtonGroup group2 = new ButtonGroup();
		group2.add(coupon);
		group2.add(gift);
		group2.add(both);
		

		noproxy.setSelected(true);
		friendlist.setSelected(true);
		coupon.setSelected(true);
		

		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets = new Insets(10, 10, 10, 10);

		add(proxy, constraints);
		constraints.gridx = 1;
		add(noproxy, constraints);
		
		constraints.gridy = 1;
		constraints.gridx = 0;
		
		add(continuous, constraints);
		constraints.gridx = 1;
		add(friendlist, constraints);
		constraints.gridx = 2;
		add(groups, constraints);
		
		constraints.gridy = 2;
		constraints.gridx = 0;
		
		add(coupon, constraints);
		constraints.gridx = 1;
		add(gift, constraints);
		constraints.gridx = 2;
		add(both, constraints);

		
		

		constraints.gridy = 4;
		add(buttonOK, constraints);

		RadioButtonActionListener actionListener = new RadioButtonActionListener();
		proxy.addActionListener(actionListener);
		noproxy.addActionListener(actionListener);
		continuous.addActionListener(actionListener);
		friendlist.addActionListener(actionListener);
		groups.addActionListener(actionListener);
		coupon.addActionListener(actionListener);
		gift.addActionListener(actionListener);
		both.addActionListener(actionListener);
		

		buttonOK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
			 INVScanner scan = new INVScanner();
			 try {
				scan.search(input,proxyc,choice);
			} catch (InterruptedException | JSONException | URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//	 new Thread(scan).start();
			// System.exit(0);
			}
		});

		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	class RadioButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			JRadioButton button = (JRadioButton) event.getSource();
			if (button == proxy) {

				proxyc = 1;

			} else if (button == noproxy) {

				proxyc = 0;

			} 
			
			if (button == continuous) {

				input = 1;

			} else if (button == friendlist) {

				input = 2;

			} else if (button == groups) {

				input = 3;
			}
			
			if (button == gift) {

				choice = 1;

			} else if (button == coupon) {

				choice = 2;

			} else if (button == both) {

				choice = 3;
			}
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new Gui().setVisible(true);
			}
		});
	}
}