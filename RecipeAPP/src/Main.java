import com.Recipes.RecipeManagementApp;



import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RecipeManagementApp();
            }
        });
    }
}
