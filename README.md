Recipe Management App
Overview
The Recipe Management App allows users to manage recipes in a graphical user interface (GUI). Users can add recipes, delete them, upload ingredient images, switch between English and Georgian languages, and save/load recipes from a text file.

Features
Add Recipe: Add a new recipe by specifying the title and ingredients.
Delete Recipe: Delete a recipe by providing its title.
Upload Ingredient Image: Upload an image related to an ingredient.
Save Recipes: Save all recipes to a .txt file.
Open Recipes: Open saved recipes from a .txt file and display them in the app.
Language Toggle: Switch the app's language between English and Georgian.
Requirements
Java 8+: The app is developed in Java, so you need Java 8 or higher to run it.
Custom Font: The app uses the "Noto Sans Georgian" font, so ensure that the font file (NotoSansGeorgian-VariableFont_wdth,wght.ttf) is available in the project directory.
Image Files: Ingredient images can be uploaded in .jpg, .png, or other supported image formats.
Setup and Installation
Clone or Download: Download the source code or clone the repository to your local machine.

bash
Copy code
git clone https://github.com/user18121/Recipe-app.git
Install Java: Ensure that Java 8 or higher is installed on your machine. You can download the latest version of Java from here.

Build the Project: Use an IDE like IntelliJ IDEA or Eclipse to open the project. You can also build it via the command line with:

bash
Copy code
javac RecipeManagementApp.java
Run the App: After building the project, run the RecipeManagementApp class.

bash
Copy code
java RecipeManagementApp
Usage
Add a Recipe:

Enter the title and ingredients of the recipe in the respective fields.
Click the "Add Recipe" button to add the recipe to the list.
Delete a Recipe:

Enter the title of the recipe you want to delete.
Click the "Delete Recipe" button to remove it.
Upload Ingredient Image:

Click the "Upload Ingredient Image" button to select an image from your file system.
Save Recipes:

Click the "Save Recipes" button to store all recipes in a .txt file (recipes.txt).
Open Recipes:

Click the "Open Recipes" button to load saved recipes from the .txt file and display them in the app.
Switch Language:

Click the "Change Language" button to toggle between English and Georgian.
File Structure
RecipeManagementApp.java: Main application class with GUI components and recipe management logic.
recipes.txt: Text file where recipes are saved (located in the project directory).
NotoSansGeorgian-VariableFont_wdth,wght.ttf: Custom font for Georgian language support.
License
This project is licensed under the MIT License - see the LICENSE file for details.
