package com.Recipes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.ArrayList;

public class RecipeManagementApp {
    private final JFrame frame;
    private final JTextArea recipeArea;
    private final JTextField titleField;
    private final JTextField ingredientsField;
    private final ArrayList<Recipe> recipeList;
    private final JButton addButton;
    private final JButton deleteButton;
    private final JButton uploadImageButton;
    private final JButton languageButton;
    private final JButton saveButton;
    private final JButton openButton;
    private BufferedImage ingredientImage;
    private boolean isEnglish = true;
    private Font customFont;

    public RecipeManagementApp() {
        recipeList = new ArrayList<>();
        frame = new JFrame("Recipe Management App");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setBackground(new Color(255, 255, 255));

        // Load the custom font
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("NotoSansGeorgian-VariableFont_wdth,wght.ttf")).deriveFont(14f);
        } catch (FontFormatException | IOException e) {
            customFont = new Font("Arial", Font.PLAIN, 14); // Fallback to Arial if font loading fails
        }

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.setBackground(new Color(238, 238, 238));

        JLabel titleLabel = new JLabel("Recipe Title:");
        titleLabel.setFont(customFont);
        titleLabel.setForeground(new Color(0, 0, 0));

        titleField = new JTextField();
        titleField.setFont(customFont);
        titleField.setBackground(new Color(255, 255, 255));
        titleField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));

        JLabel ingredientsLabel = new JLabel("Ingredients:");
        ingredientsLabel.setFont(customFont);
        ingredientsLabel.setForeground(new Color(0, 0, 0));

        ingredientsField = new JTextField();
        ingredientsField.setFont(customFont);
        ingredientsField.setBackground(new Color(255, 255, 255));
        ingredientsField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));

        addButton = new JButton("Add Recipe");
        addButton.setFont(customFont);
        styleButton(addButton);

        deleteButton = new JButton("Delete Recipe");
        deleteButton.setFont(customFont);
        styleButton(deleteButton);

        uploadImageButton = new JButton("Upload Ingredient Image");
        uploadImageButton.setFont(customFont);
        styleButton(uploadImageButton);

        languageButton = new JButton("Change Language");
        languageButton.setFont(customFont);
        styleButton(languageButton);

        saveButton = new JButton("Save Recipes");
        saveButton.setFont(customFont);
        styleButton(saveButton);

        openButton = new JButton("Open Recipes");
        openButton.setFont(customFont);
        styleButton(openButton);

        inputPanel.add(titleLabel);
        inputPanel.add(titleField);
        inputPanel.add(ingredientsLabel);
        inputPanel.add(ingredientsField);
        inputPanel.add(uploadImageButton);
        inputPanel.add(addButton);
        inputPanel.add(deleteButton);
        inputPanel.add(languageButton);
        inputPanel.add(saveButton);
        inputPanel.add(openButton);

        recipeArea = new JTextArea();
        recipeArea.setEditable(false);
        recipeArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        recipeArea.setBackground(new Color(240, 240, 240));
        recipeArea.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(recipeArea), BorderLayout.CENTER);

        uploadImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Choose an Image for Ingredient");
                int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try {
                        ingredientImage = ImageIO.read(file);
                        JOptionPane.showMessageDialog(frame, "Image uploaded successfully!");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, "Failed to upload image.");
                    }
                }
            }
        });

        titleField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    addRecipe();
                }
            }
        });
        ingredientsField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    addRecipe();
                }
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRecipe();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                if (!title.isEmpty()) {
                    recipeList.removeIf(recipe -> recipe.getTitle().equalsIgnoreCase(title));
                    displayRecipes();
                    titleField.setText("");
                    ingredientsField.setText("");
                } else {
                    JOptionPane.showMessageDialog(frame, "Please enter a title to delete.");
                }
            }
        });

        languageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isEnglish) {
                    setGeorgianLanguage();
                } else {
                    setEnglishLanguage();
                }
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveRecipesToFile();
            }
        });

        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openRecipesFromFile();
            }
        });

        frame.setVisible(true);
    }

    private void addRecipe() {
        String title = titleField.getText();
        String ingredients = ingredientsField.getText();
        if (!title.isEmpty() && !ingredients.isEmpty()) {
            Recipe newRecipe = new Recipe(title, ingredients, ingredientImage);
            recipeList.add(newRecipe);
            displayRecipes();
            titleField.setText("");
            ingredientsField.setText("");
            ingredientImage = null;
        } else {
            JOptionPane.showMessageDialog(frame, "Both fields must be filled.");
        }
    }

    private void displayRecipes() {
        recipeArea.setText("");
        for (Recipe recipe : recipeList) {
            recipeArea.append(recipe.toString() + "\n--------------------\n");
            if (recipe.getIngredientImage() != null) {
                recipeArea.append("Image available for this ingredient.\n");
            }
        }
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(33, 150, 243));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(33, 150, 243), 1));
        button.setFont(customFont);  // Set the custom font
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(30, 136, 229));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(33, 150, 243));
            }
        });
    }

    private void setGeorgianLanguage() {
        isEnglish = false;
        addButton.setText("რეცეპტის დამატება");
        deleteButton.setText("რეცეპტის წაშლა");
        uploadImageButton.setText("სურათის ატვირთვა");
        languageButton.setText("გახანგრძლივება");
        recipeArea.setText("რეცეპტები:\n--------------------\n");
    }

    private void setEnglishLanguage() {
        isEnglish = true;
        addButton.setText("Add Recipe");
        deleteButton.setText("Delete Recipe");
        uploadImageButton.setText("Upload Ingredient Image");
        languageButton.setText("Change Language");
        recipeArea.setText("Recipes:\n--------------------\n");
    }

    private void saveRecipesToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("recipes.txt"))) {
            for (Recipe recipe : recipeList) {
                writer.write(recipe.getTitle() + "\n");
                writer.write(recipe.getIngredients() + "\n");
                writer.write("--------------------\n");
            }
            JOptionPane.showMessageDialog(frame, "Recipes saved successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Failed to save recipes.");
        }
    }

    private void openRecipesFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("recipes.txt"))) {
            recipeList.clear();
            String line;
            String title = "";
            String ingredients = "";
            while ((line = reader.readLine()) != null) {
                if (line.equals("--------------------")) {
                    if (!title.isEmpty() && !ingredients.isEmpty()) {
                        recipeList.add(new Recipe(title, ingredients, null));
                    }
                    title = "";
                    ingredients = "";
                } else if (title.isEmpty()) {
                    title = line;
                } else {
                    ingredients = line;
                }
            }
            displayRecipes();
            JOptionPane.showMessageDialog(frame, "Recipes loaded successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Failed to open recipes.");
        }
    }
}
