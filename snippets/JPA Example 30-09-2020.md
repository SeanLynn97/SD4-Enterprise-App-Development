
## To Do: :point_down:

1. Create DB connection (you may be required to add a [JAR file](https://moodle.lit.ie/mod/resource/view.php?id=136565) for MySQL to Netbeans to do this).

2. Add Persistence Unit to project.

3. Add [Dependency](#mysql-dependency-for-pom) for MySQL to POM.

4. Add [DBUtil](#dbutiljava) class to project.

5. Add Servlet to project.

6. Generate (*Authors*) entity class from DB. Remove *toString()* and get NetBeans to generate one instead.

7. Add *AuthorsDB* class to project.

8. Add a method to [retrieve an *Authors* object by id](#getauthorbyid) to *AuthorsDB* class

9. Add a call to the Servlet to test this method and print the object it returns to the browser.

10. Add a method to [retrieve a list of *Authors* objects](#getallauthors) to *AuthorsDB* class

11. Add a call to the Servlet to test this method and print the list it returns to the browser.

12. Add a method to [update an *Authors* object](#updateauthor)  to *AuthorsDB* class

13. Update an *Authors* object in the Servlet. Pass object to updateAuthor method in *AuthorsDB* class.

14. Add a method to [inserting an *Authors* object](#insertauthor)  to *AuthorsDB* class

15. Create an *Authors* object in the Servlet. Pass object to insertAuthor method in *AuthorsDB* class.

## Snippets

### mysql dependency for POM
```xml
 <dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.21</version>
 </dependency>
```
### DBUtil.java 
```java

public class DBUtil {
    
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Books_PU");

    public static EntityManagerFactory getEmf() {
        return emf;
    }
    
}
```

### getAuthorById
```java
public static Authors getAuthorById(int id) {

        //get em
        EntityManager em = DBUtil.getEmf().createEntityManager();

        Authors a = em.find(Authors.class, id);

        em.close();

        return a;

    }//end getAuthorById
```

### getAllAuthors
```java
public static List<Authors> getAllAuthors() {
        EntityManager em = DBUtil.getEmf().createEntityManager();
        
        String q = "SELECT a from Authors a";
        
        TypedQuery<Authors> tq = em.createQuery(q, Authors.class);
        
        List<Authors> list;
        
        try {
            list = tq.getResultList();
            
            if (list == null || list.isEmpty())
                list = null;
            
        }
        finally {
            em.close();
        }

        return list;
        
    }//end getAllAuthors
 ``` 
 
 ### updateAuthor
```java
public static void updateAuthor(Authors a) {

        EntityManager em = DBUtil.getEmf().createEntityManager();

        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.merge(a);
            trans.commit();
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            em.close();
        }

    }//end updateAuthor
   ```
     
 ### insertAuthor
   ```java
    public static void insertAuthor(Authors a ) {
        
        EntityManager em = DBUtil.getEmf().createEntityManager();
        
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.persist(a);
            trans.commit();
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
        finally {
            em.close();
        }  
        
    }//end insertAuthor()
   
   ```
 
