1-Types de Beans :

@Stateless       // Bean sans état, le plus utilisé
@Stateful        // Bean avec état, conserve l'état entre les appels
@Singleton       // Une seule instance partagée
@MessageDriven   // Pour le traitement asynchrone des messages JMS

2-Injection de dépendances :

@EJB            // Injection d'un EJB
@Resource       // Injection de ressources (DataSource, etc.)
@PersistenceContext   // Injection d'un EntityManager
@Inject         // Injection CDI (Contexts and Dependency Injection)

3-Cycle de vie :

@PostConstruct   // Méthode appelée après l'injection des dépendances
@PreDestroy      // Méthode appelée avant la destruction du bean

4-Transactions :

@TransactionManagement   // Définit le type de gestion des transactions
@TransactionAttribute    // Définit le comportement transactionnel

Exemple :
javaCopy@TransactionAttribute(TransactionAttributeType.REQUIRED)
public void maMethode() { }

5-Sécurité :

@RolesAllowed({"admin", "user"})   // Autorise certains rôles
@PermitAll                         // Autorise tout le monde
@DenyAll                          // Interdit l'accès à tous
@RunAs                            // Exécute en tant qu'un rôle spécifique

6-Intercepteurs :

@Interceptors    // Ajoute des intercepteurs
@AroundInvoke    // Méthode d'interception

7-Interface Remote/Local :

@Remote          // Interface accessible à distance
@Local           // Interface accessible uniquement en local

8-Timer Service :

@Schedule        // Pour les tâches programmées
@Timeout         // Méthode appelée quand le timer expire

9-log
private static final Logger logger = Logger.getLogger(Class.class.getName());