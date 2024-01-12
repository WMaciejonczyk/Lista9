import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        var queryStringA = "SELECT ROUND(100.0 * COUNT(p) / ((SELECT COUNT(p2) FROM ProductsEntity p2)), 2) FROM ProductsEntity p WHERE p.iron + p.calcium > 50";
        Query queryA = em.createQuery(queryStringA);
        Double resultA = (Double) queryA.getSingleResult();
        System.out.println(String.format("Procent produktów dla podpunktu a: %s%%", resultA));

        var queryStringB = "SELECT AVG(calories) FROM ProductsEntity p WHERE p.itemName LIKE '%Bacon%'";
        Query queryB = em.createQuery(queryStringB);
        Double resultB = (Double) queryB.getSingleResult();
        System.out.println("Średnia wartość kaloryczna produktów z bekonem w nazwie dla podpunktu b: " + resultB);

        var queryStringC = "SELECT p.category.catName, p.category.catId, MAX(p.itemName), MAX(p.cholesterole) FROM ProductsEntity p GROUP BY p.category";
        Query queryC = em.createQuery(queryStringC);
        List<Object[]> resultC = queryC.getResultList();
        System.out.println("Produkty o największej wartości cholesterolu w nazwie dla podpunktu c: ");
        for (Object[] o : resultC) {
            System.out.println(o[0] + " (" + o[1] + "): " + o[2]);
        }

        var queryStringD = "SELECT COUNT(id) FROM ProductsEntity p WHERE (p.itemName LIKE '%Mocha%' OR p.itemName LIKE '%Coffee%') AND p.fiber = 0";
        Query queryD = em.createQuery(queryStringD);
        Long resultD = (Long) queryD.getSingleResult();
        System.out.println("Liczba kaw niezawierających błonnika dla podpunktu d: " + resultD);

        var queryStringE = "SELECT 4.184 * calories FROM ProductsEntity p WHERE p.itemName LIKE '%McMuffin%' ORDER BY p.calories DESC";
        Query queryE = em.createQuery(queryStringE);
        List<Object> resultE = queryE.getResultList();
        System.out.println("Kaloryczność wszystkich McMuffinów w kJ dla podpunktu e: " + resultE);

        var queryStringF = "SELECT COUNT(DISTINCT carbs) FROM ProductsEntity p";
        Query queryF = em.createQuery(queryStringF);
        Long resultF = (Long) queryF.getSingleResult();
        System.out.println("Liczba różnych wartości węglowodanów dla podpunktu f: " + resultF);
    }
}
