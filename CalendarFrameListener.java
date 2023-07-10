import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalendarFrameListener implements ActionListener {
    private final CalendarFrame calendarFrame; // Référence vers l'instance de CalendarFrame

    public CalendarFrameListener(CalendarFrame calendarFrame) {
        this.calendarFrame = calendarFrame;
    }
    
    // Méthode appelée lorsque l'action est déclenchée
    public void actionPerformed(ActionEvent e) {
        // Vérifie quelle composante a déclenché l'action
        if (e.getSource() == calendarFrame.getPrevButton()) {
            // Si c'est le bouton précédent, décrémente la date du calendrier
            calendarFrame.getCalendar().previousDay();
        } else if (e.getSource() == calendarFrame.getNextButton()) {
            // Si c'est le bouton suivant, incrémente la date du calendrier
            calendarFrame.getCalendar().nextDay();
        } 
        
        // Met à jour l'affichage de la date dans la fenêtre du calendrier
        calendarFrame.updateDate();
    }
}
