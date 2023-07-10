import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalendarFrame extends JFrame {
    private Calendar calendar; // Calendrier utilisé pour afficher la date
    private JLabel dayName; // Étiquette pour afficher le nom du jour
    private JLabel dayNumber; // Étiquette pour afficher le numéro du jour
    private JComboBox<String> monthComboBox; // Liste déroulante pour sélectionner le mois
    private JLabel yearNumber; // Étiquette pour afficher l'année
    private JButton prevButton; // Bouton pour naviguer vers le jour précédent
    private JButton nextButton; // Bouton pour naviguer vers le jour suivant

    public CalendarFrame() {
        initComponents(); // Initialise les composants graphiques de la fenêtre

        // Configuration de la fenêtre
        this.setTitle("Calendrier");
        this.setLocationRelativeTo(null);
        this.setPreferredSize(new Dimension(500, 100));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Configuration de la disposition des composants
        this.setLayout(new GridLayout(1, 7));

        // Ajout des composants à la fenêtre
        this.add(this.prevButton);
        this.add(this.dayName);
        this.add(this.dayNumber);
        this.add(this.monthComboBox);
        this.add(this.yearNumber);
        this.add(this.nextButton);

        // Configuration de l'alignement des étiquettes
        this.dayName.setHorizontalAlignment(JLabel.RIGHT);
        this.dayNumber.setHorizontalAlignment(JLabel.CENTER);
        this.yearNumber.setHorizontalAlignment(JLabel.CENTER);

        // Configuration des actions lors de la sélection d'un mois dans la liste déroulante
        this.monthComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateCalendar();
            }
        });

        // Configuration des actions lors du clic sur les boutons précédent et suivant
        CalendarFrameListener listener = new CalendarFrameListener(this);
        prevButton.addActionListener(listener);
        nextButton.addActionListener(listener);

        this.setFocusable(true); 

        // Ajuste la taille de la fenêtre en fonction des composants
        this.pack();
        this.setVisible(true);
    }

    private void initComponents() {
        // Initialisation des composants graphiques
        this.calendar = new Calendar();
        this.dayName = new JLabel(this.calendar.getDayName());
        this.dayNumber = new JLabel(String.valueOf(this.calendar.getDayNumber()));
        this.monthComboBox = new JComboBox<>(this.calendar.getMonthsNames());
        this.yearNumber = new JLabel(String.valueOf(this.calendar.getYear()));
        this.prevButton = new JButton("<");
        this.nextButton = new JButton(">");

        // Configuration des actions lors de l'appui sur les touches de direction
        this.prevButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "prev");
        this.prevButton.getActionMap().put("prev", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                calendar.previousDay();
                updateDate();
            }
        });

        this.nextButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "next");
        this.nextButton.getActionMap().put("next", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                calendar.nextDay();
                updateDate();
            }
        });
    }

    // Méthode pour mettre à jour l'affichage de la date
    public void updateDate() {
        dayName.setText(calendar.getDayName());
        dayNumber.setText(String.valueOf(calendar.getDayNumber()));
        monthComboBox.setSelectedIndex(calendar.getMonthNumber() - 1);
        yearNumber.setText(String.valueOf(calendar.getYear()));
    }

    // Méthode pour mettre à jour le calendrier lorsque le mois est modifié dans la liste déroulante
    public void updateCalendar() {
        int monthIndex = monthComboBox.getSelectedIndex();
        calendar.setMonth(monthIndex + 1);
        updateDate();
    }

    // Getters pour les composants du calendrier
    public Calendar getCalendar() {
        return calendar;
    }

    public JButton getPrevButton() {
        return prevButton;
    }

    public JButton getNextButton() {
        return nextButton;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CalendarFrame().setVisible(true);
            }
        });
    }
}