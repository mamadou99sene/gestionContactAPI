public class DatabaseConnection {
private static final String URL = "jdbc:mysql://localhost:3306/contactsdb";
private static final String USER = "root";
private static final String PASSWORD = "password";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver MySQL non trouvé", e);
        }
    }
}


public class ContactDAO {
// Requêtes SQL
private static final String INSERT_CONTACT = "INSERT INTO contacts (nom, prenom, email) VALUES (?, ?, ?)";
private static final String SELECT_ALL_CONTACTS = "SELECT * FROM contacts";
private static final String UPDATE_CONTACT = "UPDATE contacts SET nom = ?, prenom = ?, email = ? WHERE id = ?";
private static final String SELECT_CONTACT_BY_ID = "SELECT * FROM contacts WHERE id = ?";

    // Ajouter un contact
    public void ajouterContact(Contact contact) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT_CONTACT, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, contact.getNom());
            pstmt.setString(2, contact.getPrenom());
            pstmt.setString(3, contact.getEmail());
            
            pstmt.executeUpdate();
            
            // Récupérer l'ID généré
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    contact.setId(rs.getLong(1));
                }
            }
        }
    }
    
    // Lister tous les contacts
    public List<Contact> listerContacts() throws SQLException {
        List<Contact> contacts = new ArrayList<>();
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_CONTACTS)) {
            
            while (rs.next()) {
                Contact contact = new Contact();
                contact.setId(rs.getLong("id"));
                contact.setNom(rs.getString("nom"));
                contact.setPrenom(rs.getString("prenom"));
                contact.setEmail(rs.getString("email"));
                contacts.add(contact);
            }
        }
        
        return contacts;
    }
    
    // Modifier un contact
    public void modifierContact(Contact contact) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(UPDATE_CONTACT)) {
            
            pstmt.setString(1, contact.getNom());
            pstmt.setString(2, contact.getPrenom());
            pstmt.setString(3, contact.getEmail());
            pstmt.setLong(4, contact.getId());
            
            pstmt.executeUpdate();
        }
    }
    
    // Rechercher un contact par ID
    public Contact trouverContactParId(Long id) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_CONTACT_BY_ID)) {
            
            pstmt.setLong(1, id);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Contact contact = new Contact();
                    contact.setId(rs.getLong("id"));
                    contact.setNom(rs.getString("nom"));
                    contact.setPrenom(rs.getString("prenom"));
                    contact.setEmail(rs.getString("email"));
                    return contact;
                }
            }
        }
        return null;
    }
}