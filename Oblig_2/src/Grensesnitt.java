import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Grensesnitt extends JFrame implements ActionListener {
	private Kontroll kontroll = Kontroll.getInstance();
	DefaultTableModel vareinnhold;
	DefaultTableModel visvareinnhold;
	DefaultTableModel visordreinnhold;
	JTable varetabell;
	JTable visvaretabell;
	JTable visordretabell;
	
	JScrollPane varerullefelt,visvarerullefelt,visordrerullefelt;
	JPanel contentPane;
	JTextField textField,textField_1,textField_2,textField_3,textField_4,textField_5;
	//JButton nyhest,nyttløp,vise,kobleopp,koblened,avslutt,lagreløp,spørreløp;
	//JTextField txtRegnr,txtNavn,txtEier,txtFullblods,txtLøpRegnr,txtDato,txtDistanse,txtPlassering;
	private final String[] varekolonnenavn = {"Vnr:","Betegnelse:","Pris:","KatNr:","Antall:","Hylle:","KatNavn:"};
	private final String[] visvarekolonnenavn = {"Vnr:","Betegnelse:","Pris:","KatNr:","Antall:","Hylle:","KatNavn:"};
	private final String[] visordrekolonnenavn = {"OrdreNr:","OrdreDato:","KNr","AnsNr","SendteDato","BetalteDato"};
	private final Object[][] defaulttabell = new Object[][] {{null,null,null,null,null,null},{null,null,null,null,null,null,null}};
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Grensesnitt frame = new Grensesnitt();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Grensesnitt() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 815);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 449, 776);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Registrer vare", null, panel, null);
		panel.setLayout(null);
		
		JPanel varetabellpanel = new JPanel();
		varetabellpanel.setBounds(0, 278, 449, 498);
		vareinnhold = new DefaultTableModel(defaulttabell, varekolonnenavn);
		varetabell = new JTable(vareinnhold);
		varerullefelt = new JScrollPane(varetabell);
		varetabellpanel.add(varerullefelt);
		panel.add(varetabellpanel);
		
		JLabel lblVarenr = new JLabel("VareNr");
		lblVarenr.setBounds(58, 26, 46, 14);
		panel.add(lblVarenr);
		
		textField_6 = new JTextField();
		textField_6.setBounds(138, 18, 137, 20);
		panel.add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblBetignelse = new JLabel("Betegnelse");
		lblBetignelse.setBounds(58, 51, 70, 14);
		panel.add(lblBetignelse);
		
		textField = new JTextField();
		textField.setBounds(138, 49, 137, 17);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblPris = new JLabel("Pris");
		lblPris.setBounds(58, 80, 46, 14);
		panel.add(lblPris);
		
		textField_1 = new JTextField();
		textField_1.setBounds(138, 77, 137, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblKatnr = new JLabel("KatNr");
		lblKatnr.setBounds(58, 111, 46, 14);
		panel.add(lblKatnr);
		
		textField_2 = new JTextField();
		textField_2.setBounds(138, 108, 137, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblAntall = new JLabel("Antall");
		lblAntall.setBounds(58, 142, 46, 14);
		panel.add(lblAntall);
		
		textField_3 = new JTextField();
		textField_3.setBounds(138, 139, 137, 20);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblHylle = new JLabel("Hylle");
		lblHylle.setBounds(58, 172, 46, 14);
		panel.add(lblHylle);
		
		textField_4 = new JTextField();
		textField_4.setBounds(138, 170, 137, 20);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnOk = new JButton("Lagre");
		btnOk.setBounds(10, 214, 76, 23);
		btnOk.addActionListener(this);	
		panel.add(btnOk);
		
		JButton btnKobleTil = new JButton("Koble til");
		btnKobleTil.setBounds(96, 214, 79, 23);
		btnKobleTil.addActionListener(this);
		panel.add(btnKobleTil);
		
		JButton btnKobleFra = new JButton("Koble fra");
		btnKobleFra.setBounds(185, 214, 90, 23);
		btnKobleFra.addActionListener(this);
		panel.add(btnKobleFra);
		
		JButton btnAvslutt = new JButton("Avslutt");
		btnAvslutt.setBounds(284, 214, 76, 23);
		btnAvslutt.addActionListener(this);
		panel.add(btnAvslutt);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Vis varer", null, panel_1, null);
		panel_1.setLayout(null);
		
		JPanel  visvaretabellpanel = new JPanel();
		visvaretabellpanel.setBounds(0, 61, 449, 687);
		panel_1.add(visvaretabellpanel);
		visvareinnhold = new DefaultTableModel(defaulttabell, visvarekolonnenavn);
		visvaretabell = new JTable(visvareinnhold);
		visvarerullefelt = new JScrollPane(visvaretabell);
		visvaretabellpanel.add(visvarerullefelt);
		
		JButton btnVisVarer = new JButton("Vis varer");
		btnVisVarer.setBounds(140, 11, 89, 23);
		btnVisVarer.addActionListener(this);
		panel_1.add(btnVisVarer);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Vis ordre", null, panel_2, null);
		panel_2.setLayout(null);
		
		JPanel  visordretabellpanel = new JPanel();
		visordretabellpanel.setBounds(0, 133, 449, 615);
		panel_2.add(visordretabellpanel);
		visordreinnhold = new DefaultTableModel(defaulttabell, visordrekolonnenavn);
		visordretabell = new JTable(visordreinnhold);
		visordretabell.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = visordretabell.getSelectedRow();
				TableModel model = visordretabell.getModel();
				textField_7.setText(model.getValueAt(i, 2).toString());
				textField_8.setText(model.getValueAt(i, 0).toString());
				textField_10.setText(model.getValueAt(i, 1).toString());
				}
		});
		visordrerullefelt = new JScrollPane(visordretabell);
		visordretabellpanel.add(visordrerullefelt);
		
		JLabel lblKundenr = new JLabel("KundeNr");
		lblKundenr.setBounds(56, 24, 58, 14);
		panel_2.add(lblKundenr);
		
		textField_7 = new JTextField();
		textField_7.setBounds(142, 21, 143, 20);
		panel_2.add(textField_7);
		textField_7.setColumns(10);
		
		JLabel lblOrdreNr = new JLabel("OrdreNr");
		lblOrdreNr.setBounds(56, 55, 68, 14);
		panel_2.add(lblOrdreNr);
		
		textField_8 = new JTextField();
		textField_8.setBounds(142, 52, 143, 20);
		panel_2.add(textField_8);
		textField_8.setColumns(10);
		
		JLabel lblOrdredato = new JLabel("OrdreDato");
		lblOrdredato.setBounds(56, 86, 68, 14);
		panel_2.add(lblOrdredato);
		
		textField_10 = new JTextField();
		textField_10.setBounds(142, 83, 143, 20);
		panel_2.add(textField_10);
		textField_10.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Slett kunde", null, panel_3, null);
		panel_3.setLayout(null);
		
		JLabel lblKundeNr = new JLabel("Kunde Nr");
		lblKundeNr.setBounds(78, 49, 56, 14);
		panel_3.add(lblKundeNr);
		
		textField_5 = new JTextField();
		textField_5.setBounds(168, 46, 152, 20);
		panel_3.add(textField_5);
		textField_5.setColumns(10);
		
		JButton btnSlettKunde = new JButton("Slett Kunde");
		btnSlettKunde.setBounds(146, 195, 116, 23);
		btnSlettKunde.addActionListener(this);
		panel_3.add(btnSlettKunde);
		
		JButton btnOk_1 = new JButton("OK");
		btnOk_1.setBounds(356, 45, 64, 23);
		btnOk_1.addActionListener(this);
		panel_3.add(btnOk_1);
		
		JLabel lblEtternavn_1 = new JLabel("Etternavn");
		lblEtternavn_1.setBounds(78, 97, 56, 14);
		panel_3.add(lblEtternavn_1);
		
		textField_11 = new JTextField();
		textField_11.setBounds(168, 94, 152, 20);
		panel_3.add(textField_11);
		textField_11.setColumns(10);
		
		JLabel lblPostAddresse = new JLabel("Post Addresse");
		lblPostAddresse.setBounds(78, 143, 87, 14);
		panel_3.add(lblPostAddresse);
		
		textField_12 = new JTextField();
		textField_12.setBounds(168, 140, 152, 20);
		panel_3.add(textField_12);
		textField_12.setColumns(10);	
	}

	@Override
	//public void actionPerformed(ActionEvent arg0) {
	  public void actionPerformed(ActionEvent e) {
			String valg = e.getActionCommand();
			if(valg.equals("Lagre")) {
				try{
				   lagreVare();
				}catch(Exception ex) {JOptionPane.showMessageDialog(null, ex.getMessage());}
			}
			else if(valg.equals("Koble til")) {
				try {
					kontroll.lagForbindelse();
					fyllTabell();
					fyllOrdretabell();
					
				}catch(Exception ex) {JOptionPane.showMessageDialog(null, ex.getMessage());}
			}
			else if(valg.equals("Koble fra")) {
				try {
					kontroll.lukk();
				}catch(Exception ex) {JOptionPane.showMessageDialog(null, ex.getMessage());}
			}
			else if(valg.equals("Vis varer")) {
				try{
					fyllVisVareTabell();
				}catch(Exception ex) {JOptionPane.showMessageDialog(null, ex.getMessage());}
			}
			else if(valg.equals("Vis Ordre")) {
				try{
					fyllOrdretabell();
				}catch(Exception ex) {JOptionPane.showMessageDialog(null, ex.getMessage());}
			}
			else if(valg.equals("OK")) {
				try{
					finnKunde();
				}catch(Exception ex) {JOptionPane.showMessageDialog(null, ex.getMessage());}
			}
			else if(valg.equals("Slett Kunde")) {
				try{
					SlettKunde();
				}catch(Exception ex) {JOptionPane.showMessageDialog(null, ex.getMessage());}
			}
			else if(valg.equals("Avslutt")) System.exit(0);
		} 
		
		public void lagreVare() throws Exception {
			try{
				int varenr = Integer.parseInt(textField_6.getText());
				String betegnelse = textField.getText();
				String pris = textField_1.getText();
				String katnr = textField_2.getText();
				int antall = Integer.parseInt(textField_3.getText());
				String hylle = textField_4.getText();
				kontroll.lagreVarer(varenr,betegnelse,pris,katnr,antall,hylle);			
				fyllTabell();
			}catch(Exception ex) {JOptionPane.showMessageDialog(null, ex.getMessage());}
		}
		
		public void fyllTabell() {
			Object[][] liste = lagListe();
			varetabell.setModel(new DefaultTableModel(liste,varekolonnenavn));
		}
		public void fyllVisVareTabell() {
			Object[][] liste = lagListe();
			visvaretabell.setModel(new DefaultTableModel(liste,visvarekolonnenavn));
		}
		public void fyllOrdretabell() {
			Object[][] liste = lagOrdreListe();
			visordretabell.setModel(new DefaultTableModel(liste,visordrekolonnenavn));
		}
		public Object[][] lagListe() {
			Object[][] innhold = new Object[200][7];
			int teller = 0;
			try {
				ResultSet liste = kontroll.leseVarer();
	            while(liste.next()) {
	               String varenr = liste.getString(1);
	               String betegnelse = liste.getString(2);
	               String pris = liste.getString(3);
	               String katnr = liste.getString(4);
	               String antall = liste.getString(5);
	               String hylle = liste.getString(6);
	               String katnavn = liste.getString(7);
	               innhold[teller][0] = varenr;
	               innhold[teller][1] = betegnelse;
	               innhold[teller][2] = pris;
	               innhold[teller][3] = katnr;
	               innhold[teller][4] = antall;
	               innhold[teller][5] = hylle;
	               innhold[teller][6] = katnavn;
	               teller++;
				} //løkke
			}catch(Exception e){JOptionPane.showMessageDialog(null, "Klarte ikke opprette tabell");}
			return innhold;
	    } //metode
		
		public Object[][] lagOrdreListe() {
			Object[][] innhold = new Object[3000][6];
			int teller = 0;
			try {
				ResultSet liste = kontroll.leseOrdre();
	            while(liste.next()) {
	               String ordrenr = liste.getString(1);
	               String ordredato = liste.getString(2);
	               String Knr = liste.getString(3);
	               String ansnr = liste.getString(4);
	               String sendtedato = liste.getString(5);
	               String betaltdato = liste.getString(6);
	               innhold[teller][0] = ordrenr;
	               innhold[teller][1] = ordredato;
	               innhold[teller][2] = Knr;
	               innhold[teller][3] = ansnr;
	               innhold[teller][4] = sendtedato;
	               innhold[teller][5] = betaltdato;
	               teller++;
				} //løkke
			}catch(Exception e){JOptionPane.showMessageDialog(null, "Klarte ikke opprette tabell");}
			return innhold;
		} //metode
		
		public void finnKunde() throws Exception {
			try {
				int kundenr = Integer.parseInt(textField_5.getText());
				ResultSet query = kontroll.finnKunde(kundenr);
				 if(query.next()){
					 textField_11.setText(query.getString("Etternavn"));
					 textField_12.setText(query.getString("Adresse")); 
					 }
				 else if(!query.next()) {
					 JOptionPane.showMessageDialog(null,"kunde finnes ikke");
				 }	 
			}catch(Exception ex) {JOptionPane.showMessageDialog(null, ex.getMessage());}
		}
		
		public void SlettKunde() throws Exception {
			try{
				int kundenr = Integer.parseInt(textField_5.getText());
				kontroll.slettKunde(kundenr);			
			}catch(Exception ex) {JOptionPane.showMessageDialog(null, ex.getMessage());}
		}
}
